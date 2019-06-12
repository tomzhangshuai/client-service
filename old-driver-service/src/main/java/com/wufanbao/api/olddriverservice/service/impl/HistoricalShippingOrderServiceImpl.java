package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.DistributionOrderDao;
import com.wufanbao.api.olddriverservice.entity.DistributionOrderInfo;
import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.service.HistoricalShippingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HistoricalShippingOrderServiceImpl implements HistoricalShippingOrderService {
    @Autowired
    private DistributionOrderDao distributionOrderDao;

    /**
     * 查询历史订单
     *
     * @param employee 员工信息
     * @return
     */
    @Override
    public ResponseInfo getHistoricalShippingOrder(Employee employee) {
        ResponseInfo<List<DistributionOrderInfo>> responseInfo = new ResponseInfo();
        Map map = new HashMap();
        List<DistributionOrderInfo> distributionOrderInfoList = distributionOrderDao.getHistoricalShippingOrder(employee.getEmployeeId());
        if (distributionOrderInfoList.size() > 0) {
            for (int i = 0; i < distributionOrderInfoList.size(); i++) {
                //获取订单货物数量放到配送单信息
                distributionOrderInfoList.get(i).setCargoQuantity(distributionOrderDao.getCargoQuantity(distributionOrderInfoList.get(i).getDistributionOrderId()));
                //获取机器数量放到配送单信息
                distributionOrderInfoList.get(i).setDeviceNumber(distributionOrderDao.getDeviceNumber(distributionOrderInfoList.get(i).getDistributionOrderId()));
            }
            map.put("distributionOrderInfoList", distributionOrderInfoList);
            responseInfo.setCode(1);
            responseInfo.setData(distributionOrderInfoList);
        } else {
            responseInfo.setCode(2061);
            responseInfo.setData(distributionOrderInfoList);
        }
        return responseInfo;
    }
}
