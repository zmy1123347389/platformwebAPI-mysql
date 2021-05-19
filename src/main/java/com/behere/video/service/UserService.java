package com.behere.video.service;

import java.util.List;

import com.behere.video.domain.*;
import com.behere.video.model.*;

public interface UserService {

	/**
	 * 根据手机号查询用户
	 * @param mobile
	 * @return
	 */
    List<User> queryUserByMobile(String mobile);

	/**
	 * 根据手机号和密码查询用户
	 * @param mobile
	 * @param password
	 * @return
	 */
	UserModel queryUserByMobileAndPassword(String mobile, String password);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户头像
     */
    void updateUserHeadPortrait(String headPortrait, Long userId);

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
	void updateWechatQRcode(long userId, String webchatCode);
	
	/**
	 * 通过昵称查询用户
	 * @param nickName
	 * @return
	 */
	List<User> queryUserByNickName(String nickName);
	
	/**
	 * 通过昵称判断用户是否已经存在
	 * @param nickName
	 * @return
	 */
	boolean isExist(String nickName);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	void updateUserInfoById(User user) throws Exception;
	
	/**
	 * 通过用户ID修改用户性别
	 * @param userId
	 * @param gender
	 */
	void updateUserGenderById(long userId, short gender);

	/**
	 * 调用"保存用户"和"创建网易云账户"接口
	 * @param user
	 */
	void saveUserWithNetesaToken(User user);

	/**
	 * 更新用户网易云信token
	 * @param neteaseToken
	 * @param userId
	 * @return
	 */
	int updateUserNeteaseToken(String neteaseToken, long userId);

	/**
	 *   修改密码
	 * @param password
	 * @param mobile
	 * @return
	 */
	int updatePassword(String password, String mobile);


	/**
	 * 修改服务价格
	 * @param servicePrice
	 * @param userId
	 * @return
	 */
	int updateServicePrice(int servicePrice, long userId);


	/**
	 * 根据性别查找用户 0未知  1 男  2 女  -1查询所有
	 * @param gender
	 * @return
	 */
	List<UserSearchModel> queryUsersByGender(short gender);

	/**
	 * 通过ID或者昵称查找用户
	 * @param queryParam
	 * @return
	 */
	List<UserSearchModel> likeUserByNickName(QueryParam queryParam);

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
	 * 解锁微信二维码
	 * @param unlock
	 */
	int unlockWechat(Unlock unlock);

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
	List<User> isExsitNickName(String nickName, long userId);

	/**
	 * 更新用户身份证图片
	 * @param identityCard
	 * @param userId
	 */
	void updateUserIdentityCard(String identityCard, Long userId);

	/**
	 * 更新认证 封面头像
	 * @param indexImage
	 * @param userId
	 * @return
	 */
	int updateUserIndexImage(String indexImage, long userId);

	/**
	 * 更新视频认证
	 * @param authVideo
	 * @return
	 */
	int updateUserAuthVideo(AuthVideo authVideo);

	/**
	 * 获取用户认证信息
	 * @param userId
	 * @return
	 */
	UserAuthModel queryUserAuth(long userId);

	/**
	 * 提交用户认证信息
	 * @param userId
	 * @return
	 */
	int saveUserAuth(long userId);

	/**
	 * 查询用户首页资料
	 * @param userId
	 * @return
	 */
	UserIndex queryUserIndex(long userId);

	/**
	 * 查询用户首页守护者前三
	 * @param userId
	 * @return
	 */
	List<UserIndexWatch> queryUserIndexWatch(long userId);

	/**
	 * 查询用户首页标签
	 * @param userId
	 * @return
	 */
	List<UserIndexLabel> queryUserIndexLabel(long userId);

	/**
	 * 查询用户首页视频
	 * @param userId
	 * @return
	 */
	List<VideoModel> queryUserIndexVideo(long userId);

	/**
	 * 统计守护者数量
	 * @param userId
	 * @return
	 */
	int countUserWatch(long userId);

	/**
	 * 统计用户评分
	 * @param userId
	 * @return
			 */
	UserIndexScore countUserIndexScore(long userId);

	/**
	 * 更新网易云信用户头像
	 * @param accid
	 * @param icon
	 */
	String updateNeteaseUserIcon(String accid, String icon) throws Exception;

	/**
	 * 上传用户照片
	 * @param id
	 * @param picUrl
	 * @param userId
	 * @return
	 */
	int saveUserPic(String id, String picUrl,  long userId);

	/**
	 * 获取用户照片
	 * @param userId
	 * @return
	 */
	List<UserPic> queryUserPics(long userId);

	/**
	 * 删除用户照片
	 * @param picId
	 * @return
	 */
	int deleteUserPic(String picId);

	/**
	 * 保存交易信息
	 * @param fromUser
	 * @param toUser
	 * @param flower
	 * @param dealType
	 * @return
	 */
	int saveDealInformation(long fromUser, long toUser, long flower, int dealType, String businessId);

	/**
	 * 通过用户ID查询鲜花
	 * @param userId
	 * @return
	 */
	double queryMyFlower(long userId);

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
	List<OnlineUser> queryOnlineUser(List<String> list, int gender, int auth, int offset, int limit);

    List<VideoModel> queryUserVideos(long userId);

	/**
	 * 通过unionId获取用户信息
	 * @param unionId
	 * @return
	 */
	UserModel queryUserByUnionId(String unionId);

	int saveOpenUser(WxLogin wxLogin);

    /**
     * 修改用户昵称
     * @param nickName
     * @param userId
     * @return
     */
    int updateUserNickName(String nickName, long userId);

	/**
	 * 根据用户ID查询最近的充值记录
	 * @param userId
	 * @return
	 */
	RechargeRecord queryNewestRechargeReocrdByUserId(long userId);

	void isVip(UserModel userModel);

    int updateUserOnline(Disturb disturb);

	List<InvitationUser> queryInvitationUsers(String invitationCode);

	String queryShareUserConfig(int type);

	void setVideoShareUrl(List<VideoModel> videos, String shareUrl);

    List<UserSearchModel> queryRecommendUsers(QueryParam queryParam);

    int updateUserOnOrOffOnline(String userId, int online);

    List<OnlineUser> queryUsers(int auth, int gender);

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
    List<FlowerRank> queryFlowerRankingList(long toUser);

    /**
     * 获取鲜花榜前三名
     * @param toUser
     * @return
     */
    List<FlowerRank> queryTopThreeFlowerRanking(long toUser);

	/**
	 * 获取最佳好友（守护鲜花最高）
	 * @param toUser
	 * @return
	 */
	List<BestFriend> queryBestFriend(long toUser);

    /**
     * 通过自己的用户ID和其他用户的ID查询为其他用户消费的鲜花
     * @param fromUser
     * @param toUser
     * @param type
     * @return
     */
    Integer queryFlowerRecordByMyIdWithOtherUserId(long fromUser, long toUser, int type);

    /**
     * PK榜个人信息
     * @param userId
     * @return
     */
    BestFriend queryMyPkInformation(long userId);

    /**
     * 行级锁
     * @param fromUser
     * @param toUser
     * @return
     */
	List<UserModel> lockUser(long fromUser, long toUser);

	/**
	 * 修改用户认证状态
	 * @param userAuth
	 * @param userId
	 * @return
	 */
	int updateUserAuth(int userAuth, long userId);

    /**
     * 查询用户提交的认证信息
     * @param userId
     * @return
     */
    List<UserAuth> queryUserAuths(long userId);

	/**
	 *
	 * @param user
	 * @return
	 */
	int updateUserOnlineStatus(UserModel user);

	long queryTotalFlower(long userId);

	/**
	 * 设置离线状态
	 * @param userId
	 */
	void setUserOfflineTime(long userId);

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
	String queryGiftNameByBusinessId(String businessId);

	/**
	 * 通过businessId获取短视频标题
	 * @param businessId
	 * @return
	 */
	String queryShortVideoTitleByBusinessId(String businessId);

    /**
     * 通过邀请码查找用户
     * @param invitationCode
     * @return
     */
    UserModel queryUserByMyinvitation(String invitationCode);

    void addSharingUserFlower(long userId, long flower);

    long countMyInvitationUser(String invitationCode, int type);

    double countSharingUserContributionValue(String invitationCode);

    /**
     * 查询认证用户贡献值
     * @param invitationCode
     * @return
     */
    double countSharingAuthUserContributionValue(String invitationCode);

    /**
     * 邀请用户列表
     * @return
     */
    List<ShareUser> listSharingAuthUser(String invitationCode);

    /**
     * 邀请认证用户列表
     * @return
     */
    List<ShareUser> listSharingUserRecharge(String invitationCode);

    /**
     * 更新通话/空闲状态
     * @param customerId
     * @param serviceId
     * @param status
     */
    void updateUserFaceTime(long customerId, long serviceId, int status);

    /**
     * 记录免费聊天
     * @param writingChat
     */
    void saveWritingChatRecord(WritingChat writingChat);

    /**
     * 统计免费聊天条数
     * @param writingChat
     * @return
     */
    long countWritingChatRecord(WritingChat writingChat);
}