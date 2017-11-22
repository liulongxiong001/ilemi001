<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml"> 
  <head>
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="keywords" content="小说，阅读，网络小说，" />
	<meta name="description" content="友趣阅读" />
	
	<title>友趣阅读-书架</title> 
	<link rel="stylesheet" type="text/css" href="css/mainstyle.css" />
  </head>
	<body>
		<%
			String code = request.getParameter("code");
			if(code !=null){
				request.setAttribute("code", code);
				response.sendRedirect("login/?code="+code);
		%>
		<% 
			}
		%>
		
<!-- 代码部分end -->
	</body>
	
</html>

