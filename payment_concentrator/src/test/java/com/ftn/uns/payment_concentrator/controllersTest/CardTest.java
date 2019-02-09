package com.ftn.uns.payment_concentrator.controllersTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.uns.payment_concentrator.controller.CardController;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.PaymentType;
import com.ftn.uns.payment_concentrator.repository.MagazineRepository;
import com.ftn.uns.payment_concentrator.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CardTest {

	@Autowired
	private CardController cardController;
	
	
	@Resource
	private MagazineRepository magazineRepository;
	
	@Resource
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Test
	public void checkIfCOntrollerIsNull() throws Exception {
		assertThat(cardController).isNotNull();
	}
	
	@Test
	public void testBankPayment() throws Exception { 
		Order order = new Order();
		Optional<Magazine> magazine = magazineRepository.findById("93545180");
		order.setAmount(50.0);
		order.setBuyerUsername("sima");
		order.setExecuted(false);
		order.setMagazine(magazine.get());
		order.setMerchantId("pera");
		order.setMerchantPassword("merchantPass");
		order.setDate(new Date());
		order.setPaymentType(PaymentType.CARD);
		
		Map<String, Object> ret = cardController.createOrder(order);
		assertThat(ret).isNotNull();
		assertThat(ret.get("status")).isEqualTo("success");
		assertThat(ret.get("redirect_url")).isNotNull();
		System.out.println();
	}
	
	@Test
	public void testBankPaymentComplete() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("pera","pera"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setParameter("merchantOrderId", "1000000001");
		Map<String, Object> ret = cardController.complete(request);
		assertThat(ret.get("status")).isEqualTo("success");
	}
	
	
	
}
