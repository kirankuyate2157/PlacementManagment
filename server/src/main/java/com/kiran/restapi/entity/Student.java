package com.kiran.restapi.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private List<Certificate> certificate;
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
			List<Certificate> certificate, String hallticket, float percentage, String branch) {
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

	public void forEach(Consumer<? super Certificate> action) {
		certificate.forEach(action);
	}

	public int size() {
		return certificate.size();
	}

	public boolean isEmpty() {
		return certificate.isEmpty();
	}

	public boolean contains(Object o) {
		return certificate.contains(o);
	}

	public Iterator<Certificate> iterator() {
		return certificate.iterator();
	}

	public Object[] toArray() {
		return certificate.toArray();
	}

	public <T> T[] toArray(T[] a) {
		return certificate.toArray(a);
	}

	public boolean add(Certificate e) {
		return certificate.add(e);
	}

	public boolean remove(Object o) {
		return certificate.remove(o);
	}

	public boolean containsAll(Collection<?> c) {
		return certificate.containsAll(c);
	}

	public boolean addAll(Collection<? extends Certificate> c) {
		return certificate.addAll(c);
	}

	public boolean addAll(int index, Collection<? extends Certificate> c) {
		return certificate.addAll(index, c);
	}

	public boolean removeAll(Collection<?> c) {
		return certificate.removeAll(c);
	}

	public boolean retainAll(Collection<?> c) {
		return certificate.retainAll(c);
	}

	public void replaceAll(UnaryOperator<Certificate> operator) {
		certificate.replaceAll(operator);
	}

	public <T> T[] toArray(IntFunction<T[]> generator) {
		return certificate.toArray(generator);
	}

	public void sort(Comparator<? super Certificate> c) {
		certificate.sort(c);
	}

	public void clear() {
		certificate.clear();
	}

	public boolean equals(Object o) {
		return certificate.equals(o);
	}

	public int hashCode() {
		return certificate.hashCode();
	}

	public Certificate get(int index) {
		return certificate.get(index);
	}

	public Certificate set(int index, Certificate element) {
		return certificate.set(index, element);
	}

	public void add(int index, Certificate element) {
		certificate.add(index, element);
	}

	public boolean removeIf(Predicate<? super Certificate> filter) {
		return certificate.removeIf(filter);
	}

	public Certificate remove(int index) {
		return certificate.remove(index);
	}

	public int indexOf(Object o) {
		return certificate.indexOf(o);
	}

	public int lastIndexOf(Object o) {
		return certificate.lastIndexOf(o);
	}

	public ListIterator<Certificate> listIterator() {
		return certificate.listIterator();
	}

	public ListIterator<Certificate> listIterator(int index) {
		return certificate.listIterator(index);
	}

	public List<Certificate> subList(int fromIndex, int toIndex) {
		return certificate.subList(fromIndex, toIndex);
	}

	public Spliterator<Certificate> spliterator() {
		return certificate.spliterator();
	}

	public Stream<Certificate> stream() {
		return certificate.stream();
	}

	public Stream<Certificate> parallelStream() {
		return certificate.parallelStream();
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

	public List<Certificate> getCertificate() {
		return certificate;
	}

	public void setCertificate(List<Certificate> certificate) {
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", rollno=" + rollno + ", name=" + name + ", college=" + college
				+ ", qualification=" + qualification + ", couerse=" + couerse + ", year=" + year + ", certificate="
				+ certificate + ", hallticket=" + hallticket + ", percentage=" + percentage + ", branch=" + branch
				+ "]";
	}

}
