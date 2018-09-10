package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateOnetoMany {

	//��ʾһ�Զ��޸�
	@Test
	public void testChangeDemo()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.����id��ѯmary��ϵ�ˣ�����id��ѯbaidu�Ŀͻ�
			Customer baidu = session.get(Customer.class, 1);
			LinkMan mary = session.get(LinkMan.class, 2);
			//2.���ó־�̬����ֵ
			//����ϵ�˷ŵ��ͻ�����
			baidu.getSetLinkMan().add(mary);
			//�ѿͻ��ŵ���ϵ������
			mary.setCustomer(baidu);
			
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
	
	//��ʾһ�Զ༶��ɾ��
	@Test
	public void testDeleteDemo()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//1.����id��ѯ�ͻ�����
			Customer customer = session.get(Customer.class, 1);
			//2.���÷���ɾ��
			session.delete(customer);
			
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
	
	//��ʾһ�Զ༶������
	@Test
	public void testDemo1()
	{
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try
		{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//���һ���ͻ���Ϊ����ͻ����һ����ϵ��
			//1.�����ͻ�����ϵ�˶���
			Customer customer = new Customer();
			customer.setCustName("sohu");
			customer.setCustLevel("general");
			customer.setCustSource("����");
			customer.setCustPhone("110");
			customer.setCustMobile("999");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("mary");
			linkMan.setLkm_gender("��");
			linkMan.setLkm_phone("119");
			
			//2.����ʵ����֮��Ĺ�ϵ���ڿͻ���ʾ��ϵ�ˣ�����ϵ�˱�ʾ�ͻ�
			//2.1�ڿͻ������ʾ������ϵ�ˣ�����ϵ�˶���ŵ��ͻ������set��������
			customer.getSetLinkMan().add(linkMan);
			//2.2�ѿͻ�����ŵ���ϵ������
//			linkMan.setCustomer(customer);
			
			//3.�����ݱ��浽���ݿ�
			session.save(customer);
//			session.save(linkMan);
			
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
