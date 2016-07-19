<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ include file="chkSession.jsp"%>
<%request.setCharacterEncoding("utf-8"); %>

 <jsp:useBean id="user" class="net.chat.Users.Users" /> 
 <%
    String action=request.getParameter("action");
    String account=(String)session.getAttribute("_USER");
    if("kick".equals(action)){
        String kickUsers=request.getParameter("kickUsers");
		out.print(user.kickUsers(account, kickUsers));
	}else if("delete".equals(action))
	{
		String deleteUsers=request.getParameter("deleteUsers");
		out.print(user.deleteUsers(account, deleteUsers));
	}  
%>

