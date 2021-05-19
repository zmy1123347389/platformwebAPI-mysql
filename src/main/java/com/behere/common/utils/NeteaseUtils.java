package com.behere.common.utils;

import java.nio.charset.Charset;
import java.util.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;
import com.behere.common.constant.Constant;
import com.behere.common.constant.MsgConstant;

/**
 * @author: Behere
 */
public class NeteaseUtils extends Thread {

    public static String createNeteaseAccount(String accid) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String token = "";
        try {
            String url = Constant.NETEASE_CREATE_API;
            HttpPost httpPost = new HttpPost(url);
            String appKey = Constant.NETEASE_APP_KEY;
            String appSecret = Constant.NETEASE_APP_SECRET;
            String nonce = StringUtils.getSixRandomNumber();
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("accid", accid));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            // 打印执行结果
            token = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return token;
    }

    public static String updateNetease(String apiUrl, String userId, String paramName, String value) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(apiUrl);
            String appKey = Constant.NETEASE_APP_KEY;
            String appSecret = Constant.NETEASE_APP_SECRET;
            String nonce = StringUtils.getSixRandomNumber();
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("accid", userId));
            nvps.add(new BasicNameValuePair(paramName, value));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return result;
    }

    public static String createNeteaseVideoUser(long userId, String token) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(Constant.NETEASE_IM_CREATE);
            String appKey = Constant.NETEASE_APP_KEY;
            String appSecret = Constant.NETEASE_APP_SECRET;
            String nonce = StringUtils.getSixRandomNumber();
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            // 设置请求的参数
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("accid", userId);
            jsonObject.put("type", 2);
            jsonObject.put("token", token);
            httpPost.setEntity(new StringEntity(jsonObject.toJSONString(), Charset.forName("UTF-8")));
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return result;
    }


    public static String sendNetease(String msg, long toUser) throws Exception {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String token = "";
        try {
           String url = "https://api.netease.im/nimserver/msg/sendMsg.action";
       //     String url = "https://api.netease.im/nimserver/msg/sendAttachMsg.action";
            HttpPost httpPost = new HttpPost(url);
            String appKey = Constant.NETEASE_APP_KEY;
            String appSecret = Constant.NETEASE_APP_SECRET;
            String nonce = StringUtils.getSixRandomNumber();
            String curTime = String.valueOf((new Date()).getTime() / 1000L);
            String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);
            // 设置请求的header
            httpPost.addHeader("AppKey", appKey);
            httpPost.addHeader("Nonce", nonce);
            httpPost.addHeader("CurTime", curTime);
            httpPost.addHeader("CheckSum", checkSum);
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            // 设置请求的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//            nvps.add(new BasicNameValuePair("from", "1"));
//            nvps.add(new BasicNameValuePair("to", String.valueOf(toUser)));
//            nvps.add(new BasicNameValuePair("msgtype", "0"));
//            nvps.add(new BasicNameValuePair("attach", msg));
//            nvps.add(new BasicNameValuePair("save", "2"));



            nvps.add(new BasicNameValuePair("from", "1"));
            nvps.add(new BasicNameValuePair("to", String.valueOf(toUser)));
            nvps.add(new BasicNameValuePair("ope", "0"));
            nvps.add(new BasicNameValuePair("type", "100"));
            nvps.add(new BasicNameValuePair("body", "{\"type\":8}"));
            nvps.add(new BasicNameValuePair("ext", msg));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            // 打印执行结果
            token = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(token);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return token;
    }
    
    public static String setMsgExtMap(Long fromUserId, Long toUserId, String fromUserNickName, String toUserNickName, Long flower, Integer type, String title) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("fromUserId", fromUserId);
    	map.put("toUserId", toUserId);
    	map.put("fromUserNickName", fromUserNickName);
    	map.put("toUserNickName", toUserNickName);
    	if (flower != null) {
            map.put("diamond", flower / 10);
        }
    	map.put("flower", flower);
    	map.put("type", type);
    	map.put("title", title);
    	return JSONObject.toJSONString(map);
    }

    public static void sendMsg(String msg, long toUser) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sendNetease(msg, toUser);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}