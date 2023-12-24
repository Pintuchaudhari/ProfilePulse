package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.models.Blog;

public interface BlogRepo extends CrudRepository<Blog, Integer>{

}
