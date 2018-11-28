<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/file/fileUpload.js"></script>
</head>

<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 文件管理 &gt; 文件上传</div>
<hr>

<div style="font-size:12px">
    <form id="fileUploadForm" method="post" enctype="multipart/form-data">


        <table width="550px" border="0" cellpadding="3" cellspacing="3">
            <input type="hidden" id="sharedStaffs" name="sharedStaffs">
            <tr height="30px">
                <td>&nbsp;&nbsp;共享类型：</td>
                <td colspan="4">
                    <input type="radio" checked="checked" name="shareType" value="0301" />&nbsp;局内共享
                    <input type="radio" name="shareType" value="0302" />&nbsp;科室共享
                    <input type="radio" name="shareType" value="0303" />&nbsp;自定义
                </td>
            </tr>
            <tr height="30px">
                <td>&nbsp;&nbsp;操作类型：</td>
                <td colspan="4">
                    <input type="checkbox" checked="checked" name="opType" value="0401" />&nbsp;只读
                    <input type="checkbox" name="opType" value="0402" />&nbsp;可变更
                    <input type="checkbox" name="opType" value="0403" />&nbsp;可删除
                </td>
            </tr>
            <tr height="30px">
                <td>&nbsp;&nbsp;文件类型：</td>
                <td colspan="4">
                    <input id="fileType" class="easyui-combobox" name="fileType"
                           data-options="valueField:'fileTypeID',textField:'fileTypeName',panelHeight:'auto' , url:  '${pageContext.request.contextPath}/fileType/getFileTypeList.do'">
                </td>
            </tr>

            <tr height="30px">
                <td>&nbsp;&nbsp;文件夹名称：</td>
                <td colspan="4">
                    <input class="easyui-textbox" id="folderName" name="folderName" style="width:143px"> &nbsp;&nbsp;
                    <span style="color:red;"> 非必填项！若不为空，则将文件存于该文件夹下！ </span>
                </td>
            </tr>

            <tr height="30px">
                <td width="90px">&nbsp;&nbsp;选择文件：</td>
                <td colspan="3">
                    <input id="files" name="sourceFiles" class="easyui-filebox" style="width:350px" buttonText="选择文件" multiple="true">
                </td>
                <td><a id="submitBtn" class="easyui-linkbutton" style="width:60px">确定</a></td>
            </tr>

        </table>

    </form>

    <div id="shareSetting" style="visibility:hidden">
        <table width="550px" border="0" cellpadding="3" cellspacing="3">
            <tr>
                <td valign="top"  width="90px">&nbsp;&nbsp;自定义共享：</td>
                <td >
                    <div id="sharePanel" class="easyui-panel" title="部门/职员"
                         style="width:293px;height:250px;padding:10px;background:#fafafa;">
                        <ul id="deptStaff"></ul>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</div>

</body>
</html>