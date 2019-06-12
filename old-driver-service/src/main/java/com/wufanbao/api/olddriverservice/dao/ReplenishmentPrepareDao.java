package com.wufanbao.api.olddriverservice.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: AlphaDriver
 * @description: 补货准备工作
 * @author: Wang Zhiyuan
 * @create: 2018-03-24 15:52
 **/
public interface ReplenishmentPrepareDao {
    /**
     * 生成补货单
     *
     * @param supplementOrderId 补货单ID
     * @param logisticCompanyId 物流商ID
     * @param joinCompanyId     加盟商ID
     * @param machineId         机器编号
     * @param orderEmployeeId   下单确定操作人
     * @param orderTime         下单时间
     * @param auditorEmployeeId 审核人
     * @param auditTime         审核时间
     * @param acceptEmployeeId  接单操作人
     * @param supplementStatus  补货单单状态
     * @param planTime          计划配送时间
     * @param storeId           仓库ID
     * @param cityCompanyId     城市运营商ID
     * @param supplementDay     补货日期
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    int generateSupplementOrder(@Param("supplementOrderId") long supplementOrderId,
                                @Param("logisticCompanyId") long logisticCompanyId,
                                @Param("joinCompanyId") long joinCompanyId,
                                @Param("machineId") long machineId,
                                @Param("orderEmployeeId") long orderEmployeeId,
                                @Param("orderTime") Date orderTime,
                                @Param("auditorEmployeeId") long auditorEmployeeId,
                                @Param("auditTime") Date auditTime,
                                @Param("acceptEmployeeId") long acceptEmployeeId,
                                @Param("supplementStatus") int supplementStatus,
                                @Param("planTime") Date planTime,
                                @Param("storeId") long storeId,
                                @Param("cityCompanyId") long cityCompanyId,
                                @Param("supplementDay") Date supplementDay
    );

    /**
     * 生成补货单详请
     *
     * @param supplementOrderLineId 补货单详情表ID
     * @param supplementOrderId     补货单ID
     * @param machineId             机器编号
     * @param productGlobalId       商品ID
     * @param stockUpdateTime       库存更新时间
     * @param stockQuantity         预计库存消耗
     * @param expectQuantity        期望配送数量
     * @param quantity              数量
     * @param actualQuantity        上架数量
     * @param stockConsume          预计消耗库存
     * @param lockQuantity          锁定数量
     * @param offProductGlobalId    下架商品ID
     * @param exceptionQuantity     异常数量
     * @param backQuantity          退回数量
     * @param isStaple              是否主食
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    int generateSupplementOrderLine(@Param("supplementOrderLineId") long supplementOrderLineId,
                                    @Param("supplementOrderId") long supplementOrderId,
                                    @Param("machineId") long machineId,
                                    @Param("productGlobalId") long productGlobalId,
                                    @Param("stockUpdateTime") Date stockUpdateTime,
                                    @Param("stockQuantity") int stockQuantity,
                                    @Param("expectQuantity") int expectQuantity,
                                    @Param("quantity") int quantity,
                                    @Param("actualQuantity") int actualQuantity,
                                    @Param("stockConsume") int stockConsume,
                                    @Param("lockQuantity") int lockQuantity,
                                    @Param("dumpQuantity") int dumpQuantity,
                                    @Param("offProductGlobalId") int offProductGlobalId,
                                    @Param("exceptionQuantity") int exceptionQuantity,
                                    @Param("backQuantity") int backQuantity,
                                    @Param("isStaple") boolean isStaple
    );

    /**
     * 生成配送单
     *
     * @param distributionOrderId  补货单ID
     * @param logisticCompanyId    物流商
     * @param distributeEmployeeId 配送人
     * @param storeId              仓库ID
     * @param vehicleId            车辆信息
     * @param driverEmployeeId     驾驶员Id
     * @param markEmployeeId       制单人
     * @param planDeliverTime      计划配送时间
     * @param beginDistributeTime  开始配送时间
     * @param status               状态
     * @param cityCompanyId        城市运营商
     * @param createTime           创建时间
     * @param distributeDate       配送日期
     * @param isTermination        是否终止
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    int generateDistributionOrder(@Param("distributionOrderId") long distributionOrderId,
                                  @Param("logisticCompanyId") long logisticCompanyId,
                                  @Param("distributeEmployeeId") long distributeEmployeeId,
                                  @Param("storeId") long storeId,
                                  @Param("vehicleId") long vehicleId,
                                  @Param("driverEmployeeId") long driverEmployeeId,
                                  @Param("markEmployeeId") long markEmployeeId,
                                  @Param("planDeliverTime") Date planDeliverTime,
                                  @Param("beginDistributeTime") Date beginDistributeTime,
                                  @Param("status") int status,
                                  @Param("cityCompanyId") long cityCompanyId,
                                  @Param("createTime") Date createTime,
                                  @Param("distributeDate") Date distributeDate,
                                  @Param("isTermination") int isTermination);

    /**
     * 配送单明细
     *
     * @param distributionOrderId 配送单ID
     * @param supplementOrderId   补货单ID
     * @param turn                排序
     * @param arriveTime          卸货时间
     * @param isDistributed       是否已经配送
     * @param isTerminate         是否终止
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    int generateDistributionOrderLine(@Param("distributionOrderId") long distributionOrderId,
                                      @Param("supplementOrderId") long supplementOrderId,
                                      @Param("turn") long turn,
                                      @Param("arriveTime") Date arriveTime,
                                      @Param("isDistributed") int isDistributed,
                                      @Param("isTerminate") int isTerminate
    );

    /**
     * 配送单商品详情
     *
     * @param distributionOrderId 配送单Id
     * @param productGlobalId     商品ID
     * @param quantity            配发总量
     * @param planQuantity        计划配送总量
     * @param extraQuantity       额外配送数量
     * @param actualQuantity      实际上架数量
     * @param surplusQuantity     剩余数量
     * @param storeQuantity       实际入库数量
     * @param exceptionQuantity   异常数量
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    int generateDistributionProductLine(@Param("distributionOrderId") long distributionOrderId,
                                        @Param("productGlobalId") long productGlobalId,
                                        @Param("quantity") int quantity,
                                        @Param("planQuantity") int planQuantity,
                                        @Param("extraQuantity") int extraQuantity,
                                        @Param("actualQuantity") int actualQuantity,
                                        @Param("surplusQuantity") int surplusQuantity,
                                        @Param("storeQuantity") int storeQuantity,
                                        @Param("exceptionQuantity") int exceptionQuantity
    );

    /**
     * 根据机器Id查询补货信息
     *
     * @param machineId 机器ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/3/24
     */
    List<Map> getMachineInfo(long machineId);
}
