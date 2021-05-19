package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.Video;
import com.behere.video.domain.VideoComment;
import com.behere.video.domain.VideoLike;
import com.behere.video.domain.WatchVideoRecord;
import com.behere.video.model.UserModel;
import com.behere.video.model.VideoInfoModel;
import com.behere.video.model.VideoModel;

/**
 * @author: Behere
 */
public interface VideoService {

    /**
     * 上传用户短视频
     * @param video
     * @return
     */
    int saveVideo(Video video);

    /**
     * 视频点赞
     * @param id
     * @param videoId
     * @param userId
     * @return
     */
    int likeVideo(String id, String videoId, long userId);


    /**
     * 点赞或取消点赞
     * @param isLike
     * @param userId
     * @param videoId
     * @return
     */
    int updateLikeVideo(int isLike, long userId, String videoId);

    /**
     * 查询用户是否为视频点过赞
     * @param userId
     * @param videoId
     * @return
     */
    VideoLike queryVideoLikeByUserIdWithVideoId(long userId, String videoId);

    /**
     * 保存视频评论
     * @param videoComment
     * @return
     */
    int saveVideoComment(VideoComment videoComment);

    /**
     * 查询视频列表
     * @param secret 0普通 1私密
     */
    List<VideoModel> queryVideos(int secret);

    /**
     * 通过视频ID查询视频信息
     * @param videoId
     * @return
     */
    VideoInfoModel queryVideoByVideoId(String videoId);

    /**
     * 统计视频点赞数
     * @param videoId
     * @return
     */
    long queryVideoLikes(String videoId);

    /**
     * 统计视频评论数
     * @param videoId
     * @return
     */
    long queryVideoCommentNumber(String videoId);

    /**
     * 查看视频评论
     * @param videoId
     * @param userId
     * @return
     */
    List<VideoComment> queryVideoComments(String videoId, long userId);

    /**
     * 通过视频ID和用户ID查找观看记录
     * @param videoId
     * @param userId
     * @return
     */
    WatchVideoRecord queryWatchVideoRecord(String videoId, long userId);

    /**
     * 保存观看记录
     * @param watchVideoRecord
     * @return
     */
    int saveWatchVideoRecord(WatchVideoRecord watchVideoRecord);

    int watchSecretVideo(UserModel user, VideoInfoModel video);

    /**
     * 查询视频列表
     * @param secret 0普通 1私密
     */
    List<VideoModel> querySecretVideos(int secret);

    void isWatchedVideo(List<VideoModel> videos, long userId);

    /**
     * 转发计数
     * @param videoId
     * @return
     */
    int updateVideoTransmitNum(String videoId);

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    int deleteVideoById(String videoId);

    /**
     * 通过视频ID查询鲜花数
     * @param videoId
     * @return
     */
    long queryVideoHarvest(String videoId);
}