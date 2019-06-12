package com.wufanbao.api.oldclientservice.Tool;

import com.wufanbao.api.oldclientservice.entity.Prize;

import java.text.DecimalFormat;
import java.util.List;

/**
 * alphaWuFan
 * 抽奖计算
 *
 * @author Wang Zhiyuan
 * @date 2018-04-16 14:50
 **/
public class PrizeCount {
    public int getPrizeIndex(List<Prize> prizes) {
        DecimalFormat df = new DecimalFormat("######0.00");
        int random = -1;
        try {
            //计算总权重
            double sumWeight = 0;
            for (Prize p : prizes) {
                sumWeight += p.getWinRate();
            }

            //产生随机数
            double randomNumber;
            randomNumber = Math.random();

            //根据随机数在所有奖品分布的区域并确定所抽奖品
            double d1 = 0;
            double d2 = 0;
            for (int i = 0; i < prizes.size(); i++) {
                d2 += Double.parseDouble(String.valueOf(prizes.get(i).getWinRate())) / sumWeight;
                if (i == 0) {
                    d1 = 0;
                } else {
                    d1 += Double.parseDouble(String.valueOf(prizes.get(i - 1).getWinRate())) / sumWeight;
                }
                if (randomNumber >= d1 && randomNumber <= d2) {
                    random = i;

                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("生成抽奖随机数出错，出错原因：" + e.getMessage());
        }
        return random;
    }

}
