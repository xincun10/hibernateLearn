package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

/*
 * 关于二级缓存的操作
 */
public class HibernateDemo2 extends Thread{

	public void run() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();
		User user = session.get(User.class, "1");
		System.out.println(user);
		session.getTransaction().commit();
	}

	public static void main(String[] args) {
		HibernateDemo2 main1 = new HibernateDemo2();
		main1.start();
		HibernateDemo2 main2 = new HibernateDemo2();
		main2.start();
	}
}
