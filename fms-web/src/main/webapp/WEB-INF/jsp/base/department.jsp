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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/base/department.js"></script>
</head>

<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 基础信息管理 &gt; 部门信息管理</div>
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

<table id="deptList" class="easyui-treegrid" style="width:700px;height:365px"
       url="${pageContext.request.contextPath}/department/getHierarchyDepartment.do"
       title="部门信息列表"  toolbar="#toolBar"
       data-options="idField:'deptID',treeField:'deptName'">
    <thead>
    <tr>
        <th field="deptID" checkbox="true"></th>
        <th data-options="field:'deptName',width:240">部门名称</th>
        <th data-options="field:'description',width:240">备注</th>
        <th data-options="field:'isLeaf',width:80,align:'center'">基层部门</th>
        <th data-options="field:'sortNo',width:80,align:'center'">排列顺序</th>
    </tr>
    </thead>
</table>


<div class="easyui-window" id="deptAdd" style="width:360px;height:270px" title="添加部门信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <input id="addParentID" name="addParentID" type="hidden"  />
        <tr height="2px"><td colspan="4"></td></tr>
        <tr >
            <td>&nbsp;&nbsp;部门名称：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="addDeptName" id="addDeptName" required="true" style="width:175px" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;上级部门：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="addParentName" id="addParentName"  style="width:175px" readonly="true" /></td>
        </tr>
        <tr>
            <td width="75px">&nbsp;&nbsp;基层部门：</td>
            <td>
                <select id="addIsLeaf" class="easyui-combobox" name="addIsLeaf" style="width:45px;" data-options="editable:false,panelHeight:'auto'">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </td>
            <td width="75px">排列顺序：</td>
            <td><input class="easyui-numberbox" type="text" name="addSortNo" id="addSortNo" style="width:45px;" data-options="min:1,max:100" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="addDescription" rows=4 name="addDescription" style="width:211px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>
        <tr height="45px">
            <td colspan="2" align="right"><a id="addSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="addCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
        </tr>
    </table>
</div>

<div class="easyui-window" id="deptEdit" style="width:360px;height:270px" title="编辑部门信息"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <input id="editDeptID" name="editDeptID" type="hidden"  />
        <input id="editParentID" name="editParentID" type="hidden"  />
        <tr height="2px"><td colspan="4"></td></tr>
        <tr >
            <td>&nbsp;&nbsp;部门名称：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="editDeptName" id="editDeptName" required="true" style="width:175px" /></td>
        </tr>
        <tr >
            <td>&nbsp;&nbsp;上级部门：</td>
            <td colspan="3"><input class="easyui-textbox" type="text" name="editParentName" id="editParentName"  style="width:175px" readonly="true" /></td>
        </tr>
        <tr>
            <td width="75px">&nbsp;&nbsp;基层部门：</td>
            <td>
                <select id="editIsLeaf" class="easyui-combobox" name="editIsLeaf" style="width:45px;" data-options="editable:false,panelHeight:'auto'">
                    <option value="1">是</option>
                    <option value="0">否</option>
                </select>
            </td>
            <td width="75px">排列顺序：</td>
            <td><input class="easyui-numberbox" type="text" name="editSortNo" id="editSortNo" style="width:45px;" data-options="min:1,max:100" /></td>
        </tr>
        <tr>
            <td>&nbsp;&nbsp;备注：</td>
            <td colspan="3">
                <textarea id="editDescription" rows=4 name="editDescription" style="width:211px;height:60px;font-size:12px"></textarea>
            </td>
        </tr>
        <tr height="45px">
            <td colspan="2" align="right"><a id="editSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="editCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a></td>
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