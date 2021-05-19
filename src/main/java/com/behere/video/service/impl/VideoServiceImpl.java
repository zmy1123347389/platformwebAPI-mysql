package com.behere.video.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.constant.Constant;
import com.behere.common.utils.CheckSumBuilder;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.TimeUtils;
import com.behere.common.utils.UploadVideoUtil;
import com.behere.video.dao.VideoDao;
import com.behere.video.domain.Video;
import com.behere.video.domain.VideoComment;
import com.behere.video.domain.VideoLike;
import com.behere.video.domain.WatchVideoRecord;
import com.behere.video.model.UserModel;
import com.behere.video.model.VideoInfoModel;
import com.behere.video.model.VideoModel;
import com.behere.video.service.UserService;
import com.behere.video.service.VideoService;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Behere
 */
@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoDao videoDao;
    @Autowired
    private UserService userService;

    @Override
    public int saveVideo(Video video) {
        try {
            video.setId(StringUtils.getUUID());
            String result = UploadVideoUtil.getNeteaseVideoInformation(video.getVid());
            JSONObject json = JSONObject.parseObject(result);
            JSONObject ret = JSONObject.parseObject(json.get("ret").toString());
            video.setUrl(ret.get("origUrl").toString());
            return videoDao.saveVideo(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constant.FAIL;
    }

    @Override
    public int likeVideo(String id, String videoId, long userId) {
        return videoDao.likeVideo(id, videoId, userId);
    }

    @Override
    public int updateLikeVideo(int isLike, long userId, String videoId) {
        return videoDao.updateLikeVideo(isLike, userId, videoId);
    }

    @Override
    public VideoLike queryVideoLikeByUserIdWithVideoId(long userId, String videoId) {
        return videoDao.queryVideoLikeByUserIdWithVideoId(userId, videoId);
    }

    @Override
    public int saveVideoComment(VideoComment videoComment) {
        return videoDao.saveVideoComment(videoComment);
    }

    @Override
    public List<VideoModel> queryVideos(int secret) {
        return videoDao.queryVideos(secret);
    }

    @Override
    public VideoInfoModel queryVideoByVideoId(String videoId) {
        return videoDao.queryVideoByVideoId(videoId);
    }

    @Override
    public long queryVideoLikes(String videoId) {
        return videoDao.queryVideoLikes(videoId);
    }

    @Override
    public long queryVideoCommentNumber(String videoId) {
        return videoDao.queryVideoCommentNumber(videoId);
    }

    @Override
    public List<VideoComment> queryVideoComments(String videoId, long userId) {
        return videoDao.queryVideoComments(videoId, userId);
    }

    @Override
    public WatchVideoRecord queryWatchVideoRecord(String videoId, long userId) {
        return videoDao.queryWatchVideoRecord(videoId, userId);
    }

    @Override
    public int saveWatchVideoRecord(WatchVideoRecord watchVideoRecord) {
        return videoDao.saveWatchVideoRecord(watchVideoRecord);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int watchSecretVideo(UserModel user, VideoInfoModel video) {
        try {
            String businessId = StringUtils.getUUID();
            WatchVideoRecord watchVideoRecord = new WatchVideoRecord();
            watchVideoRecord.setId(businessId);
            watchVideoRecord.setUserId(user.getId());
            watchVideoRecord.setVideoId(video.getId());
            int status = userService.saveDealInformation(user.getId(), video.getUserId(), video.getFlower(), Constant.SHORT_VIDEO, businessId);
            if (status == Constant.SUCCESS) {
            	videoDao.saveWatchVideoRecord(watchVideoRecord);
            	return Constant.SUCCESS;
            } else {
            	return Constant.FAIL;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<VideoModel> querySecretVideos(int secret) {
        return videoDao.querySecretVideos(secret);
    }

    @Override
    public void isWatchedVideo(List<VideoModel> videos, long userId) {
        for (VideoModel video : videos) {
            video.setWhenTime(TimeUtils.format(video.getCreateTime()));
            WatchVideoRecord watchVideoRecord = queryWatchVideoRecord(video.getId(), userId);
            if (watchVideoRecord != null || video.getUserId() == userId) {
                video.setIsWatched(1);
            }
        }
    }

    @Override
    public int updateVideoTransmitNum(String videoId) {
        return videoDao.updateVideoTransmitNum(videoId);
    }

    @Override
    public int deleteVideoById(String videoId) {
        return videoDao.deleteVideoById(videoId);
    }

    @Override
    public long queryVideoHarvest(String videoId) {
        return videoDao.queryVideoHarvest(videoId);
    }
}