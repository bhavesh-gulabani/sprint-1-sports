package com.cg;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import com.cg.controller.CustomerController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Sprint1SportsApplicationTests {

	@Autowired
	CustomerController customerController;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(customerController);
	}

}