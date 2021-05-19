package com.behere.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.behere.common.constant.Constant;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author: Behere
 */
public class UploadVideoUtil {

    public static String getNeteaseVideoInformation(long vid) throws Exception{
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String result = "";
        try {
            HttpPost httpPost = new HttpPost("https://vcloud.163.com/app/vod/video/get");
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
            jsonObject.put("vid", vid);
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
}
