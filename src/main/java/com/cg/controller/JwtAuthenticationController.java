package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.JwtRequest;
import com.cg.service.IUserService;


@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired IUserService userService;

	@PostMapping({"/users/authenticate"})
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		return new ResponseEntity<>(userService.createAuthenticationToken(authenticationRequest), HttpStatus.OK);
	}
}