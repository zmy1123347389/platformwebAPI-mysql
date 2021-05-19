package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Area;

import java.util.List;

@Mapper
public interface AreaDao {

	/**
	 * 通过pid查询地区信息
	 * @param pid
	 * @return
	 */
	List<Area> queryAreaByPid(@Param("pid") Integer pid);

	/**
	 * 获取全部地区信息
	 * @return
	 */
	List<Area> queryAreas();
}
