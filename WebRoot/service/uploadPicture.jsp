<%@page import="net.chat.Constant.Constant"%>
<%@page import="java.util.regex.Pattern"%>
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
			//这个处理文件没有对图片进行压缩
			SmartUpload smartUpload = new SmartUpload();
			smartUpload.initialize(pageContext);
			smartUpload.upload("utf-8");
			smartUpload.getRequest().getParameter("file1");
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			
			String ext = null;
			float file_size = 0f;
			long file_size_max =  50*1024*1024;
			file_size = myFile.getSize();
			
			if(myFile.isMissing())
			{
				out.println("请选择发送的文件");
			}
			else
			{
			
				String myFileName = myFile.getFileName();//取得上载的文件的文件名
	
				ext = myFile.getFileExt();//取得后缀名
				if (!(ext.equals("img") || ext.equals("bmp") || ext.equals("jpg") || ext.equals("jpeg") || ext.equals("png") || ext.equals("gif"))) {
					out.println("只支持img,bmp,jpg,jpeg,png,gif图片");
				}
				else
				{
					String saveUrl = Constant.IMG_PATH;//文件保存路径

					if (file_size < file_size_max) {
						//更改文件名，取得当前上传时间的毫秒数值
						Calendar calendar = Calendar.getInstance();
						String fileName = String.valueOf(calendar.getTimeInMillis());//设置新的文件名
						saveUrl += fileName + "." + ext;
	
						myFile.saveAs(saveUrl, smartUpload.SAVE_VIRTUAL);
						
						//存储记录到数据库
						String content="<a href='"+Constant.IMG_PATH+fileName + "." + ext+"' target='_blank'>"+"<img src='"+Constant.IMG_PATH+fileName + "." + ext+"' width='200'/></a>";
						String msgFrom=session.getAttribute("_USER").toString();
						String msgTo=smartUpload.getRequest().getParameter("msgTo");
						Pattern pattern=Pattern.compile("^([0-9]+)*$");
						if(!pattern.matcher(msgTo).matches())
						{
							msgTo="0";
						}
						if(dd.addContent(content,msgFrom,msgTo).equals("True"))
						{	
							out.println("<img src='uploadfile/images/"+fileName + "." + ext+"'/>");
							out.println("<script language='JavaScript'>");
		   					out.println("function closepage(){");
		      				out.println("window.opener=null;");
		      			 	out.println("window.open('','_self');");
		      				out.println("window.close();}");
		      				out.println("</script>"); 
	      				}
	      				else
	      				{
	      					out.println("发生错误，请重新发送");
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
