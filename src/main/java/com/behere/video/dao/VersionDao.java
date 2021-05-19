package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.IosSwitch;
import com.behere.video.domain.Version;

/**
 * @author: Behere
 */
@Mapper
public interface VersionDao {

    /**
     * 通过设备类型查询最新版本号
     * @param deviceType
     * @return
     */
    Version queryVersionByDeviceType(@Param("deviceType") int deviceType);

    /**
     * 通过版本获取审核开关状态
     * @param versionNo
     * @return
     */
    IosSwitch querySwitch(@Param("versionNo") int versionNo);
}
