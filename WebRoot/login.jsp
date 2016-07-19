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
	<script language="javascript">
	//javascript check函数，用于检查表单中输入的用户昵称和登录密码是否为空
	 function check()
	 {
	  if(document.form1.account.value=='')
	  {
	   alert("账号不能为空!");
	   document.form1.account.focus();
	   return false;
	  }
	  else if(document.form1.userPassword.value=='')
	  {
	   alert("登录密码不能为空");
	   document.form1.userPassword.focus();
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
				<a href="index.jsp"><img src="./images/logo.png" title="logo" /></a>
			</div>
    </div>
    <div class="login">用户登录</div>
  	<!-- 定义一个form表单，表单提交的后台处理页面为chklogin.jsp-->
  	<!-- onSubmit事件监听，如果用户名或者密码为空就不跳转 -->
	<form action="service/chklogin.jsp" method="post" name="form1" onSubmit="return check()">
    <TABLE align="center" cellpadding="0" cellspacing="0" border="0" width="500">
	 
	<tr>
		<td width="200" align="center" style="color:dark" >
		账号:
		</td>
		<td width="278">
		<input type="text" name="account" style="width:250px;height:30px" maxlength="15">
		</td>
	</tr>
	<tr>
		<td colspan="2" height="20">
		</td>
	</tr>
	<tr>
	<td align="center" style="color:dark">
	密码:
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
	<td colspan="2" align="center" height="30" valign="bottom" >
	<input type="submit" value="登录 " style="width:100px;height:30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="reset" value="重置" style="width:100px;height:30px">
	</td>
	<td align="center" style="color:dark" width="100">
	<a href="resetpwd.jsp"  text-decoration="none">忘记密码？</a>
	</td>
	</tr>
	</TABLE>
	</form>
	<div class="login-bottom-message">
	<p>还没有还没有账号？快来注册会员，与大家一起！共享交流所带来的无限欢乐吧！</p>
	</div>
	<div class="login-bottom-register"><a href="register.jsp">点此立即注册</a></div>
  </body>
</html>
