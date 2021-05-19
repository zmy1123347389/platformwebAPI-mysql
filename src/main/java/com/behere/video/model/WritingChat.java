package com.behere.video.model;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class WritingChat implements Param {

    private long callerId;

    private long calledId;

    private long num;

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

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }
}