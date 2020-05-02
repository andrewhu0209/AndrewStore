$(function() {

	/*商品小圖片加鼠標移入加邊框、移出移除邊框*/
	$(".img-small").hover(function() {
			$(this).css("border", "1px solid #4288c3");
		},
		function() {
			$(this).css("border", "");
		})
	//點擊時變化大圖片
	$(".img-small").click(function() {

			//獲得點擊的小圖片數據
			var n = $(this).attr("data");
			//所有大圖隱藏
			$(".img-big").hide();
			//顯示點擊的小圖對應的大圖
			$(".img-big[data='" + n + "']").show();

		})
		//購物數量加1
	$("#numUp").click(function() {
		var n = parseInt($("#num").val());
		$("#num").val(n + 1);
	})

	//購物數量-1
	$("#numDown").click(function() {
		var n = parseInt($("#num").val());
		if (n == 1) {
			return;
		}
		$("#num").val(n - 1);
	})


	$(".img-big:eq(0)").show();

})