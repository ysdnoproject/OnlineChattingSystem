package net.chat.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;

public class User {
	/**
	 * 更新用户个人信息
	 * @param String account
	 *                  用户账号
	 * @param String nickname
	 *                  用户昵称
	 * @param String year
	 *                  用户出生年
	 * @param String month
	 *                  用户出生月
	 * @param String day
	 *                  用户出生日
	 * @param String sex
	 *                  用户性别
	 * @param String address
	 *                  用户地址
	 * @param String memo
	 *                  用户个人签名
	 * @return Boolean
	 *         	返回一个Boolean：如果成功修改信息
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public boolean updateuserinfo(String account,String nickname,String year,String month,String day,String sex,String address,String memo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "update userInfo set nickname=?,birthday=?,sex=?,address=?,memo=? where account=?";
			String birthday="";
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			if(account == null)
			{
				return false;
			}
			else {
				int accountnum=Integer.parseInt(account);
				if (accountnum>0) {
					ps.setInt(6, accountnum);
				}
				else {
					return false;
				}
				if("".equals(year))
				{
					year="某";
				}
				if("".equals(month))
				{
					month="某";
				}
				if("".equals(day))
				{
					day="某";
				}
				birthday=year+"年"+month+"月"+day+"日";
				ps.setString(1, nickname);
				ps.setString(2, birthday);
				ps.setString(3, sex);
				ps.setString(4, address);
				ps.setString(5, memo);
				
				int temp=conn.executeUpdate();
				if(temp ==1)
				{
					return true;
				}
				else {
					return false;
				}
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
	 * 查看用户个人信息
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个字符串用来显示用户个人信息
	 *
	 * */	
	public String viewuserinfo(String account)
			throws SQLException,ClassNotFoundException{
		//System.out.println(account);
		BaseConn conn = null;
		StringBuffer sb=new StringBuffer();
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userinfo where account=?";
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			if(account == null)
			{
				return "nouserinfo";
			}
			else {
				int accountnum=Integer.parseInt(account);
				if (accountnum>0) {
					ps.setInt(1, accountnum);
				}
				else {
					return "nouserinfo";
				}
				
				ResultSet rs=conn.executeQuery();
				String nickname="";
				String birthday="";
				String sex="";
				String address="";
				String memo="";
				String lastlogintime="";
				int resultCount=0;
				//最多只有一个结果
				while (rs.next()) {
					nickname=rs.getString("nickname");	
					birthday=rs.getString("birthday");
					sex=rs.getString("sex");
					address=rs.getString("address");
					memo=rs.getString("memo");
					lastlogintime=rs.getString("lastlogintime");
					resultCount++;
				}
				
				if(resultCount ==0)
				{
					return "nouserinfo";
				}
				else {
					sb.append("<body align='center'>");
					sb.append("<h1>用户资料</h1>");
					sb.append("账号:").append(account).append("<br>");
					sb.append("昵称:").append(nickname).append("<br>");
					sb.append("生日:").append(birthday).append("<br>");
					sb.append("性别:").append(sex).append("<br>");
					sb.append("地址:").append(address).append("<br>");
					sb.append("最后登录时间:").append(lastlogintime).append("<br>");
					sb.append("个人简介:").append(memo).append("<br>");
					sb.append("</body>");
					return sb.toString();
				}
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
	 * 获取当前用户的个人信息用以修改
	 * @param String account
	 *                  用户账号
	 * @return String
	 *         	返回一个字符串以供用户浏览自己的信息并且做出相应的修改
	 *
	 * */	
	public String getuserinfo(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userinfo where account=?";
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			if(account == null)
			{
				return "nouserinfo";
			}
			else {
				try {
					int accountnum=Integer.parseInt(account);
					if (accountnum>0) {
						ps.setInt(1, accountnum);
					}
					else {
						return "nouserinfo";
					}
				} catch (Exception e) {
					return "nouserinfo";// TODO: handle exception
				}
				
				ResultSet rs=conn.executeQuery();
				String nickname="";
				String birthday="";
				String sex="";
				String address="";
				String memo="";
				int resultCount=0;
				//最多只有一个结果
				while (rs.next()) {
					nickname=rs.getString("nickname");	
					birthday=rs.getString("birthday");
					sex=rs.getString("sex");
					address=rs.getString("address");
					memo=rs.getString("memo");
					resultCount++;
				}
				
				if(resultCount ==0)
				{
					return "nouserinfo";
				}
				else {
					return htmluserinfo(nickname, birthday, sex, address, memo);
				}
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
	 * 将getuserinfo(String account)获取到的信息格式化以供在html页面显示
	 * @param String nickname
	 *                  用户昵称
	 * @param String birthday
	 *                  用户出生日期
	 * @param String sex
	 *                  用户性别
	 * @param String address
	 *                  用户地址
	 * @param String memo
	 *                  用户个人签名
	 * @return String
	 *         	返回一个字符串以供用户浏览自己的信息并且做出相应的修改
	 *
	 * */	
	public String htmluserinfo(String nickname,String birthday,String sex,String address,String memo)
	{
		StringBuffer sb=new StringBuffer();
		String year="";
		String month="";
		String day="";
		if(!birthday.equals(""))
		{
			year=birthday.substring(0,birthday.indexOf("年"));
			month=birthday.substring(birthday.indexOf("年")+1,birthday.indexOf("月"));
			day=birthday.substring(birthday.indexOf("月")+1,birthday.indexOf("日"));
		}
		sb.append("<form action=\"service/updateuser.jsp\" method=\"post\" name=\"form1\" >");
		sb.append("<table align=\"center\">");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">昵称：</td>");
		sb.append(" 		<td width=\"278\"><input type=\"text\" name=\"nickname\" style=\"width:250px;height:30px\" maxlength=\"20\" value=\""+nickname+"\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">生日：</td>");
		sb.append("		<td width=\"278\"><input type=\"text\" name=\"year\" style=\"width:100px;height:30px\" maxlength=\"4\"  value=\""+year+"\">年<input type=\"text\" name=\"month\" style=\"width:60px;height:30px\" maxlength=\"2\" value=\""+month+"\">月<input type=\"text\" name=\"day\" style=\"width:60px;height:30px\" maxlength=\"2\" value=\""+day+"\">日</td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("    	<tr>");
		sb.append("	<td width=\"200\">性别：</td>");
		sb.append("		<td width=\"278\">");
		sb.append("	<select name=\"sex\"  style=\"width:250px;height:30px\">");
		if("男".equals(sex))
		{
			sb.append("		<option value=\"保密\">保密</option>");
			sb.append("		<option value=\"男\" selected=\"selected\">男</option>");
			sb.append("		<option value=\"女\">女</option>");
		}else if ("女".equals(sex)) {
			sb.append("		<option value=\"保密\" selected=\"selected\">保密</option>");
			sb.append("		<option value=\"男\">男</option>");
			sb.append("		<option value=\"女\" selected=\"selected\">女</option>");
		}else {
			sb.append("		<option value=\"保密\" selected=\"selected\">保密</option>");
			sb.append("		<option value=\"男\">男</option>");
			sb.append("		<option value=\"女\">女</option>");
		}
		sb.append("	</select></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">地址：</td>");
		sb.append(" 		<td width=\"278\"><input type=\"text\" name=\"address\" style=\"width:250px;height:30px\" maxlength=\"200\" value=\""+address+"\"></td>");
		sb.append(" 	</tr>");
		sb.append(" 	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">个人简介：</td>");
		sb.append("		<td width=\"278\"><textarea name=\"memo\" rows=\"3\" cols=\"64\" maxlength=\"200\" style=\"resize:none;\" >"+memo+"</textarea></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" align=\"center\" height=\"30\" valign=\"bottom\" >");
		sb.append("		<input type=\"submit\" value=\"修改\" style=\"width:100px;height:30px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("	<input type=\"reset\" value=\"清空\" style=\"width:100px;height:30px\">");
		sb.append("	</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append(" </form>");
		return sb.toString();
	}
	
	/**
	 * 重置用户密码(根据密保问题和答案)
	 * @param String account
	 *                  用户账号
	 * @param String newpassword
	 *                  用户新密码
	 * @param String question
	 *                  用户密保问题
	 * @param String answer
	 *                  用户密保问题答案
	 * @return Boolean
	 *         	返回一个Boolean：如果成功重置密码
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public boolean reset(String account,String newpassword,String question,String answer)
			throws SQLException,ClassNotFoundException{
		//System.out.println(account+" "+newpassword+" "+question+" "+answer);
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userInfo where account=?";
			String upSql="update userInfo set userPassword=?,logged='0' where account=?";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			PreparedStatement upps=conn.preparedStatement(upSql);
			//account前台检证过肯定是数字并且不会为空
			int accountnum=Integer.parseInt(account);
			if(accountnum<=0)
			{
				return false;
			}
			else {
				ps.setInt(1, accountnum);
				upps.setInt(2, accountnum);
			}
			
			ResultSet rs=ps.executeQuery();
			String questionString="";
			String answerString="";
			//account为主键，所以最多只有一个结果
			while (rs.next()) {
				questionString=rs.getString("question");
				answerString=rs.getString("answer");
			}
			if(questionString.equals(question) && answerString.equals(answer))
			{
				upps.setString(1, newpassword);
				int temp=upps.executeUpdate();
				if(temp == 1)
				{
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
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
	 * 修改用户密码
	 * @param String account
	 *                  用户账号
	 * @param String pwd
	 *                  用户原密码
	 * @param String newpwd
	 *                  用户新密码
	 * @return Boolean
	 *         	返回一个Boolean：如果成功修改密码
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public boolean changepwd(String account,String pwd,String newpwd)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userInfo where account=?";
			String upSql="update userInfo set userPassword=?,logged='0' where account=?";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			PreparedStatement upps=conn.preparedStatement(upSql);
			if(account == null)
			{
				return false;
			}
			else {
				int accountnum=Integer.parseInt(account);
				if(accountnum<=0)
				{
					return false;
				}
				else {
					ps.setInt(1, accountnum);
					upps.setInt(2, accountnum);
				}
				
				ResultSet rs=ps.executeQuery();
				String password="";
				//account为主键，所以最多只有一个结果
				while (rs.next()) {
					password=rs.getString("userPassword");
				}
				if(password.equals(pwd))
				{
					upps.setString(1, newpwd);
					int temp=upps.executeUpdate();
					if(temp == 1)
					{
						return true;
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
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
	 * 判断用户身份是否为admin（管理员）
	 * @param String account
	 *                  用户账号
	 * @return Boolean
	 *         	返回一个Boolean：如果是admin（管理员）
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public static boolean isAdmin(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from userInfo where account=?";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			if(account == null)
			{
				return false;
			}
			else {
				int accountnum=0;
				try {
					accountnum=Integer.parseInt(account);
				}catch (Exception e) {
					return false;
				}
				
				if(accountnum<=0)
				{
					return false;
				}
				else {
					ps.setInt(1, accountnum);
				}
				
				ResultSet rs=ps.executeQuery();
				String role="member";
				//account为主键，所以最多只有一个结果
				while (rs.next()) {
					role=rs.getString("role");
				}
				if("admin".equals(role))
				{
					return true;
				}
				else {
					return false;
				}
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
	 * 删除用户在数据库的所有信息
	 * @param String account
	 *                  用户账号
	 * @return Boolean
	 *         	返回一个Boolean：如果是成功删除
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public static boolean deleteUser(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "delete from userInfo where account=?";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			if(account ==null)
			{
				return false;
			}
			else {
				ps.setString(1, account);
				conn.executeUpdate();
				return true;
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
