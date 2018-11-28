package com.esc.fms.service.file;

import com.esc.fms.entity.FileOperateRight;

import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
public interface FileOperateRightService {

    int insertRecord(FileOperateRight fileOperateRight);

    List<FileOperateRight> selectByFileID(Integer fileID);

    int deleteByFileIDandOpRight(Integer fileID,String opRight);

    int deleteByFileID(Integer fileID);
}
