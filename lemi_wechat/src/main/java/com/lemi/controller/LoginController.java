package com.lemi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lemi.constant.Constants;
import com.lemi.model.WeixinUserInfo;
import com.lemi.service.WeixinUserInfoServiceI;
import com.lemi.utils.ServletContextUtil;
import com.lemi.utils.WeixinUtil;

import net.sf.json.JSONObject;

/**
 * 书架
 * @author admin
 *
 */

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private WeixinUserInfoServiceI weixinUserInfoService;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/index")
	public String login(HttpServletRequest request) throws InterruptedException{
		
		Map<String, Object> wxConfig = WeixinUtil.getWxConfig(request);
		String openid = request.getParameter("openid");
		return "login/login";
	}
	
	/**
	 * 网页授权登录
	 * @param request
	 * @return
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value="")
	public String oauth(HttpServletRequest request) {
		String code = request.getParameter("code");
		System.out.println("code ======================== : "+code);
    	if(code !=null){
    		String accessToken = (String)ServletContextUtil.get().getAttribute(Constants.ACCESS_TOKEN);
    		String get_access_token_url = Constants.GET_ACCESS_TOKEN_URL.replace("APPID", Constants.APPID).replace("SECRET", Constants.APPSECRET).replace("CODE", code);
            JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);
            boolean flag = true;
            String access_token = "";
			try {
				access_token = jsonObject.getString("access_token");
			} catch (Exception e) {
				flag = false;
				//e.printStackTrace();
			}
			if(flag){
//	            String refresh_token = jsonObject.getString("refresh_token");
	            String openid = jsonObject.getString("openid");//oSISR1Lk5KftQOHd7iNhHciF2yEM
	            WeixinUserInfo userInfo = WeixinUtil.getUserInfo(accessToken, openid);
//	            System.out.println("access_token:"+access_token);
//	            String get_userinfo = Constants.GET_USERINFO.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
//	            
//	            JSONObject userInfoJO = WeixinUtil.httpRequest(get_userinfo, "GET", null);
//	            System.out.println("userInfoJO"+userInfoJO);//{"errcode":48001,"errmsg":"api unauthorized, hints: [ req_id: CLF6MA0482s158 ]"}
//	            String nickname = userInfoJO.getString("nickname");
//	            
//	            int sex = userInfoJO.getInt("sex");
//	            String language = userInfoJO.getString("language");
//	            String city = userInfoJO.getString("city");
//	            String province = userInfoJO.getString("province");
//	            String country = userInfoJO.getString("country");
//	            String headimgurl = userInfoJO.getString("headimgurl");
//	            
//	            
//	            
//	            WeixinUserInfo userInfo = new WeixinUserInfo();
//	            userInfo.setNickname(nickname);
//	            userInfo.setSex(sex);
//	            userInfo.setLanguage(language);
//	            userInfo.setCountry(country);
//	            userInfo.setCity(city);
//	            userInfo.setProvince(province);
//	            userInfo.setHeadimgurl(headimgurl);
//	            userInfo.setOpenid(openid);
	            
	            request.getSession().setAttribute("nickname", userInfo.getNickname());
	            request.getSession().setAttribute("headimgurl", userInfo.getHeadimgurl());
	            request.getSession().setAttribute("openid", openid);
	            
//	            WeixinUserInfo userSelectById = weixinUserInfoService.selectById(openid);
//	            if(userSelectById == null){
//	            	weixinUserInfoService.insert(userInfo);
//	            } else{
//	            	weixinUserInfoService.update(userInfo);
//	            }
	            
	            //System.out.println("userInfoJO : "+userInfoJO.toString());
			}

    	}
		
    	//查询书架
		Map<String, Object> wxConfig = WeixinUtil.getWxConfig(request);
    	request.setAttribute("title", "友趣阅读-书架");
		return "login/login";
	}
	
}
