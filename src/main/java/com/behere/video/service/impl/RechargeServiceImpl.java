package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.common.utils.StringUtils;
import com.behere.video.dao.RechargeDao;
import com.behere.video.dao.UserDao;
import com.behere.video.domain.InvitationRecharge;
import com.behere.video.domain.RechargePackage;
import com.behere.video.domain.RechargeRecord;
import com.behere.video.domain.WithdrawalRecordList;
import com.behere.video.model.InvitationRechargeRecord;
import com.behere.video.model.UserModel;
import com.behere.video.service.RechargeService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeDao rechargeDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<RechargePackage> queryRechargePackages() {
        return rechargeDao.queryRechargePackages();
    }

    @Override
    public RechargePackage queryRechargePackageById(int rechargePackageId) {
        return rechargeDao.queryRechargePackageById(rechargePackageId);
    }

    @Override
    public int saveRechargeRecord(RechargeRecord rechargeRecord) {
        return rechargeDao.saveRechargeRecord(rechargeRecord);
    }

    @Override
    public RechargeRecord queryRechargeRecordByOrderId(String orderId) {
        return rechargeDao.queryRechargeRecordByOrderId(orderId);
    }

    @Override
    public int updateRechargeRecordStatus(String orderId) {
        return rechargeDao.updateRechargeRecordStatus(orderId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int recharge(RechargeRecord rechargeRecord) {
        try {
            updateRechargeRecordStatus(rechargeRecord.getOrderId());
            userDao.addBalance(rechargeRecord.getUserId(), rechargeRecord.getDiamond());
            //为邀请人充值充值者5%钻石
            UserModel user = userDao.queryUserById(rechargeRecord.getUserId());
            if (!StringUtils.isEmpty(user.getInvitationCode())) {
                UserModel invitationUser = userDao.queryUserByInvitationCode(user.getInvitationCode());
                if (invitationUser != null) {
                    int giveBalance = Math.round(rechargeRecord.getDiamond() * 0.05F);
                    userDao.addBalance(invitationUser.getId(), giveBalance);
                    saveInvitationRechargeRecord(rechargeRecord.getUserId(), giveBalance, rechargeRecord.getDiamond(), invitationUser.getId());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return Constant.SUCCESS;
    }

    @Override
    public List<RechargeRecord> queryRechargeRecord(long userId) {
        return rechargeDao.queryRechargeRecord(userId);
    }

    /**
     * 保存邀请人充值记录
     * @param userId
     * @param giveBalance
     * @param rechargeBalance
     * @param invitationUser
     * @return
     */
    public int saveInvitationRechargeRecord(long userId, int giveBalance, int rechargeBalance, long invitationUser) {
        InvitationRecharge invitationRecharge = new InvitationRecharge();
        invitationRecharge.setId(StringUtils.getUUID());
        invitationRecharge.setBalance(rechargeBalance);
        invitationRecharge.setGiveBalance(giveBalance);
        invitationRecharge.setUserId(userId);
        invitationRecharge.setInvitationUser(invitationUser);
        return  rechargeDao.saveInvitationRechargeRecord(invitationRecharge);
    }

    @Override
    public List<InvitationRechargeRecord> queryInvitationRechargeRecords(long userId) {
        return rechargeDao.queryInvitationRechargeRecords(userId);
    }

    @Override
    public List<WithdrawalRecordList> queryWithdrawalRecordList(long userId, String createTime) {
        return rechargeDao.queryWithdrawalRecordList(userId, createTime);
    }

    @Override
    public double sumWithdrawal(long userId) {
        return rechargeDao.sumWithdrawal(userId);
    }
}
