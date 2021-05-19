package com.behere.video.service;

import com.behere.video.domain.IosSwitch;
import com.behere.video.domain.Version;

/**
 * @author: Behere
 */
public interface VersionService {

    /**
     * 通过设备类型查询最新版本号
     * @param deviceType
     * @return
     */
    Version queryVersionByDeviceType(int deviceType);

    /**
     * 通过版本获取审核开关状态
     * @param versionNo
     * @return
     */
    IosSwitch querySwitch(int versionNo);

}
