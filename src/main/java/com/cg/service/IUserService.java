package com.cg.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.cg.bean.User;

@Service
public interface IUserService {
	public User signIn(User user);
	public User signOut(User user);
	public User changePassword(long id, User user);
	public List<User> getAllUsers();
	public User getUserById(long custId);
}