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
	<script language="javascript" type="text/javascript" src="javascript/checkonline.js" charset="utf-8"></script>
  </head>
  
  <body>
     <div>
      <form action="service/uploadsharingfile.jsp" method="post" enctype="multipart/form-data"
        name="form1">
        <table align="center">
          <tr>
            <td>
              	请选择上传文件(请不要超过50MB):
              <input type="file" name="file1" />
            </td>
          </tr>
          <tr>
            <td>
                            请添加相应的描述（不要超过100字节）:
              <input type="text" name="memo" maxlength=100 />
            </td>
          </tr>
          <tr>
            <td>
              <input type="submit" name="submit" value="发送" />
            </td>
          </tr>
        </table>
      </form>
    </div>
  </body>
</html>
