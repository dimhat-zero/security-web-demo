<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>XSS Demo</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>
    <h1>文章</h1>
    <h2>评论</h2>
    <c:if test="${safe==true}">
        <c:out value="${commit}"></c:out>
    </c:if>
    <c:if test="${empty safe||safe==false}">
        ${commit}
    </c:if>

    <h2>提交评论</h2>
    <form action="/xss/commit" class="form-horizontal">
        <div class="form-group">
            <label for="commit" class="col-sm-1 control-label">评论</label>
            <div class="col-sm-2"><input class="form-control" type="text" name="commit" value="<c:out value="${commit}"/>"></div>
            <div class="col-sm-2">测试代码（安全模式下会进行转义）：&lt;script&gt;alert(document.cookie)&lt;/script&gt;</div>
        </div>

        <div class="form-group">
            <label for="safe" class="col-sm-1 control-label">安全模式</label>
            <div class="col-sm-2">
            是
            <input type="radio" name="safe" value="true">
            否
            <input  type="radio" name="safe" value="false">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2 col-sm-offset-1">
                <button type="submit">提交</button>
            </div>

        </div>

    </form>
</body>
</html>
