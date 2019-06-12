package com.wufanbao.api.oldclientservice.Tool;

import com.beust.jcommander.internal.Maps;

import java.util.Map;


/**
 * 格式化返回客户端数据格式（json）
 *
 * @author Wang Zhiyuan
 * @date 2018/3/31
 */
public class ReturnFormat {
    private static Map<String, String> messageMap = Maps.newHashMap();

    //初始化状态码与文字说明
    static {
        messageMap.put("0", "");
        messageMap.put("1", "请求成功");

        messageMap.put("400", "Bad Request!");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");
        messageMap.put("600", "Token 校验失败");

        messageMap.put("1000", "[服务器]运行时异常");
        messageMap.put("1001", "[服务器]空值异常");
        messageMap.put("1002", "[服务器]数据类型转换异常");
        messageMap.put("1003", "[服务器]IO异常");
        messageMap.put("1004", "[服务器]未知方法异常");
        messageMap.put("1005", "[服务器]数组越界异常");
        messageMap.put("1006", "[服务器]网络异常");

        messageMap.put("1010", "签到失败");
        messageMap.put("1011", "今天已经签过到了，不要重复签到");
        messageMap.put("1012", "注册失败");
        messageMap.put("1013", "该用户已经注册");
        messageMap.put("1014", "");
        messageMap.put("1015", "");

        messageMap.put("1020", "修改个人信息失败");
        messageMap.put("1021", "");
        messageMap.put("1022", "");
        messageMap.put("1023", "");
        messageMap.put("1029", "");

        messageMap.put("1030", "用户信息未完善");
        messageMap.put("1031", "此用户不存在");
        messageMap.put("1032", "");
        messageMap.put("1033", "");
        messageMap.put("1034", "");

        messageMap.put("1040", "该用户已经被绑定");
        messageMap.put("1041", "该用户没有注册");
        messageMap.put("1042", "不能绑定自己的账户");
        messageMap.put("1043", "不能绑定上级账户");
        messageMap.put("1044", "不能绑定主账户");

        messageMap.put("1050", "修改子账户权限失败");
        messageMap.put("1051", "");

        messageMap.put("1060", "删除子账户失败");
        messageMap.put("1061", "");

        messageMap.put("1070", "验证码发送失败");
        messageMap.put("1071", "");

        messageMap.put("1080", "验证码发送失败");
        messageMap.put("1081", "");

        messageMap.put("1090", "验证失败");
        messageMap.put("1091", "绑定失败");

        messageMap.put("1100", "积分发送失败");
        messageMap.put("1101", "该新闻已经领取过积分了");

        messageMap.put("1110", "发送过于频繁");

        messageMap.put("1120", "兑换失败");


        messageMap.put("2010", "缺少参数或值为空");

        messageMap.put("2029", "参数不合法");
        messageMap.put("2020", "无效的Token");
        messageMap.put("2021", "无操作权限");
        messageMap.put("2022", "RSA解密失败,密文数据已损坏");
        messageMap.put("2023", "请重新登录");

        messageMap.put("2030", "该二维码已失效");
        messageMap.put("2031", "你今天已经领过了");

        //thirdPartyLogin
        messageMap.put("2035", "验证码验证失败");
        messageMap.put("2036", "绑定失败");
        //lottery
        messageMap.put("2040", "抽奖失败");
        messageMap.put("2041", "积分不足");
        messageMap.put("2042", "次数不足");

        messageMap.put("2050", "绑定亲密付失败");
        messageMap.put("2051", "解绑亲密付失败");
        messageMap.put("2052", "子账户不能添加亲密付");
        messageMap.put("2053", "此账户已经被绑定");
        messageMap.put("2054", "密码错误");
        messageMap.put("2055", "主账户不能被绑定");

        messageMap.put("2060", "订单失效");
        messageMap.put("2061", "扫一扫");

    }

    public static String retParam(int code, Object data) {
        OutPutJson json = new OutPutJson(code, messageMap.get(String.valueOf(code)), data);
        return json.toString();
    }
}
