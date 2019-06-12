//package com.wufanbao.api.clientservice;
//
//import com.wufanbao.api.clientservice.entity.Prize;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CommonTest {
//    @Test
//    public void extractPrize(){
//
//        String str="http://m.wufanbao.cn/#/utility/machine.html?machineId=194752675841";
//        String[] strs=str.split("machineId=");
//
//
//
//        Prize[] prizes = Prize.getProbabilityPrizes();
//        for (int i=0;i<10;i++) {
//            Prize prize = lottery1(prizes);
//            if(prize!=null) {
//                System.out.println(i + ":" + prize.getId() + "," + prize.getProbability() + "," + prize.getValue() + "," + prize.getCount());
//            }else{
//                System.out.println(i+":null");
//            }
//        }
//
//    }
//
//    private Prize lottery(List<Prize> prizes) {
//        //总的概率区间
//        float totalPro = 0f;
//        //存储每个奖品新的概率区间
//        List<Float> proSection = new ArrayList<Float>();
//        //遍历每个奖品，设置概率区间，总的概率区间为每个概率区间的总和
//        for (Prize prize : prizes) {
//            //每个概率区间为奖品概率乘以100（把三位小数转换为整）再乘以剩余奖品数量
//            totalPro += prize.getProbability() * 100 * prize.getCount();
//            proSection.add(totalPro);
//        }
//        //获取总的概率区间中的随机数
//        Random random = new Random();
//        float randomPro = (float) random.nextInt((int) totalPro);
//        //判断取到的随机数在哪个奖品的概率区间中
//        for (int i = 0, size = proSection.size(); i < size; i++) {
//            if (randomPro >= proSection.get(i) && randomPro < proSection.get(i + 1)) {
//                return prizes.get(i);
//            }
//        }
//        return null;
//    }
//
//    private Prize lottery1(Prize[] prizes) {
//        int length=0;
//        for (Prize prize : prizes) {
//            length += prize.getCount();
//        }
//        for(int i=0; i<prizes.length; i++){
//            Random random = new Random();
//            int ret = random.nextInt(length);       //获取 0-总数 之间的一个随随机整数
//            if(ret<prizes[i].getCount()){
//                return prizes[i];                   //如果在当前的概率范围内,得到的就是当前概率
//            }
//            else {
//                length-=prizes[i].getCount();       //否则减去当前的概率范围,进入下一轮循环
//            }
//        }
//        return null;
//    }
//
//
//}
