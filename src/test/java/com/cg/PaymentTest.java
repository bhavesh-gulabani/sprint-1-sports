package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.bean.Payment;
import com.cg.dao.IPaymentRepository;
import com.cg.service.PaymentServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class PaymentTest {
	
	List<Payment> paymentList; 
	Payment payment1 = new Payment("Cash", "Paid", null, null);
	Payment payment2 = new Payment("NetBanking", "Paid", null, null);
	Payment payment3 = new Payment("UPI", "Paid", null, null);
	Payment payment4 = new Payment("Cash", "Not Paid", null, null);

	@Mock
	IPaymentRepository paymentRepoMock;
	
	@InjectMocks
	PaymentServiceImpl paymentService;
	
	@BeforeEach
	void setUp() throws Exception
	{
		paymentList = new ArrayList<>();
		paymentList.add(payment1);
		paymentList.add(payment2);
		paymentList.add(payment3);	
	}
	
	@Test
	public void addPaymentTest() {
		when(paymentRepoMock.save(payment1)).thenReturn(payment1);
		assertEquals(payment1, paymentService.addPayment(payment1));
	}
	
	@Test
	public void removePaymentTest() {
		when(paymentRepoMock.findById(payment2.getId())).thenReturn(Optional.of(payment2));
		assertEquals(payment2, paymentService.removePayment(payment2.getId()));
	}
	
	@Test
	public void updatePaymentTest() {
		when(paymentRepoMock.findById(payment3.getId())).thenReturn(Optional.of(payment3));
		when(paymentRepoMock.save(payment3)).thenReturn(payment3);
		assertEquals(payment3, paymentService.updatePayment(payment3));
	}
	
	@Test
	public void getPaymentDetailsTest() {
		when(paymentRepoMock.findById(payment4.getId())).thenReturn(Optional.of(payment4));
		assertEquals(payment4, paymentService.getPaymentDetails(payment4.getId()));
	}
	
	@Test
	public void getAllPaymentDetailsTest() {
		when(paymentRepoMock.findAll()).thenReturn(paymentList);
		assertEquals(3, paymentService.getAllPaymentDetails().size());
	}

}