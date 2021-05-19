package com.behere.video.domain;

import java.util.Date;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class UserLabel implements Param {
    private String id;

    private long userId;

    private long criticsId;

    private int score;

    private String coment;

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

    public long getCriticsId() {
        return criticsId;
    }

    public void setCriticsId(long criticsId) {
        this.criticsId = criticsId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}