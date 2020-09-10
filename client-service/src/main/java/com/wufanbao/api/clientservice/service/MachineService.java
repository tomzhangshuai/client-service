package com.wufanbao.api.clientservice.service;

import com.wufanbao.api.clientservice.common.*;
import com.wufanbao.api.clientservice.dao.CompanyDao;
import com.wufanbao.api.clientservice.dao.MachineDao;
import com.wufanbao.api.clientservice.dao.ProductDao;
import com.wufanbao.api.clientservice.entity.Area;
import com.wufanbao.api.clientservice.entity.Machine;
import com.wufanbao.api.clientservice.entity.MachineStatus;
import com.wufanbao.api.clientservice.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

@Service
public class MachineService {
    //test1
    @Autowired
    private MachineDao machineDao;
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CommonFun commonFun;

    //获取所有运行中的机器
    public List<Machine> getMachines() {
        return machineDao.getMachines();
    }

    //更加机器id获取机器信息
    public Machine getMachine(long machineId) {
        return machineDao.getMachine(machineId);
    }

    //获取附近的5台机器
    public List<Data> getMachineProducts(String areaName, BigDecimal x, BigDecimal y) throws ApiServiceException {
        Area area = companyDao.getCityByAreaName(areaName);
        if (area == null) {
            throw new ApiServiceException(1001, "当前城市未开发");
        }
        return getNearMachineProducts(area.getAreaId(), x, y);
    }

    //根据机器id获取附近5台机器
    public List<Data> getMachineProductsByMachineId(long machineId) throws ApiServiceException {
        Machine putMachine = machineDao.getMachine(machineId);
        if (putMachine == null) {
            throw new ApiServiceException("当前机器不存在");
        }
        if(putMachine.getStatus()==5) {
            //机器处在测试阶段，只获取当前机器信息
            return getTestingMachineProducts(putMachine);
        }else {
            //机器非测试阶段
            int areaId = putMachine.getPutAreaId();
            BigDecimal x = putMachine.getX();
            BigDecimal y = putMachine.getY();
            return getNearMachineProducts(areaId, x, y);
        }
    }

    private  List<Data> getTestingMachineProducts(Machine machine)
    {
        List<Data> dataList = new ArrayList<>();
        Data datas = new Data();
        datas.put("distance", 0);
        datas.put("machineId", machine.getMachineId());
        datas.put("putMachineName", machine.getPutMachineName());
        datas.put("x", machine.getX());
        datas.put("y", machine.getY());
        datas.put("areaId", machine.getPutAreaId());
        datas.put("areaName", machine.getAreaName());
        datas.put("address", machine.getAddress());
        String[] seekPhotos = null;
        if (!StringUtils.isNullOrEmpty(machine.getSeekPhotos())) {
            String[] strs = machine.getSeekPhotos().split(";");
            seekPhotos = new String[strs.length];
            for (int i = 0; i < strs.length; i++) {
                seekPhotos[i] = commonFun.sourceImage(strs[i]);
            }
        }
        datas.put("seekPhotos", seekPhotos);
        datas.put("inMaintenance", machine.getInMaintenance());
        datas.put("quantity", machine.getQuantity());
        datas.put("status", machine.getStatus());
        List<Product> products = productDao.getProductsByMachineId(machine.getMachineId());
        for (Product p : products) {
            p.setImageUrl(commonFun.sourceImage(p.getImageUrl()));
            String[] attachImageUrls = null;
            if (!StringUtils.isNullOrEmpty(p.getAttachImageUrls())) {
                String[] strs = p.getAttachImageUrls().split(";");
                attachImageUrls = new String[strs.length];
                for (int i = 0; i < strs.length; i++) {
                    attachImageUrls[i] = commonFun.sourceImage(strs[i]);
                }
            }
            p.setAttachImageUrls(org.apache.commons.lang.StringUtils.join(attachImageUrls, ";"));
        }
        if (products.size() >0) {
            datas.put("products", JsonUtils.GsonString(products));
        }
        dataList.add(datas);
        return dataList;
    }

    private List<Data> getNearMachineProducts(int areaId, BigDecimal x, BigDecimal y) {
        //获取机器，启用中的并且运行中
        List<Machine> machines = machineDao.getMachinesByAreaId(areaId);
        Collections.sort(machines, new Comparator<Machine>() {

            @Override
            public int compare(Machine o1, Machine o2) {
                double distance1 = Distance.GetDistance(x, y, o1.getX(), o1.getY());
                double distance2 = Distance.GetDistance(x, y, o2.getX(), o2.getY());
                if (distance1 > distance2) {
                    return 1;
                } else if (distance1 < distance2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        List<Data> dataList = new ArrayList<>();
        for (Machine machine : machines) {
            Data datas = new Data();
            //分别算出来用户与每台机器的距离
            double distance = Distance.GetDistance(x, y, machine.getX(), machine.getY());
            datas.put("distance", distance);
            datas.put("machineId", machine.getMachineId());
            datas.put("putMachineName", machine.getPutMachineName());
            datas.put("x", machine.getX());
            datas.put("y", machine.getY());
            datas.put("areaId", machine.getPutAreaId());
            datas.put("areaName", machine.getAreaName());
            datas.put("address", machine.getAddress());
            String[] seekPhotos = null;
            if (!StringUtils.isNullOrEmpty(machine.getSeekPhotos())) {
                String[] strs = machine.getSeekPhotos().split(";");
                int j = 0;
                for (int i = 0; i < strs.length; i++) {
                    if (!StringUtils.isNullOrEmpty(strs[i])) {
                        j++;
                    }
                }
                seekPhotos = new String[j];
                int t=0;
                for (int i = 0; i <strs.length; i++) {
                    if(!StringUtils.isNullOrEmpty(strs[i])){
                        seekPhotos[t] = commonFun.sourceImage(strs[i]);
                        t++;
                    }
                }
            }
            datas.put("seekPhotos", seekPhotos);
            datas.put("inMaintenance", machine.getInMaintenance());
            Boolean flag=machine.getInMaintenance();
            datas.put("quantity", machine.getUseableQuantity());
            datas.put("status", machine.getStatus());
            List<Product> products = productDao.getProductsByMachineId(machine.getMachineId());
            for (Product p : products) {
                p.setImageUrl(commonFun.sourceImage(p.getImageUrl()));
                String[] attachImageUrls = null;
                if (!StringUtils.isNullOrEmpty(p.getAttachImageUrls())) {
                    String[] strs = p.getAttachImageUrls().split(";");
                    attachImageUrls = new String[strs.length];
                    for (int i = 0; i < strs.length; i++) {
                        attachImageUrls[i] = commonFun.sourceImage(strs[i]);
                    }
                }
                p.setAttachImageUrls(org.apache.commons.lang.StringUtils.join(attachImageUrls, ";"));
            }
            if (products.size() <= 0) {
                continue;
            }
            datas.put("products", JsonUtils.GsonString(products));
            dataList.add(datas);
        }
        return dataList;
    }

    //根据所在区域获取机器
    public List<Machine> getMachineByArearId(int areaId, BigDecimal x, BigDecimal y) {
        List<Machine> machines = machineDao.getMachinesByAreaId(areaId);
        Collections.sort(machines, new Comparator<Machine>() {
            /*
             * int compare(Machine o1, Machine o2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            @Override
            public int compare(Machine o1, Machine o2) {
                double distance1 = Distance.GetDistance(x, y, o1.getX(), o1.getY());
                double distance2 = Distance.GetDistance(x, y, o2.getX(), o2.getY());
                o1.setDistance(distance1);
                o2.setDistance(distance2);
                if (distance1 > distance2) {
                    return 1;
                } else if (distance1 < distance2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        return machines;
    }
}
