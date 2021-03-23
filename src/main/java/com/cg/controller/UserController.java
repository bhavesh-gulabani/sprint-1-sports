package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cg.bean.User;
import com.cg.exception.UserNotFoundException;
import com.cg.service.IUserService;

@Controller
//@RequestMapping("/rest")
public class UserController 
{
	
	@Autowired
	IUserService userService;
	
	//Working
	// Change Password
	@PutMapping("user/users/{id}")
	public ResponseEntity<User> changePassword(@PathVariable long id, @RequestBody User user) throws UserNotFoundException
	{
		return new ResponseEntity<User>(userService.changePassword(id, user), HttpStatus.OK);		
	}
	
	
	
	
	
	
	
	
	

}
