package com.kiran.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column
	private String name;
	@Column
	private String password;
	
	
	public Admin() {
	
		// TODO Auto-generated constructor stub
	}
	
	
	public Admin(long id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
	
	
}
