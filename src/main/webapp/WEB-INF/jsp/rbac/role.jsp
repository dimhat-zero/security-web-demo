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

    <title>角色管理</title>
    <style>
        i {cursor:pointer;}
    </style>
</head>
<body>
<div id="containtor">
    新建角色<i class="cus-add" title="增加"></i>
    <table class="table" id="permTable">
        <thead>
        <th width="15%">角色名称</th>
        <th width="20%">角色描述</th>
        <th width="35%">拥有权限</th>
        <th width="10%">锁定</th>
        <th width="20%">操作</th>
        </thead>
        <tbody>
        <c:forEach var="role" items="${roles}">
            <tr>
                <td>${role.roleName}</td>
                <td>${role.description}</td>
                <td>${role.permNames()}</td>
                <td>
                    <c:if test="${role.roleName!='admin'}">
                    <i class="cus-table-edit" title="修改" data-id="${role.id}"></i>
                    <i class="cus-lock-add"></i><i class="cus-lock-delete"></i>
                    <i class="cus-delete" title="删除" data-id="${role.id}" data-desc="${role.description}"></i>
                    </c:if>
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
<script src="/js/common.js"></script>
<script src="/js/jquery.form.js"></script>
<script>
    $(function () {
        $(".cus-add").click(function () {
            var id=$(this).data("id");
            $("#myModalLabel").html("增加角色");
            $("#myModal").modal("show");
            var url = "/role/add";
            $.get(url,function (data) {
                $("#modal-content").html(data);
                $("#editForm").prop("action",url);
            });
        });

        $(".cus-table-edit").click(function () {
            var id=$(this).data("id");
            $("#myModalLabel").html("修改角色");
            $("#myModal").modal("show");
            var url="/role/"+id+"/update";
            $.get(url,function (data) {
                $("#modal-content").html(data);
                $("#editForm").prop("action",url);
            })
        })

        $(".cus-delete").click(function(){
            var id=$(this).data("id");
            var desc=$(this).data("desc");
            if(confirm("是否删除角色【"+desc+"】")){
                $.post("/role/"+id+"/delete",ajaxSuccessReload);
            }
        });
        //ajax提交表单
        $("#editSubmit").click(function () {
            $("#editForm").ajaxSubmit({
                success:function(data) {
                    if(data && data.success){
                        location.reload();
                    }else{
                        alert(data.msg);
                    }
                }
            });
        });

    });
</script>
</body>
</html>
