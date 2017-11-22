package com.lemi.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lemi.constant.Constants;
import com.lemi.utils.WeixinUtil;

import net.sf.json.JSONObject;

public class LoginInterceptor implements HandlerInterceptor {
	
	 	@Override
	    public void afterCompletion(HttpServletRequest arg0,
	            HttpServletResponse arg1, Object arg2, Exception arg3)
	            throws Exception {
	        // TODO Auto-generated method stub
	    }
	    @Override
	    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
	            Object arg2, ModelAndView arg3) throws Exception {
	        // TODO Auto-generated method stub
	    }
	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
	            Object o) throws Exception {
	    	
	    	String path = request.getContextPath();
	        String baseUrl =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath();
	        String webUrl = request.getScheme()+"://"+request.getServerName();
	        request.setAttribute("path", path);
	        request.setAttribute("webUrl", webUrl);
	        request.setAttribute("baseUrl", baseUrl);
	        return true;
	    }
	
}

