package com.cg;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.cg.bean.Order;
import com.cg.dao.IOrderRepository;
import com.cg.exception.OrderNotFoundException;
import com.cg.service.OrderServiceImpl;

@SpringBootTest
public class OrderTest {

		@Mock
		IOrderRepository orderRepo;
		
		@InjectMocks
		OrderServiceImpl orderService;
		
		List <Order> orderList;
		Order order1,order2,order3;
		
		
		Map<Integer, Integer> cart = new HashMap<>();
		
		
	@SuppressWarnings("deprecation")
	@Before(value="")
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
	void setUp() throws Exception{
		
		orderList = new ArrayList<>();
		cart.put(101,5);
		cart.put(102,10);
		
		order1 = new Order(10000, LocalDate.of(1989, 02, 02), cart, new Customer("userjohn", "passjohn", "customer", 
				"John", "john@gmail.com", "9876543210", LocalDate.of(1989, 02, 02),
				new Address("D101", "Wakad street", "Wakad", "Pune", "Maharashtra", 123456), null), null );
		
		order2 = new Order(20000, LocalDate.of(1994, 8, 16), cart, new Customer("userjane", "passjane", "customer", 
				"Jane", "jane@gmail.com", "01234567890", LocalDate.of(1994, 8, 16),
				new Address("D102", "Aundh street", "Aundh", "Pune", "Maharashtra", 654321), null), null ); 
		
		order3 = new Order(30000, LocalDate.of(1998, 4, 18), cart,new Customer("userdavid", "passdavid", "customer", 
				"David", "david@gmail.com", "6845321489", LocalDate.of(1998, 4, 18),
				new Address("D103", "Hinjewadi street", "Hinjewadi", "Pune", "Maharashtra", 564123), null), null );
		
		orderList.add(order1);
		orderList.add(order2);
		orderList.add(order3);
		
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	public void getAllOrdersTest() {
		when(orderRepo.findAll()).thenReturn(orderList);
		Assertions.assertEquals(3, orderService.getAllOrders().size());
	}
	
	@Test
	public void getOrderTest() throws OrderNotFoundException {
		when(orderRepo.findById(order1.getId())).thenReturn(Optional.of(order1));
		Assertions.assertEquals(order1, orderService.getOrderDetails(order1.getId()));
	}
	
	@Test
	public void addOrderTest() {
		when(orderRepo.save(order1)).thenReturn(order1);
		Assertions.assertEquals(order1, orderService.addOrder(order1));
	}
	
	@Test
	public void removeOrderTest() throws OrderNotFoundException {
		when(orderRepo.findById(order1.getId())).thenReturn(Optional.of(order1));
		Assertions.assertEquals(order1, orderService.removeOrder(order1.getId()));
	}
	
	@Test
	public void updateOrderTest() throws OrderNotFoundException {
		when(orderRepo.findById(order1.getId())).thenReturn(Optional.of(order1));
		when(orderRepo.save(order1)).thenReturn(order3);
		Assertions.assertEquals(order3, orderService.updateOrder(order1));
	}
	
	
}
