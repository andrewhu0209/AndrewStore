package edu.neu.store.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.neu.store.entity.Goods;



/**
 * 商口數據的持久層接口
 */
public interface GoodsMapper {
	
	/**
	 * 根據商品分類，查詢商品列表
	 * @param categoryId 商品分類的id
	 * @param offset 偏移量
	 * @param count 獲取的數據的最大數量
	 * @return 商品列表
	 */
	List<Goods> findByCategory(
		@Param("categoryId") Long categoryId,
		@Param("offset") Integer offset,
		@Param("count") Integer count);
	
	/**
	 * 
	 * 
	 * 根據關鍵字查詢商品的資訊
	 * @param key 關鍵字
	 * @return 商品列表
	 */
	List<Goods> findByKey(@Param("key") String key);

	/**
	 * 根據id查詢商品詳情
	 * @param id 商品的id
	 * @return 商品詳情，如果沒有匹配的數據，則返回null
	 */
	Goods findById(Long id);
	
	/**
	 * 根據優先級獲取商品數據的列表
	 * @param count 獲取的商品的數量
	 * @return 優先級最高的幾個商品數據的列表
	 */
	List<Goods> findByPriority(Integer count);
	
	
	List<Goods> findByTime(Integer count);
	
}





