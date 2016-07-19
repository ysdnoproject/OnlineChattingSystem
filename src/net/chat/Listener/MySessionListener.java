package net.chat.Listener;

import java.sql.SQLException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.chat.BasicAction.Logout;

public class MySessionListener implements HttpSessionListener{
	/**
	 * ��д������
	 * ���û���session�ݻ�ʱ����
	 * �����ݿ����û��ĵ�¼״̬����Ϊ0��δ��¼��
	 * */	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//��ʹ�û��Ƿ��˳�Ҳ������1���Ӻ����õ�¼״̬
		Logout lg=new Logout();
		if(se.getSession().getAttribute("_USER") != null)
		{
			String account=se.getSession().getAttribute("_USER").toString();
			if(account != null)
			{
				if(Integer.parseInt(account)>0)
				{
					try {
						lg.logout(account);
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		//System.out.println("success");
		
	}

}
