package com.csomet.springboot.app.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csomet.springboot.app.model.dao.IUserDao;
import com.csomet.springboot.app.model.entity.Authority;
import com.csomet.springboot.app.model.entity.User;


@Service("JPAUserDetailsService")
public class JPAUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserDao userDato;
	
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDato.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("username: " + username + " does not exists!");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(Authority auth : user.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
		}
		
		if (authorities.isEmpty()) {
			throw new UsernameNotFoundException("username: " + username + " has not roles assigned !");
		}
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.isEnabled(), true, true, true, authorities);
	}


}
