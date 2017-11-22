package com.lemi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bookstore")
public class BookstoreController {

	@RequestMapping(value="/bookstore")
	public String bookstore(HttpServletRequest request) {
		
		request.setAttribute("bookstrore", "wo lai le !!");
		return "bookstore/bookstore";
	}
	
	@RequestMapping(value="/bookview")
	public String bookview(HttpServletRequest request) {
		
		request.setAttribute("bookview", "wo lai le !!");
		
		return "bookstore/bookview";
	}
	
	@RequestMapping(value="/pageview")
	public String pageview(HttpServletRequest request) {
		
		request.setAttribute("pageview", "wo lai le !!");
		return "bookstore/pageview";
	}
	
}
