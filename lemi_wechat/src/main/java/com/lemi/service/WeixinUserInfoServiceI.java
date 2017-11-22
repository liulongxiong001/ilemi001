package com.lemi.service;

import com.lemi.model.WeixinUserInfo;

public interface WeixinUserInfoServiceI {

	WeixinUserInfo selectById(String id);
	
    int insert(WeixinUserInfo userInfo);
    
    int update(WeixinUserInfo userInfo);
    
    int delete(String id);
}
