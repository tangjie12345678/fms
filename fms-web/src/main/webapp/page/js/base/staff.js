/**
 * Created by tangjie on 2016/12/31.
 */


$(document).ready(function(){
    $('#searchBtn').click(function(){
        $('#staffList').datagrid('load',{
            staffID: $('#staffID').val(),
            staffName: $('#staffName').val(),
            sex: $('#sex').combobox('getValue')
        });
    });

    $('#addBtn').click(function(){
        $('#staffAdd').window('open');
    });

    $('#addCancelBtn').click(function(){
        $('#staffAdd').window('close');
    });

    $('#editCancelBtn').click(function(){
        $('#staffEdit').window('close');
    });

    $('#eDepartment').datalist({
        url: getContextPath() + "/department/getAllDepartment.do",
        checkbox: true,
        lines: true,
        valueField:"deptID",
        textField:"deptName",
        singleSelect:false
    });

    $('#editDepartment').datalist({
        url: getContextPath() + "/department/getAllDepartment.do",
        checkbox: true,
        lines: true,
        valueField:"deptID",
        textField:"deptName",
        singleSelect:false,
        selectOnCheck:true,
        checkOnSelect:true
    });

    $('#editBtn').click(function(){
        var rows = $('#staffList').datalist('getChecked');

        if(rows.length > 1){
            alert("请只选择一条记录！")
        }
        else if(rows.length ==0){
            alert("请选择需要修改的记录！")
        }
        else{
            var data = "&StaffRecordID="+ rows[0].staffRecordID;

            $.ajax({
                async: false,
                method: 'POST',
                url:getContextPath() + "/staff/getStaffByPrimaryKey.do",
                dataType: 'json',
                data:data,
                success: function (result) {
                    var staff = result.staff;


                    $('#editStaffID').textbox('setValue',staff.staffID);
                    $('#editStaffName').textbox('setValue',staff.staffName);
                    $('#editTelephone').textbox('setValue',staff.telephone);
                    $('#editMobilephone').textbox('setValue',staff.mobilephone);
                    $('#editAddress').textbox('setValue',staff.address);
                    $('#editNativePlace').textbox('setValue',staff.nativePlace);
                    $('#editCollege').textbox('setValue',staff.college);
                    $('#editBirthday').datebox('setValue', staff.birthday);
                    $('#editDegree').combobox('setValue',staff.degree);
                    $('#editSex').combobox('setValue',staff.sex);
                    $('#editDescription').val(staff.description);
                    $('#staffRecordID').val(staff.staffRecordID);

                    $('#editDepartment').datalist('uncheckAll');

                    var depts = result.dept;
                    var deptStr = "";

                    var rows = $("#editDepartment").datalist('getData').rows;
                    for (var i = 0; i < depts.length; i++) {

                        deptStr = deptStr + depts[i];
                        if(i != depts.length -1){
                            deptStr = deptStr + ",";
                        }

                        for (var j = 0; j < rows.length; j++) {
                            if (rows[j]['deptID'] == depts[i]) {
                                $('#editDepartment').datalist('checkRow',j);
                                break;
                            }
                        }
                    }

                    if(deptStr != ""){
                        $('#oldDepartment').val(deptStr);
                    }

                    $('#staffEdit').window('open');
                },
                error: function(e) {
                    $.messager.show({
                        msg:"获取人员信息出现异常，请联系统管理员！",
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

    $('#addSubmitBtn').click(function(){

        var rows = $('#eDepartment').datalist('getChecked');
        if(rows.length == 0){
            return false;
        }

        var depts = "";

        for(var i=0;i<rows.length;i++){
            depts = depts + rows[i].deptID;
            if(i != rows.length -1){
                depts = depts + ",";
            }
        }

        var data = "&StaffID="+ $("#eStaffID").val() + "&StaffName=" + $("#eStaffName").val();
        data = data + "&Birthday="+ $("#eBirthday").datebox("getValue") + "&Sex=" + $("#eSex").combobox('getValue');
        data = data + "&Telephone="+ $("#eTelephone").val() + "&Mobilephone=" + $("#eMobilephone").val();
        data = data + "&Address="+ $("#eAddress").val() + "&NativePlace=" + $("#eNativePlace").val();
        data = data + "&Degree="+ $("#eDegree").combobox('getValue') + "&College=" + $("#eCollege").val();
        data = data + "&Department="+ depts + "&Description=" + $("#eDescription").val();
        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/staff/addStaff.do",
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

            },
            error: function(e) {
                $.messager.show({
                    msg:"新增人员信息出现异常，请联系统管理员！",
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

    $('#editSubmitBtn').click(function(){

        var rows = $('#editDepartment').datalist('getChecked');
        if(rows.length == 0){
            return false;
        }

        var depts = "";

        for(var i=0;i<rows.length;i++){
            depts = depts + rows[i].deptID;
            if(i != rows.length -1){
                depts = depts + ",";
            }
        }

        var data = "&StaffRecordID="+ $("#staffRecordID").val();
        data = data + "&StaffID="+ $("#editStaffID").val() + "&StaffName=" + $("#editStaffName").val();
        data = data + "&Birthday="+ $("#editBirthday").datebox("getValue") + "&Sex=" + $("#editSex").combobox('getValue');
        data = data + "&Telephone="+ $("#editTelephone").val() + "&Mobilephone=" + $("#editMobilephone").val();
        data = data + "&Address="+ $("#editAddress").val() + "&NativePlace=" + $("#editNativePlace").val();
        data = data + "&Degree="+ $("#editDegree").combobox('getValue') + "&College=" + $("#editCollege").val();
        data = data + "&Department="+ depts + "&OldDepartment="+ $('#oldDepartment').val() + "&Description=" + $("#editDescription").val();

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/staff/editStaff.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showType:'show',
                    timeout:2000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+dococumentEleument.dment.scrollTop,
                        bottom:''
                    }
                });
                $('#staffEdit').window('close');
            },
            error: function(e) {
                $.messager.show({
                    msg:"修改人员信息出现异常，请联系统管理员！",
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
        var rows = $('#staffList').datagrid('getChecked');
        if(rows.length == 0){
            return false;
        }

        var staffs = "";

        for(var i=0;i<rows.length;i++){
            staffs = staffs + rows[i].staffRecordID;
            if(i != rows.length - 1){
                staffs = staffs + ",";
            }
        }

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选记录吗?',
            fn: function(r){
                if (r){
                    var data = "&StaffRecordIDs="+ staffs;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/staff/delStaff.do",
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

                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除人员信息出现异常，请联系统管理员！",
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