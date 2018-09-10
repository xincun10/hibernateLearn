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

	//��ʾhibernate��������
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
			
			//1.��ѯcid=1�Ŀͻ�
			//������������öϵ㣬�۲�ִ��get����֮���Ƿ���sql���
//			Customer customer = session.get(Customer.class, 1);
//			System.out.println(customer);
			/**
			 * ����load����֮�󲻻����Ϸ���sql���
			 * ��1�����ض�������ֻ��idֵ
			 * ��2��ֻ���ڵõ��������治��id������ֵ��ʱ��Żᷢ��sql���
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
	
	//��ʾhibernate���������ӳ�
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
			
			//1.����cid=1�Ŀͻ����ٲ�ѯ����ͻ�����������ϵ��
			Customer customer = session.get(Customer.class, 1);
			//�ٲ�ѯ����ͻ�����������ϵ��
			//ֱ�ӵõ��ͻ�������ϵ�˵�set����
			//�õ�set���ϣ�û�з������
			Set<LinkMan> linkman = customer.getSetLinkMan();
			//��ʱ�������
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
	
	//��ʾhibernate����ץȡ
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
			
			//1.��ѯ���пͻ�
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> list = criteria.list();
			//2.�õ�ÿ���ͻ������������ϵ��
			for(Customer customer:list)
			{
				System.out.println(customer.getCid()+customer.getCustName());;
				//ÿ���ͻ������������ϵ��
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
