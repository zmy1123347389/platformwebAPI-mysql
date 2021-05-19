package com.behere.video.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.common.utils.StringUtils;
import com.behere.video.dao.GiftDao;
import com.behere.video.domain.Gift;
import com.behere.video.domain.MyGift;
import com.behere.video.domain.SendGift;
import com.behere.video.service.GiftService;
import com.behere.video.service.UserService;

/**
 * @author: Behere
 */
@Service
public class GiftServiceImpl implements GiftService {

    @Autowired
    private GiftDao giftDao;

    @Autowired
    private UserService userService;

    @Override
    public List<Gift> queryGifts() {
        return giftDao.queryGifts();
    }

    @Override
    public Gift queryGiftById(int giftId) {
        return giftDao.queryGiftById(giftId);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int sendGift(SendGift sendGift, Gift gift) {
        try {
            String businessId = StringUtils.getUUID();
            sendGift.setId(businessId);
            int status = userService.saveDealInformation(sendGift.getFromUser(), sendGift.getToUser(), gift.getFlower(), Constant.SEND_GIFT, businessId);
            if (status == Constant.SUCCESS) {
            	giftDao.saveSendGiftRecord(sendGift);
            	return Constant.SUCCESS;
            } else {
            	return Constant.FAIL;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<MyGift> queryMyGift(long userId) {
        return giftDao.queryMyGift(userId);
    }
}
