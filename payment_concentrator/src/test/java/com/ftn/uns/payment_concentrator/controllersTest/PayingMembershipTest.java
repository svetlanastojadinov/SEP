package com.ftn.uns.payment_concentrator.controllersTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftn.uns.payment_concentrator.controller.PayPalController;
import com.ftn.uns.payment_concentrator.model.Membership;
import com.ftn.uns.payment_concentrator.repository.ArticleRepository;
import com.ftn.uns.payment_concentrator.repository.MagazineRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PayingMembershipTest {
	
	@Autowired
	private PayPalController paypalController;
	
	@Resource
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
	public void payMembershipTest() throws Exception {
		Map<String, Object> ret = paypalController.payMembership("14509636");
		boolean flag = ret.get("redirect_url").toString().contains("https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=");
		assertThat(flag).isEqualTo(true);
		assertThat(ret.get("status")).isEqualTo("success");
	}
	
	@Test
	public void setMembershipTest() throws Exception {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("sima","sima"));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		ResponseEntity<Membership> ret = paypalController.setMembership("14509636");
		assertThat(ret.getBody().getPayDay()).isEqualTo(Date.valueOf(LocalDate.now().plusMonths(1)));
		assertThat(ret.getBody().getMagazine().getIssn()).isEqualTo("14509636");
		//System.out.println(ret);
	}
	
	
}
