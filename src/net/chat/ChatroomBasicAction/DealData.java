package net.chat.ChatroomBasicAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;
import net.chat.Constant.Constant;

public class DealData {
	/**
	 * ��ȡ�����û��б�
	 * @return String
	 *         	����һ���ַ���������ʾ�����û�
	 *
	 * */	
	public String GetOnlineUsers()
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from userInfo where logged='1'";
			
			//�����ݿ��в�ѯ���û����Ƿ������ݿ����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
		return sb.toString();
	}
	
	/**
	 * �����ݿ����������Ϣ
	 * @param String content
	 *                  �û����͵�������Ϣ����
	 * @param String msgFrom
	 *                  ��Ϣ�����ߵ��˺�
	 * @param String msgTo
	 *                  ��Ϣ�����ߵ��˺�
	 * @return String
	 *         	����һ���ַ���������ɹ������Ϣ�����ݿ�
     *                    	�����ַ��� True
     *                         	����
     *                   	�����ַ��� False
	 *
	 * */
	public String addContent(String content,String msgFrom,String msgTo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//����һ����Ԥ�����SQL���
			String sql="insert into msginfo(msgFrom,msgTo,content,sendtime) values (?,?,?,?)";
			
			//����һ��Ԥ����SQL����
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
			//����ǰ̨�����msgtoֻ��Ϊ���ֻ��߿գ�����ֻ��Ҫ����գ�0�����������˵��
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
			//�������ɹ�������һ���յ�Ӱ��
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ��ȡ��������������ʾ��������Ϣ
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ���ַ���������ʾ������Ϣ
	 *
	 * */	
	public String AllChatList(String account)
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//����һ����Ԥ�����SQL���
			//ֻ��ʾ���µ�20�������¼
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
					 sb.append("<font size=4 color='grey'>"+time+"   �˺�Ϊ"+msgfrom+"������˵:</font><br>"+content+"<br>");
				 }
				 else {
					 if(account.equals(msgfrom))
					 {
						 sb.append("<font size=4 color='grey'>"+time+"  "+"��"+msgto+"���Ļ�:</font><br>"+content+"<br>");
					 }
					 else if(account.equals(msgto))
					 {
						 sb.append("<font size=4 color='grey'>"+time+"  �˺�Ϊ"+msgfrom+"�����Ѷ���˵���Ļ�:</font><br>"+content+"<br>");
					 }
				}
			}
			//������ռλ����ԭΪ����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * �û����߼��
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ���ַ���������û�����
     *                    	�����ַ��� online
     *                         	����
     *                   	�����ַ��� offline
	 *
	 * */	
	public String checkOnline(String account) 
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//����һ����Ԥ�����SQL���
			String sql="select * from userinfo where account=?";
			//����һ��Ԥ����SQL����
			PreparedStatement ps = conn.preparedStatement(sql);
			
			int accountnum=Integer.parseInt(account);
			if(accountnum>0)
			{
				ps.setInt(1, accountnum);
			}
			else {
				return "offline";//����������account
			}
			
			ResultSet rs=ps.executeQuery();
			String loginState="0";
			while(rs.next())//�����������������ֻ��һ�������û�о���Ϊ�ǲ�����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
}
