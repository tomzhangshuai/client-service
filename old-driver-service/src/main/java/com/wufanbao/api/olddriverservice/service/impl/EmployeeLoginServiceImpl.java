package com.wufanbao.api.olddriverservice.service.impl;

import com.wufanbao.api.olddriverservice.dao.DistributionOrderDao;
import com.wufanbao.api.olddriverservice.dao.EmployeeDao;
import com.wufanbao.api.olddriverservice.entity.DistributionOrderInfo;
import com.wufanbao.api.olddriverservice.entity.Employee;
import com.wufanbao.api.olddriverservice.entity.ResponseInfo;
import com.wufanbao.api.olddriverservice.service.EmployeeLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * creator:WangZhiyuan
 * createTime;2017/9/7 10:30
 */
@Service
@Transactional
public class EmployeeLoginServiceImpl implements EmployeeLoginService {
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DistributionOrderDao distributionOrderDao;

    /**
     * 员工登录
     *
     * @param mb
     * @param password
     * @return
     */
    @Override
    public ResponseInfo<Employee> employeeLogin(String mb, String password) {
        ResponseInfo<Employee> responseInfo = new ResponseInfo<Employee>();
        //根据手机后去获取用户信息
        Employee employee = new Employee();
        String ePassword = encryptionPassword(password);
        //查询用户信息
        try {
            //根据用户手机号和密码去查询用户信息
            employee = employeeDao.getEmployee(mb, ePassword);
        } catch (Exception e) {
            employee = null;
        }
        //用户不存在
        if (employee == null) {
            responseInfo.setCode(2001);
            responseInfo.setData(employee);
        } else if (employee.getIsActive() == 1) {
            responseInfo.setCode(1);
            employee.setPassWord("");
            responseInfo.setData(employee);
        } else {
            responseInfo.setCode(2002);
            responseInfo.setData(employee);
        }
        return responseInfo;
    }

    /**
     * 用工配送单信息
     *
     * @param employeeId
     * @return
     */
    @Override
    public ResponseInfo distributionInfo(long employeeId) {
        ResponseInfo<List<DistributionOrderInfo>> responseInfo = new ResponseInfo<>();
        List<DistributionOrderInfo> distributionOrderInfoList = new ArrayList<>();
        //获取配送单信息
        try {
            distributionOrderInfoList = distributionOrderDao.getDistributionOrderByEmployeeId(employeeId);
            for (int i = 0; i < distributionOrderInfoList.size(); i++) {
                //获取订单货物数量放到配送单信息
                distributionOrderInfoList.get(i).setCargoQuantity(distributionOrderDao.getCargoQuantity(distributionOrderInfoList.get(i).getDistributionOrderId()));
                //获取机器数量放到配送单信息
                distributionOrderInfoList.get(i).setDeviceNumber(distributionOrderDao.getDeviceNumber(distributionOrderInfoList.get(i).getDistributionOrderId()));
            }
        } catch (Exception e) {
            distributionOrderInfoList = null;
        }
        if (distributionOrderInfoList.size() > 0) {
            responseInfo.setCode(1);
            responseInfo.setData(distributionOrderInfoList);
        } else {
            responseInfo.setCode(2011);
            responseInfo.setData(distributionOrderInfoList);
        }
        return responseInfo;
    }


    /**
     * 密码加密
     *
     * @param password
     * @return
     */
    public String encryptionPassword(String password) {
        String data = password;
        int[] buf = new int[16];
        for (int i = 0; i < data.length(); i = i + 2) {
            if (data.charAt(i) != '0') {
                buf[i / 2] = Integer.parseInt(data.substring(i, i + 2), 16);
            } else {
                buf[i / 2] = Integer.parseInt(data.substring(i + 1, i + 2), 16);
            }
        }
        char[] res2 = new char[32];
        int y = 0;
        for (int i = 0; i < 16; i++) {
            int b = ((buf[i] + i) & 0xf);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
            b = ((buf[i] + i) >> 4);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
        }
        String result = new String(res2);
        return result;
    }
}
