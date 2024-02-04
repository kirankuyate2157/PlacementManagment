package com.kiran.restapi.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Placement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "college_id")
	private College college;


	@Column
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column
	private String qualification;

	@Column
	private String company_name;

	@Column
	private String company_description;
	@Column
	private String job_position;

	@Column
	private int year;

	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "placement_student", joinColumns = @JoinColumn(name = "placement_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	private Set<Student> participants = new HashSet<>();

	public Placement() {
		// Default constructor
	}
    
	public Placement(long id, String name, College college, Date date, String qualification, String company_name,
			String company_description, String job_position, int year, Set<Student> participants) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.date = date;
		this.qualification = qualification;
		this.company_name = company_name;
		this.company_description = company_description;
		this.job_position = job_position;
		this.year = year;
		this.participants = participants;
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

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getCompany_description() {
		return company_description;
	}

	public void setCompany_description(String company_description) {
		this.company_description = company_description;
	}

	public String getJob_position() {
		return job_position;
	}

	public void setJob_position(String job_position) {
		this.job_position = job_position;
	}

	public Set<Student> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<Student> participants) {
		this.participants = participants;
	}

	@Override
	public String toString() {
		return "Placement [id=" + id + ", name=" + name + ", college=" + college + ", date=" + date + ", qualification="
				+ qualification + ", company_name=" + company_name + ", company_description=" + company_description
				+ ", job_position=" + job_position + ", year=" + year + ", participants=" + participants + "]";
	}

}
