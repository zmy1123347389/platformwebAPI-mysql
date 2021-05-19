package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.video.dao.AreaDao;
import com.behere.video.domain.Area;
import com.behere.video.service.AreaService;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> queryAreaByPid(Integer pid) {
        return areaDao.queryAreaByPid(pid);
    }

    @Override
    public List<Area> queryAreas() {
        return areaDao.queryAreas();
    }
}
