package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class HibernateSelect {

	//验证一级缓存存在
	@Test
	public void testCasch()
	{
		//1.调用工具类得到sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.获取session
		Session session = sessionFactory.openSession();
		//3.开启事务
		Transaction tx = session.beginTransaction();
		//4.根据uid=1查询
		//观察执行第一个get方法是否查询数据库，是否发送sql语句
		User user1 = session.get(User.class, 1);
		System.out.println(user1);
		
		//再根据uid=1查询
		//观察执行第一个get方法是否查询数据库，是否发送sql语句
		User user2 = session.get(User.class, 1);
		System.out.println(user1);
		//5.提交事务
		tx.commit();
		//6.关闭
		session.close();
		sessionFactory.close();
	}
}
