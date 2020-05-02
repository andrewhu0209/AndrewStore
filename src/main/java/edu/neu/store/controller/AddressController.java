package edu.neu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.store.entity.Address;
import edu.neu.store.service.IAddressService;
import edu.neu.store.util.ResponseResult;

/**
 * 處理收貨地址相關請求的控制器類
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Autowired
	private IAddressService addressService;

	@PostMapping("/create")
	public ResponseResult<Void> handleCreate(Address address, HttpSession session) {
		// 根據session獲取username
		String username = session.getAttribute("username").toString();

		// 根據session獲取uid
		Integer uid = getUidFromSession(session);
		// 將uid封裝到address中
		address.setUid(uid);

		// 調用業務層對象執行創建收貨地址
		addressService.create(username, address);

		// 返回
		return new ResponseResult<>(SUCCESS);
	}

	@RequestMapping("/list")
	public ResponseResult<List<Address>> getListByUid(HttpSession session) {
		// 獲取uid
		Integer uid = getUidFromSession(session);
		// 查詢數據
		List<Address> list = addressService.getListByUid(uid);
		// 返回
		return new ResponseResult<List<Address>>(SUCCESS, list);
	}
	
	@GetMapping("/default/{id}")
	public ResponseResult<Void> setDefault(HttpSession session, @PathVariable("id") Integer id){
		// 從session中獲取uid
		Integer uid = getUidFromSession(session);
	    // 調用業務層方法執行設置
		addressService.setDefault(uid, id);
	    // 返回
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@GetMapping("/delete/{id}")
	public ResponseResult<Void> deleteById(HttpSession session, @PathVariable("id") Integer id){
		// 從session中獲取uid
		Integer uid = getUidFromSession(session);
	    // 調用業務層方法執行設置
		addressService.delete(uid, id);
	    // 返回
		return new ResponseResult<Void>(SUCCESS);
	}

}
