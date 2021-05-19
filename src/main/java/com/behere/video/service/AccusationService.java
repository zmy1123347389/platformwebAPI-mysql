package com.behere.video.service;

import org.springframework.web.multipart.MultipartFile;

import com.behere.video.domain.Accusation;
import com.behere.video.domain.Area;

import java.util.List;

public interface AccusationService {

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
	int saveAccusationInformation(String id, long reportUserId, long reportedUserId, int accusationId, String content);

	/**
	 * 保存举报图片
	 * @param id
	 * @param reportId
	 * @param pic
	 * @return
	 */
	int saveAccusationPic(String id, String reportId, String pic);

    int saveAccusation(long reportUserId, long reportedUserId, int accusationId, MultipartFile[] files, String content);
}
