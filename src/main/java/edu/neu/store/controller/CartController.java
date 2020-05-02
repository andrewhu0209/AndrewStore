package edu.neu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.store.entity.Cart;
import edu.neu.store.service.ICartService;
import edu.neu.store.util.ResponseResult;
import edu.neu.store.vo.CartVO;

/**
 * 購物車數據的控制器類
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {

	@Autowired
	private ICartService cartService;

	@PostMapping("/add_to_cart")
	public ResponseResult<Integer> addToCart(HttpSession session, Cart cart) {
		// 從session中獲取username
		String username = session.getAttribute("username").toString();
		// 從session中獲取uid
		Integer uid = getUidFromSession(session);
		// 將uid封裝到cart中
		cart.setUid(uid);
		// 執行業務方法
		Integer cartID = cartService.addToCart(username, cart);
		System.out.println("cart ID= "+cartID);
		// 返回
		return new ResponseResult<>(SUCCESS,cartID);
	}

	@GetMapping("/list")
	public ResponseResult<List<CartVO>> getByUid(HttpSession session) {
		// 從session中獲取uid
		Integer uid = getUidFromSession(session);
		// 執行查詢，獲取結果
		List<CartVO> list = cartService.getByUid(uid);
		// 返回
		return new ResponseResult<List<CartVO>>(SUCCESS, list);
	}

	@GetMapping("/add_count")
	public ResponseResult<Void> addCount(@RequestParam("id") Integer id, HttpSession session) {
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 執行業務
		cartService.addCount(id, uid);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@GetMapping("/minus_count")
	public ResponseResult<Void> minusCount(@RequestParam("id") Integer id, HttpSession session) {
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 執行業務
		cartService.minusCount(id, uid);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@GetMapping("/get_by_ids")
	public ResponseResult<List<CartVO>> getByIds(@RequestParam("cart_id") Integer[] ids) {
		// 執行查詢，獲取結果
		List<CartVO> list = cartService.getByIds(ids);
		// 返回
		return new ResponseResult<List<CartVO>>(SUCCESS, list);
	}

	@GetMapping("/delete")
	public ResponseResult<List<CartVO>> removeById(@RequestParam("id") Integer id, HttpSession session) {
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 執行業務
		cartService.removeById(id, uid);
		// 返回
		return new ResponseResult<>(SUCCESS);
	}

}
