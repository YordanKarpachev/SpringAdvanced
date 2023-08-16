package com.example.dbImages.dbImages.model;

public class FileDownloadModel {

    private final   byte[] fileData;
    private final String contentType;


    private final String fileName;

    public FileDownloadModel(byte[] fileData, String contentType, String getFilName) {
        this.fileData = fileData;
        this.contentType = contentType;
        this.fileName = getFilName;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileName() {
        return fileName;
    }
}
