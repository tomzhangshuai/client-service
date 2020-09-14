package com.wufanbao.api.clientservice.service;
import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wufanbao.api.clientservice.common.Data;
import com.wufanbao.api.clientservice.entity.UserOrder;

import java.util.*;

public class Demo {
    public static void main(String []args) {
        //c:111
        //d:111
        //C:222
        //c:333
        HashMap map=new HashMap<String,Object>();
        map.put("machineId", "001");
        map.put("address", "address");
        map.put("machineName", "machineName");
        map.put("putMachineName", "putMachineName");
        List<UserOrder> userOrders = new List<UserOrder>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<UserOrder> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(UserOrder userOrder) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends UserOrder> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends UserOrder> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public UserOrder get(int index) {
                return null;
            }

            @Override
            public UserOrder set(int index, UserOrder element) {
                return null;
            }

            @Override
            public void add(int index, UserOrder element) {

            }

            @Override
            public UserOrder remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<UserOrder> listIterator() {
                return null;
            }

            @Override
            public ListIterator<UserOrder> listIterator(int index) {
                return null;
            }

            @Override
            public List<UserOrder> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
        UserOrder userOrder1=new UserOrder();
        userOrder1.setUserOrderId(111);
        userOrder1.setStatus(1);
        userOrder1.setDescription("takeNo111");
        userOrder1.setMachineId(001);
        UserOrder userOrder2=new UserOrder();
        userOrder2.setUserOrderId(222);
        userOrder2.setStatus(2);
        userOrder2.setDescription("takeNo222");
        userOrder2.setMachineId(002);
        UserOrder userOrder3=new UserOrder();
        userOrder3.setUserOrderId(333);
        userOrder3.setStatus(3);
        userOrder3.setDescription("takeNo333");
        userOrder3.setMachineId(003);

        userOrders.add(userOrder1);
        userOrders.add(userOrder2);
        userOrders.add(userOrder3);
        List<HashMap> userOrderDatas = new ArrayList<>();
        for (int i = 1; i <=3 ; i++) {
            HashMap userOrderData = new HashMap();
            switch(i){
                case 1:
                    userOrderData.put("userOrderId", userOrder1.getUserOrderId());
                    userOrderData.put("description", userOrder1.getDescription());
                    userOrderData.put("status", userOrder1.getStatus());
                    break;
                case 2:
                    userOrderData.put("userOrderId", userOrder2.getUserOrderId());
                    userOrderData.put("description", userOrder2.getDescription());
                    userOrderData.put("status", userOrder2.getStatus());
                    break;
                case 3:
                    userOrderData.put("userOrderId", userOrder3.getUserOrderId());
                    userOrderData.put("description", userOrder3.getDescription());
                    userOrderData.put("status", userOrder3.getStatus());
                    break;
            }
            userOrderDatas.add(userOrderData);
        }
        HashMap data = new HashMap();
        data.put("machine", map);
        data.put("userOrders", userOrderDatas);

        System.out.println("HashMap map:"+map);
        System.out.println("list<HashMap>userOrderDatas:"+userOrderDatas);
        System.out.println("Map.put(map,userOrderDatas):"+data);
        //return data;












    }
}
