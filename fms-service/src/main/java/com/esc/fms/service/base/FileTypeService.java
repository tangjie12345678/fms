package com.esc.fms.service.base;

import com.esc.fms.entity.FileType;

import java.util.List;

/**
 * Created by tangjie on 2017/4/8.
 */
public interface FileTypeService {

    List<FileType> getAllFileType();

    int getRecordCount();

    List<FileType> getFileTypePageList(int offset,int pageSize);

    FileType getFileTypeByID(Integer fileTypeID);

    int addFileType(FileType fileType);

    int updateByFileTypeID(FileType record);

    boolean delFileTypes(List<Integer> fileTypeIDs);

}
