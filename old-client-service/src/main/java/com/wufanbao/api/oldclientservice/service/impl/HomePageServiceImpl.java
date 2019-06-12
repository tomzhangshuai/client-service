package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.config.ClientSetting;
import com.wufanbao.api.oldclientservice.Tool.Distance;
import com.wufanbao.api.oldclientservice.dao.AppAdDao;
import com.wufanbao.api.oldclientservice.dao.HomePageDao;
import com.wufanbao.api.oldclientservice.entity.*;
import com.wufanbao.api.oldclientservice.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

/**
 * User:WangZhiyuan
 */
@Service
public class HomePageServiceImpl implements HomePageService {
    private static HomePageServiceImpl homePageServiceImpl;
    @Autowired
    private HomePageDao homePageDao;
    @Autowired
    private ClientSetting clientSetting;

    public void setHomePageDao(HomePageDao homePageDao) {
        this.homePageDao = homePageDao;
    }

    @PostConstruct     //关键二   通过@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作
    public void init() {
        homePageServiceImpl = this;
        homePageServiceImpl.homePageDao = this.homePageDao;   // 初使化时将已静态化的testService实例化

    }

    @Autowired
    private AppAdDao appAdDao;

    /**
     * 根据定位地址提供最近的机器
     *
     * @param name 城市名称
     * @param x    经线
     * @param y    纬线
     * @return
     */
    @Override
    public MachineDistance.MachineDistanceInfo getNearMachine(String name, BigDecimal x, BigDecimal y) {
        Machine machine = homePageDao.getNearMachine(name, x, y);
        //根据用户位置计算机器与用户之间的直线据离
        double distance = Distance.GetDistance(x, y, machine.getX(), machine.getY());
        MachineDistance.MachineDistanceInfo machineDistance = new MachineDistance.MachineDistanceInfo();
        machineDistance.setDistance(distance);
        machineDistance.setMachine(machine);
        return machineDistance;
    }

    /**
     * 根据机器id查询机器上的商品
     *
     * @param machineId 机器id
     * @return
     */
    @Override
    public List<ProductInfo> getProductInfo(long machineId) {
        List<ProductInfo> productInfoList = homePageDao.getProductInfo(machineId);
        return productInfoList;
    }

    /**
     * 查询离我最近的五台机器
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    @Override
    public MachineDistance getNearMachine5(String areaName, BigDecimal x, BigDecimal y) {
        //及其信息集合的实体
        MachineDistance machineDistance = new MachineDistance();
        //机器信息的集合
        List<MachineDistance.MachineDistanceInfo> machineDistanceList = new ArrayList<>();
        //根据用户位置查出来附近的五台机器
        List<Machine> machine = homePageDao.getNearMachine5(areaName, x, y);
        for (int i = 0; i < machine.size(); i++) {
            //分别算出来用户与每台机器的距离
            double distance = Distance.GetDistance(x, y, machine.get(i).getX(), machine.get(i).getY());
            //机器信息的类
            MachineDistance.MachineDistanceInfo machineDistanceInfo = new MachineDistance.MachineDistanceInfo();
            //距离
            machineDistanceInfo.setDistance(distance);
            //一台机器的信息
            machineDistanceInfo.setMachine(machine.get(i));
            machineDistanceList.add(machineDistanceInfo);
        }
        machineDistance.setMachineDistanceInfos(machineDistanceList);
        return machineDistance;
    }

    /**
     * 获取附近五台机器的信息还有机器上的商品信息
     *
     * @param areaName 城市名称
     * @param x        经线
     * @param y        纬线
     * @return
     */
    @Override
    public MachineDistance getNearMachine5ProductInfo(String areaName, BigDecimal x, BigDecimal y) {
        Area area = homePageDao.whetherOpen(areaName);
        Map map = new HashMap();
        MachineDistance machineDistance = new MachineDistance();
        //机器信息的集合
        List<MachineDistance.MachineDistanceInfo> machineDistanceList = new ArrayList<>();
        if (area != null) {
            machineDistance.setReturnState(1);
            //机器信息集合的实体
        } else {
            machineDistance.setReturnState(0);
            //    machineDistance.setMachineDistanceInfos(machineDistanceList);
        }
        //根据用户位置查出来附近的五台机器
        List<Machine> machine = homePageDao.getNearMachine5(areaName, x, y);

        for (int i = 0; i < machine.size(); i++) {
            //分别算出来用户与每台机器的距离
            double distance = Distance.GetDistance(x, y, machine.get(i).getX(), machine.get(i).getY());
            //机器信息的类
            MachineDistance.MachineDistanceInfo machineDistanceInfo = new MachineDistance.MachineDistanceInfo();
            machineDistanceInfo.setDistance(distance);
            machine.get(i).setDistance(distance);
            machineDistanceInfo.setMachine(machine.get(i));
            //机器是否为空
            boolean count = machineIfNull(machine.get(i).getMachineId());
            machine.get(i).setMachineIsNull(count);
            if (!count) {
                machine.get(i).setMachineState(2);
            } else {
                if (!machine.get(i).isInMaintenance()) {
                    machine.get(i).setMachineState(1);
                } else {
                    machine.get(i).setMachineState(0);
                }
            }
            //根据机器id获取机器上面的商品信息放到商品信息的集合
            List<ProductInfo> productInfoList = homePageDao.getProductInfo(machine.get(i).getMachineId());
            //过滤没有商品的机器
            if (productInfoList.size() > 0) {
                //将这台机器的与用户的距离放到商品信息
                List<ProductInfo> productInfos = new ArrayList<>();
                for (int j = 0; j < productInfoList.size(); j++) {
                    if (productInfoList.get(j).getUseableQuantity() <= 0 && !productInfoList.get(j).isActive()) {
                        continue;
                    }
                    String str = productInfoList.get(j).getAttachImageUrls();
                    String[] arr = str.split(";");
                    productInfoList.get(j).setImage60(clientSetting.getResource() + arr[0]);
                    productInfoList.get(j).setImageUrl(clientSetting.getResource() + productInfoList.get(j).getImageUrl());
                    productInfoList.get(j).setAttachImageUrls(attachImageUrls(productInfoList.get(j).getAttachImageUrls()));
                    productInfoList.get(j).setDistance(distance);
                    productInfoList.get(j).setAddress(machine.get(i).getAddress());
                    productInfoList.get(j).setJoinCompanyId(machine.get(i).getJoinCompanyId());
                    productInfoList.get(j).setOnLinePrice(productInfoList.get(j).getOnLinePrice() / 100);
                    productInfoList.get(j).setOffLinePrice(productInfoList.get(j).getOffLinePrice() / 100);
                    productInfos.add(productInfoList.get(j));
                }
                machineDistanceInfo.setProductInfoList(productInfos);
                machineDistanceList.add(machineDistanceInfo);
            }
        }
        machineDistance.setMachineDistanceInfos(machineDistanceList);
        return machineDistance;
    }

    /**
     * 根据机器Id展示附近五台机器
     *
     * @param machineId 机器ID
     * @return com.wufanbao.api.oldclientservice.entity.MachineDistance
     * @author Wang Zhiyuan
     * @date 2018/3/13
     */
    @Override
    public MachineDistance getProductInfoByMachineId(long machineId) {
        Machine machineArea = homePageDao.machineArea(machineId);
        //机器投放城市
        String areaName = machineArea.getAreaName();
        //机器坐标
        BigDecimal x = null;
        BigDecimal y = null;
        try {
            x = machineArea.getX();
            y = machineArea.getY();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断是不是开放城市
        Area area = homePageDao.whetherOpen(areaName);
        Map map = new HashMap();
        MachineDistance machineDistance = new MachineDistance();
        //机器信息的集合
        List<MachineDistance.MachineDistanceInfo> machineDistanceList = new ArrayList<>();
        if (area != null) {
            machineDistance.setReturnState(1);
            //机器信息集合的实体
        } else {
            machineDistance.setReturnState(0);
        }
        MachineDistance.MachineDistanceInfo machineDistanceInfo1 = homePageServiceImpl.machineDistanceInfo(machineArea, x, y);
        machineDistanceList.add(machineDistanceInfo1);
        List<Machine> machine = homePageDao.getNearMachine5(areaName, x, y);
        for (int i = 0; i < machine.size(); i++) {
            //不查询当前机器
            if (machineId != machine.get(i).getMachineId()) {
                //计算机器的数据
                MachineDistance.MachineDistanceInfo machineDistanceInfo = homePageServiceImpl.machineDistanceInfo(machine.get(i), x, y);
                //不传递为空的数据
                if (machineDistanceInfo.getProductInfoList() != null) {
                    machineDistanceList.add(machineDistanceInfo);
                }
            }
        }
        machineDistance.setMachineDistanceInfos(machineDistanceList);
        return machineDistance;
    }

    public MachineDistance.MachineDistanceInfo machineDistanceInfo(Machine machine, BigDecimal x, BigDecimal y) {
        //分别算出来用户与每台机器的距离
        double distance = Distance.GetDistance(x, y, machine.getX(), machine.getY());
        //机器信息的类
        MachineDistance.MachineDistanceInfo machineDistanceInfo = new MachineDistance.MachineDistanceInfo();
        machineDistanceInfo.setDistance(distance);
        machine.setDistance(distance);
        machineDistanceInfo.setMachine(machine);
        //机器是否为空
        boolean count = machineIfNull(machine.getMachineId());
        machine.setMachineIsNull(count);
        if (!count) {
            machine.setMachineState(2);
        } else {
            if (!machine.isInMaintenance()) {
                machine.setMachineState(1);
            } else {
                machine.setMachineState(0);
            }
        }
        //根据机器id获取机器上面的商品信息放到商品信息的集合
        List<ProductInfo> productInfoList = homePageServiceImpl.homePageDao.getProductInfo(machine.getMachineId());
        //过滤没有商品的机器
        if (productInfoList.size() > 0) {
            //将这台机器的与用户的距离放到商品信息
            List<ProductInfo> productInfos = new ArrayList<>();
            for (int j = 0; j < productInfoList.size(); j++) {
                if (productInfoList.get(j).getUseableQuantity() <= 0 && !productInfoList.get(j).isActive()) {
                    continue;
                }
                String str = productInfoList.get(j).getAttachImageUrls();
                String[] arr = str.split(";");
                productInfoList.get(j).setImage60(clientSetting.getResource() + arr[0]);
                productInfoList.get(j).setImageUrl(clientSetting.getResource() + productInfoList.get(j).getImageUrl());
                productInfoList.get(j).setAttachImageUrls(attachImageUrls(productInfoList.get(j).getAttachImageUrls()));
                productInfoList.get(j).setDistance(distance);
                productInfoList.get(j).setAddress(machine.getAddress());
                productInfoList.get(j).setJoinCompanyId(machine.getJoinCompanyId());
                productInfoList.get(j).setOnLinePrice(productInfoList.get(j).getOnLinePrice() / 100);
                productInfoList.get(j).setOffLinePrice(productInfoList.get(j).getOffLinePrice() / 100);
                productInfos.add(productInfoList.get(j));
            }
            machineDistanceInfo.setProductInfoList(productInfos);
        }
        return machineDistanceInfo;
    }

    public String attachImageUrls(String str) {
        String[] arr = str.split(";");
        String urls = "";
        for (int i = 0; i < arr.length; i++) {
            urls = urls + clientSetting.getResource() + arr[i] + ";";
        }
        return urls;
    }

    /**
     * 获取首页广告
     *
     * @return
     */
    @Override
    public Map getAppAd() {
        Map map = new HashMap();
        //获取广告集合
        List<AppAd> appAdList = appAdDao.getAppAd();
        for (int i = 0; i < appAdList.size(); i++) {
            appAdList.get(i).setAdImageUrl(clientSetting.getResource() + appAdList.get(i).getAdImageUrl());
        }
        //判断集合是否为空
        if (appAdList.size() > 0) {
            //广告信息集合不为空
            map.put("type", 1);
            map.put("appAdList", appAdList);
        } else {
            //广告信息为空
            map.put("type", 0);
            map.put("appAdList", appAdList);
        }
        return map;
    }

    /**
     * 根据已开通城市名称查询某台机器上商品信息
     *
     * @param areaName 城市名称
     * @return
     */
    @Override
    public MachineDistance getNearMachineByAddsProductInfo(String areaName) {
        //机器信息集合的实体
        MachineDistance machineDistance = new MachineDistance();
        //根据已开通城市获取一台机器信息
        Machine machine = homePageDao.getNearMachineByAdds(areaName);
        //根据机器id获取机器上面商品的信息
        List<ProductInfo> productInfoList = homePageDao.getProductInfo(machine.getMachineId());
        //机器信息的集合
        List<MachineDistance.MachineDistanceInfo> machineDistanceList = new ArrayList<>();
        MachineDistance.MachineDistanceInfo machineDistanceInfo = new MachineDistance.MachineDistanceInfo();
        for (int j = 0; j < productInfoList.size(); j++) {
            productInfoList.get(j).setAddress(machine.getAddress());
            productInfoList.get(j).setJoinCompanyId(machine.getJoinCompanyId());
            productInfoList.get(j).setOnLinePrice(productInfoList.get(j).getOnLinePrice() / 100);
            productInfoList.get(j).setOffLinePrice(productInfoList.get(j).getOffLinePrice() / 100);
        }
        machineDistanceList.add(machineDistanceInfo);
        machineDistanceInfo.setProductInfoList(productInfoList);
        machineDistanceInfo.setMachine(machine);
        machineDistance.setMachineDistanceInfos(machineDistanceList);
        return machineDistance;
    }

    /**
     * 获取已经开放的城市
     *
     * @return
     */
    @Override
    public Map getOpenCity() {
        List<Area> areaList = homePageDao.getOpenCity();
        Map map = new HashMap();
        if (areaList.size() > 0) {
            map.put("returnState", 1);//有数据
            map.put("areaList", areaList);//已经开放的城市
        } else {
            map.put("returnState", 0);//有数据
            map.put("areaList", areaList);//已经开放的城市
        }
        return map;
    }

    /**
     * 查询机器是否为空
     *
     * @param machineId
     * @return
     */
    @Override
    public boolean machineIfNull(long machineId) {
        int count = homePageDao.machineIfNull(machineId);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 版本控制
     *
     * @return
     */
    @Override
    public ResponseInfo versionCode() {
        Map map = appAdDao.versionCode();
        ResponseInfo responseInfo = new ResponseInfo();
        if (map != null) {
            responseInfo.setCode(1);
            responseInfo.setData(map);
        }
        return responseInfo;
    }

    /**
     * 根据机器投放区域查询机器信息
     *
     * @param areaId 区域Id
     * @param x      经线
     * @param y      纬线
     * @return com.wufanbao.api.oldclientservice.entity.ResponseInfo
     * @author Wang Zhiyuan
     * @date 2018/4/16
     */
    @Override
    public ResponseInfo getMachineByAreaId(int areaId, BigDecimal x, BigDecimal y) {
        List<Map> machineList = homePageDao.getMachineByAreaId(areaId);
        int machineListSize = machineList.size();
        if (machineListSize > 0) {
            for (int i = 0; i < machineListSize; i++) {
                Map map = machineList.get(i);
                boolean inMaintenance = Boolean.valueOf(map.get("inMaintenance").toString());
                int quantity = Integer.parseInt(map.get("USEABLEQUANTITY").toString());
                BigDecimal machineX = BigDecimal.valueOf(Double.parseDouble(map.get("x").toString()));
                BigDecimal machineY = BigDecimal.valueOf(Double.parseDouble(map.get("y").toString()));
                //距离
                String distance = Distance.getDistanceKm(machineX, machineY, x, y);
                map.put("distance", distance);
                String machineType = "正常";
                if (inMaintenance) {
                    machineType = "维护";
                    map.put("machineType", machineType);
                    continue;
                }
                if (quantity == 0) {
                    machineType = "售罄";
                    map.put("machineType", machineType);
                    continue;
                }
                map.put("machineType", machineType);
            }
        }
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setCode(1);
        responseInfo.setData(machineList);
        return responseInfo;
    }
}
