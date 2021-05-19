package com.behere.video.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;
import com.behere.common.utils.NeteaseUtils;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.TimeUtils;
import com.behere.common.utils.UploadVideoUtil;
import com.behere.video.dao.UserDao;
import com.behere.video.domain.*;
import com.behere.video.model.*;
import com.behere.video.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    RedisUtil redisUtil;

    @Override
    public List<User> queryUserByMobile(String mobile) {
        return userDao.queryUserByMobile(mobile);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

	@Override
	public UserModel queryUserByMobileAndPassword(String mobile, String password) {
		return userDao.queryUserByMobileAndPassword(mobile, password);
	}

	@Override
	public void updateUserHeadPortrait(String headPortrait, Long userId) {
		userDao.updateUserHeadPortrait(headPortrait, userId);
	}

	@Override
	public UserModel queryUserById(Long userId) {
		return userDao.queryUserById(userId);
	}

	@Override
	public void updateWechatQRcode(long userId, String webchatCode) {
		userDao.updateWechatQRcode(userId, webchatCode);
	}

	@Override
	public List<User> queryUserByNickName(String nickName) {
		return userDao.queryUserByNickName(nickName);
	}

	@Override
	public boolean isExist(String nickName) {
		return queryUserByNickName(nickName).isEmpty() ? false : true;
	}

	@Override
	public void updateUserInfoById(User user) throws Exception {
		userDao.updateUserInfoById(user);
		NeteaseUtils.updateNetease(Constant.NETEASE_UPDATE_ICON, String.valueOf(user.getId()), "name", user.getNickName());
	}

	@Override
	public void updateUserGenderById(long userId, short gender) {
		userDao.updateUserGenderById(userId, gender);
	}

	@Override
	public String updateNeteaseUserIcon(String accid, String icon) throws Exception {
		return NeteaseUtils.updateNetease(Constant.NETEASE_UPDATE_ICON, accid, "icon", icon);
	}

	//TODO 待重构逻辑
	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public void saveUserWithNetesaToken(User user) {
    	try {
			String token = "";
			user.setMyInvitationCode(StringUtils.toSerialCode(Long.valueOf(StringUtils.getSixRandomNumber())).toUpperCase());
			saveUser(user);
			String neteaseToken = NeteaseUtils.createNeteaseAccount(String.valueOf(user.getId()));
			if (!StringUtils.isEmpty(neteaseToken)) {
				token = StringUtils.parseNeteaseJSON(neteaseToken, "token");
			}
            updateUserNickName(Constant.DEFAULT_NICK_NAME + user.getId(),user.getId());
			updateUserNeteaseToken(token, user.getId());
			NeteaseUtils.createNeteaseVideoUser(user.getId(), token);
		} catch (Exception e) {
    		throw new RuntimeException();
		}
	}

	@Override
	public int updateUserNeteaseToken(String neteaseToken, long userId) {
		return userDao.updateUserNeteaseToken(neteaseToken, userId);
	}

	@Override
	public int updatePassword(String password, String mobile) {
		return userDao.updatePassword(password, mobile);
	}

	@Override
	public int updateServicePrice(int servicePrice, long userId) {
		return userDao.updateServicePrice(servicePrice, userId);
	}

    @Override
    public List<UserSearchModel> queryUsersByGender(short gender) {
        return userDao.queryUsersByGender(gender);
    }

    @Override
    public List<UserSearchModel> likeUserByNickName(QueryParam queryParam) {
        return userDao.likeUserByNickName(queryParam);
    }

	@Override
	public int saveLockInformation(Unlock unlock) {
		return userDao.saveLockInformation(unlock);
	}

	@Override
	public UnlockWechatPrice queryUnlockWechatPrice() {
		return userDao.queryUnlockWechatPrice();
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int unlockWechat(Unlock unlock) {
		long lockUserId = unlock.getLockUserId();
		long unlockUserId = unlock.getUnlockUserId();
		try {
			UnlockWechatPrice unlockWechatPrice = queryUnlockWechatPrice();
			UserModel unlockUser = queryUserById(unlock.getUnlockUserId());
			//用户余额不足
			if (unlockUser.getBalance() * Constant.DIAMOND_TO_FLOWER_RATE < unlockWechatPrice.getUnlockWechatPrice()) {
				return -1;
			}
			String businessId = StringUtils.getUUID();
			unlock.setId(businessId);
			saveLockInformation(unlock);
			long price = unlockWechatPrice.getUnlockWechatPrice();
			saveDealInformation(unlockUserId, lockUserId, price, Constant.UNLOCK_WECHAT, businessId);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return Constant.SUCCESS;
	}


	@Override
	public List<Unlock> queryUnlock(Unlock unlock) {
		return userDao.queryUnlock(unlock);
	}

	@Override
	public List<User> isExsitNickName(String nickName, long userId) {
		return userDao.isExsitNickName(nickName, userId);
	}

	@Override
	public void updateUserIdentityCard(String identityCard, Long userId) {
		userDao.updateUserIdentityCard(identityCard, userId);
	}

	@Override
	public int updateUserIndexImage(String indexImage, long userId) {
		return userDao.updateUserIndexImage(indexImage, userId);
	}

	@Override
	public int updateUserAuthVideo(AuthVideo authVideo) {
    	String url = "";
    	try {
			String result = UploadVideoUtil.getNeteaseVideoInformation(authVideo.getVid());
			JSONObject json = JSONObject.parseObject(result);
			JSONObject ret = JSONObject.parseObject(json.get("ret").toString());
			url= ret.get("origUrl").toString();
		} catch (Exception e) {
    		e.printStackTrace();
		}
		return userDao.updateUserAuthVideo(url, authVideo.getId());
	}

	@Override
	public UserAuthModel queryUserAuth(long userId) {
		return userDao.queryUserAuth(userId);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int saveUserAuth(long userId) {
    	try {
            List<UserAuth> userAuths = queryUserAuths(userId);
            UserModel user = queryUserById(userId);
            if (userAuths.size() == 0 && (user.getAuth() == 0 || user.getAuth() == -2)) {
                userDao.saveUserAuth(userId);
                userDao.updateUserAuth(-1, userId);
                String content = "您的认证资料已成功提交，小颜会尽快处理，请耐心等待哦。";
                String msg = NeteaseUtils.setMsgExtMap(null, null, null, null, null, MsgConstant.AUTH_PROCESSING, content);
                NeteaseUtils.sendMsg(msg, userId);
            }
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return Constant.SUCCESS;
	}

	@Override
	public UserIndex queryUserIndex(long userId) {
		return userDao.queryUserIndex(userId);
	}

	@Override
	public List<UserIndexWatch> queryUserIndexWatch(long userId) {
		return userDao.queryUserIndexWatch(userId);
	}

	@Override
	public List<UserIndexLabel> queryUserIndexLabel(long userId) {
		return userDao.queryUserIndexLabel(userId);
	}

	@Override
	public List<VideoModel> queryUserIndexVideo(long userId) {
		return userDao.queryUserIndexVideo(userId);
	}

	@Override
	public int countUserWatch(long userId) {
		return userDao.countUserWatch(userId);
	}

	@Override
	public UserIndexScore countUserIndexScore(long userId) {
		return userDao.countUserIndexScore(userId);
	}




	@Override
	public int saveUserPic(String id, String picUrl, long userId) {
		return userDao.saveUserPic(id, picUrl, userId);
	}

	@Override
	public List<UserPic> queryUserPics(long userId) {
		return userDao.queryUserPics(userId);
	}

	@Override
	public int deleteUserPic(String picId) {
		return userDao.deleteUserPic(picId);
	}

	@Override
	public int saveDealInformation(long fromUser, long toUser, long flower, int dealType, String businessId) {
        AccountBook accountBook = new AccountBook();
        long value = 0;
		try {
            lockUser(fromUser, toUser);
            UserModel toUserModel = queryUserById(toUser);
			int reduceRow = userDao.reduceBalance(fromUser, flower / Constant.DIAMOND_TO_FLOWER_RATE);
			if (reduceRow == 0) {
			    return Constant.FAIL;
            }

			accountBook.setId(StringUtils.getUUID());
			accountBook.setBusinessId(businessId);
			accountBook.setFromUser(fromUser);
			if (!StringUtils.isEmpty(toUserModel.getInvitationCode()) && toUserModel.getAuth() == 1) {
			    accountBook.setStatus(1);
                UserModel shareUser = queryUserByMyinvitation(toUserModel.getInvitationCode());
                value = Math.round(flower / 2 * 0.1);
                accountBook.setReduceFlower(value);
                userDao.addFlower(shareUser.getId(), value);
            }
			accountBook.setToUser(toUser);
			accountBook.setType(dealType);
			accountBook.setFlower(flower);
            userDao.addFlower(toUser, flower / 2 - value);
			userDao.saveAccountBook(accountBook);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return Constant.SUCCESS;
	}

	@Override
	public double queryMyFlower(long userId) {
		return userDao.queryMyFlower(userId);
	}

	@Override
	public List<AccountBook> queryMyEarningsDetails(UserFlower userFlower) {
		return userDao.queryMyEarningsDetails(userFlower);
	}

	@Override
	public List<OnlineUser> queryOnlineUser(List<String> list, int gender, int auth, int offset, int limit) {
		return userDao.queryOnlineUser(list, gender, auth, offset, limit);
	}

    @Override
    public List<VideoModel> queryUserVideos(long userId) {
        return userDao.queryUserVideos(userId);
    }

	@Override
	public UserModel queryUserByUnionId(String unionId) {
		return userDao.queryUserByUnionId(unionId);
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public int saveOpenUser(WxLogin wxLogin) {
    	try {
			String token = "";
    		String nickName = wxLogin.getNickName();
			List<User> users = queryUserByNickName(wxLogin.getNickName());
			if (!users.isEmpty()) {
				nickName += StringUtils.getSixRandomNumber();
				wxLogin.setNickName(nickName);
			}
            wxLogin.setMyInvitationCode(StringUtils.toSerialCode(Long.valueOf(StringUtils.getSixRandomNumber())).toUpperCase());
			userDao.saveOpenUser(wxLogin);
			String neteaseToken = NeteaseUtils.createNeteaseAccount(String.valueOf(wxLogin.getId()));
			if (!StringUtils.isEmpty(neteaseToken)) {
				token = StringUtils.parseNeteaseJSON(neteaseToken, "token");
			}
			updateUserNeteaseToken(token, wxLogin.getId());
			updateNeteaseUserIcon(String.valueOf(wxLogin.getId()), wxLogin.getHeadimgurl());
			NeteaseUtils.createNeteaseVideoUser(wxLogin.getId(), token);
		} catch (Exception e) {
    		e.printStackTrace();
		}
		return Constant.SUCCESS;
	}

    @Override
    public int updateUserNickName(String nickName, long userId) {
        return userDao.updateUserNickName(nickName, userId);
    }

	@Override
	public RechargeRecord queryNewestRechargeReocrdByUserId(long userId) {
		return userDao.queryNewestRechargeReocrdByUserId(userId);
	}

	@Override
	public void isVip(UserModel userModel) {
		try {
			RechargeRecord rechargeRecord = queryNewestRechargeReocrdByUserId(userModel.getId());
			if (rechargeRecord != null) {
				long days = TimeUtils.twoDate(new Date(), rechargeRecord.getCreateTime());
				if (days < 31) {
					userModel.setVip(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int updateUserOnline(Disturb disturb) {
		return userDao.updateUserOnline(disturb);
	}


    @Override
    public List<InvitationUser> queryInvitationUsers(String invitationCode) {
        return userDao.queryInvitationUsers(invitationCode);
    }

	@Override
	public String queryShareUserConfig(int type) {
		return userDao.queryShareUserConfig(type);
	}

    @Override
    public void setVideoShareUrl(List<VideoModel> videos, String shareUrl) {
        for (VideoModel video : videos) {
            video.setShareUrl(shareUrl);
        }
    }

	@Override
	public List<UserSearchModel> queryRecommendUsers(QueryParam queryParam) {
        UserModel userModel = userDao.queryUserById(queryParam.getUserId());
		return userDao.queryRecommend(null, userModel.getGender() , userModel.getAuth());
	}

    @Override
    public int updateUserOnOrOffOnline(String userId, int online) {
        return userDao.updateUserOnOrOffOnline(userId, online);
    }

    @Override
    public List<OnlineUser> queryUsers(int auth, int gender) {
        return userDao.queryUsers(auth, gender);
    }

	@Override
	public int saveAccountBook(AccountBook accountBook) {
		return userDao.saveAccountBook(accountBook);
	}

    @Override
    public List<FlowerRank> queryFlowerRankingList(long toUser) {
        return userDao.queryFlowerRankingList(toUser);
    }
    @Override
    public List<FlowerRank> queryTopThreeFlowerRanking(long toUser) {
        return userDao.queryTopThreeFlowerRanking(toUser);
    }

	@Override
	public List<BestFriend> queryBestFriend(long toUser) {
		return userDao.queryBestFriend(toUser);
	}

    @Override
    public Integer queryFlowerRecordByMyIdWithOtherUserId(long fromUser, long toUser, int type) {
        Integer flower = userDao.queryFlowerRecordByMyIdWithOtherUserId(fromUser, toUser, type);
        return flower == null ? 0 : flower;
    }

    @Override
    public BestFriend queryMyPkInformation(long userId) {
        return userDao.queryMyPkInformation(userId);
    }

    @Override
    public List<UserModel> lockUser(long fromUser, long toUser) {
        return userDao.lockUser(fromUser, toUser);
    }

    @Override
    public int updateUserAuth(int userAuth, long userId) {
        return userDao.updateUserAuth(userAuth, userId);
    }

    @Override
    public List<UserAuth> queryUserAuths(long userId) {
        return userDao.queryUserAuths(userId);
    }

	@Override
	public int updateUserOnlineStatus(UserModel user) {
		if (user.getOnline() == Constant.LOGOUT_DONT_DISTURB || user.getOnline() == Constant.DONT_DISTURB) {
			updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.DONT_DISTURB);
		} else {
			updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.ONLINE);
		}
		return Constant.SUCCESS;
	}

	@Override
	public long queryTotalFlower(long userId) {
    	return userDao.queryTotalFlower(userId);
	}

	@Override
	public void setUserOfflineTime(long userId) {
		userDao.setUserOfflineTime(userId);
	}

    @Override
    public long queryUserIndexFlower(UserIndex userIndex) {
        return userDao.queryUserIndexFlower(userIndex);
    }

	@Override
	public String queryGiftNameByBusinessId(String businessId) {
		return userDao.queryGiftNameByBusinessId(businessId);
	}

    @Override
    public String queryShortVideoTitleByBusinessId(String businessId) {
        return userDao.queryShortVideoTitleByBusinessId(businessId);
    }

    @Override
    public UserModel queryUserByMyinvitation(String invitationCode) {
        return userDao.queryUserByMyinvitation(invitationCode);
    }

    @Override
    public void addSharingUserFlower(long userId, long flower) {
        UserModel userModel = userDao.queryUserById(userId);
        if (!StringUtils.isEmpty(userModel.getInvitationCode())) {
            UserModel sharingUser = queryUserByMyinvitation(userModel.getInvitationCode());
            userDao.addFlower(sharingUser.getId(), flower);
        }
    }

    @Override
    public long countMyInvitationUser(String invitationCode, int type) {
        return userDao.countMyInvitationUser(invitationCode, type);
    }

    @Override
    public double countSharingUserContributionValue(String invitationCode) {
        return userDao.countSharingUserContributionValue(invitationCode);
    }

    @Override
    public double countSharingAuthUserContributionValue(String invitationCode) {
        return userDao.countSharingAuthUserContributionValue(invitationCode);
    }

    @Override
    public List<ShareUser> listSharingAuthUser(String invitationCode) {
        return userDao.listSharingAuthUser(invitationCode);
    }

    @Override
    public List<ShareUser> listSharingUserRecharge(String invitationCode) {
        return userDao.listSharingUserRecharge(invitationCode);
    }

    @Override
    public void updateUserFaceTime(long customerId, long serviceId, int status) {
        userDao.updateUserFaceTime(customerId, serviceId, status);
    }

    @Override
    public void saveWritingChatRecord(WritingChat writingChat) {
        userDao.saveWritingChatRecord(writingChat);
    }

    @Override
    public long countWritingChatRecord(WritingChat writingChat) {
        return userDao.countWritingChatRecord(writingChat);
    }
}