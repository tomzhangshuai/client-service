package com.wufanbao.api.olddriverservice.service;

import com.wufanbao.api.olddriverservice.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface MachineStorageService {
    /**
     * 商品位置
     *
     * @return
     */
    Map getGoodsPosition(long machineId);

    /**
     * 对商品进行全排列
     *
     * @param num
     * @return
     */
    ArrayList<ArrayList<String>> permute(String[] num);

    /**
     * 最优解
     *
     * @param supplementOrderId
     * @param machineId
     * @return
     */
    ResponseInfo optimalSolution(long supplementOrderId, long machineId);

    /**
     * 判断下架商品是否按照规定顺序排列
     *
     * @param arrangeList
     * @param sb
     * @return
     */
    boolean judge(List<Arrange> arrangeList, String sb);

    /**
     * @param machineId
     * @return
     */
    List<Arrange> soldOutInfo(long machineId);

    /**
     * 一组商品的排列
     *
     * @param list
     * @return
     */
    String aGroupArrange(List<String> list);

    /**
     * 一组商品的数量
     *
     * @param list
     * @return
     */
    List aGroupProductNum(List<String> list, Map map);

    /**
     * 一组商品的全排列
     *
     * @param list
     * @return
     */
    List aGroupWholeArrangement(List<GoodsAreArranged.PermuteInfo> list, int boxNum);

    /**
     * 对比商品计算出同一位置不同商品的数量
     *
     * @param list
     * @return
     */
    List<GoodsAreArranged.needToMove> aGroupDifference(List list, List getGoodsPositionList);

    /**
     * 根据补货单和机器内商品数量计算商品在机器内应占用的数量
     *
     * @param supplementOrderId
     * @param machineId
     * @return
     */
    Map calculateGoodsPosition(long supplementOrderId, long machineId, int countRow, int boxNum);//,int countRow,int boxNum

    /**
     * 计算上架数量
     *
     * @param list
     * @return
     */
    Map countPutawayNum(List<GoodsAreArranged.PermuteInfo> list);

    /**
     * 重新调整机器的位置
     *
     * @param machineStorageList
     * @return
     */
    ResponseInfo arrangeGoods(String machineStorageList);

    /**
     * 理想的填仓图
     *
     * @param supplementOrderId
     * @param machineId
     * @return
     */
    ResponseInfo idealDrawing(long supplementOrderId, long machineId);

    /**
     * 插入机器位置表id
     *
     * @param machineId
     * @return
     */
    Map insertGoods(long machineId);

    /**
     * 根据机器id获取机器型号
     *
     * @param machineId
     * @return
     */
    MachineModel getMachineModel(long machineId);

    /**
     * 机器表格
     *
     * @param machineId
     * @return
     */
    ResponseInfo machineTable(long machineId);
}
