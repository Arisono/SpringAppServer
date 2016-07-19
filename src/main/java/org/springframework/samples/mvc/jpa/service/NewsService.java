package org.springframework.samples.mvc.jpa.service;

import javax.annotation.Resource;

import org.springframework.samples.mvc.jpa.dao.JNewsDao;
import org.springframework.samples.mvc.jpa.entity.News;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
	@Resource
	private JNewsDao jnewDao;
	
	public void save(News news){
		jnewDao.save(news);
	}
}
