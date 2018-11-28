<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<%
    Map<String,List<String>> permissionMaps = (Map<String,List<String>>)session.getAttribute("permissionMaps");
    List<String> rightList = permissionMaps.get("filemanage");
    StringBuilder rightsBuilder = new StringBuilder();
    for(String right : rightList){
        rightsBuilder.append(right).append(",");
    }
    String rights = rightsBuilder.toString().substring(0,rightsBuilder.length()-1);
%>

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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/file/fileManage.js"></script>
</head>
<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 文件管理 &gt; 文件管理</div>

<hr>

<div id="searchBar" style="padding:2px;font-size:12px">
    <span>文件名称:</span>
    <input id="fileName"  class="easyui-textbox" style="line-height:20px;width:150px;border:1px solid #ccc">
    &nbsp;&nbsp;<span>文件类型:</span>
    <input id="fileType" class="easyui-combobox" name="fileType"
           style="line-height:20px;border:1px solid #ccc"
           data-options="valueField:'fileTypeID',textField:'fileTypeName',editable:false,panelHeight:'auto',
    		url:'${pageContext.request.contextPath}/fileType/getFileTypeList.do'" />
    &nbsp;&nbsp;<span>文件夹:</span>
    <input id="folderName" class="easyui-textbox" style="line-height:20px;width:100px;border:1px solid #ccc">
    &nbsp;&nbsp;<span>创建者:</span>
    <input id="creator" class="easyui-textbox" style="line-height:20px;width:60px;border:1px solid #ccc">
    &nbsp;&nbsp;<span>修改者:</span>
    <input id="updator" class="easyui-textbox" style="line-height:20px;width:60px;border:1px solid #ccc">

    &nbsp;&nbsp;<a id="searchBtn" class="easyui-linkbutton">查询</a>
</div>

<div id="toolBar" style="padding:1px;height:auto">
    <div>
        &nbsp;&nbsp;
        <a href="#" id="propertySet" class="easyui-linkbutton" iconCls="icon-edit"  text="属性重设" plain="true"></a>
        &nbsp;&nbsp;
        <a href="#" id="fileDownload" class="easyui-linkbutton" iconCls="icon-edit"  text="文件下载" plain="true"></a>
        &nbsp;&nbsp;
        <a href="#" id="fileReplace" class="easyui-linkbutton" iconCls="icon-edit"  text="文件替换" plain="true"></a>
        &nbsp;&nbsp;
        <a href="#" id="fileDelete" class="easyui-linkbutton" iconCls="icon-remove"  text="文件删除" plain="true"></a>
    </div>
</div>

<table id="fileList" class="easyui-datagrid" style="height:365px"
       url="${pageContext.request.contextPath}/filemanage/getFileList.do"
       title="共享文件列表" idField="fileID" toolbar="#toolBar"
       rownumbers="true" pagination="true">
    <thead>
    <tr>
        <th field="fileID" checkbox="true"></th>
        <th field="fileName" width="200">文件名称</th>
        <th field="fileTypeName" width="120">文件类型</th>
        <th field="folderName" width="150" >文件夹</th>
        <th field="creatorName" width="80" >创建者</th>
        <th field="createTime" width="150" >创建时间</th>
        <th field="updatorName" width="80">修改者</th>
        <th field="updateTime" width="150">修改时间</th>
    </tr>
    </thead>
</table>

<div class="easyui-window" id="editFileProperty" style="width:500px;height:370px" title="编辑文件属性"
     data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
    <!--<form id="StaffInfo" method="post">-->
    <table width="100%" border="0" cellpadding="2" cellspacing="2">
        <input type="hidden" id="editFileID" name="editFileID">
        <input type="hidden" id="oldOpRights" name="oldOpRights">
        <input type="hidden" id="editOldSharedStaffs" name="editOldSharedStaffs">
        <input type="hidden" id="editNewSharedStaffs" name="editNewSharedStaffs">
        <tr height="30px">
            <td>&nbsp;&nbsp;共享类型：</td>
            <td colspan="2">
                <input type="radio" name="editShareType" value="0301" />&nbsp;局内共享
                <input type="radio" name="editShareType" value="0302" />&nbsp;科室共享
                <input type="radio" name="editShareType" value="0303" />&nbsp;自定义
            </td>
        </tr>
        <tr height="30px">
            <td width="85px">&nbsp;&nbsp;操作类型：</td>
            <td width="250px">
                <input type="checkbox" name="editOpType" value="0401" />&nbsp;只读
                <input type="checkbox" name="editOpType" value="0402" />&nbsp;可变更
                <input type="checkbox" name="editOpType" value="0403" />&nbsp;可删除
            </td>
            <td width="160px">
                &nbsp;&nbsp;<a id="editSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;<a id="editCancelBtn" class="easyui-linkbutton" style="width:60px">取消</a>
            </td>
        </tr>
    </table>

    <div id="editShareSetting" style="visibility:hidden">
        <table width="400px" border="0" cellpadding="3" cellspacing="3">
            <tr>
                <td valign="top"  width="90px">&nbsp;&nbsp;自定义共享：</td>
                <td >
                    <div id="sharePanel" class="easyui-panel" title="部门/职员"
                         style="width:296px;height:250px;padding:10px;background:#fafafa;">
                        <ul id="editDeptStaff"></ul>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</div>


    <div class="easyui-window" id="replaceFileWindow" style="width:450px;height:210px" title="文件替换"
         data-options="closed:true,inline:false,collapsible:false,minimizable:false,maximizable:false,draggable:false,resizable:false">
        <form id="fileReplaceForm" method="post" enctype="multipart/form-data">
            <input type="hidden" id="replaceFileID" name="replaceFileID">
            <table width="95%" border="0" cellpadding="2" cellspacing="2">
                <tr height="30px">
                    <td>&nbsp;&nbsp;文件名称:</td>
                    <td colspan="2"><span id="replacedFileName"></span></td>
                </tr>
                <tr height="30px">
                    <td>&nbsp;&nbsp;文件类型:</td>
                    <td colspan="2"><span id="replacedFileType"></span></td>
                </tr>
                <tr height="30px">
                    <td>&nbsp;&nbsp;创建者:</td>
                    <td colspan="2"><span id="replacedFileCreator"></span></td>
                </tr>
                <tr height="30px">
                    <td width="90px">&nbsp;&nbsp;选择文件：</td>
                    <td>
                        <input id="replaceFile" name="sourceFiles" class="easyui-filebox" style="width:250px" buttonText="选择文件" multiple="false">
                    </td>
                    <td><a id="replaceSubmitBtn" class="easyui-linkbutton" style="width:60px">确定</a></td>
                </tr>
            </table>
        </form>
    </div>


<form id="fileDownForm" method="post">
    <input type="hidden" id="fileUrls" name="fileUrls">
</form>

<form id="fileOpenForm" method="post">
    <input type="hidden" id="fileUrl" name="fileUrl">
</form>

</body>
</html>

<script language="javascript" type="text/javascript">
    var rights = "<%=rights%>";
    var rightArr = rights.split(",");

    $("#propertySet").hide();
    $("#fileDownload").hide();
    $("#fileReplace").hide();
    $("#fileDelete").hide();

    for(i=0;i<rightArr.length;i++){
        if(rightArr[i] =="propertySet"){
            $("#propertySet").show();
        }else if(rightArr[i] =="fileDownload"){
            $("#fileDownload").show();
        }else if(rightArr[i] =="fileReplace"){
            $("#fileReplace").show();
        }else if(rightArr[i] =="fileDelete"){
            $("#fileDelete").show();
        }
    }
</script>