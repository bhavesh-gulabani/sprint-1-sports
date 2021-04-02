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

import com.cg.bean.Product;
import com.cg.dao.IProductRepository;
import com.cg.exception.ResourceNotFoundException;
import com.cg.exception.EmptyInventoryException;
import com.cg.exception.IncorrectPriceException;
import com.cg.service.ProductServiceImpl;

@SpringBootTest
class ProductTest {
	
	@Mock
	IProductRepository prodRepo;
	
	@InjectMocks
	ProductServiceImpl prodService;
	
	List<Product> prodList;
	Product prod1,prod2,prod3,prod4;
	//for getBy methods
	List<Product>blackProdList, telstarProdList, sizeLProdList, priceProdList, footballProds; 	
	
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
		prodList = new ArrayList<>();
		
		 prod1 = new Product(101,"Carbonex-3000","Head Heavy racquet" ,"Badminton Racquets", "Yonex", "Black", "L",
				3000, 10, 3, LocalDate.of(2021, 03, 25));
		 prod2 = new Product(102,"SB-100","Head Light racquet" ,"Badminton Racquets", "Silvers", "Blue", "M",
				3999, 20, 5, LocalDate.of(2021, 03, 22));
		 prod3 = new Product(103,"Telstar","2010 WC football" ,"Football", "Adidas", "Brown", "S",
				4500, 15, 15, LocalDate.of(2021, 03, 28));
		 prod4 = new Product(104,"Telstar","2010 WC football" ,"Football", "Adidas", "Black", "L",
				4500, 15, 15, LocalDate.of(2021, 03, 28));
		
		prodList.add(prod1);
		prodList.add(prod2);
		prodList.add(prod3);
		prodList.add(prod4);
		
		//List of Products Named Telstar
		telstarProdList = new ArrayList<>();
		telstarProdList.add(prod3);
		telstarProdList.add(prod4);
		
		//List of Products with size L
		sizeLProdList = new ArrayList<>();
		sizeLProdList.add(prod1);
		sizeLProdList.add(prod4);
		
		//List of Products priced 4500
		priceProdList = new ArrayList<>();
		priceProdList.add(prod3);
		priceProdList.add(prod4);
	
		//List of Black Products
		blackProdList = new ArrayList<>();
		blackProdList.add(prod1);
		blackProdList.add(prod4);
		
		//List of products with category Football
		footballProds = new ArrayList<>();
		footballProds.add(prod3);
		footballProds.add(prod4);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test()
	public void addProductTest() throws IncorrectPriceException {
		when(prodRepo.save(prod1)).thenReturn(prod1);
		Assertions.assertEquals(prod1, prodService.addProduct(prod1));
	}
	
	@Test
	public void removeProductTest() throws ResourceNotFoundException {
		when(prodRepo.findById(prod1.getId())).thenReturn(Optional.of(prod1));
		Assertions.assertEquals(prod1.getName()+" deleted from the inventory", prodService.removeProduct(prod1.getId()));
	}
	
	@Test
	public void updateProductTest() throws ResourceNotFoundException, IncorrectPriceException {
		when(prodRepo.findById(prod1.getId())).thenReturn(Optional.of(prod1));
		when(prodRepo.save(prod1)).thenReturn(prod3);
		Assertions.assertEquals(prod3, prodService.updateProduct(prod1.getId(), prod1));
	}
	
	@Test
	public void getProductByIdTest() throws ResourceNotFoundException {
		when(prodRepo.findById(prod1.getId())).thenReturn(Optional.of(prod1));
		Assertions.assertEquals(prod1, prodService.getProductById(prod1.getId()));
	}
	
	@Test
	public void getAllProductsTest() throws EmptyInventoryException {
		when(prodRepo.findAll()).thenReturn(prodList);
		Assertions.assertEquals(4, prodService.getAllProducts().size());
	}	
	
	@Test
	public void getProductsByName() throws ResourceNotFoundException {
		when(prodRepo.findAllByName("Telstar")).thenReturn(telstarProdList);
		Assertions.assertEquals(telstarProdList, prodService.getProductsByName("Telstar"));
	}	
	
	@Test
	public void getProductsBySize() throws ResourceNotFoundException {
		when(prodRepo.findAllBySize("L")).thenReturn(sizeLProdList);
		Assertions.assertEquals(sizeLProdList, prodService.getProductsBySize("L"));
	}
	
	@Test
	public void getProductsByPrice() throws ResourceNotFoundException {
		when(prodRepo.findAllByPriceAfterDiscount(4500)).thenReturn(priceProdList);
		Assertions.assertEquals(priceProdList, prodService.getProductsByPrice(4500));
	}
	
	@Test
	public void getProductsByColor() throws ResourceNotFoundException {		
		when(prodRepo.findAllByColor("Black")).thenReturn(blackProdList);
		Assertions.assertEquals(blackProdList, prodService.getProductsByColor("Black"));
	}
	
	@Test
	public void getbyCategory() throws ResourceNotFoundException {		
		when(prodRepo.findAllByCategory("Football")).thenReturn(footballProds);
		Assertions.assertEquals(footballProds, prodService.getProductsByCategory("Football"));
	}
	
	
}