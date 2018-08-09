package com.csomet.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.csomet.springboot.app.auth.filter.JWTAuthenticationFilter;
import com.csomet.springboot.app.auth.filter.JWTAuthorizationFilter;
import com.csomet.springboot.app.auth.service.IJWTService;
import com.csomet.springboot.app.services.JPAUserDetailsService;


@EnableGlobalMethodSecurity(securedEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JPAUserDetailsService JPAUserDetailsService;
	
	@Autowired
	private IJWTService jwtService;
	
	
	@Autowired
	public void configGlobal(AuthenticationManagerBuilder build) {
		
		//Spring security JDBC
		try {
			
			//With JPA
			build.userDetailsService(JPAUserDetailsService)
			.passwordEncoder(passwordEncoder);
			
		
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.authorizeRequests().antMatchers("/").permitAll()
		.anyRequest().authenticated()
		//.and()
		//.formLogin().loginPage("/login")
		//.permitAll()
		//and()
		//.logout().permitAll()
		.and()
		 .addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
		 .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

}
