package net.chat.ChatroomBasicAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;
import net.chat.Constant.Constant;

public class DealData {
	/**
	 * 获取在线用户列表
	 * @return String
	 *         	返回一个字符串用来显示在线用户
	 *
	 * */	
	public String GetOnlineUsers()
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userInfo where logged='1'";
			
			//从数据库中查询该用户名是否在数据库存在
			ResultSet rs =conn.executeQuery(sql);
			while(rs.next())
			{				
				sb.append("<a href=viewuserinfo.jsp?account="+rs.getString("account")+" target='_blank'>"
			+"<font size=5px>"+rs.getString("account")+":"+rs.getString("nickname")+"</font></a><br>");
				//sb.append(rs.getString("account")+":"+rs.getString("nickname")+"<br>");
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			conn.closeDB(); //关闭数据库连接，释放JDBC资源
		}
		return sb.toString();
	}
	
	/**
	 * 向数据库添加聊天信息
	 * @param String content
	 *                  用户发送的聊天信息内容
	 * @param String msgFrom
	 *                  信息发送者的账号
	 * @param String msgTo
	 *                  信息接收者的账号
	 * @return String
	 *         	返回一个字符串：如果成功添加信息到数据库
     *                    	返回字符串 True
     *                         	否则
     *                   	返回字符串 False
	 *
	 * */
	public String addContent(String content,String msgFrom,String msgTo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//创建一个用预处理的SQL语句
			String sql="insert into msginfo(msgFrom,msgTo,content,sendtime) values (?,?,?,?)";
			
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			
			int msgFromaccount=Integer.parseInt(msgFrom);
			
			
			if(msgFromaccount>0)
			{
				ps.setInt(1, msgFromaccount);
			}
			else {
				return "False";
			}
			
			int msgToaccount=0;
			//由于前台检查了msgto只能为数字或者空，所以只需要处理空，0代表对所有人说话
			if(msgTo==null || msgTo.equals(""))
			{
				msgToaccount=0;
			}
			else {
				msgToaccount=Integer.parseInt(msgTo);
			}	
			
			if(msgToaccount>=0)
			{
				ps.setInt(2, msgToaccount);
			}
			else {
				return "False";
			}
			ps.setString(3, content);
			
			
			SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String time = cal.format(new java.util.Date());
		    
		    ps.setString(4, time);
			int temp=conn.executeUpdate();
			//如果插入成功，即有一行收到影响
		    if(temp==1)
		    {
		    	return "True";
		    }
		    else
		    {
		    	return "False";
		    }			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			conn.closeDB(); //关闭数据库连接，释放JDBC资源
		}
	}
	
	/**
	 * 获取聊天室内用来显示的聊天信息
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个字符串用来显示聊天信息
	 *
	 * */	
	public String AllChatList(String account)
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//创建一个用预处理的SQL语句
			//只显示最新的20条聊天记录
			String sql="select * from (select * from msginfo order by sendtime DESC limit 20) as A order by sendtime";
			
			ResultSet rs = conn.executeQuery(sql);
			String msgfrom="msgfrom";
			String msgto="msgto";
			String time="";
			String content="";
			
			
			while(rs.next())
			{
				 msgfrom=rs.getString("msgFrom");
				 msgto=rs.getString("msgTo");
				 time=rs.getString("sendtime");
				 time=time.substring(0, time.length()-2);
				 content=rs.getString("content");
				 if(msgto.equals("0"))
				 {
					 sb.append("<font size=4 color='grey'>"+time+"   账号为"+msgfrom+"的朋友说:</font><br>"+content+"<br>");
				 }
				 else {
					 if(account.equals(msgfrom))
					 {
						 sb.append("<font size=4 color='grey'>"+time+"  "+"对"+msgto+"悄悄话:</font><br>"+content+"<br>");
					 }
					 else if(account.equals(msgto))
					 {
						 sb.append("<font size=4 color='grey'>"+time+"  账号为"+msgfrom+"的朋友对你说悄悄话:</font><br>"+content+"<br>");
					 }
				}
			}
			//将表情占位符还原为表情
			String str=sb.toString();
			for(int i=1;i<=Constant.Facecount;i++)
			{
				str=str.replaceAll("<:"+i+":>", "<img src='face/"+i+".gif' id='"+i+"'/>");
			}
			return str;
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			conn.closeDB(); //关闭数据库连接，释放JDBC资源
		}
	}
	
	/**
	 * 用户在线监测
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个字符串：如果用户在线
     *                    	返回字符串 online
     *                         	否则
     *                   	返回字符串 offline
	 *
	 * */	
	public String checkOnline(String account) 
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//创建一个用预处理的SQL语句
			String sql="select * from userinfo where account=?";
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			
			int accountnum=Integer.parseInt(account);
			if(accountnum>0)
			{
				ps.setInt(1, accountnum);
			}
			else {
				return "offline";//不是正常的account
			}
			
			ResultSet rs=ps.executeQuery();
			String loginState="0";
			while(rs.next())//由于是主键所以最多只有一个，如果没有就认为是不在线
			{
				loginState=rs.getString("logged");
			}
			if(loginState.equals("0"))
			{
				return "offline";
			}
			else {
				return "online";
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			throw ex;
		}catch(ClassNotFoundException ex)
		{
			ex.printStackTrace();
			throw ex;
		}
		finally
		{
			conn.closeDB(); //关闭数据库连接，释放JDBC资源
		}
	}
}
