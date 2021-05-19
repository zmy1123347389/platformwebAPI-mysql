package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Area;
import com.behere.video.domain.Banner;

import java.util.List;

@Mapper
public interface BannerDao {

	/**
	 * 获取banner
	 * @return
	 */
	List<Banner> queryBanners();
}
