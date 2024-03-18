package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(auth -> auth.disable())
			.authorizeHttpRequests(auth -> auth			// SpringSecurity version6 부터 람다식 사용
				.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
				.requestMatchers("/user/register", "/user/login",
						"/img/**", "/css/**", "/js/**", "/error/**").permitAll()
				.requestMatchers("/admin/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(auth -> auth
				.loginPage("/user/login")				// 로그인 폼
				.loginProcessingUrl("/user/login")		// Spring Security가 낚아챔, UserDetailsService 구현객체에서 처리해주어야 함
				.usernameParameter("uid")
				.passwordParameter("pwd")
				.defaultSuccessUrl("/user/list", true)
				.permitAll()
			)
			.logout(auth -> auth
				.logoutUrl("/user/logout")
				.invalidateHttpSession(true)		// 로그아웃시 세션 초기화
				.deleteCookies("JSESSIONID")		// 로그아웃시 쿠키 삭제
				.logoutSuccessUrl("/user/login")
			);
		
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
