package com.lemi.constant;

/**
 * 常量接口
 * @author Administrator
 *
 */
public interface Constants {

	/**
	 * 项目配置相关的常量
	 */
	String JDBC_DRIVER = "jdbc.driver";
	String JDBC_DATASOURCE_SIZE = "jdbc.datasource.size";
	String JDBC_URL = "jdbc.url";
	String JDBC_USER = "jdbc.user";
	String JDBC_PASSWORD = "jdbc.password";
	
	String ACCESS_TOKEN = "ACCESS_TOKEN";
	String JSAPI_TICKET = "JSAPI_TICKET";
	
	/**
	 * 第三方用户唯一凭证
	 */
	String APPID =  "wx0ddb0005648971ff";//  "wx471b8763f04943bb";  // 正式的
	
	/**
	 * 第三方用户唯一凭证密钥  
	 */
	String APPSECRET =  "79a72b45eb2940f1e29850ffda84460b";//	"a067f831f5a4609bab7a0dd51d25614f";  // 正式的
	
	/**
	 * 与微信配置中的的Token一致
	 */
	String TOKEN  = "lemi";	
	
	/**
	 * 内网映射的URL
	 */
	String NGROK_URL ="http://wx.soutuw.com";// "http://8ad78565.ngrok.io";	// 
	
	/**
	 * 菜单创建（POST） 限100（次/天）  
	 */
	String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	/**
	 * 网页授权获取用户基本信息
	 */
	String REDIRECT_URI = "http://wx.soutuw.com";// "http://8ad78565.ngrok.io";// 
	
	/**
	 * 用户同意授权，获取code
	 */
	String GET_CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
	
	/**
	 * 通过code换取网页授权access_token
	 */
	String GET_ACCESS_TOKEN_URL =  "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	/**
	 * 获取第二步的refresh_token后，请求以下链接获取access_token：  (网页授权的)
	 */
	String REFRESH_TOKEN =  "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN"; 
	
	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo),这个access_token参数是用户授权后获取的，不是本公众号的那种
	 */
	String GET_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	/**
	 * 通过上面的access_token获取用户openID
	 */
	String YIDONG_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=ACCESS_TOKEN";
	
	
	/**
	 * 获取用户基本信息
	 */
	String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN ";
	/**
	 * 获取用户列表
	 */
	String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	/**
	 * 获取access_token的接口地址（GET） 限200（次/天）  
	 */
	String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";  
	
	/**
	 * 获取微信服务器的IP地址数组  的 接口URL
	 */
	String WECHAT_SERVER_IP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";
	
	/**
	 * 获取关注者的openID
	 */
	String GET_ALL_USER_OPENID = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
	
	
	/**
	 * 网页授权登录地址
	 */
	String TEST_OAURHURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx471b8763f04943bb&redirect_uri=http%3A%2F%2F8ad78565.ngrok.io&response_type=code&scope=snsapi_base&state=123#wechat_redirect";  
	
	
}
