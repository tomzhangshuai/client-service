package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.SupplementOrder;
import com.wufanbao.api.distributionservice.transfer.DistributingOrderDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistributionOrderLineDao {


    /**
     * 获取配送单明细，补货中的补货单（已卸货，未补货完成)
     * @param distributionOrderId
     * @return
     */
    public List<SupplementOrder> getDistributingSupplementOrder(
            @Param("distributionOrderId") long distributionOrderId);

    /**
     * 终止配送单中未配送的明细
     * @param distributionOrderId
     * @return
     */
    public int terminateLine(
            @Param("distributionOrderId") long distributionOrderId);

    /**
     * 获取配送中的配送单明细
     * @param distributionOrderId 配送单ID
     * @return
     */
    public List<DistributingOrderDetail> getDistributingOrderDetail(
            @Param("distributionOrderId") long distributionOrderId);

    /**
     * 配送中，根据补货单ID获取配送单ID
     * @param supplementOrderId 补货单ID
     * @return 配送单ID
     */
    public long getDistributionOrderIdBySupplementOrderId(
            @Param("supplementOrderId") long supplementOrderId
    );

    /**
     * 补货单下货时更新到达时间
     * @return
     */
    public int dumpArrived(
            @Param("distributionOrderId") long distributionOrderId,
            @Param("supplementOrderId") long supplementOrderId
    );

    /**
     * 完成补货
     * @param distributionOrderId
     * @param supplementOrderId
     * @return
     */
    public int completeSupplement(
            @Param("distributionOrderId") long distributionOrderId,
            @Param("supplementOrderId") long supplementOrderId
    );


    /**
     * 获取配送单未补货的记录数量
     * @return
     */
    public int getUndistributedCount(
            @Param("distributionOrderId") long distributionOrderId);
}
