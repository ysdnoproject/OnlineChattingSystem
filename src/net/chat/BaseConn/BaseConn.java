package net.chat.BaseConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.chat.Constant.Constant;

public class BaseConn {
  private Connection conn = null;
  private Statement stmt = null;
  private PreparedStatement ps = null;
  private ResultSet rs = null;  
  /**
   * BaseConn�Ĺ��캯��������������������ݿ�ĳ�ʼ�����������������ݿ����
   * */
  public BaseConn() throws SQLException,ClassNotFoundException{
	  String url="jdbc:mysql://localhost:3306/chatroom?useUnicode=true&characterEncoding=UTF-8";
	  String driver = "com.mysql.jdbc.Driver";
	  try
	  {
		  Class.forName(driver);
		  conn=DriverManager.getConnection(url, Constant.DBUSERNAME, Constant.DBPWD);
		  stmt = conn.createStatement();
	  }
	  catch(SQLException e)
	  {
		  System.out.println("�޷��������ݿ�");
		  throw e;

	  }catch(ClassNotFoundException e) { 
			System.out.println("��������ʧ�ܣ�");
			throw e;
		}
  }
  
  /**
   * ��ʼ��Ԥ����� SQL ���Ķ���
   * */
  public PreparedStatement preparedStatement(String sql) throws SQLException
  {
    try
    {
      ps = conn.prepareStatement(sql);
      return ps;
    }catch(SQLException e)
    {
      System.out.println("Error occured when create preparedStatement:"+e);
      throw e;
    }
  }  
  /**
   * ִ�о�̬ SQL ��ѯ��䲢�����������ɽ���Ķ���
   * */
  public ResultSet executeQuery(String sql) throws SQLException
  {
    rs = null;
    try {
      rs = stmt.executeQuery(sql);
    }
    catch (SQLException ex) {
      System.out.println("Error occured when query database��" + ex);
      throw ex;
    }
    return rs;
  }
  
  /**
   * ִ�о�̬SQL������䲢����Ӱ����������
   * */
  public int executeUpdate(String sql) throws SQLException
  {
    try {
      conn.setAutoCommit(false);
      int re = stmt.executeUpdate(sql);
      conn.commit();
      return re;
    }
    catch (SQLException e) {
      conn.rollback();
      System.out.println("Error occured when update database��" + e);
      throw e;
    }
  }  
  /**
   * ִ��Ԥ�����SQL��ѯ���
   * 
   * */
  public ResultSet executeQuery() throws SQLException
  {
    try {
      return ps.executeQuery();
    }
    catch (SQLException e) {
      System.out.println("Error occured when query database��" + e);
      throw e;
    }
  }
  /**
   * ִ��Ԥ�����SQL�������
   * 
   * */
  public int executeUpdate() throws SQLException
  {
    try {
      conn.setAutoCommit(false);
      int r = ps.executeUpdate();
      conn.commit();
      return r;
    }
    catch (SQLException e) {
      conn.rollback();
      System.out.println("Error occured when update database��" + e);
      throw e;
    }
  }
 /**
  * ���ݿ�رղ���
  * */
  public boolean closeDB() throws SQLException
  {
    try {
      if (this.rs != null)
        rs.close();
      if (this.stmt != null)
        this.stmt.close();
      if (this.ps != null)
        this.ps.close();
      if (this.conn != null)
        conn.close();
      return true;
    }
    catch (SQLException e) {
      System.out.println("Error occured when close database��" + e);
      throw e;
    }
  }
}
