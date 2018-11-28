package com.esc.fms.service.file.impl;

import com.esc.fms.service.file.FileUploadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2017/4/16.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FileUploadServiceTest {

    @Autowired
    private FileUploadService fileUploadService;

    @Test
    public void fileUploadTest(){
        List<String> sharedStaffs =null;
        String shareType = "0301";
        List<String> opTypes = new ArrayList<String>();
        opTypes.add("0401");
        opTypes.add("0403");
        Integer fileType = 1;
        String folderName = "特殊文件";
        MultipartFile[] sourceFiles = null;
//        fileUploadService.fileUpload(sharedStaffs,shareType,opTypes,fileType,folderName,sourceFiles,null);
    }



}
