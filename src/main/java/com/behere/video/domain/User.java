package com.behere.video.domain;

import com.behere.common.utils.Param;

public class User implements Param{
	private static final long serialVersionUID = 1L;
	private Long id;
    private String headPortrait;
    private String nickName;
    private String city;
    private int profession;
    private String signature;
    private String wechatCode;
    private String mobile;
    private String username;
    private String password;
    private short gender;
    private int age;
    private String verificaCode;
    private String neteaseToken;
    private int servicePrice;
    private short vip;
    private long balance;
    private String indexImage;
    private String authVideo;
    private long myId;
    private String invitationCode;
    private String myInvitationCode;
    private short deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getProfession() {
        return profession;
    }

    public void setProfession(int profession) {
        this.profession = profession;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getVerificaCode() {
        return verificaCode;
    }

    public void setVerificaCode(String verificaCode) {
        this.verificaCode = verificaCode;
    }

    public String getNeteaseToken() {
        return neteaseToken;
    }

    public void setNeteaseToken(String neteaseToken) {
        this.neteaseToken = neteaseToken;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }

    public short getVip() {
        return vip;
    }

    public void setVip(short vip) {
        this.vip = vip;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(String indexImage) {
        this.indexImage = indexImage;
    }

    public String getAuthVideo() {
        return authVideo;
    }

    public void setAuthVideo(String authVideo) {
        this.authVideo = authVideo;
    }

    public long getMyId() {
        return myId;
    }

    public void setMyId(long myId) {
        this.myId = myId;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public String getMyInvitationCode() {
        return myInvitationCode;
    }

    public void setMyInvitationCode(String myInvitationCode) {
        this.myInvitationCode = myInvitationCode;
    }

    public short getDeleted() {
        return deleted;
    }

    public void setDeleted(short deleted) {
        this.deleted = deleted;
    }
}