package net.chat.Listener;

import java.sql.SQLException;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.chat.BasicAction.Logout;

public class MySessionListener implements HttpSessionListener{
	/**
	 * 重写监听器
	 * 当用户的session摧毁时触发
	 * 将数据库内用户的登录状态重置为0（未登录）
	 * */	
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		//即使用户非法退出也可以在1分钟后重置登录状态
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
