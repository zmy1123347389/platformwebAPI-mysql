package com.behere.video.service;

import com.behere.video.domain.FaceTime;
import com.behere.video.domain.IM;

/**
 * @author: Behere
 */
public interface ImService {

    int talk(FaceTime faceTime, IM im);

    int updateAccountBook(long fromUser, long toUser, long flower, String businessId);
}
