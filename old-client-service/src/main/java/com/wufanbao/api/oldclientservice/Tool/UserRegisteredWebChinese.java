package com.wufanbao.api.oldclientservice.Tool;

import com.wufanbao.api.oldclientservice.core.LRUCache;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * User:Wangshihao
 * Date:2017-08-02
 * Time:10:27
 */

/**
 * Tool：短信接口调用
 */
public class UserRegisteredWebChinese {
    public Object WebChinese(String mb) throws IOException {

        //分配1000条缓存 并保存5分钟
        LRUCache lruCache = new LRUCache(10000, 300000);

        String mB = mb;

        //UserRegistered userRegistered = new UserRegistered();
        //String in = userRegistered.getmB();
        if (mB != " " && mB != null) {
            Random random = new Random();
            int suiji = random.nextInt(10000);
            lruCache.put(mB, suiji);
            System.out.println(suiji);
            String yanzhengma = "您好，您的验证码：" + suiji + ",10分钟内有效";
            System.out.println(yanzhengma);
            HttpClient client = new HttpClient();
            PostMethod postMethod = new PostMethod("http://118.178.138.170/msg/HttpBatchSendSM");
            postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
            NameValuePair[] data = {
                    new NameValuePair("account", "cszh960"),// 注册的用户名
                    new NameValuePair("pswd", "hzSd960.com"),//注册成功后，登录网站使用的密匙
                    new NameValuePair("mobile", mB),//手机号码
                    new NameValuePair("msg", yanzhengma),//设置短信内容
                    new NameValuePair("needstatus", "true")
            };
            postMethod.setRequestBody(data);
            client.executeMethod(postMethod);
            Header[] headers = postMethod.getRequestHeaders();
            int statusCode = postMethod.getStatusCode();
            System.out.println(statusCode);
            for (Header h : headers) {
                System.out.println(h.toString());
            }
            String result = new String(postMethod.getResponseBodyAsString().getBytes("utf-8"));
            System.out.println(result);
            postMethod.releaseConnection();
            Map map = new HashMap();
            map.put("success", "success");
            System.out.println(map);
            return map;
        }
        Map map1 = new HashMap();
        map1.put("error", "error");
        System.out.println(map1);
        return map1;
    }
}
