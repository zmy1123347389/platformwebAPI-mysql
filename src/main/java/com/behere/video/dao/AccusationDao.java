package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Accusation;
import com.behere.video.domain.Area;

import java.util.List;

@Mapper
public interface AccusationDao {

	/**
	 * 查询所有举报文案
	 * @return
	 */
	List<Accusation> queryAccusations();


	/**
	 * 保存举报信息
	 * @param reportUserId
	 * @param reportedUserId
	 * @param accusationId
	 * @return
	 */
	int saveAccusationInformation(@Param("id") String id,
			                      @Param("reportUserId") long reportUserId,
								  @Param("reportedUserId") long reportedUserId,
								  @Param("accusationId") int accusationId,
                                  @Param("content") String content);

    /**
     * 保存举报图片
     * @param id
     * @param reportId
     * @param pic
     * @return
     */
	int saveAccusationPic(@Param("id") String id, @Param("reportId") String reportId, @Param("pic") String pic);
}
