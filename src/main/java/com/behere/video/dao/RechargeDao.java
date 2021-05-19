package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.InvitationRecharge;
import com.behere.video.domain.RechargePackage;
import com.behere.video.domain.RechargeRecord;
import com.behere.video.domain.WithdrawalRecordList;
import com.behere.video.model.InvitationRechargeRecord;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface RechargeDao {

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
    RechargePackage queryRechargePackageById(@Param("rechargePackageId") int rechargePackageId);

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
    RechargeRecord queryRechargeRecordByOrderId(@Param("orderId") String orderId);

    /**
     * 修改充值订单状态
     * @param orderId
     * @return
     */
    int updateRechargeRecordStatus(@Param("orderId") String orderId);

    /**
     * 充值记录列表
     * @param userId
     * @return
     */
    List<RechargeRecord> queryRechargeRecord(@Param("userId") long userId);

    /**
     * 保存邀请人充值记录
     * @param invitationRecharge
     * @return
     */
    int saveInvitationRechargeRecord(InvitationRecharge invitationRecharge);

    /**
     * 获取邀请人充值记录
     * @param userId
     * @return
     */
    List<InvitationRechargeRecord> queryInvitationRechargeRecords(@Param("userId") long userId);

    /**
     * 提现记录
     * @param userId
     * @return
     */
    List<WithdrawalRecordList> queryWithdrawalRecordList(@Param("userId") long userId, @Param("createTime") String createTime);

    /**
     * 统计已提现金额
     * @param userId
     * @return
     */
    double sumWithdrawal(@Param("userId") long userId);
}
