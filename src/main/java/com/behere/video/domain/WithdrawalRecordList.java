package com.behere.video.domain;

import java.util.Date;

/**
 * @author: Behere
 */
public class WithdrawalRecordList {

    private int status;
    private double money;
    private Date createTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}