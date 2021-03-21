package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.User;
import com.cg.dao.IUserRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepo;

	// signIn returns the user object if successfully signedIn else returns null
	@Override
	public User signIn(User user) {
		if (user == null)
			return null;
		
		List<User> userList = getAllUsers();
		for (User systemUser : userList) {
			if ( (systemUser.getUsername().equals(user.getUsername())) && (systemUser.getPassword().equals(user.getPassword())) ) {
				System.out.println("Successfully signed in...");
				return user;
			}
		}
		return null;
	}

	// signOut returns the user object if successfully signed out else returns null
	@Override
	public User signOut(User user) {
		if(userRepo.existsById(user.getId())) {
			System.out.println("Successfully signed out...");
			return user;
		}
		return null;
	}

	// changePassword returns the user object if successfully changed password else returns null
	@Override
	public User changePassword(long id, User user) {	
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isEmpty())
			return null;
		
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> UserList = (List<User>) userRepo.findAll();
		return UserList;
	}

	@Override
	public User getUserById(long userId) {
		Optional<User> userOptional = userRepo.findById(userId);
		return userOptional.isEmpty() ? null : userOptional.get();
	}
}

