package com.behere.video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.behere.common.constant.Constant;
import com.behere.common.utils.R;
import com.behere.video.model.ShareVideoModel;
import com.behere.video.model.VideoInfoModel;
import com.behere.video.service.VideoService;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "/api/v1/share")
public class ShareController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/getSharePage")
    public String getSharePage(String videoId) {
        return "/share";
    }

    @PostMapping("/shareVideo")
    @ResponseBody
    public R shareVideo(String videoId) {
        try {
            ShareVideoModel shareVideoModel = new ShareVideoModel();
            VideoInfoModel video = videoService.queryVideoByVideoId(videoId);
            long likes = videoService.queryVideoLikes(videoId);
            long comments = videoService.queryVideoCommentNumber(videoId);
            if (video != null) {
                shareVideoModel.setHeadPortrait(video.getHeadPortrait());
                shareVideoModel.setLikeNumber(likes);
                shareVideoModel.setCommentNumber(comments);
                shareVideoModel.setNickName(video.getNickName());
                shareVideoModel.setTitle(video.getTitle());
                shareVideoModel.setSecret(video.getSecret());
                shareVideoModel.setUrl(video.getUrl());
                shareVideoModel.setImage(video.getImage());
            }
            return R.ok(shareVideoModel);
        } catch (Exception e) {
            return R.error(-1, Constant.GET_DATA_FAIL);
        }
    }
}
