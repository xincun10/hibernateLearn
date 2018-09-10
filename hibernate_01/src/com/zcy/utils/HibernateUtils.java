package com.zcy.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	static Configuration cfg = null;
	static SessionFactory sessionFactory = null;
	
	static
	{
		//���غ��������ļ�
		cfg = new Configuration();
		cfg.configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
	//�ṩ��������sessionFactory
	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}
}
