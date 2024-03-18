package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.entity.MyUserDetails;
import com.example.security.entity.SecurityUser;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired private SecurityUserService securityService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SecurityUser securityUser = securityService.findByUid(username);
		
		if (securityUser != null) {
			return new MyUserDetails(securityUser);
		}
		return null;
	}

}
