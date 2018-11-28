/**
 * Created by tangjie on 2016/12/4.
 */
function getContextPath() {
    var pathName = document.location.pathname;
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}

function alertMessage(msg){
    alert(msg);
}