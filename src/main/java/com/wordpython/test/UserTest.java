package com.wordpython.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.wordpython.domain.User;

public class UserTest {
	@Test
	public void saveUser() {
		//保存用户数据
		//1.获取核心配置文件对象，默认是加载src的hibernate.cfg.xml文件
		Configuration cfg=new Configuration().configure();
//		Configuration cfg=new Configuration().configure("hibernate.cfg.xml");
		//2.创建会话工厂
		SessionFactory factory=cfg.buildSessionFactory();
		//3.创建会话（相当于连接Connect）
		Session session=factory.openSession();
//		Session session=factory.getCurrentSession();//获取一个与当前线程绑定的session
		//开启事务
		Transaction trans=session.beginTransaction();
		//保存
		User user=new User();
		user.setAccount("111111111");
		user.setUsername("1aaaa2");
		user.setPassword("cccccc");
		user.setPhone("98765432123");
//		session.save(user);
		session.saveOrUpdate(user);//没有实体类的编号就执行保存操作，如果有实体类的编号就执行修改操作
		
		//提交事务
		trans.commit();
		//4.关闭会话
		//如果是通过open方法打开session，要自己关闭，如果是通过get方法获取session，session不需要关闭，
		//因为事务提交或者回滚会自动关闭
		session.close();
		//5.关闭工厂，释放资源
		factory.close();
	}
}
