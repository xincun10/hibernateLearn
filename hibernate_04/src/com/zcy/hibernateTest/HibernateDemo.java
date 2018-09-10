package com.zcy.hibernateTest;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateDemo {

	//��ʾ���󵼺���ѯ
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
			
			//��ѯcid=1�Ŀͻ����ٲ�ѯ����ͻ�����������ϵ��
			Customer customer = session.get(Customer.class, 1);
			//��ѯ������ϵ��
			Set<LinkMan> linkManSet = customer.getSetLinkMan();
			System.out.println(linkManSet.size());
			
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
