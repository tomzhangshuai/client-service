package com.wufanbao.api.distributionservice.dao;

import com.wufanbao.api.distributionservice.entities.DistributionProductLine;
import com.wufanbao.api.distributionservice.transfer.DistributionProductLineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistributionProductLineDao {

    /**
     * 获取配送单商品明细
     * @param distributionOrderId
     * @return
     */
    public List<DistributionProductLine> getOrderProductLines(
            @Param("distributionOrderId") long distributionOrderId);

    /**
     * 增加在途剩余库存,机器返回商品
     * @param distributionOrderId 配送单ID
     * @param productGlobalId 商品id
     * @param actualQuantity 上架数
     * @param backQuantity 可用退回数
     * @param exceptionQuantity 异常数
     * @return
     */
    public int shelve(
            @Param("distributionOrderId") long distributionOrderId,
            @Param("productGlobalId") long productGlobalId,
            @Param("actualQuantity") long actualQuantity,
            @Param("backQuantity") long backQuantity,
            @Param("exceptionQuantity") long exceptionQuantity);

    /**
     * 下货
     * @param distributionOrderId
     * @param productGlobalId
     * @param dumpQuantity
     * @param extraQuantity
     * @return
     */
    public int dump(
            @Param("distributionOrderId") long distributionOrderId,
            @Param("productGlobalId") long productGlobalId,
            @Param("dumpQuantity") long dumpQuantity,
            @Param("extraQuantity") long extraQuantity);

    /**
     * 获取配送数量
     * @param distributionOrderId 配送ID
     * @return
     */
    public DistributionProductLine getDistributingQuantity(
            @Param("distributionOrderId") long distributionOrderId);


    /**
     * 获取返仓确认数据
     * @param distributionOrderId
     * @return
     */
    public List<DistributionProductLineInfo> getConfirmData(
            @Param("distributionOrderId") long distributionOrderId);



    /**
     * 返仓确认时，修改异常数量及剩余数量
     * @param line
     * @return
     */
    public int modifyByConfirmDistribution(DistributionProductLine line);
}
