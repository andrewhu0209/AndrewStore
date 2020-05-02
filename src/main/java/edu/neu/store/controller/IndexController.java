package edu.neu.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Set Default page as web/index.html
 * 設定預設頁面為index.html
 * @author andrew
 *
 */

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
		return "redirect:web/index.html";
	}

}
