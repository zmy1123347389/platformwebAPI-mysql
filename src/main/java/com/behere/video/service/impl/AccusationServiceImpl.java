package com.behere.video.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.behere.common.config.FTPConfig;
import com.behere.common.constant.Constant;
import com.behere.common.utils.FTPUtil;
import com.behere.common.utils.StringUtils;
import com.behere.common.utils.UploadUtils;
import com.behere.video.dao.AccusationDao;
import com.behere.video.domain.Accusation;
import com.behere.video.service.AccusationService;

import java.util.List;

@Service
public class AccusationServiceImpl implements AccusationService {
    @Autowired
    private AccusationDao accusationDao;

    @Autowired
    private FTPConfig ftpConfig;

    @Override
    public List<Accusation> queryAccusations() {
        return accusationDao.queryAccusations();
    }

    @Override
    public int saveAccusationInformation(String id, long reportUserId, long reportedUserId, int accusationId, String content) {
        return accusationDao.saveAccusationInformation(id, reportUserId, reportedUserId, accusationId, content);
    }

    @Override
    public int saveAccusationPic(String id, String reportId, String pic) {
        return accusationDao.saveAccusationPic(id, reportId, pic);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int saveAccusation(long reportUserId, long reportedUserId, int accusationId, MultipartFile[] files, String content) {
        try {
            String id = StringUtils.getUUID();
            saveAccusationInformation(id, reportUserId, reportedUserId, accusationId, content);
            for (MultipartFile file : files) {
                String oldName = file.getOriginalFilename();
                String picNewName = UploadUtils.generateRandonFileName(oldName);
                FTPUtil.pictureUploadByConfig(ftpConfig, picNewName, file.getInputStream());
                saveAccusationPic(StringUtils.getUUID(), id, picNewName);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return Constant.SUCCESS;
    }
}
