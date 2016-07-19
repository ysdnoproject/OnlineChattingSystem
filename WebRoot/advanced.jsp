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
	<%@ include file="service/chkSession.jsp"%>
	<link href="./css/style.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="javascript/jquery-1.8.0.min.js"></script>
	<script language="javascript" type="text/javascript" src="javascript/advanced.js" charset="utf-8"></script>
	<script language="javascript" type="text/javascript" src="javascript/checkonline.js" charset="utf-8"></script>
  </head>
  
  <body>
  	<input id="allusers" type="button" value="显示所有用户" class="allusers" onclick="GetAllUsers()"/>
  	<input id="onlineusers" type="button" value="显示在线用户" class="onlineusers" onclick="GetOnlineUsers()"/>
	<div class="divmanager"><a href="chatroom.jsp">返回聊天室</a></div>
    <table>
    <tr>
    <td width="150">账号</td>
    <td width="200">昵称</td>
    <td>上次登录时间</td>
    </tr>
    </table>
 
  	
	<div class="divShowUsers" id="divContent"></div>
	

    <div class="managerbottom">
    <input id="kickusers" type="button"  value="踢出聊天" class="kickusers" onclick="KickUsers()"/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input id="deleteusers" type="button"  value="删除用户" class="deleteusers" onclick="confirmDeleteUsers()"/>
    </div>
    
  </body>
</html>
