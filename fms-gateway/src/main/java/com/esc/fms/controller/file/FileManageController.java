package com.esc.fms.controller.file;

import com.esc.fms.common.constant.Constants;
import com.esc.fms.entity.FileListElement;
import com.esc.fms.service.base.FtpService;
import com.esc.fms.service.file.FileManageService;
import com.esc.fms.service.file.FileService;
import org.apache.commons.io.IOExceptionWithCause;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by tangjie on 2017/4/23.
 */
@Controller
@RequestMapping("/filemanage")
public class FileManageController {

    private static final Logger logger = LoggerFactory.getLogger(FileManageController.class);

    @Autowired
    private FileService fileService;

    @Autowired
    private FtpService ftpService;

    @Autowired
    private FileManageService fileManageService;

    @RequestMapping("/view.do")
    public ModelAndView goToFileUploadPage() {
        return new ModelAndView("file/fileManage");
    }

    @RequestMapping("/getFileList.do")
    @ResponseBody
    public ModelMap getFileList(@RequestParam(value = "fileName", required = false) String fileName,
                                @RequestParam(value = "fileType", required = false) Integer fileType,
                                @RequestParam(value = "folderName", required = false) String folderName,
                                @RequestParam(value = "creator", required = false) String creator,
                                @RequestParam(value = "updator", required = false) String updator,
                                @RequestParam(value = "page") int pageIndex,
                                @RequestParam(value = "rows") int pageSize) {
        int offset = (pageIndex - 1) * pageSize;
        ModelMap result = new ModelMap();
        result.put("total", fileService.getCountByConditions(fileName, fileType, folderName, creator, updator));
        result.put("rows", fileService.getFileListByConditions(fileName, fileType, folderName, creator, updator, offset, pageSize));
        return result;
    }

    @RequestMapping("/getFileProperties")
    @ResponseBody
    public ModelMap getFileProperties(@RequestParam("FileID") Integer fileID) {
        ModelMap result = fileService.getFileProperties(fileID);
        return result;
    }

    @RequestMapping("/resetProperty")
    @ResponseBody
    public ModelMap resetProperty(@RequestParam("fileID") Integer fileID,
                                  @RequestParam("shareType") String shareType,
                                  @RequestParam("oldOpRights") List<String> oldOpRights,
                                  @RequestParam("newOpRights") List<String> newOpRights,
                                  @RequestParam(value = "oldSharedStaffs", required = false) List<String> oldSharedStaffs,
                                  @RequestParam(value = "newSharedStaffs", required = false) List<String> newSharedStaffs) {
        ModelMap result = new ModelMap();
        String success = "fail";
        String msg = "";
        try {
            fileService.resetProperty(fileID, shareType, oldOpRights, newOpRights, oldSharedStaffs, newSharedStaffs);
            success = "success";
            msg = "重设文件属性成功！";
        } catch (Exception e) {
            logger.warn("重设文件属性失败, 文件编号:[{}]", fileID);
            msg = "重设文件属性失败!";
        }

        result.addAttribute("success", success);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/fileDelete.do")
    @ResponseBody
    public ModelMap fileReplace(@RequestParam("FileIDs") List<Integer> fileIDs,HttpServletRequest request){
        ModelMap result = new ModelMap();
        String msg = "";

        String userName = request.getSession().getAttribute("userName").toString();

        Boolean executeResult = false;
        try{
            executeResult = fileManageService.fileDelete(fileIDs,userName);
        }catch (IOException e){
            executeResult = false;
            e.printStackTrace();
        }

        result.put("success",executeResult);

        if(executeResult == true){
            result.put("msg","文件删除成功！");
        }else{
            result.put("msg","文件删除失败！");
        }
        return  result;
    }

    @RequestMapping(value = "/fileReplace.do",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap fileReplace(@RequestParam("replaceFileID") Integer fileID,@RequestParam MultipartFile sourceFiles, HttpServletRequest request){
        ModelMap result = new ModelMap();

        logger.debug("fileId:[{}],sourceFilesName:[{}]",fileID,sourceFiles.getOriginalFilename());

        if(null != sourceFiles){
            String userName = request.getSession().getAttribute("userName").toString();

            Boolean executeResult = false;
            try{
                executeResult =  fileManageService.fileReplace(fileID,sourceFiles,userName);
            }catch (IOException e){
                executeResult = false;
                e.printStackTrace();
            }

            result.put("success",executeResult);

            if(executeResult == true){
                result.put("msg","文件替换成功！");
            }else{
                result.put("msg","文件替换失败！");
            }
        }else{
            result.put("success",false);
            result.put("msg","上传文件为空！");
        }

        return  result;
    }

    @RequestMapping("/fileDownload")
    public void fileDownload(@RequestParam("fileUrls") List<String> fileUrls, HttpServletResponse response){

        FTPClient ftpClient = null;
        ZipOutputStream zipOut = null;
        byte[] buf = new byte[4096];

        try{
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/x-zip-compressed");
            response.setHeader("Content-Disposition", "inline;fileName=" + new String("下载文件".getBytes("gb2312"),"ISO8859-1") + ".zip");

            ftpClient = ftpService.getFtpClient();

            zipOut = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream(),4096));

            for(String fileUrl : fileUrls){
                logger.debug("file Download ... fileUrl:[{}]",fileUrl);
                String[] paths = fileUrl.split("/");
                for(int i=0;i<paths.length-1;i++){
                    String path = new String(paths[i].getBytes(ftpClient.getControlEncoding()), Constants.SERVER_CHARSET);
                    if(!ftpClient.changeWorkingDirectory(path)){
                        throw new IOException("为文件路径[" + fileUrl + "]切换目录[" + path + "]时，失败!");
                    }
                }

                String fileName = paths[paths.length-1];
                logger.debug("文件名：[{}]",fileName);

                ZipEntry entry = new ZipEntry(fileName);
                zipOut.putNextEntry(entry);

                logger.debug("Current working directory : [{}]",ftpClient.printWorkingDirectory() );
                logger.debug("ftp is connected :[{}]",ftpClient.isConnected());
                InputStream bis = ftpClient.retrieveFileStream(fileName);
                if(null != bis){
                    int readLen = -1;
                    while((readLen = bis.read(buf,0,4096)) != -1){
                        zipOut.write(buf,0,readLen);
                    }
                    bis.close();
                    if(!ftpClient.completePendingCommand()){
                        throw new IOException("执行completePendingCommand命令失败！");
                    }
                }else{
                    throw new IOException("获取文件" + fileName + "的输入流失败,replyCode :[" + ftpClient.getReplyCode() + "]");
                }

                for(int i =0; i < paths.length -1;i++){
                    if(!ftpClient.changeToParentDirectory()){
                        throw new IOException("切换到父目录失败！");
                    }
                }
            }
        }catch (IOException e){
            logger.error(e.getMessage());

            e.printStackTrace();
        }finally {
            if(null != zipOut){
                try{
                    zipOut.close();
                }catch (IOException e){
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }

            try{
                ftpService.closeFtpConnection(ftpClient);
            }catch (IOException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @RequestMapping("/fileOpen")
    public void fileOpen(@RequestParam("fileUrl") String fileUrl, HttpServletResponse response){

        FTPClient ftpClient = null;
        BufferedOutputStream out = null;
        try{
            ftpClient = ftpService.getFtpClient();

            logger.debug("fileUrl : [{}]",fileUrl);

            String[] paths = fileUrl.split("/");
            for(int i=0;i<paths.length-1;i++){
                String path = new String(paths[i].getBytes(ftpClient.getControlEncoding()), Constants.SERVER_CHARSET);
                if(!ftpClient.changeWorkingDirectory(path)){
                    StringBuilder msg = new StringBuilder();
                    msg.append("切换目录[").append(path).append("]失败!");
                    throw new IOException(msg.toString());
                }
            }

            String fileName = paths[paths.length-1];

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "inline;fileName=" + fileName);

            out = new BufferedOutputStream(response.getOutputStream(),4096);

            if(!ftpClient.retrieveFile(fileName,out))
            {
                throw new IOException("下载文件" + fileName + "失败！");
            }
        }catch (IOException e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }finally {

            try{
                ftpService.closeFtpConnection(ftpClient);
            }catch (IOException e){
                logger.error(e.getMessage());
                e.printStackTrace();
            }

            if(null != out){
                try{
                    out.close();
                }catch (IOException e){
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }

            }
        }
    }
}
