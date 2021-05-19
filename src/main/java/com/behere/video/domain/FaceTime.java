package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class FaceTime implements Param {

    private String businessId;
    private long callerId;
    private long calledId;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public long getCallerId() {
        return callerId;
    }

    public void setCallerId(long callerId) {
        this.callerId = callerId;
    }

    public long getCalledId() {
        return calledId;
    }

    public void setCalledId(long calledId) {
        this.calledId = calledId;
    }
}