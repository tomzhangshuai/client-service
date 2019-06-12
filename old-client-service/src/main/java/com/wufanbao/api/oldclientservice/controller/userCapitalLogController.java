package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.GetUserInfoService;
import com.wufanbao.api.oldclientservice.service.UserCapitalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User:WangZhiyuan
 * Date:2017-08-5
 */
@Controller
@RequestMapping(value = "UserCapitalLog")
public class userCapitalLogController {
    @Autowired
    private UserCapitalLogService userCapitalLogService;
    @Autowired
    private GetUserInfoService getUserInfoService;

    /**
     * 获取用户资金流水 GetUserInfoServiceImpl
     *
     * @param userId
     * @return
     * @eidtor zhaojing
     */
    @LoginRequired
    @ResponseBody
    @RequestMapping(value = "getUserCapitalLogInfo", method = RequestMethod.POST)
    public Object getUserCapitalLogInfo(long userId) {
        Map map = getUserInfoService.getUserUsableBalance(userId);
        BigDecimal usableBalance = BigDecimal.valueOf(Double.parseDouble(map.get("usableBalance").toString()));
        //查询年份
        UserCapitalLogInfo userCapitalLogInfo = new UserCapitalLogInfo();
        //new一个新的参数
        UserCapitalLogParameter uCLP = new UserCapitalLogParameter();
        uCLP.setUserId(userId);
        //根据用户id查询所有年份
        List<UserCapitalLogParameter> ylist = userCapitalLogService.getYear(uCLP);
        List<UserCapitalLogInfo.YearInfo> zongYearInfo = new ArrayList<>();
        if (ylist.size() > 0) {
            //循环根据年份去查询月份数
            for (int i = 0; i < ylist.size(); i++) {
                //用户资金流水信息一年的类
                UserCapitalLogInfo.YearInfo yearInfo = new UserCapitalLogInfo.YearInfo();
                UserCapitalLogParameter uCLP1 = new UserCapitalLogParameter();
                uCLP1.setUserId(userId);
                uCLP1.setYear(ylist.get(i).getYear());
                int yea = ylist.get(i).getYear();
                //根据年份查询出来月份数
                List<UserCapitalLogParameter> mlist = userCapitalLogService.getMonth(uCLP1);
                List<UserCapitalLogInfo.YearInfo.MonthInfo> montInfoList = new ArrayList<>();//规范
                //循环根据年份，月份去查询当月的数据
                for (int j = 0; j < mlist.size(); j++) {
                    //规范
                    UserCapitalLogInfo.YearInfo.MonthInfo monthInfo = new UserCapitalLogInfo.YearInfo.MonthInfo();
                    UserCapitalLogParameter uCLP2 = new UserCapitalLogParameter();
                    uCLP2.setUserId(userId);
                    uCLP2.setYear(ylist.get(i).getYear());
                    uCLP2.setMonth(mlist.get(j).getMonth());
                    //将查询出来的某年某月的数据放到集合中
                    List<UserCapitalLog> ri = userCapitalLogService.getMonthInfo(uCLP2);
                    monthInfo.setMonth(ri);
                    monthInfo.setMonthNumber(mlist.get(j).getMonth());
                    montInfoList.add(monthInfo);
                }
                yearInfo.setYear(montInfoList);//规范
                yearInfo.setYearNumber(ylist.get(i).getYear());
                zongYearInfo.add(yearInfo);
            }
            userCapitalLogInfo.setType(1);
            userCapitalLogInfo.setZongYear(zongYearInfo);
            userCapitalLogInfo.setUsableBalance(usableBalance);
            return userCapitalLogInfo;
        } else {
            userCapitalLogInfo.setType(0);
            userCapitalLogInfo.setZongYear(zongYearInfo);
            userCapitalLogInfo.setUsableBalance(usableBalance);
            return userCapitalLogInfo;
        }
    }
}

