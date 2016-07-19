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
	<%@ include file="chkSession.jsp"%>
	<%request.setCharacterEncoding("utf-8"); %>
	<jsp:useBean id="myfile" class="net.chat.SharedFile.MyFile" />
	 <%
    	String account=session.getAttribute("_USER").toString();
    	String fileUrl=request.getParameter("fileUrl");
    	//由于都是超链接传递的参数，所以 
		//request.setCharacterEncoding("utf-8")并没有作用（这个只针对post请求才有效）
		//所以将字符转码，自己转换成utf-8
		fileUrl=new String(fileUrl.getBytes("ISO-8859-1"),"UTF-8");
    	if(myfile.deleteFile(account, fileUrl))
    	{
    		out.println("<script language='JavaScript'>");
			out.println("function closepage(){");
			out.println("alert('删除成功');");
 			out.println("window.location.href='sharingfiles.jsp';}");
			//out.println("window.open('','_self');");
			//out.println("window.close();}");
			out.println("</script>);"); 
		}
		else
		{
			out.println("发生错误，请重新删除该文件");
		}	
    %>
  </head>
  
  <body onload="closepage()">
  </body >
</html>
