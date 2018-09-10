package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.manytomany.Role;
import com.zcy.manytomany.User;
import com.zcy.utils.HibernateUtils;

public class HibernateManytoMany {

	//��ʾ��Զ༶������
	@Test
	public void testSaveDemo()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//��������û���ÿ���û����������ɫ
			//1.��������
			User user1 = new User();
			user1.setUser_name("lucy");
			user1.setUser_password("123");
			
			User user2 = new User();
			user2.setUser_name("mary");
			user2.setUser_password("123");
			
			Role r1 = new Role();
			r1.setRole_name("�ܾ���");
			r1.setRole_memo("�ܾ���");
			
			Role r2 = new Role();
			r2.setRole_name("����");
			r2.setRole_memo("����");
			
			Role r3 = new Role();
			r3.setRole_name("����");
			r3.setRole_memo("����");
			
			//2.������ϵ���ѽ�ɫ�ŵ��û�����
			//user1 -- r1/r2
			//user2 -- r2/r3
			user1.getSetRole().add(r1);
			user1.getSetRole().add(r2);
			user2.getSetRole().add(r2);
			user2.getSetRole().add(r3);
			
			//3.�����û�
			session.save(user1);
			session.save(user2);
			
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
	
	//��ʾ��Զ༶��ɾ��
	@Test
	public void testDelDemo()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//����ɾ��
			User user = session.get(User.class, 1);
			session.delete(user);
			
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
	
	//��ʾά�������ű�
	@Test
	public void testTable()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//��ĳ���û���ĳ����ɫ
			//��lucy�о����˽�ɫ
			//1.��ѯlucy�;�����
			User lucy = session.get(User.class, 1);
			Role role = session.get(Role.class, 1);
			//2.�ѽ�ɫ�ŵ��û���set��������
			lucy.getSetRole().add(role);
			
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
	//��ʾά�������ű�
	@Test
	public void testDelTable()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//��ĳ���û�û��ĳ����ɫ
			//��2���û�û��3�Ľ�ɫ
			//1.��ѯ�û�2�ͽ�ɫ3
			User user = session.get(User.class, 2);
			Role role = session.get(Role.class, 3);
			//2.�ѽ�ɫ�Ƴ��û���set��������
			user.getSetRole().remove(role);
			
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
