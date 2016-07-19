package net.chat.BasicAction;

import java.sql.*;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;

public class CheckLogin {	
	/**
	 * ����û���¼��Ϣ
	 * @param String account
	 *                   �û���¼���˻�
	 * @param String userPassword
	 *                   	�û���¼������                  
	 * @return String
	 *         	����һ���ַ���������û����Ѿ������ݿ���ڲ����û����������Ҳ��ȷ
     *                    	�����ַ��� SUCCESS_LOGIN
	 *                        	����û��ѵ�¼
     *                     	�����ַ��� ALREADY_LOGIN
     *                        	����˻�������
     *                      �����ַ��� NO_ACCOUNT
     *                         	����
     *                   	�����ַ��� FAIL_LOGIN
	 *
	 * */	
	public String checklogin(String account,String userPassword) 
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from userInfo where account=?";
			
			//����һ��Ԥ����SQL����
			PreparedStatement ps = conn.preparedStatement(sql);
			ps.setString(1,account);
			
			//�����ݿ��в�ѯ���û����Ƿ������ݿ����
			ResultSet rs = conn.executeQuery();
			if(rs.next())//�������
			{
				if(rs.getString("userPassword").equals(userPassword))
				{
					if(rs.getString("logged").equals("0"))
					{
						SimpleDateFormat cal = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					    String time = cal.format(new java.util.Date());				    
					    //�޸��û�������¼ʱ��
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
}
