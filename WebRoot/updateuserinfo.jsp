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
	<link href="./css/style.css" rel="stylesheet" type="text/css" />
	<%@ include file="service/chkSession.jsp"%>
	<script language="javascript" type="text/javascript" src="javascript/jquery-1.8.0.min.js"></script>
	<script language="javascript" type="text/javascript" src="javascript/checkonline.js" charset="utf-8"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
	   			type:"POST",
	   			url:"service/getuserinfo.jsp",
	   			success:function(data){
	   				$("#olduserinfo").html(data);
	   			}
	   		});
		});
	</script>
  </head>
  <body>
    <div class="header" id="top">
		<div class="logo">
			<img src="./images/logo.png" title="logo" />
		</div>
    </div>
    <div class="login">修改个人资料</div>
    <div id="olduserinfo">
    	
    </div>
  </body>
</html>
