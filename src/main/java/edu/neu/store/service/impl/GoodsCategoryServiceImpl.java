package edu.neu.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.neu.store.entity.GoodsCategory;
import edu.neu.store.mappers.GoodsCategoryMapper;
import edu.neu.store.service.IGoodsCategoryService;

@Service
public class GoodsCategoryServiceImpl implements IGoodsCategoryService {

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Override
	public List<GoodsCategory> getByParent(Long parentId) {
		return findByParent(parentId);
	}
	
	

	@Override
	public List<GoodsCategory> getAllList() {
		return findAll();
	}



	/**
	 * 根據父級id獲取子級的商品分類的數據的列表
	 * 
	 * @param parentId 父級商品分類的id
	 * @return 子級的商品分類的數據的列表
	 */
	private List<GoodsCategory> findByParent(Long parentId) {
		return goodsCategoryMapper.findByParent(parentId);
	}
	
	private List<GoodsCategory> findAll(){
		return goodsCategoryMapper.findAll();
	}

}
