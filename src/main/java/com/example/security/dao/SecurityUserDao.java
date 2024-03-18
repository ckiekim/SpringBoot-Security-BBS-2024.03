package com.example.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.security.entity.SecurityUser;

@Mapper
public interface SecurityUserDao {

	@Select("select * from securityUser where suid=#{suid}")
	SecurityUser findBySuid(int sid);
	
	@Select("select * from securityUser where uid=#{uid}")
	SecurityUser findByUid(String uid);
	
	@Select("select * from securityUser where email=#{email}")
	SecurityUser findByEmail(String email);
	
	@Select("select * from securityUser where isDeleted=0 order by sid"
			+ " limit #{count} offset #{offset}")
	List<SecurityUser> getSecurityUserList(int count, int offset);
	
	@Insert("insert into securityUser values (default, #{uid}, #{pwd}, #{uname}, #{email}, "
			+ "#{provider}, default, default, default, #{profile}, #{github}, #{insta}, #{location})")
	void insertSecurityUser(SecurityUser securityUser);
	
}
