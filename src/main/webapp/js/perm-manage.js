$(function () {
    /*
     * ztree 配置
     */
    var setting = {
        view: {
            dblClickExpand: true,
            showLine: true,//是否显示连接线
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable:true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: ""
            }
        },
        callback: {
            onClick:function(event,treeId,treeNode){//功能2：点击目录事件
                loadDirPath(treeNode.id);
                $("#dir_id").val(treeNode.id);//记录当前路径，作为图片上传的路径
                loadFile();
            }
        }
    };

    var zNodes;
    var zTree;
    //功能3：导入目录
    function loadTree(){
        $.get("/perm/dir-tree",function(data){
            if(typeof(data)!="object") data=JSON.parse(data);
            if(data && data.success){
                if(zTree) zTree.destroy();
                zNodes = data.data;
                zTree = $.fn.zTree.init($("#tree"), setting, zNodes);
                var selectedNode = zTree.getNodeByParam("id", $("#dir_id").val(), null);//获得节点
                zTree.expandNode(selectedNode,true,false,false);//展开节点，子节点忽略，焦点
                zTree.selectNode(selectedNode);
            }else{
                alert(data.message);
            }
        });
    }

    //功能1：当前所在文件夹
    function loadDirPath(dir_id){
        $.ajax({
            type:'get',
            url:"/perm/dir-path?dir_id="+dir_id,
            async:false,
            success:function (data) {
                if(typeof(data)!="object") data=JSON.parse(data);
                if(data && data.success){
                    $("#dir_path_ol").html("");
                    $.each(data.data,function(i,item){
                        $("#dir_path_ol").append($("<li>").html(item.description));
                        $("#dir_id").val(item.id);
                    });
                }else{
                    alert(data.message);
                }
            }
        })
    }

    function loadFile(){
        $.get("/perm/query",$("#queryForm").serialize(),function(data){
            if(typeof(data)!="object") data=JSON.parse(data);
            if(data && data.success){
                $("#permTable tr:gt(0)").remove();
                $.each(data.data,function(i,item){
                    addItem(item);
                });
            }else{
                alert(data.message);
            }
        });
    }


    function addItem(perm) {
        var tr = $("<tr>").appendTo($("#permTable"))
            .append($("<td>").text(perm.permission))
            .append($("<td>").text(perm.description))
            .append($("<td>").text(perm.menu?"是":"否"));
        var op = $("<td>").appendTo(tr);
        if(perm.menu){
            op.append($("<i>").addClass("cus-add").prop("title","添加子节点").data("id",perm.id));
        }
        if(!perm.root){
            op.append($("<i>").addClass("cus-table-edit").prop("title","修改").data("id",perm.id));
            op.append($("<i>").addClass("cus-delete").prop("title","删除").data("id",perm.id).data("desc",perm.description));
        }
    }

    function init() {
        loadDirPath(0);//同步
        loadTree();
        loadFile();
    }

    $(init());

    //功能4：查询文件(表单查询)
    $("#searchSubmit").click(function(){
        $("#name").val($("#searchName").val());
        loadFile();
    });


    /**
     * 下面是按钮操作
     */
    $("#permTable").on("click",".cus-add",function () {
        var id=$(this).data("id");
        $("#myModalLabel").html("增加权限");
        $("#myModal").modal("show");
        var url = "/perm/"+id+"/addChild";
        $.get(url,function (data) {
            $("#modal-content").html(data);
            $("#editForm").prop("action",url);
        });
    });

    $("#permTable").on("click",".cus-table-edit",function () {
        var id=$(this).data("id");
        $("#myModalLabel").html("修改权限");
        $("#myModal").modal("show");
        var url="/perm/"+id+"/update";
        $.get(url,function (data) {
            $("#modal-content").html(data);
            $("#editForm").prop("action",url);
        })
    })

    $("#permTable").on("click",".cus-delete",function(){
        var id=$(this).data("id");
        var desc=$(this).data("desc");
        if(confirm("是否删除权限【"+desc+"】")){
            $.post("/perm/"+id+"/delete",function (data) {
                if(data && data.success){
                    loadTree();
                    loadFile();
                }else{
                    alert(data.msg);
                }
            });
        }
    });

    $("#permTable").on("click",".cus-lock-add",function () {
        var id=$(this).data("id");
        $.post("/role/"+id+"/lock",function (data) {
            if(data && data.success){
                loadTree();
                loadFile();
            }else{
                alert(data.msg);
            }
        });
    });

    $("#permTable").on("click",".cus-lock-delete",function () {
        var id=$(this).data("id");
        $.post("/role/"+id+"/unlock",function (data) {
            if(data && data.success){
                loadTree();
                loadFile();
            }else{
                alert(data.msg);
            }
        });
    });

    //ajax提交表单
    $("#editSubmit").click(function () {
        $("#editForm").ajaxSubmit({
            success:function(data) {
                if(data && data.success){
                    $("#myModal").modal("hide");
                    loadTree();
                    loadFile();
                }else{
                    alert(data.msg);
                }
            }
        });
    });
});