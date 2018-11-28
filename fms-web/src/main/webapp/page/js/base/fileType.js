
$(document).ready(function(){

    $('#addBtn').click(function(){

        $('#addFileTypeName').textbox('clear');
        $("#addFileTypeDesc").val('');

        $('#fileTypeAddWindow').window('open');
    });

    $('#addCancelBtn').click(function(){
        $('#fileTypeAddWindow').window('close');
    });

    $('#addSubmitBtn').click(function(){

        var data = "&FileTypeName=" +  $("#addFileTypeName").val();
        data = data + "&FileTypeDesc=" +   $("#addFileTypeDesc").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/fileType/addFileType.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showType:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });

                $('#fileTypeList').datagrid('reload');
            },
            error: function(e) {
                $.messager.show({
                    msg:"新增文件类型出现异常，请联系系统管理员！",
                    showType:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
            }
        });

    });

    $('#editBtn').click(function(){
        var rows = $('#fileTypeList').datagrid('getChecked');
        if(rows.length > 1){
            alert("请只选择一条记录！");
        }
        else if(rows.length ==0){
            alert("请选择需要修改的记录！");
        }
        else{
            $('#editFileTypeID').val(rows[0].fileTypeID);
            $('#editFileTypeName').textbox('setValue',rows[0].fileTypeName);
            $('#editFileTypeDesc').val(rows[0].fileTypeDesc);
            $('#fileTypeEditWindow').window('open');
        }
    });

    $('#editCancelBtn').click(function(){
        $('#fileTypeEditWindow').window('close');
    });

    $('#editSubmitBtn').click(function(){

        var data = "&FileTypeID=" +  $("#editFileTypeID").val();
        data = data + "&FileTypeName=" +  $("#editFileTypeName").val();
        data = data + "&FileTypeDesc=" +  $("#editFileTypeDesc").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/fileType/editFileType.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showType:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                $('#fileTypeEditWindow').window('close');
                $('#fileTypeList').datagrid('reload');
                $('#fileTypeList').datagrid('unselectAll');
            },
            error: function(e) {
                $.messager.show({
                    msg:"修改文件类型出现异常，请联系系统管理员！",
                    showType:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                $('#fileTypeEditWindow').window('close');
            }
        });

    });

    $('#delBtn').click(function(){

        var rows = $('#fileTypeList').datagrid('getChecked');
        if(rows.length ==0){
            return false;
        }

        var fileTypeIDs = "";

        for(var i=0;i<rows.length;i++){
            fileTypeIDs = fileTypeIDs + rows[i].fileTypeID + ",";
        }

        fileTypeIDs = fileTypeIDs.substring(0,fileTypeIDs.length-1);

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选记录吗?',
            fn: function(r){
                if (r){
                    var data = "&FileTypeIDs="+ fileTypeIDs;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/fileType/delFileType.do",
                        dataType: 'json',
                        data:data,
                        success: function (result) {
                            $.messager.show({
                                msg:result.msg,
                                showType:'show',
                                timeout:1000,
                                style:{
                                    right:'',
                                    top:document.body.scrollTop+document.documentElement.scrollTop,
                                    bottom:''
                                }
                            });

                            $('#fileTypeList').datagrid('reload');
                            $('#fileTypeList').datagrid('uncheckAll');
                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除文件类型出现异常，请联系系统管理员！",
                                showType:'show',
                                timeout:1000,
                                style:{
                                    right:'',
                                    top:document.body.scrollTop+document.documentElement.scrollTop,
                                    bottom:''
                                }
                            });
                        }
                    });
                }
            }
        });

    });

});