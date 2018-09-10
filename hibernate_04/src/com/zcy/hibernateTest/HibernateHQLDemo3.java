package com.zcy.hibernateTest;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateHQLDemo3 {

	//演示hibernate检索策略
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
			
			//1.查询cid=1的客户
			//在下面这句设置断点，观察执行get方法之后，是否发送sql语句
//			Customer customer = session.get(Customer.class, 1);
//			System.out.println(customer);
			/**
			 * 调用load方法之后不会马上发送sql语句
			 * （1）返回对象里面只有id值
			 * （2）只有在得到对象里面不是id的其他值的时候才会发送sql语句
			 */
			Customer customer = session.load(Customer.class, 1);
			System.out.println(customer.getCid());
			System.out.println(customer.getCustName());
			
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
	
	//演示hibernate关联级别延迟
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
			
			//1.根据cid=1的客户，再查询这个客户里面所有联系人
			Customer customer = session.get(Customer.class, 1);
			//再查询这个客户里面所有联系人
			//直接得到客户里面联系人的set集合
			//得到set集合，没有发送语句
			Set<LinkMan> linkman = customer.getSetLinkMan();
			//此时发送语句
			System.out.println(linkman.size());
			
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
	
	//演示hibernate批量抓取
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
			
			//1.查询所有客户
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> list = criteria.list();
			//2.得到每个客户里面的所有联系人
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());;
				//每个客户里面的所有联系人
				Set<LinkMan> setLinkMan = customer.getSetLinkMan();
				for(LinkMan linkman:setLinkMan)
				{
					System.out.println(linkman.getLkm_id()+"::"+linkman.getLkm_name());
				}
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
	
}
