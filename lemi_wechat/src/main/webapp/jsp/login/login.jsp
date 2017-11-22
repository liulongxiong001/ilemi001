<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="com.lemi.utils.WeixinUtil" %>
<%-- <jsp:include page="${baseUrl }/jsp/common/common.jsp" flush="false" > --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="keywords" content="小说，阅读，网络小说，" />
	<meta name="description" content="友趣阅读" />
	<title>${title}</title>
	<link rel="stylesheet" type="text/css" href="${baseUrl }/css/mainstyle.css" />
	
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
	<script type="text/javascript" src="${baseUrl }/js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="${baseUrl }/js/jquery.form-2.87.js"></script>
	<script type="text/javascript" src="${baseUrl }/js/jquery.js"></script>
  </head>
  	
	<body>
		<div id="acontent">
			 <div class="blist">
			  	<div class="edit">
			  	  <a href="#">
			  	    管理书架
			  	  </a>
			  	</div>
			  	<div class="noone">
			  		书架空空如也<br />去推荐页找找真爱吧<br />
			  		<a href="#">
			  		   去看看 ${nickname}
			  		</a>
			  	</div>
			  </div>

			  <div class="blist">
			  	<div class="edit">
			  	  <a href="#">
			  	    管理书架
			  	  </a>
			  	</div>
			  	<ul>
			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	  <span class="clear"></span>
			  	</li>

			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	   <span class="clear"></span>
			  	</li>

			  	<li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	  <span class="clear"></span>
			  	</li>

			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	   <span class="clear"></span>
			  	</li>
			  </ul>
			  </div>


			  <div class="blist">
			  	<h4 class="listTit">猜你喜欢</h4>
			  	<ul>
			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	  <span class="clear"></span>
			  	</li>

			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	   <span class="clear"></span>
			  	</li>

			  	<li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	  <span class="clear"></span>
			  	</li>

			  	  <li><a href="#">
			  	  	<img src="${baseUrl }/images/book_cover_sf.png" alt="cover" />
			  	  	<h5>回到三国</h5>
			  	  	<p>作者：转轮王</p>
			  	  	<p>正在阅读：第一章 顶嘴顶顶顶...</p>
			  	  </a>
			  	   <span class="clear"></span>
			  	</li>
			  </ul>
			  </div>
		    </div>
		    	<div class="amenu">
		    		<a class="mybook" href="#">书架</a>
		    		<a class="bookstore" href="${baseUrl }/bookstore/bookstore">推荐</a>
		    		<a class="cotaray" href="${baseUrl }/category/category">分类</a>
		    		<a class="myaccount" href="${baseUrl }/mine/myacc">我的</a>
		    	</div>
<!-- 代码部分end -->
	</body>
	<script type="text/javascript">
		
		wx.config({
		    debug: false, 
		    appId: '${appId}', 
		    timestamp: '${timestamp}', 
		    nonceStr: '${nonceStr}', 
		    signature: '${signature}',
		    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone'] 
		});
		
		wx.onMenuShareTimeline({
	        title:  '${title}', // 分享标题
	        link:	'${requestUrl}', 
	        imgUrl: 'http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg',  // 分享图标
	        success: function () { 
	        	alert("分享成功！");
	        }
	    });
		
		wx.ready(function(){
		    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，
		    // config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，
		    // 则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
		    	
			// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		    wx.onMenuShareTimeline({
		        title:  '${title}', // 分享标题
		        link:	'${requestUrl}', 
		        imgUrl: 'http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg',  // 分享图标
		        success: function () { 
		        	alert("分享成功！");
		        }
		    });
		    // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
		    wx.onMenuShareAppMessage({
		        //desc: "第七篇 ：微信公众平台开发实战Java版之如何获取微信用户基本信息", // 分享描述
		        title:  '${title}', // 分享标题
		        link:	'${requestUrl}', 
		        imgUrl: 'http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg',  // 分享图标
		        type: 'link', // 分享类型,music、video或link，不填默认为link
		        success: function () { 
		        	alert("分享成功！");
		        }
		    });
		    
		    //获取“分享到QQ”按钮点击状态及自定义分享内容接口
		    wx.onMenuShareQQ({
		        title: '${title}', // 分享标题
		        //desc: '第六篇 ：微信公众平台开发实战Java版之如何自定义微信公众号菜单', // 分享描述
		        link:	'${requestUrl}', 
		        imgUrl: "http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg", // 分享图标
		        success: function () { 
		        	alert("分享成功！");
		        },
		        cancel: function () { 
		           // 用户取消分享后执行的回调函数
		        }
		    });
		    
		    //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
		    wx.onMenuShareWeibo({
		        title: '${title}', // 分享标题
		        //desc: '分享到腾讯微博描述', // 分享描述
		        link:	'${requestUrl}', 
		        imgUrl: "http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg", // 分享图标
		        success: function () { 
		           alert("分享成功！");
		        },
		        cancel: function () { 
		            // 用户取消分享后执行的回调函数
		        }
		    });
		    
		    //获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
		    wx.onMenuShareQZone({
		        title:  '${title}', // 分享标题
		        //desc: '分享到QQ空间描述', // 分享描述
		        link:	'${requestUrl}', 
		        imgUrl: "http://f54a63bd.ngrok.io/images/share/oauthLogin.jpg", // 分享图标
		        success: function () { 
		        	alert("分享成功！");
		        },
		        cancel: function () {  
		            // 用户取消分享后执行的回调函数  
		        },  
		        fail: function (res) {  
		        	alert("分享失败，请重新尝试");  
	           } 
		    });
		    
		});
		
		wx.error(function(res){
		    // config信息验证失败会执行error函数，如签名过期导致验证失败，
		    //具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
		});

		
	</script>
	
</html>

