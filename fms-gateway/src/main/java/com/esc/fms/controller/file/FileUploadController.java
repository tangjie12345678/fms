package com.esc.fms.controller.file;

import com.esc.fms.service.file.FileUploadService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by tangjie on 2017/3/12.
 */
@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping("/view.do")
    public ModelAndView goToFileUploadPage()
    {
        return new ModelAndView("file/fileUpload");
    }

    @RequestMapping(value="/upload.do",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap filesUpload(@RequestParam(value = "sharedStaffs",required = false) List<String> sharedStaffs,
                                @RequestParam("shareType") String shareType,
                                @RequestParam("opType") List<String> opTypes,
                                @RequestParam("fileType") Integer fileType,
                                @RequestParam(value = "folderName",required = false) String folderName,
                                @RequestParam MultipartFile[] sourceFiles, HttpServletRequest request){

        ModelMap result = new ModelMap();

        String userName = request.getSession().getAttribute("userName").toString();

        Boolean executeResult = false;
        try{
            executeResult =  fileUploadService.fileUpload(sharedStaffs,shareType,opTypes,fileType,folderName,sourceFiles,userName);
        }catch (IOException e){
            e.printStackTrace();
        }

        result.put("success",executeResult);

        if(executeResult == true){
            result.put("msg","文件上传成功！");
        }else{
            result.put("msg","文件上传失败！");
        }

        return  result;
    }

}

