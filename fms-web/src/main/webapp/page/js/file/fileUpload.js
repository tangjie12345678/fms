/**
 * Created by tangjie on 2017/3/12.
 */


$(document).ready(function(){

    //loadDeptStaffInfo();


    $('#fileUploadForm').form({
        url:getContextPath() + "/fileupload/upload.do",
        onSubmit: function(param){

            $("#sharedStaffs").val('');

            var opType = $("input:checkbox[name='opType']:checked").val();

            if(opType == ""){
                alert("请选择文件可操作的类型！");
                return false;
            }

            var fileType = $("#fileType").combobox('getValue');

            if(fileType == ""){
                alert("请选择文件的类型！");
                return false;
            }

            var shareType = $("input:radio[name='shareType']:checked").val();

            if(shareType == '0303'){
                var rows = $('#deptStaff').tree('getChecked');
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
                $("#sharedStaffs").val(staffs);
            }

            var files = $("#files").filebox('getText');

            if(files.length == 0){
                alert("请选择需要上传的文件！");
                return false;
            }
        },
        success:function(data){
            var result = JSON.parse(data);
            alert(result.msg);
        },
        error:function(data){
            var result = JSON.parse(data);
            alert(result.msg);
        }
    });


    $("input:radio[name='shareType']").change(function(){
        var shareType = $("input:radio[name='shareType']:checked").val();
        if(shareType == '0303'){

            if($('#deptStaff').children().length == 0 )
            {
                loadDeptStaffInfo();
            }

            $("#shareSetting").css("visibility","visible");
        }else{
            $("#shareSetting").css("visibility","hidden");
        }
    });

    $("#submitBtn").click(function(){
        $('#fileUploadForm').submit();
    });

});

function loadDeptStaffInfo(){

    $('#deptStaff').tree({
        url:getContextPath() + "/staff/getDeptStaff.do",
        checkbox:true,
        lines:true
    })
}





