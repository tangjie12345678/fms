package com.esc.fms.service.base.impl;

import com.esc.fms.entity.Department;
import com.esc.fms.entity.HDepartment;
import com.esc.fms.service.base.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tangjie on 2016/12/27.
 */
@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService deptService;

//    @Test
//    public void testGetTopDeptInfo()
//    {
//        Department dept = deptService.getTopDeptInfo();
//        System.out.println(dept);
//    }
//
//    @Test
//    public void testSelectByParentID(){
//        int parentID = 3;
//
//        List<Department> deptList = deptService.selectByParentID(parentID);
//
//        System.out.println(deptList.size());
//
//    }

    @Test
    public void testGetAllDepartment(){
        List<Department> depts = deptService.getAllDepartment();

        System.out.println(depts);
    }

    @Test
    public void testGetHierarchyDepartment(){
        HDepartment hdept = deptService.getHierarchyDepartment();
        System.out.println("test");
    }
}
