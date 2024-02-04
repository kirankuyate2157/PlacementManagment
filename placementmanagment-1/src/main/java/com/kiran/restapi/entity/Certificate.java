package com.kiran.restapi.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Certificate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String time;
	@Column
	private String doc;
	@Column
	private String certificate_name;
	@Column
	private String student_name;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private College college;

	public Certificate() {
		// TODO Auto-generated constructor stub
	}

	public Certificate(long id, String time, String doc, String certificate_name, String student_name,
			College college) {
		super();
		this.id = id;
		this.time = time;
		this.doc = doc;
		this.certificate_name = certificate_name;
		this.student_name = student_name;
		this.college = college;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCertificate_name() {
		return certificate_name;
	}

	public void setCertificate_name(String certificate_name) {
		this.certificate_name = certificate_name;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "Certificate [id=" + id + ", time=" + time + ", doc=" + doc + ", student_name=" + student_name
				+ ", college=" + college + "]";
	}

}
