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
<title>个人首页</title>
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
	<h1>个人首页</h1>
	<c:out value="${msg }" />
	欢迎：${userInfo.username }登录，昵称为：<c:out value="${userInfo.nickName } " />
	<div class="form-group">
		<label class="col-sm-2 control-label">修改用户信息：</label>
		<div class="col-sm-4">
			<a href="/user/update">修改用户信息</a>
		</div>
	</div>
	
</form>
</div>
</body>
</html>

