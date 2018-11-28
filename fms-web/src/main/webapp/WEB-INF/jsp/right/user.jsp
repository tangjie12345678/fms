<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Map<String,List<String>> permissionMaps = (Map<String,List<String>>)session.getAttribute("permissionMaps");
    List<String> rightList = permissionMaps.get("department");
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/right/user.js"></script>
</head>

<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 用户权限管理 &gt; 用户管理</div>
<hr>

<div id="searchBar" style="padding:2px;font-size:12px">
    <span>用户名:&nbsp;</span>
    <input id="userName" class="easyui-textbox"  style="line-height:20px;width:60px;border:1px solid #ccc">
    &nbsp;<span>员工姓名:&nbsp;</span>
    <input id="staffName" class="easyui-textbox"  style="line-height:20px;width:60px;border:1px solid #ccc">
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

<table id="userList" class="easyui-datagrid" style="height:365px;width:700px"
       url="${pageContext.request.contextPath}/user/getUserList.do"
       title="用户信息列表" toolbar="#toolBar" idField="roleID"
       rownumbers="true" pagination="true" checkOnSelect="true">
    <thead>
    <tr>
        <th field="userID" checkbox="true"></th>
        <th field="userName" width="80">用户名</th>
        <th field="staffName" width="80">员工</th>
        <th field="locked" width="80">锁定</th>
        <th field="description" width="240">备注</th>
    </tr>
    </thead>
</table>

<div class="easyui-window" id="userAdd" style="width:385px;height:420px" title="添加用户信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr >
            <td width="70px">&nbsp;&nbsp;用户名：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="addUserName" id="addUserName" required="true" /></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;密码：</td>
            <td colspan="3"><input class="easyui-validatebox" name="addPassword" id="addPassword" type="password" data-options="required:true" ></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;确认密码：</td>
            <td colspan="3"><input class="easyui-validatebox" name="addCnfmPassword" id="addCnfmPassword" type="password"  data-options="required:true" validType="equals['#addPassword']" ></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;员工：</td>
            <td colspan="3">
                <select id="addStaff" name="addStaff" class="easyui-combogrid" style="width:150px;"
                        data-options="panelWidth:190,
                                   idField:'staffRecordID',
                                   textField:'staffName',
                                   url:'${pageContext.request.contextPath}/staff/getAllStaffForDropdownList.do',
                                   columns:[[
                                    {field:'staffID',title:'编号',width:80},
                                    {field:'staffName',title:'姓名',width:100}
                                    ]]"></select>

            </td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;角色：</td>
            <td colspan="3">
                <ul class="easyui-datalist" id="addRole" title="角色列表" style="width:200px;height:130px"
                    data-options="url:'${pageContext.request.contextPath}/role/getAllRole.do',
                               checkbox:true,
                               lines:true,
                               valueField:'roleID',
                               textField:'role',
                               singleSelect:false,
                               checkOnSelect:true"></ul>
            </td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="addDescription" rows=4 name="addDescription" style="width:250px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
</div>

<div class="easyui-window" id="userEdit" style="width:385px;height:370px" title="修改用户信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <input id="editUserID" name="editUserID" type="hidden" />
    <input id="oldRoles" name="oldRoles" type="hidden" />
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr >
            <td width="70px">&nbsp;&nbsp;用户名：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="editUserName" id="editUserName" required="true" /></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;员工：</td>
            <td colspan="3">
                <select id="editStaff" name="editStaff" class="easyui-combogrid" style="width:150px;"
                        data-options="panelWidth:190,
                                   idField:'staffRecordID',
                                   textField:'staffName',
                                   url:'${pageContext.request.contextPath}/staff/getAllStaffForDropdownList.do',
                                   columns:[[
                                    {field:'staffID',title:'编号',width:80},
                                    {field:'staffName',title:'姓名',width:100}
                                    ]]"></select>

            </td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;角色：</td>
            <td colspan="3">
                <ul class="easyui-datalist" id="editRole" title="角色列表" style="width:200px;height:130px"
                    data-options="url:'${pageContext.request.contextPath}/role/getAllRole.do',
                               checkbox:true,
                               lines:true,
                               valueField:'roleID',
                               textField:'role',
                               singleSelect:false,
                               checkOnSelect:true"></ul>
            </td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="editDescription" rows=4 name="editDescription" style="width:250px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="editSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="editCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
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