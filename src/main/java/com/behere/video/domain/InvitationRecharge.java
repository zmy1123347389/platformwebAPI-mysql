package com.behere.video.domain;

import java.util.Date;

/**
 * @author: Behere
 */
public class InvitationRecharge {

    private String id;

    private long userId;

    private int balance;

    private int giveBalance;

    private long invitationUser;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getGiveBalance() {
        return giveBalance;
    }

    public void setGiveBalance(int giveBalance) {
        this.giveBalance = giveBalance;
    }

    public long getInvitationUser() {
        return invitationUser;
    }

    public void setInvitationUser(long invitationUser) {
        this.invitationUser = invitationUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}