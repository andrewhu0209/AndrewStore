package edu.neu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.store.entity.Goods;
import edu.neu.store.mappers.GoodsMapper;
import edu.neu.store.service.IGoodsService;



/**
 * 商品數據的實現類
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public List<Goods> getByCategory(Long categoryId, Integer offset, Integer count) {
		return findByCategory(categoryId, offset, count);
	}
	
	
	
	@Override
	public List<Goods> getByKey(String key) {
		return findByKey(key);
	}



	@Override
	public Goods getById(Long id) {
		return findById(id);
	}

	@Override
	public List<Goods> getByPriority(Integer count) {
		return findByPriority(count);
	}
	
	/**
	 * 根據商品分類，查詢商品列表
	 * @param categoryId 商品分類的id
	 * @param offset 偏移量
	 * @param count 獲取的數據的最大數量
	 * @return 商品列表
	 */
	private List<Goods> findByCategory(Long categoryId,
		Integer offset, Integer count) {
		return goodsMapper.findByCategory(categoryId, offset, count);
	}
	
	private List<Goods> findByKey(String key){
		return goodsMapper.findByKey(key);
	}
	
	/**
	 * 根據id查詢商品詳情
	 * @param id 商品的id
	 * @return 商品詳情，如果沒有匹配的數據，則返回null
	 */
	private Goods findById(Long id) {
		return goodsMapper.findById(id);
	}
	
	/**
	 * 根據優先級獲取商品數據的列表
	 * @param count 獲取的商品的數量
	 * @return 優先級最高的幾個商品數據的列表
	 */
	private List<Goods> findByPriority(Integer count) {
		return goodsMapper.findByPriority(count);
	}


}
