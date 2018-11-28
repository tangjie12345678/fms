package com.esc.fms.dao;

import com.esc.fms.entity.FileOperationHistory;

public interface FileOperationHistoryMapper {
    int deleteByPrimaryKey(Long recordID);

    int insert(FileOperationHistory record);

    int insertSelective(FileOperationHistory record);

    FileOperationHistory selectByPrimaryKey(Long recordID);

    int updateByPrimaryKeySelective(FileOperationHistory record);

    int updateByPrimaryKey(FileOperationHistory record);
}