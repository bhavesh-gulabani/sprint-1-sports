package com.cg;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.cg.bean.Order;
import com.cg.bean.Payment;
import com.cg.bean.User;
import com.cg.dao.ICustomerRepository;
import com.cg.dao.IUserRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.WrongCredentialsException;
import com.cg.service.CustomerServiceImpl;
import com.cg.service.UserServiceImpl;

@SpringBootTest
class CustomerTest {
	
	@Mock
	ICustomerRepository customerRepo;
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@Mock
	IUserRepository userRepo;
	
	@InjectMocks
	UserServiceImpl userService;
	
	List<Customer> customerList;
	Customer customer1, customer2, customer3;
	Set<Order> orders;
	Order order1, order2;
	Payment payment1;
	
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
		customer1 = new Customer("john@gmail.com", "passjohn", "customer",
				"John", "9876543210", LocalDate.of(1989, 02, 02), "",
				new Address("D101", "Wakad street", "Wakad", "Pune", "Maharashtra", 123456), "Valid", null, null);
//		customer2 = new Customer("jane@gmail.com", "passjane", "customer", 
//				"Jane", "01234567890", LocalDate.of(1994, 8, 16),
//				new Address("D102", "Aundh street", "Aundh", "Pune", "Maharashtra", 654321), null, "", "Valid");
//		customer3 = new Customer("david@gmail.com", "passdavid", "customer", 
//				"David", "6845321489", LocalDate.of(1998, 4, 18),
//				new Address("D103", "Hinjewadi street", "Hinjewadi", "Pune", "Maharashtra", 564123), null, "", "Valid");
//		
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);
		
		orders = new HashSet<>();
//		order1 = new Order(301, 1000, null, customer1, null);
//		order2 = new Order(302, 2000, null, customer1, null);
//		
		// Linking payment to order
		payment1 = new Payment("cash", "paid", null, null);
		order1.setPayment(payment1);
		
		orders.add(order1);
		orders.add(order2);
		
		// Linking orders to customer
		customer1.setOrders(orders);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	public void getAllCustomersTest() throws ResourceNotFoundException {
		when(customerRepo.findAll()).thenReturn(customerList);
		Assertions.assertEquals(3, customerService.getAllCustomers().size());
	}
	
	@Test
	public void getCustomerTest() throws ResourceNotFoundException {
		when(customerRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(customer1, customerService.getCustomer(customer1.getId()));
	}
	
	@Test
	public void addCustomerTest() {
		when(customerRepo.save(customer1)).thenReturn(customer1);
		Assertions.assertEquals(customer1, customerService.addCustomer(customer1));
	}
	
	@Test
	public void removeCustomerTest() throws ResourceNotFoundException {
		when(customerRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(customer1, customerService.removeCustomer(customer1.getId()));
	}
	
	@Test
	public void updateCustomerTest() throws ResourceNotFoundException {
		when(customerRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		when(customerRepo.save(customer1)).thenReturn(customer3);
		Assertions.assertEquals(customer3, customerService.updateCustomer(customer1));
	}
	
	@Test
	public void getAllOrdersTest() {
		Assertions.assertEquals(orders, customer1.getOrders());
	}
	
	@Test
	public void getOrderDetailsTest() throws ResourceNotFoundException {
		when(customerRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(order1, customerService.getOrderDetails(customer1.getId(), order1.getId()));
	}
	
	@Test
	public void getPaymentDetailsTest() throws ResourceNotFoundException {
		when(customerRepo.findById(customer1.getId())).thenReturn(Optional.of(customer1));
		Assertions.assertEquals(payment1, customerService.getPaymentDetails(customer1.getId(), order1.getId()));
	}
	
	@Test
	public void customerSignInTest() throws WrongCredentialsException, ResourceNotFoundException {
		List<User> userList = new ArrayList<>();
		userList.add(customer1);
		
		when(userRepo.findAll()).thenReturn(userList);
		Assertions.assertEquals(customer1, userService.signIn(customer1));
	}
	
	@Test
	public void customerSignOutTest() {
		when(userRepo.existsById(customer1.getId())).thenReturn(true);
		Assertions.assertEquals(customer1, userService.signOut(customer1));
	}
}