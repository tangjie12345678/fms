package com.esc.fms.entity;

public class UserListElement {
    private Integer userID;

    private String userName;

    private Integer staffRecordID;

    private String staffName;

    private String locked;

    private String description;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getStaffRecordID() {
        return staffRecordID;
    }

    public void setStaffRecordID(Integer staffRecordID) {
        this.staffRecordID = staffRecordID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}