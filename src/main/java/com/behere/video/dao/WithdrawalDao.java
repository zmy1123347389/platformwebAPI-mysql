package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;

import com.behere.video.domain.Withdrawal;

/**
 * @author: Behere
 */
@Mapper
public interface WithdrawalDao {

    /**
     * 保存提现申请
     * @param withdrawal
     * @return
     */
    int saveApplyWithdrawal(Withdrawal withdrawal);

}
