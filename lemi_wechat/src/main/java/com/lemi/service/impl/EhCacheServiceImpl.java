package com.lemi.service.impl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lemi.service.EhCacheTestServiceI;
import com.lemi.utils.WeixinUtil;

import net.sf.json.JSONObject;

@Service
public class EhCacheServiceImpl implements EhCacheTestServiceI {
	
	@Cacheable(value="cacheTest",key="#param")
	public String getTimestamp(String param) {
		Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
	}
	
	@Cacheable(value="codeAccessToken",key="#code")
	public JSONObject getCodeAccessToken(String code) {
		JSONObject accessToken = WeixinUtil.getAccessTokenByCode(code);
		System.out.println("accessToken================================:"+accessToken.toString());
        return accessToken;
	}
	
	@Cacheable(value="accessToken",key="#access_token")
	public String getAccessToken(String access_token) {
		return access_token;
	}

}
