package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class HibernateDemo {

	@Test
	public void TestAdd()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			//1.调用工具类得到sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//2.获取session
			session = sessionFactory.openSession();
			//3.开启事务
			tx = session.beginTransaction();
			//4.删除操作
			//第二种方式
			User user = new User();
			user.setUid(3);
			session.delete(user);
			//5.提交事务
			tx.commit();
		}catch(Exception e)
		{
			//回滚事务
			tx.rollback();
		}finally
		{
			//关闭操作
			//6.关闭
			session.close();
			sessionFactory.close();
		}
		
//		//第一种 根据id查询对象
//		User user = session.get(User.class, 2);
//		session.delete(user);
//		//4.修改操作
//		//修改uid=2记录的username值
//		//4.1根据id查询
//		User user = session.get(User.class, 2);
//		//4.2向返回的user对象里面设置修改之后的值
//		user.setUsername("咸鱼");
//		//4.3调用session的方法update修改
//		//执行过程：到user对象里面找到uid值，根据uid值进行修改
//		session.update(user);
		
		//4.根据id查询
		//调用session里面的get方法
		//第一个参数：实体类的class
		//第二个参数：id值
//		User user = session.get(User.class, 1);
//		System.out.println(user);
		
	}
}
