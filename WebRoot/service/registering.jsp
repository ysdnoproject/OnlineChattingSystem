<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<!--按照中文接收-->
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
   <jsp:useBean id="registation" class="net.chat.BasicAction.Register" />
  <body>
    <%
     /*
       设置request的字符集为utf-8
        如果在这里不设置utf-8编码，所有的中文字符
       都会变为乱码。
    */
    
    //获取用户名
    String nickName = request.getParameter("nickName");
    //获取用户密码
    String userPassword=request.getParameter("userPassword");
    //获取邮箱
    String email=request.getParameter("mail");
    //获取密保问题
    String question=request.getParameter("question");
    //获取密保问题答案
    String answer=request.getParameter("answer");
    //获取注册之后取得的账号
    int account=registation.register(nickName, userPassword, email,question,answer);
    //如果注册成功(返回的账号大于0)
    if(account>0)
    {
      out.println("<p align='center'><font size='5'>注册成功，请记下你的账户!</font></p>");
      out.println("<p align='center'><font size='5'>你的账户为："+account);
      out.println("</font></p><p align='center'><a href=login.jsp>点击立即返回登录页面</a></p>");   
    }
    else{
    	out.println("<p align='center'><font size='5'>发生了错误，注册失败,将在3秒后跳转到注册页面</font></p>");
		out.println("<p align='center'><a href=register.jsp>点击立即跳转到注册页面</a></p>");
		//清除session中保存的登录信息
		if(session.getAttribute("_LOGIN")!=null)
		{
			session.invalidate();
		}
		//三秒后跳转到注册页面
		response.setHeader("refresh","3;url='../register.jsp'");
    }
    %>
  </body>
</html>
