package com.esc.fms.dao;

import com.esc.fms.entity.FileOperateRight;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileOperateRightMapper {
    int deleteByPrimaryKey(Long recordID);

    int insert(FileOperateRight record);

    int insertSelective(FileOperateRight record);

    FileOperateRight selectByPrimaryKey(Long recordID);

    int updateByPrimaryKeySelective(FileOperateRight record);

    int updateByPrimaryKey(FileOperateRight record);

    List<FileOperateRight> selectByFileID(Integer fileID);

    int deleteByFileIDandOpRight(@Param("fileID") Integer fileID,@Param("opRight") String opRight);

    int deleteByFileID(Integer fileID);
}