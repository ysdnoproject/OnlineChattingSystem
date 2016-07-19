package net.chat.BasicAction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;

public class Logout {
	/**
	 * 用户登出
	 * @param String account
	 *                   用户的账户
	 * @return boolean
	 *         	返回一个Boolean：如果成功登出
     *                    	返回true
     *                         	否则
     *                   	返回false
	 *
	 * */	
	public boolean logout(String account) 
			throws SQLException,ClassNotFoundException
	{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "update userInfo set logged='0' where account=?";
			
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			ps.setString(1,account);
			//更改登录状态为0（未登录）
			conn.executeUpdate();
			return true;
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
