<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action="/perm/saveOrUpdate" id="editForm" method="post" class="form-horizontal">
    <input type="hidden" name="id" value="${perm.id}">
    <input type="hidden" name="parentId" value="${perm.parentId}">
    <div class="form-group">
        <label class="control-label col-sm-3">父权限</label>
        <div class="col-sm-8"><p class="form-control-static">${perm.parentDescription}</p></div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">权限字符串</label>
        <div class="col-sm-8"><input type="text" class="form-control" name="permission" value="${perm.permission}" placeholder="*是通配符"> </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">权限描述</label>
        <div class="col-sm-8"><input type="text" class="form-control" name="description" value="${perm.description}" placeholder="权限描述"></div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-3">菜单</label>
        <div class="col-sm-8">
            <label class="radio-inline"><input type="radio" name="menu" value="true" <c:if test="${perm.menu eq true}">checked="checked"</c:if>>是</label>
            <label class="radio-inline"><input type="radio" name="menu" value="false" <c:if test="${perm.menu eq false}">checked="checked"</c:if>>否</label>
        </div>
    </div>
</form>
