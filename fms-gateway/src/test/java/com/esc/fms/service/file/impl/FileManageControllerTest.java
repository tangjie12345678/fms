package com.esc.fms.service.file.impl;

import com.esc.fms.controller.file.FileManageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by tangjie on 2017/8/13.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class FileManageControllerTest {

    @Autowired
    private FileManageController fileManageController;

    @Test
    public void fileOpenTest(){

        HttpServletResponse response = new MockHttpServletResponse();

        fileManageController.fileOpen("文件类型3/IMG_20170504_072820.jpg",response);
    }

}
