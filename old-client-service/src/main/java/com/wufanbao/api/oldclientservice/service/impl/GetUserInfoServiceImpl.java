package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.dao.*;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.GetUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * user:WangZhiyuan
 */
@Service
public class GetUserInfoServiceImpl implements GetUserInfoService {
    @Autowired
    private GetUserInfoDao getUserInfoDao;
    @Autowired
    private UserCouponDao userCouponDao;
    @Autowired
    private InsertInvitationCodeDao insertInvitationCodeDao;
    @Autowired
    private UserGradeDao userGradeDao;
    @Autowired
    private SignInDao signInDao;
    @Autowired
    private UserLoginDao userLoginDao;
    @Autowired
    private ClientSetting clientSetting;
    //private final static String ImageUrl = ResourceBundle.getBundle("IP").getString("ImageUrl");

    /**
     * 根据用户UserId获取银行卡是否被绑定
     *
     * @param userId 用户UserId
     * @return
     */
    @Override
    public List<UserBank> getUserBankInfo(long userId) {
        return getUserInfoDao.getUserBankInfo(userId);
    }

    /**
     * 根据用户userId 获取用户当前便当币数量
     *
     * @param userId 用户UserId
     * @return
     */
    @Override
    public Map getUserUsableBalance(long userId) {
        //根据用户id查询用户可用的便当币的数量
        BigDecimal usableBalance = getUserInfoDao.getUserUsableBalance(userId);
        Map map = new HashMap();
        //判断用户有没有便当币没有的话给他空
        if (usableBalance == null) {
            map.put("usableBalance", 0);
        } else {
            map.put("usableBalance", usableBalance);
        }
        return map;
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param userId 用户id
     * @return 存放着用户信息的Map
     */
    @Override
    public Map getUserInfoService(Long userId, String bindingId) throws Exception {
        Map map = new HashMap();
        UserRegistered userInfo = new UserRegistered();
        if (bindingId != null && userId == null) {
            Long userIds = userLoginDao.getUserIdByBindingId(bindingId);
            if (userIds != null) {
                userId = userIds;
            }
        }
        try {
            userInfo = getUserInfoDao.getUserInfo(userId);
        } catch (Exception e) {
            userInfo = null;
        }
        if (userInfo == null) {
            return map;
        }
        //获取用户可用优惠券集合
        try {
            List<UserCoupon> userCouponList = userCouponDao.userCouponDaoRow(userId);
            int UserCouponDaoRow;//可用优惠券数量
            if (userCouponList.size() < 0) {
                UserCouponDaoRow = 0;
            } else {
                UserCouponDaoRow = userCouponList.size();
            }
            //根据userId获取用户信息
            //判读支付密码是否设置
            if (userInfo.getPayPassWord() == null || userInfo.getPayPassWord().equals("")) {
                //0没有设置
                map.put("payPasswordType", 0);
            } else {
                //1已经设置
                map.put("payPasswordType", 1);
            }
            //根据用户id去查询该用户是否被邀请
            List<UserInvitation> userInvitationList = insertInvitationCodeDao.getUserInvitation(userId);
            if (userInvitationList.size() > 0) {
                //邀请码已经填写
                map.put("invitationCodeType", 1);
            } else {
                //邀请码没有填写
                map.put("invitationCodeType", 0);
            }
            //判断银行卡有没有填写
            List<UserBank> userBankList = getUserInfoDao.getUserBankInfo(userId);
            if (userBankList.size() > 0) {
                map.put("userBankType", 1);
            } else {
                map.put("userBankType", 0);
            }
            boolean ifSignIn = signInDao.ifSignIn(userId);
            //个人的验证码
            map.put("invitationCode", userInfo.getInvitationCode());
            map.put("usableBalance", userInfo.getUsableBalance());//用户的便当币余额
            map.put("integral", userInfo.getIntegral());
            map.put("mb", userInfo.getmB());//用户手机号
            map.put("ifSignIn", ifSignIn);
            map.put("UserCouponDaoRow", UserCouponDaoRow);//用户可用优惠券数量
            String portrait = "";
            if (userInfo.getPortrait() != null && !("").equals(userInfo.getPortrait())) {
                portrait = clientSetting.getResource() + userInfo.getPortrait();
            } else {
                portrait = "";
            }
            map.put("portrait", portrait);//用户头像
            map.put("UserName", userInfo.getUserName());//用户名
            map.put("LoginType", 1);//正确时状态：1
            map.put("UserId", userInfo.getUserId());//用户id
            map.put("sex", userInfo.getSex());
            List list = new ArrayList();
            list.add(userInfo.getBreakFast());
            list.add(userInfo.getLunch());
            list.add(userInfo.getDinner());
            list.add(userInfo.getBirthday());
            List list1 = formatDate(list);
            map.put("birthday", list1.get(0));
            map.put("breakfast", list1.get(1));
            map.put("lunch", list1.get(2));
            map.put("dinner", list1.get(3));
            map.put("gradeName", userGradeDao.getUserGradeName(userInfo.getGradeValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    private Date canTime(List<Date> list) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf1.format(new Date());
        Date newDate = sdf.parse(sdf.format(new Date()));
        for (int i = 0; i < list.size() - 1; i++) {
            Date date = sdf.parse(sdf.format(list.get(i)));
            if (newDate.getTime() < date.getTime()) {
                String sss = sdf.format(list.get(i));
                String ss = time + " " + sss;
                return sdf2.parse(ss);
            }
        }
        return new Date();
    }

    /**
     * 常用取餐时间
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public ResponseInfo oftenTime(long userId) throws Exception {
        Map map = new HashMap();
        UserRegistered userInfo = getUserInfoDao.getUserInfo(userId);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ResponseInfo responseInfo = new ResponseInfo();
        if (userInfo.getBreakFast() != null && userInfo.getLunch() != null && userInfo.getDinner() != null) {
            List<Date> list = new ArrayList();
            list.add(userInfo.getBreakFast());
            list.add(userInfo.getLunch());
            list.add(userInfo.getDinner());
            list.add(userInfo.getBirthday());

            map.put("canTime", sdf1.format(canTime(list)));
            List list1 = formatDate(list);
            map.put("breakfast", list1.get(1));
            map.put("lunch", list1.get(2));
            map.put("dinner", list1.get(3));
            map.put("oftenTime", true);
        } else {
            map.put("breakfast", null);
            map.put("lunch", null);
            map.put("dinner", null);
            map.put("oftenTime", false);
            map.put("canTime", sdf1.format(new Date()));
        }
        map.put("appointTime", appointTime());
        responseInfo.setCode(1);
        responseInfo.setData(map);
        return responseInfo;
    }

    private List appointTime() {
        List list = new ArrayList();
        List list1 = new ArrayList();
        List list2 = new ArrayList();
        List list3 = new ArrayList();
        list.add("00:00");
        list.add("00:30");
        list.add("01:00");
        list.add("01:30");
        list.add("02:00");
        list.add("02:30");
        list.add("03:00");
        list.add("03:30");
        list.add("04:00");
        list.add("04:30");
        list.add("05:00");
        list.add("05:30");
        list.add("06:00");
        list.add("06:30");
        list.add("07:00");
        list.add("07:30");
        list.add("08:00");
        list.add("08:30");
        list.add("09:00");
        list.add("09:30");
        list.add("10:00");
        list.add("10:30");
        list.add("11:00");
        list.add("11:30");
        list.add("11:45");
        list.add("12:00");
        list.add("12:15");
        list.add("12:30");
        list.add("13:00");
        list.add("13:30");
        list.add("14:00");
        list.add("14:30");
        list.add("15:00");
        list.add("15:30");
        list.add("16:00");
        list.add("16:30");
        list.add("17:00");
        list.add("17:15");
        list.add("17:30");
        list.add("17:45");
        list.add("18:00");
        list.add("18:15");
        list.add("18:30");
        list.add("19:00");
        list.add("19:30");
        list.add("20:00");
        list.add("21:30");
        list.add("22:00");
        list.add("22:30");
        list.add("23:00");
        list.add("23:30");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        SimpleDateFormat sdf1 = new SimpleDateFormat("mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        Double date1 = Double.valueOf(sdf.format(date)) + Double.parseDouble(sdf1.format(date)) / 100;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();
        for (int i = 0; i < list.size(); i++) {
            Double date2 = Double.valueOf(list.get(i).toString().substring(0, 2)) + Double.parseDouble(list.get(i).toString().substring(3)) / 100;
            if (date2 < date1) {
                list1.add(list.get(i));
            } else {
                list2.add(list.get(i));
            }
        }
        for (int d = 0; d < list2.size(); d++) {
            list3.add(sdf2.format(date) + " " + list2.get(d).toString());
        }
        for (int ji = 0; ji < list1.size(); ji++) {
            list3.add(sdf2.format(tomorrow) + " " + list1.get(ji).toString());
        }

        return list3;
    }

    private List formatDate(List list) throws Exception {
        String date1;
        List list1 = new ArrayList();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (null != list.get(3)) {
                date1 = sdf1.format(list.get(3));
            } else {
                date1 = "";
            }
        } catch (Exception e) {
            date1 = "";
        }
        list1.add(date1);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (int i = 0; i < list.size() - 1; i++) {
            String date;
            try {
                if (null != list.get(i)) {
                    date = sdf.format(list.get(i));
                } else {
                    date = "";
                }
            } catch (Exception e) {
                date = "";
            }
            list1.add(date);
        }
        return list1;
    }
}
