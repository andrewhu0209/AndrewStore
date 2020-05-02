package edu.neu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.store.entity.Goods;
import edu.neu.store.service.IGoodsService;
import edu.neu.store.util.ResponseResult;

@RestController
@RequestMapping("/goods")
public class GoodsController extends BaseController {

	@Autowired
	private IGoodsService goodsService;

	@GetMapping("/list/{categoryId}")
	public ResponseResult<List<Goods>> getByCategory(@PathVariable("categoryId") Long categoryId) {
		List<Goods> list = goodsService.getByCategory(categoryId, 0, 20);
		return new ResponseResult<List<Goods>>(SUCCESS, list);
	}

	@GetMapping("/details/{id}")
	public ResponseResult<Goods> getById(@PathVariable("id") Long id) {
		Goods goods = goodsService.getById(id);
		return new ResponseResult<Goods>(SUCCESS, goods);
	}

	@GetMapping("/hot")
	public ResponseResult<List<Goods>> getHotGoods() {
		List<Goods> list = goodsService.getByPriority(6); 
		return new ResponseResult<List<Goods>>(SUCCESS, list);
	}
	
	@GetMapping("/hot/all")
	public ResponseResult<List<Goods>> getAllHotGoods() {
		List<Goods> list = goodsService.getByPriority(100);//暫定100個 
		return new ResponseResult<List<Goods>>(SUCCESS, list);
	}
	
	@GetMapping("/key/{key}")
	public ResponseResult<List<Goods>> getByKey(@PathVariable("key") String key) {
		System.out.println("KEY = " + key);
		//調用查詢Service
		List<Goods> list = goodsService.getByKey(key);
		//反回List<Goods>資料
		return new ResponseResult<List<Goods>>(SUCCESS, list);
	}

}
