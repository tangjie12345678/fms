package com.esc.fms.service.file.impl;

import com.esc.fms.common.constant.Constants;
import com.esc.fms.dao.FileTypeMapper;
import com.esc.fms.entity.*;
import com.esc.fms.service.base.FileTypeService;
import com.esc.fms.service.base.FtpConnectionInfoService;
import com.esc.fms.service.base.FtpService;
import com.esc.fms.service.file.FileOperateRightService;
import com.esc.fms.service.file.FileRefStaffService;
import com.esc.fms.service.file.FileService;
import com.esc.fms.service.file.FileUploadService;
import com.esc.fms.service.right.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by tangjie on 2017/4/6.
 */
@Service("fileUploadService")
public class FileUploadServiceImpl implements FileUploadService {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadServiceImpl.class);


    @Autowired
    private FileTypeService fileTypeService;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRefStaffService fileRefStaffService;

    @Autowired
    private FileOperateRightService fileOperateRightService;

    @Autowired
    private UserService userService;

    @Autowired
    private FtpService ftpService;


    public boolean fileUpload(List<String> sharedStaffs, String shareType, List<String> opTypes, Integer fileType, String folderName, MultipartFile[] sourceFiles, String userName) throws IOException{

        boolean result = false;

        StringBuilder filePath = new StringBuilder("");

        filePath.append(fileTypeService.getFileTypeByID(fileType).getFileTypeName());

        if(null != folderName && folderName.length()>0)
        {
            filePath.append("/").append(folderName);
        }

        logger.debug("filePath: {}" ,filePath.toString());

        result = ftpUploadFile(filePath.toString(),sourceFiles);

        if(result == true){

            User user = userService.getUserByUserName(userName);

            logger.debug("完成文件上传！");

            for(MultipartFile file : sourceFiles){
                if(!file.isEmpty()){
                    logger.debug("filename: {}",file.getOriginalFilename());
                    String[] arr = file.getOriginalFilename().split("\\.");
                    logger.debug("length of arr: {}",arr.length);
                    SharedFile sf = new SharedFile();

                    logger.debug("MimeType : " + file.getContentType());

                    sf.setFileName(arr[0]);
                    sf.setFileExtType(arr[1]);
                    sf.setMimeType(file.getContentType());
                    sf.setFileSize(file.getSize());
                    sf.setFileTypeID(fileType);
                    sf.setFolderName(folderName);
                    sf.setFilePath(filePath.toString());
                    sf.setShareType(shareType);
                    sf.setCreator(user.getUserID());
                    sf.setCreateTime(new Date());

                    fileService.insertFile(sf);

                    Integer fileID = sf.getFileID();

                    if(shareType != null && shareType.equals("0303"))
                    {
                        if(sharedStaffs != null && !sharedStaffs.isEmpty()){
                            for(String staffInfo : sharedStaffs)
                            {
                                FileRefStaff frs = new FileRefStaff();
                                frs.setFileID(fileID);

                                String[] tempArr = staffInfo.split("_");
                                frs.setDeptID(Integer.valueOf(tempArr[0]));
                                frs.setStaffID(Integer.valueOf(tempArr[1]));
                                fileRefStaffService.insertFileRefStaff(frs);
                            }
                        }
                    }

                    if(opTypes != null && !opTypes.isEmpty()){
                        for(String opType : opTypes){
                            FileOperateRight fopr = new FileOperateRight();
                            fopr.setFileID(fileID);
                            fopr.setOpRight(opType);

                            fileOperateRightService.insertRecord(fopr);
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean ftpUploadFile(String filePath, MultipartFile[] sourceFiles) throws IOException{
        boolean success = false;
        FTPClient ftpClient = null;
        try{
            ftpClient = ftpService.getFtpClient();

            logger.info("filePath : [{}]",filePath);
            String[] paths = filePath.split("/");
            for(String path : paths){
                path = new String(path.getBytes(ftpClient.getControlEncoding()), Constants.SERVER_CHARSET);
                if(!ftpClient.changeWorkingDirectory(path)){
                    if(!ftpClient.makeDirectory(path)){
                        logger.error("创建目录[{}]失败，没有操作权限!",path);
                        throw new IOException("创建目录失败，没有操作权限!");
                    }else{
                        ftpClient.changeWorkingDirectory(path);
                    }
                }
            }

            for(MultipartFile file : sourceFiles){
                if(!file.isEmpty()){
                    logger.debug("begin upload file [{}]",file.getOriginalFilename());
                    if(!ftpClient.storeFile(file.getOriginalFilename(),file.getInputStream())){
                        logger.error("文件[{}]上传失败，没有操作权限!",file.getOriginalFilename());
                        throw new IOException("文件上传失败，没有操作权限!");
                    }
                    file.getInputStream().close();
                    logger.debug("end upload file [{}]",file.getOriginalFilename());
                }
            }
            success = true;
        }catch (IOException e){
            throw e;
        }finally{
            ftpService.closeFtpConnection(ftpClient);
        }
        return success;
    }

}
