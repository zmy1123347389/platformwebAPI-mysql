package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class Unlock implements Param {

    private String id;

    private long lockUserId;

    private long unlockUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getLockUserId() {
        return lockUserId;
    }

    public void setLockUserId(long lockUserId) {
        this.lockUserId = lockUserId;
    }

    public long getUnlockUserId() {
        return unlockUserId;
    }

    public void setUnlockUserId(long unlockUserId) {
        this.unlockUserId = unlockUserId;
    }
}