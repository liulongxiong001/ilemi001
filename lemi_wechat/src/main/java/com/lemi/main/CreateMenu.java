package com.lemi.main;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.lemi.constant.Constants;
import com.lemi.pojo.AccessToken;
import com.lemi.pojo.Button;
import com.lemi.pojo.CommonButton;
import com.lemi.pojo.ComplexButton;
import com.lemi.pojo.Menu;
import com.lemi.utils.WeixinUtil;

public class CreateMenu {
	
	public static void main(String[] args){
		
		// 调用接口获取access_token  
        String token = WeixinUtil.getAccessToken(Constants.APPID, Constants.APPSECRET);  
        if (null != token) {
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(getMenu(), token);  
            // 判断菜单创建结果  
            if (0 == result)
                System.out.println("菜单创建成功！");
            else  
            	System.out.println("菜单创建失败，错误码：" + result);  
        }  
		
	}
	
	
	/** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
    	String redirectUrl = "";
    	try {
    		redirectUrl = URLEncoder.encode(Constants.NGROK_URL, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	CommonButton btn11 = new CommonButton();  
        btn11.setName("乐米登录");
        btn11.setType("view");
        btn11.setKey("11");
        btn11.setUrl(Constants.GET_CODE_URL.replace("APPID", Constants.APPID).replace("REDIRECT_URI", redirectUrl));
        
        //https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx471b8763f04943bb&redirect_uri=http%3A%2F%2F8ad78565.ngrok.io&response_type=code&scope=snsapi_base&state=123#wechat_redirect
        
        CommonButton btn21 = new CommonButton();  
        btn21.setName("包月");  
        btn21.setType("click");  
        btn21.setKey("21");  
  
        CommonButton btn22 = new CommonButton();  
        btn22.setName("测试");  
        btn22.setType("click");  
        btn22.setKey("22");  
  
        CommonButton btn31 = new CommonButton();  
        btn31.setName("获取1");  
        btn31.setType("click");  
        btn31.setKey("31");  
  
        CommonButton btn32 = new CommonButton();  
        btn32.setName("体验2");  
        btn32.setType("click");  
        btn32.setKey("32");  
  
//    	ComplexButton mainBtn1 = new ComplexButton();  
//        mainBtn1.setName("乐米登录");  
//        mainBtn1.setSub_button(new CommonButton[] { btn11 });  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("支付");  
        mainBtn2.setSub_button(new CommonButton[] { btn21, btn22 });  
  
       ComplexButton mainBtn3 = new ComplexButton();  
        mainBtn3.setName("更多体验");  
        mainBtn3.setSub_button(new CommonButton[] { btn31, btn32 }); 
  
        /** 
         * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
         *  
         * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
         * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
         * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
         */  
        Menu menu = new Menu();  
        menu.setButton(new Button[] {btn11, btn22, mainBtn3 });
        
        return menu; 
    }  

}
