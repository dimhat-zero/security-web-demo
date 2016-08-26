$(function () {

    $(".cus-table-edit").click(function () {
        var id=$(this).data("id");
        $("#myModalLabel").html("修改用户");
        $("#myModal").modal("show");
        var url="/user/"+id+"/update";
        $.get(url,function (data) {
            $("#modal-content").html(data);
            $("#editForm").prop("action",url);
        })
    })

    $(".cus-delete").click(function(){
        var id=$(this).data("id");
        var desc=$(this).data("desc");
        if(confirm("是否删除角色【"+desc+"】")){
            $.post("/user/"+id+"/delete",ajaxSuccessReload);
        }
    });

    $(".cus-lock-add").click(function () {
        var id=$(this).data("id");
        $.post("/user/"+id+"/lock",ajaxSuccessReload);
    });

    $(".cus-lock-delete").click(function () {
        var id=$(this).data("id");
        $.post("/user/"+id+"/unlock",ajaxSuccessReload);
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