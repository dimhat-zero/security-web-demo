<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editForm" method="post" class="form-horizontal">
    <input type="hidden" name="id" id="id" value="${user.id}">
    <div class="form-group">
        <label class="control-label col-sm-3">用户名</label>
        <div class="col-sm-8"><input type="text" class="form-control" value="${user.username}" readonly> </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">昵称</label>
        <div class="col-sm-8"><input type="text" class="form-control" name="nickName" value="${user.nickName}" placeholder="请输入昵称"></div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">拥有角色</label>
        <input type="hidden" name="roleIds" id="roleIds" value="${user.roleIds()}">
        <div class="col-sm-6">
            <p id="roleNames">${user.roleNames()}</p>
            <div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color:aliceblue;">
                <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
            </div>
        </div>
        <div class="col-sm-2"><a id="menuBtn" class="btn " href="#">选择权限</a></div>
    </div>
</form>


<link rel="stylesheet" href="/static/jquery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
<script src="/static/jquery-zTree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script src="/js/user-role-select.js"></script>
