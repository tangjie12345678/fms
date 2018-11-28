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

    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/beta/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/js/beta/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/resources/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/page/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/resources/easyui/themes/default/easyui.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/resources/easyui/themes/icon.css" type="text/css"/>

    <title>文件管理系统</title>
</head>

<body class="easyui-layout">
    <div data-options="region:'north',split:true"  style="height:139px;">
        <img src="<%=request.getContextPath()%>/page/image/header.jpg"/>
    </div>
<!--    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div> -->
<!--    <div data-options="region:'east',title:'East',split:true" style="width:100px;"></div>-->
        <div data-options="region:'west',title:'菜单',split:true" style="width:200px;">
            <div id="menu" class="easyui-accordion" style="width:193px;">
                <c:forEach items="${sessionScope.menuList}" var="item" varStatus="menuStatus">
                    <c:if test="${item.parentID == null && menuStatus.first == false}">
                            </dl>
                        </div>
                    </c:if>
                    <c:if test="${item.parentID == null}">
                        <div id="${item.menuCode}" title="${item.menuName}" style="overflow:auto;padding:10px;">
                                <dl>
                    </c:if>

                    <c:if test="${item.parentID != null}">
                            <dt><a href="${pageContext.request.contextPath}${item.href}"  target="mainFrame" plain="true"  class="easyui-linkbutton">${item.menuName}</a></dt>
                    </c:if>
                    <c:if test="${menuStatus.last == true }">
                            </dl>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
        <div data-options="region:'center'" style="padding:5px;background:#eee;">
            <iframe id="mainFrame" name="mainFrame" scrolling="auto" frameborder="0"  style="width:100%;height:100%;"></iframe>
        </div>
</body>