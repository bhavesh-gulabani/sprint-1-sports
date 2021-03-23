package com.cg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.bean.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {

}
 
