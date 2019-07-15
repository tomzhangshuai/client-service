package com.wufanbao.api.distributionservice.services;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wufanbao.api.distributionservice.config.Code;
import com.wufanbao.api.distributionservice.config.CodeException;
import com.wufanbao.api.distributionservice.config.Setting;
import com.wufanbao.api.distributionservice.dao.CompanyDao;
import com.wufanbao.api.distributionservice.dao.EmployeeDao;
import com.wufanbao.api.distributionservice.entities.Company;
import com.wufanbao.api.distributionservice.entities.CompanyType;
import com.wufanbao.api.distributionservice.entities.Employee;
import com.wufanbao.api.distributionservice.tools.CommonFun;
import com.wufanbao.api.distributionservice.tools.RedisUtils;
import com.wufanbao.api.distributionservice.tools.Token;
import com.wufanbao.api.distributionservice.tools.TokenFactor;
import com.wufanbao.api.distributionservice.transfer.EmployeeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private CompanyDao companyDao;

    @Autowired
    Setting setting;

    /**
     * token存储前缀
     */
    private final String TokenStorePrefix="DSAPI_Token_";

    /**
     * 员工存储前缀
     */
    private final String EmployeeStorePrefix="DSAPI_Employee_";

    /**
     * 员工登录
     *
     * @param tokenFactor 构建token因子
     * @param loginNo
     * @param password
     * @return
     */
    public EmployeeInfo login(TokenFactor tokenFactor, String loginNo, String password) throws Exception {
        //根据手机后去获取用户信息
        String ePassword = CommonFun.encryptionPassword(password);
        //查询用户信息
        Employee employee= employeeDao.getEmployeeByLoginNo(loginNo);
        if(employee==null){
            throw new CodeException(Code.employeeLoginError);
        }
        if(!ePassword.equals(employee.getPassWord())){
            throw new CodeException(Code.employeeLoginError);
        }
        if(!employee.getIsActive()){
            throw new CodeException(Code.employeeForbid);
        }
        long companyId = employee.getCompanyId();

        Company company=companyDao.getCompany(employee.getCompanyId());

        if(company.getCompanyType()!=4){
            throw new CodeException(Code.employeeNotAllow);
        }
        if(!company.getIsActive()){
            throw new CodeException(Code.employeeLoginError);
        }
        //检查token 假如存在以前的token，删除以前的token缓存
        String employeeStoreKey=EmployeeStorePrefix+employee.getEmployeeId();
        String lastLoginInfo=redisUtils.get(employeeStoreKey);
        if(lastLoginInfo!=null) {
            EmployeeInfo lastInfo=JSON.parseObject(lastLoginInfo,new TypeReference<EmployeeInfo>() {});
            if(lastInfo!=null) {
                redisUtils.del(TokenStorePrefix+lastInfo.getToken());
            }
        }
        //创建新的用户信息缓存，并返回
        tokenFactor.setEmployeeId(String.valueOf(employee.getEmployeeId()));
        Token token=new Token(tokenFactor);
        EmployeeInfo info=new EmployeeInfo();
        info.setCompanyId(employee.getCompanyId());
        info.setEmployeeId(employee.getEmployeeId());
        info.setEmployeeName(employee.getEmployeeName());
        info.setMb(employee.getMb());
        info.setToken(token.create(setting.getAesKey()));
        String tokenStoreKey=TokenStorePrefix+info.getToken();

        String employeeStr=JSON.toJSONString(info);
        long invalidSeconds=CommonFun.getInvaildSeconds(1,5);
        redisUtils.set(tokenStoreKey,employeeStr,(int)invalidSeconds);
        redisUtils.set(employeeStoreKey,employeeStr,(int)invalidSeconds);
        return info;
    }


    /**
     * 登出
     * @param info
     */
    public void logout(EmployeeInfo info){
        redisUtils.del(TokenStorePrefix+info.getToken());
        String redisEmployeeInfoString=redisUtils.get(EmployeeStorePrefix+info.getEmployeeId());
        EmployeeInfo redisEmployeeInfo=JSON.parseObject(redisEmployeeInfoString,new TypeReference<EmployeeInfo>(){});
        if(redisEmployeeInfo!=null)
        {
            if(redisEmployeeInfo.getToken().equals(info.getToken())) {//redis 用户token信息一致,移除用户信息
                redisUtils.del(EmployeeStorePrefix+info.getEmployeeId());
            }
        }
    }


}
