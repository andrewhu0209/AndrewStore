package edu.neu.store.service;

import java.util.List;

import edu.neu.store.entity.Cart;
import edu.neu.store.service.exception.AccessDeniedException;
import edu.neu.store.service.exception.CartNotFoundException;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.service.exception.UpdateException;
import edu.neu.store.vo.CartVO;

/**
 * 購物車業務層接口
 */
public interface ICartService {

	/**
	 * 將商品添加到購物車
	 * 
	 * @param username 當前操作的執行人
	 * @param cart     購物車數據
	 * @throws InsertException
	 * @throws UpdateException
	 * @return cart ID
	 */
	Integer addToCart(String username, Cart cart) throws InsertException, UpdateException;

	/**
	 * 增加購物車中商品的數量
	 * 
	 * @param id  購物車數據的id
	 * @param uid 當前用戶的id
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void addCount(Integer id, Integer uid) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	/**
	 * 減少購物車中商品的數量
	 * 
	 * @param id  購物車數據的id
	 * @param uid 當前用戶的id
	 * @throws CartNotFoundException
	 * @throws AccessDeniedException
	 * @throws UpdateException
	 */
	void minusCount(Integer id, Integer uid) throws CartNotFoundException, AccessDeniedException, UpdateException;
	
	/**
	 * 根據用戶id查詢該用戶的購物車數據列表
	 * 
	 * @param uid 用戶尖
	 * @return 該用戶的購物車數據列表
	 */
	List<CartVO> getByUid(Integer uid);
	
	/**
	 * 根據若干個id查詢匹配的購物車數據的集合
	 * @param ids 若干個id
	 * @return 匹配的購物車數據的集合
	 */
	List<CartVO> getByIds(Integer[] ids);
	
	/**
	 * 根據id刪除其對應的數據
	 * @param id
	 */
	void removeById(Integer id, Integer uid);
	
	/**
	 * 根據id刪除其對應的數據
	 * @param id
	 */
	void removeByIds(Integer[] ids, Integer uid);

}
