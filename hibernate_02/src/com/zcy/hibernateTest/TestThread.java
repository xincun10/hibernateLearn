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
			//得到与本地线程绑定的session
			session = HibernateUtils.getSessionObject();
			//开启事务
			tx = session.beginTransaction();
			//添加
			User user = new User();
			user.setUsername("hebe");
			user.setPassword("330");
			user.setAddress("台北");
			
			session.save(user);
			//提交事务
			tx.commit();
		}catch(Exception e)
		{
			e.printStackTrace();
			//回滚操作
			tx.rollback();
		}finally
		{
			//session.close();
		}
		
	}
	
}
