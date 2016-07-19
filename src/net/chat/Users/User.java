package net.chat.Users;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;

public class User {
	/**
	 * �����û�������Ϣ
	 * @param String account
	 *                  �û��˺�
	 * @param String nickname
	 *                  �û��ǳ�
	 * @param String year
	 *                  �û�������
	 * @param String month
	 *                  �û�������
	 * @param String day
	 *                  �û�������
	 * @param String sex
	 *                  �û��Ա�
	 * @param String address
	 *                  �û���ַ
	 * @param String memo
	 *                  �û�����ǩ��
	 * @return Boolean
	 *         	����һ��Boolean������ɹ��޸���Ϣ
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public boolean updateuserinfo(String account,String nickname,String year,String month,String day,String sex,String address,String memo)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "update userInfo set nickname=?,birthday=?,sex=?,address=?,memo=? where account=?";
			String birthday="";
			//����һ��Ԥ����SQL����
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
					year="ĳ";
				}
				if("".equals(month))
				{
					month="ĳ";
				}
				if("".equals(day))
				{
					day="ĳ";
				}
				birthday=year+"��"+month+"��"+day+"��";
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * �鿴�û�������Ϣ
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ���ַ���������ʾ�û�������Ϣ
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
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from userinfo where account=?";
			//����һ��Ԥ����SQL����
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
				//���ֻ��һ�����
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
					sb.append("<h1>�û�����</h1>");
					sb.append("�˺�:").append(account).append("<br>");
					sb.append("�ǳ�:").append(nickname).append("<br>");
					sb.append("����:").append(birthday).append("<br>");
					sb.append("�Ա�:").append(sex).append("<br>");
					sb.append("��ַ:").append(address).append("<br>");
					sb.append("����¼ʱ��:").append(lastlogintime).append("<br>");
					sb.append("���˼��:").append(memo).append("<br>");
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ��ȡ��ǰ�û��ĸ�����Ϣ�����޸�
	 * @param String account
	 *                  �û��˺�
	 * @return String
	 *         	����һ���ַ����Թ��û�����Լ�����Ϣ����������Ӧ���޸�
	 *
	 * */	
	public String getuserinfo(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from userinfo where account=?";
			//����һ��Ԥ����SQL����
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
				//���ֻ��һ�����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ��getuserinfo(String account)��ȡ������Ϣ��ʽ���Թ���htmlҳ����ʾ
	 * @param String nickname
	 *                  �û��ǳ�
	 * @param String birthday
	 *                  �û���������
	 * @param String sex
	 *                  �û��Ա�
	 * @param String address
	 *                  �û���ַ
	 * @param String memo
	 *                  �û�����ǩ��
	 * @return String
	 *         	����һ���ַ����Թ��û�����Լ�����Ϣ����������Ӧ���޸�
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
			year=birthday.substring(0,birthday.indexOf("��"));
			month=birthday.substring(birthday.indexOf("��")+1,birthday.indexOf("��"));
			day=birthday.substring(birthday.indexOf("��")+1,birthday.indexOf("��"));
		}
		sb.append("<form action=\"service/updateuser.jsp\" method=\"post\" name=\"form1\" >");
		sb.append("<table align=\"center\">");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">�ǳƣ�</td>");
		sb.append(" 		<td width=\"278\"><input type=\"text\" name=\"nickname\" style=\"width:250px;height:30px\" maxlength=\"20\" value=\""+nickname+"\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">���գ�</td>");
		sb.append("		<td width=\"278\"><input type=\"text\" name=\"year\" style=\"width:100px;height:30px\" maxlength=\"4\"  value=\""+year+"\">��<input type=\"text\" name=\"month\" style=\"width:60px;height:30px\" maxlength=\"2\" value=\""+month+"\">��<input type=\"text\" name=\"day\" style=\"width:60px;height:30px\" maxlength=\"2\" value=\""+day+"\">��</td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("    	<tr>");
		sb.append("	<td width=\"200\">�Ա�</td>");
		sb.append("		<td width=\"278\">");
		sb.append("	<select name=\"sex\"  style=\"width:250px;height:30px\">");
		if("��".equals(sex))
		{
			sb.append("		<option value=\"����\">����</option>");
			sb.append("		<option value=\"��\" selected=\"selected\">��</option>");
			sb.append("		<option value=\"Ů\">Ů</option>");
		}else if ("Ů".equals(sex)) {
			sb.append("		<option value=\"����\" selected=\"selected\">����</option>");
			sb.append("		<option value=\"��\">��</option>");
			sb.append("		<option value=\"Ů\" selected=\"selected\">Ů</option>");
		}else {
			sb.append("		<option value=\"����\" selected=\"selected\">����</option>");
			sb.append("		<option value=\"��\">��</option>");
			sb.append("		<option value=\"Ů\">Ů</option>");
		}
		sb.append("	</select></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">��ַ��</td>");
		sb.append(" 		<td width=\"278\"><input type=\"text\" name=\"address\" style=\"width:250px;height:30px\" maxlength=\"200\" value=\""+address+"\"></td>");
		sb.append(" 	</tr>");
		sb.append(" 	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td width=\"200\">���˼�飺</td>");
		sb.append("		<td width=\"278\"><textarea name=\"memo\" rows=\"3\" cols=\"64\" maxlength=\"200\" style=\"resize:none;\" >"+memo+"</textarea></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" height=\"20\"></td>");
		sb.append("	</tr>");
		sb.append("	<tr>");
		sb.append("		<td colspan=\"2\" align=\"center\" height=\"30\" valign=\"bottom\" >");
		sb.append("		<input type=\"submit\" value=\"�޸�\" style=\"width:100px;height:30px\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		sb.append("	<input type=\"reset\" value=\"���\" style=\"width:100px;height:30px\">");
		sb.append("	</td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append(" </form>");
		return sb.toString();
	}
	
	/**
	 * �����û�����(�����ܱ�����ʹ�)
	 * @param String account
	 *                  �û��˺�
	 * @param String newpassword
	 *                  �û�������
	 * @param String question
	 *                  �û��ܱ�����
	 * @param String answer
	 *                  �û��ܱ������
	 * @return Boolean
	 *         	����һ��Boolean������ɹ���������
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public boolean reset(String account,String newpassword,String question,String answer)
			throws SQLException,ClassNotFoundException{
		//System.out.println(account+" "+newpassword+" "+question+" "+answer);
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
			String sql = "select * from userInfo where account=?";
			String upSql="update userInfo set userPassword=?,logged='0' where account=?";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			PreparedStatement upps=conn.preparedStatement(upSql);
			//accountǰ̨��֤���϶������ֲ��Ҳ���Ϊ��
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
			//accountΪ�������������ֻ��һ�����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * �޸��û�����
	 * @param String account
	 *                  �û��˺�
	 * @param String pwd
	 *                  �û�ԭ����
	 * @param String newpwd
	 *                  �û�������
	 * @return Boolean
	 *         	����һ��Boolean������ɹ��޸�����
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public boolean changepwd(String account,String pwd,String newpwd)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
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
				//accountΪ�������������ֻ��һ�����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * �ж��û�����Ƿ�Ϊadmin������Ա��
	 * @param String account
	 *                  �û��˺�
	 * @return Boolean
	 *         	����һ��Boolean�������admin������Ա��
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public static boolean isAdmin(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
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
				//accountΪ�������������ֻ��һ�����
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
	
	/**
	 * ɾ���û������ݿ��������Ϣ
	 * @param String account
	 *                  �û��˺�
	 * @return Boolean
	 *         	����һ��Boolean������ǳɹ�ɾ��
	 *                         ����true
	 *                             ����
	 *                         ����false
	 *
	 * */	
	public static boolean deleteUser(String account)
			throws SQLException,ClassNotFoundException{
		BaseConn conn = null;
		try
		{
			conn = new BaseConn();
			
			//����һ����Ԥ�����SQL���
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
			conn.closeDB(); //�ر����ݿ����ӣ��ͷ�JDBC��Դ
		}
	}
}
