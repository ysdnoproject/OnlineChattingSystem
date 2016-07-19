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
  	<script language="javascript">	
	function check()
	{
		var reg=/^[0-9]+$/;
		if(document.form1.password.value=='')
		{
		 alert("请填写密码!");
		 document.form1.password.focus();
		 return false;
		}
		else if(document.form1.userPassword.value=='')
		{
		 alert("请填写新密码");
		 document.form1.userPassword.focus();
		 return false;
		}
		else if(document.form1.userPassword.value != document.form1.confirmPassword.value)
		{
		 alert("两次密码不一致");
		 document.form1.confirmPassword.focus();
		 return false;
		}
		else
		 return true;
	}
	</script>
  </head>
  
  <body>
  		<div class="header" id="top">
			<div class="logo">
				<a href="chatroom.jsp"><img src="./images/logo.png" title="logo" /></a>
			</div>
	   </div>
	    <div class="login">修改密码</div>
 	 	<form action="service/changepassword.jsp" method="post" name="form1" onSubmit="return check()">
	    <TABLE align="center" cellpadding="0" cellspacing="0" border="0" width="500">
		 
		<tr>
			<td width="200" align="center" style="color:dark" >
			原密码:
			</td>
			<td width="278">
			<input type="password" name="password" style="width:250px;height:30px" maxlength="15">
			</td>
		</tr>
		<tr>
			<td colspan="2" height="20">
			</td>
		</tr>
		<tr>
		<td align="center" style="color:dark">
		新密码:
		</td>
		<td>
		<!-- 表单中的登录密码输入框，name属性为userPassword，用于后台处理页面接收用户输入的密码-->
		<input type="password" name="userPassword" style="width:250px;height:30px" maxlength="20">
		</td>
		</tr>
			<tr>
			<td colspan="2" height="20">
			</td>
		</tr>
		<tr>
		<td align="center" style="color:dark">
		确认密码:
		</td>
		<td>
		<input type="password" name="confirmPassword" style="width:250px;height:30px" maxlength="20">
		</td>
		</tr>
		<tr>
		<td colspan="2" align="center" height="30" valign="bottom" >
		<input type="submit" value="提交" style="width:100px;height:30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="清空" style="width:100px;height:30px">
		</td>
		</tr>
		</TABLE>
		</form>  
  </body>
</html>
