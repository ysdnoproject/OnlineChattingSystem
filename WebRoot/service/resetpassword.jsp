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
	<!--按照中文接收-->
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
  <jsp:useBean id="reset" class="net.chat.Users.User" />
  <body>
    <%
    //获取账号
    String account = request.getParameter("account");
    //获取用户密码
    String userPassword=request.getParameter("userPassword");
    //获取密保问题
    String question=request.getParameter("question");
    //获取密保问题答案
    String answer=request.getParameter("answer");
    if(reset.reset(account, userPassword, question, answer))
    {
    	out.println("<p align='center'><font size='5'>重置密码成功,将在3秒后跳转到登陆页面</font></p>");
		out.println("<p align='center'><a href=login.jsp>点击立即跳转到登陆页面</a></p>");
		response.setHeader("refresh","3;url='../login.jsp'");
    }
    else
    {
    	out.println("<p align='center'><font size='5'>重置密码失败,将在3秒后跳转到找回密码页面</font></p>");
		out.println("<p align='center'><a href=resetpwd.jsp>点击立即跳转到找回密码页面</a></p>");
		response.setHeader("refresh","3;url='../resetpwd.jsp'");
    }
    %>
  </body>
</html>
