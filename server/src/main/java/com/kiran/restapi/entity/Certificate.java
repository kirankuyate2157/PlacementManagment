package com.kiran.restapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Certificate {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	private int  year;
	@Column
	private College college;
	
	
	
	public Certificate() {
		// TODO Auto-generated constructor stub
	}



	public Certificate(long id, int year, College college) {
		super();
		this.id = id;
		this.year = year;
		this.college = college;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public int getYear() {
		return year;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public College getCollege() {
		return college;
	}



	public void setCollege(College college) {
		this.college = college;
	}



	@Override
	public String toString() {
		return "Certificate [id=" + id + ", year=" + year + ", college=" + college + "]";
	}
	
	
	
}
