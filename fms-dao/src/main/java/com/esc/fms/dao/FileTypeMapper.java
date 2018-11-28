package com.esc.fms.dao;

import com.esc.fms.entity.FileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileTypeMapper {
    int deleteByPrimaryKey(Integer fileTypeID);

    int disableFileType(Integer fileTypeID);

    int insert(FileType record);

    int insertSelective(FileType record);

    FileType selectByPrimaryKey(Integer fileTypeID);

    int updateByPrimaryKeySelective(FileType record);

    int updateByPrimaryKey(FileType record);

    List<FileType> getALLFileType();

    List<FileType> getFileTypePageList(@Param("offset") int offset,@Param("pageSize") int pageSize);

    int getRecordCount();

}