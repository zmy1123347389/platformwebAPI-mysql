package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Area;

public interface AreaService {

	/**
	 * 通过pid查询地区信息
	 * @param pid
	 * @return
	 */
	List<Area> queryAreaByPid(Integer pid);

	/**
	 * 获取全部地区信息
	 * @return
	 */
	List<Area> queryAreas();
}
