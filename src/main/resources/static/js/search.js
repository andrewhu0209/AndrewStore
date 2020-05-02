
var productData;
var keyWord;

$(function() {
	console.log("Ready!!!")


	processSearch();
	showProduct();


	effect();



})




//獲取當前的url，進行分析要處理哪種收尋
//1.透過typeId 進行收尋
//2.透過關鍵字(key)進行收尋

function processSearch(){
	var url = location.search.substring(1);
	var search = url.split("&")[0];
	var name = search.split("=")[0];
	var value = search.split("=")[1];

	console.log("start search: name= "+name+"value: "+value);


	if(name == "typeid"){
		//透過typeID進行查詢
		console.log("start search");
		//獲取key word
		keyWord = decodeURI(url.split("&")[1].split("=")[1]);
		searchById(name,value);


	}else if(name == "key"){

		keyWord = decodeURI(value);
		//透過關鍵字進行查詢
		searchByKey(value);



	}else if(name == "hot"){
		keyWord = "Best Sellers";
		searchByHot();

	}else{
		//無此查詢
		//顯示查無結果
	}





}


function searchById(name,value) {
	console.log("In searchID function")
	var url = "../goods/list/"+value;
	//var data = value;

	$.ajax({
		"url": url,

		"type": "GET",
		"dataType": "json",
		"async": false,
		"success": function(json) {
			console.log("success get data");
			productData = json.data;

		},
		"error": function() {
			alert("您的登錄信息已經過期！請重新登錄！");
			location.href = "login.html";
		}
	});




}





function searchByKey(value){

	var url = "../goods/key/"+value;
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"async": false,
		"success": function(json) {
			console.log("success get data");
			productData = json.data;

		},
		"error": function() {
			alert("您的登錄信息已經過期！請重新登錄！");
			location.href = "login.html";
		}
	});


}


function searchByHot(){

	var url = "../goods/hot/all";
	$.ajax({
		"url": url,
		"type": "GET",
		"dataType": "json",
		"async": false,
		"success": function(json) {
			console.log("success get data");
			productData = json.data;

		},
		"error": function() {
			alert("您的登錄信息已經過期！請重新登錄！");
			location.href = "login.html";
		}
	});


}


//顯示在頁面上
function showProduct() {

	//設定標題
	$("#search-title").html(keyWord);


	$("#search-result-data").empty();
	var html = '';


	//沒有資料
	if(productData.length == 0){
		html += '<div class="col-md-offset-1 col-md-10"><div class="text-center"><h3>Sorry! No results for '+keyWord+'!</h3> </div></div>';
		$("#search-result-data").append(html);
		return;
	}



	for(var i=0; i<productData.length; i++){

		if((i+1)%4 == 1){
			html += '<div class="col-md-offset-1 col-md-10">';
		}


		html += '<div class="col-md-3">' +
			'            <div class="goods-panel">\n' +
			'                <div class="move-img img-search">\n' +
			'                    <img src="..#{image}collect.png" width="100%"/>\n' +
			'                </div>\n' +
			'                <h4>$#{price}</h4>\n' +
			'                <h5 class="text-row-3"><a href="product.html?id=#{id}"><small>#{title}</small></a></h5>' +
			'                <span>' +
			'<a class="btn btn-default btn-xs add-fav" href="javascript:void(0)">' + //加入收藏的連結，之後要完成
			'<span class="fa fa-heart-o"></span>加入收藏' +
			'</a>' +
			'<a class="btn btn-default btn-xs add-cart" href="javascript:addToCart(#{id},#{price});"><span class="fa fa-cart-arrow-down"></span>加入購物車</a>' +
			'</span>' +
			'\n' +
			'            </div>' +
			'        </div>';



		//取代
		html = html.replace(/#{id}/g, productData[i].id);
		html = html.replace(/#{image}/g, productData[i].image);
		html = html.replace(/#{price}/g, productData[i].price);
		html = html.replace(/#{title}/g, productData[i].title);



		if((i+1)%4 == 0){
			html += '</div>';
		}


	}

	if(productData.length%4 != 0){
		html += '</div>';
	}


	$("#search-result-data").append(html);






}



//加入購物車
function addToCart(id,g_price){

	var url = "../cart/add_to_cart";
	var gid = id;
	var price = g_price;
	var count = 1;
	var data = "gid=" + gid + "&price=" + price + "&count=" + count;
	console.log("[data]: " + data);
	$.ajax({
		"url": url,
		"data": data,
		"type": "POST",
		"dataType": "json",
		"success": function(json){
			if(json.state == 200){
				alert("添加成功! ");
			}else{
				alert("添加失敗！" + json.message);
			}

		},
		"error": function(){
			alert("您的登入信息已經過期! 請重新登入!");
			location.href = "login.html";
		},
	});


}





//動畫效果
function effect(){

	/*商品列表，鼠标移入时加阴影、移出移除阴影*/
	$(".goods-panel").hover(function() {
		$(this).css("box-shadow", "0px 0px 8px #888888");

	}, function() {
		$(this).css("box-shadow", "");
	})

	//加入收藏时，♥的实心空心切换
	$(".add-fav").toggle(function() {
		$(this).html("<span class='fa fa-heart'></span>取消收藏");
	}, function() {
		$(this).html("<span class='fa fa-heart-o'></span>加入收藏");
	})

}












