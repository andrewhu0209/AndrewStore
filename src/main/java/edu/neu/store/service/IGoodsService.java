package edu.neu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.neu.store.entity.Goods;

/**
 * 商品數據的業務層接口
 */
public interface IGoodsService {

	/**
	 * 根據商品分類，查詢商品列表
	 * 
	 * @param categoryId 商品分類的id
	 * @param offset     偏移量
	 * @param count      獲取的數據的最大數量
	 * @return 商品列表
	 */
	List<Goods> getByCategory(Long categoryId, Integer offset, @Param("count") Integer count);

	/**
	 * 依據關鍵字查詢商品列表
	 * @param key 關鍵字
	 * @return 商品列表
	 */
	List<Goods> getByKey(String key);
	/**
	 * 根據id查詢商品詳情
	 * 
	 * @param id 商品的id
	 * @return 商品詳情，如果沒有匹配的數據，則返回null
	 */
	Goods getById(Long id);

	/**
	 * 根據優先級獲取商品數據的列表
	 * 
	 * @param count 獲取的商品的數量
	 * @return 優先級最高的幾個商品數據的列表
	 */
	List<Goods> getByPriority(Integer count);
}
