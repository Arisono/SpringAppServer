package org.springframework.samples.mvc.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity  
@Table(name = "tbl_user")  
public class User {  
	/** 自增 用于MySQL */  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id", nullable = false)    
    private Long id;   
    
	@Column(name = "username", nullable = false)    
    private String username;
    
	@Column(name = "password", nullable = false)    
    private String password;
	
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL )//指向多的那方的pojo的关联外键字
	@JoinColumn(name="user_ID")
	private List<News> news=new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = (long) id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
    
	
	
    
    
}  