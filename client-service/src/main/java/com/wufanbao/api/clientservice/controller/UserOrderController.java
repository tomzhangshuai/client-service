package com.wufanbao.api.clientservice.controller;

import com.google.gson.reflect.TypeToken;
import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.common.alipay.AliPay;
import com.wufanbao.api.clientservice.common.wechat.WechatPay;
import com.wufanbao.api.clientservice.entity.Machine;
import com.wufanbao.api.clientservice.entity.UserOrder;
import com.wufanbao.api.clientservice.entity.UserOrderLine;
import com.wufanbao.api.clientservice.entity.UserOrderStatus;
import com.wufanbao.api.clientservice.service.ApiServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;

@RestController
public class UserOrderController extends BaseController {
    //获取用户支付过程中的相关信息：优惠券、企业付、家庭付款
    @PostMapping("/auth/getUserPayInfo")
    public ResponseData getUserPayInfo(String data, long machineId, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areamName = map.get("areaName");//所在城市
        String productIdStr = map.get("productIds");//商品ids
        String amountStr = map.get("amount");//订单金额
        if (StringUtils.isNullOrEmpty(areamName) || StringUtils.isNullOrEmpty(productIdStr) || StringUtils.isNullOrEmpty(amountStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            double amount = Double.valueOf(amountStr);
            List<Long> productIds = JsonUtils.GsonToBean(productIdStr, new TypeToken<List<Long>>() {
            }.getType());
            Data result = userOrderService.getUserPayInfo(userId, BigDecimal.valueOf(amount / 100), productIds, areamName);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //确认订单
    @PostMapping("/auth/confirmUserOrder")
    public ResponseData confirmUserOrder(String data, long machineId, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String areaName = map.get("areaName");//所在区域
        String userOrderJson = map.get("userOrder");//订单数据
        String userOrderLineJson = map.get("userOrderLine");//订单行数据
        UserOrder userOrder = new UserOrder();
        List<UserOrderLine> userOrderLines = new ArrayList<>();
        try {
            Data dataUserOrder = JsonUtils.GsonToBean(userOrderJson, Data.class);
            List<Data> dataUserOrderLines = JsonUtils.GsonToBean(userOrderLineJson, new TypeToken<List<Data>>() {
            }.getType());
            double amount = Double.valueOf(dataUserOrder.get("amount").toString());//订单金额
            double actualAmount = Double.valueOf(dataUserOrder.get("actualAmount").toString());//订单金额
            int payType = Integer.parseInt(dataUserOrder.get("payType").toString());//支付方式
            double companyPayAmount = Double.valueOf(dataUserOrder.get("companyPayAmount").toString());//企业付金额
            double familyPayAmount = Double.valueOf(dataUserOrder.get("familyPayAmount").toString());//家庭付金额
            double discountAmount = Double.valueOf(dataUserOrder.get("discountAmount").toString());//优惠券付金额
            Object objTakeTime = dataUserOrder.get("takeTime");//制定取餐时间
            long discountId = Long.parseLong(dataUserOrder.get("discountId").toString());//优惠券id
            //订单
            userOrder.setAmount(BigDecimal.valueOf(amount / 100));
            userOrder.setActualAmount(BigDecimal.valueOf(actualAmount / 100));
            userOrder.setPayType(payType);
            userOrder.setDiscountId(discountId);
            userOrder.setMachineId(machineId);
            userOrder.setCompanyPayAmount(BigDecimal.valueOf(companyPayAmount / 100));
            userOrder.setFamilyPayAmount(BigDecimal.valueOf(familyPayAmount / 100));
            userOrder.setDiscountAmount(BigDecimal.valueOf(discountAmount / 100));
            userOrder.setDiscountId(discountId);
            userOrder.setUserId(userId);
            if (objTakeTime != null && objTakeTime.toString().length() > 0) {
                userOrder.setTakeTime(DateUtils.StringToDate(DateUtils.DateToString(new Date(), "yyyy-MM-dd") + " " + objTakeTime.toString() + ":00"));
                if (userOrder.getTakeTime().compareTo(new Date()) == -1) {
                    throw new ApiServiceException("指定的取餐时间不能小于当前时间");
                }
            }
            //订单行
            List<Long> productIds = new ArrayList<>();
            for (Data dataUserOrderLine : dataUserOrderLines) {
                long productId = Long.parseLong(dataUserOrderLine.get("productId").toString());
                int quantity = Integer.parseInt(dataUserOrderLine.get("quantity").toString());
                double price = Double.valueOf(dataUserOrderLine.get("price").toString());
                if (quantity <= 0 || productId <= 0) {
                    return responseData.error("确认订单参数数据错误").sign(null);
                }
                if (!productIds.contains(productId)) {
                    UserOrderLine userOrderLine = new UserOrderLine();
                    userOrderLine.setProductGlobalId(productId);
                    userOrderLine.setQuantity(quantity);
                    userOrderLine.setPrice(BigDecimal.valueOf(price / 100));
                    userOrderLines.add(userOrderLine);
                }
            }
        } catch (Exception ex) {
            return responseData.error("参数数据解析失败：" + ex.getMessage()).sign(null);
        }
        try {
            Machine machine = machineService.getMachine(machineId);
            UserOrder result = userOrderService.confirmUserOrder(userOrder, userId, areaName, userOrderLines, machine);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //支付订单
    @PostMapping("/auth/payUserOrder")
    public ResponseData payUserOrder(String data, long userId, HttpServletRequest request) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        String payTypeStr = map.get("payType");//payType:支付方式，2=微信，3=支付宝，4=余额
        String payPassword = map.get("payPassword");
        String amountStr = map.get("amount");
        String ip = map.get("ip");
        ip = commonFun.getClientIP(request);
        if (StringUtils.isNullOrEmpty(userOrderIdStr) || StringUtils.isNullOrEmpty(payTypeStr) || StringUtils.isNullOrEmpty(amountStr)) {
            return responseData.error("支付参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            int payType = Integer.parseInt(payTypeStr);
            if (payType <= 0) {
                throw new ApiServiceException("支付方式错误");
            }
            double amount = Double.valueOf(amountStr);
            Object result = userOrderService.appPayUserOrder(userId, userOrderId, payType, payPassword, BigDecimal.valueOf(amount / 100), ip);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //查询支付状态
    @PostMapping("/auth/payQuery")
    public ResponseData payQuery(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        String payTypeStr = map.get("payType");
        if (StringUtils.isNullOrEmpty(userOrderIdStr) || StringUtils.isNullOrEmpty(payTypeStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            int payType = Integer.parseInt(payTypeStr);
            return responseData.success().sign(userOrderService.payQuery(userOrderId, payType));
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //微信公众号支付订单
    @PostMapping("/auth/wxPayUserOrder")
    public ResponseData wxPayUserOrder(String data, long userId, HttpServletRequest request) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        //String payTypeStr = map.get("payType");
        //String payPassword = map.get("payPassword");
        String amountStr = map.get("amount");
        String openId = map.get("openId");
        String ip = commonFun.getClientIP(request);
        if (StringUtils.isNullOrEmpty(userOrderIdStr) || StringUtils.isNullOrEmpty(amountStr) || StringUtils.isNullOrEmpty(openId)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            int payType = 2;//微信
            double amount = Double.valueOf(amountStr);
            Object result = userOrderService.wxPayUserOrder(userId, userOrderId, payType, BigDecimal.valueOf(amount / 100), ip, openId);
            redisUtils.set("openId"+userOrderId,openId);
            redisUtils.expire("openId"+userOrderId,3600*2);
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            logger.error(commonFun.getStackTraceInfo(e));
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //支付宝h5 支付
    @PostMapping("/auth/aliWapPayUserOrder")
    public ResponseData aliWapPayUserOrder(String data, long userId, HttpServletRequest request) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        String amountStr = map.get("amount");
        String ip = commonFun.getClientIP(request);
        if (StringUtils.isNullOrEmpty(userOrderIdStr) || StringUtils.isNullOrEmpty(amountStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            int payType = 3;//支付宝
            double amount = Double.valueOf(amountStr);
            Object result = userOrderService.aliWapPayUserOrder(userOrderId, payType, BigDecimal.valueOf(amount / 100));
            return responseData.success().sign(result);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception e) {
            return responseData.error(e).sign(null);
        }
    }

    //微信支付成功通知
    @PostMapping("/webapi/wechat/payNotify")
    public void wxPayNotify(HttpServletRequest request, HttpServletResponse response) throws ApiServiceException{
        Map<String, String> map = new HashMap<>();
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
            map = XMLUtil.doXMLParse(result);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
        }

        if (map.containsKey("return_code") && map.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
            try {
                WechatPay wechatPay = new WechatPay();
                if (wechatPay.checkSign(map)) {
                    long userOrderId = Long.parseLong(map.get("out_trade_no"));
                    long totalFee = Long.parseLong(map.get("total_fee"));
                    String transaction_id = map.get("transaction_id");//微信支付订单号
                    BigDecimal amount = BigDecimal.valueOf(totalFee).divide(BigDecimal.valueOf(100));
                    userOrderService.payNotify(userOrderId, amount, transaction_id, 2);
                    PrintWriter writer = null;
                    try {
                        writer = response.getWriter();
                        writer.write(wechatPay.notifySuccess("SUCCESS", "OK")); //告诉微信服务器，我收到信息了，不要在调用回调action了
                        writer.flush();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(commonFun.getStackTraceInfo(ex));
            }
        }else{
            throw new ApiServiceException("微信支付失败！");
        }
    }

    //微信退款成功通知
    @PostMapping("/webapi/wechat/refundNotify")
    public void wxRefundNotify(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> requltMap = new HashMap<>();
        try {
            InputStream inStream = request.getInputStream();
            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outSteam.write(buffer, 0, len);
            }
            outSteam.close();
            inStream.close();
            String result = new String(outSteam.toByteArray(), "utf-8");// 获取微信调用我们notify_url的返回信息
            requltMap = XMLUtil.doXMLParse(result);
        } catch (Exception ex) {
            logger.error(commonFun.getStackTraceInfo(ex));
        }
        if (requltMap.containsKey("return_code") && requltMap.get("return_code").toString().equalsIgnoreCase("SUCCESS")) {
            try {
                String reqInfo = requltMap.get("req_info");
                WechatPay wechatPay = new WechatPay();
                Map<String, String> map = wechatPay.decodeData(reqInfo);
                long userOrderId = Long.parseLong(map.get("out_trade_no"));
                long totalFee = Long.parseLong(map.get("total_fee"));
                long refundFee=Long.parseLong(map.get("refund_fee"));
                String transaction_id = map.get("transaction_id");//微信支付订单号
                String refundStatus=map.get("refund_status");
                long out_refund_no = Long.parseLong(map.get("out_refund_no"));//商户退款单号
//                BigDecimal amount = BigDecimal.valueOf(totalFee).divide(BigDecimal.valueOf(100));
                BigDecimal amount = BigDecimal.valueOf(refundFee).divide(BigDecimal.valueOf(100));
                if("SUCCESS".equals(refundStatus)){
                    userOrderService.insertWxuserorderrefund(requltMap,map);
                    userOrderService.refundNotify(userOrderId, out_refund_no, amount, transaction_id, 2);
                    PrintWriter writer = null;
                    try {
                        writer = response.getWriter();
                        writer.write(wechatPay.notifySuccess("SUCCESS", "OK")); //告诉微信服务器，我收到信息了，不要在调用回调action了
                        writer.flush();
                    } finally {
                        if (writer != null) {
                            writer.close();
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(commonFun.getStackTraceInfo(ex));
            }
        }
    }

    //支付宝支付成功通知
    @PostMapping("/webapi/alipay/payNotify")
    public void alipayPayNotify(HttpServletRequest request, HttpServletResponse response) {
        String tradeStatus = request.getParameter("trade_status");
        String refundStatus=request.getParameter("refund_status");
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            Enumeration<?> pNames = request.getParameterNames();
            Map<String, String> param = new HashMap<String, String>();
            try {
                logger.info("----------------------------------");
                while (pNames.hasMoreElements()) {
                    String pName = (String) pNames.nextElement();
                    param.put(pName, request.getParameter(pName));
                    logger.info(pName+"="+request.getParameter(pName));
                }
                AliPay aliPay = new AliPay();
                if (aliPay.checkSign(param)) {
                    try {
                        long userOrderId = Long.parseLong(param.get("out_trade_no"));
                        String trade_no = param.get("trade_no");//支付宝订单号
                        String refund_fee = param.get("refund_fee");
                        if(!StringUtils.isNullOrEmpty(refund_fee)){
                            Double refundFee=Double.parseDouble(refund_fee );
                            long capitalLogId = IDGenerator.generateById("capitalLogId", userOrderId);
                            userOrderService.insertAlipayuserorderrefund(param);
                            userOrderService.refundNotify(userOrderId, capitalLogId, BigDecimal.valueOf(refundFee), trade_no , 3);
                        }else {
                            Double totalFee = Double.parseDouble(param.get("total_amount"));
                            userOrderService.payNotify(userOrderId, BigDecimal.valueOf(totalFee), trade_no, 3);
                        }
                        PrintWriter writer = null;
                        try {
                            writer = response.getWriter();
                            writer.write("success"); //一定要打印success
                            writer.flush();
                        } finally {
                            if (writer != null) {
                                writer.close();
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(commonFun.getStackTraceInfo(ex));
                    }
                }
            } catch (Exception e) {
                logger.error(commonFun.getStackTraceInfo(e));
            }
        }
        //退款
        if ("TRADE_CLOSED".equals(tradeStatus)) {
            Enumeration<?> pNames = request.getParameterNames();
            Map<String, String> param = new HashMap<String, String>();
            try {
                while (pNames.hasMoreElements()) {
                    String pName = (String) pNames.nextElement();
                    param.put(pName, request.getParameter(pName));
                    logger.info(pName+"="+request.getParameter(pName));
                }
                AliPay aliPay = new AliPay();
                if (aliPay.checkSign(param)) {
                    try {
                        long userOrderId = Long.parseLong(param.get("out_trade_no"));
                        Double totalFee = Double.parseDouble(param.get("total_amount"));
                        Double refundFee=Double.parseDouble(param.get("refund_fee"));
                        logger.info("refundFee"+refundFee);
                        String trade_no = param.get("trade_no");//支付宝订单号
                        long capitalLogId = IDGenerator.generateById("capitalLogId", userOrderId);
                        userOrderService.insertAlipayuserorderrefund(param);
                        userOrderService.refundNotify(userOrderId, capitalLogId, BigDecimal.valueOf(refundFee), trade_no , 3);
                        PrintWriter writer = null;
                        try {
                            writer = response.getWriter();
                            writer.write("success"); //一定要打印success
                            writer.flush();
                        } finally {
                            if (writer != null) {
                                writer.close();
                            }
                        }
                    } catch (Exception ex) {
                        logger.error(commonFun.getStackTraceInfo(ex));
                    }
                }
            } catch (Exception e) {
                logger.error(commonFun.getStackTraceInfo(e));
            }
        }
    }

    //取消订单
    @PostMapping("/auth/cancleUserOrder")
    public ResponseData cancleUserOrder(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        if (StringUtils.isNullOrEmpty(userOrderIdStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            userOrderService.cancleUserOrder(userOrderId);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //退款
    @PostMapping("/auth/refundUserOrder")
    public ResponseData refundUserOrder() {
        return responseData.success().sign(null);
    }

    //获取订单列表
    @PostMapping("/auth/getUserOrders")
    public ResponseData getUserOrders(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String statusStr = map.get("status");//订单状态
        String pageIndexStr = map.get("pageIndex");//第几页

        if (StringUtils.isNullOrEmpty(statusStr) || StringUtils.isNullOrEmpty(pageIndexStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        int status = Integer.parseInt(statusStr);
        int pageIndex = Integer.parseInt(pageIndexStr);
        int pageSize = 10;
        int pageStart = (pageIndex - 1) * pageSize;
        if (pageStart < 0) {
            pageStart = 0;
        }
        try {
            List<UserOrder> userOrders = userOrderService.getUserOrders(userId, status, pageStart, pageSize);
            for (UserOrder userOrder : userOrders) {
                userOrder.setImageUrl(commonFun.sourceImage(userOrder.getImageUrl()));
            }
            return responseData.success().sign(userOrders);
        } catch (ApiServiceException e) {
            return responseData.error(e);
        } catch (Exception ex) {
            return responseData.error(ex);
        }
    }

    //获取订单详情
    @PostMapping("/auth/getUserOrder")
    public ResponseData getUserOrder(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单id
        if (StringUtils.isNullOrEmpty(userOrderIdStr)) {
            return responseData.error("参数数据错误").sign(null);
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            Data result = userOrderService.getUserOrder(userOrderId);
            Data userOrderData = (Data) result.get("userOrder");
            int status = Integer.parseInt(userOrderData.get("status").toString());
            int payType = Integer.parseInt(userOrderData.get("payType").toString());
            if (status == -1) {
                UserOrder userOrder = userOrderService.refundQuery(userOrderId, payType);
                userOrderData.put("status", userOrder.getStatus());
                result.put("userOrder", userOrderData);
            }
            return responseData.success().sign(result);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }
    //退款详情
    @PostMapping("/auth/getOrderDetailRefund")
    public ResponseData getOrderDetailRefund(String data,long userId){
        Map<String,String> map =JsonUtils.GsonToMaps(data);
        String orderRefundIdStr = map.get("orderRefundId");
        if(StringUtils.isNullOrEmpty(orderRefundIdStr)){
            return responseData.error("参数数据错误").sign(null);
        }
        long orderRefundId = Long.parseLong(orderRefundIdStr);
        try {
            Data result=userOrderService.getOrderDetailRefund(orderRefundId);
            return responseData.success().sign(result);
        } catch (Exception ex) {
            return  responseData.error(ex).sign(null);
        }
    }

    //获取机器里的用户可以取餐订单
    @PostMapping("/auth/getMachineTakeUserOrder")
    public ResponseData getMachineTakeUserOrder(long userId, long machineId) {
        try {
            return responseData.success().sign(userOrderService.getMachineTakeUserOrder(userId, machineId));
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //取餐
    @PostMapping("/auth/takeProduct")
    public ResponseData takeProduct(String data, long userId, long machineId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");
        if (StringUtils.isNullOrEmpty(userOrderIdStr)) {
            return responseData.error("取餐参数异常");
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            return responseData.success().sign(userOrderService.takeProduct(userId, machineId, userOrderId));
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //评价订单
    @PostMapping("/auth/assessUserOrder")
    public ResponseData assessUserOrder(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");//订单Id
        String userOrderLineJson = map.get("userOrderLine");//订单行数据
        if (StringUtils.isNullOrEmpty((userOrderIdStr)) || StringUtils.isNullOrEmpty(userOrderLineJson)) {
            return responseData.error("评价参数数据错误").sign(null);
        }
        List<UserOrderLine> userOrderLines = new ArrayList<>();
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            List<Data> dataUserOrderLines = JsonUtils.GsonToBean(userOrderLineJson, new TypeToken<List<Data>>() {
            }.getType());

            //订单行
            List<Long> productIds = new ArrayList<>();
            for (Data dataUserOrderLine : dataUserOrderLines) {
                long productId = Long.parseLong(dataUserOrderLine.get("productId").toString());
                int tastyDegree = Integer.parseInt(dataUserOrderLine.get("tastyDegree").toString());
                String evaluation = dataUserOrderLine.get("evaluation").toString().replaceAll("[^a-zA-Z0-9\u4e00-\u9fa5,.;*@!()+_，。！？；：/（）#&]","");
                if (tastyDegree < 0 || productId <= 0) {
                    return responseData.error("参数数据错误").sign(null);
                }
                if (!productIds.contains(productId)) {
                    UserOrderLine userOrderLine = new UserOrderLine();
                    userOrderLine.setUserOrderId(userOrderId);
                    userOrderLine.setProductGlobalId(productId);
                    userOrderLine.setTastyDegree(tastyDegree);
                    userOrderLine.setEvaluation(evaluation);
                    userOrderLines.add(userOrderLine);
                }
            }
            userOrderService.assessUserOrder(userOrderId, userId, userOrderLines);
            return responseData.success().sign(null);
        } catch (ApiServiceException e) {
            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //取餐详情
    @PostMapping("/auth/getUserOrderTakeDetail")
    public ResponseData getUserOrderTakeDetail(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");
        try {
            if (StringUtils.isNullOrEmpty(userOrderIdStr)) {
                return responseData.error("取餐详情参数异常");
            }
            long userOrderId = Long.parseLong(userOrderIdStr);
            return responseData.success().sign(userOrderService.getUserOrderTakeDetail(userOrderId));
        } catch (ApiServiceException e) {

            return responseData.error(e).sign(null);
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //获取单个商品的取餐情况
    @PostMapping("/auth/getUserOrderProductLine")
    public ResponseData getUserOrderProductLine(String data) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderProductLineIdStr = map.get("userOrderProductLineId");
        if (StringUtils.isNullOrEmpty(userOrderProductLineIdStr)) {
            return responseData.error("商品取餐参数异常");
        }
        try {
            long userOrderProductLineId = Long.parseLong(userOrderProductLineIdStr);
            return responseData.success().sign(userOrderService.getUserOrderProductLine(userOrderProductLineId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }

    //评价详情
    @PostMapping("/auth/getAssessUserOrderDetail")
    public ResponseData getAssessUserOrderLineAndProducts(String data, long userId) {
        Map<String, String> map = JsonUtils.GsonToMaps(data);
        String userOrderIdStr = map.get("userOrderId");
        if (StringUtils.isNullOrEmpty(userOrderIdStr)) {
            return responseData.error("评价详情参数异常");
        }
        try {
            long userOrderId = Long.parseLong(userOrderIdStr);
            return responseData.success().sign(userOrderService.getAssessUserOrderLineAndProducts(userOrderId));
        } catch (Exception ex) {
            return responseData.error(ex).sign(null);
        }
    }


}
