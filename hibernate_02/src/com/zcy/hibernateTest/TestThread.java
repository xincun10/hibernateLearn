package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.User;
import com.zcy.utils.HibernateUtils;

public class TestThread {

	@Test
	public void testTx()
	{
		Session session = null;
		Transaction tx = null;
		try
		{
			//�õ��뱾���̰߳󶨵�session
			session = HibernateUtils.getSessionObject();
			//��������
			tx = session.beginTransaction();
			//���
			User user = new User();
			user.setUsername("hebe");
			user.setPassword("330");
			user.setAddress("̨��");
			
			session.save(user);
			//�ύ����
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			//�ع�����
			tx.rollback();
		}finally
		{
			//session.close();
		}
		
	}
	
}
