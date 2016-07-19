//点击关闭按钮之后更新登录状态ok
	/*由于许多浏览器不支持这个函数，现在采用ajax来检查是否下线
	window.onbeforeunload  =  function()   
	{
		if(event.clientX>360&&event.clientY<0||event.altKey)
		{
			alert("离开了聊天室");
			//初始化account
			var account=0;
			logout(account);
		}
    }*/
    
   	
	//到底部去
   	function goToBottom(){
   		div=document.getElementById("divContent");
		div.scrollTop = div.scrollHeight;
   	}
   	
	//音乐分享
	function sharemusic()
	{
		//悄悄话对象验证必须为数字或者空
   		var myreg = /^([0-9]+)*$/;
   		if(!myreg.test($("#txtMsgTo").val()))
   		{
   			alert("请输入正确的分享对象账号（0或不填为全体）");
   			$("txtMsgTo").focus();
   		}
   		else
   		{
			$.ajax({
	   			type:"POST",
	   			url:"service/sharemusic.jsp",
	   			data:"musicUrl="+$("#selectmusic").children('option:selected').val()+"&msgTo="+$("#txtMsgTo").val(),
	   			success:function(data){
	   				goToBottom();
	   			}
	    	});
   		}
	}
	
	//收听分享的音乐
	function listensharedmuisc(music)
	{
		$("#jquery_jplayer_1").jPlayer("stop").jPlayer("setMedia", {mp3:music}).jPlayer("play");
		$("#selectmusic").val(music);
		goToBottom();
	}
	
    //下线
    function logout(account)
    {
    	$.ajax({
   			type:"POST",
   			url:"service/logout.jsp",
   			success:function(data){
   			}
    	});
    }
		
    //页面载入时的工作
	$(function(){
	//键盘监听回车键
	$(document).keypress(function(e){
		if(e.which==13){
    		var $content=$("#txtContent");
       		if($content.val()!=""){
       			SendContent($content.val(),$("#txtMsgTo").val());
       		}else{
       			alert("发送内容不能为空！");
       			return false;
       		}
		}
	});
   	//读取消息
   	GetMessageList();
   	//读取在线用户
   	GetOnlineUsers();
   	//初始化表情
   	InitFaces();
   	goToBottom();
   	//自动刷新
   	AutoRefresh();
   	//图片发送响应
	$("#send").click(function(){
		window .open("sendPicture.jsp","child","height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
	});
	
	//文件发送响应
	$("#sendfile").click(function(){
		window .open("sendfile.jsp","child","height=200,width=400,status=yes,toolbar=no,menubar=no,location=no");
	});
	
   	//发送按钮事件响应
   	$("#Button1").click(function(){
   		var $content=$("#txtContent");
   		if($content.val()!=""){
   			SendContent($content.val(),$("#txtMsgTo").val());
   			goToBottom();
   		}else{
   			alert("发送内容不能为空！");
   			return false;
   		}
   	});
   	//下线按钮事件响应
   	$("#Button2").click(function(){
   		if(confirm("确认下线？")){
   			var account=0;
   			logout(account);
   			window.location.href="index.jsp";
   		}
   	});
   	//发送消息
   	function SendContent(content,msgTo){
   		if(content.length>200)
   		{
   			alert("请不要输入超过200个字符");
   		}
   		else
   		{
	   		//悄悄话对象验证必须为数字或者空
	   		var myreg = /^([0-9]+)*$/;
	   		if(!myreg.test($("#txtMsgTo").val()))
	   		{
	   			alert("请输入正确的悄悄话对象账号");
	   			$("txtMsgTo").focus();
	   		}
	   		else
	   		{
	       		$.ajax({
	       			type:"POST",
	       			url:"service/DealData.jsp",
	       			data:"action=SendContent&d="+new Date()+"&content="+content+"&msgTo="+msgTo,
	       			success:function(data){
	       				if(data.indexOf("True") !=-1){
	       					//获取消息
	       					GetMessageList();
	       					//清空发送框
	       					$("#txtContent").val("");
	       					
	       					goToBottom();
	       					//alert('shit for SendMessage');
	       				}else{
	       					alert("发送失败");
	       				}
	       			}
	       		});
	   		}
   		}
   	}
   	//获取消息
   	function GetMessageList(){
   		$.ajax({
   			type:"POST",
   			url:"service/DealData.jsp",
   			data:"action=ChatList&d="+new Date(),
   			success:function(data){
   				if(data.indexOf("unLogin")!=-1){
   					alert("非法进入，请先登录！");
   					window.location.href="index.jsp";
   				}else{
   					$("#divContent").html(data);
   					//goToBottom();
   				}
   				//alert('shit for GetMessage');
   			}
   		});
   	}
   	//获取在线用户列表
   	function GetOnlineUsers(){
   		$.ajax({
   			type:"POST",
   			url:"service/DealData.jsp",
   			data:"action=onLineList&d="+new Date(),
   			success:function(data){
   				$("#divOnline").html(data);
   			}
   		});
   	}

   	//初始化表情
   	function InitFaces(){
   		var strHTML="";
   		for(var i=1;i<=7;i++){
   			strHTML+="<img src='face/"+i+".gif' id='"+i+"'/>";
   		}
   		$("#divFace").html(strHTML);
   	}
   	//表情图片监听
   	$("table tbody tr td img").click(function(){
   		var $txtContent=$("#txtContent");
   		if($txtContent.val()!=undefined){
   			var strContent=$txtContent.val()+"<:"+this.id+":>";
   		}else{
   			var strContent="<:"+this.id+":>";
   		} 
           $("#txtContent").val(strContent);
       });
   	
   	//自动更新页面信息
   	function AutoRefresh(){
   		setInterval(GetMessageList,1000);
   		setInterval(GetOnlineUsers,4000);
   	}
    //音乐选择框事件
 	$("#selectmusic").change(function(){
			var music=$(this).children('option:selected').val();
			$("#jquery_jplayer_1").jPlayer("stop").jPlayer("setMedia", {mp3:music}).jPlayer("play");
		});
 	//初始化音乐播放器
 	var stream = {
 			mp3: $("#selectmusic").children('option:selected').val()
 		},
 		ready = false;

 		$("#jquery_jplayer_1").jPlayer({
 			ready: function (event) {
 				ready = true;
 				$(this).jPlayer("setMedia", stream);
 			},
 			pause: function() {
 				$(this).jPlayer("pause");
 			},
 			error: function(event) {
 				if(ready && event.jPlayer.error.type === $.jPlayer.error.URL_NOT_SET) {
 					$(this).jPlayer("setMedia", stream).jPlayer("play");
 				}
 			},
 			swfPath: "javascript",
 			//设置支持的格式mp3
 			supplied: "mp3",
 			//自动预载入
 			preload: "auto",
 			//设置Flash 的wmode，由于是mp3所以必须为window
 			wmode: "window",
 			//让暂停键可用，不设置就会不可用
 			useStateClassSkin: true,
 			//允许键盘操作
 			keyEnabled: true
 		});	
	});