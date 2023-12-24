package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.models.UserRegister;
import com.example.demo.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService userService;

	
	 @PostMapping("/register")
	 public ResponseEntity Register(@RequestBody @Valid UserRegister userRegister) {
		 org.springframework.boot.autoconfigure.security.SecurityProperties.User user=this.userService.register(userRegister);
		 return new ResponseEntity(user,HttpStatus.CREATED);
	 }
}
