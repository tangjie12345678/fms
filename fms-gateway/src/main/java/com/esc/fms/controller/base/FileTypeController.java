package com.esc.fms.controller.base;

import com.esc.fms.entity.FileType;
import com.esc.fms.service.base.FileTypeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tangjie on 2017/4/8.
 */
@Controller
@RequestMapping("/fileType")
public class FileTypeController {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileTypeController.class) ;

    @Autowired
    private FileTypeService fileTypeService;

    @RequestMapping("/getFileTypeList.do")
    @ResponseBody
    public List<FileType> getFileTypeList(){
        return fileTypeService.getAllFileType();
    }

    @RequestMapping("/getFileTypePageList.do")
    @ResponseBody
    public ModelMap getFileTypePageList(@RequestParam(value = "page") int pageIndex,
                                    @RequestParam(value = "rows") int pageSize){
        logger.debug("get filetype page list with page : {},rows : {}",pageIndex,pageSize);
        int offset = (pageIndex-1)*pageSize;
        ModelMap result = new ModelMap();
        result.put("total",fileTypeService.getRecordCount());
        result.put("rows",fileTypeService.getFileTypePageList(offset,pageSize));
        return result;
    }

    @RequestMapping("/view.do")
    public ModelAndView gotoFileTypeListPage(){
        return new ModelAndView("base/fileType");
    }

    @RequestMapping("/addFileType.do")
    @ResponseBody
    public ModelMap addFileType(@RequestParam("FileTypeName") String fileTypeName,@RequestParam("FileTypeDesc") String fileTypeDesc){
        ModelMap result = new ModelMap();

        boolean success = false;
        String msg = "";

        FileType fileType = new FileType();
        fileType.setFileTypeName(fileTypeName);
        fileType.setFileTypeDesc(fileTypeDesc);
        fileType.setEnabled(true);

        int count = fileTypeService.addFileType(fileType);
        logger.debug("Add File Type count :{} ",count);
        if(1 == count){
            msg = "增加文件类型成功！";
            success = true;
        }else{
            msg = "增加文件类型失败！";
            success = false;
        }

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @RequestMapping("/editFileType.do")
    @ResponseBody
    public ModelMap editFileType(@RequestParam("FileTypeID") int fileTypeID,
                                 @RequestParam("FileTypeName") String fileTypeName,
                                 @RequestParam("FileTypeDesc") String fileTypeDesc){
        ModelMap result = new ModelMap();
        boolean success = false;
        String msg = "";

        FileType fileType = new FileType();
        fileType.setFileTypeID(fileTypeID);
        fileType.setFileTypeName(fileTypeName);
        fileType.setFileTypeDesc(fileTypeDesc);
        fileType.setEnabled(true);

        int count = fileTypeService.updateByFileTypeID(fileType);
        logger.debug("Edited File Type count :{} ",count);
        if(1 == count){
            msg = "修改文件类型成功！";
            success = true;
        }else{
            msg = "修改文件类型失败！";
            success = false;
        }

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @RequestMapping("/delFileType.do")
    @ResponseBody
    public ModelMap delFileType(@RequestParam("FileTypeIDs") List<Integer>  fileTypeIDs){

        ModelMap result = new ModelMap();
        boolean success = false;
        String msg = "";

        if(fileTypeService.delFileTypes(fileTypeIDs)){
            msg = "删除文件类型成功！";
            success = true;
        }else{
            msg = "删除文件类型失败！";
            success = false;
        }

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }


}
