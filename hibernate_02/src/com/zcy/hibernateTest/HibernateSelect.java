package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class HibernateSelect {

	//��֤һ���������
	@Test
	public void testCasch()
	{
		//1.���ù�����õ�sessionFactory
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		//2.��ȡsession
		Session session = sessionFactory.openSession();
		//3.��������
		Transaction tx = session.beginTransaction();
		//4.����uid=1��ѯ
		//�۲�ִ�е�һ��get�����Ƿ��ѯ���ݿ⣬�Ƿ���sql���
		User user1 = session.get(User.class, 1);
		System.out.println(user1);
		
		//�ٸ���uid=1��ѯ
		//�۲�ִ�е�һ��get�����Ƿ��ѯ���ݿ⣬�Ƿ���sql���
		User user2 = session.get(User.class, 1);
		System.out.println(user1);
		//5.�ύ����
		tx.commit();
		//6.�ر�
		session.close();
		sessionFactory.close();
	}
}
