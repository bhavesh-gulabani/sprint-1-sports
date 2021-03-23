package com.cg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cg.bean.User;
import com.cg.exception.UserNotFoundException;

@Service
@Transactional
public interface IUserService {
	public User signIn(User user) throws UserNotFoundException;
	public User signOut(User user);
	public User changePassword(long id, User user)  throws UserNotFoundException;
	public List<User> getAllUsers();
	public User getUserById(long id) throws UserNotFoundException;
	public User removeUser(long id) throws UserNotFoundException;
	public User addUser(User user);

}
