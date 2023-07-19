package com.example.demo.repository;

import com.example.demo.model.UploadInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadInfoRepository extends JpaRepository<UploadInfo, Integer> {
}
