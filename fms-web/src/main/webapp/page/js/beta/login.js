if(top!=self){
    if(top.location != self.location){
        top.location=self.location;
    }
}

if(!String.prototype.trim){
    String.prototype.trim = function(){
        return this.replace(/(^\s*)|(\s*$)/g, "");
    }
}

$(function(){
    $('#userName').val(getCookie('userName'));
});

function setCookie(c_name,value,expiredays){
    var exdate=new Date();
    exdate.setDate(exdate.getDate()+expiredays);
    document.cookie=c_name+ "=" +escape(value)+((expiredays==null) ? "" : ";expires="+exdate.toGMTString());
};

function getCookie(c_name){
    if (document.cookie.length>0){
        var c_start=document.cookie.indexOf(c_name + "=");
        if(c_start!=-1){
            c_start=c_start + c_name.length+1
            var c_end=document.cookie.indexOf(";",c_start)
            if (c_end==-1){c_end=document.cookie.length;}
            return unescape(document.cookie.substring(c_start,c_end))
        }
    }
    return ""
};

function addError(value){
    if($(".error").html()==null){
        $(".errorMsg").html("<label class='error' style='margin-left:23px;width:300px; align'>"+value+"</label>");
    }else{
        $(".errorMsg").html(value);
    }
}

function loginIn(){

	var userName =$("#userName").val();
	var password=$("#password").val();
    if(userName.trim()=="" || userName.trim()==null){
        addError("用户名不能为空！");
        return false;
    }

    if(password.trim()=="" || password.trim()==null){
        addError("密码不能为空！");
        return false;
    }    	

    //password = $.md5(password);
    var data = "&userName="+ userName + "&password=" + password;
   	var cookieData={userName:userName};
    $.ajax({
        async: false,
        method: 'POST',
        url:getContextPath() + "/login/loginIn.do",
        dataType: 'json',
        data:data,
        success: function (result) {
            if(result.success){
                setCookie('userName',cookieData.userName,30);
                window.location.href=getContextPath() + "/index.do";
            }else{
                addError(result.msg);
                alert("error");
            }
        },
        error: function(e) {
            addError("账户验证过程出现严重错误！");
        }
    });

}

