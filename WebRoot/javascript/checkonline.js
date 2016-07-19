	//用户在线监测
   	function CheckOnline(){
   		$.ajax({
   			type:"POST",
   			url:"service/DealData.jsp",
   			data:"action=CheckOnline&d="+new Date(),
   			success:function(data){
   				if(data.indexOf("offline")>0)
   				{
   					alert("您已经被踢出");  
					window.location.href="index.jsp";  
   				}
   				//alert('shit for GetOnlineUsers');
   			}
   		});
   	}
   	
   	//设置10秒自动检测一次
   	setInterval(CheckOnline,10000);