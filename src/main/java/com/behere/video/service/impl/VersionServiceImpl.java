package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.video.dao.VersionDao;
import com.behere.video.domain.IosSwitch;
import com.behere.video.domain.Version;
import com.behere.video.service.VersionService;

/**
 * @author: Behere
 */
@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    private VersionDao versionDao;

    @Override
    public Version queryVersionByDeviceType(int deviceType) {
        return versionDao.queryVersionByDeviceType(deviceType);
    }

    @Override
    public IosSwitch querySwitch(int versionNo) {
        return versionDao.querySwitch(versionNo);
    }
}
