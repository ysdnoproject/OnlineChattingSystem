package net.chat.BasicAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;


public class Register {
	/**
	 * 用户注册
	 * @param String nickname
	 *                   用户昵称
	 * @param String password
	 *                   用户密码
	 * @param String email
	 *                   用户邮箱
	 * @param String question
	 *                    用户密保问题
	 * @param String answer
	 *                    用户密保问题答案
	 * @return integer
	 *         	返回一个整数：如果注册成功
     *                    	返回系统为用户自动生成的账号
     *                         	否则
     *                   	返回-1
	 *
	 * */	
	public int register(String nickName,String password,String email,String question,String answer)
			throws SQLException,ClassNotFoundException
	{
		BaseConn conn = null;
		int account=0;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select max(account) from userInfo";
			
			//从数据库中查询当前最大的账号
			ResultSet rs =conn.executeQuery(sql);
			if(rs.next())//如果存在最大的账号
			{
				account=rs.getInt(1)+1;
			}
			else
			{
				account=1;
			}
			
			sql="insert into userInfo(account,nickName,userPassword,lastlogintime,role,email,question,answer,logged) values (?,?,?,?,?,?,?,?,?)";
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			
			SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String time = cal.format(new java.util.Date());
		    
		    ps.setInt(1, account);
		    ps.setString(2, nickName);
		    ps.setString(3, password);
		    ps.setString(4, time);
		    ps.setString(5, "member");
		    ps.setString(6, email);
		    ps.setString(7, question);
		    ps.setString(8, answer);
		    ps.setString(9, "0");
		    
		    int temp=conn.executeUpdate();
		    //如果插入成功，即有一行收到影响
		    if(temp==1)
		    {
		    	return account;
		    }
		    else
		    {
		    	return -1;
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
