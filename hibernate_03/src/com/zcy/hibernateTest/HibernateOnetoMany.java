package com.zcy.hibernateTest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.zcy.entity.Customer;
import com.zcy.entity.LinkMan;
import com.zcy.utils.HibernateUtils;

public class HibernateOnetoMany {

	//演示一对多修改
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
			
			//1.根据id查询mary联系人，根据id查询baidu的客户
			Customer baidu = session.get(Customer.class, 1);
			LinkMan mary = session.get(LinkMan.class, 2);
			//2.设置持久态对象值
			//把联系人放到客户里面
			baidu.getSetLinkMan().add(mary);
			//把客户放到联系人里面
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
	
	//演示一对多级联删除
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
			
			//1.根据id查询客户对象
			Customer customer = session.get(Customer.class, 1);
			//2.调用方法删除
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
	
	//演示一对多级联保存
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
			
			//添加一个客户，为这个客户添加一个联系人
			//1.创建客户和联系人对象
			Customer customer = new Customer();
			customer.setCustName("sohu");
			customer.setCustLevel("general");
			customer.setCustSource("网络");
			customer.setCustPhone("110");
			customer.setCustMobile("999");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setLkm_name("mary");
			linkMan.setLkm_gender("男");
			linkMan.setLkm_phone("119");
			
			//2.建立实体类之间的关系，在客户表示联系人，在联系人表示客户
			//2.1在客户里面表示所有联系人，把联系人对象放到客户对象的set集合里面
			customer.getSetLinkMan().add(linkMan);
			//2.2把客户对象放到联系人里面
//			linkMan.setCustomer(customer);
			
			//3.把数据保存到数据库
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
