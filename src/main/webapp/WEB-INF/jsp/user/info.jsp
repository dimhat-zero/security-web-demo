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

<title>用户信息修改</title>

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
		<h1>用户信息修改</h1>
		<!-- 默认escapeXml="true"，防xss -->
		<c:out value="${msg }"></c:out>
		<form action="" method="post" class="form-horizontal">
			<input type="hidden" name="token" value="${token }" />
			<div class="form-group">
				<label class="col-sm-2 control-label">账号：</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" name="username" value="${user.username }" readonly
						placeholder="账号" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">昵称：</label>
				<div class="col-sm-4">
					<input class="form-control" type="text" name="nickName" value="<c:out value="${user.nickName }" />"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit">修改</button>
				</div>
		</form>
	</div>
</body>
</html>
