package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Admin;
import com.cg.bean.User;
import com.cg.exception.ResourceNotFoundException;
import com.cg.service.IUserService;

@RestController
public class UserController {
	@Autowired
	IUserService userService;
	
	@PutMapping({"/admin/changepassword", "/customers/changepassword"})
	public ResponseEntity<User> changePassword(@RequestBody Admin admin) throws ResourceNotFoundException {
		return new ResponseEntity<>(userService.changePassword(admin.getId(), admin), HttpStatus.OK);		
	}
}