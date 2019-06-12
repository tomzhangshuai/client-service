package com.wufanbao.api.oldclientservice.controller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


/**
 * User:Wangshihao
 * Date:2017-09-25
 * Time:11:54
 */
public class SMSInterface {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SMSInterface.class);


    public Object SMS(String mB, String code) throws UnsupportedEncodingException {
        String url = "http://202.91.244.252:30001/yqx/v1/sms/single_send";
        String account = "5796";
        String apikey = "284859566e6ae78c918d0c5f57fc2636";
        String yanzhengma = "您好，您的验证码是：" + code + "，5分钟内有效";
        System.out.println(yanzhengma);
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(
                account + apikey).getBytes());
        JSONObject json = new JSONObject();
        json.put("account", account);
        json.put("mobile", mB);
        json.put("text", yanzhengma);
        json.put("sign", sign);
        json.put("extend", "");
        System.out.println("request:" + json.toString());
        // 设置PostMethod
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setContentCharset("utf-8");
        postMethod.getParams().setHttpElementCharset("utf-8");
        // 设置requestEntity
        RequestEntity requestEntity = new StringRequestEntity(
                json.toString(), "application/json", "UTF-8");
        postMethod.setRequestEntity(requestEntity);
        // http client 参数设置 连接超时 读取数据超时
        HttpClient httpClient = new HttpClient();
        // httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        try {
            httpClient.executeMethod(postMethod);
            if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
                InputStream resStream = postMethod
                        .getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(resStream, "UTF-8"));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                String result = resBuffer.toString();
                System.out.println(result);
                return result;
            }
        } catch (HttpException e) {
            logger.info(e.toString());
        } catch (IOException e) {
            logger.info(e.toString());
        } finally {
            postMethod.releaseConnection();
        }

        return 1;
    }

    //        String appId="EUCP-EMY-SMS1-0POZC";
//        String url="http://shmtn.b2m.cn/simpleinter/sendSMS";
//        String content = "【伍饭宝】您好，您的验证码是："+code+"，5分钟内有效";
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
//        String timestamp=sdf.format(new Date());
//        String secretKey ="36BC4BA09AA628D7";
//        String sign=DigestUtils.md5Hex(appId+secretKey+timestamp);
//        String sr=sendPost(url, "appId="+appId+"&timestamp="+timestamp+"&sign="+sign+"&mobiles="+mB+"&content="+content);
//        System.out.println(sr);
//    /**
//     * 向指定 URL 发送POST方法的请求
//     *
//     * @param url
//     *            发送请求的 URL
//     * @param param
//     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
//     * @return 所代表远程资源的响应结果
//     */
    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn
                    .getOutputStream(), "UTF-8");
            // 发送请求参数
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}

