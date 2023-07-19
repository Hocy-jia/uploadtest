package com.example.demo.model;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class UploadInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String info;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(columnDefinition = "VARCHAR(10)") // 修改数据库字段类型为 VARCHAR(10)
    private String startTime;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(columnDefinition = "VARCHAR(10)") // 修改数据库字段类型为 VARCHAR(10)
    private String endTime;


    private String location;
    private String bannerImgUrl;
    private String posterImgUrl;
    private String publisher;
    private int orderIndex;

    // Getters and setters

    public UploadInfo() {
    }

    // Constructor
    public UploadInfo(String title, String info, LocalDate startTime, LocalDate endTime,
                      String location, String bannerImgUrl, String posterImgUrl,
                      String publisher, int orderIndex) {
        this.title = title;
        this.info = info;
        this.startTime = startTime.toString(); // 将 LocalDate 转换为字符串并存储
        this.endTime = endTime.toString();     // 将 LocalDate 转换为字符串并存储
        this.location = location;
        this.bannerImgUrl = bannerImgUrl;
        this.posterImgUrl = posterImgUrl;
        this.publisher = publisher;
        this.orderIndex = orderIndex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime.toString(); // 将 LocalDate 转换为字符串并存储
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime.toString(); // 将 LocalDate 转换为字符串并存储
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBannerImgUrl() {
        return bannerImgUrl;
    }

    public void setBannerImgUrl(String bannerImgUrl) {
        this.bannerImgUrl = bannerImgUrl;
    }

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public void setPosterImgUrl(String posterImgUrl) {
        this.posterImgUrl = posterImgUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public int getId() {
        return id;
    }
}
