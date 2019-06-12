package com.wufanbao.api.olddriverservice.dao;

import org.apache.ibatis.annotations.Param;

public interface DistributionProductLineDao {

    /**
     * 增加在途剩余库存
     *
     * @param distributionOrderId
     * @param productGlobalId
     * @param quantity
     * @return
     */
    public Integer increaseSurplusQuantity(@Param("distributionOrderId") long distributionOrderId, @Param("productGlobalId") long productGlobalId, @Param("quantity") long quantity);

    /**
     * 减少在途剩余库存
     *
     * @param distributionOrderId
     * @param productGlobalId
     * @param quantity
     * @return
     */
    public Integer decreaseSurplusQuantity(@Param("distributionOrderId") long distributionOrderId, @Param("productGlobalId") long productGlobalId, @Param("quantity") long quantity);
}
