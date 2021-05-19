package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Accusation;
import com.behere.video.domain.UserLabel;

import java.util.List;

@Mapper
public interface UserLabelDao {

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
	List<UserLabel> queryUserLabelByUserId(@Param("userId") long userId);
}
