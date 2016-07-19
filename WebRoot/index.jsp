<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>OnlineChatting系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="./css/style.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
   <div class="header" id="top">
		<div class="logo">
			<a href="index.jsp"><img src="./images/logo.png" title="logo" /></a>
		</div>
    </div>
	<div class="border">
	</div>
	<div class="wel-mesg">
			<h2>欢迎来到本聊天室!</h2>
            <p>在这里你可以和趣味相投的人毫无顾忌的聊天！</p>
            <a href="login.jsp">点击登录!</a>
            <a href="register.jsp">点击注册!</a>
    </div>
    <div class="bottom">
    </div>
  </body>
</html>
