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
	<%request.setCharacterEncoding("utf-8"); %>
	<%@ include file="chkSession.jsp"%>
	<jsp:useBean id="dd" class="net.chat.ChatroomBasicAction.DealData" /> 
  </head>
  
  <body>
  <% 
   String account=session.getAttribute("_USER").toString();
   String musicUrl=request.getParameter("musicUrl");
   String msgTo=request.getParameter("msgTo");
   if(msgTo ==null || "".equals(msgTo))
   {
   		msgTo="0";
   }
   String content="";
   content+="分享音乐<a href=\"javascript:void(0)\" onclick=\"listensharedmuisc('"+musicUrl+"')\">"+musicUrl+"</a>";
   dd.addContent(content, account, msgTo);
  %>
   
  </body>
</html>
