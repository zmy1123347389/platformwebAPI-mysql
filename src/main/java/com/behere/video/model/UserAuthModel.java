package com.behere.video.model;

/**
 * @author: Behere
 */
public class UserAuthModel {

    private long id;

    private String identityCard;

    private String indexImage;

    private String authVideo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
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
}