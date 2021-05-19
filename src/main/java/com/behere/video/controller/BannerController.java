package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.utils.R;
import com.behere.video.domain.Banner;
import com.behere.video.service.BannerService;

import java.util.List;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    /**
     * 获取banner
     * @return
     */
    @PostMapping("/queryBanners")
    @ResponseBody
    public R queryBanners() {
        try {
            return R.ok(bannerService.queryBanners());
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }
}