package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.entity.ProductOff;
import com.wufanbao.api.clientservice.entity.ProductPrepare;
import com.wufanbao.api.clientservice.entity.UserOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoffDao {

    /**
     * 获取最近3天的产品下架信息，status=3
     * @return
     */
    List<Data> getProductoff();

    int updateMessageStatus(@Param("userOrderId") long userOrderId);

    List<ProductOff> getProductoffBySourceid(@Param("sourceId") long sourceId);

    /**
     * 获取与制作异常商品
     * @return
     */
    List<ProductPrepare> getProductPrepare();

    /**
     * 获取机器Id
     * @param productOffId
     * @return
     */
    long getMachineId(long productOffId);
}
