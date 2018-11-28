/**
 * Created by tangjie on 2017/4/23.
 */

$(document).ready(function(){

    loadDeptStaffInfo();

    $('#searchBtn').click(function(){
        $('#fileList').datagrid('load',{
            fileName : $('#fileName').val(),
            fileType : $('#fileType').combobox('getValue'),
            folderName : $('#folderName').val(),
            creator : $('#creator').val(),
            updator : $('#updator').val()
        });
    });

    $("input:radio[name='editShareType']").change(function(){
        var shareType = $("input:radio[name='editShareType']:checked").val();
        if(shareType == '0303'){
            $("#editShareSetting").css("visibility","visible");
        }else{
            $("#editShareSetting").css("visibility","hidden");
        }
    });

    $('#fileList').datagrid({
        onDblClickRow:function(index,row){
            var fileUrl = row.filePath+ "/" + row.fileName + "." + row.fileExtType;
            $('#fileUrl').val(fileUrl);
            $('#fileOpenForm').form({
                url:getContextPath() + "/filemanage/fileOpen.do"
            });

            $('#fileOpenForm').submit();
        }
    });

    $('#fileDownload').click(function(){
        var rows = $('#fileList').datagrid('getChecked');
        if(rows.length ==0){
            alert("请选择需要下载的文件！")
        }
        else{
            var fileUrls = "";
            for(var i=0;i<rows.length;i++){
                fileUrls =fileUrls + rows[i].filePath+ "/" + rows[i].fileName + "." + rows[i].fileExtType + ",";
            }

            fileUrls = fileUrls.substring(0,fileUrls.length-1);

            $('#fileUrls').val(fileUrls);
            $('#fileDownForm').form({
                url:getContextPath() + "/filemanage/fileDownload.do"
            });

            $('#fileDownForm').submit();
        }
    });

    $('#fileDelete').click(function () {
        var rows = $('#fileList').datagrid('getChecked');
        if(rows.length == 0){
            return false;
        }

        var fileIDs = "";

        for(var i=0;i<rows.length;i++){
            fileIDs = fileIDs + rows[i].fileID + ",";
        }

        fileIDs = fileIDs.substring(0,fileIDs.length-1);

        $.messager.confirm({
            title: '确认',
            msg: '确认删除所选文件吗?',
            fn: function(r){
                if (r){
                    var data = "&FileIDs="+ fileIDs;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/filemanage/fileDelete.do",
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

                            $('#fileList').datagrid('reload');
                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"删除文件信息出现异常，请联系统管理员！",
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

    $('#fileReplace').click(function () {

        var rows = $('#fileList').datagrid('getChecked');

        if(rows.length > 1){
            alert("请只选择一条文件记录！")
        }
        else if(rows.length ==0){
            alert("请选择需要替换的文件记录！")
        }
        else{

            $("#replaceFileID").val(rows[0].fileID);
            $("#replacedFileName").html(rows[0].fileName);
            $("#replacedFileType").html(rows[0].fileTypeName);
            $("#replacedFileCreator").html(rows[0].creatorName);
            $("#replaceFile").filebox('clear');
            $('#replaceFileWindow').window('open');
        }
    });

    $('#fileReplaceForm').form({
        url:getContextPath() + "/filemanage/fileReplace.do",
        onSubmit: function(){
            var fileNameStr = $("#replaceFile").filebox('getText');
            if(fileNameStr.length == 0){
                alert("请为选中的文件选择替换文件！")
                return false;
            }else{
                var fileNames = fileNameStr.split(",");
                if(fileNames.length > 1){
                    alert("只能上传一个文件用以替换被选中文件！");
                    return false;
                }
            }
            $('#replaceFileWindow').window('close');
        },
        success:function(data){
            var result = JSON.parse(data);
            alert(result.msg);
            $('#fileList').datagrid('reload');
        },
        error:function(data){
            var result = JSON.parse(data);
            alert(result.msg);
        }
    });

    $('#replaceSubmitBtn').click(function(){
        $('#fileReplaceForm').submit();
    });



    $('#propertySet').click(function(){

        var rows = $('#fileList').datagrid('getChecked');

        if(rows.length > 1){
            alert("请只选择一条记录！")
        }
        else if(rows.length ==0){
            alert("请选择需要修改的记录！")
        }
        else{

            clearDeptStaffInfo();

            $("#editShareSetting").css("visibility","hidden");

            $("#editFileID").val(rows[0].fileID);

            var data = "&FileID="+ rows[0].fileID;

            $.ajax({
                async: false,
                method: 'POST',
                url:getContextPath() + "/filemanage/getFileProperties.do",
                dataType: 'json',
                data:data,
                success: function (result) {

                    var vFile = result.file;

                    $("input:radio[name='editShareType'][value='" + vFile.shareType + "']").prop("checked","checked");

                    if(vFile.shareType == '0303'){

                        var vStaffs = result.staffs;
                        var strStaff = "";
                        for(var i = 0;i<vStaffs.length;i++){
                            strStaff = strStaff + vStaffs[i].deptID + "_" + vStaffs[i].staffID + ",";

                            var staffInfo =vStaffs[i].deptID + "_" + vStaffs[i].staffID;

                            var node = $('#editDeptStaff').tree('find',staffInfo);
                            if(null != node && undefined != node)
                            {
                                $('#editDeptStaff').tree('check',node.target);
                            }
                        }

                        strStaff = strStaff.substring(0,strStaff.length-1);

                        $("#editOldSharedStaffs").val(strStaff);

                        $("#editShareSetting").css("visibility","visible");
                    }


                    $("input:checkbox[name='editOpType']:checked").attr('checked',false);

                    var vFileOperateRight = result.opRights;

                    var opRightStr = "";

                    for(var i=0;i<vFileOperateRight.length;i++)
                    {
                        opRightStr = opRightStr + vFileOperateRight[i].opRight + ","

                        $("input:checkbox[name='editOpType'][value='" + vFileOperateRight[i].opRight + "']").prop("checked","checked");
                    }

                    opRightStr = opRightStr.substring(0,opRightStr.length-1);

                    $("#oldOpRights").val(opRightStr);

                    $('#editFileProperty').window('open');
                },
                error: function(e) {
                    $.messager.show({
                        msg:"获取文件属性出现异常，请联系统管理员！",
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


    $('#editCancelBtn').click(function(){
        $('#editFileProperty').window('close');
    });

    $('#editSubmitBtn').click(function(){

        $("#editNewSharedStaffs").val('');

        var optypes = "";

        $("input:checkbox[name='editOpType']:checked").each(function(){
            optypes = optypes + $(this).val() + ",";
        });

        if(optypes == ""){
            alert("请选择文件可操作的类型！");
            return false;
        }else{
            optypes = optypes.substring(0,optypes.length-1);
        }

        var sharetype = $("input:radio[name='editShareType']:checked").val();

        if(sharetype == '0303'){
            var rows = $('#editDeptStaff').tree('getChecked');
            if(rows.length == 0){
                alert("请自定义文件共享的范围！");
                return false;
            }

            var staffs = "";


            for(var i=0;i<rows.length;i++){
                if(null != rows[i].id && rows[i].id !="" && null != rows[i].attributes && rows[i].attributes == "staff")
                {
                    staffs = staffs + rows[i].id;
                    staffs = staffs + ",";
                }
            }

            staffs = staffs.substring(0,staffs.length-1);
            $("#editNewSharedStaffs").val(staffs);
        }

        var data = "&fileID=" +  $("#editFileID").val();
        data = data + "&shareType=" +  sharetype;
        data = data + "&oldOpRights=" +  $("#oldOpRights").val();
        data = data + "&newOpRights=" +  optypes;
        data = data + "&oldSharedStaffs=" + $("#editOldSharedStaffs").val();
        data = data + "&newSharedStaffs=" + $("#editNewSharedStaffs").val();

        $.ajax({
            async: false,
            method: 'post',
            url:getContextPath() + "/filemanage/resetProperty.do",
            datatype: 'json',
            data:data,
            success: function (result) {
                $.messager.show({
                    msg:result.msg,
                    showtype:'show',
                    timeout:1000,
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    }
                });
                $('#editFileProperty').window('close');
                $('#fileList').datagrid('reload');

            },
            error: function(e) {
                $.messager.show({
                    msg:"重设文件属性失败",
                    showtype:'show',
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


});

function loadDeptStaffInfo(){

    $('#editDeptStaff').tree({
        url:getContextPath() + "/staff/getDeptStaff.do",
        checkbox:true,
        lines:true
    })
}

function clearDeptStaffInfo(){
    var rows = $('#editDeptStaff').tree('getChecked');
    if(undefined != rows && rows.length > 0){
        for(var i=0;i<rows.length;i++){
            var id =rows[i].id;
            if(null !=id && id !=""){
                var node = $('#editDeptStaff').tree('find',id);
                if(null != node && undefined != node)
                {
                    $('#editDeptStaff').tree('uncheck',node.target);
                }
            }
        }
    }
}