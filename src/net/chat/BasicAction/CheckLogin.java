package net.chat.BasicAction;

import java.sql.*;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;

public class CheckLogin {	
	/**
	 * 检测用户登录信息
	 * @param String account
	 *                   用户登录的账户
	 * @param String userPassword
	 *                   	用户登录的密码                  
	 * @return String
	 *         	返回一个字符串：如果用户名已经在数据库存在并且用户输入的密码也正确
     *                    	返回字符串 SUCCESS_LOGIN
	 *                        	如果用户已登录
     *                     	返回字符串 ALREADY_LOGIN
     *                        	如果账户不存在
     *                      返回字符串 NO_ACCOUNT
     *                         	否则
     *                   	返回字符串 FAIL_LOGIN
	 *
	 * */	
	public String checklogin(String account,String userPassword) 
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userInfo where account=?";
			
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			ps.setString(1,account);
			
			//从数据库中查询该用户名是否在数据库存在
			ResultSet rs = conn.executeQuery();
			if(rs.next())//如果存在
			{
				if(rs.getString("userPassword").equals(userPassword))
				{
					if(rs.getString("logged").equals("0"))
					{
						SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String time = cal.format(new java.util.Date());				    
					    //修改用户的最后登录时间
					    sql="update userInfo set lastLoginTime=?,logged='1' where account=?";
					    ps=conn.preparedStatement(sql);
					    ps.setString(1,time);
					    ps.setString(2,account);
					    conn.executeUpdate();
						return "SUCCESS_LOGIN";
					}
					else {
						return "ALREADY_LOGIN";
					}
				}
				else
					return "FAIL_LOGIN";
			}
			else
				return "NO_ACCOUNT";
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
