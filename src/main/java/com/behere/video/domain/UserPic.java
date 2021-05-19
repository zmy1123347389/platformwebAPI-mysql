package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class UserPic implements Param {

    private String id;

    private long userId;

    private String picUrl;

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

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}