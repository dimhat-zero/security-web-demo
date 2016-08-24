<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="editForm" method="post" class="form-horizontal">
    <input type="hidden" name="id" id="id" value="${role.id}">
    <div class="form-group">
        <label class="control-label col-sm-3">角色字符串</label>
        <div class="col-sm-8"><input type="text" class="form-control" name="roleName" value="${role.roleName}" placeholder="请输入角色"> </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">角色描述</label>
        <div class="col-sm-8"><input type="text" class="form-control" name="description" value="${role.description}" placeholder="请输入角色描述"></div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">拥有权限</label>
        <input type="hidden" name="permIds" id="permIds">
        <div class="col-sm-6">
            <p id="permNames">${role.permNames()}</p>
            <div id="menuContent" class="menuContent" style="display:none; position: absolute;background-color:aliceblue;">
                <ul id="tree" class="ztree" style="margin-top:0; width:160px;"></ul>
            </div>
        </div>
        <div class="col-sm-2"><a id="menuBtn" class="btn " href="#">选择权限</a></div>
    </div>
</form>


<link rel="stylesheet" href="/static/jquery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
<script src="/static/jquery-zTree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script src="/js/role-perm-select.js"></script>
