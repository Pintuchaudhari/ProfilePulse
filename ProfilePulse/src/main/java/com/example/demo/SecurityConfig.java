package com.example.demo;

import org.springframework.context.annotation.Bean;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.services.CustAuthService;
@Configuration
@EnableWebSecurity


public class SecurityConfig {
	
	@Autowired
	CustAuthService custAuthService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationProvider autProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(custAuthService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}
	
	
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder() ;
	}
//	@Bean	
//	public UserDetailsService useService() {
//		UserDetails firstUser=User
//				.withUsername("karan")
//				.password(passEncoder().encode("abc"))
//				.roles("USER")
//				.build();
//			
//		System.out.println(passEncoder().encode("abc"));		
//		
//		UserDetails secondUser=User
//				.withUsername("priya")
//				.password(passEncoder().encode("abc"))
//				.roles("USER","ADMIN")
//				.build();
//		InMemoryUserDetailsManager data=new InMemoryUserDetailsManager(firstUser,secondUser);
//		
//		return data;
//			
//	}

@Bean
public SecurityFilterChain setPermission(HttpSecurity http)throws Exception{
	
	return http
	 .csrf(csrf->csrf.disable())
	 .authorizeHttpRequests(authorize->
//	 authorize.requestMatchers(HttpMethod.GET,"/blogs").permitAll()
//	 .requestMatchers(HttpMethod.GET,"/blogs/**").permitAll()
	 authorize.requestMatchers(HttpMethod.POST,"/blogs").hasRole("ADMIN")
	 .requestMatchers(HttpMethod.DELETE,"/blogs**").hasRole("ADMIN")
	 .requestMatchers(HttpMethod.PUT,"/blogs**").hasRole("ADMIN")
	 .requestMatchers(HttpMethod.GET,"/blogs").permitAll()
	 .requestMatchers(HttpMethod.GET,"/blogs/**").permitAll()
	 .requestMatchers(HttpMethod.POST,"/auth/rgister").permitAll()
	 .anyRequest()
	 .authenticated())
	 .httpBasic(withDefaults())
	 .build();
}
}
