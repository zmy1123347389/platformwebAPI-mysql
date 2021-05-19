package com.behere.video.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.behere.common.annotation.Log;
import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.sms.SmsUtil;
import com.behere.common.utils.*;
import com.behere.video.domain.*;
import com.behere.video.model.FlowerRank;
import com.behere.video.model.ImageModel;
import com.behere.video.model.InvitationUser;
import com.behere.video.model.UnlockModel;
import com.behere.video.model.UserAuthModel;
import com.behere.video.model.UserIndexScore;
import com.behere.video.model.UserModel;
import com.behere.video.model.UserSearchModel;
import com.behere.video.model.VideoModel;
import com.behere.video.service.UserService;
import com.behere.video.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 用户操作接口
 * @author Fengj
 * @date 2018/05/03
 *
 */
@Controller
@RequestMapping(value = "/api/v1/user")
public class UserController {
	@Autowired
	private VideoService videoService;
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	UserService userService;
	@Autowired
	FTPConfig ftpConfig;

	@Log("发送短信验证码")
	@PostMapping(value = "/sendSMS")
	@ResponseBody
	public R sendSMS(String m) {
		try {
//			String verificaCode = StringUtils.getRandomNumber();
			String verificaCode = "9527";
			User user = RequestUtils.parseParameter(m, User.class);
			String mobile = user.getMobile();
			redisUtil.set(mobile, verificaCode, Constant.SMS_EXPIRE_SECONDS);
			//SmsUtil.sendSMS(mobile, verificaCode);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.VERIFI_CODE_FAIL);
		}
	}

	@Log("注册")
	@PostMapping(value = "/regist")
	@ResponseBody
	public R regist(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			String verificaCode = redisUtil.get(user.getMobile());
			if (!(user.getVerificaCode().equals(verificaCode))) {
				return R.error(-1, Constant.VERIFI_CODE_WRONG);
			}
			List<User> users = userService.queryUserByMobile(user.getMobile());
			if (!users.isEmpty()) {
				return R.error(-1, Constant.MOIBLE_REGISTED);
			}
			userService.saveUserWithNetesaToken(user);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.REGIST_FAIL);
		}
	}

	@Log("登录")
	@PostMapping(value = "/login")
	@ResponseBody
	public R login(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			UserModel userInfo = userService.queryUserByMobileAndPassword(user.getMobile(), user.getPassword());
			if (userInfo == null) {
				return R.error(-1, Constant.MOBILE_OR_PASSWORD_WRONG);
			}
			if (userInfo.getDeleted() == 1) {
			    return R.error(-1, Constant.USER_FREEZE);
            }
            userService.updateUserOnlineStatus(userInfo);
			return R.ok(userInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.LOGIN_FAIL);
		}
	}


	@Log("上传用户头像")
	@PostMapping("/uploadImages")
	@ResponseBody
	public R uploadImages(@RequestParam("userId") Long userId,
						  @RequestParam("file") MultipartFile file,
						  @RequestParam("type") int type) {
		ImageModel imageModel = new ImageModel();
		try {
			String picNewName = ImageUtils.uploadImage(file, ftpConfig);
			if (type == Constant.HEAD_PORTRAIT) {
				userService.updateUserHeadPortrait(picNewName, userId);
				userService.updateNeteaseUserIcon(String.valueOf(userId), picNewName);
			} else if (type == Constant.WECHAT_QRCODE) {
				userService.updateWechatQRcode(userId, picNewName);
			} else if (type == Constant.IDENTITY_CARD) {
				userService.updateUserIdentityCard(picNewName, userId);
			} else if (type == Constant.USER_INDEX_IMAGE) {
				userService.updateUserIndexImage(picNewName, userId);
			}
			imageModel.setType(type);
			imageModel.setImage(picNewName);
			return R.ok(imageModel);
		} catch (Exception e) {
			return R.error(-1, Constant.UPLOAD_IMAGE_FAIL);
		}
	}

	@Log("上传用户认证视频地址")
	@PostMapping("/uploadAuthVideo")
	@ResponseBody
	public R uploadAuthVideo(String m) {
		try {
			AuthVideo authVideo = RequestUtils.parseParameter(m, AuthVideo.class);
			userService.updateUserAuthVideo(authVideo);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPLOAD_IMAGE_FAIL);
		}
	}

	@Log("获取用户信息")
	@PostMapping("/getUserById")
	@ResponseBody
	public R getUserById(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			UserModel userInfo = userService.queryUserById(user.getId());
			userInfo.setDiamondToFlowerRate(Constant.DIAMOND_TO_FLOWER_RATE);
			//判断用户是否为VIP
            userInfo.setTotalFlower(userService.queryTotalFlower(userInfo.getId()));
            userService.isVip(userInfo);
			return R.ok(userInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_USER_INFO_FAIL);
		}
	}

	@Log("修改用户性别")
	@PostMapping("/updateUserGenderById")
	@ResponseBody
	public R updateUserGenderById(String m) throws IOException {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			if (user != null) {
				userService.updateUserGenderById(user.getId(), user.getGender());
			}
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_USER_SEX_FAIL);
		}
	}
	
	@Log("更新用户信息")
	@PostMapping("/updateUserById")
	@ResponseBody
	public R updateUserById(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			List<User> users = userService.isExsitNickName(user.getNickName(), user.getId());
			if (!users.isEmpty()) {
				return R.error(-1, Constant.NICK_NAME_EXIST);
			}
			if (user.getAge() == 0) {
				user.setAge(18);
			}
			userService.updateUserInfoById(user);
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_USER_INFO_FAIL);
		}
	}

	@Log("修改用户密码")
	@PostMapping("/updatePassword")
	@ResponseBody
	public R updatePassword(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			String verificaCode = redisUtil.get(user.getMobile().toString());
			if (!(user.getVerificaCode().equals(verificaCode))) {
				return R.error(-1, Constant.VERIFI_CODE_WRONG);
			}
			userService.updatePassword(user.getPassword(), user.getMobile());
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_PASSWORD_FAIL);
		}
	}


	@Log("修改服务价格")
	@PostMapping("/updateServicePrice")
	@ResponseBody
	public R updateServicePrice(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			userService.updateServicePrice(user.getServicePrice(), user.getId());
			return R.ok();
		} catch (Exception e) {
			return R.error(-1, Constant.UPDATE_SERVICED_PRICE_FAIL);
		}
	}

	@Log("根据性别查找用户")
	@PostMapping("/queryUsersByGender")
	@ResponseBody
	public R queryUsersByGender(String m,
								@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
								@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			PageHelper.startPage(pageNum, pageSize);
			User user = RequestUtils.parseParameter(m, User.class);
			List<UserSearchModel> users = userService.queryUsersByGender(user.getGender());
			PageInfo<UserSearchModel> pageInfo = new PageInfo<UserSearchModel>(users);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_DATA_FAIL);
		}
	}

	@Log("通过ID或者昵称查找用户")
	@PostMapping("/queryUserByIdOrNickName")
	@ResponseBody
	public R queryUserByIdOrNickName(String m,
									 @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
									 @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
            List<UserSearchModel> users = new ArrayList<UserSearchModel>();
			QueryParam queryParam = RequestUtils.parseParameter(m, QueryParam.class);
            PageHelper.startPage(pageNum, pageSize);
			if (StringUtils.isEmpty(queryParam.getIdOrNickName())) {
				UserModel user = userService.queryUserById(queryParam.getUserId());
				if (user != null) {
					queryParam.setGender(user.getGender());
					queryParam.setAuth(user.getAuth());
				}
                users = userService.queryRecommendUsers(queryParam);
            } else {
                users = userService.likeUserByNickName(queryParam);
            }
            PageInfo<UserSearchModel> pageInfo = new PageInfo<UserSearchModel>(users);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error(-1, Constant.GET_DATA_FAIL);
		}
	}

	@Log("移动端判断微信是否解过锁")
	@PostMapping("/isUnlockWeChat")
	@ResponseBody
	public R isUnlockWeChat(String m) {
		UnlockModel unlockModel = new UnlockModel();
		try {
			Unlock unlock = RequestUtils.parseParameter(m, Unlock.class);
			List<Unlock> unlocks = userService.queryUnlock(unlock);
			//判断用户是否解过锁
			if (!unlocks.isEmpty()) {
				UserModel userVO = userService.queryUserById(unlock.getLockUserId());
				unlockModel.setWeChatImage(userVO.getWechatCode());
				unlockModel.setFlag(Constant.UNLOCKED);
			} else {
				UnlockWechatPrice unlockWechatPrice = userService.queryUnlockWechatPrice();
				unlockModel.setPrice(unlockWechatPrice.getUnlockWechatPrice());
				unlockModel.setFlag(Constant.Not_UNLOCK);
			}
			return R.ok(unlockModel);
		} catch (Exception e) {
 			return R.error();
		}
	}


	@Log("解锁用户微信二维码")
	@PostMapping("/unlockWeChat")
	@ResponseBody
	public R unlockWeChat(String m) {
		try {
			Unlock unlock = RequestUtils.parseParameter(m, Unlock.class);
			int result = userService.unlockWechat(unlock);
			if (result == -1) {
				return R.error(-1, Constant.BALANCE_NOT_ENOUGH);
			}
			UnlockModel unlockModel = new UnlockModel();
			UserModel userModel = userService.queryUserById(unlock.getLockUserId());
			unlockModel.setWeChatImage(userModel.getWechatCode());
			unlockModel.setFlag(Constant.UNLOCKED);
			return R.ok(unlockModel);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("获取用户个人主页信息")
	@PostMapping("/queryUserIndex")
	@ResponseBody
	public R queryUserIndex(String m) {
		try {

			double userScore = 5.0;
			User user = RequestUtils.parseParameter(m, User.class);
			long userId = user.getId();
			UserIndex userIndex = userService.queryUserIndex(userId);
			List<BestFriend> bestFriend = userService.queryBestFriend(userId);
			List<VideoModel> userVideos = userService.queryUserIndexVideo(userId);
			videoService.isWatchedVideo(userVideos, user.getMyId());
			List<UserIndexLabel> userIndexLabels = userService.queryUserIndexLabel(userId);
            List<UserPic> userPics = new ArrayList<UserPic>();
            UserPic userPic = new UserPic();
            if (!StringUtils.isEmpty(userIndex.getHeadPortrait())) {
                userPic.setPicUrl(userIndex.getHeadPortrait());
                userPic.setUserId(userId);
                userPics.add(userPic);
            }
            userPics.addAll(userService.queryUserPics(userId));
			userIndex.setUserPics(userPics);
			userIndex.setBestFriend(bestFriend);
			userIndex.setUserIndexLabels(userIndexLabels);
			userIndex.setUserVideos(userVideos);
			UserIndexScore userIndexScore = userService.countUserIndexScore(userId);
			if (userIndexScore != null && !StringUtils.isEmpty(userIndexScore.getScore() != null)) {
				userScore = Double.parseDouble(userIndexScore.getScore());
			}
			userIndex.setScore(userScore);
			if (userIndex.getOnline() != Constant.ONLINE) {
				userIndex.setOfflineDistanceNow(DateUtils.getOfflineDistanceNow(userIndex.getOfflineTime()));
			}
			userIndex.setFlower(userService.queryUserIndexFlower(userIndex));
			return R.ok(userIndex);
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@Log("获取认证信息")
	@PostMapping("/queryUserAuth")
	@ResponseBody
	public R queryUserAuth(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			UserAuthModel userAuthModel = userService.queryUserAuth(user.getId());
			return R.ok(userAuthModel);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("提交认证信息")
	@PostMapping("/submitAuth")
	@ResponseBody
	public R submitAuth(String m) {
		try {
			User user = RequestUtils.parseParameter(m, User.class);
			userService.saveUserAuth(user.getId());
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("用户上传照片")
	@PostMapping("/uploadUserPic")
	@ResponseBody
	public R uploadUserPic(@RequestParam("userId") Long userId, @RequestParam("files") MultipartFile[] files) {
		try {
			for (MultipartFile file : files) {
				String picNewName = ImageUtils.uploadImage(file, ftpConfig);
				if (picNewName != null) {
					userService.saveUserPic(StringUtils.getUUID(), picNewName, userId);
				}
			}
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("删除用户照片")
	@PostMapping("/deleteUserPic")
	@ResponseBody
	public R deleteUserPic(String m) {
		try {
			UserPic userPic = RequestUtils.parseParameter(m, UserPic.class);
			userService.deleteUserPic(userPic.getId());
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("获取用户照片")
	@PostMapping("/queryUserPics")
	@ResponseBody
	public R queryUserPics(String m,
						   @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
						   @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			PageHelper.startPage(pageNum, pageSize);
			UserPic userPic = RequestUtils.parseParameter(m, UserPic.class);
			List<UserPic> userPics = userService.queryUserPics(userPic.getUserId());
			PageInfo<UserPic> pageInfo = new PageInfo<UserPic>(userPics);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("获取收益信息")
	@PostMapping("/queryMyEarnings")
	@ResponseBody
	public R queryMyEarnings(String m) {
		DecimalFormat dFormat = new DecimalFormat("#.00");
		try {
			UserFlower userFlower = RequestUtils.parseParameter(m, UserFlower.class);
			double myFlower = userService.queryMyFlower(userFlower.getUserId());
			userFlower.setFlower(myFlower);
			userFlower.setMoney(Double.valueOf(dFormat.format(myFlower / Constant.DIAMOND_TO_FLOWER_RATE / Constant.CNY_TO_DIAMOND_RATE)));
			userFlower.setTotalFlower(userService.queryTotalFlower(userFlower.getUserId()));
			return R.ok(userFlower);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("获取收益明细")
	@PostMapping("/queryMyEarningsDetails")
	@ResponseBody
	public R queryMyEarningsDetails(String m,
									@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
									@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			PageHelper.startPage(pageNum, pageSize);
			UserFlower userFlower = RequestUtils.parseParameter(m, UserFlower.class);
			List<AccountBook> accountBooks = userService.queryMyEarningsDetails(userFlower);
			PageInfo<AccountBook> pageInfo = new PageInfo<AccountBook>(accountBooks);
			for (AccountBook account : accountBooks) {
				if (account.getType() == Constant.SEND_GIFT) {
                    account.setTitle(userService.queryGiftNameByBusinessId(account.getBusinessId()));
				}
				if (account.getType() == Constant.SHORT_VIDEO) {
                    account.setTitle(userService.queryShortVideoTitleByBusinessId(account.getBusinessId()));
                }
			}
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("用户视频列表")
	@PostMapping("/queryUserVideoByMyId")
	@ResponseBody
	public R queryUserVideoByMyId(String m,
									@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
									@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
		    String shareUrl = userService.queryShareUserConfig(0);
			PageHelper.startPage(pageNum, pageSize);
			User user = RequestUtils.parseParameter(m, User.class);
			List<VideoModel> videoModels = userService.queryUserVideos(user.getId());
            userService.setVideoShareUrl(videoModels, shareUrl);
			videoService.isWatchedVideo(videoModels, user.getMyId());
			PageInfo<VideoModel> pageInfo = new PageInfo<VideoModel>(videoModels);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error();
		}
	}

	@PostMapping("wx/login")
    @ResponseBody
    public R wxLogin(WxLogin wxLogin) {
        try {
            UserModel user = userService.queryUserByUnionId(wxLogin.getUnionId());
            if  (user != null) {
				if (user.getDeleted() == 1) {
					return R.error(-1, Constant.USER_FREEZE);
				}
                userService.updateUserOnlineStatus(user);
                return R.ok(user);
            }
            userService.saveOpenUser(wxLogin);
            user = userService.queryUserById(wxLogin.getId());
            return R.ok(user);
        } catch (Exception e) {
            return R.error();
        }
    }

    @PostMapping("/donotDisturb")
    @ResponseBody
    public R donotDisturb(String m) {
        try {
            Disturb disturb = RequestUtils.parseParameter(m, Disturb.class);
            if (disturb.getStatus() == 0) {
            	disturb.setStatus(1);
			}
            userService.updateUserOnline(disturb);
            if (disturb.getStatus() == 2) {
            	userService.setUserOfflineTime(disturb.getUserId());
			}
            UserModel user = userService.queryUserById(disturb.getUserId());
            return R.ok(user);
        } catch (Exception e) {
            return R.error();
        }
    }

    @Log("邀请用户列表")
    @PostMapping("/queryInvitation")
    @ResponseBody
    public R queryInvitation(String myInvitationCode,
                                  @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                                  @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            List<InvitationUser> users = null;
            PageHelper.startPage(pageNum, pageSize);
            if (!StringUtils.isEmpty(myInvitationCode)) {
                users = userService.queryInvitationUsers(myInvitationCode);
            }
            PageInfo<InvitationUser> pageInfo = new PageInfo<InvitationUser>(users);
            return R.ok(pageInfo);
        } catch (Exception e) {
            return R.error();
        }
    }

	@Log("邀请好友")
	@PostMapping("/shareUser")
	@ResponseBody
	public R shareUser() {
		try {
		    Map<String, String> map = new HashMap<String, String>();
		    String url = userService.queryShareUserConfig(1);
		    map.put("shareUser", url);
			return R.ok(map);
		} catch (Exception e) {
			return R.error();
		}
	}

	@Log("鲜花榜")
	@PostMapping("/queryFlowerRank")
	@ResponseBody
	public R queryFlowerRank(long toUser,
							 @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
							 @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<FlowerRank> flowerRanks = userService.queryFlowerRankingList(toUser);
			PageInfo<FlowerRank> pageInfo = new PageInfo<FlowerRank>(flowerRanks);
			return R.ok(pageInfo);
		} catch (Exception e) {
			return R.error();
		}
	}

    @Log("最佳好友PK")
    @PostMapping("/bestFriendPk")
    @ResponseBody
    public R bestFriendPk(long myUserId, long userId) {
	    BestFriendPk bestFriendPk = new BestFriendPk();
        try {
            List<BestFriend> bestFriends = userService.queryBestFriend(userId);
            BestFriend myInformation = userService.queryMyPkInformation(myUserId);
            Integer flower = userService.queryFlowerRecordByMyIdWithOtherUserId(myUserId, userId, Constant.WATCH_USER);
            myInformation.setFlower(flower);
            bestFriendPk.setBestFriends(bestFriends);
            bestFriendPk.setMy(myInformation);
            return R.ok(bestFriendPk);
        } catch (Exception e) {
            return R.error();
        }
    }

	@Log("退出登录")
	@PostMapping("/logout")
	@ResponseBody
	public R logout(String m) {
		try {
            User user = RequestUtils.parseParameter(m, User.class);
            UserModel userModel = userService.queryUserById(user.getId());
            if (userModel.getOnline() == Constant.DONT_DISTURB) {
                userService.updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.LOGOUT_DONT_DISTURB);
            } else {
                userService.updateUserOnOrOffOnline(String.valueOf(user.getId()), Constant.LOGOUT_ONLINE);
            }
			return R.ok();
		} catch (Exception e) {
			return R.error();
		}
	}

	@PostMapping("/querySharingPlan")
    @ResponseBody
    public R querySharingPlan(long userId, int type) {
        double contributionValue = 0.0;
	    try {
            UserModel user = userService.queryUserById(userId);
            long sharingUserNums = userService.countMyInvitationUser(user.getMyInvitationCode(), type);
            if (type != 1) {
                contributionValue = userService.countSharingUserContributionValue(user.getMyInvitationCode());
            } else {
                contributionValue = userService.countSharingAuthUserContributionValue(user.getMyInvitationCode());
            }
            SharingPlan sharingPlan = new SharingPlan();
            sharingPlan.setContributionValue(contributionValue);
            sharingPlan.setSharingNum(sharingUserNums);
            return R.ok(sharingPlan);
        } catch (Exception e) {
	        e.printStackTrace();
	        return R.error();
        }
    }

    @PostMapping("/querySharedUsers")
    @ResponseBody
    public R querySharedUsers(long userId, int type,
                              @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                              @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize) {
        try {
            UserModel userModel = userService.queryUserById(userId);
            List<ShareUser> shareUsers = new ArrayList<ShareUser>();
            PageHelper.startPage(pageNum, pageSize);
            if (type == 1) {
                shareUsers = userService.listSharingAuthUser(userModel.getMyInvitationCode());
            } else {
                shareUsers = userService.listSharingUserRecharge(userModel.getMyInvitationCode());
            }
            PageInfo<ShareUser> pageInfo = new PageInfo<ShareUser>(shareUsers);
            return R.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}