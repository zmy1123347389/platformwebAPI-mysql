package com.behere.common.utils.exam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * sha加密
 *
 * @author nanbo
 */
public class ShaEncryptUtil {
    private static String token = "lawapp_mobile";

    /**
     * 对字符串进行字典排序和sha加密
     *
     * @param str
     * @return
     */
    public static String sha(Object[] strs) {
        String shaStr = "";
        try {
            if (strs != null && strs.length > 0) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                byte[] shaMd = md.digest(dictionarySort(strs).getBytes());
                shaStr = byteToString(shaMd);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return shaStr;
    }

    /**
     * 将byte数组转换为十六进制
     *
     * @param bytes
     * @return
     */
    public static String byteToString(byte[] bytes) {
        String result = "";
        for (int i = 0; i < bytes.length; i++) {
            int temp = bytes[i] & 0xff;
            String tempStr = Integer.toHexString(temp);
            if (tempStr.length() < 2) {
                result += "0" + tempStr;
            } else {
                result += tempStr;
            }
        }
        return result;
    }

    /**
     * 字符串字典排序
     *
     * @param strs
     * @return
     */
    public static String dictionarySort(Object[] strs) {
        String sortStr = "";
        if (strs != null && strs.length > 0) {
            Arrays.sort(strs);
            for (int i = 0; i < strs.length; i++) {
                sortStr += strs[i];
            }
        }
        return sortStr;
    }

    /**
     * 根据用户名和时间戳返回生成后的token
     *
     * @param timestamp
     * @param userId
     * @return
     */
    public static String convertSignature(String timestamp, String userId) {
        List<String> list = new ArrayList<String>();
        list.add(userId);
        list.add(timestamp);
        list.add(token);
        return sha(list.toArray());
    }
    
    public static void main(String[] args) {
    	String convertSignature = convertSignature("1602751390498", "1004");
    	System.out.println(convertSignature);
	}
}