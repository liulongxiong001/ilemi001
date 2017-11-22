package com.lemi.service;

import net.sf.json.JSONObject;

public interface EhCacheTestServiceI {
	
	public String getTimestamp(String param);
	
	public JSONObject getCodeAccessToken(String code);
	
	public String getAccessToken(String access_token);

}
