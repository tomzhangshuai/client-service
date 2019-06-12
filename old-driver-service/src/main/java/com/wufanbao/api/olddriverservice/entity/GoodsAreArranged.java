package com.wufanbao.api.olddriverservice.entity;

import java.util.List;

public class GoodsAreArranged {
    private String ProductPermute;
    private List<PermuteInfo> permuteInfoList;
    private int permuteNum;//
    private List<needToMove> needToMoveList;//商品位置集合
    private List optimalLayoutList;//最优布局
    private List practicalLayoutList;//实际布局

    public List getOptimalLayoutList() {
        return optimalLayoutList;
    }

    public void setOptimalLayoutList(List optimalLayoutList) {
        this.optimalLayoutList = optimalLayoutList;
    }

    public List getPracticalLayoutList() {
        return practicalLayoutList;
    }

    public void setPracticalLayoutList(List practicalLayoutList) {
        this.practicalLayoutList = practicalLayoutList;
    }

    public List<needToMove> getNeedToMoveList() {
        return needToMoveList;
    }

    public void setNeedToMoveList(List<needToMove> needToMoveList) {
        this.needToMoveList = needToMoveList;
    }

    public String getProductPermute() {
        return ProductPermute;
    }

    public void setProductPermute(String productPermute) {
        ProductPermute = productPermute;
    }

    public List<PermuteInfo> getPermuteInfoList() {
        return permuteInfoList;
    }

    public void setPermuteInfoList(List<PermuteInfo> permuteInfoList) {
        this.permuteInfoList = permuteInfoList;
    }

    public int getPermuteNum() {
        return permuteNum;
    }

    public void setPermuteNum(int permuteNum) {
        this.permuteNum = permuteNum;
    }

    public static class PermuteInfo {
        private long productGlobalId;//补货单商品id
        private int productNum;//补货单商品数据量

        public long getProductGlobalId() {
            return productGlobalId;
        }

        public void setProductGlobalId(long productGlobalId) {
            this.productGlobalId = productGlobalId;
        }

        public int getProductNum() {
            return productNum;
        }

        public void setProductNum(int productNum) {
            this.productNum = productNum;
        }
    }

    public static class needToMove {
        private long productGlobalId;//商品id
        private int turn;//商品位置

        public long getProductGlobalId() {
            return productGlobalId;
        }

        public void setProductGlobalId(long productGlobalId) {
            this.productGlobalId = productGlobalId;
        }

        public int getTurn() {
            return turn;
        }

        public void setTurn(int turn) {
            this.turn = turn;
        }
    }
}
