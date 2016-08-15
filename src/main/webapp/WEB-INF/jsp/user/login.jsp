<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div style="width:900px;margin-left: auto;margin-right: auto;">

<form action="" method="post" class="form-horizontal">
	<h1>登录</h1>
	<c:out value="${msg }" />
	<div class="form-group">
		<label class="col-sm-2 control-label">账号：</label>
		<div class="col-sm-4">
			<input class="form-control" type="text" name="username" placeholder="账号" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">密码：</label>
		<div class="col-sm-4">
			<input class="form-control" type="password" name="password" />
		</div>
	</div>
	<c:if test="${veriCode==true }">
	<div class="form-group">
		<label class="col-sm-2 control-label">验证码：</label>
		<div class="col-sm-2">
			<input class="form-control" type="text" name="veriCode" />
		</div>
		<div class="col-sm-2">
			<img width="120" height="34" src="/veriCode" />
		</div>
	</div>
	</c:if>
	
	<div class="form-group">
    <div class="col-sm-offset-2 col-sm-1">
	<button type="submit" >登录</button>
	</div>
	<div class="col-sm-9">
		<a href="/register">还没有账号？注册</a>
	</div>
</form>
</div>
</body>
</html>

