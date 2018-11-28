package com.esc.fms.service.file.impl;

import com.esc.fms.common.constant.Constants;
import com.esc.fms.entity.Dictionary;
import com.esc.fms.entity.FileOperationHistory;
import com.esc.fms.entity.SharedFile;
import com.esc.fms.entity.User;
import com.esc.fms.service.base.DictionaryService;
import com.esc.fms.service.base.FtpService;
import com.esc.fms.service.file.FileManageService;
import com.esc.fms.service.file.FileOperationHistoryService;
import com.esc.fms.service.file.FileService;
import com.esc.fms.service.right.UserService;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by tangjie on 2017/9/7.
 */
@Service("fileManageService")
public class FileManageServiceImpl implements FileManageService{

    private static final Logger logger = LoggerFactory.getLogger(FileManageServiceImpl.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private FtpService ftpService;

    @Autowired
    private UserService userService;

    @Autowired
    private FileOperationHistoryService fileOperationHistoryService;

    public boolean fileReplace(Integer fileID, MultipartFile file, String userName) throws IOException {

        logger.debug("call fileReplace method!");

        SharedFile sharedFile = fileService.selectByPrimaryKey(fileID);

        String fileUrl = sharedFile.getFilePath();
        String removedFileName = sharedFile.getFileName() + "." + sharedFile.getFileExtType();

        FTPClient ftpClient = null;

        try{

            ftpClient = ftpService.getFtpClient();

            logger.debug("file Download ... fileUrl:[{}]",fileUrl);

            String[] paths = fileUrl.split("/");
            for(int i=0;i<paths.length;i++){
                String path = new String(paths[i].getBytes(ftpClient.getControlEncoding()), Constants.SERVER_CHARSET);
                if(!ftpClient.changeWorkingDirectory(path)){
                    throw new IOException("为文件路径[" + fileUrl + "]切换目录[" + path + "]时，失败!");
                }
            }

            if(!ftpClient.deleteFile(removedFileName)){
                throw new IOException("删除路径[" + sharedFile.getFilePath() + "]下的文件[" + removedFileName + "]失败!");
            }

            logger.debug("begin upload file [{}]",file.getOriginalFilename());
            if(!ftpClient.storeFile(file.getOriginalFilename(),file.getInputStream())){
                throw new IOException("文件[" + file.getOriginalFilename() + "]上传失败，没有操作权限!");
            }
            file.getInputStream().close();
            logger.debug("end upload file [{}]",file.getOriginalFilename());

            User user = userService.getUserByUserName(userName);

            String[] arr = file.getOriginalFilename().split("\\.");

            sharedFile.setFileName(arr[0]);
            sharedFile.setFileExtType(arr[1]);
            sharedFile.setMimeType(file.getContentType());
            sharedFile.setFileSize(file.getSize());
            sharedFile.setLastUpdator(user.getUserID());
            sharedFile.setUpdateTime(new Date());

            fileService.updateByPrimaryKey(sharedFile);

            recordFileOpHistory(sharedFile,"0501",user.getUserID());

        }catch (IOException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            try{
                ftpService.closeFtpConnection(ftpClient);
            }catch (IOException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean fileDelete(List<Integer> fileIDs, String userName) throws IOException {
        logger.debug("call fileDelete method!");

        FTPClient ftpClient = null;

        try{
            for(Integer fileID : fileIDs){
                SharedFile sharedFile = fileService.selectByPrimaryKey(fileID);

                String fileUrl = sharedFile.getFilePath();
                String removedFileName = sharedFile.getFileName() + "." + sharedFile.getFileExtType();


                ftpClient = ftpService.getFtpClient();

                logger.debug("file Delete ... fileUrl:[{}]",fileUrl);

                String[] paths = fileUrl.split("/");
                for(int i=0;i<paths.length;i++){
                    String path = new String(paths[i].getBytes(ftpClient.getControlEncoding()), Constants.SERVER_CHARSET);
                    if(!ftpClient.changeWorkingDirectory(path)){
                        throw new IOException("为文件路径[" + fileUrl + "]切换目录[" + path + "]时，失败!");
                    }
                }

                logger.debug("begin delete file [{}]",removedFileName);

                if(!ftpClient.deleteFile(removedFileName)){
                    throw new IOException("删除路径[" + sharedFile.getFilePath() + "]下的文件[" + removedFileName + "]失败!");
                }

                if(!fileService.deleteByPrimaryKey(fileID)){
                    throw new IOException("删除文件[" + sharedFile.getFileName() + "]数据库中的记录失败!");
                }

                User user = userService.getUserByUserName(userName);

                recordFileOpHistory(sharedFile,"0502",user.getUserID());

                logger.debug("end delete file [{}]",removedFileName);
            }


        }catch (IOException e){
            logger.error(e.getMessage());
            throw e;
        }finally {
            try{
                ftpService.closeFtpConnection(ftpClient);
            }catch (IOException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return true;
    }

    private void recordFileOpHistory(SharedFile file,String fileOpType,Integer userID){

        FileOperationHistory foh = new FileOperationHistory();
        foh.setFileID(file.getFileID());
        foh.setFileName(file.getFileName());
        foh.setFileExtType(file.getFileExtType());
        foh.setFilePath(file.getFilePath());
        foh.setFolderName(file.getFolderName());
        foh.setFileOpType(fileOpType);
        foh.setCreator(file.getCreator());
        foh.setCreatTime(file.getCreateTime());
        foh.setUpdateTime(new Date());
        foh.setUpdator(userID);
        fileOperationHistoryService.insert(foh);

    }
}
