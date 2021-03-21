package com.cg;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bean.Address;
import com.cg.bean.Customer;
import com.cg.dao.ICustomerRepository;
import com.cg.exception.CustomerNotFoundException;
import com.cg.service.CustomerServiceImpl;

@SpringBootTest
class CustomerTest {
	
	@Mock
	ICustomerRepository custRepo;
	
	@InjectMocks
	CustomerServiceImpl custService;
	
	List<Customer> customerList;
	Customer customer1, customer2, customer3;
	
   @SuppressWarnings("deprecation")
   @Before(value = "")
   public void init() {
	   MockitoAnnotations.initMocks(this);
   }
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		customerList = new ArrayList<>();
		customer1 = new Customer("userjohn", "passjohn", "customer", 
				"John", "john@gmail.com", "9876543210", LocalDate.of(1989, 02, 02),
				new Address("D101", "Wakad street", "Wakad", "Pune", "Maharashtra", 123456), null);
		customer2 = new Customer("userjane", "passjane", "customer", 
				"Jane", "jane@gmail.com", "01234567890", LocalDate.of(1994, 8, 16),
				new Address("D102", "Aundh street", "Aundh", "Pune", "Maharashtra", 654321), null);
		customer3 = new Customer("userdavid", "passdavid", "customer", 
				"David", "david@gmail.com", "6845321489", LocalDate.of(1998, 4, 18),
				new Address("D103", "Hinjewadi street", "Hinjewadi", "Pune", "Maharashtra", 564123), null);
		
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void getAllCustomersTest() {
		when(custRepo.findAll()).thenReturn(customerList);
		Assertions.assertEquals(3, custService.getAllCustomers().size());
	}
	
	@Test
	public void getCustomerTest() throws CustomerNotFoundException {
		when(custRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(customer1, custService.getCustomer(customer1.getId()));
	}
	
	@Test
	public void addCustomerTest() {
		when(custRepo.save(customer1)).thenReturn(customer1);
		Assertions.assertEquals(customer1, custService.addCustomer(customer1));
	}
	
	@Test
	public void removeCustomerTest() throws CustomerNotFoundException {
		when(custRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(customer1, custService.removeCustomer(customer1.getId()));
	}
	
	@Test
	public void updateCustomerTest() throws CustomerNotFoundException {
		when(custRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		when(custRepo.save(customer1)).thenReturn(customer3);
		Assertions.assertEquals(customer3, custService.updateCustomer(customer1));
	}
}
