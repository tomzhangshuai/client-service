package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * creator:WangZhiyuan
 * createTime;2017/9/7 10:02
 */
public interface EmployeeLoginService {
    /**
     * 员工登录
     *
     * @param mb
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    ResponseInfo<Employee> employeeLogin(String mb, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException;

    /**
     * 用工配送单信息
     *
     * @param employeeId
     * @return
     */
    ResponseInfo distributionInfo(long employeeId);


}
