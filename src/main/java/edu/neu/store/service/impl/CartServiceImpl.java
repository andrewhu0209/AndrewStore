package edu.neu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.store.entity.Cart;
import edu.neu.store.mappers.CartMapper;
import edu.neu.store.service.ICartService;
import edu.neu.store.service.exception.AccessDeniedException;
import edu.neu.store.service.exception.CartNotFoundException;
import edu.neu.store.service.exception.DeleteException;
import edu.neu.store.service.exception.InsertException;
import edu.neu.store.service.exception.UpdateException;
import edu.neu.store.vo.CartVO;

/**
 * 購物車業務層實現類
 */
@Service
public class CartServiceImpl implements ICartService {

	@Autowired
	private CartMapper cartMapper;

	@Override
	public Integer addToCart(String username, Cart cart) throws InsertException, UpdateException {
		// 獲取當前操作時間
		Date now = new Date();
		// 根據參數cart中的uid和gid查詢數據
		Integer uid = cart.getUid();
		Long goodsId = cart.getGid();
		Cart data = findByUidAndGid(uid, goodsId);
		// 判斷查詢結果是否為null
		if (data == null) {
			// 是：該用戶尚未在購物車中添加該商品，則執行新增
			cart.setCreatedUser(username);
			cart.setCreatedTime(now);
			cart.setModifiedUser(username);
			cart.setModifiedTime(now);
			addnew(cart);
		} else {
			// 否：該用戶已經在購物車中添加該商品，則取出此前查詢到的數據中的id和count
			cart.setId(data.getId());
			Integer dataId = data.getId();
			Integer oldCount = data.getCount();
			// 根據上一步取出的count與參數cart中的count（此次用戶提交的count），相加得到新的count
			Integer newCount = oldCount + cart.getCount();
			// 執行更新
			updateCount(dataId, newCount);
		}
		return cart.getId();
	}

	@Override
	public void addCount(Integer id, Integer uid) throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根據id查詢數據
		Cart data = findById(id);
		// 判斷數據是否為null
		if (data == null) {
			// 是：拋出異常：CartNotFoundException
			throw new CartNotFoundException("修改商品數量失敗！嘗試訪問的購物車數據不存在！");
		}

		// 判斷數據歸屬是否不匹配
		if (!data.getUid().equals(uid)) {
			// 是：拋出異常：AccessDeniedException
			throw new AccessDeniedException("修改商品數量失敗！訪問數據權限驗證不通過！");
		}

		// 獲取原來的數量
		Integer count = data.getCount();
		// 將數量+1
		count++;
		// 更新購物車數據中的數量:updateCount(id, count)
		updateCount(id, count);
	}

	@Override
	public void minusCount(Integer id, Integer uid)
			throws CartNotFoundException, AccessDeniedException, UpdateException {
		// 根據id查詢數據
		Cart data = findById(id);
		// 判斷數據是否為null
		if (data == null) {
			// 是：拋出異常：CartNotFoundException
			throw new CartNotFoundException("修改商品數量失敗！嘗試訪問的購物車數據不存在！");
		}

		// 判斷數據歸屬是否不匹配
		if (!data.getUid().equals(uid)) {
			// 是：拋出異常：AccessDeniedException
			throw new AccessDeniedException("修改商品數量失敗！訪問數據權限驗證不通過！");
		}

		// 獲取原來的數量
		Integer count = data.getCount();

		if (count == 0) {
			throw new AccessDeniedException("修改商品數量失敗！商品數量已經是0了");
		}

		// 將數量-1
		count--;
		// 更新購物車數據中的數量:updateCount(id, count)
		updateCount(id, count);
	}

	@Override
	public List<CartVO> getByUid(Integer uid) {
		return findByUid(uid);
	}

	@Override
	public List<CartVO> getByIds(Integer[] ids) {
		return findByIds(ids);
	}

	@Override
	public void removeById(Integer id, Integer uid) {
		// 根據id查詢數據
		Cart data = findById(id);
		// 判斷數據是否為null
		if (data == null) {
			// 是：拋出異常：CartNotFoundException
			throw new CartNotFoundException("刪除商品失敗！嘗試訪問的購物車數據不存在！");
		}

		// 判斷數據歸屬是否不匹配
		if (!data.getUid().equals(uid)) {
			// 是：拋出異常：AccessDeniedException
			throw new AccessDeniedException("刪除商品失敗！訪問數據權限驗證不通過！");
		}

		deleteById(id);

	}
	
	@Override
	@Transactional
	public void removeByIds(Integer[] ids, Integer uid) {
		for(int i=0; i<ids.length; i++) {
			removeById(ids[i], uid);
		}
	}
	
	

	/**
	 * 新增購物車數據
	 * 
	 * @param cart 購物車數據
	 */
	private void addnew(Cart cart) {
		Integer rows = cartMapper.addnew(cart);
		if (rows != 1) {
			throw new InsertException("創建購物車數據時發生未知錯誤！");
		}
	}

	/**
	 * 更新購物車中商品的數量
	 * 
	 * @param id    購物車數據的id
	 * @param count 新的數量
	 */
	private void updateCount(Integer id, Integer count) {
		Integer rows = cartMapper.updateCount(id, count);
		if (rows != 1) {
			throw new UpdateException("修改購物車商品數量時發生未知錯誤！");
		}
	}

	/**
	 * 根據用戶id和商品id查詢購物車數據
	 * 
	 * @param uid     用戶id
	 * @param goodsId 商品id
	 * @return 匹配的購物車數據，如果沒有匹配的數據，則返回null
	 */
	private Cart findByUidAndGid(Integer uid, Long goodsId) {
		return cartMapper.findByUidAndGid(uid, goodsId);
	}

	/**
	 * 根據id獲取購物車數據
	 * 
	 * @param id 購物車數據的id
	 * @return 匹配的購物車數據，如果沒有匹配的數據，則返回null
	 */
	private Cart findById(Integer id) {
		return cartMapper.findById(id);
	}

	/**
	 * 根據用戶id查詢該用戶的購物車數據列表
	 * 
	 * @param uid 用戶尖
	 * @return 該用戶的購物車數據列表
	 */
	private List<CartVO> findByUid(Integer uid) {
		return cartMapper.findByUid(uid);
	}

	/**
	 * 根據若干個id查詢匹配的購物車數據的集合
	 * 
	 * @param ids 若干個id
	 * @return 匹配的購物車數據的集合
	 */
	private List<CartVO> findByIds(Integer[] ids) {
		return cartMapper.findByIds(ids);
	}

	private void deleteById(Integer id) {
		Integer rows = cartMapper.deleteById(id);
		if (rows != 1) {
			throw new DeleteException("刪除購物車商品時發生未知錯誤！");
		}
	}

}
