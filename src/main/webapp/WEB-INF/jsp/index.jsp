<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet"
          href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- add jquery-ui -->
    <link rel="stylesheet" href="/static/jquery-ui-1.12.0/jquery-ui.min.css" >
    <script type="application/javascript" src="/static/jquery-ui-1.12.0/jquery-ui.min.js"></script>
</head>
<body>
<h1>首页</h1>
<div><form action="/logout" method="post"><button type="submit">退出登录</button></form></div>
<div><a href="/rbac">权限</a></div>
<div><button id="rbacBtn">角色ajax</button></div>
<div id="dialog_show"  title="基本的对话框">
    <form action="/user/home" class="form-horizontal">
        <label class="control-label col-sm-3">姓名</label>
        <div class="col-sm-9"><input class="form-control" type="text" name="name"/></div>
        <label class="control-label col-sm-3">邮箱</label>
        <div class="col-sm-9"><input class="form-control" type="text" name="email"/></div>
        <button>个人主页</button>
    </form>
</div>
<script type="application/javascript">
$(function () {
    $("#dialog_show").dialog();

    $("#rbacBtn").click(function(){
        $.get("/rbac/role",function(data){
           console.log(data);
            if(data && data.success){
                console.log("success");
            }else{
                if(data.code=="${exceptionCode.notLoginCode}"){
                    alert("未登录，弹出登录框");
                }else{
                    alert("未识别的异常："+data.msg);
                }
            }
        });
    })
});

</script>
</body>
</html>