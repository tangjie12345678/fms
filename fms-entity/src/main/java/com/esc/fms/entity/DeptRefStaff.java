package com.esc.fms.entity;

public class DeptRefStaff {
    private Integer recordID;

    private Integer deptID;

    private Integer staffRecordID;

    public Integer getRecordID() {
        return recordID;
    }

    public void setRecordID(Integer recordID) {
        this.recordID = recordID;
    }

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getStaffRecordID() {
        return staffRecordID;
    }

    public void setStaffRecordID(Integer staffRecordID) {
        this.staffRecordID = staffRecordID;
    }
}