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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/right/pwdModify.js"></script>
</head>

<body style="background:#FFF">

<div id="location" style="font-size:12px">当前位置 : 用户权限管理 &gt; 密码修改</div>
<hr>

<div style="font-size:12px">
    <table width="250px" border="0" cellpadding="3" cellspacing="3">
        <tr >
            <td width="70px">&nbsp;&nbsp;用户名：</td>
            <td colspan="3">
                <input class="easyui-textbox" id="userName" value="${sessionScope.userName}" readonly="true">
            </td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;当前密码：</td>
            <td colspan="3"><input class="easyui-validatebox" id="oldPassword" type="password" data-options="required:true" ></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;新密码：</td>
            <td colspan="3"><input class="easyui-validatebox"  id="newPassword" type="password" data-options="required:true" ></td>
        </tr>
        <tr >
            <td width="70px">&nbsp;&nbsp;确认密码：</td>
            <td colspan="3"><input class="easyui-validatebox"  id="newCnfmPassword" type="password"  data-options="required:true" validType="equals['#newPassword']" ></td>
        </tr>

        <tr height="45px">
            <td colspan="2" align="right">&nbsp;&nbsp;&nbsp;&nbsp;<a id="submitBtn" class="easyui-linkbutton" style="width:60px">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
            <td colspan="2" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" style="width:60px">清空</a></td>
        </tr>
    </table>
</div>

</body>
</html>