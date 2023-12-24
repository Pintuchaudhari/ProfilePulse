package com.example.demo.services;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repo.UserRepo;

import jakarta.transaction.Transactional;
@Service
public class CustAuthService implements UserDetailsService{
	
	
	@Autowired
	UserRepo userRepo;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println(email);
		
		return userRepo.findByEmail(email)
				.map(user->new User(user.getEmail(),
						user.getPassword(),
						user.getRoles().stream()
						.map(role->new SimpleGrantedAuthority(role))
						.collect(Collectors.toList())))
				        .orElseThrow(()->new UsernameNotFoundException("user not found"));
	}

}
