package com.wufanbao.api.oldclientservice.entity;

import java.util.List;

public class UserOrderInfo {
    private int type;//用户可开票信息的状态
    private List<YearInfo> zongYear;//用户所有的年

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<YearInfo> getZongYear() {
        return zongYear;
    }

    public void setZongYear(List<YearInfo> zongYear) {
        this.zongYear = zongYear;
    }

    public static class YearInfo {
        private int yearNumber;//年份
        private List<MonthInfo> year;//一年中所有的月

        public int getYearNumber() {
            return yearNumber;
        }

        public void setYearNumber(int yearNumber) {
            this.yearNumber = yearNumber;
        }

        public List<MonthInfo> getYear() {
            return year;
        }

        public void setYear(List<MonthInfo> year) {
            this.year = year;
        }

        public static class MonthInfo {
            private int monthNumber;//月份
            private List<UserOrder> month;//一个月中所有的可开发票订单

            public int getMonthNumber() {
                return monthNumber;
            }

            public void setMonthNumber(int monthNumber) {
                this.monthNumber = monthNumber;
            }

            public List<UserOrder> getMonth() {
                return month;
            }

            public void setMonth(List<UserOrder> month) {
                this.month = month;
            }
        }
    }
}
