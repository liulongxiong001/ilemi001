package com.lemi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CateyoryController {

	@RequestMapping(value="/category")
	public String mine(HttpServletRequest request) {
		
		request.setAttribute("category", "wo lai le !!");
		return "category/category";
	}
	
}
