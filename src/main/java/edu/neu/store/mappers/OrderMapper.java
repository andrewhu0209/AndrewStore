package edu.neu.store.mappers;

import java.util.List;

import edu.neu.store.entity.Order;
import edu.neu.store.entity.OrderItem;
import edu.neu.store.vo.OrderVO;

/**
 * 訂單與訂單商品數據的持久層接口
 */
public interface OrderMapper {

	/**
	 * 插入訂單數據
	 * @param order 訂單數據
	 * @return 受影響的行數
	 */
	Integer insertOrder(Order order);

	/**
	 * 插入訂單商品數據
	 * @param orderItem 訂單商品數據
	 * @return 受影響的行數
	 */
	Integer insertOrderItem(OrderItem orderItem);
	
	/**
	 * 根據id查詢訂單詳情
	 * @param id 訂單id
	 * @return 匹配的訂單詳情，如果沒有匹配的數據，則返回null
	 */
	OrderVO findById(Integer id);
	
	/**
	 * 根據user id查詢訂單id
	 * @param uid user id
	 * @return 匹配的訂單id，如果沒有匹配的數據，則返回null
	 */
	List<Integer> findByUid(Integer uid);
}
