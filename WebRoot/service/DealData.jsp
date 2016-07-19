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

  </head>
  <%@ include file="chkSession.jsp"%>
  <jsp:useBean id="dd" class="net.chat.ChatroomBasicAction.DealData" /> 
  <body>
    <%
	String action=request.getParameter("action");
	//获取在线用户列表
	 if("onLineList".equals(action)){
		out.println(dd.GetOnlineUsers());
	}
	//发送消息
	else if("SendContent".equals(action)){
		String content=request.getParameter("content");
		String msgFrom=session.getAttribute("_USER").toString();
		String msgTo=request.getParameter("msgTo");
		out.println(dd.addContent(content,msgFrom,msgTo));
		
	}
	//获取所有聊天消息
	else if("ChatList".equals(action)){
		String account=(String)session.getAttribute("_USER");
		String loginstate=(String)session.getAttribute("_LOGIN");
		if(session.isNew()==true||session.getAttribute("_LOGIN")==null||!session.getAttribute("_LOGIN").equals("_SUCCESS"))
		{
			out.print("unLogin");
		}
		if(account==null){
			out.print("unLogin");
		}else{
			out.print(dd.AllChatList(account));
		}
	}
	//检测在线状态
	else if("CheckOnline".equals(action))
	{
		String account=(String)session.getAttribute("_USER");
		if(account==null)
		{
			out.print("offline");
		}
		else
		{
			String result=dd.checkOnline(account);
			if(result.equals("offline"))
			{
			    //删除session
				session.invalidate();
			}
			out.print(result);
		}
	}

%>
  </body>
</html>
