/**
 * Created by tangjie on 2017/3/7.
 */

$('document').ready(function(){


    $.extend($.fn.validatebox.defaults.rules, {
        equals: {
            validator: function(value,param){
                return value == $(param[0]).val();
            },
            message: '密码和确认密码不一致！'
        }
    });

    $('#clearBtn').click(function(){
        $('oldPassword').val('');
        $('newPassword').val('');
        $('newCnfmPassword').val('');
    });

    $('#submitBtn').click(function(){

        var userName = $('#userName').val();

        if(userName == null || userName== undefined || userName == ""){
            alertMessage("用户名不能为空，请确认！");
            return false;
        }

        var oldPassword = $('#oldPassword').val();

        if(oldPassword == null || oldPassword== undefined || oldPassword == ""){
            alertMessage("当前密码不能为空，请确认！");
            return false;
        }

        var newPassword = $('#newPassword').val();
        if(newPassword == null || newPassword== undefined || newPassword == ""){
            alertMessage("新密码不能为空，请确认！");
            return false;
        }

        var data = "&UserName=" +  userName;
        data = data + "&Password=" +  oldPassword;

        $.ajax({
            async: false,
            method: 'POST',
            url:getContextPath() + "/pwd/authenticate.do",
            dataType: 'json',
            data:data,
            success: function (result) {
                if(result.success == false) {
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
                }
                else{

                    data = "&UserName=" +  userName;
                    data = data + "&Password=" + newPassword;

                    $.ajax({
                        async: false,
                        method: 'POST',
                        url:getContextPath() + "/pwd/modifyPassword.do",
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
                        },
                        error: function(e) {
                            $.messager.show({
                                msg:"修改密码出现异常，请联系系统管理员！",
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
            },
            error: function(e) {
                $.messager.show({
                    msg:"用户验证出现异常，请联系系统管理员！",
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

});