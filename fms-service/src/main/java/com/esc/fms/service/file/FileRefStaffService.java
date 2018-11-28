package com.esc.fms.service.file;

import com.esc.fms.entity.FileRefStaff;

import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
public interface FileRefStaffService {

    int insertFileRefStaff(FileRefStaff fileRefStaff);

    List<FileRefStaff> selectByFileID(Integer fileID);

    int deleteByFileID(Integer fileID);

    int deleteByFileIDandStaffID(Integer fileID,Integer staffID);

}
