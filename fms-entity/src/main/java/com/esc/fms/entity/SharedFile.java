package com.esc.fms.entity;

import java.util.Date;

public class SharedFile {
    private Integer fileID;

    private String fileName;

    private String fileExtType;

    private String mimeType;

    private Long fileSize;

    private Integer fileTypeID;

    private String folderName;

    private String filePath;

    private String shareType;

    private Integer creator;

    private Date createTime;

    private Integer lastUpdator;

    private Date updateTime;

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileExtType() {
        return fileExtType;
    }

    public void setFileExtType(String fileExtType) {
        this.fileExtType = fileExtType == null ? null : fileExtType.trim();
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType == null ? null : mimeType.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getFileTypeID() {
        return fileTypeID;
    }

    public void setFileTypeID(Integer fileTypeID) {
        this.fileTypeID = fileTypeID;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType == null ? null : shareType.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator == null ? null : creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(Integer lastUpdator) {
        this.lastUpdator = lastUpdator == null ? null : lastUpdator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}