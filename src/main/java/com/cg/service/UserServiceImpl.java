package com.cg.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.bean.User;
import com.cg.dao.IUserRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.WrongCredentialsException;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepo;
	
	// signIn returns the user object if successfully signedIn else returns null
	@Override	
	public User signIn(User user) throws WrongCredentialsException, ResourceNotFoundException {
		if (user == null)
			return null;
		
		List< @NotBlank User> userList = getAllUsers();
		for (User systemUser : userList) {
			if ( (systemUser.getUsername().equals(user.getUsername())) && (systemUser.getPassword().equals(user.getPassword())) ) {
				return user;
			}
		}
		throw new WrongCredentialsException("Incorrect credentials");
	}

	// signOut returns the user object if successfully signed out else returns null
	@Override
	public User signOut(User user) {
		if(userRepo.existsById(user.getId())) {
			return user;
		}
		return null;
	}

	// changePassword returns the user object if successfully changed password else returns null
	@Override
	public User changePassword(long id, User user) throws ResourceNotFoundException {	
		getUserById(id);	// to check whether user exists
		return userRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = (List<User>) userRepo.findAll();		
		return userList;
	}

	@Override
	public User getUserById(long userId) throws ResourceNotFoundException {
		Optional<User> userOptional = userRepo.findById(userId);
		return userOptional.orElseThrow(() -> new ResourceNotFoundException("User details not found"));
	}

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		List< @NotBlank User> userList = getAllUsers();
		for (User systemUser : userList) {
			if (systemUser.getUsername().equals(user.getUsername()))
				throw new UserAlreadyExistsException("User Already exists");
		}
		return userRepo.save(user);
	}

	public User removeUser(long id) throws ResourceNotFoundException {
		User customerToBeRemoved = getUserById(id);
		userRepo.deleteById(id);
		return customerToBeRemoved;
	}
	
	public Admin updateUser(Admin admin) throws ResourceNotFoundException {
		getUserById(admin.getId());	// to check whether the user exists
		return userRepo.save(admin); 
	}
}