package com.zcy.hibernateTest;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class HibernateQueryDemo {

	//使用SQLQuery对象
	@Test
	public void TestSQLQuery()
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
			//1 创建对象
			//参数是普通sql语句
			SQLQuery sqlQuery = session.createSQLQuery("select * from t_user3");
			
			//使返回的list中每部分是对象形式
			sqlQuery.addEntity(User.class);
			//调用sqlQuery里面的方法
			List<User> list = sqlQuery.list();
			for(User user:list)
			{
				//把数组输出
				System.out.println(user);
			}
			
//			//调用sqlQuery里面的方法
//			//返回List集合，默认里面每部分结构都是数组
//			List<Object[]> list = sqlQuery.list();
//			for(Object[] objects:list)
//			{
//				//把数组输出
//				System.out.println(Arrays.toString(objects));
//			}
			
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
		
	}
	
	
	//使用criteria对象
	@Test
	public void TestCriteria()
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
			//1 创建criteria对象
			//方法里面参数是实体类class
			Criteria criteria = session.createCriteria(User.class);
			//调用方法得到结果
			List<User> list = criteria.list();
			for(User user:list)
			{
				System.out.println(user);
			}
			
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
		
	}
	
	//使用query对象
	@Test
	public void TestQuery()
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
			//1 创建Query对象
			//方法里面写hql语句
			Query query = session.createQuery("from User");
			
			//2 调用query对象里面的方法得到结果
			List<User> list = query.list();
			
			for(User user:list)
			{
				System.out.println(user);
			}
			
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
		
	}
}
