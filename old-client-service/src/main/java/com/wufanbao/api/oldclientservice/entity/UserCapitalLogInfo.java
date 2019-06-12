package com.wufanbao.api.oldclientservice.entity;

import java.math.BigDecimal;
import java.util.List;
//这个用户的所有的记录

/**
 * User:WangZhiyuan
 * Date:2017-08-5
 */
public class UserCapitalLogInfo {
    private int type;//查询是否有消费记录
    private BigDecimal usableBalance;
    private List<YearInfo> zongYear;//用户所有年份的记录

    public BigDecimal getUsableBalance() {
        return usableBalance;
    }

    public void setUsableBalance(BigDecimal usableBalance) {
        this.usableBalance = usableBalance;
    }

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

    //一年中所有的记录
    public static class YearInfo {
        private int yearNumber;//年份
        private List<MonthInfo> year;//一年的所有记录

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

        //一月中所有记录
        public static class MonthInfo {
            private int monthNumber;//月份
            private List<UserCapitalLog> month;//一月的所有记录

            public int getMonthNumber() {
                return monthNumber;
            }

            public void setMonthNumber(int monthNumber) {
                this.monthNumber = monthNumber;
            }

            public List<UserCapitalLog> getMonth() {
                return month;
            }

            public void setMonth(List<UserCapitalLog> month) {
                this.month = month;
            }
        }
    }
}
