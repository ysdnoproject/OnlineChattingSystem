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
	<%
	out.println("<script language='JavaScript'>");
	out.println("function closepage(){");
	out.println("window.opener=null;");
	out.println("window.open('','_self');");
	out.println("window.close();}");
	out.println("</script>");  
	%>
  </head>
   <jsp:useBean id="user" class="net.chat.Users.User" />
  <body>
    <%
    	String account=(String)session.getAttribute("_USER"); 
    	String nickname=request.getParameter("nickname");
    	String year=request.getParameter("year");
    	String month=request.getParameter("month");
    	String day=request.getParameter("day");
    	String sex=request.getParameter("sex");
    	String address=request.getParameter("address");
    	String memo=request.getParameter("memo");
    	if(user.updateuserinfo(account, nickname, year, month, day, sex, address, memo))
    	{
    	out.print("<p align='center'><font size=18px>修改成功</font></p><p align=center><a href='javascript:closepage()'>点击关闭</font></p>");
    	}
    	else{
    	out.print("<p align='center'><font size=18px>发生错误，修改失败</font></p>");
    	}
    %>
  </body>
</html>
