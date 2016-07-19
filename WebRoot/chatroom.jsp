<%@page import="net.chat.util.ReadFile"%>
<%@page import="net.chat.Constant.Constant"%>
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
	<link href="css/jplayer.blue.monday.css" rel="stylesheet" type="text/css" />
  <%@ include file="service/chkSession.jsp"%>

	<script language="javascript" type="text/javascript" src="javascript/jquery-1.8.0.min.js"></script>
	<script language="javascript" type="text/javascript" src="javascript/chatroom.js" charset="utf-8"></script>
	<script language="javascript" type="text/javascript" src="javascript/checkonline.js" charset="utf-8"></script>
	<script type="text/javascript" src="javascript/jquery.jplayer.min.js"></script>
  </head>
  
  <body>
  <div id="divMain">
  			<div class="divmenu">
  			<div class="divuserinfo">
  				<div class="divMusic">
					<div id="jquery_jplayer_1" class="jp-jplayer"></div>
					<div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
						<div class="jp-type-single">
							<div class="jp-gui jp-interface">
								<div class="jp-controls">
								<button class="jp-play" role="button" tabindex="0">play</button>
								<button class="jp-stop" role="button" tabindex="0">stop</button>
								</div>
								<div class="jp-progress">
									<div class="jp-seek-bar">
										<div class="jp-play-bar"></div>
									</div>
								</div>
							<div class="jp-volume-controls">
							<button class="jp-mute" role="button" tabindex="0">mute</button>
							<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
								<div class="jp-volume-bar">
									<div class="jp-volume-bar-value"></div>
								</div>
							</div>
							<div class="jp-time-holder">
								<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
								<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
								<div class="jp-toggles">
									<button class="jp-repeat" role="button" tabindex="0">repeat</button>
								</div>
							</div>
						</div>
						<div class="jp-no-solution">
							<span>Update Required</span>
							To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
						</div>
					</div>
					<div id="musicList">
					<select id="selectmusic" style="width:380px;background:#eee;border:0px">
		 			<%
		 						
                                ArrayList<String> list=new ArrayList<String>();
                                //list.add("4 Real");
                                //list.add("As Long As You Love Me");
                                ReadFile.readfile(Constant.LOCAL_MUSIC_PATH, list);
    
                                if(list!=null)
                                for(int i=0;i<list.size();i++){    
                                    String p=list.get(i);
                                    
                               %>
                     <option value="<%=Constant.MUSIC_PATH+p+".mp3" %>"><%=p %></option>
                     <%} %>
					</select>
					<a href="javascript:sharemusic()">分享</a>
					</div>
				</div>
				</div>			
  			</div>
  			<div class="divmanager">			
  				<div class="roommanager">
	  			<a href="updateuserinfo.jsp" target='_blank'>修改个人资料</a>
	  			&nbsp;<a href="changepwd.jsp">修改密码</a>
	  			&nbsp;<a href="sharingfiles.jsp" target='_blank'>共享文件</a>&nbsp;
	  			<a href="chatrecord.jsp?curPage=1" target='_blank'>查看聊天记录</a>&nbsp;
	  			<a href="advanced.jsp" target='_blank'>用户管理</a>&nbsp;
	  			<input id="Button2" type="button" value="退出" class="btn" />
	  			</div>
	  			<div class="roomlogo"><img src="./images/logo.png" title="logo" /></div>
  			</div>
  			</div>
  			
        	<div class="divtop">
        		<div class="divL">
        			<div class="divShow" id="divContent"></div>
        		</div>
        		<div class="divR">
        			<div class="divOnline" id="divOnline"></div>
        		</div>
        	</div>
        	
        	
        	<div class="divBot">
        		<table>
        			<tr><td><input id="send" type="button" value="发送图片" class="btn"/>&nbsp;&nbsp;&nbsp;<input id="sendfile" type="button" value="发送文件" class="btn"/></td></tr>
        			<tr><td colspan="2" id="divFace" class="pb"></td></tr>
        			<tr>
        				<td>
        					<textarea id="txtContent" class="txt" rows="3" cols="64" maxlength="200" style="resize:none;"></textarea>
        				</td>
        				<td width="20"></td>
						<td>
        					<input id="Button1" type="button" value="发送" class="btn" />
        				</td>  
        				<td width="20"></td>
        				<td width="140"><font size="3">悄悄话对象账号：</font><input type="text" id="txtMsgTo" size="15" maxlength="15"></td>
        				<td width="20"></td> 

        				
        			</tr>
        			    
        		</table>
        	</div>
            

            <span id="spnMsg" class="clsTip">正在发送数据...</span>
        </div>
  </body>
</html>
