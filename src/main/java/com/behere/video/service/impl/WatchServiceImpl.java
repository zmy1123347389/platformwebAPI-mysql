package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.common.utils.StringUtils;
import com.behere.video.dao.WatchUserDao;
import com.behere.video.domain.Watch;
import com.behere.video.domain.WatchRecord;
import com.behere.video.model.WatchPriceModel;
import com.behere.video.model.WatchUserModel;
import com.behere.video.service.UserService;
import com.behere.video.service.WatchService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class WatchServiceImpl implements WatchService {
    @Autowired
    private WatchUserDao watchUserDao;
    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int watchUser(WatchRecord watchRecord) {
        long fromUser = watchRecord.getFromUser();
        long toUser = watchRecord.getToUser();
        long flower = watchRecord.getFlower();
        try {
            String businessId = StringUtils.getUUID();
            watchRecord.setId(businessId);
            int status = userService.saveDealInformation(fromUser, toUser, flower, Constant.WATCH_USER, businessId);
            if (status == Constant.SUCCESS) {
            	saveWatchRecord(watchRecord);
            	return Constant.SUCCESS;
            } else {
            	return Constant.FAIL;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public int saveWatchRecord(WatchRecord watchUser) {
        return watchUserDao.saveWatchRecord(watchUser);
    }

    @Override
    public List<WatchUserModel> queryWatchMeList(long toUser) {
        return watchUserDao.queryWatchMeList(toUser);
    }

    @Override
    public List<WatchUserModel> queryMyWatchList(long fromUser) {
        return watchUserDao.queryMyWatchList(fromUser);
    }

    @Override
    public List<WatchPriceModel> queryWatchPrices() {
        return watchUserDao.queryWatchPrices();
    }

    @Override
    public int queryWatchPriceById(int priceId) {
        return watchUserDao.queryWatchPriceById(priceId);
    }

    @Override
    public void deleteMyWatchRecord(Watch watch) {
        watchUserDao.deleteMyWatchRecord(watch);
    }

    @Override
    public long queryWatchFlowerByFromUserId(WatchRecord watchRecord) {
        return watchUserDao.queryWatchFlowerByFromUserId(watchRecord);
    }
}