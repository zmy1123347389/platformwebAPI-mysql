package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.video.dao.BannerDao;
import com.behere.video.domain.Banner;
import com.behere.video.service.BannerService;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;

    @Override
    public List<Banner> queryBanners() {
        return bannerDao.queryBanners();
    }
}
