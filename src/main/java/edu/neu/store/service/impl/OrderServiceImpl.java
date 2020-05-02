package edu.neu.store.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.store.entity.Address;
import edu.neu.store.entity.Order;
import edu.neu.store.entity.OrderItem;
import edu.neu.store.mappers.OrderMapper;
import edu.neu.store.service.IAddressService;
import edu.neu.store.service.ICartService;
import edu.neu.store.service.IOrderService;
import edu.neu.store.service.exception.AddressNotFoundException;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.vo.CartVO;
import edu.neu.store.vo.OrderVO;

/**
 * 訂單與訂單商品的業務層實現類
 */
@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;
	@Autowired
	private IOrderService orderService;

	@Override
	@Transactional
	public Order createOrder(Integer uid, String username, Integer addressId, Integer[] cartIds)
			throws InsertException {
		// 創建Date對象
		Date now = new Date();

		// 聲明pay變量
		Long pay = 0L;
		// List<CartVO> cartService.getByIds(ids)
		List<CartVO> carts = cartService.getByIds(cartIds);
		// 創建List<OrderItem> orderItems
		List<OrderItem> orderItems = new ArrayList<>();
		// 遍歷集合
		for (CartVO cartVO : carts) {
			// 計算總價pay
			pay += cartVO.getNewPrice() * cartVO.getCount();
			// 創建OrderItem
			OrderItem item = new OrderItem();
			// item屬性：goods_5，OK
			item.setGoodsId(cartVO.getGid());
			item.setGoodsTitle(cartVO.getTitle());
			item.setGoodsImage(cartVO.getImage());
			item.setGoodsPrice(cartVO.getNewPrice());
			item.setGoodsCount(cartVO.getCount());
			// item屬性：4個日誌，OK
			item.setCreatedUser(username);
			item.setCreatedTime(now);
			item.setModifiedUser(username);
			item.setModifiedTime(now);
			// 將item添加到集合中
			orderItems.add(item);
		}

		// 創建Order對象
		Order order = new Order();
		// order屬性：uid，OK
		order.setUid(uid);
		// order屬性：pay，OK
		order.setPay(pay);

		// 通過addressService.getById()得到收貨地址數據
		Address address = addressService.getById(addressId);
		// 判斷是否查詢到address數據
		if (address == null) {
			throw new AddressNotFoundException("創建訂單失敗！收貨地址數據有誤，請刷新後再次嘗試！");
		}

		// order屬性：recv_4，OK
		order.setRecvName(address.getName());
		order.setRecvPhone(address.getPhone());
		order.setRecvDistrict(address.getDistrict());
		order.setRecvAddress(address.getAddress());

		// order屬性：order_time，OK
		order.setOrderTime(now);
		// order屬性：status，OK，值為0
		order.setStatus(0);

		// order屬性：4個日誌，OK
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);

		// 插入訂單數據並獲取oid：insertOrder(order)
		insertOrder(order);

		// 遍歷orderItems
		for (OrderItem orderItem : orderItems) {
			// item屬性：oid
			orderItem.setOid(order.getId());
			// 插入訂單商品數據
			insertOrderItem(orderItem);
		}

		// 刪除購物車中對應的數據
		cartService.removeByIds(cartIds, uid);

		// 返回
		return order;
	}

	@Override
	public OrderVO getById(Integer id) {
		return findById(id);
	}
	
	

	@Override
	@Transactional
	public List<OrderVO> getByUId(Integer uid) {
		
		List<Integer> orderIds = findByUid(uid);
		List<OrderVO> order = new ArrayList<OrderVO>();
		for(int i=0; i<orderIds.size(); i++) {
			order.add(orderService.getById(orderIds.get(i)));	
		}
	
		return order;
	}

	/**
	 * 插入訂單數據
	 * 
	 * @param order 訂單數據
	 */
	private void insertOrder(Order order) {
		Integer rows = orderMapper.insertOrder(order);
		if (rows != 1) {
			throw new InsertException("插入訂單數據時發生未知錯誤！");
		}
	}

	/**
	 * 插入訂單商品數據
	 * 
	 * @param orderItem 訂單商品數據
	 * @return 受影響的行數
	 */
	private void insertOrderItem(OrderItem insertOrderItem) {
		Integer rows = orderMapper.insertOrderItem(insertOrderItem);
		if (rows != 1) {
			throw new InsertException("插入訂單商品數據時發生未知錯誤！");
		}
	}

	/**
	 * 根據id查詢訂單詳情
	 * 
	 * @param id 訂單id
	 * @return 匹配的訂單詳情，如果沒有匹配的數據，則返回null
	 */
	private OrderVO findById(Integer id) {
		return orderMapper.findById(id);
	}
	
	private List<Integer> findByUid(Integer uid){
		return orderMapper.findByUid(uid);
		
	}

}
