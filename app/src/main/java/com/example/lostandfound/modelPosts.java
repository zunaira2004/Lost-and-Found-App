package com.example.lostandfound;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class modelPosts {
    private String itemName;
    private String postedBy;
    private String description;
    private String messege;
    private String status;
    private String location;
    private String postTime;
    private String itemPicUrl;
    private String PostedByPicUrl;
    private String userId;
    public modelPosts(){}
    public modelPosts(String itemName,String postedBy,String description,String messege,String status,String location,String PostedByPicUrl,String userId,String itemPicUrl) {
        this.itemName=itemName;
        this.postedBy=postedBy;
        this.description=description;
        this.messege=messege;
        this.status=status;
        this.location=location;
        this.PostedByPicUrl=PostedByPicUrl;
        this.itemPicUrl=itemPicUrl;
        this.userId=userId;

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.postTime = now.format(formatter);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setPostTime(String currentTime)
    {
        this.postTime = currentTime;
    }

    public String getPostTime() {
        return this.postTime;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public void setItemPostImageUrl(String imageUrl) {
        itemPicUrl=imageUrl;
    }

    public String getItemPostImageUrl() {
        return itemPicUrl;
    }

    public void setPostedByPicUrl(String profileImageUrl) {
        PostedByPicUrl=profileImageUrl;
    }

    public String getPostedByPicUrl() {
        return PostedByPicUrl;
    }
}
