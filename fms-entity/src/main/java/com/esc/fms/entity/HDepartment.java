package com.esc.fms.entity;

import java.util.List;

/**
 * Created by tangjie on 2017/2/6.
 */
public class HDepartment {

    private Integer deptID;

    private Integer parentID;

    private String deptName;

    private String description;

    private String isLeaf;

    private Boolean isEnabled;

    private Integer sortNo;

    private List<HDepartment> children;

    public Integer getDeptID() {
        return deptID;
    }

    public void setDeptID(Integer deptID) {
        this.deptID = deptID;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public List<HDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<HDepartment> children) {
        this.children = children;
    }
}
