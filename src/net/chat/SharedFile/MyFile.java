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
	 * ��ӹ����ļ���Ϣ
	 * @param String account
	 *                  �û��˺�
	 * @param String fileUrl
	 *                  �����ļ����ļ�·��
	 * @return Boolean
	 *         	����һ��Boolean������ɹ������Ϣ
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public boolean addSharingFile(String account,String fileUrl,String memo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		
		try
		{
			conn = new BaseConn();
			//����һ����Ԥ�����SQL���
			String sql="insert into sharingfileinfo(account,fileUrl,memo,uploadtime) values (?,?,?,?)";
			
			//����һ��Ԥ����SQL����
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
			//�������ɹ�������һ���յ�Ӱ��
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ��ȡ�����ļ���Ϣ
	 * @param String user
	 *                  �û���
	 * @return String
	 *         	����һ���ַ���������ʾ�����ļ���Ϣ
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
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from sharingfileinfo";
			//����һ��Ԥ����SQL����
				
			ResultSet rs=conn.executeQuery(sql);
			String account="";		
			String fileUrl="";
			String memo="";
			@SuppressWarnings("unused")
			String uploadtime="";
			sb.append("<table border='1'>");
			sb.append("<tr><td>�ϴ��û��˺�</td><td>�ļ�</td><td>��ע</td><td>�������</td>");
			if(isAdmin)
			{
				sb.append("<td>���ɾ��</td>");
			}
			sb.append("</tr>");
			while (rs.next()) {
				account=rs.getString("account");	
				fileUrl=rs.getString("fileUrl");
				memo=rs.getString("memo");
				uploadtime=rs.getString("uploadtime");
				sb.append("<tr><td>").append(account).append("</td><td>").append(fileUrl).append("</td><td>").append(memo).append("</td><td>");
				sb.append("<a href='service/downloadfile.jsp?fileUrl="+Constant.FILE_PATH+fileUrl+"'>����</a></td>");
				if(isAdmin)
				{
					sb.append("<td><a href='service/deletefile.jsp?fileUrl="+fileUrl+"'>ɾ��</a></td>");
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ɾ�������ļ�
	 * @param String account
	 *                  �û��˺�
	 * @param String fileUrl
	 *                  �����ļ����ļ�·��
	 * @return Boolean
	 *         	����һ��Boolean������ɹ�ɾ���ļ�
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public boolean deleteFile(String account,String fileUrl)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;

		//��������ļ�˳��ɾ����ɾ���ݿ��¼
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
					//����һ����Ԥ�����SQL���
					String sql="delete from sharingfileinfo where fileUrl=?";
					
					//����һ��Ԥ����SQL����
					PreparedStatement ps = conn.preparedStatement(sql);			
					
					ps.setString(1, fileUrl);
					
					int temp=conn.executeUpdate();
					//���ɾ���ɹ�������һ���յ�Ӱ��
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
				conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
			}
		}
		else {
			//�����ļ�ɾ��ʧ��ֱ�ӷ���false
			return false;
		}
	}
}
