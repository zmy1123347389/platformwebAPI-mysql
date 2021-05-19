package com.behere.video.controller;

import com.behere.common.annotation.Log;
import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.*;
import com.behere.video.domain.*;
import com.behere.video.model.UserModel;
import com.behere.video.model.VideoInfoModel;
import com.behere.video.model.VideoModel;
import com.behere.video.service.UserService;
import com.behere.video.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Behere
 */
@Controller
@RequestMapping(value = "api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private UserService userService;
    @Autowired
    private FTPConfig ftpConfig;

    @Log("保存用户上传视频路径")
    @PostMapping("/saveVideo")
    @ResponseBody
    public R saveVideo(Video video, @RequestParam("file") MultipartFile file) {
        try {
            String picNewName = ImageUtils.uploadImage(file, ftpConfig);
            video.setImage(picNewName);
            int result = videoService.saveVideo(video);
            if (result == Constant.FAIL) {
                return R.error();
            }
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查询视频列表")
    @PostMapping("/queryVideos")
    @ResponseBody
    public R queryVideos(int secret,
                         @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {

        try {
            String shareUrl = userService.queryShareUserConfig(0);
            PageHelper.startPage(pageNum, pageSize);
            List<VideoModel> videos = videoService.queryVideos(secret);
            userService.setVideoShareUrl(videos, shareUrl);
            PageInfo<VideoModel> pageInfo = new PageInfo<VideoModel>(videos);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查询私密视频列表")
    @PostMapping("/querySecretVideos")
    @ResponseBody
    public R querySecretVideos(int secret, long userId,
                               @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                               @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<VideoModel> videos = videoService.querySecretVideos(secret);
            videoService.isWatchedVideo(videos, userId);
            PageInfo<VideoModel> pageInfo = new PageInfo<VideoModel>(videos);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查询视频信息")
    @PostMapping("/queryVideoByVideoId")
    @ResponseBody
    public R queryVideoByVideoId(String videoId) {
        try {
            VideoInfoModel video = videoService.queryVideoByVideoId(videoId);
            return R.ok(video);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查询私密视频信息")
    @PostMapping("/querySecretVideoByVideoId")
    @ResponseBody
    public R querySecretVideoByVideoId(String videoId, long userId) {
        try {
            WatchVideoRecord watchVideoRecord = videoService.queryWatchVideoRecord(videoId, userId);
            VideoInfoModel video = videoService.queryVideoByVideoId(videoId);
            String shareUrl = userService.queryShareUserConfig(0);
            video.setShareUrl(shareUrl);
            if (video.getUserId() == userId) {
                return R.ok(video);
            }
            if (watchVideoRecord == null) {
                UserModel user = userService.queryUserById(userId);
                if (videoService.watchSecretVideo(user, video) == 0) {
                	return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
                }
              //  String msg = user.getNickName() + "观看了您的私密视频（" + video.getTitle() + "），+" + video.getFlower() / 2 + "鲜花，可在“我的收益-明细”查看";
                String msg = NeteaseUtils.setMsgExtMap(user.getId(), null, user.getNickName(), null, Long.valueOf(video.getFlower() / 2), MsgConstant.SCRET_VIDEO, video.getTitle());
                NeteaseUtils.sendMsg(msg, video.getUserId());
            }
            return R.ok(video);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("点赞或取消点赞")
    @PostMapping("/likeVideo")
    @ResponseBody
    public R likeVideo(long userId, String videoId) {
        LikeResult likeResult = new LikeResult();
        try {
            VideoLike videoLike = videoService.queryVideoLikeByUserIdWithVideoId(userId, videoId);
            UserModel user = userService.queryUserById(userId);
            VideoInfoModel videoModel = videoService.queryVideoByVideoId(videoId);
            //String msg = user.getNickName() + "点赞了您的视频（" + videoModel.getTitle() + "）";
            String msg = NeteaseUtils.setMsgExtMap(user.getId(), null, user.getNickName(), null, null, MsgConstant.LIKE_VIDEO, videoModel.getTitle());
            if (videoLike == null) {
                videoService.likeVideo(StringUtils.getUUID(), videoId, userId);
                likeResult.setIsLike(Constant.IS_LIKE);
                NeteaseUtils.sendMsg(msg, videoModel.getUserId());
            } else {
                if (videoLike.getIsLike() == Constant.IS_LIKE) {
                    videoService.updateLikeVideo(Constant.NOT_LIKE, userId, videoId);
                    likeResult.setIsLike(Constant.NOT_LIKE);
                } else {
                    videoService.updateLikeVideo(Constant.IS_LIKE, userId, videoId);
                    likeResult.setIsLike(Constant.IS_LIKE);
                    NeteaseUtils.sendMsg(msg, videoModel.getUserId());
                }
            }
            return R.ok(likeResult);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("评论视频")
    @PostMapping("/comentVideo")
    @ResponseBody
    public R comentVideo(VideoComment videoComment) {
        try {
            videoComment.setId(StringUtils.getUUID());
            int row = videoService.saveVideoComment(videoComment);
            if (row > 0) {
                return R.ok();
            } else {
                return R.error();
            }
        } catch (Exception e) {
            return R.error();
        }
    }



    @Log("查询视频点赞和留言数量")
    @PostMapping("/queryVideoLikeAndComment")
    @ResponseBody
    public R queryVideoLikeAndComment(String videoId) {
        try {
            Map<String, Long> map = new HashMap<String, Long>();
            long likes = videoService.queryVideoLikes(videoId);
            long comments = videoService.queryVideoCommentNumber(videoId);
            long harvest = videoService.queryVideoHarvest(videoId);
            map.put("likes", likes);
            map.put("comments", comments);
            map.put("harvest", harvest);
            return R.ok(map);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("查看评论内容")
    @PostMapping("/queryVideoComments")
    @ResponseBody
    public R queryVideoComments(String videoId, long userId,
                                @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<VideoComment> videoComments = videoService.queryVideoComments(videoId, 0L);
            for (VideoComment comment : videoComments) {
                comment.setCreateAt(DateUtils.getTimeBefore(comment.getCreateTime()));
            }
            PageInfo<VideoComment> pageInfo = new PageInfo<VideoComment>(videoComments);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("是否点过赞")
    @PostMapping("/isLike")
    @ResponseBody
    public R isLike(String videoId, long userId) {
        try {
            VideoLike videoLike = videoService.queryVideoLikeByUserIdWithVideoId(userId, videoId);
            Map<String, Integer> result = new HashMap<String, Integer>();
            if (videoLike != null && videoLike.getIsLike() == Constant.IS_LIKE) {
                result.put("isLike", 1);
            } else {
                result.put("isLike", 0);
            }
            return R.ok(result);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("转发")
    @PostMapping("/transmit")
    @ResponseBody
    public R transmit(String videoId) {
        try {
            videoService.updateVideoTransmitNum(videoId);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("是否观看过视频")
    @PostMapping("/isWatchVideo")
    @ResponseBody
    public R isWatchVideo(String videoId, long userId) {
        try {
            Map<String ,Integer> map = new HashMap<String, Integer>();
            map.put("status", Constant.NOT_WATCHED);
            WatchVideoRecord watchVideoRecord = videoService.queryWatchVideoRecord(videoId, userId);
            VideoInfoModel video = videoService.queryVideoByVideoId(videoId);
            if (video.getUserId() == userId || watchVideoRecord != null) {
                map.put("status", Constant.ALREADY_WATCHED);
            }
            return R.ok(map);
        } catch (Exception e) {
            return R.error();
        }
    }


    @Log("删除视频")
    @PostMapping("/deleteVideoById")
    @ResponseBody
    public R deleteVideoById(String videoId) {
        try {
            int result = videoService.deleteVideoById(videoId);
            return R.ok();
        } catch (Exception e) {
            return R.error();
        }
    }
}