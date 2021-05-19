package com.behere.video.domain;

import java.util.Date;

/**
 * @author: Behere
 */
public class AccountBook {

    private String id;

    private String businessId;

    private long fromUser;

    private long toUser;

    private int type;

    private String nickName;

    private long flower;

    private String title;

    private int status;
    private long reduceFlower;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public long getFromUser() {
        return fromUser;
    }

    public void setFromUser(long fromUser) {
        this.fromUser = fromUser;
    }

    public long getToUser() {
        return toUser;
    }

    public void setToUser(long toUser) {
        this.toUser = toUser;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getFlower() {
        return flower;
    }

    public void setFlower(long flower) {
        this.flower = flower;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getReduceFlower() {
        return reduceFlower;
    }

    public void setReduceFlower(long reduceFlower) {
        this.reduceFlower = reduceFlower;
    }
}