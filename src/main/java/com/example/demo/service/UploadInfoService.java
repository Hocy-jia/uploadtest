package com.example.demo.service;


import com.example.demo.model.UploadInfo;
import com.example.demo.repository.UploadInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadInfoService {

    private final UploadInfoRepository uploadInfoRepository;
    private static final Logger logger = LoggerFactory.getLogger(UploadInfoService.class);

    @Autowired
    public UploadInfoService(UploadInfoRepository uploadInfoRepository) {
        this.uploadInfoRepository = uploadInfoRepository;
    }

    public void saveUploadInfo(UploadInfo uploadInfo) {
        try {
            UploadInfo savedUploadInfo = uploadInfoRepository.save(uploadInfo);
            logger.info("数据保存成功！保存后的ID为：" + savedUploadInfo.getId());
        } catch (Exception e) {
            logger.error("保存数据时出现异常：", e);
        }
    }
}
