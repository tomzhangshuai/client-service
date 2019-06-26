package com.wufanbao.api.clientservice.common;

import com.wufanbao.api.clientservice.config.ClientSetting;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SignUtils {
    @Autowired
    private ClientSetting clientSetting;

    public String getSign(RequestData data) {
        String str = JsonUtils.GsonString(data) + "&" + data.getTimestamp() + clientSetting.getToken();
        //return new String(Hex.encodeHex(DigestUtils.md5(str)));
        return StringUtils.md5(str);
    }

    public String getSign(String jsonData, String timestamp) {
        String str = jsonData + "&" + timestamp + clientSetting.getToken();
        //return new String(Hex.encodeHex(DigestUtils.md5(str)));
        return StringUtils.md5(str);
    }

    public static void main(String[] args) {
        //登录token
        /*String s="{ \"body\" : \"{\\n \\\"loginNo\\\" : \\\"13588224138\\\",\\n \\\"loginType\\\" : \\\"password\\\",\\n \\\"password\\\" : \\\"12345678\\\"\\n}\", \"timestamp\" : \"20190222112909\", \"signType\" : \"md5\", \"version\" : 2 }";
        String str=s+"&"+"20190222112909"+"9dc1f470a02712426c3ee62fb8049eb3";
        System.out.println(StringUtils.md5(str));
        String s2="{\"body\":\"{\\\"pageIndex\\\":\\\"1\\\"}\",\"machineId\":\"\",\"signType\":\"md5\",\"timestamp\":\"1558061616283\",\"version\":\"2\"}";
        String str2=s2+"&"+"1558061616283"+"9dc1f470a02712426c3ee62fb8049eb3";
        System.out.println(StringUtils.md5(str2));
        String s3="{\"body\":\"{\\\"versionCode\\\":\\\"2.1.1\\\",\\\"versionType\\\":\\\"1\\\"}\",\"machineId\":\"\",\"signType\":\"md5\",\"timestamp\":\"1558061616283\",\"version\":\"2\"}";
        String str3=s3+"&"+"1558061616283"+"9dc1f470a02712426c3ee62fb8049eb3";
        System.out.println(StringUtils.md5(str3));*/

        String s4="{\"body\":\"{\\\"password\\\":\\\"147258\\\",\\\"phone\\\":\\\"18338666518\\\"}\",\"machineId\":\"\",\"signType\":\"md5\",\"timestamp\":\"1558061616283\",\"version\":\"2\"}";
        String str4=s4+"&"+"1558061616283"+"9dc1f470a02712426c3ee62fb8049eb3";
        System.out.println(StringUtils.md5(str4));

        String s5="{\"body\":\"{\\\"pageIndex\\\":\\\"1\\\",\\\"isRead\\\":\\\"0\\\"}\",\"machineId\":\"\",\"signType\":\"md5\",\"timestamp\":\"1558061616283\",\"version\":\"2\"}";
        String str5=s5+"&"+"1558061616283"+"9dc1f470a02712426c3ee62fb8049eb3";
        System.out.println(StringUtils.md5(str5));

    }

    /*public static void main(String args[]){
        String strDate = "2008-10-19 10:11:30.345" ;
        // 准备第一个模板，从字符串中提取出日期数字
        String pat1 = "yyyy-MM-dd HH:mm:ss.SSS" ;
        // 准备第二个模板，将提取后的日期数字变为指定的格式
        String pat2 = "yyyy年MM月dd日 HH时mm分ss秒SSS毫秒" ;
        SimpleDateFormat sdf1 = new SimpleDateFormat(pat1) ;        // 实例化模板对象
        SimpleDateFormat sdf2 = new SimpleDateFormat(pat2) ;        // 实例化模板对象
        Date d = null ;
        try{
            d = sdf1.parse(strDate) ;   // 将给定的字符串中的日期提取出来
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理
            e.printStackTrace() ;       // 打印异常信息
        }
        System.out.println(d);
        System.out.println(sdf2.format(d)) ;    // 将日期变为新的格式
    }*/

}
