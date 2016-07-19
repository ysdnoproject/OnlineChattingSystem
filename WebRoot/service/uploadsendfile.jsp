<%@page import="java.util.regex.Pattern"%>
<%@page import="net.chat.Constant.Constant"%>
<%@page import="com.sun.org.apache.xml.internal.resolver.helpers.FileURL"%>
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
	<jsp:useBean id="dd" class="net.chat.ChatroomBasicAction.DealData" /> 
<%
            try
            {
			SmartUpload smartUpload = new SmartUpload();
			smartUpload.initialize(pageContext);
			smartUpload.upload("utf-8");
			smartUpload.getRequest().getParameter("file1");
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			
			float file_size = 0f;
			long file_size_max = 50*1024*1024;
			file_size = myFile.getSize();
			
			if(myFile.isMissing())
			{
				out.println("请选择发送的文件");
			}
			else
			{
			
				String myFileName = myFile.getFileName();//取得上载的文件的文件名
				if(myFileName.length()>200)
				{
					out.println("文件名过长");
				}
				else{

					String saveUrl = Constant.SEND_FILE_PATH;//文件保存路径
					String fileUrl="";
					if (file_size < file_size_max) {
						//更改文件名，取得当前上传时间的毫秒数值
						Calendar calendar = Calendar.getInstance();
						String fileName = String.valueOf(calendar.getTimeInMillis())+myFileName;//设置新的文件名
						fileUrl+=fileName;
						saveUrl+=fileName;
	
						myFile.saveAs(saveUrl, smartUpload.SAVE_VIRTUAL);
						
						//存储记录到数据库
						String content="<a href='service/downloadfile.jsp?fileUrl="+Constant.SEND_FILE_PATH+fileUrl+"'>"+fileUrl+"</a>"+"<font color='red'>本网站不对此文件的安全性做任何保证，请谨慎下载</font>";
						String msgFrom=session.getAttribute("_USER").toString();
						String msgTo=smartUpload.getRequest().getParameter("msgTo");
						Pattern pattern=Pattern.compile("^([0-9]+)*$");
						if(!pattern.matcher(msgTo).matches())
						{
							msgTo="0";
						}
						
						if(dd.addContent(content,msgFrom,msgTo).equals("True"))
						{
							out.println("<script language='JavaScript'>");
		   					out.println("function closepage(){");
		   					out.println("alert('上传成功');");
			      			out.println("window.opener=null;");
		      			 	out.println("window.open('','_self');");
		      				out.println("window.close();}");
		      				out.println("</script>"); 
	      				}
	      				else
	      				{
	      					out.println("发生错误，请重新上传");
	      				}
					}
					else{
						out.println("文件太大了");
					}
				}
			}
			}catch(NegativeArraySizeException e)
            {
               out.println("文件太大了");
            }
		%>
	</head>
	<body onload="closepage()">
	</body>
</html>
