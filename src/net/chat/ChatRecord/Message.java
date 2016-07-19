package net.chat.ChatRecord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;
import net.chat.Constant.Constant;

public class Message {
	/**
	 * ��ȡ����������Ϣ
	 * @param String account
	 *                   �û���¼���˻�
	 * @param String tmpcurPage
	 *                   ��ǰ��ʾ�ķ�ҳ��ҳ��
	 * @return String
	 *         	����һ���ַ���������ʾ������Ϣ
	 *
	 * */	
	public String getAllMessage(String account,String tmpcurPage)
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;

		try {
			conn = new BaseConn();
			//����һ����Ԥ�����SQL���
			//������������¼����ʱ�併������
			String sql="select * from msginfo where msgTo=? or msgFrom=?  or msgTo=0 order by sendtime DESC";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			if(account == null)
			{
				sb.append("û�м�¼");
				return sb.toString();
			}
			else {
				int accountnum=Integer.parseInt(account);
				if (accountnum<=0) {
					sb.append("û�м�¼");
					return sb.toString();
				}
				else {
					ps.setInt(1, accountnum);
					ps.setInt(2, accountnum);
					ResultSet rs = ps.executeQuery();
					String msgfrom="msgfrom";
					String msgto="msgto";
					String time="";
					String content="";
					//��ü�¼������
					rs.last();
					int size = rs.getRow();
					
					//���ҳ��
					int pageCount = (size%Constant.PAGESIZE==0)?(size/Constant.PAGESIZE):(size/Constant.PAGESIZE+1);
					
					//�ж�curpage�ĺϷ���
					if(tmpcurPage==null){
						tmpcurPage="1";
					}
					int curPage = Integer.parseInt(tmpcurPage);
					if(curPage>=pageCount) {
						curPage = pageCount;
					}
					
					//��ָ���ƶ�����ǰҳ��Ӧ�ö�Ӧ�ĵ�һ����¼
					rs.absolute((curPage-1)*Constant.PAGESIZE+1);
					
					//ͳ���Ѿ�������������������ڵ����趨������¼��ʱ��break
					int count = 0;
					
					do{
						if(count>=Constant.PAGESIZE){
							break;
						}
						msgfrom=rs.getString("msgFrom");
						msgto=rs.getString("msgTo");
						time=rs.getString("sendtime");
						time=time.substring(0, time.length()-2);
						content=rs.getString("content");
						//�ж��Ƿ�Ϊ���Ļ�Ȼ����ʾ
						 if(msgto.equals("0"))
						 {
							 sb.append("<font size=4 color='grey'>"+time+"   �˺�Ϊ"+msgfrom+"������˵:</font><br>"+content+"<br>");
							 count++;
						 }
						 else {
							 if(account.equals(msgfrom))
							 {
								 sb.append("<font size=4 color='grey'>"+time+"  "+"��"+msgto+"���Ļ�:</font><br>"+content+"<br>");
							 count++;
							 }
							 else if(account.equals(msgto))
							 {
							 sb.append("<font size=4 color='grey'>"+time+"  �˺�Ϊ"+msgfrom+"�����Ѷ���˵���Ļ�:</font><br>"+content+"<br>");
								 count++;
							 }
						}
					}while(rs.next());
					
					sb.append("<a href = 'chatrecord.jsp?curPage=1' >��ҳ</a>&nbsp;&nbsp;");
					if(curPage>1)
					{ 
						sb.append("<a href = 'chatrecord.jsp?curPage=").append(curPage-1).append("' >��һҳ</a>&nbsp;&nbsp;");
					}
					else{
						sb.append("&nbsp;&nbsp;&nbsp;");
					}
					if(curPage<pageCount) {
						sb.append("<a href = 'chatrecord.jsp?curPage=").append(curPage+1).append("' >��һҳ</a>&nbsp;&nbsp;");
					} 
					else{
						sb.append("&nbsp;&nbsp;&nbsp;");
					}

					sb.append("<a href = 'chatrecord.jsp?curPage=").append(pageCount).append("' >βҳ</a>&nbsp;&nbsp;");
					sb.append("��").append(curPage).append("ҳ/��").append(pageCount).append("ҳ");
					//������ռλ����ԭΪ����
					String str=sb.toString();
					for(int i=1;i<=Constant.Facecount;i++)
					{
						str=str.replaceAll("<:"+i+":>", "<img src='face/"+i+".gif' id='"+i+"'/>");
					}
					return str;
				}	
			}	
		} catch(SQLException ex)
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
