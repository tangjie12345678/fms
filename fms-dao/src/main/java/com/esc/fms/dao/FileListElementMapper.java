package com.esc.fms.dao;

import com.esc.fms.entity.FileListElement;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileListElementMapper {

    List<FileListElement> getFileListByConditions(@Param("fileName") String fileName,@Param("fileType") Integer fileType, @Param("folderName") String folderName,@Param("creator") String creator,
                                                  @Param("updator") String updator,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    Integer getCountByConditions(@Param("fileName") String fileName,@Param("fileType") Integer fileType, @Param("folderName") String folderName,
                                 @Param("creator") String creator,@Param("updator") String updator);
}