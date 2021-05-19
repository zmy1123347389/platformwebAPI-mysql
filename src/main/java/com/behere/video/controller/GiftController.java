package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.annotation.Log;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.R;
import com.behere.common.utils.RequestUtils;
import com.behere.video.domain.Gift;
import com.behere.video.domain.MyGift;
import com.behere.video.domain.SendGift;
import com.behere.video.domain.User;
import com.behere.video.model.UserModel;
import com.behere.video.service.GiftService;
import com.behere.video.service.UserService;

import java.util.List;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/gift")
public class GiftController {
    @Autowired
    private GiftService giftService;

    @Autowired
    private UserService userService;

    @Log("礼物列表")
    @PostMapping("/queryGifts")
    @ResponseBody
    public R queryGifts() {
        try {
            List<Gift> giftList = giftService.queryGifts();
            return R.ok(giftList);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("发送礼物")
    @PostMapping("/sendGift")
    @ResponseBody
    public R sendGift(String m) {
        try {
            SendGift sendGift = RequestUtils.parseParameter(m, SendGift.class);
            Gift gift = giftService.queryGiftById(sendGift.getGiftId());
            if (giftService.sendGift(sendGift, gift) == 0) {
            	return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            UserModel user = userService.queryUserById(sendGift.getFromUser());
           // String msg = user.getNickName() + "赠送您" + gift.getName() + "礼物，+" + gift.getFlower() / 2 + "鲜花，可在“我的收益-明细”查看";
            String msg = NeteaseUtils.setMsgExtMap(user.getId(), null, user.getNickName(), null, gift.getFlower() / 2, MsgConstant.GIFT, gift.getName());
            NeteaseUtils.sendMsg(msg, sendGift.getToUser());
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("我收到的礼物")
    @PostMapping("/queryMyGift")
    @ResponseBody
    public R queryMyGift(long userId) {
        try {
            List<MyGift> gifts = giftService.queryMyGift(userId);
            return R.ok(gifts);
        } catch (Exception e) {
            return R.error();
        }
    }
}