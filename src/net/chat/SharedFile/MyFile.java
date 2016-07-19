package net.chat.SharedFile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;
import net.chat.Constant.Constant;
import net.chat.Users.User;
import net.chat.util.DeleteLocalFile;

public class MyFile {
	/**
	 * 添加共享文件信息
	 * @param String account
	 *                  用户账号
	 * @param String fileUrl
	 *                  共享文件的文件路径
	 * @return Boolean
	 *         	返回一个Boolean：如果成功添加信息
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public boolean addSharingFile(String account,String fileUrl,String memo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//创建一个用预处理的SQL语句
			String sql="insert into sharingfileinfo(account,fileUrl,memo,uploadtime) values (?,?,?,?)";
			
			//创建一个预处理SQL对象
			PreparedStatement ps = conn.preparedStatement(sql);
			
			if(account ==null)
			{
				return false;
			}
			else {
				int accountnum=Integer.parseInt(account);
				
				
				if(accountnum>0)
				{
					ps.setInt(1, accountnum);
				}
				else {
					return false;
				}
			}
			
			
			ps.setString(2, fileUrl);
			
			ps.setString(3, memo);
			
			SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String time = cal.format(new java.util.Date());
		    
		    ps.setString(4, time);
			int temp=conn.executeUpdate();
			//如果插入成功，即有一行收到影响
		    if(temp==1)
		    {
		    	return true;
		    }
		    else
		    {
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
	 * 获取共享文件信息
	 * @param String user
	 *                  用户账
	 * @return String
	 *         	返回一个字符串用来显示共享文件信息
	 *
	 * */	
	public String getFileList(String user)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		StringBuffer sb=new StringBuffer();
		boolean isAdmin=User.isAdmin(user);
		try
		{
			conn = new BaseConn();
			
			//创建一个用预处理的SQL语句
			String sql = "select * from sharingfileinfo";
			//创建一个预处理SQL对象
				
			ResultSet rs=conn.executeQuery(sql);
			String account="";		
			String fileUrl="";
			String memo="";
			@SuppressWarnings("unused")
			String uploadtime="";
			sb.append("<table border='1'>");
			sb.append("<tr><td>上传用户账号</td><td>文件</td><td>备注</td><td>点击下载</td>");
			if(isAdmin)
			{
				sb.append("<td>点击删除</td>");
			}
			sb.append("</tr>");
			while (rs.next()) {
				account=rs.getString("account");	
				fileUrl=rs.getString("fileUrl");
				memo=rs.getString("memo");
				uploadtime=rs.getString("uploadtime");
				sb.append("<tr><td>").append(account).append("</td><td>").append(fileUrl).append("</td><td>").append(memo).append("</td><td>");
				sb.append("<a href='service/downloadfile.jsp?fileUrl="+Constant.FILE_PATH+fileUrl+"'>下载</a></td>");
				if(isAdmin)
				{
					sb.append("<td><a href='service/deletefile.jsp?fileUrl="+fileUrl+"'>删除</a></td>");
				}
				sb.append("</tr>");
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
	
	/**
	 * 删除共享文件
	 * @param String account
	 *                  用户账号
	 * @param String fileUrl
	 *                  共享文件的文件路径
	 * @return Boolean
	 *         	返回一个Boolean：如果成功删除文件
	 *                         返回true
	 *                             否则
	 *                         返回false
	 *
	 * */	
	public boolean deleteFile(String account,String fileUrl)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;

		//如果本地文件顺利删除再删数据库记录
		if(DeleteLocalFile.deleteFile(Constant.LOCAL_FILE_PATH+fileUrl))
		{
			try
			{
				conn = new BaseConn();
				if(!User.isAdmin(account))
				{
					return false;
				}
				else {
					//创建一个用预处理的SQL语句
					String sql="delete from sharingfileinfo where fileUrl=?";
					
					//创建一个预处理SQL对象
					PreparedStatement ps = conn.preparedStatement(sql);			
					
					ps.setString(1, fileUrl);
					
					int temp=conn.executeUpdate();
					//如果删除成功，即有一行收到影响
				    if(temp==1)
				    {
				    	return true;
				    }
				    else
				    {
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
		else {
			//本地文件删除失败直接返回false
			return false;
		}
	}
}
