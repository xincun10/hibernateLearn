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
			//1.���ù�����õ�sessionFactory
			sessionFactory = HibernateUtils.getSessionFactory();
			//2.��ȡsession
			session = sessionFactory.openSession();
			//3.��������
			tx = session.beginTransaction();
			//4.ɾ������
			//�ڶ��ַ�ʽ
			User user = new User();
			user.setUid(3);
			session.delete(user);
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
		
//		//��һ�� ����id��ѯ����
//		User user = session.get(User.class, 2);
//		session.delete(user);
//		//4.�޸Ĳ���
//		//�޸�uid=2��¼��usernameֵ
//		//4.1����id��ѯ
//		User user = session.get(User.class, 2);
//		//4.2�򷵻ص�user�������������޸�֮���ֵ
//		user.setUsername("����");
//		//4.3����session�ķ���update�޸�
//		//ִ�й��̣���user���������ҵ�uidֵ������uidֵ�����޸�
//		session.update(user);
		
		//4.����id��ѯ
		//����session�����get����
		//��һ��������ʵ�����class
		//�ڶ���������idֵ
//		User user = session.get(User.class, 1);
//		System.out.println(user);
		
	}
}
