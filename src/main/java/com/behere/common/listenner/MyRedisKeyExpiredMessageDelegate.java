package com.behere.common.listenner;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.behere.common.constant.Constant;
import com.behere.video.dao.UserDao;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
@Component
public class MyRedisKeyExpiredMessageDelegate implements MessageListener, ServletContextListener {
    public final static String LISTENER_PATTERN = "__key*__:*";

    private static WebApplicationContext context;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        UserDao userDao = context.getBean(UserDao.class);
        String messageBody = new String(message.getBody());
        String[] array = messageBody.split("\\.");
        String expiredUser = array[array.length - 1];
        userDao.updateUserFaceTime(Long.valueOf(expiredUser), 0, Constant.NO_FACE_TIMING);
    }
}
