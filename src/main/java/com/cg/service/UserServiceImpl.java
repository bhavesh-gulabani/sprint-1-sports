package com.cg.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.bean.Admin;
import com.cg.bean.JwtRequest;
import com.cg.bean.JwtResponse;
import com.cg.bean.User;
import com.cg.config.JwtTokenUtil;
import com.cg.dao.IUserRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.WrongCredentialsException;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	@Override	
	public User signIn(User user) throws WrongCredentialsException, ResourceNotFoundException {
		if (user == null)
			return null;
		
		// Check if user exists
		User returnedUser = userRepository.findByEmail(user.getEmail());
		if (returnedUser== null) 
			throw new ResourceNotFoundException("User not found");
		
		// Check if passwords match
	
		if (bcryptEncoder.matches(user.getPassword(), returnedUser.getPassword())) {
				return returnedUser;
			}
		
		throw new WrongCredentialsException("Incorrect credentials");
	}

	// signOut returns the user object if successfully signed out else returns null
	@Override
	public User signOut(User user) {
		return userRepository.findByEmail(user.getEmail());
	}

	// changePassword returns the user object if successfully changed password else returns null
	@Override
	public User changePassword(long id, User user) throws ResourceNotFoundException {	
		getUserById(id);	// to check whether user exists
		return userRepository.save(user);
	}
	
	@Override
	public List<User> getAllUsers() {
		List<User> userList = (List<User>) userRepository.findAll();		
		return userList;
	}

	@Override
	public User getUserById(long userId) throws ResourceNotFoundException {
		Optional<User> userOptional = userRepository.findById(userId);
		return userOptional.orElseThrow(() -> new ResourceNotFoundException("User details not found"));
	}

	@Override
	public User addUser(User user) throws UserAlreadyExistsException {
		List< @NotBlank User> userList = getAllUsers();
		for (User systemUser : userList) {
			if (systemUser.getEmail().equals(user.getEmail()))
				throw new UserAlreadyExistsException("User Already exists");
		}
		return userRepository.save(user);
	}

	public User removeUser(long id) throws ResourceNotFoundException {
		User customerToBeRemoved = getUserById(id);
		userRepository.deleteById(id);
		return customerToBeRemoved;
	}
	
	public Admin updateUser(Admin admin) throws ResourceNotFoundException {
		getUserById(admin.getId());	// to check whether the user exists
		return userRepository.save(admin); 
	}
	

	// Authenticate user and create JWT authentication token
	public JwtResponse createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return new JwtResponse(token);
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
}