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
		//第一步、加载hibernate核心配置文件
		//到src下面找到名称是hibernate.cfg.xml的文件
		//在hibernate里面封装对象
		Configuration cfg = new Configuration();
		cfg.configure();
		
		//第二步、创建SessionFactory对象
		//读取hibernate核心配置文件内容，创建sessionFactory
		//在这个过程中，根据映射关系，在配置数据库里面把表创建
		//SessionFactory sessionFactory = cfg.buildSessionFactory();
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		
		//第三步、使用SessionFactory创建session对象
		//类似于连接
		Session session = sessionFactory.openSession();
		
		//第四步、开启事务
		Transaction tx = session.beginTransaction();
		
		//第五步、写具体逻辑 crud操作
		//添加功能
		User user = new User();
		user.setUsername("咸鱼");
		user.setPassword("xianyu");
		user.setAddress("台北");
		//调用session的方法实现添加
		session.save(user);
		
		//第六步、提交事务
		tx.commit();
		//回滚操作
		//tx.rollback();

		//第七步、关闭资源
		session.close();
		sessionFactory.close();
	}
}
