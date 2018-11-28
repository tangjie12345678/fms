package com.esc.fms.controller.base;

import com.esc.fms.common.util.TimeUtil;
import com.esc.fms.entity.Staff;
import com.esc.fms.entity.TreeNode;
import com.esc.fms.service.base.DeptRefStaffService;
import com.esc.fms.service.base.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangjie on 2016/12/7.
 */

@Controller
@RequestMapping("/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @Autowired
    private DeptRefStaffService deptRefStaffService;



    @RequestMapping("/view.do")
    public ModelAndView gotoStaffListPage() {
        return new ModelAndView("base/staff");
    }

    @RequestMapping("/getStaffList.do")
    @ResponseBody
    public ModelMap getStaffList(@RequestParam(value = "staffID",required = false) String staffID,
                                               @RequestParam(value = "staffName",required = false) String staffName,
                                               @RequestParam(value = "sex",required = false) String sex,
                                               @RequestParam(value = "page") int pageIndex,
                                               @RequestParam(value = "rows") int pageSize)
    {
        logger.debug("参数 ：staffID=" + staffID + ",staffName=" + staffName + ",sex=" +sex + ",page=" +pageIndex  + ",rows=" +pageSize );
        int offset = (pageIndex-1)*pageSize;
        //PageModel pageModel = new PageModel();
        ModelMap result = new ModelMap();
        result.put("total",staffService.getCountByConditions(staffID, staffName, sex));
        result.put("rows",staffService.selectStaffByConditions(staffID, staffName, sex,offset,pageSize));
        return result;
    }

    @RequestMapping("/getAllStaffForDropdownList.do")
    @ResponseBody
    public List<Staff> getAllStaffForDropdownList(@RequestParam(value = "staffName",required = false) String staffName)
    {
        if (null != staffName)
        {
            staffName = staffName.trim();
        }
        return staffService.selectForDropdownList(staffName);
    }

    @RequestMapping("/getStaffByPrimaryKey.do")
    @ResponseBody
    public ModelMap getStaffByPrimaryKey(@RequestParam(value = "StaffRecordID") Integer staffRecordID){

        ModelMap result = new ModelMap();

        Staff staff = staffService.selectByPrimaryKey(staffRecordID);
        List<Integer> depts = deptRefStaffService.getDeptsByStaffRecordID(staffRecordID);
        result.put("staff",staff);
        result.put("dept",depts);

        return result;
    }

    @RequestMapping("/getAddStaffPage.do")
    public String getAddStaffPage()
    {
        return "base/StaffAdd";
    }

    @ResponseBody
    @RequestMapping("/addStaff.do")
    public ModelMap StaffInfoAdd(@RequestParam("StaffID") String staffID, @RequestParam("StaffName") String staffName,
                                 @RequestParam("Birthday") String birthday, @RequestParam("Sex") String sex,
                                 @RequestParam("Telephone") String telephone, @RequestParam("Mobilephone") String mobilephone,
                                 @RequestParam("Address") String address, @RequestParam("NativePlace") String nativePlace,
                                 @RequestParam("Degree") String degree, @RequestParam("College") String college,
                                 @RequestParam("Department") List<Integer> depts, @RequestParam("Description") String description)
    {

        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "员工信息添加成功：" + staffName;

        Staff staff = new Staff();
        staff.setStaffID(staffID);
        staff.setStaffName(staffName);
        if(StringUtils.isEmpty(birthday)){
            staff.setBirthday(null);
        }
        else{
            staff.setBirthday(TimeUtil.timeStrToSimpleDate(birthday));
        }
        staff.setSex(sex);
        staff.setTelephone(telephone);
        staff.setMobilephone(mobilephone);
        staff.setAddress(address);
        staff.setNativePlace(nativePlace);
        staff.setDegree(degree);
        staff.setCollege(college);
        staff.setDescription(description);
        staff.setAvail(true);

        staffService.addStaff(staff,depts);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @ResponseBody
    @RequestMapping("/delStaff.do")
    public ModelMap StaffInfoDel( @RequestParam("StaffRecordIDs") List<Integer> staffRecordIDs)
    {
        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "员工信息删除成功";

        staffService.delMultiStaff(staffRecordIDs);

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }

    @ResponseBody
    @RequestMapping("/editStaff.do")
    public ModelMap StaffInfoEdit(@RequestParam("StaffRecordID") Integer staffRecordID,@RequestParam("StaffID") String staffID, @RequestParam("StaffName") String staffName,
                                 @RequestParam("Birthday") String birthday, @RequestParam("Sex") String sex,
                                 @RequestParam("Telephone") String telephone, @RequestParam("Mobilephone") String mobilephone,
                                 @RequestParam("Address") String address, @RequestParam("NativePlace") String nativePlace,
                                 @RequestParam("Degree") String degree, @RequestParam("College") String college,
                                 @RequestParam("Department") List<Integer> newdepts, @RequestParam("OldDepartment") List<Integer> olddepts,  @RequestParam("Description") String description)
    {

        ModelMap result = new ModelMap();

        boolean success = true;
        String msg = "员工信息修改成功：" + staffName;

        Staff staff = new Staff();
        staff.setStaffRecordID(staffRecordID);
        staff.setStaffID(staffID);
        staff.setStaffName(staffName);
        if(StringUtils.isEmpty(birthday)){
            staff.setBirthday(null);
        }
        else{
            staff.setBirthday(TimeUtil.timeStrToSimpleDate(birthday));
        }
        staff.setSex(sex);
        staff.setTelephone(telephone);
        staff.setMobilephone(mobilephone);
        staff.setAddress(address);
        staff.setNativePlace(nativePlace);
        staff.setDegree(degree);
        staff.setCollege(college);
        staff.setDescription(description);
        staff.setAvail(true);

//        for (Integer deptID : olddepts){
//            logger.debug("olddepts : " + deptID +" ");
//        }
//
//        for (Integer deptID : newdepts){
//            logger.debug("newdepts : " + deptID +" ");
//        }


        if(newdepts.equals(olddepts)){
            logger.debug(" depts has no change.");
            staffService.updateByPrimaryKey(staff);
        }
        else{
            logger.debug(" depts has change.");
            List<Integer> intersection = new ArrayList<Integer>();
            for(Integer deptID : olddepts){
                if(newdepts.contains(deptID)){
                    intersection.add(deptID);
                }
            }

            newdepts.removeAll(intersection);
            olddepts.removeAll(intersection);

            staffService.editStaff(staff,newdepts,olddepts);
        }

        result.addAttribute("success", success);
        result.addAttribute("msg",msg);
        return result;
    }


    @RequestMapping("/getDeptStaff.do")
    @ResponseBody
    public List<TreeNode> getDeptStaff()
    {
        List<TreeNode> nodes = new ArrayList<TreeNode>();
        nodes.add(staffService.getDeptStaff());
        return nodes;
    }

}
