package com.esc.fms.entity;

public class FileOperateRight {
    private Long recordID;

    private Integer fileID;

    private String opRight;

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

    public String getOpRight() {
        return opRight;
    }

    public void setOpRight(String opRight) {
        this.opRight = opRight == null ? null : opRight.trim();
    }
}