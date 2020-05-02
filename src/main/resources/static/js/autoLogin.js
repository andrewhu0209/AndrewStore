$(function() {
	//用戶點擊登錄按鈕時
	$("#btn-login").click(function() {

			//如果用戶沒有勾選自動登錄
			if (!$("#auto").prop("checked")) {
				//清空cookie中的內容
				$.cookie("isAutoLogin", "false", {
					expire: -1
				});
				$.cookie("username", "", {
					expires: -1
				});
				$.cookie("password", "", {
					expires: -1
				});
			} else {
				//如果用戶勾選了自動登錄
				//獲得用戶名密碼
				console.log("進入autologin");
				var vusername = $("#username").val();
				var vpassword = $("#password").val();
				//存入cookie
				//expires: 7 表示存儲一個帶7天期限的cookie
				$.cookie("isAutoLogin", "true", {
					expires: 7
				});
				$.cookie("username", vusername, {
					expires: 7
				});
				$.cookie("password", vpassword, {
					expires: 7
				});
			}
		})
		//頁面加載時運行的代碼
		//判斷是否有自動登錄的內容
	if ($.cookie("isAutoLogin") == "true") {
		//如果是自動登錄，將cookie中的信息自動填寫到用戶名和密碼框中
		$("#auto").prop("checked", true);
		$("#username").val($.cookie("username"));
		$("#password").val($.cookie("password"));
	}

});