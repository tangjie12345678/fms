package com.esc.fms.service.file;

import com.esc.fms.entity.FileListElement;
import com.esc.fms.entity.SharedFile;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
public interface FileService {

    int insertFile(SharedFile sharedFile);

    List<FileListElement> getFileListByConditions(String fileName,Integer fileType,String folderName,String creator,String updator,Integer offset,Integer pageSize);

    Integer getCountByConditions(String fileName,Integer fileType, String folderName,String creator,String updator);

    ModelMap getFileProperties(Integer fileID);

    void resetProperty(Integer fileID,String shareType,List<String> oldOpRights,List<String> newOpRights,List<String> oldSharedStaffs,List<String> newSharedStaffs);

    SharedFile selectByPrimaryKey(Integer fileID);

    int updateByPrimaryKey(SharedFile record);

    boolean deleteByPrimaryKey(Integer fileID);

}
