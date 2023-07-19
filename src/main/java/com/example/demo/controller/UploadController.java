package com.example.demo.controller;

import com.example.demo.model.UploadInfo;
import com.example.demo.service.UploadInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import java.time.LocalDate;

import java.time.format.DateTimeFormatter;


@Controller
public class UploadController {

    private final UploadInfoService uploadInfoService;
    // 添加成员变量用于存储上传目录路径

    @Value("${upload.directory}")
    private String uploadDirectory;


    @Autowired
    public UploadController(UploadInfoService uploadInfoService) {
        this.uploadInfoService = uploadInfoService;
    }



    @PostMapping("/upload")
    public String handleUpload(
            @RequestParam("title") String title,
            @RequestParam("info") String info,
            @RequestParam("startTime") String startTimeString, // Changed to LocalDate type
            @RequestParam("endTime") String endTimeString,     // Changed to LocalDate type
            @RequestParam("location") String location,
            @RequestParam("bannerImg") MultipartFile bannerImg,
            @RequestParam("posterImg") MultipartFile posterImg,
            @RequestParam("publisher") String publisher,
            @RequestParam("orderIndex") int orderIndex) throws IOException {

        // 将日期字符串转换为 LocalDate 类型
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate startTime = LocalDate.parse(startTimeString, dateFormatter);
        LocalDate endTime = LocalDate.parse(endTimeString, dateFormatter);

        // 处理Banner Image上传
        if (!bannerImg.isEmpty() & !posterImg.isEmpty()) {
            String bannerImgFileName = bannerImg.getOriginalFilename();
            String bannerImgPath = uploadDirectory + bannerImgFileName;

            String posterImgFileName = posterImg.getOriginalFilename();
            String posterImgPath = uploadDirectory + posterImgFileName;

            // 保存图片到服务器或云存储
            saveImageToServer(bannerImg, bannerImgPath);

            // 保存图片到服务器或云存储
            saveImageToServer(posterImg, posterImgPath);

            // 设置图片路径

            String bannerImgUrl = "/images/" + bannerImgFileName;

            String posterImgUrl = "/images/" + posterImgFileName;

            // 创建UploadInfo对象
            UploadInfo uploadInfo = new UploadInfo(title, info, startTime, endTime,
                    location, bannerImgUrl, posterImgUrl, publisher, orderIndex);

            // 保存到数据库
            uploadInfoService.saveUploadInfo(uploadInfo);
        }

//        // 处理Poster Image上传
//        if (!posterImg.isEmpty()) {
//            String posterImgFileName = posterImg.getOriginalFilename();
//            String posterImgPath = uploadDirectory + posterImgFileName;
//
//            // 保存图片到服务器或云存储
//            saveImageToServer(posterImg, posterImgPath);
//
//            // 设置图片路径
//            String posterImgUrl = "/images/" + posterImgFileName;
//            uploadInfo.setPosterImgUrl(posterImgUrl);
//
////            // 创建UploadInfo对象
////            UploadInfo uploadInfo = new UploadInfo(title, info, startTime, endTime, location, null, posterImgUrl,
////                    publisher, orderIndex);
//
////            // 保存到数据库
////            uploadInfoService.saveUploadInfo(uploadInfo);
//        }

        return "redirect:/success"; // 可以重定向到成功页面或其他页面
    }


    private void saveImageToServer(MultipartFile file, String filePath) throws IOException {
        // 保存图片到服务器或云存储
        Path imagePath = Paths.get(filePath);
        Files.copy(file.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
    }
    private static class CustomLocalDateEditor extends PropertyEditorSupport {
        private final DateTimeFormatter formatter;

        public CustomLocalDateEditor(DateTimeFormatter formatter) {
            this.formatter = formatter;
        }

        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(LocalDate.parse(text, formatter));
        }

        @Override
        public String getAsText() {
            Object value = getValue();
            if (value instanceof LocalDate) {
                return ((LocalDate) value).format(formatter);
            }
            return "";
        }
    }
}

