/**
 * Created by tangjie on 2016/12/31.
 */




$(document).ready(function(){

    $('#searchBtn').click(function(){
        $('#roleList').datagrid('load',{
            Role: $('#roleName').val()
        });
    });

    $('#addBtn').click(function(){

        $('#addMenuPermission').tree({
            url:getContextPath() + "/permission/getPermissionList.do",
            checkbox:true,
            lines:true
        });

        $('#roleAdd').window('open');
    });

    $('#editBtn').click(function(){
        var rows = $('#roleList').datagrid('getChecked');
        if(rows.length > 1){
            alert("请只选择一条记录！")
        }
        else if(rows.length ==0){
            alert("请选择需要修改的记录！")
        }
        else{

            $('#editMenuPermission').tree({
                url:getContextPath() + "/permission/getPermissionList.do",
                checkbox:true,
                lines:true,
                cascadeCheck:false
            });

            var data = "&RoleID="+ rows[0].roleID;

            $.ajax({
                async: false,
                method: 'POST',
                url:getContextPath() + "/role/getRolePermissionByPrimaryKey.do",
                dataType: 'json',
                data:data,
                success: function (result) {

                    var role = result.Role;


                    $('#editRole').textbox('setValue',role.role);
                    $('#editDescription').val(role.description);
                    $('#editRoleID').val(role.roleID);

                    var permissions = result.permissions;

                    var pmStr = "";

                    for(var i=0;i<permissions.length;i++)
                    {
                        pmStr = pmStr + permissions[i].permissionID;
                        pmStr = pmStr + ",";

                        var permissionID = permissions[i].permissionID;
                        var node = $('#editMenuPermission').tree('find',permissionID);
                        if(null != node && undefined != node)
                        {
                            $('#editMenuPermission').tree('check',node.target);
                        }
                    }

                    pmStr = pmStr.substring(0,pmStr.length-1);

                    $('#oldPermissions').val(pmStr);

                    $('#editMenuPermission').tree('options').cascadeCheck = "true";
                    $('#roleEdit').window('open');
                },
                error: function(e) {
                    $.messager.show({
                        msg:"获取权限信息出现异常，请联系统管理员！",
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



    });

    $('#addCancelBtn').click(function(){
        $('#roleAdd').window('close');
    });

    $('#editCancelBtn').click(function(){
        $('#roleEdit').window('close');
    });


    $('#addSubmitBtn').click(function(){

        var permissions = "";

        var rows = $('#addMenuPermission').tree('getChecked',['checked','indeterminate']);
        if(rows.length == 0){
            alert("请设定角色的权限!");
            return false;
        }

        for(var i=0;i<rows.length;i++){
            if(rows[i].id != null && rows[i].id !="")
            {
                permissions = permissions + rows[i].id;
                permissions = permissions + ",";
            }
        }

        permissions = permissions.substring(0,permissions.length-1);

        var data = "&Role=" +  $("#addRole").val();
        data = data + "&Permissions=" + permissions;
        data = data + "&Description=" + $("#addDescription").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/role/addRole.do",
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
                $('#roleList').datagrid('reload');
            },
            error: function(e) {
                $.messager.show({
                    msg:"新增角色信息出现异常，请联系统管理员！",
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


    $('#editSubmitBtn').click(function(){

        var permissions = "";

        var rows = $('#editMenuPermission').tree('getChecked',['checked','indeterminate']);
        if(rows.length == 0){
            alert("请设定角色的权限!");
            return false;
        }

        for(var i=0;i<rows.length;i++){
            if(rows[i].id != null && rows[i].id !="")
            {
                permissions = permissions + rows[i].id;
                permissions = permissions + ",";
            }
        }

        permissions = permissions.substring(0,permissions.length-1)

        var data = "&RoleID=" +  $("#editRoleID").val();
        data = data + "&Role=" +  $("#editRole").val();
        data = data + "&OldPermissions=" + $("#oldPermissions").val();
        data = data + "&NewPermissions=" + permissions;
        data = data + "&Description=" + $("#editDescription").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/role/editRole.do",
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
                $('#roleList').datagrid('reload');
            },
            error: function(e) {
                $.messager.show({
                    msg:"修改角色信息出现异常，请联系统管理员！",
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


    $('#delBtn').click(function(){
        var rows = $('#roleList').datagrid('getChecked');
        if(rows.length == 0){
            return false;
        }

        var roles = "";

        for(var i=0;i<rows.length;i++){
            roles = roles + rows[i].roleID;
            roles = roles + ",";
        }

        roles = roles.substring(0,roles.length-1);

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选记录吗?',
            fn: function(r){
                if (r){
                    var data = "&RoleIDs="+ roles;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/role/delRole.do",
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

                            $('#roleList').datagrid('reload');

                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除角色信息出现异常，请联系统管理员！",
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