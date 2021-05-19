package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.annotation.Log;
import com.behere.common.utils.R;
import com.behere.video.domain.IosSwitch;
import com.behere.video.domain.Version;
import com.behere.video.service.VersionService;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/version")
public class VersionController {

    @Autowired
    private VersionService versionService;

    @Log("获取版本信息")
    @PostMapping("/queryVersion")
    @ResponseBody
    public R queryVersion(int deviceType) {
        try {
            Version version = versionService.queryVersionByDeviceType(deviceType);
            return R.ok(version);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/querySwitch")
    @ResponseBody
    public R querySwitch(int versionNum) {
        try {
            IosSwitch iosSwitch = versionService.querySwitch(versionNum);
            return R.ok(iosSwitch);
        } catch (Exception e) {
            return R.error();
        }
    }
}
