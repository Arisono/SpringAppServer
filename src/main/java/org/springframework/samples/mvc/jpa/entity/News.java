package org.springframework.samples.mvc.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name = "tbl_news")  
public class News  {
	/** 自增 用于MySQL */  
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "id", nullable = false)    
    private Long id;   
    
	@Column(name = "title", nullable = false)    
    private String title;
    
	@Column(name = "content", nullable = false)    
    private String content;
	
//	@ManyToOne(optional = false,fetch = FetchType.LAZY)//设置在“一方”pojo的外键字段上     (fetch = FetchType.LAZY,optional = true,cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
//	@JoinColumn(name = "userId" , referencedColumnName = "id")//设置对应数据表的列名和引用的数据表的列名     , referencedColumnName = "id"
//	private User userId;    
	
	@Column(name = "user_ID")
	private Long userId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

//	public User getUserId() {
//		return userId;
//	}
//
//	public void setUserId(User userId) {
//		this.userId = userId;
//	}


	
	
}
