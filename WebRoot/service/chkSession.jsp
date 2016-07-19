<%@ page pageEncoding="UTF-8"%>
<%
//这个页面检测Session，判断用户是不是通过登录进入聊天室的，以防止非法访问
//如果用户不是通过登录进入聊天室，将给出提示信息。
//注意这里是用session.getAttibute("_LOGIN")，在登录后台处理页面(chklogin.jsp)中有
//session.setAttribute("_LOGIN","_SUCCESS");
if(session.isNew()==true||session.getAttribute("_LOGIN")==null||!session.getAttribute("_LOGIN").equals("_SUCCESS"))
{
  out.println("<p align=center><font size=18px>你还没有登录，请先登录后再访问本页面<br></p>");
  out.println("<p align=center><a href=login.jsp>点击登录</font></p>");
  return;
}
%>