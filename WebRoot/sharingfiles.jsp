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
	<script language="javascript" type="text/javascript" src="javascript/checkonline.js" charset="utf-8"></script>
	<script type="text/javascript">
	$(document).ready(function()
	{
		getfilelist();
    	
    	$("#uploadfilebtn").click(function(){
		window.open("upsharingfile.jsp","child","height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
		});
	});
	
	function getfilelist()
	{
		$.ajax({
  			type:"POST",
  			url:"service/getfilelist.jsp",
  			success:function(data){
  				$("#filelist").html(data);
  			}
    	});
	}
	
	setInterval(getfilelist,2000);
	
	
	</script>

  </head>
  
  <body>
  	<input id="uploadfilebtn" type="button" value="上传文件" class="btn"/>
    <div id="filelist"></div>
  </body>
</html>
