package com.lemi.utils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.lemi.constant.Constants;
import com.lemi.model.WeixinUserInfo;
import com.lemi.pojo.AccessToken;
import com.lemi.pojo.Button;
import com.lemi.pojo.CommonButton;
import com.lemi.pojo.ComplexButton;
import com.lemi.pojo.Menu;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;   
public class WeixinUtil {
//	private static Logger log = LoggerFactory.getLogger(WeixinUtil.class);  
	
    /** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
    public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
                // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            System.out.println(buffer.toString());
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            System.out.println("Weixin server connection timed out.");
        } catch (Exception e) {  
        	System.err.println("https request error:{}");
        }  
        return jsonObject;  
    }  
    /** 
	 * 获取access_token 
	 *  
	 * @param appid 凭证 
	 * @param appsecret 密钥 
	 * @return 
	 */  
	public static String getAccessToken(String appid, String appsecret) {  
	    AccessToken accessToken = null;  
	  
	    String requestUrl = Constants.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);  
	    JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
	    // 如果请求成功  
	    if (null != jsonObject) {
	        try {  
	            accessToken = new AccessToken();  
	            accessToken.setToken(jsonObject.getString("access_token"));  
	            accessToken.setExpiresIn(jsonObject.getInt("expires_in"));  
	        } catch (Exception e) {  
	            accessToken = null;  
	            // 获取token失败  
	            System.out.println("获取token失败 errcode:"+jsonObject.getInt("errcode")+"errmsg:"+jsonObject.getString("errmsg"));
	            return null;
	        }  
	    }  
	    return accessToken.getToken();  
	}
	
	/**
	 * 设置 accessToken缓存
	 */
	public static void initAndSetAccessToken() {  
		String accessToken = getAccessToken(Constants.APPID, Constants.APPSECRET);
		if(null != accessToken) { 
			ServletContext sc = ServletContextUtil.get();  
            sc.removeAttribute(Constants.ACCESS_TOKEN);  
            sc.setAttribute(Constants.ACCESS_TOKEN, accessToken);
		}
		
	}
	
	/**
	 * 设置 jsapi_ticket缓存
	 */
	public static void initAndSetJsapiTicket(String access_token) {  
		String jsapi_ticket = getJsApiTicket(access_token);
		if(null != jsapi_ticket) { 
			ServletContext sc = ServletContextUtil.get();  
			sc.removeAttribute(Constants.JSAPI_TICKET);  
			sc.setAttribute(Constants.JSAPI_TICKET, jsapi_ticket);
		}
	}
	
	/**
	 * 获取 jsapi_ticket
	 * @param url
	 * @return
	 */
	public static String getJsApiTicket(String access_token) { 
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token + "&type=jsapi";
		String jsapi_ticket = "";
		JSONObject json = WeixinUtil.httpRequest(url, "GET", null);
        if (json != null) {
            jsapi_ticket = json.getString("ticket");
        }
		return jsapi_ticket;
	}
	

	/**
	 * code 获取jsonObject
	 * @return
	 */
	public static JSONObject getAccessTokenByCode(String code){
		String get_access_token_url = Constants.GET_ACCESS_TOKEN_URL.replace("APPID", Constants.APPID).replace("SECRET", Constants.APPSECRET).replace("CODE", code);
        
        JSONObject jsonObject = WeixinUtil.httpRequest(get_access_token_url, "GET", null);

        return jsonObject;
	}
	
	/**
     * URL编码（utf-8）
     * 
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
	
	/** 
	 * 创建菜单 
	 *  
	 * @param menu 菜单实例 
	 * @param accessToken 有效的access_token 
	 * @return 0表示成功，其他值表示失败 
	 */  
	public static int createMenu(Menu menu, String accessToken) {  
	    int result = 0;  
	  
	    // 拼装创建菜单的url  
	    String url = Constants.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);  
	    // 将菜单对象转换成json字符串  
	    String jsonMenu = JSONObject.fromObject(menu).toString();
	    //http://ef47b86a.ngrok.io 这个每次启动穿透都会变
	    //String jsonMenu = "{\"button\": [{\"type\":\"view\",\"name\":\"登录\",\"key\":\"11\",\"url\":\"http://ef47b86a.ngrok.io/eastnet_wechat/LoginServlet\"},{\"type\":\"click\",\"name\":\"支付\",\"key\":\"21\"}]}";
	    // 调用接口创建菜单
	    JSONObject jsonObject = httpRequest(url, "POST", jsonMenu);  
	    
	    if (null != jsonObject) {  
	        if (0 != jsonObject.getInt("errcode")) {  
	            result = jsonObject.getInt("errcode"); 
	            System.out.println("创建菜单失败errcode:"+jsonObject.getInt("errcode")+"errmsg:"+jsonObject.getString("errmsg"));
//	            log.error("创建菜单失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));  
	        }  
	    }  
	  
	    return result;  
	}
	/**
	 * 获取微信服务器的IPs
	 * @param string
	 * @param token
	 * @return
	 */
	public static List<String> getServerIPs(String token) {
		List<String> ips = new ArrayList<String>();
		//拼装获取IPS 的 url  
	    String url = Constants.WECHAT_SERVER_IP_URL.replace("ACCESS_TOKEN", token); 
	    JSONObject jsonObject = httpRequest(url, "GET", null); 
	    if (null != jsonObject) {  
	    		JSONArray jsonArray = jsonObject.getJSONArray("ip_list");
	    		for (int i = 0; i < jsonArray.size(); i++) {
	    			String ipStr = (String)jsonArray.get(i);
	    			ips.add(ipStr);
				}
	    		return ips;
	    }
		return null;
	}
	
	
	public static List<String> getOpenIDs(String token) {
		
		List<String> list = new ArrayList<String>();
		//拼装url  
	    String url = Constants.GET_ALL_USER_OPENID.replace("ACCESS_TOKEN", token).replace("NEXT_OPENID", "");
	    JSONObject jsonObject = httpRequest(url, "GET", null); 
	    System.out.println("url:"+url);
	    System.out.println("jsonObject.toString() : "+jsonObject.toString());
	    
	    JSONObject data = jsonObject.getJSONObject("data");
	    JSONArray openids = data.getJSONArray("openid");
	    
	    for (int i = 0; i < openids.size(); i++) {
			String openid = (String)openids.get(i);
			list.add(openid);
		}
	    
		return list;
	} 
	
	
	public static WeixinUserInfo getUserInfo(String accessToken,String openid) {
		WeixinUserInfo weixinUser = new WeixinUserInfo();
		String get_userinfo = Constants.USER_INFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openid);

        JSONObject userInfoJO = WeixinUtil.httpRequest(get_userinfo, "GET", null);

        String openId = userInfoJO.getString("openid");
        String nickname = userInfoJO.getString("nickname");
        String sex = userInfoJO.getString("sex");
        String province = userInfoJO.getString("province");
        String city = userInfoJO.getString("city");
        String country = userInfoJO.getString("country");
        String headimgurl = userInfoJO.getString("headimgurl");
        String subscribeTime = userInfoJO.getString("subscribe_time");
        int subscribe = userInfoJO.getInt("subscribe");
        weixinUser.setOpenid(openId);
        weixinUser.setNickname(nickname);
        weixinUser.setCountry(country);
        weixinUser.setSex(Integer.parseInt(sex));
        weixinUser.setCity(city);
        weixinUser.setProvince(province);
        weixinUser.setHeadimgurl(headimgurl);
        weixinUser.setSubscribetime(subscribeTime);
        weixinUser.setSubscribe(subscribe);
//        Date date = new Date(Integer.parseInt(subscribeTime));
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr = simpleDateFormat.format(new Date());
        return weixinUser;
	}
	
	/**
    * 方法名：getWxConfig</br>
    * 详述：获取微信的配置信息 </br>
    * @param request
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
    */
	public static Map<String, Object> getWxConfig(HttpServletRequest request) {
        Map<String, Object> ret = new HashMap<String, Object>();
        String requestUrl = request.getRequestURL().toString();
        String queryurl=request.getQueryString();  
        if(null!=queryurl){  
        	requestUrl+="?"+queryurl;  
        }  
        String access_token = "";
        String jsapi_ticket = "";
        String timestamp = Long.toString(System.currentTimeMillis() / 1000); // 必填，生成签名的时间戳
        String nonceStr = UUID.randomUUID().toString(); // 必填，生成签名的随机串	
        
        access_token = (String)ServletContextUtil.get().getAttribute(Constants.ACCESS_TOKEN);
        jsapi_ticket = (String)ServletContextUtil.get().getAttribute(Constants.JSAPI_TICKET);
        
        String signature = "";
        // 注意这里参数名必须全部小写，且必须有序
        String sign = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + requestUrl;//http://f54a63bd.ngrok.io/jsp/login/login.jsp
        try {
        	 MessageDigest crypt = MessageDigest.getInstance("SHA-1");
             crypt.reset();
             crypt.update(sign.getBytes("UTF-8"));
             signature = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        ret.put("appId", Constants.APPID);
        ret.put("timestamp", timestamp);
        ret.put("nonceStr", nonceStr);
        ret.put("signature", signature);
        request.setAttribute("appId", ret.get("appId"));
	    request.setAttribute("timestamp", ret.get("timestamp"));
	    request.setAttribute("nonceStr", ret.get("nonceStr"));
	    request.setAttribute("signature", ret.get("signature"));
	    request.setAttribute("requestUrl", requestUrl);
        return ret;
    }
    
	
    /**
    * 方法名：byteToHex</br>
    * 详述：字符串加密辅助方法 </br>
    * @param hash
    * @return 说明返回值含义
    * @throws 说明发生此异常的条件
     */
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    
    
    /** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    public static Menu getMenu() {  
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
