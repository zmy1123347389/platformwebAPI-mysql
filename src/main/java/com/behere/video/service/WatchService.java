package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Watch;
import com.behere.video.domain.WatchRecord;
import com.behere.video.model.WatchPriceModel;
import com.behere.video.model.WatchUserModel;

public interface WatchService {

	/**
	 * 守护
	 * @param watchRecord
	 * @return
	 */
	int watchUser(WatchRecord watchRecord);

	/**
	 * 保存守护记录
	 * @param watchUser
	 * @return
	 */
	int saveWatchRecord(WatchRecord watchUser);

	/**
	 * 查询守护我的用户列表
	 * @param toUser
	 * @return
	 */
	List<WatchUserModel> queryWatchMeList(long toUser);

	/**
	 * 查询我守护的用户列表
	 * @param fromUser
	 * @return
	 */
	List<WatchUserModel> queryMyWatchList(long fromUser);

	/**
	 * 查询守护价格
	 * @return
	 */
	List<WatchPriceModel> queryWatchPrices();

	/**
	 *
	 * @param priceId
	 * @return
	 */
	int queryWatchPriceById(int priceId);

    /**
     * 删除我守护的
     * @param watch
     * @return
     */
	void deleteMyWatchRecord(Watch watch);

	/**
	 * 通过守护者ID查询守护鲜花数量
	 * @param watchRecord
	 * @return
	 */
	long queryWatchFlowerByFromUserId(WatchRecord watchRecord);
}