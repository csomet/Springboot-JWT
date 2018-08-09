package com.csomet.springboot.app.auth.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.csomet.springboot.app.auth.SecurityConst;
import com.csomet.springboot.app.auth.service.IJWTService;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private IJWTService jwtService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, IJWTService jwtService) {
		super(authenticationManager);
		this.jwtService = jwtService;
		
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader(SecurityConst.HEADER);

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken auth = null;
		
		if (jwtService.validate(header)) {
			auth = new UsernamePasswordAuthenticationToken(jwtService.username(header), null, jwtService.getAuthorities(header));
		}
		

		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(request, response);
	}
	
	
	protected boolean requiresAuthentication(String header) {
		
		if (header == null || !header.startsWith(SecurityConst.TOKEN_PREFIX)) {
			return false;
		}
		
		return true;
	}

}
