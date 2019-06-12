package com.wufanbao.api.oldclientservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.UserRegistered;
import com.wufanbao.api.oldclientservice.service.UserPortraitService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User:Wangshihao
 * Date:2017-08-03
 * Time:10:47
 */
@Controller
@RequestMapping("userPortrait")
public class UserPortraitController {

    @Autowired
    private UserPortraitService portraitService;
    @Autowired
    private ClientSetting clientSetting;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserPortraitController.class);

    /**
     * 修改头像
     *
     * @editor zhaojing
     */
    @LoginRequired
    @RequestMapping(value = "updateUserPortrait", method = RequestMethod.POST)
    @ResponseBody
    public Object updateUserPortrait(UserRegistered userRegistered, HttpServletRequest request) throws IOException {
        long userId = Long.parseLong(request.getParameter("userId"));
        String portrait = request.getParameter("portrait");
        Map map = new HashMap();
        try {
            String url = clientSetting.getUploadUrl() + "/Image/UplodaStream";
            String path = "/Upload/AppClintImage";
            String ext = ".png";
            String dst_fname = URLEncoder.encode(portrait, "utf-8");
            dst_fname = dst_fname.replaceAll("\\+", "%20");
            String sr = sendPost(url, "path=" + path + "&ext=" + ext + "&bytes=" + dst_fname);
            Map map1 = JSON.parseObject(sr);
            String data = map1.get("data").toString();
            List<Object> list = JSONObject.parseArray(data);
            String imageUrl = list.get(0).toString();
            userRegistered.setPortrait(imageUrl);
            portraitService.updatePortrait(userRegistered);
            System.out.println(sr);
            map.put("return", "success");
        } catch (Exception e) {
            map.put("return", "error");
            logger.info(e.toString());
            throw new RuntimeException();
        }
        return map;
    }


    public static String byteArrayToStr(byte[] byteArray) {
        if (byteArray == null) {
            return null;
        }
        String str = new String(byteArray);
        return str;
    }

    public Object getUploadFile(MultipartFile file, String path) {
        if (!file.isEmpty()) {
            try {
                String fullName = this.getFileStoragePath(path, file.getContentType());
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(fullName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return null;
            } catch (Exception e) {
                logger.error("Error occurs while save upload file.", e);
                return null;
            }
        } else {
            logger.error("no image received");
            return null;
        }
    }


    private String getFileStoragePath(String path, String contentType) {
        String separator = File.separator;
        String suffix = "png|jpe?g|bmp";
        Pattern r = Pattern.compile(suffix);
        Matcher m = r.matcher(contentType);
        if (StringUtils.isBlank(contentType) || !contentType.startsWith("image/") || !m.find()) {
            logger.error("not support type for file upload");
            return null;
        }
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }


        String fileName = String.format("%s.%s", System.currentTimeMillis(), contentType.replace("image/", ""));
        return String.format("%s%s%s", path, separator, fileName);
    }

    public String getPortraitUrl(long userId, String portrait) {
        String imageUrl = null;
        Map map = new HashMap();
        // 对字节数组字符串进行Base64解码并生成图片
        try {
            String url = clientSetting.getUploadUrl() + "/Image/UplodaStream";
            String path = "/Upload/AppClintImage";
            String ext = ".png";
            String dst_fname = URLEncoder.encode(portrait, "utf-8");
            dst_fname = dst_fname.replaceAll("\\+", "%20");
            String sr = sendPost(url, "path=" + path + "&ext=" + ext + "&bytes=" + dst_fname);
            Map map1 = JSON.parseObject(sr);
            String data = map1.get("data").toString();
            List<Object> list = JSONObject.parseArray(data);
            imageUrl = list.get(0).toString();
        } catch (Exception e) {
            imageUrl = "";
        }
        return imageUrl;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
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
