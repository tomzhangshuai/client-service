package com.wufanbao.api.distributionservice.tools;

import java.util.Date;

public class CommonFun {

    /**
     * 加密密码
     * @param password
     * @return
     */
    public static String encryptionPassword(String password) {
        String data = password;
        int[] buf = new int[16];
        for (int i = 0; i < data.length(); i = i + 2) {
            if (data.charAt(i) != '0') {
                buf[i / 2] = Integer.parseInt(data.substring(i, i + 2), 16);
            } else {
                buf[i / 2] = Integer.parseInt(data.substring(i + 1, i + 2), 16);
            }
        }
        char[] res2 = new char[32];
        int y = 0;
        for (int i = 0; i < 16; i++) {
            int b = ((buf[i] + i) & 0xf);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
            b = ((buf[i] + i) >> 4);
            b = b > 9 ? b + 0x57 : b + 0x30;
            res2[y] = (char) b;
            y++;
        }
        String result = new String(res2);
        return result;
    }


    /**
     * 获取失效秒数（以下一天的5点为基准）
     * 假如 days 1  invaildHour 5  当前时间为2019-1-17 10:38  失效时间为2019-1-18 05:00   返回（date(2019-1-18 05:00)-date(2019-1-17 10:38)的秒数
     * @param days 失效的天数
     * @param invaildHour 失效的小时
     * @return
     */
    public static long getInvaildSeconds(int days,int invaildHour)
    {
        Date now=new Date();
        long times=now.getTime()/1000;//转换成秒
        long seconds=days*24*60*60-(times%(24*60*60))+(invaildHour-8)*60*60;//计算下一天的5时失效
        return seconds;
    }


}
