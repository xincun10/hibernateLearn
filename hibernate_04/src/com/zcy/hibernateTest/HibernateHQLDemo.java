package com.zcy.hibernateTest;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateHQLDemo {

	//演示查询所有
	@Test
	public void testSelect1()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			Query query = session.createQuery("from Customer");
			//2.调用方法得到结果
			List<Customer> list = query.list();
			
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示条件查询
	@Test
	public void testSelect2()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//select * from t_customer where cid=? and cname=?
			Query query = session.createQuery("from Customer c where c.cid=? and c.custName=?");
			//2.设置条件值
			//setParameter方法有两个参数
			//第一个参数：int类型，是?位置，?位置从0开始
			//第二个参数：具体参数值
			//设置第一个?值
			query.setParameter(0, 1);
			//设置第二个?值
			query.setParameter(1, "baidu");
			//3.调用方法得到结果
			List<Customer> list = query.list();
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示条件查询-模糊查询
	@Test
	public void testSelect3()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			Query query = session.createQuery("from Customer c where c.custName like ?");
			//2.设置条件值
			//setParameter方法有两个参数
			//第一个参数：int类型，是?位置，?位置从0开始
			//第二个参数：具体参数值
			//设置第一个?值
			query.setParameter(0, "%bai%");
			//3.调用方法得到结果
			List<Customer> list = query.list();
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示排序查询
	@Test
	public void testSelect4()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//升序排列
			Query query = session.createQuery("from Customer order by cid asc");
			
			//2.调用方法得到结果
			List<Customer> list = query.list();
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示分页查询
	@Test
	public void testSelect5()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//写查询所有的语句
			Query query = session.createQuery("from Customer");
			
			//2.设置分页数据
			//设置开始位置
			query.setFirstResult(0);
			//设置每页记录数
			query.setMaxResults(3);
			
			//3.调用方法得到结果
			List<Customer> list = query.list();
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示投影查询
	@Test
	public void testSelect6()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//写查询所有的语句
			Query query = session.createQuery("select custName from Customer");
			
			//2.调用方法得到结果
			List<Object> list = query.list();
			for(Object object:list)
			{
				System.out.println(object);
			}
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
	
	//演示聚集函数使用
	@Test
	public void testSelect7()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//写查询所有的语句
			Query query = session.createQuery("select count(*) from Customer");
			
			//2.调用方法得到结果
			//query对象里面有方法，直接返回对象形式
			Object object = query.uniqueResult();
			System.out.println(object);
			
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			tx.rollback();
		}finally
		{
			session.close();
			sessionFactory.close();
		}
	}
}
