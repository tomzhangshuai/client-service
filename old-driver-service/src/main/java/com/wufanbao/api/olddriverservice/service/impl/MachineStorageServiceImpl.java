package com.wufanbao.api.olddriverservice.service.impl;

import com.alibaba.fastjson.JSON;
import com.wufanbao.api.olddriverservice.dao.MachineDao;
import com.wufanbao.api.olddriverservice.dao.MachineStorageDao;
import com.wufanbao.api.olddriverservice.entity.*;
import com.wufanbao.api.olddriverservice.service.MachineStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MachineStorageServiceImpl implements MachineStorageService {
    @Autowired
    private MachineStorageDao machineStorageDao;
    @Autowired
    private MachineDao machineDao;

    /**
     * 机器内商品位置
     *
     * @param machineId
     * @return
     */
    @Override
    public Map getGoodsPosition(long machineId) {
        Map map = new HashMap();
        //获取机器内商品的位置
        List<MachineStorage> machineStorageList = machineStorageDao.getMachineStorageInfo(machineId);
        List goodsPositionList = new ArrayList();
        for (int i = 0; i < machineStorageList.size(); i++) {
            if (machineStorageList.get(i).getBoxNum() == 0) {
                goodsPositionList.add(0);
                //machineStorageList.get(i).setProductGlobalId(0);
            } else {
                goodsPositionList.add(machineStorageList.get(i).getProductGlobalId());
            }
        }
        map.put("1", machineStorageList);
        map.put("2", goodsPositionList);
        return map;
    }

    /**
     * 对商品进行全排列
     *
     * @param num
     * @return
     */
    @Override
    public ArrayList<ArrayList<String>> permute(String[] num) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result.add(new ArrayList<String>());
        for (int i = 0; i < num.length; i++) {
            ArrayList<ArrayList<String>> current = new ArrayList<ArrayList<String>>();
            for (ArrayList<String> l : result) {
                for (int j = 0; j < l.size() + 1; j++) {
                    l.add(j, num[i]);
                    ArrayList<String> temp = new ArrayList<String>(l);
                    current.add(temp);
                    l.remove(j);
                }
            }
            result = new ArrayList<ArrayList<String>>(current);
        }
        return result;
    }

    /**
     * 商品id为零时必须后置
     *
     * @return
     */
    public ArrayList<ArrayList<String>> zeroEnd(ArrayList<ArrayList<String>> list) {
        ArrayList<ArrayList<String>> returnList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List everyList = list.get(i);
            int k = 0;
            for (int j = 0; j < everyList.size(); j++) {
                int num = 0;
                int num2 = 0;
                if (j < everyList.size() - 1) {
                    num = Integer.valueOf(everyList.get(j).toString());
                    num2 = Integer.valueOf(everyList.get(j + 1).toString());
                    if (num == 0 && num2 != 0) {
                        k++;
                        break;
                    }
                }

            }
            if (k == 0) {
                returnList.add(list.get(i));
            }
        }
        return returnList;
    }


    /**
     * 最优解
     *
     * @return
     */
    @Override
    public ResponseInfo optimalSolution(long supplementOrderId, long machineId) {
        MachineModel machineModel = machineStorageDao.getMachineModel(machineId);
        int countRow = machineModel.getCountRow();
        int boxNum = machineModel.getBoxNum();
        Map calculateGoodsPositionMap = calculateGoodsPosition(supplementOrderId, machineId, countRow, boxNum);
        String[] productPermuteArray = (String[]) calculateGoodsPositionMap.get("1");
        Map productMap = (Map) calculateGoodsPositionMap.get("2");
        List<GoodsAreArranged> goodsAreArrangedArrayList = new ArrayList<GoodsAreArranged>();
        //下架商品和绑定商品可能出现的排列情况
        List<Arrange> arrangeList = soldOutInfo(machineId);
        //获取全排列的集合
        ArrayList<ArrayList<String>> zeroNoEnd = permute(productPermuteArray);

        ArrayList<ArrayList<String>> permuteList = zeroEnd(zeroNoEnd);

        //遍历数量
        Map mapGoodsPosition = getGoodsPosition(machineId);
        List<MachineStorage> machineStorageLists = (List<MachineStorage>) mapGoodsPosition.get("1");
        for (int i = 0; i < permuteList.size(); i++) {
            //获取一组的排列顺序
            String sb = aGroupArrange(permuteList.get(i));
            //获取商品位置的集合
            List getGoodsPositionList = (List) mapGoodsPosition.get("2");
            //判断全排列的组合是否符合下架商品和绑定商品顺序
            if (judge(arrangeList, sb)) {
                //获取一组的商品数量
                List<GoodsAreArranged.PermuteInfo> list2 = aGroupProductNum(permuteList.get(i), productMap);
                //按照排列顺序和数量生成排列方式
                List list = aGroupWholeArrangement(list2, boxNum);
                // 对比商品计算出同一位置不同商品的数量
                List<GoodsAreArranged.needToMove> needToMoveList = aGroupDifference(list, getGoodsPositionList);//问题所在
                GoodsAreArranged goodsAreArranged = new GoodsAreArranged();
                goodsAreArranged.setProductPermute(sb);
                goodsAreArranged.setPermuteInfoList(list2);
                goodsAreArranged.setPermuteNum(needToMoveList.size());
                goodsAreArranged.setNeedToMoveList(needToMoveList);
                goodsAreArranged.setOptimalLayoutList(list);
                goodsAreArranged.setPracticalLayoutList(getGoodsPositionList);
                goodsAreArrangedArrayList.add(goodsAreArranged);
            }
        }
        //将商品排列的集合按照最小不同位置的值来排列
        GoodsAreArranged goodsAreArranged = ascendingOrder(goodsAreArrangedArrayList);
        ResponseInfo responseInfo = new ResponseInfo();
        Map mm = new HashMap();
        if (goodsAreArranged != null) {
            responseInfo.setCode(1);
            //mm.put("returnState",1);
            responseInfo.setData(mm);
            mm.put("needToMoveList", goodsAreArranged.getNeedToMoveList());//需要挪动的商品
            mm.put("machineStorageList", machineStorageLists);//仓库内实际的排列

        } else {
            //mm.put("returnState",0);
            responseInfo.setCode(2101);
            mm.put("needToMoveList", " ");//需要挪动的商品
            mm.put("machineStorageList", " ");//仓库内实际的排列
            responseInfo.setData(mm);
        }
        return responseInfo;
    }

    /**
     * 判断全排列的组合是否符合下架商品和绑定商品顺序
     *
     * @param arrangeList
     * @param sb
     * @return
     */
    @Override
    public boolean judge(List<Arrange> arrangeList, String sb) {
        int x = 0;
        for (int i = 0; i < arrangeList.size(); i++) {
            //判断全排列的组合是否符合下架和被绑定的排列顺序
            if (sb.contains(arrangeList.get(i).getArrangeOne()) || sb.contains(arrangeList.get(i).getArrangeTwo())) {
            } else {
                //不满足的话 x 自增
                x++;
            }
        }
        //x大于0时返回false
        if (x > 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 下架商品和绑定商品可能出现的排列情况
     *
     * @param machineId 补货单机器id
     * @return
     */
    @Override
    public List<Arrange> soldOutInfo(long machineId) {
        //查询下架商品和绑定商品
        List<Product> productList = machineStorageDao.soldOutInfo(machineId);
        //所有下架商品和被绑定商品的排列情况集合
        List<Arrange> arrangeList = new ArrayList<Arrange>();
        //通过循环将下架商品和被绑定商品可能出现的排列到结合中去
        for (int i = 0; i < productList.size(); i++) {
            StringBuilder sb1 = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            Arrange arrange = new Arrange();
            //被绑定商品在前
            String arrangeOne = sb1.append(productList.get(i).getProductGlobalId() + "," + productList.get(i).getOffProductGlobalId()).toString();
            //下架商品在前
            String arrangeTwo = sb2.append(productList.get(i).getOffProductGlobalId() + "," + productList.get(i).getProductGlobalId()).toString();
            arrange.setArrangeOne(arrangeOne);
            arrange.setArrangeTwo(arrangeTwo);
            arrangeList.add(arrange);
        }
        return arrangeList;
    }

    /**
     * 一组商品的排列
     *
     * @param list
     * @return
     */
    @Override
    public String aGroupArrange(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + ",");
        }
        return sb.toString();
    }

    /**
     * 一组商品的数量
     *
     * @param list
     * @param map
     * @return
     */
    @Override
    public List aGroupProductNum(List<String> list, Map map) {
        List<GoodsAreArranged.PermuteInfo> permuteInfoList = new ArrayList<GoodsAreArranged.PermuteInfo>();
        for (int i = 0; i < list.size(); i++) {
            map.get(list.get(i));
            //存放商品信息
            GoodsAreArranged.PermuteInfo permuteInfo = new GoodsAreArranged.PermuteInfo();
            permuteInfo.setProductGlobalId(Long.parseLong(list.get(i)));
            permuteInfo.setProductNum((int) map.get(list.get(i)));
            permuteInfoList.add(permuteInfo);
        }
        return permuteInfoList;
    }

    /**
     * 对比商品计算出同一位置不同商品的数量
     *
     * @param list
     * @return
     */
    @Override
    public List<GoodsAreArranged.needToMove> aGroupDifference(List list, List getGoodsPositionList) {
        List<GoodsAreArranged.needToMove> needToMoveList = new ArrayList<GoodsAreArranged.needToMove>();
        //问题所在
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(getGoodsPositionList.get(i)) || getGoodsPositionList.get(i).equals(0)) {
            } else {
                GoodsAreArranged.needToMove needToMove = new GoodsAreArranged.needToMove();
                needToMove.setProductGlobalId(Long.parseLong(getGoodsPositionList.get(i).toString()));
                needToMove.setTurn(i + 1);
                needToMoveList.add(needToMove);

            }
        }
        return needToMoveList;
    }

    /**
     * 按照商品挪动最小次数升序排列
     *
     * @param list
     * @return
     */
    public GoodsAreArranged ascendingOrder(List<GoodsAreArranged> list) {
        GoodsAreArranged goodsAreArranged = new GoodsAreArranged();
        int x = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getPermuteNum() <= list.get(x).getPermuteNum()) {
                goodsAreArranged = list.get(i);
                x = i;
            }
        }
        System.out.println(goodsAreArranged.getPermuteInfoList() + "list list");
        return goodsAreArranged;
    }

    /**
     * 根据补货单和机器内商品数量计算商品在机器内应占用的数量
     *
     * @param supplementOrderId
     * @param machineId
     * @return
     */
    @Override
    public Map calculateGoodsPosition(long supplementOrderId, long machineId, int countRow, int boxNum) {
        int countBox = countRow * boxNum;
        Map map = new HashMap();
        List<Product> productList = machineStorageDao.getProductInfo(machineId);
        for (int i = 0; i < productList.size(); i++) {
            map.put(productList.get(i).getProductGlobalId(), productList.get(i).getQuantity());
        }
        //获取补货单的详细信息
        List<SupplementOrderLine> supplementOrderLineList = machineStorageDao.getSupplementOrderInfo(supplementOrderId);
        //补货单所有商品的期望值
        List productPermuteList = new ArrayList();
        //将机器内所有的商品一次放到集合中
        Map productMap = new HashMap();
        int x = 0;
        for (int i = 0; i < supplementOrderLineList.size(); i++) {
            //补货单的商品是否绑定了下架商品
            if (supplementOrderLineList.get(i).getOffProductGlobalId() > 0 && map.get(supplementOrderLineList.get(i).getOffProductGlobalId()) != null) {
                //被下架的商品数量
                int offNum = Integer.parseInt(map.get(supplementOrderLineList.get(i).getOffProductGlobalId()).toString());
                //所占的阁子
                int productNum = offNum % boxNum == 0 ? offNum / boxNum : offNum / boxNum + 1;
                //需要补货的商品最大期望值
                int pronum = supplementOrderLineList.get(i).getExpectQuantity() / boxNum;
                //实际可以放的商品数量
                int count = (pronum - productNum) * boxNum;
                long prid = supplementOrderLineList.get(i).getProductGlobalId();
                //需要补货的商品
                productMap.put(String.valueOf(supplementOrderLineList.get(i).getProductGlobalId()), count);
                productPermuteList.add(String.valueOf(supplementOrderLineList.get(i).getProductGlobalId()));
                //下架的商品
                productMap.put(String.valueOf(supplementOrderLineList.get(i).getOffProductGlobalId()), offNum);
                productPermuteList.add(String.valueOf(supplementOrderLineList.get(i).getOffProductGlobalId()));
            } else {
                productMap.put(String.valueOf(supplementOrderLineList.get(i).getProductGlobalId()), supplementOrderLineList.get(i).getExpectQuantity());
                productPermuteList.add(String.valueOf(supplementOrderLineList.get(i).getProductGlobalId()));
            }
            int expectQuantity = supplementOrderLineList.get(i).getExpectQuantity();//最大期望數量
            int expectQuantityNum = expectQuantity % boxNum == 0 ? expectQuantity / boxNum : expectQuantity / boxNum + 1;//最大期望數佔的格子
            x = x + expectQuantityNum * boxNum;
        }
        if (x < countBox) {
            productMap.put(String.valueOf(0), countBox - x);
            productPermuteList.add(String.valueOf(0));
        }
        //商品排列的的数组
        String[] productPermuteArray = new String[productPermuteList.size()];
        productPermuteList.toArray(productPermuteArray);
        Map returnMap = new HashMap();
        returnMap.put("1", productPermuteArray);
        returnMap.put("2", productMap);
        return returnMap;
    }

    /**
     * 计算上架数量
     *
     * @param list
     * @return
     */
    @Override
    public Map countPutawayNum(List<GoodsAreArranged.PermuteInfo> list) {
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        for (GoodsAreArranged.PermuteInfo w : list) {
            if (map.get(w.getProductGlobalId()) == null) {
                map.put(w.getProductGlobalId(), w.getProductNum());
            } else {
                map.put(w.getProductGlobalId(), w.getProductNum() + map.get(w.getProductGlobalId()));
            }
        }
        return map;
    }

    /**
     * 重新调整机器的位置
     *
     * @param machineStorageList
     * @return
     */
    @Override
    public ResponseInfo arrangeGoods(String machineStorageList) {
        ResponseInfo responseInfo = new ResponseInfo();
        Map map = new HashMap();
        List<MachineStorage> machineStorageLists = JSON.parseArray(machineStorageList, MachineStorage.class);
        long machineId = machineStorageLists.get(0).getMachineId();
        int countRow = machineStorageDao.getMachineModel(machineId).getCountRow();
        int x = 0;
        for (int i = 0; i < machineStorageLists.size(); i++) {
            int count = machineStorageDao.arrangeGoods(machineStorageLists.get(i));
            if (count > 0) {
                x++;
            }
        }
        List<Product> productList = machineStorageDao.getMachineProduct(machineId);
        int quantity = 0;
        int lock = 0;
        int useableQuantity = 0;
        for (int i = 0; i < productList.size(); i++) {
            Product product = machineStorageDao.getProduct(machineId, productList.get(i).getProductGlobalId());
            int useable = productList.get(i).getQuantity() - product.getLockQuantity();
            machineStorageDao.updateProduct(machineId, productList.get(i).getProductGlobalId(), productList.get(i).getQuantity(), useable);
            quantity = quantity + productList.get(i).getQuantity();
            lock = lock + product.getLockQuantity();
            useableQuantity = useableQuantity + useable;
        }
        System.out.println(quantity + " " + lock + "   " + useableQuantity + "  " + machineId);
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        machine.setQuantity(quantity);
        machine.setLockQuantity(lock);
        machine.setUseableQuantity(useableQuantity);
        System.out.println(machine.getMachineId() + " " + machine.getQuantity());
        int count = machineDao.updateMachine(machine);
        machine.getMachineId();

        if (x == countRow) {
            responseInfo.setCode(1);

        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            responseInfo.setCode(2101);
        }
        return responseInfo;
    }

    /**
     * 获取机器的列数
     *
     * @param machineId
     * @return
     */
    public int getRow(long machineId) {
        int bigPosition = machineStorageDao.getBigPosition(machineId);
        int row = 0;
        if (bigPosition > 33 & bigPosition < 65) {
            row = 1;
        }
        if (bigPosition >= 65 & bigPosition < 97) {
            row = 2;
        }
        if (bigPosition >= 97 & bigPosition < 129) {
            row = 3;
        }
        if (bigPosition >= 129 & bigPosition < 161) {
            row = 4;
        }
        if (bigPosition >= 161) {
            row = 5;
        }
        return row;
    }

    /**
     * 理想的填仓图
     *
     * @param supplementOrderId
     * @param machineId
     * @return
     */
    @Override
    public ResponseInfo idealDrawing(long supplementOrderId, long machineId) {
        MachineModel machineModel = machineStorageDao.getMachineModel(machineId);
        int countRow = machineModel.getCountRow();
        int boxNum = machineModel.getBoxNum();
        //根据补货单和机器内商品数量计算商品在机器内应占用的数量
        Map calculateGoodsPositionMap = calculateGoodsPosition(supplementOrderId, machineId, countRow, boxNum);
        String[] productPermuteArray = (String[]) calculateGoodsPositionMap.get("1");
        Map productMap = (Map) calculateGoodsPositionMap.get("2");
        //全部排列的集合
        List<GoodsAreArranged> goodsAreArrangedArrayList = new ArrayList<GoodsAreArranged>();
        //下架商品和绑定商品可能出现的排列情况
        List<Arrange> arrangeList = soldOutInfo(machineId);
        //获取全排列的集合
        ArrayList<ArrayList<String>> zeroNoEnd = permute(productPermuteArray);

        ArrayList<ArrayList<String>> permuteList = zeroEnd(zeroNoEnd);
        //遍历数量
        Map mapGoodsPosition = getGoodsPosition(machineId);
        List<MachineStorage> machineStorageList = (List<MachineStorage>) mapGoodsPosition.get("1");
        for (int i = 0; i < permuteList.size(); i++) {
            //获取一组的排列顺序
            String sb = aGroupArrange(permuteList.get(i));
            //获取商品位置的集合
            List getGoodsPositionList = (List) mapGoodsPosition.get("2");
            //判断全排列的组合是否符合下架商品和绑定商品顺序
            if (judge(arrangeList, sb)) {
                //获取一组的商品数量
                List<GoodsAreArranged.PermuteInfo> list2 = aGroupProductNum(permuteList.get(i), productMap);
                //按照排列顺序和数量生成排列方式
                List list = aGroupWholeArrangement(list2, boxNum);
                // 对比商品计算出同一位置不同商品的数量
                List<GoodsAreArranged.needToMove> needToMoveList = aGroupDifference(list, getGoodsPositionList);
                GoodsAreArranged goodsAreArranged = new GoodsAreArranged();
                goodsAreArranged.setProductPermute(sb);
                goodsAreArranged.setPermuteInfoList(list2);
                goodsAreArranged.setPermuteNum(needToMoveList.size());
                goodsAreArranged.setNeedToMoveList(needToMoveList);
                goodsAreArranged.setOptimalLayoutList(list);
                goodsAreArranged.setPracticalLayoutList(getGoodsPositionList);
                goodsAreArrangedArrayList.add(goodsAreArranged);
            }
        }
        //将商品排列的集合按照最小不同位置的值来排列
        GoodsAreArranged goodsAreArranged = ascendingOrder(goodsAreArrangedArrayList);
        //接受下货数量转换成map
        List<GoodsAreArranged.PermuteInfo> pgList = goodsAreArranged.getPermuteInfoList();
        Map mapNum = new HashMap();
        List<SupplementOrderLine> supplementOrderLineList = machineStorageDao.dumpQuantity(supplementOrderId);
        for (int i = 0; i < supplementOrderLineList.size(); i++) {
            // String ss=String.valueOf(supplementOrderLineList.get(i).getProductGlobalId());
            mapNum.put(String.valueOf(supplementOrderLineList.get(i).getProductGlobalId()), String.valueOf(supplementOrderLineList.get(i).getDumpQuantity()));
        }
        //Map<Object, Object> mapNum = JSONObject.parseObject(mapNum1, new TypeReference<Map<Object, Object>>(){});
        List<Product> productList = machineStorageDao.getProductInfo(machineId);
        for (int i = 0; i < productList.size(); i++) {
            long p = productList.get(i).getProductGlobalId();//商品id
            int t = productList.get(i).getQuantity();//仓库内实际商品
            if (mapNum.get(String.valueOf(p)) != null) {
                int x = Integer.parseInt(mapNum.get(String.valueOf(p)).toString());
                int dd = x + t;
                mapNum.put(String.valueOf(p), x + t);
            } else {
                mapNum.put(String.valueOf(p), t);
            }
        }
        List<MachineStorage> machineStorageLists = new ArrayList<>();
        for (int d = 0; d < pgList.size(); d++) {
            long getProductGlobalId = pgList.get(d).getProductGlobalId();//排列号的商品id
            // int pg=pgList.get(d).getProductNum();
            int bigNum = pgList.get(d).getProductNum();//能补的最大数量
            int productBigNum = bigNum % boxNum == 0 ? bigNum / boxNum : bigNum / boxNum + 1;//最大能补的数量取余数
            int practicalNum = 0;
            if (getProductGlobalId == 0) {
                practicalNum = 0;
            } else {
                practicalNum = Integer.parseInt(mapNum.get(String.valueOf(getProductGlobalId)).toString());//实际能补的数量
            }
            int productPracticalNum = practicalNum % boxNum == 0 ? practicalNum / boxNum : practicalNum / boxNum + 1;//实际能补的数量取余数
            int practicalRemainder = practicalNum % boxNum;//实际的余数
            for (int j = 0; j < productBigNum; j++) {
                MachineStorage machineStorage = new MachineStorage();
                if (j < productPracticalNum - 1) {
                    machineStorage.setProductGlobalId(getProductGlobalId);
                    machineStorage.setBoxNum(boxNum);
                    machineStorageLists.add(machineStorage);
                }
                if (j == productPracticalNum - 1) {
                    if (practicalRemainder == 0) {
                        machineStorage.setProductGlobalId(getProductGlobalId);
                        machineStorage.setBoxNum(boxNum);
                        machineStorageLists.add(machineStorage);
                    } else {
                        machineStorage.setProductGlobalId(getProductGlobalId);
                        machineStorage.setBoxNum(practicalRemainder);
                        machineStorageLists.add(machineStorage);
                    }
                }
                if (j > productPracticalNum - 1) {
                    machineStorage.setProductGlobalId(getProductGlobalId);
                    machineStorage.setBoxNum(0);
                    machineStorageLists.add(machineStorage);
                }
            }
        }
        for (int i = 0; i < machineStorageLists.size(); i++) {
            machineStorageLists.get(i).setMachineId(machineStorageList.get(i).getMachineId());
            machineStorageLists.get(i).setPosition(machineStorageList.get(i).getPosition());
            machineStorageLists.get(i).setTurn(machineStorageList.get(i).getTurn());

        }
        Map dataMap = new HashMap();
        ResponseInfo responseInfo = new ResponseInfo();
        if (machineStorageLists.size() > 0) {
            responseInfo.setCode(1);

            responseInfo.setData(machineStorageLists);
        } else {
            responseInfo.setCode(2091);
            responseInfo.setData(machineStorageLists);
        }
        return responseInfo;
    }

    /**
     * 插入机器位置表id
     *
     * @param machineId
     * @return
     */
    @Override
    public Map insertGoods(long machineId) {
        Map map = new HashMap();
        List<MachineStorage> machineStorageList = machineStorageDao.getMachineStorageInfo(194639822849l);
        int x = 0;
        for (int i = 0; i < machineStorageList.size(); i++) {
            machineStorageList.get(i).setMachineId(machineId);
            int count = machineStorageDao.insertGoods(machineStorageList.get(i));
            if (count > 0) {
                x++;
            }
        }
        if (x == 120) {
            map.put("returnState", 1);
        } else {
            map.put("returnState", 0);
        }

        return map;
    }

    /**
     * 根据机器id获取机器型号
     *
     * @param machineId
     * @return
     */
    @Override
    public MachineModel getMachineModel(long machineId) {
        return machineStorageDao.getMachineModel(machineId);
    }

    /**
     * 机器表格
     *
     * @param machineId
     * @return
     */
    @Override
    public ResponseInfo machineTable(long machineId) {
        MachineModel machineModel = machineStorageDao.getMachineModel(machineId);
        int countRow = machineModel.getCountRow();
        int row = getRow(machineId);
        int line = countRow / row;
        machineModel.setRow(row);
        machineModel.setLine(line);
        ResponseInfo responseInfo = new ResponseInfo();
        if (machineModel != null) {
            responseInfo.setCode(1);
            responseInfo.setData(machineModel);
        } else {
            responseInfo.setCode(2351);
            responseInfo.setData(machineModel);
        }
        return responseInfo;
    }

    /**
     * 一组商品的全排列
     *
     * @param list
     * @return
     */
    @Override
    public List aGroupWholeArrangement(List<GoodsAreArranged.PermuteInfo> list, int boxNum) {
        List aGroupWholeArrangementList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Long productGlobalId = list.get(i).getProductGlobalId();
            int productNum = list.get(i).getProductNum() % boxNum == 0 ? list.get(i).getProductNum() / boxNum : list.get(i).getProductNum() / boxNum + 1;
            for (int j = 0; j < productNum; j++) {
                aGroupWholeArrangementList.add(productGlobalId);
            }
        }
        return aGroupWholeArrangementList;
    }
}
