package com.behere.video.model;

import java.util.Date;

/**
 * @author: Behere
 */
public class InvitationRechargeRecord {

    private long userId;

    private int balance;

    private String headPortrait;

    private String nickName;

    private int giveBalance;

    private Date createTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGiveBalance() {
        return giveBalance;
    }

    public void setGiveBalance(int giveBalance) {
        this.giveBalance = giveBalance;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}