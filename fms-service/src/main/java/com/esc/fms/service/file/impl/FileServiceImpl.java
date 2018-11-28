package com.esc.fms.service.file.impl;

import com.esc.fms.dao.FileListElementMapper;
import com.esc.fms.dao.FileRefStaffMapper;
import com.esc.fms.dao.SharedFileMapper;
import com.esc.fms.entity.FileListElement;
import com.esc.fms.entity.FileOperateRight;
import com.esc.fms.entity.FileRefStaff;
import com.esc.fms.entity.SharedFile;
import com.esc.fms.service.file.FileOperateRightService;
import com.esc.fms.service.file.FileRefStaffService;
import com.esc.fms.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Autowired
    private SharedFileMapper sharedFileMapper;

    @Autowired
    private FileListElementMapper fileListElementMapper;

    @Autowired
    private FileRefStaffService fileRefStaffService;

    @Autowired
    private FileOperateRightService fileOperateRightService;

    public int insertFile(SharedFile sharedFile) {
        return sharedFileMapper.insert(sharedFile);
    }

    public List<FileListElement> getFileListByConditions(String fileName, Integer fileType, String folderName, String creator, String updator, Integer offset, Integer pageSize) {
        return fileListElementMapper.getFileListByConditions(fileName,fileType,folderName,creator,updator,offset,pageSize);
    }

    public Integer getCountByConditions(String fileName, Integer fileType, String folderName,String creator,String updator) {
        return fileListElementMapper.getCountByConditions(fileName,fileType,folderName,creator,updator);
    }

    public ModelMap getFileProperties(Integer fileID) {

        ModelMap result = new ModelMap();

        SharedFile sf = sharedFileMapper.selectByPrimaryKey(fileID);

        result.put("file",sf);
        result.put("opRights",fileOperateRightService.selectByFileID(fileID));
        if(sf.getShareType().equals("0303")){
            result.put("staffs",fileRefStaffService.selectByFileID(fileID));
        }
        else{
            result.put("staffs",null);
        }
        return result;
    }

    public void resetProperty(Integer fileID,String shareType,List<String> oldOpRights, List<String> newOpRights, List<String> oldSharedStaffs, List<String> newSharedStaffs) {

        List<String> interOpRights = new ArrayList<String>();

        for(String opRight:oldOpRights){
            if(newOpRights.contains(opRight)){
                interOpRights.add(opRight);
            }
        }

        oldOpRights.removeAll(interOpRights);
        newOpRights.removeAll(interOpRights);

        for(String opRight:oldOpRights){
            fileOperateRightService.deleteByFileIDandOpRight(fileID,opRight);
        }

        for(String opRight:newOpRights){
            FileOperateRight obj = new FileOperateRight();
            obj.setFileID(fileID);
            obj.setOpRight(opRight);

            fileOperateRightService.insertRecord(obj);
        }

        if(null == oldSharedStaffs || oldSharedStaffs.isEmpty()){
            if(null != newSharedStaffs && !newSharedStaffs.isEmpty()){
                for(String staffInfo : newSharedStaffs){
                    FileRefStaff obj = new FileRefStaff();
                    obj.setFileID(fileID);
                    String[] tempArr = staffInfo.split("_");
                    obj.setDeptID(Integer.valueOf(tempArr[0]));
                    obj.setStaffID(Integer.valueOf(tempArr[1]));

                    fileRefStaffService.insertFileRefStaff(obj);
                }
            }
        }else{
            if(null == newSharedStaffs || newSharedStaffs.isEmpty()){
                fileRefStaffService.deleteByFileID(fileID);
            }else{
                List<String> intersection = new ArrayList<String>();
                for(String staffInfo :oldSharedStaffs){
                    if(newSharedStaffs.contains(staffInfo)){
                        intersection.add(staffInfo);
                    }
                }

                if(!intersection.isEmpty()){
                    oldSharedStaffs.removeAll(intersection);
                    newSharedStaffs.removeAll(intersection);
                }

                if(!oldSharedStaffs.isEmpty()){
                    for(String staffInfo:oldSharedStaffs){
                        String[] tempArr = staffInfo.split("_");
                        Integer staffID =Integer.valueOf(tempArr[1]);
                        fileRefStaffService.deleteByFileIDandStaffID(fileID,staffID);
                    }
                }

                if(!newSharedStaffs.isEmpty()){
                    for(String staffInfo:newSharedStaffs){
                        FileRefStaff obj = new FileRefStaff();
                        obj.setFileID(fileID);
                        String[] tempArr = staffInfo.split("_");
                        obj.setDeptID(Integer.valueOf(tempArr[0]));
                        obj.setStaffID(Integer.valueOf(tempArr[1]));
                        fileRefStaffService.insertFileRefStaff(obj);
                    }
                }

            }
        }

        SharedFile sf = new SharedFile();
        sf.setFileID(fileID);
        sf.setShareType(shareType);

        sharedFileMapper.updateByPrimaryKeySelective(sf);
    }

    public SharedFile selectByPrimaryKey(Integer fileID) {
        return sharedFileMapper.selectByPrimaryKey(fileID);
    }

    public int updateByPrimaryKey(SharedFile sharedFile) {
        return sharedFileMapper.updateByPrimaryKey(sharedFile);
    }

    public boolean deleteByPrimaryKey(Integer fileID) {
        fileRefStaffService.deleteByFileID(fileID);
        fileOperateRightService.deleteByFileID(fileID);
        sharedFileMapper.deleteByPrimaryKey(fileID);
        return true;
    }


}
