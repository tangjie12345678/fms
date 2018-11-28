package com.esc.fms.controller.base;

import com.esc.fms.entity.Department;
import com.esc.fms.entity.HDepartment;
import com.esc.fms.service.base.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/28.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService deptService;

    @RequestMapping("/getAllDepartment.do")
    @ResponseBody
    public List<Department> getAllDepartment()
    {
        return deptService.getAllDepartment();
    }

    @RequestMapping("/view.do")
    public ModelAndView gotoStaffListPage() {
        return new ModelAndView("base/department");
    }

    @RequestMapping("/getHierarchyDepartment.do")
    @ResponseBody
    public List<HDepartment> getHierarchyDepartment(){
        List<HDepartment> hdepts = new ArrayList<HDepartment>();
        hdepts.add(deptService.getHierarchyDepartment());
        return hdepts ;
    }

    @RequestMapping("/addDept.do")
    @ResponseBody
    public ModelMap addDepartment(@RequestParam(value = "ParentID",required = false) Integer parentID,
                                  @RequestParam("DeptName") String deptName,@RequestParam("IsLeaf") Integer isLeaf,
                                  @RequestParam("SortNo") Integer sortNo,@RequestParam("Description") String desc)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "部门信息添加成功：" + deptName;

        Department dept = new Department();

        dept.setParentID(parentID);
        dept.setDeptName(deptName);
        if(isLeaf == 1){
            dept.setIsLeaf(true);
        }else{
            dept.setIsLeaf(false);
        }

        dept.setSortNo(sortNo);
        dept.setDescription(desc);
        dept.setIsEnabled(true);

        int num = deptService.insert(dept);
        if(num == 1){
            result.addAttribute("success", success);
            result.addAttribute("msg",msg);
        }

        return result;
    }

    @RequestMapping("getDeptInfoWithParentDeptName.do")
    @ResponseBody
    public ModelMap getDeptInfoWithParentDeptName(@RequestParam(value = "DeptID") Integer deptID)
    {
        ModelMap result = new ModelMap();

        Department dept = deptService.selectByPrimaryKey(deptID);
        Integer parentID = dept.getParentID();
        Department parent = deptService.selectByPrimaryKey(parentID);
        String parentDeptName = parent.getDeptName();

        result.put("dept",dept);
        result.put("parentDeptName",parentDeptName);

        return result;
    }

    @RequestMapping("editDepartment.do")
    @ResponseBody
    public ModelMap editDepartment(@RequestParam(value = "DeptID") Integer deptID,@RequestParam(value = "ParentID",required = false) Integer parentID,
                                   @RequestParam("DeptName") String deptName,@RequestParam("IsLeaf") Integer isLeaf,
                                   @RequestParam("SortNo") Integer sortNo,@RequestParam("Description") String desc)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "部门信息修改成功：" + deptName;

        Department dept = new Department();

        dept.setDeptID(deptID);
        dept.setParentID(parentID);
        dept.setDeptName(deptName);
        if(isLeaf == 1){
            dept.setIsLeaf(true);
        }else{
            dept.setIsLeaf(false);
        }

        dept.setSortNo(sortNo);
        dept.setDescription(desc);
        dept.setIsEnabled(true);

        int num = deptService.updateByPrimaryKey(dept);
        if(num == 1){
            result.addAttribute("success", success);
            result.addAttribute("msg",msg);
        }

        return result;
    }

    @RequestMapping("delDepartment.do")
    @ResponseBody
    public ModelMap delDepartment(@RequestParam(value = "DeptID") Integer deptID)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "部门信息成功删除：" + deptID;



        int num = deptService.deleteDepartment(deptID);
        if(num == 1){
            result.addAttribute("success", success);
            result.addAttribute("msg",msg);
        }

        return result;
    }

}
