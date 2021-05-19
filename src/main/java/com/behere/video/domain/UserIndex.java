package com.behere.video.domain;

import java.util.Date;
import java.util.List;

import com.behere.video.model.FlowerRank;
import com.behere.video.model.VideoModel;

/**
 * @author: Behere
 */
public class UserIndex {

    /**用户ID*/
    private long id;

    /**头像*/
    private String headPortrait;

    /**微信二维码*/
    private String wechatCode;

    /**昵称*/
    private String nickName;

    /**性别*/
    private int gender;

    /**评分*/
    private double score;

    private int age;

    /**是否在线0离线  1在线*/
    private int online;

    private List<BestFriend> bestFriend;

    /**接通率*/
    private double ConnectionRate;

    /**所在城市*/
    private String city;

    /**聊天价格*/
    private int servicePrice;

    /**用户视频列表*/
    private List<VideoModel> userVideos;

    /**评价标签*/
    private List<UserIndexLabel> userIndexLabels;

    private List<UserPic> userPics;

    private int auth;

    private Date offlineTime;

    private String offlineDistanceNow;

    private long flower;

    private int faceTime;

    @Deprecated
    private List<FlowerRank> flowerRanks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public double getConnectionRate() {
        return ConnectionRate;
    }

    public void setConnectionRate(double connectionRate) {
        ConnectionRate = connectionRate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public List<VideoModel> getUserVideos() {
        return userVideos;
    }

    public void setUserVideos(List<VideoModel> userVideos) {
        this.userVideos = userVideos;
    }

    public List<UserIndexLabel> getUserIndexLabels() {
        return userIndexLabels;
    }

    public void setUserIndexLabels(List<UserIndexLabel> userIndexLabels) {
        this.userIndexLabels = userIndexLabels;
    }

    public List<UserPic> getUserPics() {
        return userPics;
    }

    public void setUserPics(List<UserPic> userPics) {
        this.userPics = userPics;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public List<FlowerRank> getFlowerRanks() {
        return flowerRanks;
    }

    public void setFlowerRanks(List<FlowerRank> flowerRanks) {
        this.flowerRanks = flowerRanks;
    }

    public List<BestFriend> getBestFriend() {
        return bestFriend;
    }

    public void setBestFriend(List<BestFriend> bestFriend) {
        this.bestFriend = bestFriend;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public long getFlower() {
        return flower;
    }

    public void setFlower(long flower) {
        this.flower = flower;
    }

    public String getOfflineDistanceNow() {
        return offlineDistanceNow;
    }

    public void setOfflineDistanceNow(String offlineDistanceNow) {
        this.offlineDistanceNow = offlineDistanceNow;
    }

    public int getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(int faceTime) {
        this.faceTime = faceTime;
    }
}