package com.behere.video.model;

import java.util.Date;

import com.behere.common.constant.Constant;

/**
 * @author: Behere
 */
public class VideoModel {

    private String id;
    private int likeNumber;
    private int commentNumber;
    private String title;
    private String image;
    private long userId;
    private int secret;
    private Date createTime;
    private String nickName;
    private String headPortrait;
    private long flower;
    private String url;
    private String whenTime;
    private long transmit;
    private String shareUrl;
    private int duration;
    private long harvest;
    private int isWatched;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getSecret() {
        return secret;
    }

    public void setSecret(int secret) {
        this.secret = secret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public long getFlower() {
        return flower;
    }

    public void setFlower(long flower) {
        this.flower = flower;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWhenTime() {
        return whenTime;
    }

    public void setWhenTime(String whenTime) {
        this.whenTime = whenTime;
    }

    public long getTransmit() {
        return transmit;
    }

    public void setTransmit(long transmit) {
        this.transmit = transmit;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public long getHarvest() {
        return harvest;
    }

    public void setHarvest(long harvest) {
        this.harvest = harvest;
    }

    public int getIsWatched() {
        return isWatched;
    }

    public void setIsWatched(int isWatched) {
        this.isWatched = isWatched;
    }
}