<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Map<String,List<String>> permissionMaps = (Map<String,List<String>>)session.getAttribute("permissionMaps");
    List<String> rightList = permissionMaps.get("fileType");
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/base/fileType.js"></script>
</head>

<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 基础信息管理 &gt; 文件类型管理</div>
<hr>
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

<table id="fileTypeList" class="easyui-datagrid" style="height:365px;width:700px"
       url="${pageContext.request.contextPath}/fileType/getFileTypePageList.do"
       title="文件类型列表" toolbar="#toolBar" idField="fileTypeID"
       rownumbers="true" pagination="true" checkOnSelect="true">
    <thead>
    <tr>
        <th field="fileTypeID" checkbox="true"></th>
        <th field="fileTypeName" width="150">文件类型</th>
        <th field="fileTypeDesc" width="400">备注</th>
    </tr>
    </thead>
</table>


<div class="easyui-window" id="fileTypeAddWindow" style="width:385px;height:200px" title="添加文件类型"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr>
            <td width="70px">&nbsp;&nbsp;文件类型：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="addFileTypeName" id="addFileTypeName" required="true" /></td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="addFielTypeDesc" rows=4 name="addFileTypeDesc" style="width:250px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
</div>

<div class="easyui-window" id="fileTypeEditWindow" style="width:385px;height:200px" title="编辑文件类型"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <input id="editFileTypeID" name="editFileTypeID" type="hidden" />
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <tr>
            <td width="70px">&nbsp;&nbsp;文件类型：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="editFileTypeName" id="editFileTypeName" required="true" /></td>
        </tr>
        <tr>
            <td valign="top">&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="editFileTypeDesc" rows=4 name="editFileTypeDesc" style="width:250px;height:60px;font-size:12px"></textarea>
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