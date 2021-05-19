package com.behere.video.domain;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author: Behere
 */
public class WithdrawalRecord {

    private double withdrawalMoney;

    private PageInfo<WithdrawalRecordList> withdrawalRecordLists;

    public double getWithdrawalMoney() {
        return withdrawalMoney;
    }

    public void setWithdrawalMoney(double withdrawalMoney) {
        this.withdrawalMoney = withdrawalMoney;
    }

    public PageInfo<WithdrawalRecordList> getWithdrawalRecordLists() {
        return withdrawalRecordLists;
    }

    public void setWithdrawalRecordLists(PageInfo<WithdrawalRecordList> withdrawalRecordLists) {
        this.withdrawalRecordLists = withdrawalRecordLists;
    }
}