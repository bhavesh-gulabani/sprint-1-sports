package com.cg.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.dao.IUserRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	      com.cg.bean.User user =  userRepository.findByEmail(email);
	      if (user.getEmail().equals(email)) {
	    	  return  new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
	      } else {
				throw new UsernameNotFoundException("User not found with email: " + email);
	      }

}
}