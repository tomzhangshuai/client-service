package com.wufanbao.api.olddriverservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.olddriverservice.Tool.IDGenerator;
import com.wufanbao.api.olddriverservice.Tool.ReturnParameter;
import com.wufanbao.api.olddriverservice.controller.RabbitMQ.QueueSend;
import com.wufanbao.api.olddriverservice.dao.DistributionOrderDao;
import com.wufanbao.api.olddriverservice.dao.GoodsUnderDao;
import com.wufanbao.api.olddriverservice.dao.MachineStorageDao;
import com.wufanbao.api.olddriverservice.dao.SupplementOrderSureDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.DistributionOrderLineService;
import com.wufanbao.api.olddriverservice.service.GoodsUnderService;
import com.wufanbao.api.olddriverservice.service.MachineService;
import com.wufanbao.api.olddriverservice.service.SupplementOrderSureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-11
 * Time:16:01
 */
@Controller
@RequestMapping("supplementOrderInfo")
public class SupplementOrderSureController {

    @Autowired
    private SupplementOrderSureService supplementOrderSureService;
    @Autowired
    private GoodsUnderService goodsUnderService;
    @Autowired
    private MachineService machineService;
    @Autowired
    private GoodsUnderDao goodsUnderDao;
    @Autowired
    private MachineStorageDao machineStorageDao;
    @Autowired
    private DistributionOrderLineService distributionOrderLineService;
    @Autowired
    private DistributionOrderDao distributionOrderDao;
    @Autowired
    private SupplementOrderSureDao supplementOrderSureDao;
    @Autowired
    private QueueSend queueSend;
    @Autowired
    private ReturnParameter returnParameter;

    /**
     * 获取商品名字，id，图片
     *
     * @return
     */
    @RequestMapping(value = "productAll", method = RequestMethod.POST)
    @ResponseBody
    public Object productAll(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        List<ProductGlobal> productGlobalList = supplementOrderSureService.productGlobalAll();

        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(productGlobalList);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 填前仓详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "supplementOrderDetails", method = RequestMethod.POST)
    @ResponseBody
    public Object SupplementOrderDetails(String request) {

        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        // if (count==1){
        // DistributionOrder distributionOrder=JSON.parseObject(requestInfo.getData(),DistributionOrder.class);
        long supplementOrderId = JSON.parseObject(requestInfo.getData(), SupplementOrder.class).getSupplementOrderId();
        // Long supplementOrderId = Long.valueOf(request.getParameter("supplementOrderId"));
        List<SupplementOrderSure> supplementOrderSureList = supplementOrderSureService.querySupplementOrderSure(supplementOrderId);
        Map map = new HashMap();
        System.out.println(supplementOrderSureList.size() + "siz");
        if (supplementOrderSureList.size() != 0) {
            SupplementOrder supplementOrder = new SupplementOrder();
            supplementOrder.setSupplementStatus(7);
            supplementOrder.setSupplementOrderId(supplementOrderId);
            goodsUnderService.updateSupplementOrderStatus(supplementOrder);
            responseInfo.setCode(1);
            responseInfo.setData(supplementOrderSureList);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        if (supplementOrderSureList.size() == 0) {
            HeadTool headTool = supplementOrderSureService.queryHeadTool(supplementOrderId);
            responseInfo.setCode(2242);
            responseInfo.setData(headTool);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2241);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 填后仓详情接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "supplementOrderhou", method = RequestMethod.POST)
    @ResponseBody
    public Object SupplementOrderhou(String request) {
        ResponseInfo responseInfo = new ResponseInfo();
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        //int count=checkRequest.checkRequest(requestInfo);

        SupplementOrder supplementOrder1 = JSON.parseObject(requestInfo.getData(), SupplementOrder.class);
        long supplementOrderId = supplementOrder1.getSupplementOrderId();
        List<SupplementOrderSure> supplementOrderSureList = supplementOrderSureService.querysupplementOrderhou(supplementOrderId);
        Map map = new HashMap();
        if (supplementOrderSureList.size() != 0) {
            SupplementOrder supplementOrder = new SupplementOrder();
            supplementOrder.setSupplementStatus(9);
            supplementOrder.setSupplementOrderId(supplementOrderId);
            goodsUnderService.updateSupplementOrderStatus(supplementOrder);
            //map.put("return", supplementOrderSureList);
            responseInfo.setCode(1);
            responseInfo.setData(supplementOrderSureList);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        if (supplementOrderSureList.size() == 0) {
            HeadTool headTool = supplementOrderSureService.queryHeadTool(supplementOrderId);
            responseInfo.setCode(2232);
            responseInfo.setData(headTool);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            //map.put("return", headTool);
            return responseInfo;
        }
        responseInfo.setCode(2231);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 填前仓确定
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "supplementOrderSure", method = RequestMethod.POST)
    @ResponseBody
    public Object SupplementOrderSure(String request) {
        ResponseInfo responseInfo = new ResponseInfo();
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        long supplementOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("supplementOrderId").toString());
        String quantityGather = JSONObject.parseObject(requestInfo.getData()).getString("quantityGather").toString();
        Long distributionOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("distributionOrderId").toString());
        int dumpQuantity = 0;
        SupplementOrder supplementOrders = goodsUnderService.queryGoodsUnder(supplementOrderId);
        long machineId = supplementOrders.getMachineId();
        List<Inet> InetList = JSON.parseArray(quantityGather, Inet.class);

        if (InetList.size() != 0) {
            for (int j = 0; j < InetList.size(); j++) {
                Inet inet = InetList.get(j);
                String gather = inet.getGather();
                System.out.println(gather);
                String pid = gather.substring(0, gather.lastIndexOf(","));
                long productGlobalId = Long.valueOf(pid);
                System.out.println("id>>>>>>>>>>>>" + productGlobalId);
                String quan = gather.substring(gather.indexOf(",") + 1);
                int actualQuantitys = Integer.valueOf(quan);//上架数量
                List<SupplementOrderLine> supplementOrderLines = supplementOrderSureService.querySMT(supplementOrderId, productGlobalId);
                for (int i = 0; i < supplementOrderLines.size(); i++) {
                    SupplementOrderLine supplementOrderLine = supplementOrderLines.get(i);
                    dumpQuantity = supplementOrderLine.getDumpQuantity();
                }
                System.out.println(dumpQuantity + ">>>>>>>>>>>>>>>>>>>>>>>>>>>::::::::");
                int kk = distributionline(distributionOrderId, productGlobalId, actualQuantitys, dumpQuantity);
                if (kk == 0) {
                    SupplementOrderLine SOL = new SupplementOrderLine();
                    SOL.setActualQuantity(actualQuantitys);
                    SOL.setSupplementOrderId(supplementOrderId);
                    SOL.setProductGlobalId(productGlobalId);
                    supplementOrderSureService.updateSupplementOrderLine(SOL);
                    Product product = machineStorageDao.getProduct(machineId, productGlobalId);
                    machineStorageDao.updateProduct(machineId, productGlobalId, actualQuantitys + product.getQuantity(), actualQuantitys - product.getLockQuantity() + product.getQuantity());
                }
            }
            SupplementOrder supplementOrder = new SupplementOrder();
            supplementOrder.setSupplementStatus(8);
            supplementOrder.setSupplementOrderId(supplementOrderId);
            goodsUnderService.updateSupplementOrderStatus(supplementOrder);
            responseInfo.setCode(1);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        } else {
            responseInfo.setCode(2311);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
    }

    /**
     * 前仓修改distributionproductline
     */
    public int distributionline(long distributionOrderId, long productGlobalId, int actualQuantitys, int dumpQuantity) {
//        List<DistributionProductLine> distributionproductlines = supplementOrderSureService.queryDistributionproductline(distributionOrderId,productGlobalId);
//        if (distributionproductlines.size() != 0) {
//            for (int i = 0; i < distributionproductlines.size(); i++) {
//                DistributionProductLine distributionproductline = distributionproductlines.get(i);
//                int extraQuantity = distributionproductline.getExtraQuantity();
//                int actualQuantity = distributionproductline.getActualQuantity();
//                int surplusQuantity = distributionproductline.getSurplusQuantity();
//                DistributionProductLine distributionproductline1 = new DistributionProductLine();
//                //不改变额外值
//                distributionproductline1.setExtraQuantity(extraQuantity);
//                distributionproductline1.setActualQuantity(actualQuantity + actualQuantitys);
//                System.out.println(surplusQuantity-dumpQuantity+"<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>");
//                distributionproductline1.setSurplusQuantity(surplusQuantity - dumpQuantity);
//                distributionproductline1.setProductGlobalId(productGlobalId);
//                distributionproductline1.setDistributionOrderId(distributionOrderId);
//                supplementOrderSureService.updateDistributionproductline(distributionproductline1);
//    }
        int backQuantity = dumpQuantity - actualQuantitys;
        int count = supplementOrderSureDao.updateDistributionProductLine(distributionOrderId, productGlobalId, null, backQuantity, actualQuantitys, backQuantity);
        if (count > 0) {
            return 0;
        }
        return 1;
//        }
//        return 1;
    }

    /**
     * 后仓确定
     */
    @RequestMapping(value = "supplementOrderAfter", method = RequestMethod.POST)
    @ResponseBody
    public Object supplementOrderAfter(String request) throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //补货单ID
        long supplementOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("supplementOrderId").toString());
        //配送单ID
        long distributionOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("distributionOrderId").toString());
        String quantityGather = JSONObject.parseObject(requestInfo.getData()).getString("quantityGather").toString();
        //long distributionOrderId = Long.valueOf(request.getParameter("distributionOrderId"));
        //long supplementOrderId = Long.valueOf(request.getParameter("supplementOrderId"));
        //String quantityGather = request.getParameter("quantityGather");//数量

        int statuszero = afterStatuszero(distributionOrderId, supplementOrderId, quantityGather);
        //String productNamesGather =JSONObject.parseObject(requestInfo.getData()).getString("productNamesGather").toString();
        if (statuszero == 0) {
//            boolean b = productNamesGather.equals("0");
//            if (b==false) {
//                int statusone = afterStatusone(productNamesGather,supplementOrderId);
//                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<ssss"+statusone);
//            }
            SupplementOrder supplementOrder = new SupplementOrder();
            supplementOrder.setSupplementStatus(10);
            supplementOrder.setSupplementOrderId(supplementOrderId);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ss = simpleDateFormat.format(new Date());
            supplementOrder.setShelvedTime(ss);
            goodsUnderService.updateSupplementOrderStatus(supplementOrder);
            DistributionOrderLine distributionOrderLine = new DistributionOrderLine();
            distributionOrderLine.setIsDistributed(1);
            distributionOrderLine.setDistributionOrderId(distributionOrderId);
            distributionOrderLine.setSupplementOrderId(supplementOrderId);
            distributionOrderLineService.updateDistributedTime(distributionOrderLine);
            org.json.JSONObject jjjj = new org.json.JSONObject();
            jjjj.put("supplementOrderId", supplementOrderId);
            SupplementOrder supplementOrder1 = goodsUnderService.queryGoodsUnder(supplementOrderId);
            long machineId = supplementOrder1.getMachineId();
            jjjj.put("machineId", machineId);
            queueSend.deliveryOver(jjjj);//MQMQ确人补货单完成
            responseInfo.setCode(1);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2301);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    @RequestMapping(value = "testOrderAfter")
    @ResponseBody
    public Object test() throws Exception {
        long supplementOrderId = 579441459204L;
        org.json.JSONObject jjjj = new org.json.JSONObject();
        jjjj.put("supplementOrderId", supplementOrderId);
        long machineId = 194752675841L;
        jjjj.put("machineId", machineId);
        queueSend.deliveryOver(jjjj);
        return "succeed";
    }

    public int afterStatuszero(long distributionOrderId, Long supplementOrderId, String quantityGather) {
        //填仓后的详情
        List<Quantity> quantityList = JSON.parseArray(quantityGather, Quantity.class);
        //根据补货单ID 获取机器编码
        long machineId = goodsUnderService.queryGoodsUnder(supplementOrderId).getMachineId();
        int lockQuantity = 0;
        int quantityss = 0;
        //获取机器内商品数量的情况
        List<Machine> machineList = machineService.queryMachine(machineId);
        for (int y = 0; y < machineList.size(); y++) {
            Machine machine = machineList.get(y);
            //锁定的商品数量
            lockQuantity = machine.getLockQuantity();
        }
        for (int i = 0; i < quantityList.size(); i++) {
            Quantity quantity = quantityList.get(i);
            //商品ID
            long productGlobalId = quantity.getProductGlobalId();
            //上架数量;
            String actualQuantit = quantity.getActualQuantity();
            int actualQuantity = Integer.valueOf(actualQuantit);
            //异常数量
            String exceptionQuantit = quantity.getExceptionQuantity();
            int exceptionQuantity = Integer.valueOf(exceptionQuantit);
            //退回数量
            String backQuantit = quantity.getBackQuantity();
            int backQuantity = Integer.valueOf(backQuantit);
            //下货数量
            List<SupplementOrderLine> supplementOrderLines = supplementOrderSureService.querySMT(supplementOrderId, productGlobalId);
//                for (int j = 0; j < supplementOrderLines.size(); j++) {
//                    SupplementOrderLine supplementOrderLine = supplementOrderLines.get(j);
//                    //下货数量
//                    int dumpQuantity = supplementOrderLine.getDumpQuantity();
//                    //下货数量+退回数量
//                   // quantityss = dumpQuantity+backQuantity;
//                }
            SupplementOrderLine supplementOrderLine = goodsUnderDao.supplementOrderInfoOne(supplementOrderId, productGlobalId);
            //像配送单详情中添加数量
            int kk = distributionlinehou(distributionOrderId, productGlobalId, actualQuantity, backQuantity, exceptionQuantity, supplementOrderLine);
            if (kk == 0) {
                SupplementOrderLine orderLint = new SupplementOrderLine();
                orderLint.setSupplementOrderId(supplementOrderId);
                orderLint.setProductGlobalId(productGlobalId);
                orderLint.setExceptionQuantity(exceptionQuantity);
                orderLint.setBackQuantity(backQuantity);
                supplementOrderSureService.updateAfterSupplementOrderAfter(orderLint);
                // Product product=machineStorageDao.getProduct(machineId,productGlobalId);
                // machineStorageDao.updateProduct(machineId,productGlobalId,actualQuantity+product.getQuantity(),actualQuantity-product.getLockQuantity()+product.getQuantity());
//                    Machine machine = new Machine();
//                    machine.setMachineId(machineId);
//                    machine.setQuantity(actualQuantity);
//                    machine.setLockQuantity(lockQuantity);
//                    machine.setUseableQuantity(actualQuantity-lockQuantity);
                // machineService.updateMachineOne(machine);
            } else {
                return 1;
            }
        }
        return 0;
    }

    public int afterStatusone(String productNamesGather, long supplementOrderId) {
        List<ProductNames> productNamesList = JSON.parseArray(productNamesGather, ProductNames.class);
        for (int j = 0; j < productNamesList.size(); j++) {
            ProductNames productNames = productNamesList.get(j);
            String productName = productNames.getProductName();
            int bquantity = Integer.valueOf(productNames.getQuantity());
            //根据补货单ID查询机器ID
            SupplementOrder supplementOrder = goodsUnderService.queryGoodsUnder(supplementOrderId);
            long machineId = supplementOrder.getMachineId();
            List<ProductGlobal> productGlobalList = supplementOrderSureService.queryProductGuobal(productName);
            if (productGlobalList.size() > 0) {
                for (int k = 0; k < productGlobalList.size(); k++) {
                    ProductGlobal productGlobal = productGlobalList.get(k);
                    long productGlobalId1 = productGlobal.getProductGlobalId();
                    SupplementOrderLine supplementOrderLine = new SupplementOrderLine();
                    long supplementOrderLineId = IDGenerator.generateById("supplementOrderLineId", supplementOrderId);
                    supplementOrderLine.setSupplementOrderLineId(supplementOrderLineId);
                    supplementOrderLine.setSupplementOrderId(supplementOrderId);
                    supplementOrderLine.setMachineId(machineId);
                    supplementOrderLine.setProductGlobalId(productGlobalId1);
                    supplementOrderLine.setBackQuantity(bquantity);
                    supplementOrderSureService.addSupplementOrderLine(supplementOrderLine);
                }
            }
        }
        return 0;
    }

    /**
     * 后仓修改distributionproductline
     */
    public int distributionlinehou(long distributionOrderId, long productGlobalId, int actualQuantitys, int backQuantity, int exceptionQuantity, SupplementOrderLine supplementOrderLine) {
        int dumpQuantity = supplementOrderLine.getDumpQuantity();
        int actualQuantity = supplementOrderLine.getActualQuantity();
        if (dumpQuantity - actualQuantity != backQuantity || actualQuantity != actualQuantitys) {
            return 1;
        }
        //退回可用数量
        int useBackQuantity = backQuantity - exceptionQuantity;
        //修改后仓
        int count = supplementOrderSureDao.updateDistributionProductLine(distributionOrderId, productGlobalId, exceptionQuantity, useBackQuantity, actualQuantitys, useBackQuantity);
        //修改是否成功
        if (count > 0) {
            return 0;
        }
//        List<DistributionProductLine> distributionproductlines = supplementOrderSureService.queryDistributionproductline(distributionOrderId,productGlobalId);
//        if (distributionproductlines.size()!=0) {
//            for (int i = 0; i < distributionproductlines.size(); i++) {
//                DistributionProductLine distributionproductline = distributionproductlines.get(i);
//                int  Exceptionx=distributionproductline.getExceptionQuantity();
//                int extraQuantity = distributionproductline.getExtraQuantity();
//                //上架数量
//                int actualQuantity = distributionproductline.getActualQuantity();
//                //剩余数量
//                int surplusQuantity = distributionproductline.getSurplusQuantity();
//                DistributionProductLine distributionproductline1 = new DistributionProductLine();
//                //额外配送数量
//                 distributionproductline1.setExtraQuantity(backQuantity-exceptionQuantity);
//                //上架数量
//                distributionproductline1.setActualQuantity(actualQuantity + actualQuantitys);
//                //返仓数量=剩余数量-下货数量+退回数量
//                distributionproductline1.setSurplusQuantity(surplusQuantity+backQuantity);
//                distributionproductline1.setProductGlobalId(productGlobalId);
//                distributionproductline1.setExceptionQuantity(distributionproductline.getExceptionQuantity()+exceptionQuantity);
//                distributionproductline1.setDistributionOrderId(distributionOrderId);
//                supplementOrderSureService.updateDistributionproductline(distributionproductline1);
//                }
//
//            }
        return 1;
    }

    @PostMapping("distributionStatus")
    @ResponseBody
    public Object distributionStatus(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        //验证数字摘要
        // CheckRequest checkRequest=new CheckRequest();
        // int count=checkRequest.checkRequest(requestInfo);
        ResponseInfo responseInfo = new ResponseInfo();

        long distributionOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("distributionOrderId").toString());
        int count = distributionOrderDao.updateDeliverStatus(distributionOrderId, 6);
        Map map = new HashMap();
        if (count > 0) {
            responseInfo.setCode(1);
            responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
            return responseInfo;
        }
        responseInfo.setCode(2341);
        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }

    /**
     * 补货单异常状态回滚
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "rollbackStatus", method = RequestMethod.POST)
    @ResponseBody
    public Object rollbackStatus(String request) {
        RequestInfo requestInfo = JSON.parseObject(request, RequestInfo.class);
        long supplementOrderId = Long.parseLong(JSONObject.parseObject(requestInfo.getData()).getString("supplementOrderId").toString());
        System.out.println(supplementOrderId + "supplement");
        ResponseInfo responseInfo = supplementOrderSureService.rollbackStatus(supplementOrderId);

        responseInfo = returnParameter.returnRequestInfo(responseInfo, requestInfo.getVersion());
        return responseInfo;
    }
}

