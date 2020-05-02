package edu.neu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.neu.store.entity.District;
import edu.neu.store.service.IDistrictService;
import edu.neu.store.util.ResponseResult;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController {

	@Autowired
	private IDistrictService districtService;

	@RequestMapping("/list/{parent}")
	public ResponseResult<List<District>> getListByParent(@PathVariable("parent") String parent) {
		List<District> list = districtService.getListByParent(parent);
		return new ResponseResult<List<District>>(SUCCESS, list);
	}

}
