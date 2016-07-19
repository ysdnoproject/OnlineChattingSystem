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
	<!--按照中文接收-->
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
   <jsp:useBean id="message" class="net.chat.ChatRecord.Message" />
  <body>
     <%
    	String curPage=request.getParameter("curPage");
    	String account=session.getAttribute("_USER").toString();
    	out.print(message.getAllMessage(account, curPage));
     %>
  </body>
</html>
