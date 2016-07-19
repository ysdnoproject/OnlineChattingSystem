package net.chat.BasicAction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import net.chat.BaseConn.BaseConn;


public class Register {
	/**
	 * �û�ע��
	 * @param String nickname
	 *                   �û��ǳ�
	 * @param String password
	 *                   �û�����
	 * @param String email
	 *                   �û�����
	 * @param String question
	 *                    �û��ܱ�����
	 * @param String answer
	 *                    �û��ܱ������
	 * @return integer
	 *         	����һ�����������ע��ɹ�
     *                    	����ϵͳΪ�û��Զ����ɵ��˺�
     *                         	����
     *                   	����-1
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
			
			//����һ����Ԥ�����SQL���
			String sql = "select max(account) from userInfo";
			
			//�����ݿ��в�ѯ��ǰ�����˺�
			ResultSet rs =conn.executeQuery(sql);
			if(rs.next())//������������˺�
			{
				account=rs.getInt(1)+1;
			}
			else
			{
				account=1;
			}
			
			sql="insert into userInfo(account,nickName,userPassword,lastlogintime,role,email,question,answer,logged) values (?,?,?,?,?,?,?,?,?)";
			//����һ��Ԥ����SQL����
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
		    //�������ɹ�������һ���յ�Ӱ��
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
}
