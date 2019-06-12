package com.wufanbao.api.oldclientservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.RequestGenerator;
import com.wufanbao.api.oldclientservice.dao.AlipayDao;
import com.wufanbao.api.oldclientservice.dao.UserOrderDao;
import com.wufanbao.api.oldclientservice.entity.Alipay;
import com.wufanbao.api.oldclientservice.entity.ResponseInfo;
import com.wufanbao.api.oldclientservice.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-09-14
 * Time:10:13
 */
@Service
@Transactional
public class AlipayServiceImpl implements AlipayService {

    /**
     * 支付宝H5页面支付 aliPayH5Url
     */
    //private final static String ALI_PAY_H5_URL = ResourceBundle.getBundle("IP").getString("aliPayH5Url");

    @Autowired
    private AlipayDao alipayDao;
    private UserOrderDao userOrderDao;

    @Autowired
    private ClientSetting clientSetting;

    @Override
    public List<Alipay> queryAlipay(long userOrderId) {
        return alipayDao.queryAlipay(userOrderId);
    }

    /**
     * 支付宝H5支付
     *
     * @param userId      用户Id
     * @param userOrderId 用户OrderId
     * @return com.wufanbao.api.oldclientservice.tool.ResponseInfo
     * @date 2018/5/7
     */
    @Override
    public ResponseInfo orderPayForH5(long userId, long userOrderId) {
        Map<String, String> orderPyaInfoMap = verifyOrderPayInfo(userId, userOrderId);
        ResponseInfo responseInfo = new ResponseInfo();
        if (orderPyaInfoMap == null) {
            responseInfo.setCode(2502);
            return responseInfo;
        }
        //订单标题
        String description = orderPyaInfoMap.get("description");
        //支付金额
        String payAmount = orderPyaInfoMap.get("payAmount");
        String responseJson = RequestGenerator.sendPost(clientSetting.getAliPayH5Url(), "userOrderId=" + userOrderId + "&amount=" + payAmount + "&subject=" + description);
        responseInfo = JSON.parseObject(responseJson, ResponseInfo.class);
        return responseInfo;
    }

    /**
     * 验证订单支付信息
     *
     * @param userId      用户ID
     * @param userOrderId 用户订单ID
     * @return java.util.Map<java.lang.String , java.lang.String>
     * @author Wang Zhiyuan
     * @date 2018/5/7
     */
    @Override
    public Map<String, String> verifyOrderPayInfo(long userId, long userOrderId) {
        Map userOrderInfoMap = userOrderDao.inquireUserOrderInfoByUserOrderId(userId, userOrderId);
        if (userOrderInfoMap == null) {
            return null;
        }
        //订单状态
        int status = Integer.valueOf(userOrderInfoMap.get("status").toString());
        if (status != 2) {
            return null;
        }
        Map<String, String> orderPayMap = new HashMap(6);
        //订单金额
        BigDecimal amount = BigDecimal.valueOf(Double.parseDouble(userOrderInfoMap.get("amount").toString()));
        //家庭付金额
        BigDecimal familyPayAmount = BigDecimal.valueOf(Double.parseDouble(userOrderInfoMap.get("familyPayAmount").toString()));
        //企业付金额
        BigDecimal companyPayAmount = BigDecimal.valueOf(Double.parseDouble(userOrderInfoMap.get("companyPayAmount").toString()));
        //优惠金额
        BigDecimal discountAmount = BigDecimal.valueOf(Double.parseDouble(userOrderInfoMap.get("discountAmount").toString()));
        //备注
        String description = userOrderInfoMap.get("description").toString();
        //定单应支付价格
        BigDecimal payAmount = amount.subtract(familyPayAmount).subtract(companyPayAmount).subtract(discountAmount);
        orderPayMap.put("description", description);
        orderPayMap.put("payAmount", payAmount.toString());
        orderPayMap.put("userOrderId", String.valueOf(userOrderId));
        return orderPayMap;
    }
}
