package com.behere.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URLEncoder;
import java.util.Random;
import java.util.UUID;

/**
 * @author behere
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/** 自定义进制(0,1没有加入,容易与o,l混淆) */
	private static final char[] r=new char[]{'q', 'w', 'e', '8', 'a', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'i', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 'l', 't', 'n', '6', 'b', 'g', 'h'};

	/** (不能与自定义进制有重复) */
	private static final char b='o';

	/** 进制长度 */
	private static final int binLen=r.length;

	/** 序列最小长度 */
	private static final int s=6;

    public static String getRandomNumber() {
		return String.valueOf((int)((Math.random() * 9 + 1) * 1000));
	}

	public static String getSixRandomNumber() {
		return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
	}
    
    /**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] strList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	public static String dealNull(Object obj) {
		String str = "";
		if(obj!=null){
			if(obj instanceof String){
				str = (String)obj;
			}else{
				str = obj.toString();
			}
		}
		return str;
	}
	
	public static String dealNull(String str, String defaultVal) {
		return str != null ? str.trim() : defaultVal;
	}
	
	
	public static boolean isEmpty(String s){
		return s == null || "".compareTo(s) == 0;
    }
	
	public static boolean isEmpty(Object str) {
		return str == null || "".equals(str);
	}
	
	public static boolean isNull(String str){
		return str == null || "".equals(str) || "null".equals(str);
	}
	
	public static String encode(String str, final String encoding) {
		try {
			if (!StringUtils.isEmpty(str)) {
				str = URLEncoder.encode(str, encoding);
			}
		} catch (Exception e) {
		}
		return str;
	}

	/**
	 *  获取UUID
	 * @return
	 *
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 字符转码
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer buffer = new StringBuffer(len);
		for (int i = 0; i < len;) {
			aChar = theString.charAt(i++);
			if (aChar == '\\') {
				aChar = theString.charAt(i++);
				if (aChar == 'u') {
					int val = 0;
					for (int j = 0; j < 4; j++) {
						aChar = theString.charAt(i++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							val = (val << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							val = (val << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							val = (val << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed encoding.");
						}
					}
					buffer.append((char) val);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					}
					if (aChar == 'r') {
						aChar = '\r';
					}
					if (aChar == 'n') {
						aChar = '\n';
					}
					if (aChar == 'f') {
						aChar = '\f';
					}
					buffer.append(aChar);
				}
			} else {
				buffer.append(aChar);
			}
		}
		return buffer.toString();
	}

	/**
	 * 解析网易云信json
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static String parseNeteaseJSON(String jsonStr, String key) {
		JSONObject object = JSON.parseObject(jsonStr);
		JSONObject token = JSON.parseObject(object.get("info").toString());
		return token.get(key).toString();
	}

	/**
	 * 根据ID生成六位随机码
	 * @param id ID
	 * @return 随机码
	 */
	public static String toSerialCode(long id) {
		char[] buf=new char[32];
		int charPos=32;

		while((id / binLen) > 0) {
			int ind=(int)(id % binLen);
			buf[--charPos]=r[ind];
			id /= binLen;
		}
		buf[--charPos]=r[(int)(id % binLen)];
		String str=new String(buf, charPos, (32 - charPos));
		// 不够长度的自动随机补全
		if(str.length() < s) {
			StringBuilder sb=new StringBuilder();
			sb.append(b);
			Random rnd=new Random();
			for(int i=1; i < s - str.length(); i++) {
				sb.append(r[rnd.nextInt(binLen)]);
			}
			str+=sb.toString();
		}
		return str;
	}

	public static long codeToId(String code) {
		char chs[]=code.toCharArray();
		long res=0L;
		for(int i=0; i < chs.length; i++) {
			int ind=0;
			for(int j=0; j < binLen; j++) {
				if(chs[i] == r[j]) {
					ind=j;
					break;
				}
			}
			if(chs[i] == b) {
				break;
			}
			if(i > 0) {
				res=res * binLen + ind;
			} else {
				res=ind;
			}
		}
		return res;
	}

	public static void main(String[] args) {
        System.out.println(toSerialCode(Long.valueOf(StringUtils.getSixRandomNumber())));
    }
}