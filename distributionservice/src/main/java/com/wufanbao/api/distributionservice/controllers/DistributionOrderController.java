package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.entities.DistributionOrder;
import com.wufanbao.api.distributionservice.resolvers.Custom;
import com.wufanbao.api.distributionservice.resolvers.User;
import com.wufanbao.api.distributionservice.services.DistributionOrderService;
import com.wufanbao.api.distributionservice.transfer.DistributingOrderDetail;
import com.wufanbao.api.distributionservice.transfer.DistributionConfirmInfo;
import com.wufanbao.api.distributionservice.transfer.DistributionOrderInfo;
import com.wufanbao.api.distributionservice.transfer.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class DistributionOrderController {

    @Autowired
    DistributionOrderService distributionOrderService;

    /**
     * 获取配送员正在配送的配送单（包含未配送及配送中）
     * @param employee 配送员
     * @return 配送员正在配送的配送单
     */
    @RequestMapping(value = {"/distributionOrder/getDistributingOrders","/employeeLogin/distributionInfo"}, method = RequestMethod.POST)
    public List<DistributionOrderInfo> getDistributingOrders(@User EmployeeInfo employee)
    {
        return distributionOrderService.getDistributingOrders(employee.getEmployeeId());
    }

    /**
     * 获取配送员已配送的配送单
     * @param employee 配送员
     * @return 配送员正在配送的配送单
     */
    @RequestMapping(value = {"/distributionOrder/getHistoryOrders","/historicalShippingOrder/historicalShippingOrderInfo"}, method = RequestMethod.POST)
    public List<DistributionOrderInfo> getHistoryOrders(@User EmployeeInfo employee)
    {
        return distributionOrderService.getHistoryOrders(employee.getEmployeeId());
    }

    /**
     * 出发
     * @param order 订单
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  {"/distributionOrder/setOut","/distributionOperation/beganDelivery"}, method = RequestMethod.POST)
    public Object setOut(@User EmployeeInfo employeeInfo,@Custom DistributionOrder order) throws Exception
    {
        distributionOrderService.setOut(employeeInfo.getEmployeeId(),order.getDistributionOrderId());
        return new Object();
    }

    /**
     * 终止
     * @param order 订单
     * @return
     * @throws Exception
     */
    @RequestMapping(value =  {"/distributionOrder/terminate","/distributionOperation/terminationDistribution"}, method = RequestMethod.POST)
    public Object terminate(@Custom DistributionOrder order) throws Exception
    {
        distributionOrderService.terminate(order);
        return new Object();
    }

    /**
     * 设置已配送/完成配送
     * @param order
     * @return
     */
    @RequestMapping(value =  {"/distributionOrder/setDistributed","/supplementOrderInfo/distributionStatus"})
    public Object setDistributed(@Custom DistributionOrder order) throws Exception{
        distributionOrderService.setDistributed(order.getDistributionOrderId());
        return new Object();
    }

    /**
     * 获取待确认数据
     * @param order
     * @return
     */
    @RequestMapping(value =  {"/distributionOrder/getConfirmData","/distributionOperation/confirmDeliveryOrder"}, method = RequestMethod.POST)
    public Object getConfirmData(@Custom DistributionOrder order)
    {
        return  distributionOrderService.getConfirmData(order.getDistributionOrderId());
    }
    /**
     * 确认完成  返仓确认
     * @param distributionConfirmInfo
     * @return
     */
    @RequestMapping(value= {"/distributionOrder/complete","/distributionOperation/confirmAnOrder"})
    public Object complete(@Custom DistributionConfirmInfo distributionConfirmInfo) throws Exception
    {
        distributionOrderService.complete(distributionConfirmInfo.getDistributionOrderId(),distributionConfirmInfo.getItems());
        return new Object();
    }

    /**
     * 获取配送中的订单详情
     * @param order
     * @return
     */
    @RequestMapping(value={"/distributionOrder/getDistributingOrderDetail","/distribution/distributionLine"})
    public Object getDistributingOrderDetail(@Custom DistributionOrder order)
    {
        return  distributionOrderService.getDistributingOrderDetail(order.getDistributionOrderId());
    }

    /**
     * 获取配送中的订单的配送数量及仓库信息
     * @param order
     * @return
     */
    @RequestMapping(value = {"/distributionOrder/getDistributingOrderOtherInfo","/distribution/distributionProductLine"})
    public Object getDistributingOrderOtherInfo(@Custom DistributionOrder order)
    {
        return distributionOrderService.getDistributingOrderOtherInfo(order.getDistributionOrderId());
    }
}
