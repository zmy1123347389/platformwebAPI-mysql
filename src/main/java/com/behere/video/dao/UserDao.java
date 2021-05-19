package com.behere.video.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.behere.video.domain.*;
import com.behere.video.model.*;

@Mapper
public interface UserDao {

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
    List<User> queryUserByMobile(String mobile);

	/**
	 * 通过手机号和密码查找用户
	 * @param mobile
	 * @param password
	 * @return
	 */
	UserModel queryUserByMobileAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户头像
     */
    void updateUserHeadPortrait(@Param("headPortrait") String headPortrait, @Param("userId") Long userId);

	/**
	 * 更新用户身份证
	 * @param identityCard
	 * @param userId
	 */
	void updateUserIdentityCard(@Param("identityCard") String identityCard, @Param("userId") Long userId);

    /**
     * 通过用户ID获取用户信息
     * @param userId
     * @return
     */
	UserModel queryUserById(Long userId);
	
	/**
	 * 更新用户微信二维码
	 * @param userId
	 * @param webchatCode
	 */
	void updateWechatQRcode(@Param("userId") long userId, @Param("wechatCode") String webchatCode);
	
	/**
	 * 通过昵称查询用户
	 * @param nickName
	 * @return
	 */
	List<User> queryUserByNickName(@Param("nickName") String nickName);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUserInfoById(User user);
	
	/**
	 * 通过用户ID修改用户性别
	 * @param userId
	 * @param gender
	 */
	void updateUserGenderById(@Param("userId") long userId, @Param("gender") short gender);

	/**
	 * 更新用户网易云信token
	 * @param neteaseToken
	 * @param userId
	 * @return
	 */
	int updateUserNeteaseToken(@Param("neteaseToken") String neteaseToken, @Param("userId") long userId);

	/**
	 *   修改密码
	 * @param password
	 * @param mobile
	 * @return
	 */
	int updatePassword(@Param("password") String password, @Param("mobile") String mobile);

	/**
	 * 修改服务价格
	 * @param servicePrice
	 * @param userId
	 * @return
	 */
	int updateServicePrice(@Param("servicePrice") int servicePrice, @Param("userId") long userId);

	/**
	 * 更新用户vip状态
	 * @param vip
	 * @param userId
	 * @return
	 */
	int updateUserVip(@Param("vip") short vip, @Param("userId") long userId);

	/**
	 * 根据性别查找用户 0未知  1 男  2 女  -1查询所有
	 * @param gender
	 * @return
	 */
	List<UserSearchModel> queryUsersByGender(@Param("gender") short gender);

	/**
	 * 通过昵称查找用户
	 * @param queryParam
	 * @return
	 */
	List<UserSearchModel> likeUserByNickName(QueryParam queryParam);

	/**
	 * 查询所有用户
	 * @return
	 */
	List<UserModel> queryAllUsers();

	/**
	 * 通过userId增加余额、钻石、鲜花
	 * balance 余额
	 * diamond 钻石
	 * flower  鲜花
	 * @param userId
	 * @param num
	 * @return
	 */
	int addFlower(@Param("userId") long userId, @Param("num") long num);

	int reduceFlower(@Param("userId") long userId, @Param("num") double num);

	/**
	 * 通过userId扣除余额、钻石、鲜花
	 * @param userId
	 * @param num
	 * @return
	 */
	int reduceBalance(@Param("userId") long userId, @Param("num") long num);

	int addBalance(@Param("userId") long userId, @Param("num") long num);

	/**
	 * 保存解锁信息
	 * @param unlock
	 * @return
	 */
	int saveLockInformation(Unlock unlock);

	/**
	 * 查询解锁微信条件
	 * @return
	 */
	UnlockWechatPrice queryUnlockWechatPrice();

	/**
	 * 通过解锁用户ID和被解锁用户ID查询解锁信息
	 * @param unlock
	 * @return
	 */
	List<Unlock> queryUnlock(Unlock unlock);

	/**
	 * 除了自己是否有重名昵称
	 * @param nickName
	 * @param userId
	 * @return
	 */
	List<User> isExsitNickName(@Param("nickName") String nickName, @Param("userId") long userId);

	/**
	 * 更新认证 封面头像
	 * @param indexImage
	 * @param userId
	 * @return
	 */
	int updateUserIndexImage(@Param("indexImage") String indexImage, @Param("userId") long userId);

	/**
	 * 更新用户认证视频地址
	 * @param authVideo
	 * @param userId
	 * @return
	 */
	int updateUserAuthVideo(@Param("authVideo") String authVideo, @Param("userId") long userId);


	/**
	 * 获取用户认证信息
	 * @param userId
	 * @return
	 */
	UserAuthModel queryUserAuth(@Param("userId") long userId);

	/**
	 * 提交用户认证信息
	 * @param userId
	 * @return
	 */
	int saveUserAuth(@Param("userId") long userId);

	/**
	 * 查询用户首页资料
	 * @param userId
	 * @return
	 */
	UserIndex queryUserIndex(@Param("userId") long userId);

	/**
	 * 查询用户首页守护者前三
	 * @param userId
	 * @return
	 */
	List<UserIndexWatch> queryUserIndexWatch(@Param("userId") long userId);

	/**
	 * 查询用户首页标签
	 * @param userId
	 * @return
	 */
	List<UserIndexLabel> queryUserIndexLabel(@Param("userId") long userId);

	/**
	 * 查询用户首页视频
	 * @param userId
	 * @return
	 */
	List<VideoModel> queryUserIndexVideo(@Param("userId") long userId);

	/**
	 * 统计守护者数量
	 * @param userId
	 * @return
	 */
	int countUserWatch(@Param("userId") long userId);

	/**
	 * 统计用户评分
	 * @param userId
	 * @return
	 */
	UserIndexScore countUserIndexScore(@Param("userId") long userId);

	/**
	 * 上传用户照片
	 * @param id
	 * @param picUrl
	 * @param userId
	 * @return
	 */
	int saveUserPic(@Param("id") String id, @Param("picUrl") String picUrl, @Param("userId") long userId);


	/**
	 * 获取用户照片
	 * @param userId
	 * @return
	 */
	List<UserPic> queryUserPics(@Param("userId") long userId);

	/**
	 * 删除用户照片
	 * @param picId
	 * @return
	 */
	int deleteUserPic(@Param("picId") String picId);

	/**
	 * 通过用户ID查询鲜花
	 * @param userId
	 * @return
	 */
	double queryMyFlower(@Param("userId") long userId);

	/**
	 * 通过用户ID获取收益明细
	 * @param userFlower
	 * @return
	 */
	List<AccountBook> queryMyEarningsDetails(UserFlower userFlower);

	/**
	 * 通过多个userid获取用户信息
	 * @param list
	 * @return
	 */
	List<OnlineUser> queryOnlineUser(@Param("list") List<String> list, @Param("gender") int gender, @Param("auth") int auth, @Param("offset") int offset, @Param("limit") int limit);
	List<OnlineUser> tempQueryOnlineUser(@Param("list") List<String> list, @Param("gender") int gender, @Param("auth") int auth, @Param("offset") int offset, @Param("limit") int limit);

	List<VideoModel> queryUserVideos(@Param("userId") long userId);

	/**
	 * 通过unionId获取用户信息
	 * @param unionId
	 * @return
	 */
	UserModel queryUserByUnionId(@Param("unionId") String unionId);

	/**
	 * 保存第三方登录用户
	 * @param wxLogin
	 */
	void saveOpenUser(WxLogin wxLogin);

    /**
     * 修改用户昵称
     * @param nickName
     * @param userId
     * @return
     */
	int updateUserNickName(@Param("nickName") String nickName, @Param("userId") long userId);

	/**
	 * 根据用户ID查询最近的充值记录
	 * @param userId
	 * @return
	 */
	RechargeRecord queryNewestRechargeReocrdByUserId(@Param("userId") long userId);

	int updateUserOnline(Disturb disturb);

	/**
	 * 通过邀请码获取用户信息
	 * @param invitationCode
	 * @return
	 */
	UserModel queryUserByInvitationCode(@Param("invitationCode") String invitationCode);

	List<InvitationUser> queryInvitationUsers(@Param("invitationCode") String invitationCode);

	String queryShareUserConfig(@Param("type") int type);

	List<UserSearchModel> queryRecommend(@Param("list") List<String> userIds, @Param("gender") int gender, @Param("auth") int auth);

	int updateUserOnOrOffOnline(@Param("userId") String userId, @Param("online") int online);

	List<OnlineUser> queryUsers(@Param("auth") int auth, @Param("gender") int gender);

	/**
	 * 保存消费流水账本
	 * @param accountBook
	 * @return
	 */
	int saveAccountBook(AccountBook accountBook);

	/**
	 * 鲜花榜
	 * @param toUser
	 * @return
	 */
	List<FlowerRank> queryFlowerRankingList(@Param("toUser") long toUser);
	List<FlowerRank> queryTopThreeFlowerRanking(@Param("toUser") long toUser);

	/**
	 * 获取最佳好友（守护鲜花最高）
	 * @param toUser
	 * @return
	 */
	List<BestFriend> queryBestFriend(@Param("toUser") long toUser);

    /**
     * 通过自己的用户ID和其他用户的ID查询为其他用户消费的鲜花
     * @param fromUser
     * @param toUser
     * @param type
     * @return
     */
	Integer queryFlowerRecordByMyIdWithOtherUserId(@Param("fromUser") long fromUser, @Param("toUser") long toUser, @Param("type") int type);

    /**
     * PK榜个人信息
     * @param userId
     * @return
     */
	BestFriend queryMyPkInformation(@Param("userId") long userId);

    /**
     * 行级锁
     * @param fromUserId
     * @param toUser
     * @return
     */
	List<UserModel> lockUser(@Param("fromUser") long fromUserId, @Param("toUser") long toUser);

	/**
	 * 修改用户认证状态
	 * @param userAuth
	 * @param userId
	 * @return
	 */
	int updateUserAuth(@Param("userAuth") int userAuth, @Param("userId") long userId);

    /**
     * 查询用户提交的认证信息
     * @param userId
     * @return
     */
	List<UserAuth> queryUserAuths(@Param("userId") long userId);

    int updateAccountBookByBusinessId(@Param("flower") long flower, @Param("businessId") String businessId);

    long queryTotalFlower(@Param("userId") long userId);

	/**
	 * 设置离线状态
	 * @param userId
	 */
	void setUserOfflineTime(@Param("userId") long userId);

    /**
     * 用户主页鲜花
     * @param userIndex
     * @return
     */
	long queryUserIndexFlower(UserIndex userIndex);

	/**
	 * 通过businessId获取礼物名称
	 * @param businessId
	 * @return
	 */
	String queryGiftNameByBusinessId(@Param("businessId") String businessId);
	/**
	 * 通过businessId获取短视频标题
	 * @param businessId
	 * @return
	 */
	String queryShortVideoTitleByBusinessId(@Param("businessId") String businessId);

    /**
     * 通过邀请码查找用户
     * @param invitationCode
     * @return
     */
    UserModel queryUserByMyinvitation(@Param("invitationCode") String invitationCode);

    /**
     * 统计邀请用户数量
     * @param invitationCode
     * @return
     */
    long countMyInvitationUser(@Param("invitationCode") String invitationCode, @Param("type") int type);

    /**
     * 查询普通用户贡献值
     * @param invitationCode
     * @return
     */
    double countSharingUserContributionValue(@Param("invitationCode") String invitationCode);

    /**
     * 查询认证用户贡献值
     * @param invitationCode
     * @return
     */
    double countSharingAuthUserContributionValue(@Param("invitationCode") String invitationCode);

    /**
     * 邀请用户列表
     * @return
     */
    List<ShareUser> listSharingAuthUser(@Param("invitationCode") String invitationCode);

    /**
     * 邀请认证用户列表
     * @return
     */
    List<ShareUser> listSharingUserRecharge(@Param("invitationCode") String invitationCode);

    void updateUserFaceTime(@Param("customerId") long customerId, @Param("serviceId") long serviceId, @Param("status") int status);

    void saveWritingChatRecord(WritingChat writingChat);

    long countWritingChatRecord(WritingChat writingChat);
}