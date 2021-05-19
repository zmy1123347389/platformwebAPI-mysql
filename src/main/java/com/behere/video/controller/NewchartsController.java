package com.behere.video.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.constant.Constant;
import com.behere.common.utils.R;
import com.behere.video.domain.Newcharts;
import com.behere.video.service.NewchartsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/newcharts")
public class NewchartsController {
    @Autowired
    private NewchartsService newchartsService;

    @PostMapping("/queryNewCharts")
    @ResponseBody
    public R queryAnchorNewCharts(int date,
                                  int type,
                                  @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            List<Newcharts> newchartsList = null;
            PageHelper.startPage(pageNum, pageSize);
            if (type == Constant.ANCHOR) {
                newchartsList = newchartsService.queryAnchorNewCharts(date);
            } else {
                newchartsList = newchartsService.queryRicherNewCharts(date);
            }
            PageInfo<Newcharts> pageInfo = new PageInfo<Newcharts>(newchartsList);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }
}
