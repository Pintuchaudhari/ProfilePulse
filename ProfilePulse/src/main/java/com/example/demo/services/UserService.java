package com.example.demo.services;
import org.springframework.*;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.models.User;
import com.example.demo.models.UserRegister;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
   @Autowired
   PasswordEncoder passwordEncoder;
   
   public User register(UserRegister userRegister) {
	   if(this.userRepo.findByEmail(userRegister.getEmail()).isPresent()) {
		   throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
	   }
	   
	   User user=new User();
	   user.setName(userRegister.getName());
	   user.setEmail(userRegister.getEmail());
	   user.setPassword(userRegister.getPassword());
	   
	    
	  
	   return this.userRepo.save(user);
   }

}
