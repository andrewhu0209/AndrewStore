$(function() {

    checkLoginStatus()
    
})


function  checkLoginStatus() {


    var url = "../user/checkLogin.do";
  
    $.ajax({
        "url" : url,
        "type" : "GET",
        "dataType" : "json",
        "success" : function(json) {
            if (json.state == 200) {
                //已經登入
               
                var username = json.data;
                var htm = '<a href="#" onclick="logout();"><span class="fa fa-user"></span>&nbsp;Logout</a>';
                $("#li-login-logout").html(htm);
                $("#welcome-title").html('Welcome '+username+'!');
                console.log("Over!")


            } else {
                //Do nothing
            }

        }
    });

}

function  logout() {

    var url = "../user/logout.do";

    $.ajax({
        "url" : url,
        "type" : "GET",
        "dataType" : "json",
        "success" : function(json) {
            if (json.state == 200) {
                location.href = "index.html";
            } else {

            }

        }
    });


}