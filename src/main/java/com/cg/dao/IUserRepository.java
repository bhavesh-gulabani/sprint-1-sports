package com.cg.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
	@Query("SELECT user from User user WHERE user.email= ?1")
	public User findByEmail(String email);
}
