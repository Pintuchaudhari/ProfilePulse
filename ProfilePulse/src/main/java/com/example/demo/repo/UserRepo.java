package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.User;
import java.util.List;


public interface UserRepo extends CrudRepository<User, Integer>{
	public Optional<User> findByEmail(String email);

	public org.springframework.boot.autoconfigure.security.SecurityProperties.User save(
			org.springframework.boot.autoconfigure.security.SecurityProperties.User user);

}
