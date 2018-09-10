package com.zcy.hibernateTest;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateQBCDemo {

	//��ʾ��ѯ����
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
			
			//1.��������
			Criteria criteria = session.createCriteria(Customer.class);
			//2.���÷����õ����
			List<Customer> list = criteria.list();
			
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
	
	//��ʾ������ѯ
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
			
			//1.��������
			Criteria criteria = session.createCriteria(Customer.class);
			//2.ʹ��criteria��������ķ�����������ֵ
			// ����ʹ��add��������ʾ��������ֵ
			// ��add��������ʹ����ķ���ʵ����������
			//������cid=? and custName="baidu"
			criteria.add(Restrictions.eq("cid", 1));
			criteria.add(Restrictions.eq("custName", "baidu"));
			//3.���÷����õ����
			List<Customer> list = criteria.list();
			
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
	
	//��ʾ������ѯ-ģ����ѯ
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
			
			//1.��������
			Criteria criteria = session.createCriteria(Customer.class);
			//2.��������ֵ
			criteria.add(Restrictions.like("custName", "%bai%"));
			
			//3.���÷����õ����
			List<Customer> list = criteria.list();
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
	
	//��ʾ�����ѯ
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
			
			//1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//2.���ö��ĸ����Խ��������Լ��������
			criteria.addOrder(Order.desc("cid"));
			//3.���÷����õ����
			List<Customer> list = criteria.list();
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
	
	//��ʾ��ҳ��ѯ
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
			
			//1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//2 ���÷�ҳ����
			//���ÿ�ʼλ��
			criteria.setFirstResult(0);
			//����ÿҳ��ʾ��¼��
			criteria.setMaxResults(3);
			
			//3.���÷����õ����
			List<Customer> list = criteria.list();
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
	
	//��ʾͳ�Ʋ�ѯ
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
			
			//1.����Criteria����
			Criteria criteria = session.createCriteria(Customer.class);
			//2.���ò���
			criteria.setProjection(Projections.rowCount());
			//3.���÷����õ����
			Object object = criteria.uniqueResult();
			Long lobj = (Long) object;
			int count = lobj.intValue();
			
			System.out.println(count);
			
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
	
	//��ʾ���߲�ѯ
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
			
			//1.��������
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
			//2.����ִ��ʱ�����Ҫ�õ�session
			Criteria criteria = detachedCriteria.getExecutableCriteria(session);
			List<Customer> list = criteria.list();
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
}
