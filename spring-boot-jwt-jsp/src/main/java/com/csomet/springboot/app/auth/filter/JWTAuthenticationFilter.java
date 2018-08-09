package com.csomet.springboot.app.auth.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.csomet.springboot.app.auth.SecurityConst;
import com.csomet.springboot.app.auth.service.IJWTService;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private AuthenticationManager authenticationManager;
	private IJWTService jwtService;
	

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,  IJWTService jwtService) {
		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
		this.jwtService = jwtService;
	}



	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String token = jwtService.create(authResult);
		
		response.addHeader(SecurityConst.HEADER, SecurityConst.TOKEN_PREFIX + token);
		
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("message", String.format("%s logged in successfully!", ((User) authResult.getPrincipal()).getUsername() ));
		
		//convert to JSON info data
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		//set content type
		response.setContentType("application/json");
		//set status OK
		response.setStatus(200);
		
	}



	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
	
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		
		
		if (username == null && password == null) {

			//RAW params received (JSON)
			com.csomet.springboot.app.model.entity.User user = null;
			
			try {
				 user = new ObjectMapper().readValue(request.getInputStream(), com.csomet.springboot.app.model.entity.User.class);
				 username = user.getUsername();
				 password = user.getPassword();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		username = username.trim();
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authToken);
	}

	
	
}
