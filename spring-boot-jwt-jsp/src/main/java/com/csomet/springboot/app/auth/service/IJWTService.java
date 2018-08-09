package com.csomet.springboot.app.auth.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;

public interface IJWTService {
	
	public String create(Authentication auth)  throws IOException;
	public boolean validate(String token);
	public Claims getClaims(String token);
	public String username(String token);
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException;
	public String prepareToken(String token);

}
