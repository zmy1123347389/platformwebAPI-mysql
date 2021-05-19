package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import com.behere.video.domain.Gift;
import com.behere.video.domain.MyGift;
import com.behere.video.domain.SendGift;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface GiftDao {

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
    Gift queryGiftById(@Param("giftId") int giftId);

    /**
     *  发送礼物
     * @param sendGift
     * @return
     */
    int saveSendGiftRecord(SendGift sendGift);

    /**
     * 查询我的礼物
     * @param userId
     * @return
     */
    List<MyGift> queryMyGift(@Param("userId") long userId);

}
