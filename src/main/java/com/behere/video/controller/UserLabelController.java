package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.annotation.Log;
import com.behere.common.utils.R;
import com.behere.video.domain.UserLabel;
import com.behere.video.service.UserLabelService;

/**
 *
 * @author Fengj
 * @date 2018/05/04
 *
 */
@Controller
@RequestMapping(value = "/api/v1/label")
public class UserLabelController {
	@Autowired
	private UserLabelService userLabelService;

	@Log("添加标签")
	@PostMapping(value = "/saveUserLabel")
	@ResponseBody
	public R saveUserLabel(UserLabel userLabel) {
		try {
			userLabelService.saveUserLabel(userLabel);
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}
}