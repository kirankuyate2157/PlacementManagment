package com.kiran.restapi.entity;

import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private int rollno;
	@Column(name = "student_name")
	private String name;
	@Column
	private String college;
	@Column
	private String qualification;
	@Column
	private String couerse;
	@Column
	private int year;
	@Column
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	 private Set<Certificate >certificate;
	@Column
	private String hallticket;
	@Column
	private float percentage;
	@Column
	private String branch;

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(long id, int rollno, String name, String college, String qualification, String couerse, int year,
			Set<Certificate> certificate, String hallticket, float percentage, String branch) {
		super();
		this.id = id;
		this.rollno = rollno;
		this.name = name;
		this.college = college;
		this.qualification = qualification;
		this.couerse = couerse;
		this.year = year;
		this.certificate = certificate;
		this.hallticket = hallticket;
		this.percentage = percentage;
		this.branch = branch;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRollno() {
		return rollno;
	}

	public void setRollno(int rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getCouerse() {
		return couerse;
	}

	public void setCouerse(String couerse) {
		this.couerse = couerse;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Set<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(Set<Certificate> certificate) {
		this.certificate = certificate;
	}

	public String getHallticket() {
		return hallticket;
	}

	public void setHallticket(String hallticket) {
		this.hallticket = hallticket;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	
}
