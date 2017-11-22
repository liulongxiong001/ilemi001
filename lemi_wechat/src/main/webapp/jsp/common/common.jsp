<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="keywords" content="小说，阅读，网络小说，" />
	<meta name="description" content="友趣阅读" />
	<title>友趣阅读-我的</title> 
  </head>
	<body>
		
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