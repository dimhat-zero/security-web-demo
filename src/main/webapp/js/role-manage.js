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

    $(".cus-lock-add").click(function () {
        var id=$(this).data("id");
        $.post("/role/"+id+"/lock",ajaxSuccessReload);
    });

    $(".cus-lock-delete").click(function () {
        var id=$(this).data("id");
        $.post("/role/"+id+"/unlock",ajaxSuccessReload);
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