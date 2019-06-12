package com.wufanbao.api.clientservice.dao;

import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.entity.ProductOff;
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
}
