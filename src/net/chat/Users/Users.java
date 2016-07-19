package net.chat.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;
import net.chat.BasicAction.Logout;

public class Users {
	/**
	 * ��ȡ�����û�
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ��String�������admin������Ա��
	 *                         ���������û��б�
	 *                             ����
	 *                         ���� û��Ȩ��(no-1548as79da561)
	 *
	 * */	
	public String getAllUsers(String account) 
			throws SQLException,ClassNotFoundException{
		if(!User.isAdmin(account))
		{
			return "û��Ȩ��(no-1548as79da561)";
		}
		else {
			StringBuffer sb=new StringBuffer();
			BaseConn conn = null;
			try
			{
				conn = new BaseConn();
				
				//����һ����Ԥ�����SQL���
				String sql = "select * from userInfo";
				
				sb.append("<table>");
				//�����ݿ��в�ѯ���û����Ƿ������ݿ����
				ResultSet rs =conn.executeQuery(sql);
				while(rs.next())
				{				
					sb.append("<tr><td width='150'>"+"<input type='checkbox' id='users' name='users' value='"+rs.getString("account")+"'>"+rs.getString("account")
							+"</td>"+"<td width='200'>"+rs.getString("nickname")+"</td>"+"<td>"
							+rs.getString("lastlogintime")+"</td></tr>");
				}
				sb.append("</table>");
				return sb.toString();
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
	
	/**
	 * ��ȡ�����û�
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ��String�������admin������Ա��
	 *                         ���������û��б�
	 *                             ����
	 *                         ���� û��Ȩ��(no-1548as79da561)
	 *
	 * */	
	public String getOnlineUsers(String account)
			throws SQLException,ClassNotFoundException{
		if(!User.isAdmin(account))
		{
			return "û��Ȩ��(no-1548as79da561)";
		}
		else {
			StringBuffer sb=new StringBuffer();
			BaseConn conn = null;
			try
			{
				conn = new BaseConn();
				
				//����һ����Ԥ�����SQL���
				String sql = "select * from userInfo where logged='1'";
				
				sb.append("<table>");
				//�����ݿ��в�ѯ���û����Ƿ������ݿ����
				ResultSet rs =conn.executeQuery(sql);
				while(rs.next())
				{				
					sb.append("<tr><td width='150'>"+"<input type='checkbox' id='users' name='users' value='"+rs.getString("account")+"'>"+rs.getString("account")
							+"</td>"+"<td width='200'>"+rs.getString("nickname")+"</td>"+"<td>"
							+rs.getString("lastlogintime")+"</td></tr>");
				}
				sb.append("</table>");
				return sb.toString();
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
	
	/**
	 * �߳��û�
	 * @param String account
	 *                  �û��˺�
	 * @param String kickUsers
	 *                  �����û��˺�
	 * @return String
	 *         	����һ��String������ɹ����
	 *                         ���� success
	 *                             ����
	 *                         ���� fail
	 *
	 * */	
	public String kickUsers(String account,String kickUsers)
			throws SQLException,ClassNotFoundException{
		String[] kicks=kickUsers.split(",");
//		for (String string : kicks) {
//			System.out.println(string);
//		}
		Logout lg=new Logout();
		if(!User.isAdmin(account))
		{
			return "fail";
		}
		else {
			for (String string : kicks) {
				lg.logout(string);
			}
			return "success";
		}	
	}
	
	/**
	 * ɾ���û�
	 * @param String account
	 *                  �û��˺�
	 * @param String deleteUsers
	 *                  ��ɾ�����û��˺�
	 * @return String
	 *         	����һ��String������ǳɹ�ɾ��
	 *                         ���� success
	 *                             ����
	 *                         ���� fail
	 *
	 * */	
	public String deleteUsers(String account,String deleteUsers)
			throws SQLException,ClassNotFoundException{
		String[] deletes=deleteUsers.split(",");
		if(!User.isAdmin(account))
		{
			return "fail";
		}
		else {
			for (String string : deletes) {
				User.deleteUser(string);
			}
			return "success";
		}
	}
}
