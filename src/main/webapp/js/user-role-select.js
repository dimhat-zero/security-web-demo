$(function () {
    /**
     * 初始化设置
     * chkboxType: { "Y": "ps", "N": "ps" } 联动效应
     */
    var setting = {
        check: {
            enable: true ,
            chkStyle:"checkbox",
            chkboxType: { "Y": "", "N": "" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onCheck
        }
    };

    var zNodes =[];
    //ajax获取zNode节点
    $.get("/user/roleNode?id="+$("#id").val(),function (data) {
        if(data&&data.success){
            zNodes = data.data;
            $.fn.zTree.init($("#tree"), setting, zNodes);
        }else{
            alert(data.msg);
        }
    });

    //选中事件处理
    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("tree"),
            nodes = zTree.getCheckedNodes(true),
            id = "",
            name = "";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            id += nodes[i].id + ",";
            name += nodes[i].name + ",";
        }
        if (id.length > 0 ) id = id.substring(0, id.length-1);
        if (name.length > 0 ) name = name.substring(0, name.length-1);
        $("#roleIds").val(id);
        $("#roleNames").html(name);
    }

    function showMenu() {
        $("#menuContent").slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideMenu() {
        $("#menuContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
            hideMenu();
        }
    }

    $("#menuBtn").click(showMenu);
});