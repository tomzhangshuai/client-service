package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.User;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Wang Zhiyuan
 * @date 2017-08-01
 */
public interface UserLoginDao {
    /**
     * 根据手机号密码登录
     *
     * @param mb       手机号
     * @param password 密码
     * @return 用户信息
     * @author Wang Zhiyuan
     */
    UserRegistered getUserLogin(@Param("mb") String mb, @Param("password") String password);

    /**
     * 根据手机号快速登录
     *
     * @param mB
     * @return com.wufanbao.api.oldclientservice.entity.UserRegistered
     * @author Wang Zhiyuan
     * @date 2018/4/9
     */
    UserRegistered checkUserMb(@Param("mB") String mB);

    /**
     * 用户userId
     *
     * @param userId
     * @return
     */
    UserRegistered appointTime(@Param("userId") long userId);

    /**
     * 查看用户是否绑定微信openId
     */
    Long getUserBinding(@Param("bindingId") String bindingId);

    /**
     * 修改用户绑定的userID
     *
     * @param bindingId openId
     * @param userId    用户ID
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/3/6
     */
    int updateBinDingUserId(@Param("bindingId") String bindingId, @Param("userId") long userId);

    /**
     * 根据BinDingId获取用户id
     *
     * @param bindingId
     * @return
     */
    Long getUserIdByBindingId(@Param("bindingId") String bindingId);

    User getUserByUserId(@Param("userId") long userId);

    boolean updateUserByUserId(@Param("loginNo") String logingNo, @Param("userId") long userId);

    /**
     * 用户绑定第三方平台id
     *
     * @param userId      用户ID
     * @param bindingId   bindingID
     * @param bindingType 绑定平台
     * @return java.lang.Integer
     */
    int userBinding(@Param("userId") long userId, @Param("bindingId") String bindingId, @Param("bindingType") int bindingType);

    /**
     * 查询用户是否绑定过
     *
     * @param userId      用户ID
     * @param bindingType 绑定类型
     * @return java.util.Map
     * @author Wang Zhiyuan
     * @date 2018/5/17
     */
    Map getUserBindingInfo(@Param("userId") long userId, @Param("bindingType") int bindingType);

    /**
     * 更新用户第三方ID
     *
     * @param userId      用户ID
     * @param bindingType 绑定类型
     * @param bindingId   绑定ID
     * @return int
     * @author Wang Zhiyuan
     * @date 2018/5/17
     */
    int updateUserBinding(@Param("userId") long userId, @Param("bindingType") int bindingType, @Param("bindingId") String bindingId);

}
