package com.example.security.controller;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.security.entity.SecurityUser;
import com.example.security.service.SecurityUserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class SecurityUserController {
	@Autowired private SecurityUserService securityService;
	@Autowired private BCryptPasswordEncoder bCryptEncoder;

	@GetMapping("/list") 
	public String list(Model model) {
		return "user/list";
	}
	
	@GetMapping("/login") 
	public String loginForm() {
		return "user/login";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess(HttpSession session) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 세션의 현재 사용자 아이디
		String uid = authentication.getName();
		// 세션의 현재 사용자 role
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		Iterator<? extends GrantedAuthority> iter = authorities.iterator();
		GrantedAuthority auth = iter.next();
		String role = auth.getAuthority();
		
		System.out.println("uid=" + uid + ", role=" + role);
		session.setAttribute("uid", uid);
		session.setAttribute("role", role);
		return "user/sample";
	}
	
	@GetMapping("/register")
	public String registerForm() {
		return "user/register";
	}
	
	@PostMapping("/register")
	public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
		SecurityUser secUser = securityService.findByUid(uid);
		if (secUser != null || pwd == null || !pwd.equals(pwd2))
			return "user/register";
		String hashedPwd = bCryptEncoder.encode(pwd);
		secUser = new SecurityUser(uid, hashedPwd, uname, email, "ck world", "profile path");
		securityService.insertSecurityUser(secUser);
		return "redirect:/user/login";
	}
	
}
