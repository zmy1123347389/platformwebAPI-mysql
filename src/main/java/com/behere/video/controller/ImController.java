package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.constant.Constant;
import com.behere.common.push.Demo;
import com.behere.common.utils.R;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.RequestUtils;
import com.behere.common.utils.StringUtils;
import com.behere.video.domain.FaceTime;
import com.behere.video.domain.IM;
import com.behere.video.model.UserModel;
import com.behere.video.model.WritingChat;
import com.behere.video.service.ImService;
import com.behere.video.service.UserService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Fengj
 */
@Controller
@RequestMapping(value = "/api/v1/im")
public class ImController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ImService imService;

    @PostMapping("/summon")
    @ResponseBody
    public R summon(long userId, long calledId, int deviceType) {
        try {
            UserModel user = userService.queryUserById(userId);
            userService.isVip(user);
            Demo androidDemo = new Demo(Constant.ANDROID_PUSH_APP_KEY, Constant.ANDROID_PUSH_Master_SECRET);
            Demo iosDemo = new Demo(Constant.IOS_PUSH_APP_KEY, Constant.IOS_PUSH_Master_SECRET);
            iosDemo.sendIOSCustomizedcast(String.valueOf(calledId),"IM",Constant.APP_NAME,user.getNickName() +  Constant.SUMMON_PUSH_CONTENT);
            androidDemo.sendAndroidCustomizedcast(String.valueOf(calledId),"IM",Constant.APP_NAME,user.getNickName() +  Constant.SUMMON_PUSH_CONTENT);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/cantFaceTime")
    @ResponseBody
    public R cantFaceTime(String m) {
        try {
            FaceTime faceTime = RequestUtils.parseParameter(m, FaceTime.class);
            UserModel caller = userService.queryUserById(faceTime.getCallerId());
            UserModel called = userService.queryUserById(faceTime.getCalledId());

            if (called.getOnline() != Constant.ONLINE) {
                return R.error(-2, Constant.CALLED_NOT_ONLINE);
            }

            if (Constant.AUTH != caller.getAuth() && Constant.AUTH != called.getAuth()) {
                return R.error(-2, Constant.ALL_DONT_AUTH);
            }
            if (Constant.AUTH == caller.getAuth() && Constant.AUTH == called.getAuth()) {
                return R.error(-2, Constant.CANT_TALK);
            }
            UserModel customer = null;
            UserModel servicer = null;
            if (caller.getAuth() == Constant.AUTH) {
                servicer = caller;
                customer = called;
            } else {
                servicer = called;
                customer = caller;
            }
            if (customer.getBalance() * Constant.DIAMOND_TO_FLOWER_RATE < servicer.getServicePrice()) {
                if (caller.getAuth() == 1) {
                    return R.error(-1, Constant.CUSTOMER_BALANCE_NOT_ENOUGH);
                }
                return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            return R.ok(servicer);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/faceTime")
    @ResponseBody
    public R faceTime(String m) {
        IM im = new IM();
        try {
            FaceTime faceTime = RequestUtils.parseParameter(m, FaceTime.class);
            int result = imService.talk(faceTime, im);
            if (result == 0) {
                return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            return R.ok(im);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    @PostMapping("/canWritingChat")
    @ResponseBody
    public R canWritingChat(String m) {
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("canWritingChat", 1L);
        result.put("nums", 10L);
        try {
            WritingChat writingChat = RequestUtils.parseParameter(m, WritingChat.class);
            UserModel caller = userService.queryUserById(writingChat.getCallerId());
            if (caller.getAuth() == 1) {
                return R.ok(result);
            }
            long writingChatNums = userService.countWritingChatRecord(writingChat);
            result.put("nums", 10 - writingChatNums);
            if (writingChatNums >= 10) {
                result.put("canWritingChat", 0L);
                return R.ok(result);
            }
            return R.ok(result);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/writingChat")
    @ResponseBody
    public R writingChat(String m) {
        Map<String, Long> resultMap = new HashMap<String, Long>();
        resultMap.put("nums", 10L);
        try {
            WritingChat writingChat = RequestUtils.parseParameter(m, WritingChat.class);
            int result = userService.saveDealInformation(writingChat.getCallerId(), writingChat.getCalledId(), 10L, Constant.WRITING_CHAT, StringUtils.getUUID());
            if (result == 0) {
                return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            userService.saveWritingChatRecord(writingChat);
            long writingChatNums = userService.countWritingChatRecord(writingChat);
            resultMap.put("nums", 10L - writingChatNums);
            return R.ok(resultMap);
        } catch (Exception e) {
            return R.error();
        }
    }
}