package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.dao.SupplementOrderSureDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionOrderLineService;
import com.wufanbao.api.olddriverservice.service.GoodsUnderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User:Wangshihao
 * Date:2017-09-08
 * Time:14:43
 */
@Controller
@RequestMapping("goodsUnder")
public class GoodsUnderController {

    @Autowired
    private GoodsUnderService goodsUnderService;
    @Autowired
    private DistributionOrderLineService distributionOrderLineService;
    @Autowired
    private SupplementOrderSureDao supplementOrderSureDao;
    @Autowired
    private ReturnParameter returnParameter;


    /**
     * 下货详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "goodsUnderList", method = RequestMethod.POST)
    @ResponseBody
    public Object GoodsUnderList(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();
        DistributionOrderLine distributionOrderLine = JSON.parseObject(requestInfo.getData(), DistributionOrderLine.class);
        //补货单ID
        long supplementOrderId = distributionOrderLine.getSupplementOrderId();
        //配送单ID
        long distributionOrderId = distributionOrderLine.getDistributionOrderId();
        GoodsMax goodsMax = new GoodsMax();
        //最大补货数Map
        Map map = maxGoods(supplementOrderId);
        //商品信息
        Object info = infoGoods(supplementOrderId, requestInfo);
        Map map1 = new HashMap();
        //补货单详情 商品数量 和 商品ID
        List<SupplementOrderLine> supplementOrderLineList = goodsUnderService.qureySupp(supplementOrderId);
        //如果补货单商品不为0
        if (supplementOrderLineList.size() != 0) {
            for (int i = 0; i < supplementOrderLineList.size(); i++) {
                //补货单商品ID
                long productGlobalId = supplementOrderLineList.get(i).getProductGlobalId();
                //预计配送商品数量
                int quantity = supplementOrderLineList.get(i).getQuantity();
                //查询配送单中该商品的数量
                List<DistributionProductLine> distributionproductlineList = goodsUnderService.qureyDBPL(distributionOrderId, productGlobalId);
                for (int j = 0; j < distributionproductlineList.size(); j++) {
                    DistributionProductLine distributionproductline = distributionproductlineList.get(j);
                    //额外配送数量
                    int extraQuantity = distributionproductline.getExtraQuantity();
                    int surplusQuantity = distributionproductline.getSurplusQuantity();
//                    //异常数量
//                    int exceptionQuantity=distributionproductline.getExceptionQuantity();
                    //商品ID
                    String productGlobalId1 = String.valueOf(productGlobalId);
                    //机器内实际需要的商品数量
                    int shiji = (int) map.get(productGlobalId1);

                    //剩余数不足已补充商品实际补货数，按照剩余数
                    if (shiji > surplusQuantity) {
                        shiji = surplusQuantity;
                    }
                    //实际下货数量
                    if (quantity + extraQuantity >= shiji) {
                        //使用的额外数量

                        map1.put(productGlobalId1, shiji);
                    } else {
                        int num = quantity + extraQuantity;

                        map1.put(productGlobalId1, num);
                    }
                    //配送单商品详情表下货

                }
            }
            goodsMax.setData(info);
            goodsMax.setGoods(map1);
            responseInfo.setCode(1);
            responseInfo.setData(goodsMax);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2271);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    public Object infoGoods(Long supplementOrderId, RequestInfo requestInfo) {
        List<GoodsUnder> goodsUnderList = goodsUnderService.queryGoodsUnders(supplementOrderId);
        if (goodsUnderList.size() != 0) {
            return goodsUnderList;
        }
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(2272);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    public Map maxGoods(Long supplementOrderId) {
        //根据补货单号查询机器ID
        SupplementOrder supplementOrder = goodsUnderService.queryGoodsUnder(supplementOrderId);
        //机器ID
        long machineId = supplementOrder.getMachineId();

        Map map = new HashMap();
        //查询机器上所有商品的数量
        List<Product> productList = goodsUnderService.ProductInfo(machineId);
        for (Product product : productList) {
            map.put(product.getProductGlobalId(), product.getQuantity());
        }

        //将机器内所有的商品一次放到MAP中 商品ID为键，商品数量为值
        Map productMap = new HashMap();
        List<SupplementOrderLine> supplementOrderLineList = goodsUnderService.SupplementOrderInfo(supplementOrderId);//获取补货单的详细信息
        for (SupplementOrderLine supplementOrderLine : supplementOrderLineList) {
            Long productGlobalId = supplementOrderLine.getProductGlobalId();
            int quantity = (int) map.get(productGlobalId);//商品库存
            if (supplementOrderLine.getOffProductGlobalId() != 0) {
                int offQuantity = (int) map.get(supplementOrderLine.getOffProductGlobalId());//下架商品数
                //商品是否需要补货 商品是否下架 商品Id是否存在所有商品中
                int offnum = (int) offQuantity; //被下架的商品数量
                int linenum = (int) quantity; //现有的商品数量
                //所占的阁子
                int productNum = offnum % 3 == 0 ? offnum / 3 : offnum / 3 + 1;
                //需要补货的商品最大期望值
                int pronum = supplementOrderLine.getExpectQuantity() / 3;
                //实际可以放的商品数量
                int count = (pronum - productNum) * 3 - linenum;
                //需要补货的商品
                productMap.put(String.valueOf(supplementOrderLine.getProductGlobalId()), count);
            } else {
                if (supplementOrderLine.getExpectQuantity() > quantity) {
                    productMap.put(String.valueOf(productGlobalId), supplementOrderLine.getExpectQuantity() - quantity);
                } else {
                    productMap.put(String.valueOf(productGlobalId), 0);
                }
            }
        }
        return productMap;
    }

    /**
     * 下货数量
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateSupplement", method = RequestMethod.POST)
    @ResponseBody
    public Object UpdateSupplement(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        long supplementOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("supplementOrderId").toString());
        String quantityGather = JSONObject.parseObject(requestInfo.getData()).getString("quantityGather").toString();
        ResponseInfo responseInfo = new ResponseInfo();
        //配送单ID
        DistributionOrderLine distributionOrderLine1 = distributionOrderLineService.queryId(supplementOrderId);
        long distributionOrderId = distributionOrderLine1.getDistributionOrderId();

        List<Inet> InetList = JSON.parseArray(quantityGather, Inet.class);
        if (InetList.size() != 0) {
            for (int j = 0; j < InetList.size(); j++) {
                Inet inet = InetList.get(j);
                String gather = inet.getGather();
                System.out.println(gather);
                String pid = gather.substring(0, gather.lastIndexOf(","));
                //商品Id
                long productGlobalId = Long.valueOf(pid);
                String quan = gather.substring(gather.indexOf(",") + 1);
                //下货数量
                int xiaquantity = Integer.valueOf(quan);
                SupplementOrderLine SOL = new SupplementOrderLine();
                SOL.setDumpQuantity(xiaquantity);
                SOL.setSupplementOrderId(supplementOrderId);
                SOL.setProductGlobalId(productGlobalId);
                int ss = goodsUnderService.updateSupplementOrderLint(SOL);
                SupplementOrder supplementOrder = new SupplementOrder();
                supplementOrder.setSupplementStatus(6);
                supplementOrder.setSupplementOrderId(supplementOrderId);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = simpleDateFormat.format(new Date());
                supplementOrder.setShelvingTime(time);
                goodsUnderService.updateSupplementOrderStatus(supplementOrder);
                int quantity = supplementOrderSureDao.getQuantityBySupplementOrderId(supplementOrderId, productGlobalId);
                int useExtraQuantity = quantity - xiaquantity;
                int dumpQuantity = xiaquantity;
                supplementOrderSureDao.updateDistributionProductLine(distributionOrderId, productGlobalId, null, useExtraQuantity, null, -dumpQuantity);
            }

            DistributionOrderLine distributionOrderLine = new DistributionOrderLine();
            distributionOrderLine.setIsDistributed(0);
            distributionOrderLine.setDistributionOrderId(distributionOrderId);
            distributionOrderLine.setSupplementOrderId(supplementOrderId);
            distributionOrderLineService.updateArriveTime(distributionOrderLine);
            responseInfo.setCode(1);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;

        }
        responseInfo.setCode(2261);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }
}
