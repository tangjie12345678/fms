<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/beta/common.js"></script>
    <script type="text/javascript" src = "${pageContext.request.contextPath}/page/js/beta/login.js"></script>


    <title>文件管理系统</title>

    <style type="text/css">
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }

        #loginTable{
            width:350px;
            height:200px;
            text-align:left;
            vertical-align:middle;
            margin:0 auto;
            /*position: relative;
            top: 50%;
            transform: translateY(-50%);*/
        }

    </style>
</head>
<body>
<form>
    <div class="errorMsg" ></div>
    <table id="loginTable">
        <tr>
            <td colspan="3" align="center">文件管理系统</td>
        </tr>
        <tr>
            <td	style="width:80px">用户名 ：</td>
            <td style="width:160px"><input name="userName" type="text" id="userName" size="20" /></td>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td>密码 ：</td>
            <td><input name="password" type="password" id="password" size="20" /></td>
            <td><input type="button"  name="submit" id="submit" value="登录" onclick="return loginIn()" /></td>
        </tr>
    </table>
   
</form>
</body>
</html>