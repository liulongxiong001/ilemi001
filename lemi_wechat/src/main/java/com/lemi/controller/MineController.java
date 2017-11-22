package com.lemi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mine")
public class MineController {

	
	@RequestMapping(value="/myacc")
	public String mine(HttpServletRequest request) {
		
		request.setAttribute("mine", "wo lai le !!");
		return "mine/myacc";
	}
	
	@RequestMapping(value="/myvip")
	public String myvip(HttpServletRequest request) {
		
		request.setAttribute("myvip", "wo lai le !!");
		return "mine/myvip";
	}
	
	@RequestMapping(value="/payrecord")
	public String payrecord(HttpServletRequest request) {
		
		request.setAttribute("payrecord", "wo lai le !!");
		return "mine/payrecord";
	}
	
	
	@RequestMapping(value="/recentread")
	public String recentread(HttpServletRequest request) {
		
		request.setAttribute("recentread", "wo lai le !!");
		return "mine/recentread";
	}
	
	
	@RequestMapping(value="/feedback")
	public String feedback(HttpServletRequest request) {
		
		request.setAttribute("feedback", "wo lai le !!");
		return "mine/feedback";
	}
	
}
