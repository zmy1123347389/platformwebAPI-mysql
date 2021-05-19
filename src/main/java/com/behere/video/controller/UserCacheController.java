package com.behere.video.controller;

import com.behere.common.constant.Constant;
import com.behere.common.utils.DateUtils;
import com.behere.common.utils.R;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.StringUtils;
import com.behere.video.domain.OnlineUser;
import com.behere.video.model.UserModel;
import com.behere.video.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @author: Behere
 */
@RequestMapping(value = "api/v1/usercache")
@Controller
public class UserCacheController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/putUserCache")
    @ResponseBody
    public R putUserCache(String userId) {
        try {
//            redisUtil.set("behere.appuser.online." + userId, String.valueOf(userId), 130L);
//            userService.updateUserOnOrOffOnline(userId, Constant.ONLINE);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/getUserCache")
    @ResponseBody
    public R getUserCache(int auth, int gender, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<OnlineUser> users = userService.queryUsers(auth, gender);
            PageInfo<OnlineUser> pageInfo = new PageInfo<OnlineUser>(users);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/getUserOnlineStatus")
    @ResponseBody
    public R getUserOnlineStatus(long userId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            UserModel user = userService.queryUserById(userId);
            map.put("online", user.getOnline());
            return R.ok(map);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/sayHello")
    @ResponseBody
    public R sayHello(int gender, int auth, int offset, int limit, long userId) {
        List<OnlineUser> users = new ArrayList<OnlineUser>();
        try {
            String isExsit = redisUtil.get("behere.appuser.isexist." + DateUtils.getYearMonthDay() + "." + userId);
            if (StringUtils.isEmpty(isExsit)) {
                users = getOnlineUser(gender, auth, offset, limit);
                if (users.size() > 0) {
                    redisUtil.set("behere.appuser.isexist." + DateUtils.getYearMonthDay() + "." + userId, String.valueOf(userId), 86400L);
                }
            }
            return R.ok(users);
        } catch (Exception e) {
            return R.error();
        }
    }

    public List<OnlineUser> getOnlineUser(int gender, int auth, int offset, int limit) {
//        List<OnlineUser> users = new ArrayList<OnlineUser>();
//        Set<String> set = redisUtil.getKeys("behere.appuser.online.*");
//        List<String> userIds = new ArrayList<String>();
//        for (String s : set) {
//            userIds.add(redisUtil.get(s));
//        }
//        if (!userIds.isEmpty()) {
//            users = userService.queryOnlineUser(userIds, gender, auth, offset, limit);
//        }
        return userService.queryOnlineUser(null, gender, auth, offset, limit);
    }
}