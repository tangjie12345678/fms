package com.esc.fms.service.base;

import com.esc.fms.entity.Staff;
import com.esc.fms.entity.StaffListElement;
import com.esc.fms.entity.TreeNode;

import java.util.List;

/**
 * Created by tangjie on 2016/12/8.
 */
public interface StaffService {

    List<StaffListElement> selectStaffByConditions(String staffID, String staffName, String sex, int offset,int pageSize);

    Integer getCountByConditions(String staffID,String staffName, String sex);

    boolean addStaff(Staff staff, List<Integer> depts);

    Staff selectByPrimaryKey(Integer staffRecordID);

    boolean editStaff(Staff staff,List<Integer> newdepts,List<Integer> olddepts);

    int updateByPrimaryKey(Staff record);

    boolean delMultiStaff(List<Integer> staffRecordIDs);

    List<Staff> selectForDropdownList(String staffName);

    TreeNode getDeptStaff();

    List<Staff> getStaffByDeptID(Integer deptID);

}
