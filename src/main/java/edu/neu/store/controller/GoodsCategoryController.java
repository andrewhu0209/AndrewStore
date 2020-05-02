package edu.neu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.store.entity.GoodsCategory;
import edu.neu.store.service.IGoodsCategoryService;
import edu.neu.store.util.ResponseResult;

@RestController
@RequestMapping("/category")
public class GoodsCategoryController extends BaseController {

	@Autowired
	private IGoodsCategoryService categoryService;

	@GetMapping("/list/{parent}")
	public ResponseResult<List<GoodsCategory>> getByParent(@PathVariable("parent") Long parent) {
		
		List<GoodsCategory> list = categoryService.getByParent(parent);
		return new ResponseResult<List<GoodsCategory>>(SUCCESS, list);
	}
	
	
	
	@GetMapping("/list/all")
	public ResponseResult<List<GoodsCategory>> getAll(){
		
		List<GoodsCategory> list = categoryService.getAllList();
		return new ResponseResult<List<GoodsCategory>>(SUCCESS, list);
	}
	
	
	

}
