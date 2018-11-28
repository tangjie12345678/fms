package com.esc.fms.service.base;

import com.esc.fms.entity.DeptRefStaff;

import java.util.List;

/**
 * Created by tangjie on 2016/12/31.
 */
public interface DeptRefStaffService {

    int insert(DeptRefStaff record);

    boolean insertMultiRecord(List<DeptRefStaff> drsList);

    List<Integer> getDeptsByStaffRecordID(Integer staffRecordID);

    int deleteByStaffRecordID(Integer staffRecordID);

    int deleteByStaffRecordIDAndDeptID(Integer staffRecordID,Integer deptID);

}
