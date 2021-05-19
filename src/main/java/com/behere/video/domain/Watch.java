package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class Watch implements Param {

    private long fromUser;

    private long toUser;

    private int priceId;

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

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }
}