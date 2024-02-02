package com.kiran.restapi.entity;

import java.util.Date;

import org.hibernate.annotations.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table
public class Placement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToAny
    private College college;

    @Column
    @Temporal(TemporalType.DATE)  // Add this annotation for the 'date' field
    private Date date;

    @Column
    private String qualification;

    @Column
    private int year;
	
	public Placement() {
		// TODO Auto-generated constructor stub
	}


	public Placement(long id, String name, College college, Date date, String qualification, int year) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.date = date;
		this.qualification = qualification;
		this.year = year;
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
	public College getCollege() {
		return college;
	}
	public void setCollege(College college) {
		this.college = college;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@Override
	public String toString() {
		return "Placement [id=" + id + ", name=" + name + ", college=" + college + ", date=" + date + ", qualification="
				+ qualification + ", year=" + year + "]";
	}
	
	
	
	
	
}
