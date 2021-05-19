package com.behere.video.domain;

import java.io.Serializable;
import java.util.Date;

import com.behere.common.utils.Param;

/**
 * 守护记录
 * @author: Behere
 */
public class WatchRecord implements Param {

    private String id;

    /** 守护人 */
    private long fromUser;

    /** 被守护人 */
    private long toUser;

    private long flower;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}