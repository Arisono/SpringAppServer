package org.springframework.samples.mvc.jpa.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.mvc.jpa.entity.User;
import org.springframework.stereotype.Repository;

@Repository("JUserDao")
public interface JUserDao  extends JpaSpecificationExecutor<User>,JpaRepository<User, Long>{

	@Query("select u from User u  where username = :userName and password = :passWord")
	public User findByUsernameAnPassword(@Param("userName") String username, @Param("passWord") String password);
}