/**
 * Created by tangjie on 2017/2/22.
 */

$(document).ready(function(){

    $.extend($.fn.validatebox.defaults.rules, {
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '密码和确认密码不一致！'
        }
    });

    $('#searchBtn').click(function(){
        $('#userList').datagrid('load',{
            UserName: $('#userName').val(),
            StaffName: $('#staffName').val()
        });
    });

    $('#addBtn').click(function(){

        $('#addUserName').textbox('clear');
        $('#addPassword').val('');
        $('#addCnfmPassword').val('');
        $('#addStaff').combogrid('clear');
        $('#addRole').datalist('clearChecked');
        $("#addDescription").val('');

        $('#userAdd').window('open');
    });

    $('#addCancelBtn').click(function(){
        $('#userAdd').window('close');
    });

    $('#editCancelBtn').click(function(){
        $('#userEdit').window('close');
    });

    $('#addStaff').combogrid({keyHandler: {
            enter: function (){
                var sname = $('#addStaff').combogrid('getText');
                if(sname == ""){
                    return false;
                }
                var grid = $('#addStaff').combogrid('grid');
                grid.datagrid('load',{staffName : sname});
                $('#addStaff').combogrid('clear');
            }
        }
        }
    );

    $('#editStaff').combogrid({keyHandler: {
            enter: function (){
                var sname = $('#editStaff').combogrid('getText');
                if(sname == ""){
                    return false;
                }
                var grid = $('#editStaff').combogrid('grid');
                grid.datagrid('load',{staffName : sname});
                $('#editStaff').combogrid('clear');
            }
        }
        }
    );

    $('#addSubmitBtn').click(function(){

        var staffRecordID = $('#addStaff').combogrid('getValue');

        if(staffRecordID == null || staffRecordID == ""){
            alert("必须指定用户所对应的员工,请确认!");
            return false;
        }

        var rows = $('#addRole').datalist('getChecked');

        if(rows.length == 0){
            alert("必须指定用户所对应的角色,请确认!");
            return false;
        }

        var roles = "";

        for(var i=0;i<rows.length;i++){
            roles = roles + rows[i].roleID;
            roles = roles + ",";
        }

        roles = roles.substring(0,roles.length-1);

        var data = "&UserName=" +  $("#addUserName").val();
        data = data + "&Password=" +  $("#addPassword").val();
        data = data + "&StaffRecordID=" +  staffRecordID;
        data = data + "&Roles=" +  roles;
        data = data + "&Description=" +   $("#addDescription").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/user/addUser.do",
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
                $('#userList').datagrid('reload');
                $('#userList').datagrid('uncheckAll');
            },
            error: function(e) {
                $.messager.show({
                    msg:"新增用户信息出现异常，请联系系统管理员！",
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
        var rows = $('#userList').datagrid('getChecked');
        if(rows.length > 1){
            alert("请只选择一条记录！");
        }
        else if(rows.length ==0){
            alert("请选择需要修改的记录！");
        }
        else{

            var data = "&UserID="+ rows[0].userID;

            $.ajax({
                async: false,
                method: 'POST',
                url:getContextPath() + "/user/getUserInfoByPrimaryKey.do",
                dataType: 'json',
                data:data,
                success: function (result) {

                    var user = result.User;


                    $('#editUserName').textbox('setValue',user.userName);
                    $('#editDescription').val(user.description);
                    $('#editUserID').val(user.userID);
                    $('#editStaff').combogrid('setValue', user.staffRecordID);

                    var userRefRoles = result.UserRefRoles;

                    $('#editRole').datalist('uncheckAll');
                    var roleStr = "";

                    var rows = $("#editRole").datalist('getData').rows;
                    for (var i = 0; i < userRefRoles.length; i++) {

                        roleStr = roleStr + userRefRoles[i].roleID + ",";

                        for (var j = 0; j < rows.length; j++) {
                            if (rows[j]['roleID'] == userRefRoles[i].roleID) {
                                $('#editRole').datalist('checkRow',j);
                                break;
                            }
                        }
                    }

                    if(roleStr != ""){
                        $('#oldRoles').val(roleStr.substring(0,roleStr.length - 1));
                    }

                    $('#userEdit').window('open');
                },
                error: function(e) {
                    $.messager.show({
                        msg:"获取用户信息出现异常，请联系系统管理员！",
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

    $('#editSubmitBtn').click(function(){

        var staffRecordID = $('#editStaff').combogrid('getValue');

        if(staffRecordID == null || staffRecordID == ""){
            alert("必须指定用户所对应的员工,请确认!");
            return false;
        }

        var rows = $('#editRole').datalist('getChecked');

        if(rows.length == 0){
            alert("必须指定用户所对应的角色,请确认!");
            return false;
        }

        var roles = "";

        for(var i=0;i<rows.length;i++){
            roles = roles + rows[i].roleID;
            roles = roles + ",";
        }

        roles = roles.substring(0,roles.length-1);

        var data = "&UserID=" +  $("#editUserID").val();
        data = data + "&UserName=" +  $("#editUserName").val();
        data = data + "&OldRoles=" +  $("#oldRoles").val();
        data = data + "&NewRoles=" +  roles;
        data = data + "&Description=" +   $("#editDescription").val();
        data = data + "&StaffRecordID=" +  staffRecordID;

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/user/editUser.do",
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
                $('#userEdit').window('close');
                $('#userList').datagrid('reload');
                $('#userList').datagrid('unselectAll');
            },
            error: function(e) {
                $.messager.show({
                    msg:"修改用户信息出现异常，请联系系统管理员！",
                    showType:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                $('#userEdit').window('close');
            }
        });

    });

    $('#delBtn').click(function(){

        var rows = $('#userList').datagrid('getChecked');
        if(rows.length ==0){
            return false;
        }

        var userIDs = "";

        for(var i=0;i<rows.length;i++){
            userIDs = userIDs + rows[i].userID + ",";
        }

        userIDs = userIDs.substring(0,userIDs.length-1);

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选记录吗?',
            fn: function(r){
                if (r){
                    var data = "&UserIDs="+ userIDs;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/user/delUser.do",
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

                            $('#userList').datagrid('reload');
                            $('#userList').datagrid('uncheckAll');
                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除用户信息出现异常，请联系系统管理员！",
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