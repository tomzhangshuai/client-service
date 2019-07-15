package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.SupplementOrderLine;
import com.wufanbao.api.distributionservice.transfer.SupplementOrderLineInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作补货单明细表
 */
@Repository
public interface SupplementOrderLineDao {

    /**
     * 获取补货单明细
     *
     * @param supplementOrderId
     * @return
     */
    public List<SupplementOrderLine> getSupplementOrderLines(
            @Param("supplementOrderId") long supplementOrderId);


    /**
     * 终止配送单时更新补货单明细
     *
     * @param supplementOrderLine
     * @return
     */
    public int updateOrderLineForTermination(SupplementOrderLine supplementOrderLine);

    /**
     * 补货单更新下货数据
     * @param supplementOrderId 补货单ID
     * @param productGlobalId 商品ID
     * @param dumpQuantity 下货数量
     * @return
     */
    public int dumpProduct(
            @Param("supplementOrderId") long supplementOrderId,
            @Param("productGlobalId") long productGlobalId,
            @Param("dumpQuantity") int dumpQuantity);

    /**
     * 上前架商品
     * @param line
     * @return
     */
    public int shelveFrontProducts(SupplementOrderLine line);

    /**
     * 上后架商品
     * @param line
     * @return
     */
    public int shelveBehindProducts(SupplementOrderLine line);

    /**
     * 获取上架信息
     * @param supplementOrderId 补货单ID
     * @param isStaple  是否主食 0，1
     * @return
     */
    public List<SupplementOrderLineInfo> getShelveDetail(
            @Param("supplementOrderId") long supplementOrderId,
            @Param("isStaple") int isStaple);


    /**
     * 获取补货单明细信息
     * @param supplementOrderId 补货单ID
     * @return
     */
    public List<SupplementOrderLineInfo> getOrderDetail(
            @Param("supplementOrderId") long supplementOrderId);
}
