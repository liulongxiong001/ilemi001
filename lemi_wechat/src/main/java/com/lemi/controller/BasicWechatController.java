package com.lemi.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lemi.constant.Constants;
import com.lemi.model.WeixinUserInfo;
import com.lemi.service.impl.CoreService;
import com.lemi.utils.ServletContextUtil;
import com.lemi.utils.SignUtil;
import com.lemi.utils.WeixinUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/basic")
public class BasicWechatController {
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String eastnet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("接口测试开始！！！");
		//微信加密签名
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		//通过校验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if(SignUtil.checkSignature(signature,timestamp,nonce)){
			out.print(echostr);
		}
		out.close();
		out = null;
		System.out.println("signature:"+signature+"  timestamp:"+timestamp+"  nonce:"+nonce+"  echostr:"+echostr);
//		response.encodeRedirectURL("success.jsp");
        return null;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public void pp(HttpServletRequest request, HttpServletResponse response) throws IOException{
		 // 消息的接收、处理、响应
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respXml = CoreService.processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respXml);
        out.close();
		
	}
	
	@RequestMapping(value="/createManu")
	public void createManu(HttpServletRequest request, HttpServletResponse response) throws IOException{
		// 调用接口获取access_token  
        String token = WeixinUtil.getAccessToken(Constants.APPID, Constants.APPSECRET);  
        if (null != token) {
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(WeixinUtil.getMenu(), token);  
            // 判断菜单创建结果  
            if (0 == result)
                System.out.println("菜单创建成功！");
            else  
            	System.out.println("菜单创建失败，错误码：" + result);  
        }  
		
	}
	
	@RequestMapping(value="/getOpenIds",method=RequestMethod.GET)
	public String getOpenIds(HttpServletRequest request, HttpServletResponse response){
		
//		AccessToken accessToken = WeixinUtil.getAccessToken(Constants.APPID, Constants.APPSECRET); 
		String accessToken = (String)ServletContextUtil.get().getAttribute(Constants.ACCESS_TOKEN);
        List<String> openIDs = WeixinUtil.getOpenIDs(accessToken);
		
        String openid = openIDs.get(0);
        
        WeixinUserInfo userInfo = WeixinUtil.getUserInfo(accessToken, openid);
        System.out.println("userInfo: "+userInfo.toString());
		
        return null;
		
	}
	
	@RequestMapping(value="/oauth",method=RequestMethod.GET)
	public String oauth(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
    	String code = request.getParameter("code");
    	System.out.println("******************code=" + code);
        
        String get_access_token_url = Constants.GET_ACCESS_TOKEN_URL.replace("APPID", Constants.APPID).replace("SECRET", Constants.APPSECRET).replace("CODE", code);
        
        JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);

        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");

        String get_userinfo = Constants.GET_USERINFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);

        JSONObject userInfoJO = WeixinUtil.httpRequest(get_userinfo, "GET", null);
        
        System.out.println("userInfoJO : "+userInfoJO.toString());

        String user_openid = userInfoJO.getString("openid");
        String user_nickname = userInfoJO.getString("nickname");
        String user_sex = userInfoJO.getString("sex");
        String user_province = userInfoJO.getString("province");
        String user_city = userInfoJO.getString("city");
        String user_country = userInfoJO.getString("country");
        String user_headimgurl = userInfoJO.getString("headimgurl");

        // UserInfo_weixin userInfo=new UserInfo_weixin(user_openid,

        response.setContentType("text/html; charset=utf-8");
        
        return "login.jsp";
		
	}
	
	@RequestMapping(value="/sysoRedirectIds",method=RequestMethod.GET)
	public String sysoRedirectIds(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		
		String redirectUri = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect";
		
		String re = URLEncoder.encode(Constants.NGROK_URL, "UTF-8");
		
		redirectUri = redirectUri.replace("APPID", Constants.APPID).replace("REDIRECT_URI", re);
		
        System.out.println("redirectUri : "+redirectUri);
		
        return null;
		
	}

}
