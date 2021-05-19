package com.behere.video.model;

/**
 * @author: Behere
 */
public class UnlockModel {

    private long price;

    private String weChatImage;

    private int flag;

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getWeChatImage() {
        return weChatImage;
    }

    public void setWeChatImage(String weChatImage) {
        this.weChatImage = weChatImage;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}