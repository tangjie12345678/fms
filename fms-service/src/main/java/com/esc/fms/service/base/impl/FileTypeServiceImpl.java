package com.esc.fms.service.base.impl;

import com.esc.fms.dao.FileTypeMapper;
import com.esc.fms.entity.FileType;
import com.esc.fms.service.base.FileTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2017/4/8.
 */
@Service("fileTypeService")
public class FileTypeServiceImpl implements FileTypeService {

    @Autowired
    private FileTypeMapper fileTypeMapper;

    public List<FileType> getAllFileType() {
        return fileTypeMapper.getALLFileType();
    }

    public int getRecordCount() {
        return fileTypeMapper.getRecordCount();
    }

    public List<FileType> getFileTypePageList(int offset, int pageSize) {
        return fileTypeMapper.getFileTypePageList(offset,pageSize);
    }

    public FileType getFileTypeByID(Integer fileTypeID) {
        return fileTypeMapper.selectByPrimaryKey(fileTypeID);
    }

    public int addFileType(FileType fileType) {
        return fileTypeMapper.insert(fileType);
    }

    public int updateByFileTypeID(FileType record) {
        return fileTypeMapper.updateByPrimaryKey(record);
    }

    public boolean delFileTypes(List<Integer> fileTypeIDs) {

        for(Integer fileTypeID : fileTypeIDs){
            fileTypeMapper.disableFileType(fileTypeID);
        }

        return true;
    }
}
