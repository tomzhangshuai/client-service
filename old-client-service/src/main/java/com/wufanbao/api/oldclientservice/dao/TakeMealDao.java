package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.ProductDetails;
import com.wufanbao.api.oldclientservice.entity.ProductOff;
import com.wufanbao.api.oldclientservice.entity.ProductTakeMeal;
import com.wufanbao.api.oldclientservice.entity.UserOrderProductLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * User:wangshihao
 * Date:2017-12-06
 * Time:15:33
 */
public interface TakeMealDao {
    /**
     * 查询商品名字
     */
    public ProductDetails productName(long productGlobalId);

    /**
     * 查询订单状态
     */
    public List<ProductOff> productStatus(long sourceId);

    /**
     * 查询订单商品信息
     */
    public List<ProductTakeMeal> productLists(long userOrderId);

    /**
     * 插入订单拆分关联表
     */
    public int addUserOrderProductLine(UserOrderProductLine userOrderProductLine);

    /**
     * 查询时间排序
     */
    public List<UserOrderProductLine> queryUserOrderProductLine(long userOrderId);
}
