//package com.wufanbao.api.oldclientservice.controller;
//
//import com.wufanbao.api.oldclientservice.Tool.IDGenerator;
//import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
//import com.wufanbao.api.oldclientservice.Tool.RandomNum;
//import com.wufanbao.api.oldclientservice.entity.State;
//import com.wufanbao.api.oldclientservice.entity.UserAuth;
//import com.wufanbao.api.oldclientservice.entity.UserBank;
//import com.wufanbao.api.oldclientservice.entity.UserRegistered;
//import com.wufanbao.api.oldclientservice.service.UserAuthService;
//import com.wufanbao.api.oldclientservice.service.UserBankService;
//import com.wufanbao.api.oldclientservice.service.UserPortraitService;
//import org.json.JSONObject;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
////import sdk.api.DefaultApi;
////import sdk.wxlink.ApiException;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
///**
// *  User:Wangshihao
// *  Date:2017-08-04
// *  Time:14:35
// */
//@Controller
//@RequestMapping("userBank")
//public  class  UserBankController  {
//
//    @Autowired
//    private JedisPool jedisPool;
//    //private  final DefaultApi api  =  new DefaultApi();
//    @Autowired
//    private  UserBankService  userBankService;
//    @Autowired
//    private  UserAuthService  userAuthService;
//    @Autowired
//    private  UserPortraitService  userPortraitService;
//    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserBankController.class);
//    /**
//     *  验证银行卡信息是否正确
//     *  @param  request
//     *  @return
//     *  @throws
//     */
//    @LoginRequired
//    @RequestMapping(value  =  "userBankAdd",  method  =  RequestMethod.POST)
//    @ResponseBody
//    public Object userBankAdd(HttpServletRequest request)  throws ApiException {
//        //用户id
//        String  usercode  =  request.getParameter("userId");
//        long  userId  =  Long.parseLong(usercode);
//        //开户姓名
//        String  accName  =  request.getParameter("fullName");
//        //开户手机号
//        String  cardPhone  =  request.getParameter("phone");
//        //银行卡
//        String  cardNo  =  request.getParameter("account");
//        //身份证
//        String  certificateNo  =  request.getParameter("certificateNo");
//        String  appkey  =  "6972c5e166cf2b99ea69fb65b3c91da9";
//        UserBank  userBank  =  userBankService.checkAccount(cardNo);
//        if  (userBank  ==  null)  {
//            String  result1  =  api.keyelement(accName,cardPhone, certificateNo, cardNo, appkey);
//            System.out.println(result1);
//            JSONObject jsonObject = new JSONObject(result1);
//            String result = jsonObject.get("result").toString();
//            JSONObject jsonObject1 = new JSONObject(result);
//            System.out.println(jsonObject.get("result").toString());
//            String jj = jsonObject1.get("success").toString();
//            String ss = "true";
//            System.out.println(jj);
//            //String kk = jsonObject1.get("respMsg").toString();
//            if (jj.equals(ss)) {
//                Map map = new HashMap();
//                map.put("return", "success");
//                return map;
//            }
//            Map map2 = new HashMap();
//            map2.put("return", "error");
//            return map2;
//        }
//        Map map1 = new HashMap();
//        map1.put("return", "error");
//        return map1;
//    }
//    /**
//     * 获取用户银行卡信息
//     */
//    @LoginRequired
//    @RequestMapping(value = "userByid", method = RequestMethod.POST)
//    @ResponseBody
//    public Object userByid(long userId) {
//        System.out.println(userId);
//        List<UserBank> userBankList = userBankService.selectUserBank(userId);
//        System.out.println(userBankList);
//        if (userBankList != null) {
//            System.out.println(userBankList);
//            State state = new State();
//            state.setSuccess(userBankList);
//            state.setError(1);
//            return state;
//        }
//        State state1 = new State();
//        state1.setError(1);
//        return state1;
//    }
//    /**
//     * 删除用户银行卡
//     */
//    @LoginRequired
//    @RequestMapping(value = "deleteuserBank", method = RequestMethod.POST)
//    @ResponseBody
//    public Object deleteUserBank(HttpServletRequest request) {
//        String account = request.getParameter("account");
//        System.out.println(account);
//        Map map = new HashMap();
//        try {
//            userBankService.deleteUserBank(account);
//            map.put("return", "success");
//            return map;
//        } catch (RuntimeException e) {
//            logger.info(e.toString());
//            map.put("return", "error");
//            return map;
//        }
//    }
//    /**
//     * 发送验证码
//     */
//    @LoginRequired
//    @RequestMapping(value = "bankverification", method = RequestMethod.POST)
//    @ResponseBody
//    public Object Bankverification(HttpServletRequest request) throws IOException {
//        String cardPhone = request.getParameter("cardPhone");
//        Map map1 = new HashMap();
//        try {
//            RandomNum randomNum = new RandomNum();
//            String code = randomNum.random(4);
//            System.out.println("验证码:"+code);
//            System.out.println(">>>>>>>>>>>><<<<<<"+cardPhone);
//            Jedis jedis = jedisPool.getResource();
//            String str = Long.toString(Long.parseLong(cardPhone),12)+4;
//            System.out.println(str);
//            try {
//                String sss = jedis.get(str);
//                if (Integer.parseInt(sss) < 3) {
//                    if (jedis.get(str) != null) {
//                        jedis.incr(str);
//                        jedis.set(cardPhone,code);
//                        jedis.expire(cardPhone,300);
//                        SMSInterface smsInterface = new SMSInterface();
//                        Object status = smsInterface.SMS(cardPhone,code);
//                        System.out.println(">>>>>>>>>>>>>>>>>>状态"+status);
//                        Map map = new HashMap();
//                        map.put("return", "success");
//                        return map;
//                    }
//                } else if (Integer.parseInt(sss) == 3) {
//                    Map map = new HashMap();
//                    map1.put("return","2");
//                    return map1;
//                }
//            }catch (Exception e) {
//                if (jedis.get(str) == null) {
//                    jedis.incr(str);
//                    jedis.expire(str, 300);
//                    jedis.set(cardPhone,code);
//                    jedis.expire(cardPhone,300);
//                    SMSInterface smsInterface = new SMSInterface();
//                    Object status = smsInterface.SMS(cardPhone,code);
//                    System.out.println(">>>>>>>>>>>>>>>>>>状态"+status);
//                    Map map = new HashMap();
//                    map.put("return", "success");
//                    return map;
//                }
//                logger.info(e.toString());
//            }finally {
//                if (null!=jedis){
//                    jedis.close();
//                }
//            }
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            map1.put("return", "error");
//        }
//        return map1;
//    }
//    /**
//     * 验证验证码是否正确
//     */
//    @LoginRequired
//    @RequestMapping(value = "bankcompare", method = RequestMethod.POST)
//    @ResponseBody
//    public Object Bankcompare(HttpServletRequest request) {
//        String cardPhone = request.getParameter("cardPhone");//开户手机号
//        String code = request.getParameter("code");//验证码
//        String usercode = request.getParameter("userId");//userid
//        long userId = Long.parseLong(usercode);
//        String bankName = request.getParameter("bankName");//开户行
//        String accName = request.getParameter("accName");//开户姓名
//        String account = request.getParameter("account");//银行卡
//        String certificateNo = request.getParameter("certificateNo");//身份证
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            String codes = jedis.get(cardPhone);
//            if (codes != null) {
//                System.out.println(codes.equals(code));
//                if (codes.equals(code)) {
//                    long userBankId = IDGenerator.generateById("UserBankId", userId);
//                    UserBank userBank = new UserBank();
//                    userBank.setUserBankId(userBankId);
//                    userBank.setUserId(userId);
//                    userBank.setBankName(bankName);
//                    userBank.setFullName(accName);
//                    userBank.setPhone(cardPhone);
//                    System.out.println(userBank.getBankName());
//                    userBank.setAccount(account);
//                    userBankService.insertUserBank(userBank);
//                    UserAuth userAuth = new UserAuth();
//                    userAuth = userAuthService.check(certificateNo);
//                    if (userAuth == null) {
//                        UserAuth userAuth1 = new UserAuth();
//                        userAuth1.setUserId(userId);
//                        userAuth1.setCardType(1);
//                        userAuth1.setCardNo(certificateNo);
//                        userAuth1.setCardImages(" ");
//                        System.out.println(userAuth1.getUserId());
//                        userAuthService.insertUserAuth(userAuth1);
//                        System.out.println(userId);
//                        UserRegistered userRegistered = new UserRegistered();
//                        userRegistered.setIsAuth(0);
//                        userRegistered.setUserId(userId);
//                        userPortraitService.updateisAuth(userRegistered);
//                    }
//                    Map map = new HashMap();
//                    map.put("return", "success");
//                    return map;
//                }
//            }
//        }catch (Exception e){
//            logger.info(e.toString());
//        }finally {
//            if (null!=jedis){
//                jedis.close();
//            }
//        }
//        Map map1 = new HashMap();
//        map1.put("return", "error");
//        return map1;
//    }
//}