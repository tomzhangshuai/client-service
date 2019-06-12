package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserCapitalLog;
import com.wufanbao.api.oldclientservice.entity.UserFamilyPayRelation;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 家庭付
 *
 * @author Wang Zhiyuan
 **/
public interface FamilyPayDao {
    /**
     * 根据用户id查询用户家庭付信息
     *
     * @param userId 用户Id
     * @return com.wufanbao.api.oldclientservice.entity.UserFamilyPayRelation
     * @author Wang Zhiyuan
     */
    UserFamilyPayRelation getUserFamilyPayInfo(@Param("userId") long userId);

    /**
     * 修改父Id
     *
     * @param userId   用户Id
     * @param parentId 父Id
     * @return int
     * @author Wang Zhiyuan
     */
    int updateParentId(@Param("userId") long userId, @Param("parentId") long parentId);

    /**
     * 插入家庭付关系表
     *
     * @param masterUserId 主账户ID
     * @param userId       用户ID
     * @param quotaType    家庭付类型
     * @param limitQuota   限制额度
     * @param totalQuota   总额度
     * @param consumeQuota 已消费额度
     * @param totalAmount  总消费额度
     * @param disabled     是否启用
     * @return int
     * @author Wang Zhiyuan
     */
    int insertFamilyPayInfo(@Param("masterUserId") long masterUserId,
                            @Param("userId") long userId,
                            @Param("quotaType") int quotaType,
                            @Param("limitQuota") boolean limitQuota,
                            @Param("totalQuota") BigDecimal totalQuota,
                            @Param("consumeQuota") BigDecimal consumeQuota,
                            @Param("totalAmount") BigDecimal totalAmount,
                            @Param("disabled") boolean disabled
    );

    /**
     * 更改子账号权限
     *
     * @param quotaType    家庭付类型
     * @param limitQuota   限制额度
     * @param totalQuota   总额度
     * @param consumeQuota 已消费额度
     * @param disabled     是否启用
     * @param userId       用户ID
     * @param masterUserId 主账户ID
     * @return int
     * @author Wang Zhiyuan
     */
    int updateFamilyPayInfo(@Param("quotaType") Integer quotaType,
                            @Param("limitQuota") Boolean limitQuota,
                            @Param("totalQuota") BigDecimal totalQuota,
                            @Param("consumeQuota") BigDecimal consumeQuota,
                            @Param("disabled") Boolean disabled,
                            @Param("userId") long userId,
                            @Param("masterUserId") long masterUserId
    );

    /**
     * 删除用户家庭付信息
     *
     * @param userId       用户ID
     * @param masterUserId 主账户ID
     * @return int
     * @author Wang Zhiyuan
     */
    int deleteSonUser(@Param("userId") long userId, @Param("masterUserId") long masterUserId);

    /**
     * 查询主账户下家庭付信息
     *
     * @param masterUserId 主账户ID
     * @return java.util.List<java.util.Map>
     * @author Wang Zhiyuan
     */
    List<Map> selectUserFamilyPayInfo(@Param("masterUserId") long masterUserId);

    /**
     * 查询子账户的关联信息
     *
     * @return java.util.List<java.util.Map>
     * @date 2018/6/29
     */
    List<Map> selectSonUserFamilyPayInfo(@Param("userId") long userId);

    /**
     * 根据用户ID和主账户ID查询信息
     *
     * @param masterUserId 主账户ID
     * @param userId       用户ID
     * @return com.wufanbao.api.oldclientservice.entity.UserFamilyPayRelation
     * @author Wang Zhiyuan
     */
    UserFamilyPayRelation getUserFamilyInfo(@Param("masterUserId") long masterUserId, @Param("userId") long userId);

    /**
     * 根据子账户查询父级余额
     *
     * @param userId 用户ID
     * @return java.util.Map
     * @author Wang Zhiyuan
     */
    Map getParentBalance(@Param("userId") long userId);

    /**
     * 修改用户家庭付额度
     *
     * @param userId 用户ID
     * @param amount 消费
     * @return int
     * @author Wang Zhiyuan
     */
    int updateUserFamilyPay(@Param("userId") long userId, @Param("amount") BigDecimal amount);

    /**
     * 判断用户是否可以使用家庭付
     *
     * @param userId 用户ID
     * @return com.wufanbao.api.oldclientservice.entity.UserFamilyPayRelation
     * @author Wang Zhiyuan
     */
    UserFamilyPayRelation iFFamilyPay(@Param("userId") long userId);

    UserFamilyPayRelation ifMasterUser(@Param("userId") long userId);

    /**
     * 根据用户ID和订单id去查询家庭付余额
     *
     * @param userId      用户ID
     * @param userOrderId 用户订单ID
     * @param sourceType  来源
     * @return com.wufanbao.api.oldclientservice.entity.UserCapitalLog
     * @author Wang Zhiyuan
     */
    UserCapitalLog getUserMaster(@Param("userId") long userId, @Param("userOrderId") long userOrderId, @Param("sourceType") String sourceType);

    /**
     * 用户家庭付信息
     *
     * @param userId 用户ID
     * @return java.util.Map
     * @date 2018/7/2
     */
    Map userFamilyPayInfo(@Param("userId") long userId);

}
