package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.behere.video.dao.UserLabelDao;
import com.behere.video.domain.UserLabel;
import com.behere.video.service.UserLabelService;

import java.util.List;

@Service
public class UserLabelServiceImpl implements UserLabelService {
    @Autowired
    private UserLabelDao userLabelDao;

    @Override
    public int saveUserLabel(UserLabel userLabel) {
        return userLabelDao.saveUserLabel(userLabel);
    }

    @Override
    public List<UserLabel> queryUserLabelByUserId(long userId) {
        return userLabelDao.queryUserLabelByUserId(userId);
    }
}
