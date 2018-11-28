<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Map<String,List<String>> permissionMaps = (Map<String,List<String>>)session.getAttribute("permissionMaps");
    List<String> rightList = permissionMaps.get("staff");
    StringBuilder rightsBuilder = new StringBuilder();
    for(String right : rightList){
        rightsBuilder.append(right).append(",");
    }
    String rights = rightsBuilder.toString().substring(0,rightsBuilder.length()-1);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,no-store, must-revalidate">
    <META HTTP-EQUIV="pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="expires" CONTENT="-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">


    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/beta/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/resources/easyui/themes/default/easyui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/resources/easyui/themes/icon.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/beta/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/base/staff.js"></script>
</head>

<body style="background:#FFF">


<div id="location" style="font-size:12px">当前位置 : 基础信息管理 &gt; 员工信息管理</div>
<hr>

<div id="searchBar" style="padding:2px;font-size:12px">
    <span>编号:</span>
    <input id="staffID" class="easyui-textbox" style="line-height:20px;width:50px;border:1px solid #ccc">
    &nbsp;&nbsp;<span>姓名:</span>
    <input id="staffName" class="easyui-textbox" style="line-height:20px;width:60px;border:1px solid #ccc">
    &nbsp;&nbsp;<span>性别:</span>
    <input id="sex" class="easyui-combobox" name="sex"
           style="line-height:20px;width:45px;border:1px solid #ccc"
           data-options="valueField:'dictCode',textField:'description',editable:false,panelHeight:'auto',
    		url:'${pageContext.request.contextPath}/dictionary/selectByDictType.do?dictType=性别'" />
    &nbsp;&nbsp;<a id="searchBtn" class="easyui-linkbutton">查询</a>
</div>

<div id="toolBar" style="padding:1px;height:auto">
    <div>
        &nbsp;&nbsp;
        <a href="#" id="addBtn" class="easyui-linkbutton" iconCls="icon-add" text="新增" plain="true"></a>
        &nbsp;&nbsp;
        <a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" text="修改" plain="true"></a>
        &nbsp;&nbsp;
        <a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" text="删除" plain="true"></a>
    </div>
</div>

<table id="staffList" class="easyui-datagrid" style="height:365px"
       url="${pageContext.request.contextPath}/staff/getStaffList.do"
       title="员工信息列表" toolbar="#toolBar" idField="staffRecordID"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th field="staffRecordID" checkbox="true"></th>
        <th field="staffID" width="80">编号</th>
        <th field="staffName" width="80">姓名</th>
        <th field="sex" width="60" >性别</th>
        <th field="age" width="60" >年龄</th>
        <th field="deptName" width="180" >部门</th>
        <th field="telephone" width="100">固定电话</th>
        <th field="mobilephone" width="100">移动电话</th>
        <th field="address" width="250">联系地址</th>
    </tr>
    </thead>
</table>

<div class="easyui-window" id="staffAdd" style="width:550px;height:480px" title="添加员工信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <!--<form id="StaffInfo" method="post">-->
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr >
            <td>&nbsp;&nbsp;编号：</td>
            <td><input class="easyui-textbox" type="text" name="eStaffID" id="eStaffID" required="true" /></td>
            <td>姓名：</td>
            <td><input class="easyui-textbox" type="text" name="eStaffName" id="eStaffName" required="true" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;出生日期：</td>
            <td><input id="eBirthday" type="text" class="easyui-datebox"/></td>
            <td>性别：</td>
            <td><input id="eSex" required="true" class="easyui-combobox" name="eSex"
                       style="line-height:20px;width:50px;border:1px solid #ccc"
                       data-options="valueField:'dictCode',textField:'description',editable:false,panelHeight:'auto',
                                             url:'${pageContext.request.contextPath}/dictionary/selectByDictType.do?dictType=性别'" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;固定电话：</td>
            <td><input class="easyui-textbox" type="text" name="eTelephone" id="eTelephone" /></td>
            <td>手机：</td>
            <td><input class="easyui-textbox" type="text" name="eMobilephone" id="eMobilephone" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;居住地址：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="eAddress" id="eAddress" style="width:293px" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;籍贯：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="eNativePlace" id="eNativePlace" style="width:293px" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;学历：</td>
            <td><input id="eDegree" class="easyui-combobox" name="eDegree"
                       style="line-height:20px;width:100px;border:1px solid #ccc"
                       data-options="valueField:'dictCode',textField:'description',editable:false,panelHeight:'auto',
                                             url:'${pageContext.request.contextPath}/dictionary/selectByDictType.do?dictType=学历'" /></td>
            <td>毕业院校：</td>
            <td><input class="easyui-textbox" type="text" name="eCollege" id="eCollege" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;所属部门：</td>
            <td colspan="3">
                <div id="eDepartment" style="width:220px;height:100px"></div>
            </td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="eDescription" rows=4 name="eDescription" style="width:293px;height:80px;font-size:12px"></textarea>
            </td>
        </tr>
        <tr height="45px">
            <td colspan="2" align="right"><a id="addSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
 </div>

    <div class="easyui-window" id="staffEdit" style="width:550px;height:480px" title="修改员工信息"
         data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
        <!--<form id="StaffInfo" method="post">-->
        <input id="staffRecordID" name="staffRecordID" type="hidden" />
        <input id="oldDepartment" name="oldDepartment" type="hidden" />
        <table width="100%" border="0" cellpadding="2" cellspacing="2">
            <tr >
                <td>&nbsp;&nbsp;编号：</td>
                <td><input class="easyui-textbox" type="text" name="editStaffID" id="editStaffID" required="true" /></td>
                <td>姓名：</td>
                <td><input class="easyui-textbox" type="text" name="editStaffName" id="editStaffName" required="true" /></td>
            </tr>
            <tr >
                <td>&nbsp;&nbsp;出生日期：</td>
                <td><input id="editBirthday" type="text" class="easyui-datebox"/></td>
                <td>性别：</td>
                <td><input id="editSex" required="true" class="easyui-combobox" name="editSex"
                           style="line-height:20px;width:50px;border:1px solid #ccc"
                           data-options="valueField:'dictCode',textField:'description',editable:false,panelHeight:'auto',
                                             url:'${pageContext.request.contextPath}/dictionary/selectByDictType.do?dictType=性别'" /></td>
            </tr>
            <tr >
                <td>&nbsp;&nbsp;固定电话：</td>
                <td><input class="easyui-textbox" type="text" name="editTelephone" id="editTelephone" /></td>
                <td>手机：</td>
                <td><input class="easyui-textbox" type="text" name="editMobilephone" id="editMobilephone" /></td>
            </tr>
            <tr >
                <td>&nbsp;&nbsp;居住地址：</td>
                <td colspan="3"><input class="easyui-textbox" type="text" name="editAddress" id="editAddress" style="width:293px" /></td>
            </tr>
            <tr >
                <td>&nbsp;&nbsp;籍贯：</td>
                <td colspan="3"><input class="easyui-textbox" type="text" name="editNativePlace" id="editNativePlace" style="width:293px" /></td>
            </tr>
            <tr >
                <td>&nbsp;&nbsp;学历：</td>
                <td><input id="editDegree" class="easyui-combobox" name="editDegree"
                           style="line-height:20px;width:100px;border:1px solid #ccc"
                           data-options="valueField:'dictCode',textField:'description',editable:false,panelHeight:'auto',
                                             url:'${pageContext.request.contextPath}/dictionary/selectByDictType.do?dictType=学历'" /></td>
                <td>毕业院校：</td>
                <td><input class="easyui-textbox" type="text" name="editCollege" id="editCollege" /></td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;所属部门：</td>
                <td colspan="3">
                    <div id="editDepartment" style="width:220px;height:100px"></div>
                </td>
            </tr>
            <tr>
                <td>&nbsp;&nbsp;备注：</td>
                <td colspan="3">
                    <textarea id="editDescription" rows=4 name="editDescription" style="width:293px;height:80px;font-size:12px"></textarea>
                </td>
            </tr>
            <tr height="45px">
                <td colspan="2" align="right"><a id="editSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="editCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
            </tr>
        </table>
        <!-- </form> -->
    </div>
</body>
</html>
<script language="javascript" type="text/javascript">
    var rights = "<%=rights%>";
    var rightArr = rights.split(",");

    $("#addBtn").hide();
    $("#editBtn").hide();
    $("#delBtn").hide();

    for(i=0;i<rightArr.length;i++){
        if(rightArr[i] =="add"){
            $("#addBtn").show();
        }else if(rightArr[i] =="update"){
            $("#editBtn").show();
        }else if(rightArr[i] =="delete"){
            $("#delBtn").show();
        }
    }
</script>