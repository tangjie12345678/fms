package com.esc.fms.dao;

import com.esc.fms.entity.StaffListElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffListElementMapper {

    List<StaffListElement> selectStaffByConditions(@Param("staffID") String staffID, @Param("staffName") String staffName,
                                                   @Param("sex") String sex,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    Integer getCountByConditions(@Param("staffID") String staffID, @Param("staffName") String staffName,@Param("sex") String sex);

}