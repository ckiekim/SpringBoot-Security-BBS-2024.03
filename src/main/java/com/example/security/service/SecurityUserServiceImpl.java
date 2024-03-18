package com.example.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dao.SecurityUserDao;
import com.example.security.entity.SecurityUser;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {
	@Autowired private SecurityUserDao securityDao;
	
	@Override
	public SecurityUser findBySid(int sid) {
		return securityDao.findBySid(sid);
	}

	@Override
	public SecurityUser findByUid(String uid) {
		return securityDao.findByUid(uid);
	}

	@Override
	public SecurityUser findByEmail(String email) {
		return securityDao.findByEmail(email);
	}

	@Override
	public List<SecurityUser> getSecurityUserList(int page) {
		int count = 10;
		int offset = (page - 1) * 10;
		return securityDao.getSecurityUserList(count, offset);
	}

	@Override
	public void insertSecurityUser(SecurityUser securityUser) {
		securityDao.insertSecurityUser(securityUser);
	}

	@Override
	public void updateSecurityUser(SecurityUser securityUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteSecurityUser(int sid) {
		// TODO Auto-generated method stub
		
	}

}
