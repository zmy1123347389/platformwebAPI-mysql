package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class Disturb implements Param {

    private long userId;

    private int status;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}