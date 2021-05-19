package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.RechargePackage;
import com.behere.video.domain.RechargeRecord;
import com.behere.video.domain.WithdrawalRecordList;
import com.behere.video.model.InvitationRechargeRecord;

/**
 * @author: Behere
 */
public interface RechargeService {

    /**
     * 获取充值套餐
     * @return
     */
    List<RechargePackage> queryRechargePackages();

    /**
     * 通过ID获取充值套餐详情
     * @param rechargePackageId
     * @return
     */
    RechargePackage queryRechargePackageById(int rechargePackageId);

    /**
     * 保存预付款记录
     * @param rechargeRecord
     * @return
     */
    int saveRechargeRecord(RechargeRecord rechargeRecord);

    /**
     * 通过订单ID查询充值记录
     * @param orderId
     * @return
     */
    RechargeRecord queryRechargeRecordByOrderId(String orderId);

    /**
     * 修改充值订单状态
     * @param orderId
     * @return
     */
    int updateRechargeRecordStatus(String orderId);

    int recharge(RechargeRecord rechargeRecord);

    /**
     * 充值记录列表
     * @param userId
     * @return
     */
    List<RechargeRecord> queryRechargeRecord(long userId);

    /**
     * 获取邀请人充值记录
     * @param userId
     * @return
     */
    List<InvitationRechargeRecord> queryInvitationRechargeRecords(long userId);

    /**
     * 提现记录
     * @param userId
     * @return
     */
    List<WithdrawalRecordList> queryWithdrawalRecordList(long userId, String createTime);

    /**
     * 统计已提现金额
     * @param userId
     * @return
     */
    double sumWithdrawal(long userId);
}
