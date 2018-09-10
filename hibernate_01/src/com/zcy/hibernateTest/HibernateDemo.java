package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class HibernateDemo {

	@Test
	public void testAdd()
	{
		//��һ��������hibernate���������ļ�
		//��src�����ҵ�������hibernate.cfg.xml���ļ�
		//��hibernate�����װ����
		Configuration cfg = new Configuration();
		cfg.configure();
		
		//�ڶ���������SessionFactory����
		//��ȡhibernate���������ļ����ݣ�����sessionFactory
		//����������У�����ӳ���ϵ�����������ݿ�����ѱ���
		//SessionFactory sessionFactory = cfg.buildSessionFactory();
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		
		//��������ʹ��SessionFactory����session����
		//����������
		Session session = sessionFactory.openSession();
		
		//���Ĳ�����������
		Transaction tx = session.beginTransaction();
		
		//���岽��д�����߼� crud����
		//��ӹ���
		User user = new User();
		user.setUsername("����");
		user.setPassword("xianyu");
		user.setAddress("̨��");
		//����session�ķ���ʵ�����
		session.save(user);
		
		//���������ύ����
		tx.commit();
		//�ع�����
		//tx.rollback();

		//���߲����ر���Դ
		session.close();
		sessionFactory.close();
	}
}
