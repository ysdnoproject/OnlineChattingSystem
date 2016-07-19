<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<%@ include file="chkSession.jsp"%>
	<!--按照中文接收-->
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
  <jsp:useBean id="user" class="net.chat.Users.User" />
  <body>
   <%
   String account=session.getAttribute("_USER").toString();
   String pwd=request.getParameter("password");
   String newpwd=request.getParameter("userPassword");
   if(user.changepwd(account, pwd, newpwd))
   {
   		//清除session中保存的登录信息
		session.invalidate();
   		out.println("<p align='center'><font size='5'>修改密码成功,将在3秒后跳转到登陆页面</font></p>");
		out.println("<p align='center'><a href=login.jsp>点击立即跳转到登陆页面</a></p>");
		response.setHeader("refresh","3;url='../login.jsp'");
   }
   else{
       	out.println("<p align='center'><font size='5'>修改密码失败,将在3秒后跳转到修改密码页面</font></p>");
		out.println("<p align='center'><a href=changepwd.jsp>点击立即跳转到修改密码页面</a></p>");
		response.setHeader("refresh","3;url='../changepwd.jsp'");
   }
   %> 
  </body>
</html>
