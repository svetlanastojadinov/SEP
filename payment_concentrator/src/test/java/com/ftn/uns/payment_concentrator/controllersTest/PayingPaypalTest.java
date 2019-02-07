package com.ftn.uns.payment_concentrator.controllersTest;

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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import com.ftn.uns.payment_concentrator.controller.PayPalController;
import com.ftn.uns.payment_concentrator.model.Article;
import com.ftn.uns.payment_concentrator.model.Magazine;
import com.ftn.uns.payment_concentrator.model.Order;
import com.ftn.uns.payment_concentrator.model.PaymentType;
import com.ftn.uns.payment_concentrator.repository.ArticleRepository;
import com.ftn.uns.payment_concentrator.repository.MagazineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PayingPaypalTest {
	
	@Autowired
	private PayPalController paypalController;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Resource
	private MagazineRepository magazineRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Test
	public void checkIfCOntrollerIsNull() throws Exception {
		assertThat(paypalController).isNotNull();
	}
	
	@Test
	public void createArticlePayment() throws Exception {
		Optional<Article> article = articleRepository.findById(Long.valueOf(1));
		Order order = new Order();
		order.setAmount(4.2);
		order.setExecuted(false);
		order.setMerchantPassword("merchantPass");
		order.setMerchantTimestamp(new Date());
		order.setPaymentType(PaymentType.PAYPAL);
		order.setArticle(article.get());
		order.setMerchantId(article.get().getAuthor());
		Map<String, Object> returnedValue = paypalController.makePayment(order);
		System.out.println(returnedValue.get("status"));
		boolean flag = returnedValue.get("redirect_url").toString().contains("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=");
		assertThat(flag).isEqualTo(true);
		assertThat(returnedValue.get("status")).isEqualTo("success");
	}
	
	@Test
	public void completeArticlePayment() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("sima","sima"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/paypal/complete/payment");
		request.addParameter("paymentId", "PAYID-LROEDJY60V14309UM549861T");
		request.addParameter("payerId", "ZXD2XFZZY73PN");
		ResponseEntity<?> returnedValue = paypalController.completePayment(request);
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
		Map<String, Object> returnedValue = paypalController.makePayment(order);
		System.out.println();
		boolean flag = returnedValue.get("redirect_url").toString().contains("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=");
		assertThat(flag).isEqualTo(true);
		assertThat(returnedValue.get("status")).isEqualTo("success");
	}
	
	@Test
	public void completeMagazinePayment() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("sima","sima"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/api/paypal/complete/payment");
		request.addParameter("paymentId", "PAYID-LROEOMY4SY470643X0609917");
		request.addParameter("payerId", "ZXD2XFZZY73PN");
		ResponseEntity<?> returnedValue = paypalController.completePayment(request);
		assertThat(returnedValue).isNotNull();
	}

}
