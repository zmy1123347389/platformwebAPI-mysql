package com.behere.video.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.Video;
import com.behere.video.domain.VideoComment;
import com.behere.video.domain.VideoLike;
import com.behere.video.domain.WatchVideoRecord;
import com.behere.video.model.VideoInfoModel;
import com.behere.video.model.VideoModel;

import java.util.List;

/**
 * @author: Behere
 */
@Mapper
public interface VideoDao {

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
    int likeVideo(@Param("id") String id, @Param("videoId") String videoId, @Param("userId") long userId);


    /**
     * 点赞或取消点赞
     * @param isLike
     * @param userId
     * @param videoId
     * @return
     */
	int updateLikeVideo(@Param("isLike") int isLike, @Param("userId") long userId, @Param("videoId") String videoId);

    /**
     * 查询用户是否为视频点过赞
     * @param userId
     * @param videoId
     * @return
     */
	VideoLike queryVideoLikeByUserIdWithVideoId(@Param("userId") long userId, @Param("videoId") String videoId);

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
    List<VideoModel> queryVideos(@Param("secret") int secret);

    /**
     * 通过视频ID查询视频信息
     * @param videoId
     * @return
     */
    VideoInfoModel queryVideoByVideoId(@Param("videoId") String videoId);

    /**
     * 统计视频点赞数
     * @param videoId
     * @return
     */
    long queryVideoLikes(@Param("videoId") String videoId);

    /**
     * 统计视频评论数
     * @param videoId
     * @return
     */
	long queryVideoCommentNumber(@Param("videoId") String videoId);

    /**
     * 查看视频评论
     * @param videoId
     * @param userId
     * @return
     */
    List<VideoComment> queryVideoComments(@Param("videoId") String videoId, @Param("userId") long userId);

    /**
     * 通过视频ID和用户ID查找观看记录
     * @param videoId
     * @param userId
     * @return
     */
    WatchVideoRecord queryWatchVideoRecord(@Param("videoId") String videoId, @Param("userId") long userId);

    /**
     * 保存观看记录
     * @param watchVideoRecord
     * @return
     */
    int saveWatchVideoRecord(WatchVideoRecord watchVideoRecord);

    /**
     * 查询私密视频列表
     * @param secret 0普通 1私密
     */
    List<VideoModel> querySecretVideos(@Param("secret") int secret);

    /**
     * 转发计数
     * @param videoId
     * @return
     */
    int updateVideoTransmitNum(@Param("videoId") String videoId);

    /**
     * 删除视频
     * @param videoId
     * @return
     */
    int deleteVideoById(@Param("videoId") String videoId);

    /**
     * 通过视频ID查询鲜花数
     * @param videoId
     * @return
     */
    long queryVideoHarvest(@Param("videoId") String videoId);
}