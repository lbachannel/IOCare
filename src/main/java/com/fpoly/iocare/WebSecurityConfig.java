package com.fpoly.iocare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *	@EnableWebSecurity : Kích hoạt tính năng bảo mật trong Spring Security
 */

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

/**
 * 	WebSecurityConfigurerAdapter là một lớp trừu tượng trong Spring Security
 *                               được sử dụng để cấu hình bảo mật cho ứng dụng web
 *                               xác thực, phân quyền, giới hạn truy cập và xác thực CSRF (Cross-site Request Forgery).
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	BCryptPasswordEncoder pe;
	
	/*--Mã hóa mật khẩu--*/
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password(pe.encode("123")).roles("admin")
		.and()
		.withUser("staff").password(pe.encode("123")).roles("user");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable();
//		http.antMatcher("/")
	}
}
