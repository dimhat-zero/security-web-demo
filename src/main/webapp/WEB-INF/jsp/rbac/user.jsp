<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <link rel="stylesheet" href="/static/cus-icons.css">
    <link rel="stylesheet" href="/static/jquery-ui-1.12.0/jquery-ui.min.css">

    <link rel="stylesheet" href="/css/style.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <title>用户管理</title>
    <style>
        i {cursor:pointer;}
    </style>
</head>
<body>
<div id="containtor">
    <table class="table" id="permTable">
        <thead>
        <th width="20%">用户名</th>
        <th width="20%">昵称</th>
        <th width="30%">拥有角色</th>
        <th width="10%">锁定</th>
        <th width="20%">操作</th>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.username}</td>
                <td>${user.nickName}</td>
                <td>${user.roleNames()}</td>
                <td>${user.locked?"是":"否"}</td>
                <td>
                    <i class="cus-table-edit" title="修改" data-id="${user.id}"></i>
                    <c:if test="${not user.locked}"><i class="cus-lock-add" title="禁用" data-id="${user.id}"></i></c:if>
                    <c:if test="${user.locked}"><i class="cus-lock-delete" title="解锁" data-id="${user.id}"></i></c:if>
                    <i class="cus-delete" title="删除" data-id="${user.id}" data-name="${user.username}"></i>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" data-backdrop="static"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"
                        data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    模态框（Modal）标题
                </h4>
            </div>
            <div class="modal-body">
                <div id="modal-content"></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
                <button type="button" id="editSubmit" class="btn btn-primary">
                    提交更改
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="/static/jquery-treetable/javascripts/src/jquery.treetable.js"></script>
<script src="/js/jquery.form.js"></script>
<script src="/js/common.js"></script>
<script src="/js/user-manage.js"></script>
</body>
</html>
