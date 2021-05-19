package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.video.dao.NewchartsDao;
import com.behere.video.domain.Newcharts;
import com.behere.video.service.NewchartsService;

import java.util.List;

/**
 * @author: Behere
 */
@Service
public class NewchartsServiceImpl implements NewchartsService {
    @Autowired
    private NewchartsDao newchartsDao;

    @Override
    public List<Newcharts> queryAnchorNewCharts(int date) {
        return newchartsDao.queryAnchorNewCharts(date);
    }

    @Override
    public List<Newcharts> queryRicherNewCharts(int date) {
        return newchartsDao.queryRicherNewCharts(date);
    }
}
