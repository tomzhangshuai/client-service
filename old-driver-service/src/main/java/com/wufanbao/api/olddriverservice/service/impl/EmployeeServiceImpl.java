package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.Tool.AES_256;
import com.wufanbao.api.olddriverservice.dao.EmployeeDao;
import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.service.EmployeeService;
import com.wufanbao.api.olddriverservice.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User:Wangshihao
 * Date:2017-08-21
 * Time:16:49
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    /**
     * 登录
     */
    @Override
    public Employee queryEmployee(String Mb, String passWord) {
        return employeeDao.queryEmployee(Mb);
    }


    /****************************************************/
    /**
     * 判断用户登录
     *
     * @param loginNo  登录号
     * @param password 登录密码
     * @return
     */
    @Override
    public Employee checkLogin(String loginNo, String password) throws ServiceException {
        if (loginNo == null || loginNo.length() <= 0) {
            throw new ServiceException("登录号不能为空");
        }
        if (password == null || password.length() <= 0) {
            throw new ServiceException("登录密码不能为空");
        }
        String ePassword = AES_256.encryptionPassword(password);

        Employee employee = employeeDao.getEmployeeByLoginNo(loginNo);
        if (employee != null) {
            if (employee.getPassWord() != ePassword) {
                return null;
            }
        }
        return employee;
    }
}
