package com.behere.video.service;

import com.behere.video.domain.Withdrawal;

/**
 * @author: Behere
 */
public interface WithdrawalService {

    /**
     * 保存提现申请
     * @param withdrawal
     * @return
     */
    int saveApplyWithdrawal(Withdrawal withdrawal);

    int applyWithdrawal(Withdrawal withdrawal);

}
