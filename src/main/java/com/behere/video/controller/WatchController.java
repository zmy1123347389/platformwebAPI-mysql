package com.behere.video.controller;

import com.behere.common.annotation.Log;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.*;
import com.behere.video.domain.BestFriend;
import com.behere.video.domain.Watch;
import com.behere.video.domain.WatchRecord;
import com.behere.video.model.UserModel;
import com.behere.video.model.WatchPriceModel;
import com.behere.video.model.WatchUserModel;
import com.behere.video.service.UserService;
import com.behere.video.service.WatchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 守护 controller
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/watch")
public class WatchController {
    @Autowired
    private UserService userService;
    @Autowired
    private WatchService watchService;
    @Autowired
    private RedisUtil redisUtil;

    @Log("守护")
    @PostMapping(value = "/watchUser")
    @ResponseBody
    public R watchUser(String m) {
        try {
            Watch watch = RequestUtils.parseParameter(m, Watch.class);
            int price = watchService.queryWatchPriceById(watch.getPriceId());
            WatchRecord watchRecord = new WatchRecord();
            watchRecord.setFlower(price);
            watchRecord.setFromUser(watch.getFromUser());
            watchRecord.setToUser(watch.getToUser());
            List<BestFriend> bestFriends = userService.queryBestFriend(watch.getToUser());
            if (watchService.watchUser(watchRecord) == 0) {
            	return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
            }
            UserModel fromUser = userService.queryUserById(watchRecord.getFromUser());
            UserModel toUser = userService.queryUserById(watchRecord.getToUser());
            //String msg = fromUser.getNickName() + "守护了您，+" + watchRecord.getFlower() / 2 + "鲜花，可在“我的收益-明细”查看";
            String msg = NeteaseUtils.setMsgExtMap(fromUser.getId(), null, fromUser.getNickName(), null, watchRecord.getFlower() / 2, MsgConstant.WATCH_USER, null);
            NeteaseUtils.sendMsg(msg, watch.getToUser());

            //PK 消息推送
            if (bestFriends.size() > 0) {
                BestFriend bestFriend = bestFriends.get(0);
                long mySendFlower = watchService.queryWatchFlowerByFromUserId(watchRecord);
                if (mySendFlower > bestFriend.getFlower() && bestFriend.getId() != watchRecord.getFromUser()) {
                   // String msg0 = fromUser.getNickName() + "PK掉" + bestFriend.getNickName() + "，现已成为您的";
                    String commonMsg = "最佳女友";
                    if (toUser.getGender() == 2) {
                        commonMsg = "最佳男友";
                    }
                    String msg0 = NeteaseUtils.setMsgExtMap(fromUser.getId(), bestFriend.getId(), fromUser.getNickName(), bestFriend.getNickName(), null, MsgConstant.PK_OTHER_SUCCESS, commonMsg);
                    NeteaseUtils.sendMsg(msg0, toUser.getId());
                  //  String msg1 = fromUser.getNickName() + "PK掉您，现已成为" + toUser.getNickName() + "的";
                    String msg1 = NeteaseUtils.setMsgExtMap(fromUser.getId(), toUser.getId(), fromUser.getNickName(), toUser.getNickName(), null, MsgConstant.PK_YOU, commonMsg);
                    NeteaseUtils.sendMsg(msg1, bestFriend.getId());
                    //String msg2 = "恭喜您成功PK掉" + bestFriend.getNickName() + "，现已成为" + toUser.getNickName() + "的";
                    String msg2 = NeteaseUtils.setMsgExtMap(bestFriend.getId(), toUser.getId(), bestFriend.getNickName(), toUser.getNickName(), null, MsgConstant.PK_SUCCESS, commonMsg);
                    NeteaseUtils.sendMsg(msg2, fromUser.getId());
                }
            }
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查询守护我的列表")
    @PostMapping(value = "/queryWatchMeList")
    @ResponseBody
    public R queryWatchMeList(WatchRecord watchRecord,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<WatchUserModel> result = watchService.queryWatchMeList(watchRecord.getToUser());
            PageInfo<WatchUserModel> pageInfo = new PageInfo<WatchUserModel>(result);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }

    }

    @Log("查询我守护的列表")
    @PostMapping(value = "/queryMyWatchList")
    @ResponseBody
    public R queryMyWatchList(WatchRecord watchRecord,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<WatchUserModel> result = watchService.queryMyWatchList(watchRecord.getFromUser());
            PageInfo<WatchUserModel> pageInfo = new PageInfo<WatchUserModel>(result);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }

    @Log("查询守护价格")
    @PostMapping(value = "/queryWatchPrices")
    @ResponseBody
    public R queryWatchPrices() {
        try {
            List<WatchPriceModel> watchPrices = watchService.queryWatchPrices();
            return R.ok(watchPrices);
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }

    @Log("删除我守护的")
    @PostMapping(value = "/deleteMyWatchRecord")
    @ResponseBody
    public R deleteMyWatchRecord(String m) {
        try {
            Watch watch = RequestUtils.parseParameter(m, Watch.class);
            watchService.deleteMyWatchRecord(watch);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}