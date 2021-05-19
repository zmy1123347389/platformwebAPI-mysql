package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class UserFlower implements Param {

    private long userId;

    private double totalFlower;

    private double flower;

    private double money;

    private String createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public double getTotalFlower() {
        return totalFlower;
    }

    public void setTotalFlower(double totalFlower) {
        this.totalFlower = totalFlower;
    }

    public double getFlower() {
        return flower;
    }

    public void setFlower(double flower) {
        this.flower = flower;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}