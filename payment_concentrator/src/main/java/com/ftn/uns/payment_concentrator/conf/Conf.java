package com.ftn.uns.payment_concentrator.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.ftn.uns.payment_concentrator.security.CustomUserDetailsService;
import com.ftn.uns.payment_concentrator.security.JwtAuthenticationEntryPoint;
import com.ftn.uns.payment_concentrator.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Conf extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean(/* BeanIds.AUTHENTICATION_MANAGER */)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.csrf()
			.disable()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.antMatchers("/api/auth/login").permitAll()
			.antMatchers("/api/auth/register").permitAll()
			.antMatchers("/api/auth/registerAuthor").hasAnyAuthority("ADMIN")
			.antMatchers("/api/auth/registerRedactor").hasAnyAuthority("ADMIN")
			
			.antMatchers(HttpMethod.GET,"/api/articles").permitAll()
			.antMatchers(HttpMethod.GET,"/api/articles/{id}").permitAll()
			.antMatchers(HttpMethod.POST,"/api/articles").hasAnyAuthority("AUTOR","UREDNIK","ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/articles/{id}").hasAnyAuthority("AUTOR","UREDNIK","ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/articles/{id}").hasAnyAuthority("AUTOR","UREDNIK","ADMIN")	// ako se brisu casopisi brisu se i artikli
			
			.antMatchers("/api/bitcoin/**").authenticated()
			.antMatchers("/api/card/**").authenticated()
			
			.antMatchers(HttpMethod.GET,"/api/magazines").permitAll()
			.antMatchers(HttpMethod.GET,"/api/magazines/{issn}").permitAll()
			.antMatchers(HttpMethod.POST,"/api/magazines").hasAnyAuthority("UREDNIK","ADMIN")
			.antMatchers(HttpMethod.PUT,"/api/magazines/{issn}").hasAnyAuthority("UREDNIK","ADMIN")
			.antMatchers(HttpMethod.DELETE,"/api/magazines/{issn}").hasAnyAuthority("UREDNIK","ADMIN")
			
			.antMatchers("/api/merchants/{id}").authenticated()
			.antMatchers("/api/orders/**").authenticated()
			
			.antMatchers(HttpMethod.GET,"/api/mpayment_methods").authenticated()
			.antMatchers("/api/mpayment_methods/{id}").authenticated()
			.antMatchers(HttpMethod.POST,"/api/mpayment_methods").hasAuthority("ADMIN")
			
			.antMatchers("/api/paypal/make/payment").authenticated()
			.antMatchers("/api/paypal/complete/payment").authenticated()
			.antMatchers("/api/paypal/payMembership/{issn}").hasAnyAuthority("UREDNIK")
			.antMatchers("/api/paypal/setMembership/{issn}").hasAnyAuthority("UREDNIK")
			
			.antMatchers("/api/user").hasAnyAuthority("ADMIN")
			.antMatchers("/api/user/getCart").authenticated()
			.antMatchers("/api/user/addArticleToCart/{id}").authenticated()
			.antMatchers("/api/user/addMagazineToCart/{id}").authenticated()
			.antMatchers("/api/user/removeArticleFromCart/{id}").authenticated()
			.antMatchers("/api/user/removeMagazineFromCart/{id}").authenticated()
			.antMatchers("h2/**").authenticated()
			;
		http.headers().frameOptions().disable();
		http.addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
