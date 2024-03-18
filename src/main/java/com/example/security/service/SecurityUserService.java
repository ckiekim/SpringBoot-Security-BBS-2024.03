package com.example.security.service;

import java.util.List;

import com.example.security.entity.SecurityUser;

public interface SecurityUserService {

	SecurityUser findBySuid(int suid);
	
	SecurityUser findByUid(String uid);
	
	SecurityUser findByEmail(String email);
	
	List<SecurityUser> getSecurityUserList(int page);
	
	void insertSecurityUser(SecurityUser securityUser);
	
	void updateSecurityUser(SecurityUser securityUser);
	
	void deleteSecurityUser(int sid);
	
}
