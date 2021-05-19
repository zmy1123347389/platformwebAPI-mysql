package com.behere.testDemo;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.behere.common.utils.pay.handler.PrepayIdRequestHandler;
import com.behere.video.domain.WXPay;

import java.security.MessageDigest;
import java.util.Random;

public class TestDemo {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() / 1000);
    }
}