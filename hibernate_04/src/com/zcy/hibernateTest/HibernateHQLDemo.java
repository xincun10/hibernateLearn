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
			
			//1.����query����
			Query query = session.createQuery("from Customer");
			//2.���÷����õ����
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
			
			//1.����query����
			//select * from t_customer where cid=? and cname=?
			Query query = session.createQuery("from Customer c where c.cid=? and c.custName=?");
			//2.��������ֵ
			//setParameter��������������
			//��һ��������int���ͣ���?λ�ã�?λ�ô�0��ʼ
			//�ڶ����������������ֵ
			//���õ�һ��?ֵ
			query.setParameter(0, 1);
			//���õڶ���?ֵ
			query.setParameter(1, "baidu");
			//3.���÷����õ����
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
			
			//1.����query����
			Query query = session.createQuery("from Customer c where c.custName like ?");
			//2.��������ֵ
			//setParameter��������������
			//��һ��������int���ͣ���?λ�ã�?λ�ô�0��ʼ
			//�ڶ����������������ֵ
			//���õ�һ��?ֵ
			query.setParameter(0, "%bai%");
			//3.���÷����õ����
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
			
			//1.����query����
			//��������
			Query query = session.createQuery("from Customer order by cid asc");
			
			//2.���÷����õ����
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
			
			//1.����query����
			//д��ѯ���е����
			Query query = session.createQuery("from Customer");
			
			//2.���÷�ҳ����
			//���ÿ�ʼλ��
			query.setFirstResult(0);
			//����ÿҳ��¼��
			query.setMaxResults(3);
			
			//3.���÷����õ����
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
	
	//��ʾͶӰ��ѯ
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
			
			//1.����query����
			//д��ѯ���е����
			Query query = session.createQuery("select custName from Customer");
			
			//2.���÷����õ����
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
	
	//��ʾ�ۼ�����ʹ��
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
			
			//1.����query����
			//д��ѯ���е����
			Query query = session.createQuery("select count(*) from Customer");
			
			//2.���÷����õ����
			//query���������з�����ֱ�ӷ��ض�����ʽ
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
