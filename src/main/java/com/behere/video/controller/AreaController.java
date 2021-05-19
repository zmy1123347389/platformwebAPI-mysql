package com.behere.video.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.annotation.Log;
import com.behere.common.constant.Constant;
import com.behere.common.utils.R;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.RequestUtils;
import com.behere.video.domain.Area;
import com.behere.video.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 *
 * @author Fengj
 * @date 2018/05/04
 *
 */
@Controller
@RequestMapping(value = "/api/v1/area")
public class AreaController {
	
	@Autowired
	AreaService areaService;


	@Log("通过pid获取地区信息")
	@PostMapping(value = "/queryAreas")
	@ResponseBody
	public R queryAreas() {
		try {
			return R.ok(areaService.queryAreas());
		} catch (Exception e) {
			return R.error(-1, Constant.QUERY_AREA_FAIL);
		}
	}

	@Log("通过pid获取地区信息")
	@PostMapping(value = "/queryAreaByPid")
	@ResponseBody
	public R queryAreaByPid(String m) {
		try {
			Area area = RequestUtils.parseParameter(m, Area.class);
			return R.ok(areaService.queryAreaByPid(area.getPid()));
		} catch (Exception e) {
			return R.error(-1, Constant.QUERY_AREA_FAIL);
		}
	}
}