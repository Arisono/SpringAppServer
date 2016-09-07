package org.springframework.web.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.samples.mvc.jpa.dao.JUserDao;
import org.springframework.samples.mvc.jpa.entity.User;
import org.springframework.samples.mvc.jpa.service.TestService;
import org.springframework.samples.mvc.jpa.service.UserService;
import org.springframework.samples.mvc.jpa.service.iml.TestServiceImpl;
import org.springframework.samples.mvc.simple.SimpleController;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.jolbox.bonecp.BoneCPDataSource;

public class main {

	public static void main(String[] args) {
		// 项目根目录
		// ApplicationContext ac = new
		// FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/root-context.xml");
		// BoneCPDataSource bs=(BoneCPDataSource) ac.getBean("dataSource");
		// System.out.println(bs.getJdbcUrl());

		ApplicationContext appCt = new ClassPathXmlApplicationContext("root-context.xml");
		// BoneCPDataSource bs=(BoneCPDataSource)appCt.getBean("dataSource");
		// System.out.println(bs.getJdbcUrl());
		// System.out.println(bs.getUsername());
		// System.out.println(bs.getPassword());

		// 本地测试
//		SimpleController simpleController = appCt.getBean("SimpleController", SimpleController.class);
		// simpleController.simpleFind(62L);
		// simpleController.simpleSave("玛丽11", "123123");
//		JUserDao jUserDao = appCt.getBean("JUserDao", JUserDao.class);
//		System.out.println(JSON.toJSONString(jUserDao.findOne(12L)));
		
		
		TestService testService=(TestServiceImpl)appCt.getBean("TestService", TestServiceImpl.class);
        testService.sys();
	}

}
