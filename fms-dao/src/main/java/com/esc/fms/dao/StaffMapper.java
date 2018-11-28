package com.esc.fms.dao;

import com.esc.fms.entity.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffMapper {
    int deleteByPrimaryKey(Integer staffRecordID);

    int deleteStaff(Integer staffRecordID);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer staffRecordID);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> selectForDropdownList(@Param("staffName") String staffName);

    List<Staff> selectByDeptID(Integer deptID);
}