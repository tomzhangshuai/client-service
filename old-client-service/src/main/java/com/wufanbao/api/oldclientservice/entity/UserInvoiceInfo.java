package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserInvoiceInfo {
    private int type;
    private List<XUserInfo> xUserInfos;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<XUserInfo> getxUserInfos() {
        return xUserInfos;
    }

    public void setxUserInfos(List<XUserInfo> xUserInfos) {
        this.xUserInfos = xUserInfos;
    }

    public static class XUserInfo {
        private UserInvoice userInvoice;
        private int orderRow;
        private List<UserOrder> OrderInfo;

        public List<UserOrder> getOrderInfo() {
            return OrderInfo;
        }

        public void setOrderInfo(List<UserOrder> orderInfo) {
            OrderInfo = orderInfo;
        }

        public UserInvoice getUserInvoice() {
            return userInvoice;
        }

        public void setUserInvoice(UserInvoice userInvoice) {
            this.userInvoice = userInvoice;
        }

        public int getOrderRow() {
            return orderRow;
        }

        public void setOrderRow(int orderRow) {
            this.orderRow = orderRow;
        }
    }
}
