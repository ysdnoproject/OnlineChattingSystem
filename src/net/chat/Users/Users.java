package net.chat.Users;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;
import net.chat.BasicAction.Logout;

public class Users {
	/**
	 * 获取所有用户
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个String：如果是admin（管理员）
	 *                         返回所有用户列表
	 *                             否则
	 *                         返回 没有权限(no-1548as79da561)
	 *
	 * */	
	public String getAllUsers(String account) 
			throws SQLException,ClassNotFoundException{
		if(!User.isAdmin(account))
		{
			return "没有权限(no-1548as79da561)";
		}
		else {
			StringBuffer sb=new StringBuffer();
			BaseConn conn = null;
			try
			{
				conn = new BaseConn();
				
				//创建一个用预处理的SQL语句
				String sql = "select * from userInfo";
				
				sb.append("<table>");
				//从数据库中查询该用户名是否在数据库存在
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
				conn.closeDB(); //关闭数据库连接，释放JDBC资源
			}	
		}
	}
	
	/**
	 * 获取在线用户
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个String：如果是admin（管理员）
	 *                         返回在线用户列表
	 *                             否则
	 *                         返回 没有权限(no-1548as79da561)
	 *
	 * */	
	public String getOnlineUsers(String account)
			throws SQLException,ClassNotFoundException{
		if(!User.isAdmin(account))
		{
			return "没有权限(no-1548as79da561)";
		}
		else {
			StringBuffer sb=new StringBuffer();
			BaseConn conn = null;
			try
			{
				conn = new BaseConn();
				
				//创建一个用预处理的SQL语句
				String sql = "select * from userInfo where logged='1'";
				
				sb.append("<table>");
				//从数据库中查询该用户名是否在数据库存在
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
				conn.closeDB(); //关闭数据库连接，释放JDBC资源
			}	
		}	
	}
	
	/**
	 * 踢出用户
	 * @param String account
	 *                  用户账号
	 * @param String kickUsers
	 *                  被踢用户账号
	 * @return String
	 *         	返回一个String：如果成功提出
	 *                         返回 success
	 *                             否则
	 *                         返回 fail
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
	 * 删除用户
	 * @param String account
	 *                  用户账号
	 * @param String deleteUsers
	 *                  被删除的用户账号
	 * @return String
	 *         	返回一个String：如果是成功删除
	 *                         返回 success
	 *                             否则
	 *                         返回 fail
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
