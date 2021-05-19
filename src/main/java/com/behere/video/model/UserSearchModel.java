package com.behere.video.model;

/**
 * @author: Behere
 */
public class UserSearchModel {

    private long id;

    private String headPortrait;

    private String nickName;

    private int age;

    private int gender;

    private String signature;

    private int online;

    private int faceTime;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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