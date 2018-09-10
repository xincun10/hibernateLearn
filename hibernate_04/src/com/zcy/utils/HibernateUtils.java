package com.zcy.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	static Configuration cfg = null;
	static SessionFactory sessionFactory = null;
	
	static
	{
		//加载核心配置文件
		cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
	//提供方法返回sessionFactory
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
	//提供返回与本地线程绑定的session的方法
	public static Session getSessionObject()
	{
		return sessionFactory.getCurrentSession();
	}
	//执行可以生成表
	public static void main(String[] args)
	{
		
	}
}
