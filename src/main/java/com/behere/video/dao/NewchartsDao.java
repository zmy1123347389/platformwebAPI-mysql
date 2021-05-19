package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Newcharts;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface NewchartsDao {

    /**
     * 主播榜
     * @param date
     * @return
     */
    List<Newcharts> queryAnchorNewCharts(@Param("date") int date);

    /**
     * 富豪榜
     * @param date
     * @return
     */
    List<Newcharts> queryRicherNewCharts(@Param("date") int date);

}
