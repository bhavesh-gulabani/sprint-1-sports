package com.cg.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.bean.User;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.WrongCredentialsException;
import com.cg.exception.UserAlreadyExistsException;

@Service
public interface IUserService {
	public User signIn(User user) throws WrongCredentialsException, ResourceNotFoundException;
	public User signOut(User user);
	public User changePassword(long id, User user) throws ResourceNotFoundException;
	public List<User> getAllUsers();
	public User getUserById(long custId) throws ResourceNotFoundException;
	public User removeUser(long id) throws ResourceNotFoundException;
	public User addUser(User user) throws UserAlreadyExistsException;
	public Admin updateUser(Admin admin) throws ResourceNotFoundException;
}
