package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Newcharts;

/**
 * @author: Behere
 */
public interface NewchartsService {

    /**
     * 主播榜
     * @param date
     * @return
     */
    List<Newcharts> queryAnchorNewCharts(int date);

    /**
     * 富豪榜
     * @param date
     * @return
     */
    List<Newcharts> queryRicherNewCharts(int date);
}
