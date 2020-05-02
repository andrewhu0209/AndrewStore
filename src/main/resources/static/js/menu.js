//修改這個變量為實際控制器的地址，如../showGoods.do

var reqpath = "search.html";
var typelist;

/*ajax獲得的json對象*/





//應該要去獲取所有的catagory 資訊
 function showTypeList(){
 	var url = "../category/list/all";
 	$.ajax({
 		"url": url,
 		"type": "GET",
 		"dataType": "json",
		"async": false,
 		"success": function(json){

 			typelist = json.data;




 		}
 	});
 }

//加載json數據的到一級分類的方法
function initMenu() {


	for (var i = 0; i < typelist.length; i++) {

		if (typelist[i].parentId == "0") {

			$(".index-menu").append($("<li data='" + typelist[i].id + "'>" + typelist[i].name + "</li>"))

		}

	}
}

$(function() {

	showTypeList();//獲取數據

	initMenu();


	//根據輪播圖片的高，導航的高
	//獲得輪播圖高
	var lunh = $("#myCarousel").height();

	var lih = (lunh - 10) / 19;

	//確定導航高度
	$(".index-menu li").css("height", lih + "px")

	//確定按鈕位置	

	var btnt = Math.floor($("#myCarousel").height() / 2 - 30);


	$(".left").css("margin-top", btnt + "px");
	$(".right").css("margin-top", btnt + "px");






	/*左側分類一級菜單控制二級菜單顯示和隱藏*/
	$(".index-menu").hover(function() {
			$("#showIndex").show();
		}, function() {
			$("#showIndex").hide();
		})
		/*左側分類二級菜單控制三級菜單顯示和隱藏*/
	$(".second-menu").hover(function() {
		$("#showSecond").show();
	}, function() {
		$("#showSecond").hide();
	})

	/*二級菜單自己控制顯示和隱藏*/
	$("#showIndex").hover(function() {

		$("#showIndex").show();
	}, function() {
		$("#showIndex").hide();
	})

	/*三級菜單自己控制顯示和隱藏*/
	$("#showSecond").hover(function() {
		$("#showIndex").show();
		$("#showSecond").show();
	}, function() {
		$("#showIndex").hide();
		$("#showSecond").hide();
	})


	/*一級分類項li鼠標移入移出*/
	var offTop = -100;
	var offLeft = 0;
	$(".index-menu li").hover(function() {

		$(".second-menu").empty();

		/*加載json數據*/
		for (var i = 0; i < typelist.length; i++) {

			if ($(this).attr("data") == typelist[i].parentId) {

				$(".second-menu").append($("<li class='second-menu-li' data='" + typelist[i].id + "' >" + typelist[i].name + "</li>"))
			}
		}

		offLeft = $(this).width() + $(this).offset().left;
		offTop = $(this).offset().top;
		$("#showIndex").css("top", offTop - 2 + "px")
		$("#showIndex").css("left", offLeft - 1 + "px")
		$(this).css("background-color", "#ffffff");
		$(this).css("color", "#4288c3");
		//$(this)[0].childNodes[0].style.color = "#4288c3" ; //將jq對象轉為js對象，取得小孩第一個元素，設定顏色
	
		

	}, function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
		//$(this)[0].childNodes[0].style.color = "" ;
		


	})



	/*二級分類項li鼠標移入移出*/
	$(document).on("mouseover",".second-menu-li", function() {

		$(".third-menu").empty();
		/*加載數據*/
		for (var i = 0; i < typelist.length; i++) {

			if ($(this).attr("data") == typelist[i].parentId) {

				$(".third-menu").append($("<li class='third-menu-li' data='" + typelist[i].id + "' ><a href='" + reqpath + "?typeid=" + typelist[i].id + "&name="+ typelist[i].name +"'>" + typelist[i].name + "</a></li>"))
			}
		}

		//alert($(document).scrollTop() +":"+$(this).offset().top)
		var ot = $(document).scrollTop() == $(this).offset().top ? offTop : $(this).offset().top;
		var ol = $(this).width() + $(this).offset().left;
		$("#showSecond").css("top", ot - 2 + "px");
		$("#showSecond").css("left", ol + "px")
		$(this).css("background-color", "#4288c3");
		$(this).css("color", "#ffffff");
		$(this)[0].childNodes[0].style.color = "#ffffff";
	})

	$(document).on("mouseout", ".second-menu-li", function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
		$(this)[0].childNodes[0].style.color = "";
	})



	/*三級分類項li鼠標移入移出*/
	$(document).on("mouseover",".third-menu-li", function() {

		$(this).css("background-color", "#eeeeee");
		$(this).css("color", "#000000");
	})
	$(document).on("mouseout",".third-menu-li", function() {
		$(this).css("background-color", "");
		$(this).css("color", "");
	})

})