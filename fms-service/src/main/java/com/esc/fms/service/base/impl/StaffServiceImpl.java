package com.esc.fms.service.base.impl;

import com.esc.fms.dao.StaffListElementMapper;
import com.esc.fms.dao.StaffMapper;
import com.esc.fms.entity.*;
import com.esc.fms.service.base.DepartmentService;
import com.esc.fms.service.base.DeptRefStaffService;
import com.esc.fms.service.base.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/8.
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

    private static final Logger logger = LoggerFactory.getLogger(StaffServiceImpl.class);

    @Autowired
    private StaffListElementMapper staffListElementMapper;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private DeptRefStaffService deptRefStaffService;

    @Autowired
    private DepartmentService departmentService;

    public List<StaffListElement> selectStaffByConditions(String staffID, String staffName, String sex, int offset,int pageSize) {
        return staffListElementMapper.selectStaffByConditions(staffID, staffName, sex,offset,pageSize);
    }

    public Integer getCountByConditions(String staffID,String staffName, String sex){
        return staffListElementMapper.getCountByConditions(staffID, staffName, sex);
    }

    public boolean addStaff(Staff staff,List<Integer> depts){

        int count = staffMapper.insert(staff);

        logger.debug("新生成的StaffRecordID()：" + staff.getStaffRecordID());

        List<DeptRefStaff> drsList = new ArrayList<DeptRefStaff>();

        for (Integer deptID : depts){
            DeptRefStaff drf = new DeptRefStaff();
            drf.setDeptID(deptID);
            drf.setStaffRecordID(staff.getStaffRecordID());
            drsList.add(drf);
        }

        deptRefStaffService.insertMultiRecord(drsList);

        return true;
    }

    public Staff selectByPrimaryKey(Integer staffRecordID) {
        return staffMapper.selectByPrimaryKey(staffRecordID);
    }

    public boolean editStaff(Staff staff,List<Integer> newdepts,List<Integer> olddepts){

        Integer count = staffMapper.updateByPrimaryKey(staff);

        for(Integer deptID:olddepts){
            deptRefStaffService.deleteByStaffRecordIDAndDeptID(staff.getStaffRecordID(),deptID);
        }

        List<DeptRefStaff> drsList = new ArrayList<DeptRefStaff>();

        for (Integer deptID : newdepts){
            DeptRefStaff drf = new DeptRefStaff();
            drf.setDeptID(deptID);
            drf.setStaffRecordID(staff.getStaffRecordID());
            drsList.add(drf);
        }

        deptRefStaffService.insertMultiRecord(drsList);

        return true;
    }

    public int updateByPrimaryKey(Staff record) {
        return staffMapper.updateByPrimaryKey(record);
    }

    public boolean delMultiStaff(List<Integer> staffRecordIDs) {

        for(Integer staffRecordID:staffRecordIDs){
            staffMapper.deleteStaff(staffRecordID);
        }
        return true;
    }

    public List<Staff> selectForDropdownList(String staffName) {
        return staffMapper.selectForDropdownList(staffName);
    }

    public TreeNode getDeptStaff() {

        Department dept = departmentService.getTopDeptInfo();

        TreeNode node = new TreeNode();

        node.setId(String.valueOf(dept.getDeptID()));
        node.setText(dept.getDeptName());
        node.setAttributes("department");

        travelDepartment(node,dept);

        return node;
    }

    public void travelDepartment(TreeNode parent,Department dept){

            List<Department> depts = departmentService.selectByParentID(dept.getDeptID());
            if(depts != null){
                for(Department d:depts){
                    TreeNode node = new TreeNode();
                    node.setId(String.valueOf(d.getDeptID()));
                    node.setText(String.valueOf(d.getDeptName()));
                    node.setState("closed");
                    node.setAttributes("department");
                    parent.addChild(node);

                    fillStaffForDept(node);

                    if(d.getIsLeaf() == false) {
                        travelDepartment(node,d);
                    }
                }
            }
    }

    public void fillStaffForDept(TreeNode parent)
    {
        List<Staff> staffs = staffMapper.selectByDeptID(Integer.valueOf(parent.getId()));

        for(Staff staff : staffs)
        {
            TreeNode node = new TreeNode();
            node.setId(parent.getId() + "_" + String.valueOf(staff.getStaffRecordID()));
            node.setText(staff.getStaffName());
            node.setAttributes("staff");
            parent.addChild(node);
        }
    }

    public List<Staff> getStaffByDeptID(Integer deptID) {
        return staffMapper.selectByDeptID(deptID);
    }


}
