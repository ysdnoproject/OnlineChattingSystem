package net.chat.BasicAction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;

public class Logout {
	/**
	 * �û��ǳ�
	 * @param String account
	 *                   �û����˻�
	 * @return boolean
	 *         	����һ��Boolean������ɹ��ǳ�
     *                    	����true
     *                         	����
     *                   	����false
	 *
	 * */	
	public boolean logout(String account) 
			throws SQLException,ClassNotFoundException
	{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "update userInfo set logged='0' where account=?";
			
			//����һ��Ԥ����SQL����
			PreparedStatement ps = conn.preparedStatement(sql);
			ps.setString(1,account);
			//���ĵ�¼״̬Ϊ0��δ��¼��
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}

}
