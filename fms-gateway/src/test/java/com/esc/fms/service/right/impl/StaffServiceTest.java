package com.esc.fms.service.right.impl;

import com.esc.fms.entity.StaffListElement;
import com.esc.fms.service.base.StaffService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/8.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    @Test
    public void selectStaffByConditions(){
//        List<StaffListElement> stafflist = new ArrayList<StaffListElement>();
//        stafflist = staffService.selectStaffByConditions();
//        for( StaffListElement e : stafflist){
//            System.out.println(e);
//        }
    }

}
