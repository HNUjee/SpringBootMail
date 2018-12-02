package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Felxi
 * @date 2018-11-30
 */
@Controller
public class IndexController {
	@GetMapping(value = "/")
	public String index(){
		return "index";
	}
}
