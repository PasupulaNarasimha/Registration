package com.edutech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edutech.model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE  u.emailid=:emailid AND u.password=:password")
	List<User> findEmailAndPassword(@Param("emailid") String emailid,@Param("password")String password);
	User findByEmailid(String emailid);
	
}
