package com.esc.fms.entity;

public class FileType {
    private Integer fileTypeID;

    private String fileTypeName;

    private String fileTypeDesc;

    private Boolean enabled;

    public Integer getFileTypeID() {
        return fileTypeID;
    }

    public void setFileTypeID(Integer fileTypeID) {
        this.fileTypeID = fileTypeID;
    }

    public String getFileTypeName() {
        return fileTypeName;
    }

    public void setFileTypeName(String fileTypeName) {
        this.fileTypeName = fileTypeName == null ? null : fileTypeName.trim();
    }

    public String getFileTypeDesc() {
        return fileTypeDesc;
    }

    public void setFileTypeDesc(String fileTypeDesc) {
        this.fileTypeDesc = fileTypeDesc == null ? null : fileTypeDesc.trim();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}