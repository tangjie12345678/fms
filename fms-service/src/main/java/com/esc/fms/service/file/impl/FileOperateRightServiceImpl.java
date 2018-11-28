package com.esc.fms.service.file.impl;

import com.esc.fms.dao.FileOperateRightMapper;
import com.esc.fms.entity.FileOperateRight;
import com.esc.fms.service.file.FileOperateRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
@Service("fileOperateRightService")
public class FileOperateRightServiceImpl implements FileOperateRightService {

    @Autowired
    private FileOperateRightMapper fileOperateRightMapper;

    public int insertRecord(FileOperateRight fileOperateRight) {
        return fileOperateRightMapper.insert(fileOperateRight);
    }

    public List<FileOperateRight> selectByFileID(Integer fileID) {
        return fileOperateRightMapper.selectByFileID(fileID);
    }

    public int deleteByFileIDandOpRight(Integer fileID, String opRight) {
        return fileOperateRightMapper.deleteByFileIDandOpRight(fileID,opRight);
    }

    public int deleteByFileID(Integer fileID) {
        return fileOperateRightMapper.deleteByFileID(fileID);
    }


}
