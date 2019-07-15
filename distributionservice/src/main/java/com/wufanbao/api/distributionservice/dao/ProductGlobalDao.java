package com.wufanbao.api.distributionservice.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductGlobalDao {
    /**
     * 根据商品id获取商品名
     * @param productGlobalId
     * @return
     */
    String getProductNameById(@Param("productGlobalId") long productGlobalId);
}
