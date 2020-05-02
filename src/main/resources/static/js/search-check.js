function check () {

    var key = $("#search").val();

    if(key == ''){
        alert("Please Enter Key Word!");
        return false;
    }else{
        return true;
    }

}