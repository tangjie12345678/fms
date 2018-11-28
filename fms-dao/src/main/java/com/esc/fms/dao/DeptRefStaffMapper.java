package com.esc.fms.dao;

import com.esc.fms.entity.DeptRefStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptRefStaffMapper {
    int deleteByPrimaryKey(Integer recordID);

    int deleteByStaffRecordIDAndDeptID(@Param("staffRecordID") Integer staffRecordID,@Param("deptID") Integer deptID);

    int deleteByStaffRecordID(Integer staffRecordID);

    int insert(DeptRefStaff record);

    int insertSelective(DeptRefStaff record);

    DeptRefStaff selectByPrimaryKey(Integer recordID);

    int updateByPrimaryKeySelective(DeptRefStaff record);

    int updateByPrimaryKey(DeptRefStaff record);

    List<Integer> getDeptsByStaffRecordID(Integer staffRecordID);
}