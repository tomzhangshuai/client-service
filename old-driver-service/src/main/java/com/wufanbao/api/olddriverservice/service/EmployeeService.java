package com.wufanbao.api.olddriverservice.service;


import com.wufanbao.api.olddriverservice.entity.Employee;

public interface EmployeeService {
    /**
     * 登陆
     */
    public Employee queryEmployee(String Mb, String passWord);


    /***********************************************************/
    /**
     * 判断用户登录
     *
     * @param loginNo  登录号
     * @param password 登录密码
     * @return 员工数据
     */
    public Employee checkLogin(String loginNo, String password) throws ServiceException;
}
