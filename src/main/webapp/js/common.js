function ajaxSuccessReload(data) {
    if(data && data.success){
        location.reload();
    }else{
        alert(data.msg);
    }
}