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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.uns.payment_concentrator.controller.BTCController;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.PaymentType;
import com.ftn.uns.payment_concentrator.repository.ArticleRepository;
import com.ftn.uns.payment_concentrator.repository.MagazineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BitCoinPayingTest {

	@Autowired
	private BTCController bTCController;
	
	@Resource
	private ArticleRepository articleRepository;
	
	@Resource
	private MagazineRepository magazineRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Test
	public void checkIfCOntrollerIsNull() throws Exception {
		assertThat(bTCController).isNotNull();
	}
	
	@Test
	public void createArticlePayment() throws Exception { 
		Optional<Article> article = articleRepository.findById(Long.valueOf(12));
		Order order = new Order();
		order.setAmount(4.2);
		order.setExecuted(false);
		order.setMerchantPassword("merchantPass");
		order.setMerchantTimestamp(new Date());
		order.setPaymentType(PaymentType.PAYPAL);
		order.setArticle(article.get());
		order.setMerchantId(article.get().getAuthor());
		Map<String, Object> returnedValue = bTCController.createOrder(order);
		System.out.println(returnedValue.get("status"));
		boolean flag = returnedValue.get("redirect_url").toString().contains("https://sandbox.coingate.com/invoice");
		assertThat(flag).isEqualTo(true);
		assertThat(returnedValue.get("status")).isEqualTo("success");
	}
	
	@Test
	public void completeArticlePayment() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("sima","sima"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/bitcoin/make/payment");
		request.addParameter("paymentId", "OVDE UNESI");
		request.addParameter("payerId", "OVDE UNESI");
		ResponseEntity<?> returnedValue = bTCController.complete(request);
		assertThat(returnedValue).isNotNull();
	}
	
	@Test
	public void createMagazinePayment() throws Exception { 
		Optional<Magazine> magazine = magazineRepository.findById("93545180");
		Order order = new Order();
		order.setAmount(4.2);
		order.setExecuted(false);
		order.setMerchantPassword("merchantPass");
		order.setMerchantTimestamp(new Date());
		order.setPaymentType(PaymentType.PAYPAL);
		order.setMagazine(magazine.get());
		order.setMerchantId(magazine.get().getAuthor());
		Map<String, Object> returnedValue = bTCController.createOrder(order);
		System.out.println(returnedValue.get("status"));
		boolean flag = returnedValue.get("redirect_url").toString().contains("https://sandbox.coingate.com/invoice");
		assertThat(flag).isEqualTo(true);
		assertThat(returnedValue.get("status")).isEqualTo("success");
	}
	
	@Test
	public void completeMagazinePayment() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("sima","sima"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/bitcoin/make/payment");
		request.addParameter("paymentId", ".........");
		request.addParameter("payerId", ".................");
		ResponseEntity<?> returnedValue = bTCController.complete(request);
		assertThat(returnedValue).isNotNull();
	}
}
