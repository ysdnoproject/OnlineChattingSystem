package net.chat.ChatRecord;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.chat.BaseConn.BaseConn;
import net.chat.Constant.Constant;

public class Message {
	/**
	 * 获取所有聊天信息
	 * @param String account
	 *                   用户登录的账户
	 * @param String tmpcurPage
	 *                   当前显示的分页的页数
	 * @return String
	 *         	返回一个字符串用来显示聊天信息
	 *
	 * */	
	public String getAllMessage(String account,String tmpcurPage)
			throws SQLException,ClassNotFoundException{
		StringBuffer sb=new StringBuffer();
		BaseConn conn = null;

		try {
			conn = new BaseConn();
			//创建一个用预处理的SQL语句
			//获得所有聊天记录按照时间降序排序
			String sql="select * from msginfo where msgTo=? or msgFrom=?  or msgTo=0 order by sendtime DESC";
			
			PreparedStatement ps=conn.preparedStatement(sql);
			if(account == null)
			{
				sb.append("没有记录");
				return sb.toString();
			}
			else {
				int accountnum=Integer.parseInt(account);
				if (accountnum<=0) {
					sb.append("没有记录");
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
					//获得记录的条数
					rs.last();
					int size = rs.getRow();
					
					//获得页数
					int pageCount = (size%Constant.PAGESIZE==0)?(size/Constant.PAGESIZE):(size/Constant.PAGESIZE+1);
					
					//判断curpage的合法性
					if(tmpcurPage==null){
						tmpcurPage="1";
					}
					int curPage = Integer.parseInt(tmpcurPage);
					if(curPage>=pageCount) {
						curPage = pageCount;
					}
					
					//将指针移动到当前页码应该对应的第一条记录
					rs.absolute((curPage-1)*Constant.PAGESIZE+1);
					
					//统计已经输出的条数当条数大于等于设定的最大记录数时，break
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
						//判断是否为悄悄话然后显示
						 if(msgto.equals("0"))
						 {
							 sb.append("<font size=4 color='grey'>"+time+"   账号为"+msgfrom+"的朋友说:</font><br>"+content+"<br>");
							 count++;
						 }
						 else {
							 if(account.equals(msgfrom))
							 {
								 sb.append("<font size=4 color='grey'>"+time+"  "+"对"+msgto+"悄悄话:</font><br>"+content+"<br>");
							 count++;
							 }
							 else if(account.equals(msgto))
							 {
							 sb.append("<font size=4 color='grey'>"+time+"  账号为"+msgfrom+"的朋友对你说悄悄话:</font><br>"+content+"<br>");
								 count++;
							 }
						}
					}while(rs.next());
					
					sb.append("<a href = 'chatrecord.jsp?curPage=1' >首页</a>&nbsp;&nbsp;");
					if(curPage>1)
					{ 
						sb.append("<a href = 'chatrecord.jsp?curPage=").append(curPage-1).append("' >上一页</a>&nbsp;&nbsp;");
					}
					else{
						sb.append("&nbsp;&nbsp;&nbsp;");
					}
					if(curPage<pageCount) {
						sb.append("<a href = 'chatrecord.jsp?curPage=").append(curPage+1).append("' >下一页</a>&nbsp;&nbsp;");
					} 
					else{
						sb.append("&nbsp;&nbsp;&nbsp;");
					}

					sb.append("<a href = 'chatrecord.jsp?curPage=").append(pageCount).append("' >尾页</a>&nbsp;&nbsp;");
					sb.append("第").append(curPage).append("页/共").append(pageCount).append("页");
					//将表情占位符还原为表情
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
			conn.closeDB(); //关闭数据库连接，释放JDBC资源
		}
	}

}
