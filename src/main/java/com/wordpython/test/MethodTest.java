package com.wordpython.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.wordpython.domain.User;

/*
 * 测试session的api(方法）
 * save保存
 * get通过id查询，如果没有数据返回null
 * load 通过id查询，如果数据表没有这个id则抛异常
 * update 更新
 * delete 删除
 */
public class MethodTest {
	//查询数据
	/*
	 * get和load方法的区别
	 * get方法是直接加载数据库
	 * load的设计是懒加载，用到时才去查询数据库，比如“输出”某个用户信息既是用到了
	 * load方法返回的时对象的一个代理
	 */
	@Test
	public void find() {
		Configuration cfg=new Configuration().configure();
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		//测试session的get和load方法
		User user=session.get(User.class, 2);
//		User user=session.load(User.class, 2);
		System.out.println(user);
		session.close();
		factory.close();
	}
	//删除数据
	@Test
	public void deleteTest() {
		Configuration cfg=new Configuration().configure();
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		/*
		 * session的delete删除数据两种方式
		 * 
		 */
		//先开启事务
		session.getTransaction().begin();

		//第一方式：先获取要删除对象，然后再调用delete方法
//		User user=session.get(User.class, 3);
//		session.delete(user);
		//第二方式（推荐）：创建一个User，设置id，然后再调用delete方法
		User user=new User();
		user.setUsername("aaaaaa");
		session.delete(user);
		
		//提交事务
		session.getTransaction().commit();
		
		session.close();
		factory.close();
	}
	//更新数据
	@Test
	public void updateTest() {
		Configuration cfg=new Configuration().configure();
		SessionFactory factory=cfg.buildSessionFactory();
		Session session=factory.openSession();
		//先开启事务
		session.getTransaction().begin();
		
		User user=new User();
		user.setAccount("111111111");
		user.setPassword("666666");
		user.setUsername("1aaaa1");
		user.setPhone("11235356751");
		
		//更新三种方法
//		session.update(user);//存在则更新，不存在则报错
		session.saveOrUpdate(user);//不存在则插入，存在则更新
//		session.save(user);
		//提交事务
		session.getTransaction().commit();
		
		session.close();
		factory.close();
	}
	
}
