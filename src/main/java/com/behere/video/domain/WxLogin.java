package com.behere.video.domain;

import com.behere.common.utils.Param;

/**
 * @author: Behere
 */
public class WxLogin implements Param {

    private long id;

    private String unionId;

    private String nickName;

    private String headimgurl;

    private String myInvitationCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getMyInvitationCode() {
        return myInvitationCode;
    }

    public void setMyInvitationCode(String myInvitationCode) {
        this.myInvitationCode = myInvitationCode;
    }
}