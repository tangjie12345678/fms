/**
 * Created by tangjie on 2016/12/31.
 */


$(document).ready(function(){

    var permissionMaps = '<%=session.getAttribute("permissionMaps")%>';

    alert(permissionMaps);


    $('#addBtn').click(function(){

        $('#addParentID').val("");
        $('#addParentName').textbox('setValue',"");

        var row = $('#deptList').treegrid('getChecked');

        if(row.length == 1){
            $('#addParentName').textbox('setValue',row[0].deptName);
            $('#addParentID').val(row[0].deptID);
        }

        $('#deptAdd').window('open');
    });

    $('#addCancelBtn').click(function(){
        $('#deptAdd').window('close');
    });


    $('#addSubmitBtn').click(function(){

        var data = "&ParentID="+ $("#addParentID").val();
        data = data + "&DeptName="+ $("#addDeptName").val();
        data = data + "&IsLeaf=" + $("#addIsLeaf").combobox('getValue');
        data = data + "&SortNo="+ $("#addSortNo").numberbox('getValue');
        data = data + "&Description=" + $("#addDescription").val();
        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/department/addDept.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showType:'show',
                    timeout:2000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });

                $('#deptList').treegrid('reload');

            },
            error: function(e) {
                $.messager.show({
                    msg:"新增部门信息出现异常，请联系统管理员！",
                    showType:'show',
                    timeout:2000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
            }
        });
    });


    $('#editCancelBtn').click(function(){
        $('#deptEdit').window('close');
    });

    $('#editBtn').click(function(){
        var rows = $('#deptList').treegrid('getChecked');

        if(rows.length ==0){
            alert("请选择需要修改的一条记录！");
        }
        else{
            var data = "&DeptID="+ rows[0].deptID;

            $.ajax({
                async: false,
                method: 'POST',
                url:getContextPath() + "/department/getDeptInfoWithParentDeptName.do",
                dataType: 'json',
                data:data,
                success: function (result) {
                    var dept = result.dept;
                    var parentDeptName = result.parentDeptName;

                    $('#editDeptID').val(dept.deptID);
                    $('#editParentID').val(dept.parentID);
                    $('#editParentName').textbox('setValue',parentDeptName);

                    $('#editDeptName').textbox('setValue',dept.deptName);

                    $('#editSortNo').textbox('setValue',dept.sortNo);

                    if(dept.isLeaf == true)
                    {
                        $('#editIsLeaf').combobox('setValue',1);
                    }
                    else
                    {
                        $('#editIsLeaf').combobox('setValue',0);
                    }


                    $('#editDescription').val(dept.description);

                    $('#deptEdit').window('open');
                },
                error: function(e) {
                    $.messager.show({
                        msg:"获取部门信息出现异常，请联系统管理员！",
                        showType:'show',
                        timeout:2000,
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


        var data = "&DeptID="+ $("#editDeptID").val();
        data = data + "&ParentID="+ $("#editParentID").val();
        data = data + "&DeptName="+ $("#editDeptName").val();
        data = data + "&IsLeaf=" + $("#editIsLeaf").combobox('getValue');
        data = data + "&SortNo="+ $("#editSortNo").numberbox('getValue');
        data = data + "&Description=" + $("#editDescription").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/department/editDepartment.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showType:'show',
                    timeout:2000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                $('#deptEdit').window('close');

                $('#deptList').treegrid('reload');
            },
            error: function(e) {
                $.messager.show({
                    msg:"修改部门信息出现异常，请联系统管理员！",
                    showType:'show',
                    timeout:2000,
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
        var rows = $('#deptList').treegrid('getChecked');
        if(rows.length == 0){
            return false;
        }

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选记录吗?',
            fn: function(r){
                if (r){
                    var data = "&DeptID="+ rows[0].deptID;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/department/delDepartment.do",
                        dataType: 'json',
                        data:data,
                        success: function (result) {
                            $.messager.show({
                                msg:result.msg,
                                showType:'show',
                                timeout:2000,
                                style:{
                                    right:'',
                                    top:document.body.scrollTop+document.documentElement.scrollTop,
                                    bottom:''
                                }
                            });

                            $('#deptList').treegrid('reload');
                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除部门信息出现异常，请联系统管理员！",
                                showType:'show',
                                timeout:2000,
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