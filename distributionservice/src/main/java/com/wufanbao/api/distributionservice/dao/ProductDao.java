package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao {

    /**
     * 获取机器商品库存
     * @param supplementOrderId 补货单ID
     * @return
     */
    public List<Product> getMachineProductsBySupplementOrderId(
            @Param("supplementOrderId") long supplementOrderId);

    /**
     * 前仓商品上架
     * @param shelveQuantity 上架数量
     * @param machineId 机器ID
     * @param productGlobalId 商品id
     * @return
     */
    public int shelveFront(
            @Param("shelveQuantity") int shelveQuantity,
            @Param("machineId") long machineId,
            @Param("productGlobalId") long productGlobalId);
}
