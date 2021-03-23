package com.cg.service;

import java.util.List;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.User;
import com.cg.dao.IUserRepository;
import com.cg.exception.UserNotFoundException;

@Service
@Transactional
public class UserServiceImpl implements IUserService 
{

	@Autowired
	private IUserRepository userRepo;

	// signIn returns the user object if successfully signedIn else returns null
	@Override
	public User signIn(User user) throws UserNotFoundException 
	{
		if (user == null)
			return null;
		List< @NotBlank User> userList = getAllUsers();
		for (User systemUser : userList) 
		{
			if((systemUser.getUsername().equals(user.getUsername())) && (systemUser.getPassword().equals(user.getPassword())) )
			{
				System.out.println("Successfully signed in...");
				return user;
			}			
		}
		return null;
	}
	
	// signOut returns the user object if successfully signed out else returns null
	@Override
	public User signOut(User user) 
	{
			if(userRepo.existsById(user.getId()))
			{
				System.out.println("Successfully signed out...");
				return user;
			}
		
		return null;
	}

	// changePassword returns the user object if successfully changed password else returns null
	@Override
	public User changePassword(long id, User user)  throws UserNotFoundException
	{	
		Optional<User> userOptional = userRepo.findById(id);
		if (userOptional.isEmpty())
			return null;
		
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() 
	{
		List<User> UserList = (List<User>) userRepo.findAll();
		return UserList;
	}

	@Override
	public User getUserById(long userId) throws UserNotFoundException
	{
		Optional<User> userOptional = userRepo.findById(userId);
		return userOptional.orElseThrow(() -> new UserNotFoundException("User details not found"));
	}

	@Override
	public User addUser(User user) {
		return userRepo.save(user);
	}

	public User removeUser(long id) throws UserNotFoundException {
		User customerToBeRemoved = getUserById(id);
		userRepo.deleteById(id);
		return customerToBeRemoved;
	}

}
