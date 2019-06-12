package com.wufanbao.api.oldclientservice.dao;

import com.wufanbao.api.oldclientservice.entity.UserGrade;
import com.wufanbao.api.oldclientservice.entity.UserGradeLog;
import com.wufanbao.api.oldclientservice.entity.UserGradePrivilege;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Data:2018/01/04
 * Time:14:06
 */
public interface UserGradeDao {
    /**
     * 用户成长值增长
     *
     * @param userId 用户id
     * @param gain   本次获得值
     * @return
     */
    int gradeGrowUp(@Param("userId") long userId, @Param("gain") int gain);

    /**
     * 插入一条用户成长值记录
     *
     * @param userGradeLogId 用户记录表id
     * @param userId         用户id
     * @param gain           本次获得值
     * @param sourceType     源类型
     * @param sourceId       源ID
     * @param description    内容描述
     * @return
     */
    int insertGradeLog(
            @Param("userGradeLogId") long userGradeLogId,
            @Param("userId") long userId,
            @Param("gain") int gain,
            @Param("sourceType") String sourceType,
            @Param("sourceId") long sourceId,
            @Param("description") String description
    );

    //获取用户等级名称
    String getUserGradeName(@Param("gradeValue") long gradeValue);

    /**
     * 用户等级信息
     *
     * @param userId
     * @return
     */
    UserGrade getUserGrade(@Param("userId") long userId);

    /**
     * 根据等级查询等级信息
     *
     * @param gradeNum
     * @return
     */
    UserGrade getUserGradePlusOne(@Param("gradeNum") int gradeNum);

    /**
     * 排名
     *
     * @param userId
     * @return
     */
    double userRanking(@Param("userId") long userId);

    /**
     * 根据等级id查询用户特权
     *
     * @param userGradeId
     * @return
     */
    UserGradePrivilege getUserGradePrivilege(@Param("userGradeId") long userGradeId, @Param("privilege") int privilege);

    String getUserGradeLog(@Param("userId") long userId, @Param("sourceType") String sourceType);

    /**
     * 查询用户今天是否获得优惠券
     *
     * @param userId
     * @param couponDefinitionId
     * @return java.lang.Boolean
     * @author Wang Zhiyuan
     * @date 2018/3/25
     */
    Boolean ifGet(@Param("userId") long userId, @Param("couponDefinitionId") long couponDefinitionId);

}
