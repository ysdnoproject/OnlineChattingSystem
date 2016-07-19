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
	<!--按照中文接收-->
	<%request.setCharacterEncoding("utf-8"); %>
  </head>
  <jsp:useBean id="check" class="net.chat.BasicAction.CheckLogin" /> 
  <body>
     <%
     //如果已经登录过了，直接转到聊天室
     if(session.getAttribute("_USER")!=null)
     {
	     response.sendRedirect("../chatroom.jsp");
     }
     else
     {
	    //获取账户
	    String account = request.getParameter("account");
	    //获取用户密码
	    String userPassword=request.getParameter("userPassword");
	    
	    //将获取到的用户登录信息与数据库中保存的用户信息进行比较
	    String loginMsg = check.checklogin(account,userPassword);
	    if(loginMsg.equals("SUCCESS_LOGIN"))
	    {
	    
	      //登录成功将账号保存到session中      
	      session.setAttribute("_USER",account); 
	      
	      //在session中添加一个登录成功的标记
	      session.setAttribute("_LOGIN","_SUCCESS");     
	      
	      //转到聊天室页面
	      response.sendRedirect("../chatroom.jsp");      
	
	    }
	    else if(loginMsg.equals("FAIL_LOGIN"))
	    {
			out.println("你输入的用户名或密码错误，请检正后重新输入,将在3秒后跳转到登录页面");
			out.println("<a href=login.jsp>点击立即跳转到登录页面</a>");
			//清除session中保存的登录信息
			if(session.getAttribute("_LOGIN")!=null)
			{
				session.invalidate();
			}
			//三秒后跳转到登录页面
			response.setHeader("refresh","3;url='../login.jsp'");
		}
		else if(loginMsg.equals("ALREADY_LOGIN"))
		{
			out.println("您的账户已经登录，可能是由于非法退出或者在其他设备登录您可以");
			out.println("等待1分钟后重新登录或者试试<a href=resetpwd.jsp>找回密码</a>");
			//清除session中保存的登录信息
			if(session.getAttribute("_LOGIN")!=null)
			{
				session.invalidate();
			}
		}else if(loginMsg.equals("NO_ACCOUNT"))
        {
            out.println("账户不存在,请检正后重新输入,将在3秒后跳转到登录页面");
            out.println("<a href=login.jsp>点击立即跳转到登录页面</a>");
            //清除session中保存的登录信息
            if(session.getAttribute("_LOGIN")!=null)
            {
                session.invalidate();
            }
            //三秒后跳转到登录页面
            response.setHeader("refresh","3;url='../login.jsp'");
        }
	}
  %>
  </body>
</html>
