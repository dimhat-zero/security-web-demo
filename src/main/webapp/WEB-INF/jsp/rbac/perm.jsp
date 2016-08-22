<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link rel="stylesheet" href="/static/cus-icons.css">
    <link rel="stylesheet" href="/static/jquery-treetable/stylesheets/jquery.treetable.css">
    <link rel="stylesheet" href="/static/jquery-treetable/stylesheets/jquery.treetable.theme.default.css">


    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <title>权限管理</title>
    <style>
        i {cursor:pointer;}
    </style>
</head>
<body>

<table class="table">
    <thead>
    <th>权限字符串</th>
    <th>权限描述</th>
    <th>菜单</th>
    <th>排序号</th>
    <th>操作</th>
    </thead>
    <tbody>
        <c:forEach var="perm" items="${perms}">
        <tr data-tt-id="${perm.id}" data-tt-parent-id="${perm.parentId}">
            <td>${perm.permission}</td>
            <td>${perm.description}</td>
            <td>${perm.menu?"是":"否"}</td>
            <td>${perm.rank}</td>
            <td>
                <i class="cus-add" title="添加子节点"></i>
                <i class="cus-application-edit" title="修改"></i>
                <i class="cus-arrow-up" title="上移" data-id="${perm.id}"></i>&nbsp;
                <i class="cus-arrow-down" title="下移" data-id="${perm.id}"></i>
                <i class="cus-delete" title="删除" data-id="${perm.id}" data-desc="${perm.description}"></i>
            </td>
        </tr>
        </c:forEach>
    </tbody>
</table>


<script src="/static/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
<script src="/js/common.js"></script>
<script>
$(function () {
    $(".cus-delete").click(function(){
        var id=$(this).data("id");
        var desc=$(this).data("desc");
        if(confirm("是否删除权限【"+desc+"】")){
            $.post("/perm/"+id+"/delete",ajaxSuccessReload);
        }
    });
    
    $(".cus-arrow-up").click(function () {
        var id=$(this).data("id");
        $.post("/perm/"+id+"/shiftup",ajaxSuccessReload);
    });
    $(".cus-arrow-down").click(function () {
        var id=$(this).data("id");
        $.post("/perm/"+id+"/shiftdown",ajaxSuccessReload);
    });
});
</script>
</body>
</html>
