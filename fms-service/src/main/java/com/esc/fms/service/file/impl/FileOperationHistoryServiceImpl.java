package com.esc.fms.service.file.impl;

import com.esc.fms.dao.FileOperationHistoryMapper;
import com.esc.fms.entity.FileOperationHistory;
import com.esc.fms.service.file.FileOperationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by tangjie on 2017/9/17.
 */

@Service("fileOperationHistoryService")
public class FileOperationHistoryServiceImpl implements FileOperationHistoryService {

    @Autowired
    private FileOperationHistoryMapper fileOperationHistoryMapper;

    public int insert(FileOperationHistory record) {
        return fileOperationHistoryMapper.insert(record);
    }
}
