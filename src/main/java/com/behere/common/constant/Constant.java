package com.behere.common.constant;

public class Constant {

    public static final long SMS_EXPIRE_SECONDS = 300L;

	//购买凭证验证地址
	public static final String CERTIFICATEURL = "https://buy.itunes.apple.com/verifyReceipt";

	//测试的购买凭证验证地址
	public static final String CERTIFICATEURL_TEST = "https://sandbox.itunes.apple.com/verifyReceipt";

	/**华信网关API*/
	public static final String HUAXIN_API = "https://dx.ipyy.net/webservice.asmx?wsdl";

	/**华信短信网关用户名*/
	public static final String HUAXIN_USENAME = "8C00039";

	/**华信短信网关密码*/
	public static final String HUAXIN_PASSWORD = "8C0003988";

	/**网易云信创建api*/
	public static final String NETEASE_CREATE_API = "https://api.netease.im/nimserver/user/create.action";

	public static final String NETEASE_UPDATE_ICON = "https://api.netease.im/nimserver/user/updateUinfo.action";

	public static final String NETEASE_IM_CREATE = "https://vcloud.163.com/app/vod/thirdpart/user/create";

	/**网易云信app key*/
	public static final String NETEASE_APP_KEY = "f222b4fe373ac1c44adcb38180e0a5bd";

	/**网易云信app secret*/
	public static final String NETEASE_APP_SECRET = "a28695fb8b29";

	public static final String VERIFI_CODE_FAIL = "获取验证码失败";

	public static final String VERIFI_CODE_WRONG = "验证码错误";

	public static final String MOIBLE_REGISTED = "该手机号码已注册，请返回登录";

	public static final String REGIST_FAIL = "注册失败";

	public static final String MOBILE_OR_PASSWORD_WRONG = "手机号或密码错误";

	public static final String LOGIN_FAIL = "登录失败";

	public static final String UPLOAD_IMAGE_FAIL = "上传失败";

	public static final String GET_USER_INFO_FAIL = "获取用户信息失败";

	public static final String NICK_NAME_EXIST = "此昵称已经存在";
	
	public static final String UPDATE_USER_INFO_FAIL = "修改用户信息失败";
	
	public static final String UPDATE_USER_SEX_FAIL = "修改用户性别失败";

	public static final String QUERY_AREA_FAIL = "获取地区信息失败";

	public static final String UPDATE_PASSWORD_FAIL = "修改密码失败";

	public static final String UPDATE_SERVICED_PRICE_FAIL = "修改服务价格失败";

	public static final String GET_DATA_FAIL = "获取数据失败";

	public static final String BALANCE_NOT_ENOUGH = "余额不足";
	public static final String CUSTOMER_BALANCE_NOT_ENOUGH = "对方余额不足";

	public static final String DEFAULT_SIGNATURE = "快来和我视频吧！";

	public static final int Not_UNLOCK = 0;

	public static final int UNLOCKED = 1;

	public static final int HEAD_PORTRAIT = 0;

	public static final int WECHAT_QRCODE= 1;

	public static final int IDENTITY_CARD= 2;

	public static final int USER_INDEX_IMAGE = 3;

	public static final String NETEASE_SUCCESS = "200";

	public static final String REQUEST_TIME_OUT = "网络请求超时";

	public static final int IS_LIKE = 1;

	public static final int NOT_LIKE = 0;

	public static final String RECHARGE_SUCCESS = "SUCCESS";

	public static final int SUCCESS = 1;

	public static final int FAIL = 0;

	//0守护  1送礼物 2视频 3短视频 4解锁微信 5文字聊天

	public static final int WATCH_USER = 0;

	public static final int SEND_GIFT = 1;

	public static final int WATCH_VIDEO = 2;

	public static final int SHORT_VIDEO = 3;

	public static final int UNLOCK_WECHAT = 4;

	public static final int WRITING_CHAT = 5;

	public static final int DIAMOND_TO_FLOWER_RATE = 10;

	public static final int CNY_TO_DIAMOND_RATE = 10;

	public static final int CNY_TO_FLOWER = 100;

	public static final int ANCHOR = 0;

	public static final int RICHER = 1;

	public static final boolean IOS_PRODUCTION_STATUS = true;

	public static final String ANDROID_PUSH_APP_KEY = "5b051eb9f43e4810c0000183";

	public static final String ANDROID_PUSH_Master_SECRET = "emodd8zwradpcvc45xk1dnvbfccoxi8o";

	public static final String IOS_PUSH_APP_KEY = "5b06a019f43e4871600000a2";

	public static final String IOS_PUSH_Master_SECRET = "919jegbybzes8bpzbs2dj9bqiryveupi";

	public static final String APP_NAME = "颜语";

	public static final String SUMMON_PUSH_CONTENT = "发起了爱的召唤，TA一定很想你，赶紧聊起来吧！";

	public static final String VIP_OPERATION = "只有会员才能使用此功能";

	public static final String DEFAULT_NICK_NAME = "颜语";

	public static final int AUTH = 1;

	public static final int NOT_AUTH = 0;

	public static final String CANT_TALK = "双方为认证用户，无法通话";

	public static final String ALL_DONT_AUTH = "双方为非认证用户，无法通话";

	public static final int ALREADY_WATCHED = 1;

	public static final int NOT_WATCHED = 0;

	public static final String CALLED_NOT_DISTURB = "对方开启了勿扰模式";

	public static final String CALLED_NOT_ONLINE = "对方不在线";

	public static final int IS_VIP = 1;

	public static final int DONT_DISTURB = 2;

	public static final int ONLINE = 1;
	public static final int LOGOUT_DONT_DISTURB = 3;
	public static final int LOGOUT_ONLINE = 4;
	public static final int OFFLINE = 1;
	//分享链接
	public static final String SHARE_URL = "test";

	public static final String USER_AUTH_INFORMATION = "认证信息已提交，请等待工作人员审核";

	public static final String USER_FREEZE = "用户已经被冻结";

	public static final int FACE_TIMING = 1;
	public static final int NO_FACE_TIMING = 0;
}