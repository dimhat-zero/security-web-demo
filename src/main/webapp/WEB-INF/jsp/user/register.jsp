<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>注册</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>

<body>
	<div style="width:900px;margin-left: auto;margin-right: auto;">
		<h1>注册账号</h1>
		<!-- 默认escapeXml="true"，防xss -->
		<c:out value="${msg }"></c:out>
		<form action="" method="post" class="form-horizontal">
			<div class="form-group">
				<label class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" name="username"
						placeholder="账号" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">密码：</label>
				<div class="col-sm-4">
					<input class="form-control" type="password" name="password" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">验证码：</label>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="veriCode" />
				</div>
				<div class="col-sm-2">
					<img width="120" height="34" src="/veriCode" />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit">注册</button>
				</div>
		</form>
	</div>
</body>
</html>
