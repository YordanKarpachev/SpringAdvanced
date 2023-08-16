package com.example.dbImages.dbImages.web;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadModel {

    private MultipartFile img;

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }
}
