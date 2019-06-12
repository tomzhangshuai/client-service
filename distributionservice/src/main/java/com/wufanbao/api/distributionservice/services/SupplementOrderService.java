package com.wufanbao.api.distributionservice.services;

import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.dao.*;
import com.wufanbao.api.distributionservice.entities.*;
import com.wufanbao.api.distributionservice.rabbitMQ.Sender;
import com.wufanbao.api.distributionservice.tools.RedisUtil;
import com.wufanbao.api.distributionservice.tools.RedisUtils;
import com.wufanbao.api.distributionservice.transfer.SupplementOpenInfo;
import com.wufanbao.api.distributionservice.transfer.SupplementOrderLineInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SupplementOrderService {

    @Autowired
    SupplementOrderDao supplementOrderDao;

    @Autowired
    SupplementOrderLineDao supplementOrderLineDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    DistributionProductLineDao distributionProductLineDao;

    @Autowired
    DistributionOrderLineDao distributionOrderLineDao;
    @Autowired
    private MachineDao machineDao;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    Sender sender;
    @Autowired
    private ProductGlobalDao productGlobalDao;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * OpenMachine_
     */
    private final String RedisStoreOpenMachineKeyPrefix="OpenMachine_";

    /**
     * 计算下架商品
     * 1、获取车辆库存
     * 2、获取机器库存
     * 3、获取补货明细
     * 3、判断计算是否需要额外下货
     * 4、需要额外下货，判断车辆是否携带多余库存
     * 5、机器可下货数计算quantity=plan+extra
     * @param distributionOrderLine
     * @return
     */
    public Object computePreDumping(DistributionOrderLine distributionOrderLine)
    {
        List<DistributionProductLine> distributionProducts=distributionProductLineDao.getOrderProductLines(distributionOrderLine.getDistributionOrderId());

        List<Product> machineProducts=productDao.getMachineProductsBySupplementOrderId(distributionOrderLine.getSupplementOrderId());

        List<SupplementOrderLineInfo> lines=supplementOrderLineDao.getOrderDetail(distributionOrderLine.getSupplementOrderId());

        //
        boolean isLockQuantity=false;

        //
        int extraQuantity=0;
        int dumpQuantity=0;
        Product machineProduct=null;
        DistributionProductLine distributionProduct=null;
        for(SupplementOrderLineInfo line:lines){
            machineProduct=null;
            distributionProduct=null;
            for (DistributionProductLine p :distributionProducts){
                if(p.getProductGlobalId()==line.getProductGlobalId()) {
                    distributionProduct=p;
                    break;
                }
            }
            for (Product p :machineProducts){
                if(p.getProductGlobalId()==line.getProductGlobalId()) {
                    machineProduct=p;
                    break;
                }
            }
            if(machineProduct==null||distributionProduct==null)
            {
                //车辆或着机器中不存在相应的商品信息 ，不卸货上架
                continue;
            }

            if(line.isLockQuantity()) {
                extraQuantity=0;
            }else {
                extraQuantity=0;
                if (line.getExpectQuantity() - machineProduct.getQuantity() > line.getQuantity()) {
                    extraQuantity=line.getExpectQuantity()-machineProduct.getQuantity()-line.getQuantity();
                }
            }
            if(distributionProduct.getExtraQuantity()<extraQuantity) {
                extraQuantity=distributionProduct.getExtraQuantity();
            }
            if(extraQuantity<0){
                extraQuantity=0;
            }
            //计算得到下货数量
            dumpQuantity=line.getQuantity()+extraQuantity;
            if(dumpQuantity>distributionProduct.getSurplusQuantity()) {
                dumpQuantity=distributionProduct.getSurplusQuantity();//下货数量大于配送剩余数量   下货数量=配送剩余数量
            }
            if(dumpQuantity<0) {
                dumpQuantity=0;
            }
            line.setDumpQuantity(dumpQuantity);

        }
        /**
         * 兼容老接口
         */
        Map<String,Object> goodsMap=new HashMap<String,Object>();
        for(SupplementOrderLineInfo line:lines)
        {
            goodsMap.put(String.valueOf(line.getProductGlobalId()),line.getDumpQuantity());
        }

        Map<String,Object> returnMap=new HashMap<String,Object>();
        returnMap.put("data",lines);
        returnMap.put("goods",goodsMap);
        returnMap.put("status",0);
        return  returnMap;
    }


    /**
     * 卸货
     * 1、更新机器卸货数量
     * 2、得到机器额外携带数量（quantity-plan)
     * 3、减少车辆库存及多余库存
     * @return
     */
    @Transactional(rollbackFor = CodeException.class)
    public void dump(long distributionOrderId, long supplementOrderId,SupplementOrderLine[] lines)throws Exception{
        if(supplementOrderId<1||lines==null) {
            throw new CodeException(Code.dataError.getCode(),"下货请求数据不正确");
        }
        if(distributionOrderId<1){
            distributionOrderId= distributionOrderLineDao.getDistributionOrderIdBySupplementOrderId(supplementOrderId);
        }

        if(distributionOrderId<1)
        {
            throw new CodeException(Code.dataError.getCode(),"下货请求数据不正确");
        }

        List<SupplementOrderLine> dblines=supplementOrderLineDao.getSupplementOrderLines(supplementOrderId);
        SupplementOrderLine modifyLine=null;
        int extraQuantity=0;        //额外配送发货数量,
        int dumpQuantity=0;         //下货商品数量
        int changes=0;
        for (SupplementOrderLine dbline: dblines) {
            modifyLine=null;
            for(SupplementOrderLine line:lines){
                if(line.getProductGlobalId()==dbline.getProductGlobalId())
                {
                    modifyLine=line;
                    break;
                }
            }
            if(modifyLine==null)
            {
                continue;
            }
            dumpQuantity=modifyLine.getDumpQuantity();
            extraQuantity=dumpQuantity-dbline.getQuantity();  //额外配送发货数量=下货数量-quantity预计商品配送数量
            if(extraQuantity<0)
            {
                extraQuantity=0;
            }

            if(dumpQuantity>dbline.getExpectQuantity())
            {
                String productName=productGlobalDao.getProductNameById(dbline.getProductGlobalId());
                throw new CodeException(Code.dataError.getCode(),"编号为"+productName+"的商品：下货数量"+dumpQuantity+"大于期望补货数量"+dbline.getExpectQuantity());
            }

            changes=supplementOrderLineDao.dumpProduct(dbline.getSupplementOrderId(),dbline.getProductGlobalId(),dumpQuantity);
            if(changes<1)
            {
                throw new CodeException(Code.dataError.getCode(),"更新下货商品时发生异常");
            }
            changes=distributionProductLineDao.dump(distributionOrderId,dbline.getProductGlobalId(),dumpQuantity,extraQuantity);
            if(changes<1)
            {
                throw new CodeException(Code.dataError.getCode(),"下货失败:在途库存不足"+"下货数量："+dumpQuantity+"额外配送数量"+extraQuantity);
            }
        }

        //更新补货单状态到6
        changes=supplementOrderDao.setSupplementStatus(supplementOrderId,6);
        if(changes<1)
        {
            throw new CodeException(Code.dataInvaild.getCode(),"补货单状态已改变");
        }

        //更新配送信息
        distributionOrderLineDao.dumpArrived(distributionOrderId,supplementOrderId);
    }

    /**
     *获取前仓上架明细
     * @return
     */
    public Object getFrontDetail(long supplementOrderId){
        return supplementOrderLineDao.getShelveDetail(supplementOrderId,0);
    }

    /**
     * 确认前仓补货完成
     * 更改补货单的
     * @return
     */
    @Transactional(rollbackFor = CodeException.class)
    public void confirmFront(long supplementOrderId,SupplementOrderLine[] lines)throws Exception{

        List<SupplementOrderLine> dbLines=supplementOrderLineDao.getSupplementOrderLines(supplementOrderId);
        SupplementOrderLine line=null;
        int changed=0;

        // 更新数据库记录，line 以第一条匹配为准
        for(SupplementOrderLine dbOrderLine:dbLines)
        {
            line=null;
            for(SupplementOrderLine l:lines){
                if(l.getProductGlobalId()==dbOrderLine.getProductGlobalId()) {
                    line=l;
                    break;
                }
            }
            if(line==null){
                continue;
            }
            int backQuantity=dbOrderLine.getDumpQuantity()-line.getActualQuantity();
            if(backQuantity<0){
                throw  new CodeException(Code.dataError.getCode(),"发生数据错误：上架数大于下货数");
            }
            dbOrderLine.setActualQuantity(line.getActualQuantity());
            dbOrderLine.setBackQuantity(backQuantity);
            changed=supplementOrderLineDao.shelveFrontProducts(dbOrderLine);
            if(changed<1){
                throw  new CodeException(Code.dataError.getCode(),"更新补货单明细失败");
            }
            changed=productDao.shelveFront(dbOrderLine.getActualQuantity(),dbOrderLine.getMachineId(),dbOrderLine.getProductGlobalId());
            if(changed<1) {
                throw  new CodeException(Code.dataError.getCode(),"更新机器库存明细失败");
            }
        }

        changed=supplementOrderDao.setSupplementStatus(supplementOrderId,8);
        if(changed<1)
        {
            throw  new CodeException(Code.dataError.getCode(),"更新补货单状态失败");
        }
    }

    /**
     *获取后仓上架明细
     * @return
     */
    public Object getBehindDetail(long supplementOrderId){
        return supplementOrderLineDao.getShelveDetail(supplementOrderId,1);
    }

    /**
     * 后仓确认   完成补货
     * 1、查询数据库数据
     * 2、对比数量
     * 3、发送补货完成消息
     * @param supplementOrderId
     * @param lines
     * @throws Exception
     */
    @Transactional(rollbackFor = CodeException.class)
    public void confirmBehind(long distributionOrderId,long supplementOrderId,SupplementOrderLine[] lines)throws Exception{
        List<SupplementOrderLine> dbLines=supplementOrderLineDao.getSupplementOrderLines(supplementOrderId);
        SupplementOrderLine line=null;
        int changed=0;
        // 更新数据库记录，line 以第一条匹配为准
        for(SupplementOrderLine dbOrderLine:dbLines){
            line=null;
            for(SupplementOrderLine l:lines){
                if(l.getProductGlobalId()==dbOrderLine.getProductGlobalId()) {
                    line=l;
                    break;
                }
            }
            if(line==null){
                continue;
            }
            if( line.getExceptionQuantity()<0||line.getBackQuantity()<0) {
                throw  new CodeException(Code.dataError.getCode(),"发生数据错误：补货数量异常");
            }
            if(dbOrderLine.getDumpQuantity()!=(dbOrderLine.getActualQuantity()+line.getBackQuantity())){
                throw  new CodeException(Code.dataError.getCode(),"发生数据错误：上架数"+dbOrderLine.getActualQuantity()+
                        ",退回数"+line.getBackQuantity()+"和不等于下货数"+dbOrderLine.getDumpQuantity());
            }
            if(line.getExceptionQuantity()>line.getBackQuantity()) {
                throw  new CodeException(Code.dataError.getCode(),"发生数据错误：异常数量大于退回数");
            }
            dbOrderLine.setBackQuantity(line.getBackQuantity());
            dbOrderLine.setExceptionQuantity(line.getExceptionQuantity());
            changed=supplementOrderLineDao.shelveBehindProducts(dbOrderLine);
            if(changed<1){
                throw  new CodeException(Code.dataError.getCode(),"更新补货单明细失败");
            }
        }

        //更新配送单库存信息(包含前仓数据）
        for(SupplementOrderLine dbOrderLine :dbLines){
            //有实际上架数量或者退回数量，更新配送单数据
            if(dbOrderLine.getBackQuantity()>0||dbOrderLine.getActualQuantity()>0){
                //实际可用退回数量=退回数量-异常数量
                changed=distributionProductLineDao.shelve(
                        distributionOrderId,
                        dbOrderLine.getProductGlobalId(),
                        dbOrderLine.getActualQuantity(),
                        dbOrderLine.getBackQuantity()-dbOrderLine.getExceptionQuantity(),
                        dbOrderLine.getExceptionQuantity());
                if(changed<1) {
                    throw  new CodeException(Code.dataError.getCode(),"更新配送单明细失败");
                }
            }
        }
        changed=supplementOrderDao.setSupplementStatus(supplementOrderId,10);
        if(changed<1){
            throw  new CodeException(Code.dataError.getCode(),"更新补货单状态失败");
        }
        changed=supplementOrderDao.setSupplementCompletedTime(supplementOrderId);
        if(changed<1){
            throw new CodeException(Code.dataError.getCode(),"更新补货单时间失败");
        }
        changed=distributionOrderLineDao.completeSupplement(distributionOrderId,supplementOrderId);
        if(changed<1) {
            throw  new CodeException(Code.dataError.getCode(),"更新配送单明细失败");
        }
        long machineId=supplementOrderDao.getMachineId(supplementOrderId);
        boolean inmaintenance=machineDao.getMachineInmaintenance(machineId);
        String machineNo=machineDao.getMachineNo(machineId);
        String key="SupplementProductInfo_"+machineNo+"_"+supplementOrderId;
        if(!inmaintenance){
            Map<String,String> map=redisUtils.getAll(key);
            for (Map.Entry<String,String> entry : map.entrySet()) {
                logger.info("reids缓存读取:"+entry.getValue());
                if (entry.getValue().equals("0")){
                    throw new CodeException(Code.dataError.getCode(),"补货未完成请等待，稍后再试");
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("SupplementOrderId", supplementOrderId);
        sender.supplementCompleted(jsonObject.toJSONString());
//        redisUtil.del(key);
    }

    /**
     * 回退状态
     * @param supplementOrderId
     * @throws Exception
     */
    public void backStatus(long supplementOrderId) throws Exception
    {
        int status= supplementOrderDao.getOrderStatus(supplementOrderId);

        //状态为补前仓中或者补后仓中，回退到前一状态
        if(status==7|| status==9)
        {
            supplementOrderDao.setSupplementStatus(supplementOrderId,status-1);
        }else {
            throw new CodeException(Code.dataInvaild.getCode(),"订单状态已改变");
        }
    }

    /**
     * 扫码机器是否存在ID为supplemnetOrderId 的补货单
     * @param scanedMachineId 被扫码机器
     * @param supplemnetOrderId 的补货单ID
     */
    public void  IsScanedMachineSupplementOrder(long scanedMachineId,long supplemnetOrderId) throws Exception {
        int count=supplementOrderDao.isExistSupplementOrder(scanedMachineId,supplemnetOrderId);
        if(count<1)
        {
            throw new CodeException(Code.dataError.getCode(),"被扫码机器与补货单不匹配");
        }
    }

    /**
     * 获取补货单明细
     * @param supplementOrderId 补货单ID
     * @return
     */
    public List<SupplementOrderLineInfo> getDetail(long supplementOrderId)
    {
        return supplementOrderLineDao.getOrderDetail(supplementOrderId);
    }
    /**
     * 获取补货单状态
     * @param supplementOrderId
     * @return
     */
    public Object getOrderStatus(long supplementOrderId)
    {
        int supplementStatus=supplementOrderDao.getOrderStatus(supplementOrderId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("supplementStatus",supplementStatus);
        return map;
    }

    /**
     * 扫码
     * @param supplementOpenInfo 扫码信息
     * @return
     */
    public void scan(SupplementOpenInfo supplementOpenInfo) throws Exception{
        int existCount=supplementOrderDao.isExistSupplementOrder(supplementOpenInfo.getMachineId(), supplementOpenInfo.getSupplementOrderId());
        if(existCount<1)
        {
            throw new CodeException(Code.dataError.getCode(),"被扫码机器与补货单不匹配");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = simpleDateFormat.format(new Date());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("machineId", supplementOpenInfo.getMachineId());
        jsonObject.put("supplementOrderId", supplementOpenInfo.getSupplementOrderId());
        jsonObject.put("createTime", now);
        String content=jsonObject.toJSONString();
        redisUtils.set(RedisStoreOpenMachineKeyPrefix + String.valueOf(supplementOpenInfo.getMachineId()), content,3600);

    }


    /**
     * 开前仓
     * @param supplementOrderId
     */
    public void openFront(long supplementOrderId) throws Exception{

        long machineId=supplementOrderDao.getMachineId(supplementOrderId);
        if(machineId<1)
        {
            throw new CodeException(Code.dataError.getCode(),"补货单信息有误");
        }

        if(!redisUtils.exists(RedisStoreOpenMachineKeyPrefix+String.valueOf(machineId))) {
            //未找到补货单的开车操作信息，需要重新扫码
            throw new CodeException(Code.scanInvaild);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("SupplementOrderId", supplementOrderId);

        sender.openMachineFront(jsonObject.toJSONString());
    }

    /**
     * 开后仓
     * @param supplementOrderId
     * @throws Exception
     */
    public void openBehind(long supplementOrderId) throws Exception{
        long machineId=supplementOrderDao.getMachineId(supplementOrderId);
        if(machineId<1)
        {
            throw new CodeException(Code.dataError.getCode(),"补货单信息有误");
        }
        if(!redisUtils.exists(RedisStoreOpenMachineKeyPrefix+String.valueOf(machineId))) {
            //未找到补货单的开车操作信息，需要重新扫码
            throw new CodeException(Code.scanInvaild);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MachineId", machineId);
        jsonObject.put("SupplementOrderId", supplementOrderId);
        sender.openMachineBehind(jsonObject.toJSONString());
    }
}
