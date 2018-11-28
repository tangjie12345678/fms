package com.esc.fms.service.base;

import com.esc.fms.entity.Department;
import com.esc.fms.entity.HDepartment;
import com.esc.fms.entity.Staff;

import java.util.List;

/**
 * Created by tangjie on 2016/12/27.
 */
public interface DepartmentService {

    Department getTopDeptInfo();

    List<Department> selectByParentID(Integer parentID);

    List<Department> getAllDepartment();

    HDepartment getHierarchyDepartment();

    int insert(Department record);

    Department selectByPrimaryKey(Integer deptID);

    int updateByPrimaryKey(Department record);

    int deleteDepartment(Integer deptID);

}
