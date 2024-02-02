package com.kiran.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class College {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private User college_admin;
	@Column(name="student_name")
	private String college_name;
	@Column
	private String location;
	
	
	
	
	public College() {
		
	}
	
	public College(long id, User college_admin, String college_name, String location) {
		super();
		this.id = id;
		this.college_admin = college_admin;
		this.college_name = college_name;
		this.location = location;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getCollege_admin() {
		return college_admin;
	}
	public void setCollege_admin(User college_admin) {
		this.college_admin = college_admin;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "College [id=" + id + ", college_admin=" + college_admin + ", college_name=" + college_name
				+ ", location=" + location + "]";
	}
	
	

}
