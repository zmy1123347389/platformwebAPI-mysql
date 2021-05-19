package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.behere.common.constant.Constant;
import com.behere.common.utils.RedisUtil;
import com.behere.common.utils.StringUtils;
import com.behere.video.dao.UserDao;
import com.behere.video.domain.FaceTime;
import com.behere.video.domain.IM;
import com.behere.video.model.UserModel;
import com.behere.video.service.ImService;
import com.behere.video.service.UserService;

/**
 * @author: Behere
 */
@Service
public class ImServiceImpl implements ImService {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int talk(FaceTime faceTime, IM im) {
        int status = 0;
        im.setCanPayNextTime(true);
        String businessId = StringUtils.getUUID();
        try {
            UserModel customer = null;
            UserModel servicer = null;
            UserModel caller = userService.queryUserById(faceTime.getCallerId());
            UserModel called = userService.queryUserById(faceTime.getCalledId());
            if (caller.getAuth() == Constant.AUTH) {
                servicer = caller;
                customer = called;
            } else {
                servicer = called;
                customer = caller;
            }
            if (customer.getBalance() * Constant.DIAMOND_TO_FLOWER_RATE < servicer.getServicePrice()) {
                return Constant.FAIL;
            }
            if (StringUtils.isEmpty(faceTime.getBusinessId())) {
                status = userService.saveDealInformation(customer.getId(), servicer.getId(), servicer.getServicePrice(), Constant.WATCH_VIDEO, businessId);
                im.setBusinessId(businessId);
            } else {
                status = updateAccountBook(customer.getId(), servicer.getId(), servicer.getServicePrice(), faceTime.getBusinessId());
                im.setBusinessId(faceTime.getBusinessId());
            }
            UserModel  customer1 = userService.queryUserById(customer.getId());
            UserModel  servicer1 = userService.queryUserById(servicer.getId());
            if ((customer1.getBalance() * Constant.DIAMOND_TO_FLOWER_RATE) < servicer1.getServicePrice()) {
                im.setCanPayNextTime(false);
            }
            im.setCustomerBalance(customer1.getBalance());
            im.setDiamondToFlowerRate(Constant.DIAMOND_TO_FLOWER_RATE);
            im.setServicerId(servicer1.getId());
            im.setCustomerId(customer1.getId());
            im.setServicePrice(servicer1.getServicePrice());
            redisUtil.set("behere.appuser.facetime." + im.getCustomerId(), String.valueOf(im.getCustomerId()), 65L);
            redisUtil.set("behere.appuser.facetime." + im.getServicerId(), String.valueOf(im.getServicerId()), 65L);
            userService.updateUserFaceTime(im.getCustomerId(), im.getServicerId(), Constant.FACE_TIMING);
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int updateAccountBook(long fromUser, long toUser, long flower, String businessId) {
        try {
            userService.lockUser(fromUser, toUser);
            int reduceRow = userDao.reduceBalance(fromUser, flower / Constant.DIAMOND_TO_FLOWER_RATE);
            if (reduceRow == 0) {
                return Constant.FAIL;
            }
            userDao.addFlower(toUser, flower / 2);
            userDao.updateAccountBookByBusinessId(flower, businessId);
            return Constant.SUCCESS;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}