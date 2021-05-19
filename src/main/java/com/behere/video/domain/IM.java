package com.behere.video.domain;

/**
 * @author: Behere
 */
public class IM {

    private String businessId;
    private long customerId;
    private long customerBalance;
    private int diamondToFlowerRate;
    private long servicerId;
    private boolean canPayNextTime;
    private int servicePrice;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(long customerBalance) {
        this.customerBalance = customerBalance;
    }

    public int getDiamondToFlowerRate() {
        return diamondToFlowerRate;
    }

    public void setDiamondToFlowerRate(int diamondToFlowerRate) {
        this.diamondToFlowerRate = diamondToFlowerRate;
    }

    public long getServicerId() {
        return servicerId;
    }

    public void setServicerId(long servicerId) {
        this.servicerId = servicerId;
    }

    public boolean isCanPayNextTime() {
        return canPayNextTime;
    }

    public void setCanPayNextTime(boolean canPayNextTime) {
        this.canPayNextTime = canPayNextTime;
    }

    public int getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }
}
