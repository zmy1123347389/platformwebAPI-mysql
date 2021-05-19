package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.*;

public interface BannerService {

	/**
	 * 获取banner
	 * @return
	 */
	List<Banner> queryBanners();
}