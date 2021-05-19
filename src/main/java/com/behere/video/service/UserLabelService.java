package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Accusation;
import com.behere.video.domain.UserLabel;

public interface UserLabelService {

	/**
	 * 为用户添加标签
	 * @param userLabel
	 * @return
	 */
	int saveUserLabel(UserLabel userLabel);

	/**
	 * 获取用户标签
	 * @param userId
	 * @return
	 */
	List<UserLabel> queryUserLabelByUserId(long userId);
}
