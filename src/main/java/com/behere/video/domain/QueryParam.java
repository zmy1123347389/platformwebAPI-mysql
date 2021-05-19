package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class QueryParam implements Param {

    private String idOrNickName;

    private long userId;

    private int gender;

    private int auth;

    public String getIdOrNickName() {
        return idOrNickName;
    }

    public void setIdOrNickName(String idOrNickName) {
        this.idOrNickName = idOrNickName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}