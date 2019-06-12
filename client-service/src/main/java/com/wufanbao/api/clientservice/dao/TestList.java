/*
package com.wufanbao.api.clientservice.dao;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestList {
    static List<String> list = null;
    static List<String>list2=null;
    public static void initList() {
        list = new ArrayList<String>();
        for(int i = 1; i <= 5; i++) {
            list.add(String.format("第%d个元素", i));
        }
        list2=new ArrayList<String>();
        for (int i=10;i>=8;i--){
            list2.add(String.format("第%d个元素",i));
        }
        list2.add("第2个元素");
        for (int i=7;i>=6;i--){
            list2.add(String.format("第%d个元素",i));
        }
        list2.add("第3个元素");
        list2.add("第5个元素");
    }

    public static void deleteFromList() {
        String str;
        String str2;
        System.out.println(list2.toString());
        Iterator<String> iterator2=list2.iterator();
        while (iterator2.hasNext()){
            str2=iterator2.next();
            Iterator<String> iterator = list.iterator();
            System.out.println(str2);
            while (iterator.hasNext()) {
                str = iterator.next();
                System.out.println(str);
                if (str2.equals(str)) {
                    iterator.remove();
                    iterator2.remove();
                }
            }
        }
        System.out.println(list.toString());
        System.out.println(list2.toString());

    }
    public static void main(String[] args) {
        initList();
        deleteFromList();
    }
}
*/

