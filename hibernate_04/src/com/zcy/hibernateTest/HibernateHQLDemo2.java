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

public class HibernateHQLDemo2 {

	//��ʾhql�����Ӳ�ѯ
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
			//������
//			Query query = session.createQuery("from Customer c inner join c.setLinkMan");
			//����������
//			Query query = session.createQuery("from Customer c inner join fetch c.setLinkMan");
			//��������
//			Query query = session.createQuery("from Customer c left outer join c.setLinkMan");
			//������������
			Query query = session.createQuery("from Customer c left outer join fetch c.setLinkMan");
			//2.���÷����õ����
			List list = query.list();
			
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
