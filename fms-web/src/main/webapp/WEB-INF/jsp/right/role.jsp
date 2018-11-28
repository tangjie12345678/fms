<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    Map<String,List<String>> permissionMaps = (Map<String,List<String>>)session.getAttribute("permissionMaps");
    List<String> rightList = permissionMaps.get("role");
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/right/role.js"></script>
</head>

<body style="background:#FFF">


<div id="location" style="font-size:12px">当前位置 : 用户权限管理 &gt; 角色管理</div>
<hr>

<div id="searchBar" style="padding:2px;font-size:12px">
    <span>角色:&nbsp;</span>
    <input id="roleName" class="easyui-textbox"  style="line-height:20px;width:60px;border:1px solid #ccc">
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

<table id="roleList" class="easyui-datagrid" style="height:365px;width:700px"
       url="${pageContext.request.contextPath}/role/getRoleList.do"
       title="员工信息列表" toolbar="#toolBar" idField="roleID"
       rownumbers="true" pagination="true" checkOnSelect="true">
    <thead>
    <tr>
        <th field="roleID" checkbox="true"></th>
        <th field="role" width="160">角色</th>
        <th field="description" width="340">备注</th>
    </tr>
    </thead>
</table>

<div class="easyui-window" id="roleAdd" style="width:450px;height:450px" title="添加角色信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr >
            <td width="70px">&nbsp;&nbsp;角色：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="addRole" id="addRole" required="true" /></td>
        </tr>

        <tr >
            <td valign="top">&nbsp;&nbsp;权限设置：</td>
            <td colspan="3">
                <div id="addPermissionPanel" class="easyui-panel" title="功能列表"
                     style="width:300px;height:250px;padding:10px;background:#fafafa;">
                    <ul id="addMenuPermission"></ul>
                </div>

            </td>
        </tr>

        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="addDescription" rows=4 name="addDescription" style="width:295px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
</div>

<div class="easyui-window" id="roleEdit" style="width:450px;height:450px" title="编辑角色信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <input id="editRoleID" name="editRoleID" type="hidden" />
    <input id="oldPermissions" name="oldPermissions" type="hidden" />
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr >
            <td width="70px">&nbsp;&nbsp;角色：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="editRole" id="editRole" required="true" /></td>
        </tr>

        <tr >
            <td valign="top">&nbsp;&nbsp;权限设置：</td>
            <td colspan="3">
                <div id="editPermissionPanel" class="easyui-panel" title="功能列表"
                     style="width:300px;height:250px;padding:10px;background:#fafafa;">
                    <ul id="editMenuPermission"></ul>
                </div>

            </td>
        </tr>

        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="editDescription" rows=4 name="editDescription" style="width:295px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="editSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
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