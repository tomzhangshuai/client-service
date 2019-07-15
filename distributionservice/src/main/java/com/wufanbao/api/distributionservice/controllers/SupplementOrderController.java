package com.wufanbao.api.distributionservice.controllers;

import com.wufanbao.api.distributionservice.entities.DistributionOrderLine;
import com.wufanbao.api.distributionservice.entities.SupplementOrder;
import com.wufanbao.api.distributionservice.resolvers.Custom;
import com.wufanbao.api.distributionservice.services.SupplementOrderService;
import com.wufanbao.api.distributionservice.transfer.SupplementConfirmBehindInfo;
import com.wufanbao.api.distributionservice.transfer.SupplementConfirmFrontInfo;
import com.wufanbao.api.distributionservice.transfer.SupplementDumpInfo;
import com.wufanbao.api.distributionservice.transfer.SupplementOpenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 补货单
 */
@RestController
public class SupplementOrderController {

    @Autowired
    SupplementOrderService supplementOrderService;

    /**
     * 卸货前计算
     * @param distributionOrderLine 补货单
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/computePreDumping","/goodsUnder/goodsUnderList"}, method = RequestMethod.POST)
    public Object computePreDumping(@Custom DistributionOrderLine distributionOrderLine)
    {
         return supplementOrderService.computePreDumping(distributionOrderLine);
    }

    /**
     * 卸货  卸货信息 不包含配送单ID（希望以后加上）
     * @param supplementDumpInfo 卸货信息
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/dump","/goodsUnder/updateSupplement"}, method = RequestMethod.POST)
    public Object dump(@Custom SupplementDumpInfo supplementDumpInfo) throws Exception {
        supplementOrderService.dump(0,supplementDumpInfo.getSupplementOrderId(),supplementDumpInfo.getItems());
        return new Object();
    }

    /**
     * 获取补货详情
     * @param order
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/getDetail","/supplementDetails/supplementDetailsInfo","/distributionCompletion/distributionCompletionAll"}, method = RequestMethod.POST)
    public Object getDetail(@Custom SupplementOrder order) {
        return supplementOrderService.getDetail(order.getSupplementOrderId());
    }

    /**
     * 获取补货单状态
     * @param order
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/getOrderStatus","/openStorehouse/openStatus"}, method = RequestMethod.POST)
    public Object getOrderStatus(@Custom SupplementOrder order) {
        return supplementOrderService.getOrderStatus(order.getSupplementOrderId());
    }

    /**
     * 扫一扫
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/scan","/openStorehouse/scanCode"}, method = RequestMethod.POST)
    public Object scan(@Custom SupplementOpenInfo supplementOpenInfo) throws Exception{
        supplementOrderService.scan(supplementOpenInfo);
        return new Object();
    }

    /**
     * 回退补货单状态
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/backStatus","/supplementOrderInfo/rollbackStatus"}, method = RequestMethod.POST)
    public Object backStatus(@Custom SupplementOrder order) throws Exception{
        supplementOrderService.backStatus(order.getSupplementOrderId());
        return new Object();
    }

    /**
     * 开前仓
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/openFront","/openStorehouse/machineSupplementFront"}, method = RequestMethod.POST)
    public Object openFront(@Custom SupplementOpenInfo supplementOpenInfo) throws Exception{
         supplementOrderService.openFront(supplementOpenInfo.getSupplementOrderId());
         return new Object();
    }

    /**
     * 前仓补货详情
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/getFrontDetail","/supplementOrderInfo/supplementOrderDetails"}, method = RequestMethod.POST)
    public Object getFrontDetail(@Custom SupplementOrder order){
        return supplementOrderService.getFrontDetail(order.getSupplementOrderId());
    }

    /**
     * 确认前仓补货完成
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/confirmFront","/supplementOrderInfo/supplementOrderSure"}, method = RequestMethod.POST)
    public Object confirmFront(@Custom SupplementConfirmFrontInfo supplementConfirmInfo) throws Exception{
         supplementOrderService.confirmFront(supplementConfirmInfo.getSupplementOrderId(),supplementConfirmInfo.getItems());
         return new Object();
    }

    /**
     * 开后仓
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/openBehind","/openStorehouse/machineSupplementBehind"}, method = RequestMethod.POST)
    public Object openBehind(@Custom SupplementOpenInfo supplementOpenInfo) throws Exception{
        supplementOrderService.openBehind(supplementOpenInfo.getSupplementOrderId());
        return new Object();
    }

    /**
     * 后仓补货详情
     * @return
     */
    @RequestMapping(value ={"/supplementOrder/getBehindDetail","/supplementOrderInfo/supplementOrderhou"}, method = RequestMethod.POST)
    public Object getBehindDetail(@Custom SupplementOrder order){
       return supplementOrderService.getBehindDetail(order.getSupplementOrderId());
    }

    /**
     * 确认后仓补货完成
     * @return
     */
    @RequestMapping(value = {"/supplementOrder/confirmBehind","/supplementOrderInfo/supplementOrderAfter"}, method = RequestMethod.POST)
    public Object confirmBehind(@Custom SupplementConfirmBehindInfo supplementConfirmInfo)throws Exception {
            supplementOrderService.confirmBehind(supplementConfirmInfo.getDistributionOrderId(), supplementConfirmInfo.getSupplementOrderId(), supplementConfirmInfo.getItems());
        return new Object();
    }

}
