package com.behere.video.domain;


import com.behere.common.constant.Constant;
import com.behere.common.utils.StringUtils;

/**
 * @author: Behere
 */
public class OnlineUser {

    private long id;
    private String headPortrait;
    private String nickName;
    private String city;
    private int age;
    private String professionName;
    private String signature;
    private String wechatCode;
    private String mobile;
    private short gender;
    private String neteaseToken;
    private int servicePrice;
    private short vip;
    private long balance;
    private int online;
    private int auth;
    private long flower;
    private int faceTime;
    private short deleted;


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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        if (StringUtils.isEmpty(signature)) {
            signature = Constant.DEFAULT_SIGNATURE;
        }
        this.signature = signature;
    }

    public String getWechatCode() {
        return wechatCode;
    }

    public void setWechatCode(String wechatCode) {
        this.wechatCode = wechatCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getNeteaseToken() {
        return neteaseToken;
    }

    public void setNeteaseToken(String neteaseToken) {
        this.neteaseToken = neteaseToken;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setVip(short vip) {
        this.vip = vip;
    }

    public short getVip() {
        return vip;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public long getFlower() {
        return flower;
    }

    public void setFlower(long flower) {
        this.flower = flower;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getFaceTime() {
        return faceTime;
    }

    public void setFaceTime(int faceTime) {
        this.faceTime = faceTime;
    }
}