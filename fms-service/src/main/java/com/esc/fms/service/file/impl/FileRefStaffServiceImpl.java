package com.esc.fms.service.file.impl;

import com.esc.fms.dao.FileRefStaffMapper;
import com.esc.fms.entity.FileRefStaff;
import com.esc.fms.service.file.FileRefStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
@Service("fileRefStaffService")
public class FileRefStaffServiceImpl implements FileRefStaffService {

    @Autowired
    private FileRefStaffMapper fileRefStaffMapper;

    public int insertFileRefStaff(FileRefStaff fileRefStaff) {
        return fileRefStaffMapper.insert(fileRefStaff);
    }

    public List<FileRefStaff> selectByFileID(Integer fileID) {
        return fileRefStaffMapper.selectByFileID(fileID);
    }

    public int deleteByFileID(Integer fileID) {
        return fileRefStaffMapper.deleteByFileID(fileID);
    }

    public int deleteByFileIDandStaffID(Integer fileID, Integer staffID) {
        return fileRefStaffMapper.deleteByFileIDandStaffID(fileID,staffID);
    }
}
