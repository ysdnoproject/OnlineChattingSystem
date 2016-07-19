<%@page import="java.net.SocketException"%>
<%@page import="sun.org.mozilla.javascript.internal.JavaScriptException"%>
<%@ page language="java" import="java.util.*,com.jspsmart.upload.*" pageEncoding="utf-8"%>
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
	 <%@ include file="chkSession.jsp"%>
	 <%
	 response.reset();//如果在weblogic底下同样要加上此句
	  // 新建一个SmartUpload对象
	 SmartUpload su = new SmartUpload();
	  // 初始化 
	 su.initialize(pageContext);
	 //获取fileUrl参数
     String fileUrl = request.getParameter("fileUrl");
     //由于都是超链接传递的参数，所以 
     //request.setCharacterEncoding("utf-8")并没有作用（这个只针对post请求才有效）
     //所以将字符转码，自己转换成utf-8
     fileUrl=new String(fileUrl.getBytes("ISO-8859-1"),"UTF-8");

	  // 设定contentDisposition为null以禁止浏览器自动打开文件，
	  //保证点击链接后是下载文件。若不设定，则下载的文件扩展名为
	  //doc时，浏览器将自动用word打开它。扩展名为pdf时，
	  //浏览器将用acrobat打开。
	 su.setContentDisposition(null);
	  // 下载文件
	 try{
	  		su.downloadFile(fileUrl,"utf-8");
	  		//必须调用者两行，否则会报getOutputStream() has already been called for this response异常
	  		out.clear();//清除上面代码生成的out对象，因为其调用了getOutputStream()
	  		//重新将out对象赋值，pushBody保存了当前对象并且更新pagecontext中page范围内的out对象
	  		//此新对象没有调用getOutputStream()所以不会报错
			out = pageContext.pushBody();
	    }
	    catch(java.io.FileNotFoundException e){
	 
		out.println("<script Language='javascript'>");
	 	out.println("alert('filenotfound');");
	 	out.println("window.location.href='sharingfiles.jsp';");
		out.println("</script>");
	}catch(Exception e1)
	{
	}
	%>
  </head>
  
  <body>
   
  </body>
</html>
