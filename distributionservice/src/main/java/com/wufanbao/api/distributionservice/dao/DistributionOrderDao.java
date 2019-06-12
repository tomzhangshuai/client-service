package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.Store;
import com.wufanbao.api.distributionservice.transfer.DistributionOrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistributionOrderDao {

    /**
     * 获取配送员相关配送单（未配送及未完成配送的配送单）
     * @param employeeId  配送员
     * @return 配送单列表信息
     */
  public List<DistributionOrderInfo> getDistributingOrdersByEmployeeId(
          @Param("employeeId") long employeeId);


  /**
   * 获取配送员的历史配送单
   * @param employeeId 配送员
   * @return 配送单列表信息
   */
  public List<DistributionOrderInfo> getHistoryOrdersByEmployeeId(
          @Param("employeeId") long employeeId);


  /**
   * 获取配送单仓库
   * @param distributionOrderId
   * @return
   */
  public Store getStoreInfo(
          @Param("distributionOrderId") long distributionOrderId);


    /**
     * 查询正在配送的配送单数
     * @param employeeId 配送员
     * @param distributionOrderId 配送单ID
     * @return 返回配送单数
     */
  public int queryDistributingOrders(
          @Param("employeeId") long employeeId,
          @Param("distributionOrderId") long distributionOrderId);


    /**
     * 配送单出发
     * @param distributionOrderId 配送单ID
     * @return
     */
  public int setOut(
          @Param("distributionOrderId") long distributionOrderId);


    /**
     * 终止配送单
     * @param distributionOrderId 配送单ID
     * @param remark 终止原因
     * @return
     */
  public int terminate(
          @Param("distributionOrderId") long distributionOrderId,
          @Param("remark") String remark);

  /**
   * 设置已配送
   * @param distributionOrderId
   * @return
   */
  public int setDistributed(
          @Param("distributionOrderId") long distributionOrderId
  );


  /**
   * 返仓确认完成
   * @return
   */
  public int complete(
          @Param("distributionOrderId") long distributionOrderId);
}
