package com.wufanbao.api.clientservice.common;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.clientservice.config.ClientSetting;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;


@Component
public class CommonFun {
    @Autowired
    private ClientSetting clientSetting;

    private static List<String> list = new ArrayList();

    static {
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
    }

    //生成n位随机数
    public String random(int n) {
        if (n < 1 || n > 10) {
            throw new IllegalArgumentException("cannot random " + n + " bit number");
        }
        Random ran = new Random();
        if (n == 1) {
            return String.valueOf(ran.nextInt(10));
        }
        int bitField = 0;
        char[] chs = new char[n];
        for (int i = 0; i < n; i++) {
            while (true) {
                int k = ran.nextInt(10);
                if ((bitField & (1 << k)) == 0) {
                    bitField |= 1 << k;
                    chs[i] = (char) (k + '0');
                    break;
                }
            }
        }
        return new String(chs);
    }

    public void writer(HttpServletResponse response, ResponseData responseData) {
        PrintWriter writer = null;
        OutputStreamWriter osw = null;
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            osw = new OutputStreamWriter(response.getOutputStream(),
                    "UTF-8");
            writer = new PrintWriter(osw, true);
            String jsonStr = JSON.toJSONString(responseData);
            writer.write(jsonStr);
            writer.flush();
            writer.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            //logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } catch (IOException e) {
            //logger.error("过滤器返回信息失败:" + e.getMessage(), e);
        } finally {
            if (null != writer) {
                writer.close();
            }
            if (null != osw) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //发送短信
    public boolean sendMessage(String phone, String message) throws UnsupportedEncodingException {
        String url = "http://116.62.244.37/yqx/v1/sms/single_send";//http://202.91.244.252:30001/yqx/v1/sms/single_send
        String account = "5796";
        String apikey = "284859566e6ae78c918d0c5f57fc2636";
        String sign = Base64.encodeBase64String(DigestUtils.md5Hex(account + apikey).getBytes());

        JSONObject json = new JSONObject();
        json.put("account", "5796");
        json.put("mobile", phone);
        json.put("text", message);
        json.put("sign", sign);
        json.put("extend", "");
        // 设置PostMethod
        PostMethod postMethod = new PostMethod(url);
        postMethod.getParams().setContentCharset("utf-8");
        postMethod.getParams().setHttpElementCharset("utf-8");
        // 设置requestEntity
        RequestEntity requestEntity = new StringRequestEntity(json.toString(), "application/json", "UTF-8");
        postMethod.setRequestEntity(requestEntity);
        // http client 参数设置 连接超时 读取数据超时
        HttpClient httpClient = new HttpClient();
        // httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(timeout);
        // httpClient.getHttpConnectionManager().getParams().setSoTimeout(timeout);
        try {
            httpClient.executeMethod(postMethod);
            if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
                InputStream resStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(resStream, "UTF-8"));
                StringBuffer resBuffer = new StringBuffer();
                String resTemp = "";
                while ((resTemp = br.readLine()) != null) {
                    resBuffer.append(resTemp);
                }
                Data data = JsonUtils.GsonToBean(resBuffer.toString(), Data.class);
                String code = data.get("code").toString();
                if (code.equals("0")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (HttpException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            postMethod.releaseConnection();
        }
    }

    public String sendPost(String url, String param) {
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
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
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
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
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

    public String sendGet(String url) {
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
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
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
    //图片资源
    public String sourceImage(String imageUrl) {
        if (StringUtils.isNullOrEmpty(imageUrl)) {
            return "";
        }
        if (imageUrl.contains("http")) {
            return imageUrl;
        }
        return clientSetting.getResource() + imageUrl;
    }

    //获取取餐时间点
    public List<String> getTakeTimes() {
        Date date = new Date();
        String curent = DateUtils.DateToString(date, "yyyy-MM-dd");
        Double currentDate = Double.valueOf(DateUtils.DateToString(date, "HH")) + Double.parseDouble(DateUtils.DateToString(date, "mm")) / 100;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        String tomorrow = DateUtils.DateToString(c.getTime(), "yyyy-MM-dd");
        List<String> result = new ArrayList();

        for (String str : list) {
            Double d = Double.valueOf(str.substring(0, 2)) + Double.parseDouble(str.substring(3)) / 100;
            if (d > currentDate) {
                result.add(curent + " " + str);
            }
            //result.add(tomorrow + " " + str);
        }
        return result;
    }

    //获得用户的成长值
    public Data getGradeValue(long gradeValue) {
        Data data = new Data();
        if (gradeValue >= 0 && gradeValue <= 99) {
            data.put("gradeValue", 1);
            data.put("gradeText", "入门吃货");
            data.put("gradeNext", 100 - gradeValue);
        } else if (gradeValue >= 100 && gradeValue <= 499) {
            data.put("gradeValue", 2);
            data.put("gradeText", "小露吃性");
            data.put("gradeNext", 500 - gradeValue);
        } else if (gradeValue >= 500 && gradeValue <= 999) {
            data.put("gradeValue", 3);
            data.put("gradeText", "吃货新秀");
            data.put("gradeNext", 1000 - gradeValue);
        } else if (gradeValue >= 1000 && gradeValue <= 9999) {
            data.put("gradeValue", 4);
            data.put("gradeText", "吃中之神");
            data.put("gradeNext", 10000 - gradeValue);
        } else {
            data.put("gradeValue", 5);
            data.put("gradeText", "饕餮");
        }
        return data;
    }

    //根据连续签到天数获取用户应得积分和成长值
    public int getIntegral(int continuousSignedDay) {
        int day = 5;
        if (continuousSignedDay <= day) {
            switch (continuousSignedDay) {
                case 1:
                    return 1;
                case 2:
                    return 3;
                case 3:
                    return 5;
                case 4:
                    return 7;
                case 5:
                    return 9;
                default:
                    return 0;
            }
        } else {
            return 10;
        }
    }

    public String getClientIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public String getStackTraceInfo(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();
            return sw.toString();
        } catch (Exception ex) {
            return "读取异常发生错误";
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {

                }
            }
            if (pw != null) {
                pw.close();
            }
        }
    }
}
