package com.esc.fms.service.base.impl;

import com.esc.fms.dao.DeptRefStaffMapper;
import com.esc.fms.entity.DeptRefStaff;
import com.esc.fms.service.base.DeptRefStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2016/12/31.
 */

@Service("deptRefStaffService")
public class DeptRefStaffServiceImpl implements DeptRefStaffService {

    @Autowired
    private DeptRefStaffMapper deptRefStaffMapper;

    public int insert(DeptRefStaff record) {
        return deptRefStaffMapper.insert(record);
    }

    public boolean insertMultiRecord(List<DeptRefStaff> drsList){
        for(DeptRefStaff drf : drsList){
            int recordID = insert(drf);
        }
        return true;
    }

    public List<Integer> getDeptsByStaffRecordID(Integer staffRecordID) {
        return deptRefStaffMapper.getDeptsByStaffRecordID(staffRecordID);
    }

    public int deleteByStaffRecordID(Integer staffRecordID) {
        return deptRefStaffMapper.deleteByStaffRecordID(staffRecordID);
    }

    public int deleteByStaffRecordIDAndDeptID(Integer staffRecordID, Integer deptID) {
        return deptRefStaffMapper.deleteByStaffRecordIDAndDeptID(staffRecordID,deptID);
    }

}
