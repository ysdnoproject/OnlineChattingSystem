        //获取所有用户
        function GetAllUsers(){
     		$.ajax({
     			type:"POST",
     			url:"service/getUsers.jsp",
     			data:"action=All&d="+new Date(),
     			success:function(data){
     				if(data.indexOf("没有权限(no-1548as79da561)")!=-1)
     				{
     					alert("没有权限");
     				}
     				else
     				{
     					$("#divContent").html(data);
     				}
     			}
     		});
       	}
       	
        //获取在线用户
       	function GetOnlineUsers(){
       	   		$.ajax({
     			type:"POST",
     			url:"service/getUsers.jsp",
     			data:"action=online&d="+new Date(),
     			success:function(data){
     				if(data.indexOf("没有权限(no-1548as79da561)")!=-1)
     				{
     					alert("没有权限");
     				}
     				else
     				{
     					$("#divContent").html(data);
     				}
     				
     			}
     		});
       	}
       	
       	//踢出用户+
       	function KickUsers(){
       	//获得所有被选中的用户账号
       	var checked = [];
       	$('input:checkbox:checked').each(function() {
            checked.push($(this).val());
        });
        //alert(checked);
        $.ajax({
     			type:"POST",
     			url:"service/userManagement.jsp",
     			data:"action=kick&d="+new Date()+"&kickUsers="+checked,
     			success:function(data){
	     			if(data.indexOf("success")!=-1)
	     			{
	     				alert("成功踢出");
	     			}
	     			else
	     			{
	     				alert("踢出用户失败");
	     			}
     			}
     		});
       	}
       	
       	//删除用户确认
       	function confirmDeleteUsers()
       	{
	       	if(confirm("真的要删除这些用户吗？"))
	       	{
	       		//删除用户
	       		DeleteUsers();
	       	}
       	}
       	//删除用户
       	function DeleteUsers(){
       	//获得所有被选中的用户账号
       	var checked = [];
       	$('input:checkbox:checked').each(function() {
            checked.push($(this).val());
        });
        //alert(checked);
        $.ajax({
     			type:"POST",
     			url:"service/userManagement.jsp",
     			data:"action=delete&d="+new Date()+"&deleteUsers="+checked,
     			success:function(data){
	     			if(data.indexOf("success")!=-1)
	     			{
	     				alert("成功删除");
	     			}
	     			else
	     			{
	     				alert("删除用户失败");
	     			}
     			}
     		});
       	}