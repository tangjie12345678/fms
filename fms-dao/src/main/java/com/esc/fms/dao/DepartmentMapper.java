package com.esc.fms.dao;

import com.esc.fms.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer deptID);

    int deleteDepartment(Integer deptID);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer deptID);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

        /*
    *  get the top department
     */

    Department getTopDeptInfo();

    /*
    * get sub Department info
     */

    List<Department> selectByParentID(Integer parentID);
}