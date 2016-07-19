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
	<script language="javascript" type="text/javascript" src="javascript/jquery-1.8.0.min.js"></script>
	<script language="javascript" type="text/javascript">
	$(function(){
		var account=${param.account};
		$.ajax({
	   			type:"POST",
	   			url:"service/viewuser.jsp",
	   			data:"account="+account,
	   			success:function(data){
	   				if(data.indexOf("nouserinfo")>0)
	   				{
	   					alert("加载用户资料失败");  
						window.location.href="chatroom.jsp";  
	   				}
	   				else{
	   					$("#showuserinfo").html(data);
	   				}
	   			}
	   		}); 
	}); 
</script>
  </head>
  
  <body>
    <div id="showuserinfo"></div>
  </body>
</html>
