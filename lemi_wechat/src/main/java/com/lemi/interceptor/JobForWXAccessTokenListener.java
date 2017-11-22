package com.lemi.interceptor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.lemi.constant.Constants;
import com.lemi.utils.ServletContextUtil;
import com.lemi.utils.WeixinUtil;


public class JobForWXAccessTokenListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getParent() == null){  
            
            Runnable runnable = new Runnable() {  
                public void run() {  
                    /** 
                     * 定时设置accessToken
                     */  
                	WeixinUtil.initAndSetAccessToken();
                    String access_token = (String)ServletContextUtil.get().getAttribute(Constants.ACCESS_TOKEN);
                    WeixinUtil.initAndSetJsapiTicket(access_token);
                }  
            };  
            
//            Runnable runnable2 = new Runnable() {  
//                public void run() {  
//                    /** 
//                     * 定时设置accessToken(不知道为什么，重用后只能有5分钟的时间)
//                     */  
//                	WeixinUtil.initAndSetAccessToken();
//                }  
//            };  
            
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
            //因为access_token为7200有效期， 所以这里间隔7000调用一次就够了
            service.scheduleAtFixedRate(runnable, 1, 7000, TimeUnit.SECONDS);
//            service.scheduleAtFixedRate(runnable2, 1, 270, TimeUnit.SECONDS);
        }  
	}

}
