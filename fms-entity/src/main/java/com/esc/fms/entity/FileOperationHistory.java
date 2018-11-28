package com.esc.fms.entity;

import java.util.Date;

public class FileOperationHistory {
    private Long recordID;

    private Integer fileID;

    private String fileName;

    private String fileExtType;

    private String filePath;

    private String folderName;

    private String fileOpType;

    private Integer creator;

    private Date creatTime;

    private Integer updator;

    private Date updateTime;

    public Long getRecordID() {
        return recordID;
    }

    public void setRecordID(Long recordID) {
        this.recordID = recordID;
    }

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName == null ? null : folderName.trim();
    }

    public String getFileOpType() {
        return fileOpType;
    }

    public void setFileOpType(String fileOpType) {
        this.fileOpType = fileOpType == null ? null : fileOpType.trim();
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}