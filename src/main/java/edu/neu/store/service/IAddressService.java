package edu.neu.store.service;

import java.util.List;

import edu.neu.store.entity.Address;
import edu.neu.store.service.exception.DeleteException;
import edu.neu.store.service.exception.InsertException;

/**
 * 收貨地址的業務層接口
 */
public interface IAddressService {

	/**
	 * 創建新收貨地址
	 * 
	 * @param username 當前執行人
	 * @param address  收貨地址信息
	 * @return 受影響的行數
	 * @throws InsertException
	 */
	Address create(String username, Address address) throws InsertException;

	/**
	 * 設置默認收貨地址
	 * 
	 * @param uid 收貨地址歸屬的用戶的id
	 * @param id  將要設置為默認收貨地址的數據的id
	 */
	void setDefault(Integer uid, Integer id);

	/**
	 * 獲取某用戶的收貨地址列表
	 * 
	 * @param uid 用戶id
	 * @return 收貨地址列表
	 */
	List<Address> getListByUid(Integer uid);
	
	/**
	 * 根據id查詢收貨地址數據
	 * @param id 收貨地址id
	 * @return 匹配的收貨地址數據，如果沒有匹配的數據，則返回null
	 */
	Address getById(Integer id);

	/**
	 * 根據id刪除收貨地址
	 * 
	 * @param uid 收貨地址歸屬的用戶的id
	 * @param id  收貨地址數據的id
	 * @throws DeleteException
	 */
	void delete(Integer uid, Integer id) throws DeleteException;

}
