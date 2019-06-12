package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserIntegralLog;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 家庭付
 *
 * @author Wang Zhiyuan
 **/
public interface UserIntegralDao {
    /**
     * 用户积分增长
     *
     * @param userId   用户ID
     * @param quantity 本次获得积分
     * @return int
     * @author Wang Zhiyuan
     */
    int integralGrowUp(@Param("userId") long userId, @Param("quantity") int quantity);

    /**
     * 插入一条用户积分记录
     *
     * @param integralLogId 用户积分表id
     * @param userId        用户id
     * @param quantity      本次获得值
     * @param sourceType    源类型
     * @param sourceId      源ID
     * @param description   内容描述
     * @return int
     * @author Wang Zhiyuan
     */
    int insertIntegralLog(@Param("integralLogId") long integralLogId, @Param("userId") long userId, @Param("quantity") int quantity, @Param("sourceType") String sourceType, @Param("sourceId") long sourceId, @Param("description") String description);

    /**
     * 积分记录
     *
     * @param userId 用户ID
     * @return java.util.List<com.wufanbao.api.oldclientservice.entity.UserIntegralLog>
     * @author Wang Zhiyuan
     */
    List<UserIntegralLog> getUserIntegralLog(@Param("userId") long userId);

    /**
     * 全部用户兑换记录
     *
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/2/26
     */
    List<Map> allIntegralExchange();

    /**
     * 用户积分兑换记录
     *
     * @param userId 用户ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018/2/26
     */
    List<Map> userIntegralExchange(@Param("userId") long userId);

    /**
     * 积分商城商品
     *
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     * @date 2018//2/26
     */
    List<Map> integralCommodity();

    /**
     * 根据商品id查询商品
     *
     * @param integralCommodityId 积分商城商品ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/2/26
     */
    Map getIntegralCommodity(@Param("integralCommodityId") long integralCommodityId);

    /**
     * 兑换商品
     *
     * @param integralCommodityId 积分商城商品ID
     * @param buyQuantity         购买数量
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/2/26
     */
    int updateCommodity(@Param("integralCommodityId") long integralCommodityId, @Param("buyQuantity") int buyQuantity);

    /**
     * 兑换商品及记录
     *
     * @param integralExchangeId  用户积分兑换表ID
     * @param userId              用户ID
     * @param integralCommodityId 积分商城商品ID
     * @param amount              积分
     * @param description         备注
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/5
     */
    int addIntegralExchangeLog(@Param("integralExchangeId") long integralExchangeId, @Param("userId") long userId, @Param("integralCommodityId") long integralCommodityId, @Param("amount") BigDecimal amount, @Param("description") String description);


}
