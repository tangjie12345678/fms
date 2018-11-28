package com.esc.fms.dao;

import com.esc.fms.entity.FileRefStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileRefStaffMapper {
    int deleteByPrimaryKey(Long recordID);

    int deleteByFileID(Integer fileID);

    int deleteByFileIDandStaffID(@Param("fileID") Integer fileID,@Param("staffID") Integer staffID );

    int insert(FileRefStaff record);

    int insertSelective(FileRefStaff record);

    FileRefStaff selectByPrimaryKey(Long recordID);

    int updateByPrimaryKeySelective(FileRefStaff record);

    int updateByPrimaryKey(FileRefStaff record);

    List<FileRefStaff> selectByFileID(Integer fileID);

}