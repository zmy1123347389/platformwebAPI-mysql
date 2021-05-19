package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.R;
import com.behere.video.domain.Withdrawal;
import com.behere.video.model.UserModel;
import com.behere.video.service.UserService;
import com.behere.video.service.WithdrawalService;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/withdrawal")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private UserService userService;


    @PostMapping("/applyWithdrawal")
    @ResponseBody
    public R applyWithdrawal(Withdrawal withdrawal) {
        try {
            UserModel user = userService.queryUserById(withdrawal.getUserId());
            if (user.getFlower() / Constant.CNY_TO_FLOWER < withdrawal.getMoney()) {
                return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            withdrawalService.applyWithdrawal(withdrawal);
            String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, null, MsgConstant.APP_WITHDRAWAL, String.valueOf(withdrawal.getMoney()));
            NeteaseUtils.sendMsg(msg, withdrawal.getUserId());
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    /**
     * 个人提现明细
     * @param userId
     * @return
     */
    @PostMapping("/withdrawalList")
    @ResponseBody
    public R withdrawalList(long userId) {
        try {
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}