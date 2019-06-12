package com.wufanbao.api.olddriverservice.dao;

import com.wufanbao.api.olddriverservice.entity.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * User:Wangshihao
 * Date:2017-08-21
 * Time:17:39
 */
public interface EmployeeDao {
    /**
     * 登陆
     */
    public Employee queryEmployee(String Mb);

    /**
     * 根据用户手机号去查询用户信息
     *
     * @param Mb 员工手机号
     * @return user:WangZhiyuan
     */
    Employee getEmployee(@Param("Mb") String Mb, @Param("passWord") String passWord);

    /**
     * 根据手机号获取用户ID
     *
     * @param loginNo 手机号
     * @return java.lang.Long
     * @author Wang Zhiyuan
     * @date 2018/5/16
     */
    Long getEmployeeIdByMb(@Param("loginNo") String loginNo);

/****************************************************************************************************************************/

    /**
     * 根据登录号获取员工数据
     *
     * @param loginNo
     * @return
     */
    Employee getEmployeeByLoginNo(@Param("loginNo") String loginNo);

    /**
     * 根据员工 Id 获取员工数据
     *
     * @param employeeId
     * @return
     */
    Employee getEmployeeById(@Param("employeeId") long employeeId);
}
