package com.behere.video.controller;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.behere.common.annotation.Log;
import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.utils.FTPUtil;
import com.behere.common.utils.R;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.UploadUtils;
import com.behere.video.domain.Accusation;
import com.behere.video.service.AccusationService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Fengj
 * @date 2018/05/04
 *
 */
@Controller
@RequestMapping(value = "/api/v1/accusation")
public class AccusationController {

    @Autowired
    private FTPConfig ftpConfig;
	
	@Autowired
	private AccusationService accusationService;

	@Log("获取举报文案")
	@PostMapping(value = "/queryAccusations")
	@ResponseBody
	public R queryAccusations() {
		try {
			List<Accusation> accusations = accusationService.queryAccusations();
			return R.ok(accusations);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_DATA_FAIL);
		}
	}
	
	@Log("获取举报文案")
	@PostMapping(value = "/upload")
	@ResponseBody
	public void upload(MultipartFile file) throws IOException {
		  
		String name = file.getName();
		System.out.println(name);
	}

	@Log("举报用户")
	@PostMapping(value = "/accusationUser")
	@ResponseBody
	public R accusationUser(long reportUserId, long reportedUserId, int accusationId, String content, @RequestParam("files") MultipartFile[] files) {
		try {
            accusationService.saveAccusation(reportUserId, reportedUserId, accusationId, files, content);
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}
}