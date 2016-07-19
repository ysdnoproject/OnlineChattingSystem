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
	<%@ include file="chkSession.jsp"%>
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
  <jsp:useBean id="user" class="net.chat.Users.Users" /> 
  <body>
    <%
    String action=request.getParameter("action");
    String account=(String)session.getAttribute("_USER");
    if("All".equals(action)){
		out.print(user.getAllUsers(account));
	}else if("online".equals(action))
	{
		out.print(user.getOnlineUsers(account));
	}
    
    %>
  </body>
</html>
