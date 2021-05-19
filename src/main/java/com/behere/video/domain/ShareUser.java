package com.behere.video.domain;

import java.util.Date;

/**
 * @author: Behere
 */
public class ShareUser {

    private long sharedUserId;

    private String nickName;

    private double contributionValue;

    private Date createTime;

    public long getSharedUserId() {
        return sharedUserId;
    }

    public void setSharedUserId(long sharedUserId) {
        this.sharedUserId = sharedUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public double getContributionValue() {
        return contributionValue;
    }

    public void setContributionValue(double contributionValue) {
        this.contributionValue = contributionValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}