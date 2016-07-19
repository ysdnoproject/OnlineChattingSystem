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
			//用户名不需要验证，允许重复，会随机分配一个号码作为唯一标识		
			function registSubmit(){
			//邮箱验证
			var myreg = /^([a-zA-Z0-9]+[_|-|\.]?)*[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
			if(document.form1.nickName.value=='')
			{
			 alert("用户昵称不能为空!");
			 document.form1.nickName.focus();
			 return false;
			}
			else if(document.form1.userPassword.value=='')
			{
			 alert("登录密码不能为空");
			 document.form1.userPassword.focus();
			 return false;
			}
			else if(document.form1.userPassword.value != document.form1.confirmPassword.value)
			{
			 alert("两次密码不一致");
			 document.form1.confirmPassword.focus();
			 return false;
			}
			else if(document.form1.mail.value=='')
			{
			 alert("请填写邮箱");
			 document.form1.mail.focus();
			 return false;
			}
			else if(!myreg.test(document.form1.mail.value))
			{
			 alert("请填写正确的邮箱");
			 document.form1.mail.focus();
			 return false;
			}
			else if(document.form1.answer.value=='')
			{
			 alert("请填写密保答案！");
			 document.form1.answer.focus();
			 return false;
			}
			else if(document.form1.answer.value.length>30)
			{
			 alert("密保答案请不要超过30个字符！");
			 document.form1.answer.focus();
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
	    <div class="login">用户注册</div>

		<form action="service/registering.jsp" method="post" name="form1" onSubmit="return registSubmit()">
	    <TABLE align="center" cellpadding="0" cellspacing="0" border="0" width="500">
		 
		<tr>
			<td width="200" align="center" style="color:dark" >
			用户名:
			</td>
			<td width="278">
			<!-- 表单中的用户昵称输入框，name属性为nickName，用于后台处理页面接收用户输入的昵称-->
			<input type="text" name="nickName" style="width:250px;height:30px" maxlength="20">
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
		<td align="center" style="color:dark">
		确认密码:
		</td>
		<td>
		<input type="password" name="confirmPassword" style="width:250px;height:30px" maxlength="20">
		</td>
		</tr>
			<tr>
			<td colspan="2" height="20">
			</td>
		</tr>
		<tr>
		<td align="center" style="color:dark">
		邮箱:
		</td>
		<td>
		<input type="text" name="mail" style="width:250px;height:30px" maxlength="100">
		</td>
		</tr>
			<tr>
			<td colspan="2" height="20">
			</td>
		</tr>	
		<tr>
		<td align="center" style="color:dark">
		请选择密保问题:
		</td>
		<td>
		<select name="question"  class="xla_k" style="width:250px;height:30px">
    		<option value="父亲的名字" selected="selected">父亲的名字</option>
			<option value="母亲的名字">母亲的名字</option>
			<option value="自己的生日">自己的生日</option>
			<option value="小学的名字">小学的名字</option>
    	</select>
		</td>
		</tr>
		<tr>
			<td colspan="2" height="20">
			</td>
		</tr>	
		<tr>
		<td align="center" style="color:dark">
		请填写密保问题答案:
		</td>
		<td>
		<input type="text" name="answer" style="width:250px;height:30px" maxlength="30"> 	
		</td>
		</tr>
		<tr>
		<td colspan="2" align="center" height="30" valign="bottom" >
		<input type="submit" value="注册" style="width:100px;height:30px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="reset" value="重置" style="width:100px;height:30px">
		</td>
		</tr>
		</TABLE>
		</form>
		<div class="login-bottom-register"><a href="login.jsp">已有账号，直接登录</a></div>
  </body>
</html>
