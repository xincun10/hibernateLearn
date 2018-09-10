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

	//ʹ��SQLQuery����
	@Test
	public void TestSQLQuery()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			//1.���ù�����õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//2.��ȡsession
			session = sessionFactory.openSession();
			//3.��������
			tx = session.beginTransaction();
			//1 ��������
			//��������ͨsql���
			SQLQuery sqlQuery = session.createSQLQuery("select * from t_user3");
			
			//ʹ���ص�list��ÿ�����Ƕ�����ʽ
			sqlQuery.addEntity(User.class);
			//����sqlQuery����ķ���
			List<User> list = sqlQuery.list();
			for(User user:list)
			{
				//���������
				System.out.println(user);
			}
			
//			//����sqlQuery����ķ���
//			//����List���ϣ�Ĭ������ÿ���ֽṹ��������
//			List<Object[]> list = sqlQuery.list();
//			for(Object[] objects:list)
//			{
//				//���������
//				System.out.println(Arrays.toString(objects));
//			}
			
			//5.�ύ����
			tx.commit();
		}catch(Exception e)
		{
			//�ع�����
			tx.rollback();
		}finally
		{
			//�رղ���
			//6.�ر�
			session.close();
			sessionFactory.close();
		}
		
	}
	
	
	//ʹ��criteria����
	@Test
	public void TestCriteria()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			//1.���ù�����õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//2.��ȡsession
			session = sessionFactory.openSession();
			//3.��������
			tx = session.beginTransaction();
			//1 ����criteria����
			//�������������ʵ����class
			Criteria criteria = session.createCriteria(User.class);
			//���÷����õ����
			List<User> list = criteria.list();
			for(User user:list)
			{
				System.out.println(user);
			}
			
			//5.�ύ����
			tx.commit();
		}catch(Exception e)
		{
			//�ع�����
			tx.rollback();
		}finally
		{
			//�رղ���
			//6.�ر�
			session.close();
			sessionFactory.close();
		}
		
	}
	
	//ʹ��query����
	@Test
	public void TestQuery()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try
		{
			//1.���ù�����õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//2.��ȡsession
			session = sessionFactory.openSession();
			//3.��������
			tx = session.beginTransaction();
			//1 ����Query����
			//��������дhql���
			Query query = session.createQuery("from User");
			
			//2 ����query��������ķ����õ����
			List<User> list = query.list();
			
			for(User user:list)
			{
				System.out.println(user);
			}
			
			//5.�ύ����
			tx.commit();
		}catch(Exception e)
		{
			//�ع�����
			tx.rollback();
		}finally
		{
			//�رղ���
			//6.�ر�
			session.close();
			sessionFactory.close();
		}
		
	}
}
