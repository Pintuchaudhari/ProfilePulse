package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blog {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 	private int id;
 	 
 	private String titl;
 	
 	private String description;
 	
 	private void blog() {
		// TODO Auto-generated method stub

	}

	public Blog(int id, String titl, String description) {
		super();
		this.id = id;
		this.titl = titl;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitl() {
		return titl;
	}

	public void setTitl(String titl) {
		this.titl = titl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 	
 	
	
	
}
