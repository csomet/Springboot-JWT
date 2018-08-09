package com.csomet.springboot.app.auth.service;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.csomet.springboot.app.auth.SecurityConst;
import com.csomet.springboot.app.auth.SimpleGrantedAuthorityMix;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTServiceImpl implements IJWTService {

	@Override
	public String create(Authentication auth) throws IOException {
		
		
		String username = ((User) auth.getPrincipal()).getUsername();
		
		Collection<? extends GrantedAuthority> roles= auth.getAuthorities();
		Claims claims = Jwts.claims();
		
		claims.put("authorities", new ObjectMapper().writeValueAsString(roles));
		
		//create JWT
		String token = Jwts.builder()
							.setClaims(claims)
							.setSubject(username)
							.signWith(SignatureAlgorithm.HS512, SecurityConst.SECRET_KEY.getBytes())
							.setIssuedAt(new Date())
							.setExpiration(new Date(System.currentTimeMillis() + SecurityConst.EXP_DATE))
							.compact();
		return token;
	}

	@Override
	public boolean validate(String token) {
		
		try {
			getClaims(token);
			 
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
		return true;
	}

	@Override
	public Claims getClaims(String token) {
		Claims claims = null;
		claims = Jwts.parser()
				.setSigningKey(SecurityConst.SECRET_KEY.getBytes())
				.parseClaimsJws(prepareToken(token))
				.getBody();
		
		return claims;
	}

	@Override
	public String username(String token) {
		
		return getClaims(token).getSubject();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(String token) throws IOException {
		
		Object roles = getClaims(token).get("authorities");
		
		Collection<? extends GrantedAuthority> authorities = 
				java.util.Arrays.asList(new ObjectMapper()
						.addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMix.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
		
		return authorities;
	}

	@Override
	public String prepareToken(String token) {
		if(token != null && token.startsWith(SecurityConst.TOKEN_PREFIX))
			return token.replace(SecurityConst.TOKEN_PREFIX, "");
		else
			return null;
	}

}
