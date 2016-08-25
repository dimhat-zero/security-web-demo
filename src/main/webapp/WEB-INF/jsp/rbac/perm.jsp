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

    <link rel="stylesheet" href="/static/jquery-zTree-v3.5.15/css/zTreeStyle/zTreeStyle.css">
    <script src="/static/jquery-zTree-v3.5.15/js/jquery.ztree.all-3.5.min.js"></script>

    <title>权限管理</title>
    <style>
        i {cursor:pointer;}
        .ztree li span.button.dir_ico_open{
            margin-right: 2px;
            background-position: -110px -16px;
            vertical-align: top;
        }
        .ztree li span.button.dir_ico_close{
            margin-right: 2px;
            background-position: -110px 0;
            vertical-align: top;
        }
        .ztree li span.button.dir_ico_docu{
            margin-right: 2px;
            background-position: -110px 0px;
            vertical-align: top;
        }

        /*侧边树形导航*/
        .s-sidebar{
            width:220px;
            height:100%;
            z-index: 1;
            margin-top: 0px;
            position: absolute;
            /*position: fixed;*/
            background: #fff;
            box-shadow: 1px 0 3px #aaa;
            overflow-x: scroll;
        }
        .s-sidebar > .tree-content{
            margin-top: 120px;
            padding:5px;
            border-bottom: 1px solid #eee;
        }
        .s-sidebar > .tree-content > p{
            line-height: 30px;
            font-size: 16px;
            margin:0;
            padding:0;
            text-indent: 10px;
        }
    </style>
</head>
<body>

<ol class="breadcrumb" id="dir_path_ol"></ol>

<div class="s-sidebar">
    <ul class="tree ztree" id="tree"></ul>
    <form id="queryForm" style="display: none">
        <input type="hidden" id="dir_id" name="parentId" value="0">
        <input type="hidden" id="description" name="description">
        <input type="hidden" name="withParent" value="true">
    </form>
</div>


<div id="containtor">
<table class="table" id="permTable">
    <thead>
    <th width="25%">权限字符串</th>
    <th width="25%">权限描述</th>
    <th width="15%">菜单</th>
    <th width="20%">操作</th>
    </thead>
    <tbody>

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
<script src="/js/perm-manage.js"></script>
</body>
</html>
