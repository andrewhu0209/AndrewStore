package edu.neu.store.service;

import java.util.List;

import edu.neu.store.entity.Order;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.vo.OrderVO;

/**
 * 訂單與訂單商品的業務層接口
 */
public interface IOrderService {

	/**
	 * 創建訂單
	 * 
	 * @param uid       當前登錄的用戶的id
	 * @param username  當前登錄的用戶的用戶名
	 * @param addressId 所選擇的收貨地址的數據id
	 * @param cartIds   訂單中的商品在購物車中的數據id
	 * @return 成功創建的訂單
	 */
	Order createOrder(Integer uid, String username, Integer addressId, Integer[] cartIds) throws InsertException;

	/**
	 * 根據id查詢訂單詳情
	 * 
	 * @param id 訂單id
	 * @return 匹配的訂單詳情，如果沒有匹配的數據，則返回null
	 */
	OrderVO getById(Integer id);
	
	List<OrderVO> getByUId(Integer uid);
}
