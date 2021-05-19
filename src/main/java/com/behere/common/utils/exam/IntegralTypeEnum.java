package com.behere.common.utils.exam;

/**
 * @ClassName：EnumIntegral
 * @author: Behere
 * @Date: 2020/3/20 21:23
 * @Description:
 */
public enum IntegralTypeEnum {
    CONTEXT(1, "法律法规"),
    SIGN(2, "签到"),
    ASK(3, "提问"),
    ANSWER(4, "回答"),
    VIDEO(5, "看视频"),
    ONLINE(6, "在线考试"),
    QUALIFICATION(7, "模拟答题"),
    PROJECT(8, "每周必答"),
    CASE(9, "阅读案例"),
    CGDT(11,"闯关答题"),
    MOCKEXAM(12,"模拟考试"),
    XFSD(13,"新法速递"),
    ZFZD(14,"执法制度"),
    TWXF(15,"图文学法"),
    BXRW(16,"必学任务"),
    YPXF(17,"音频学法"),
    ;


    private Integer code;

    private String name;


    private IntegralTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getTypeName(String code) {
        String result = "";
        switch (code) {
            case "1": {
                result = CONTEXT.getName();
                break;
            }
            case "2": {
                result = SIGN.getName();
                break;
            }
            case "3": {
                result = ASK.getName();
                break;
            }
            case "4": {
                result = ANSWER.getName();
                break;
            }
            case "5": {
                result = VIDEO.getName();
                break;
            }
            case "6": {
                result = ONLINE.getName();
                break;
            }
            case "7": {
                result = QUALIFICATION.getName();
                break;
            }
            case "8": {
                result = PROJECT.getName();
                break;
            }
            case "9": {
                result = CASE.getName();
                break;
            }
            case "11": {
                result = CGDT.getName();
                break;
            }
            case "12": {
                result = MOCKEXAM.getName();
                break;
            }
            case "13": {
                result = XFSD.getName();
                break;
            }
            case "14": {
                result = ZFZD.getName();
                break;
            }
            case "15": {
                result = TWXF.getName();
                break;
            }
            case "16": {
                result = BXRW.getName();
                break;
            }
            case "17": {
                result = YPXF.getName();
                break;
            }
            default: {
                result = code;
            }
        }
        return result;
    }


    private String getName() {

        return name;
    }

	public Integer getCode() {
		return code;
	}

	
    
    
}
