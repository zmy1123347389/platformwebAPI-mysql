package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.video.dao.UserDao;
import com.behere.video.dao.WithdrawalDao;
import com.behere.video.domain.Withdrawal;
import com.behere.video.service.WithdrawalService;

/**
 * @author: Behere
 */
@Service
public class WithdrawalServiceImpl implements WithdrawalService {
    @Autowired
    private WithdrawalDao withdrawalDao;

    @Autowired
    private UserDao userDao;

    @Override
    public int saveApplyWithdrawal(Withdrawal withdrawal) {
        return withdrawalDao.saveApplyWithdrawal(withdrawal);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int applyWithdrawal(Withdrawal withdrawal) {
        try {
            saveApplyWithdrawal(withdrawal);
            userDao.reduceFlower(withdrawal.getUserId(), withdrawal.getMoney() * Constant.CNY_TO_FLOWER);
            return Constant.SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
