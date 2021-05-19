package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Gift;
import com.behere.video.domain.MyGift;
import com.behere.video.domain.SendGift;

/**
 * @author: Behere
 */
public interface GiftService {

    /**
     * 礼物列表
     * @return
     */
    List<Gift> queryGifts();

    /**
     * 通过礼物ID获取礼物信息
     * @param giftId
     * @return
     */
    Gift queryGiftById(int giftId);

    /**
     * 发送礼物
     * @param sendGift
     * @return
     */
    int sendGift(SendGift sendGift, Gift gift);

    /**
     * 查询我的礼物
     * @param userId
     * @return
     */
    List<MyGift> queryMyGift(long userId);
}
