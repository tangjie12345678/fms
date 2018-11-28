package com.esc.fms.service.base.impl;

import com.esc.fms.dao.DepartmentMapper;
import com.esc.fms.entity.Department;
import com.esc.fms.entity.HDepartment;
import com.esc.fms.service.base.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/27.
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper deptMapper;

    public Department getTopDeptInfo() {
        return deptMapper.getTopDeptInfo();
    }

    public List<Department> selectByParentID(Integer parentID) {
        return deptMapper.selectByParentID(parentID);
    }

    public List<Department> getAllDepartment(){
        List<Department> deptList = new ArrayList<Department>();
        Department dept = getTopDeptInfo();
        travelDepartment(deptList,dept);
        return deptList;
    }

    public void travelDepartment(List<Department> deptList,Department dept){
        deptList.add(dept);
        if(dept.getIsLeaf() == false){
            List<Department> depts = deptMapper.selectByParentID(dept.getDeptID());
            if(depts != null){
                for(Department d:depts){
                    travelDepartment(deptList,d);
                }
            }
        }
    }

    public HDepartment getHierarchyDepartment() {

        Department dept = getTopDeptInfo();
        HDepartment hdept = deptToHDept(dept);
        travelHierarchyDepartment(hdept);
        return hdept;
    }

    public int insert(Department record) {
        return deptMapper.insert(record);
    }

    public Department selectByPrimaryKey(Integer deptID) {
        return deptMapper.selectByPrimaryKey(deptID);
    }

    public int updateByPrimaryKey(Department record) {
        return deptMapper.updateByPrimaryKey(record);
    }

    public int deleteDepartment(Integer deptID) {
        return deptMapper.deleteDepartment(deptID);
    }

    public void travelHierarchyDepartment(HDepartment hdept) {

        if(hdept.getIsLeaf().equals("否")){
            List<Department> depts = deptMapper.selectByParentID(hdept.getDeptID());
            if(depts != null){
                List<HDepartment> hdepts = new ArrayList<HDepartment>();
                for(Department dept:depts){
                    HDepartment chdept = deptToHDept(dept);
                    travelHierarchyDepartment(chdept);
                    hdepts.add(chdept);
                }
                hdept.setChildren(hdepts);
            }
        }

    }

    public HDepartment deptToHDept(Department dept)
    {
        HDepartment hdept = new HDepartment();

        hdept.setDeptID(dept.getDeptID());
        hdept.setParentID(dept.getParentID());
        hdept.setDeptName(dept.getDeptName());
        hdept.setDescription(dept.getDescription());

        if(dept.getIsLeaf() == true)
        {
            hdept.setIsLeaf("是");
        }
        else
        {
            hdept.setIsLeaf("否");
        }


        hdept.setIsEnabled(dept.getIsEnabled());
        hdept.setSortNo(dept.getSortNo());

        return hdept;
    }




}
