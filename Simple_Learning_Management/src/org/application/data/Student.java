package org.application.data;

import java.util.ArrayList;

public class Student {
	private String id;
	private String name;
	private String grade;
	private String address;
	private String region;
	private String country;
	private String email;
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	private int countCourses = 0;
	
	public int getCountCourses() {
		return countCourses;
	}

	public void setCountCourses(int countCourses) {
		this.countCourses = countCourses;
	}

	public Student(String id, String name, String grade, String address, String region, String country, String email,
			ArrayList<Course> courses, int countCourses) {
		super();
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.address = address;
		this.region = region;
		this.country = country;
		this.email = email;
		this.courses = courses;
		this.countCourses = countCourses;
	}

	public Student() {
		this.id = null;
		this.name = null;
		this.grade = null;
		this.address = null;
		this.region = null;
		this.country = null;
		this.email = null;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String showStudent()
	{
		return this.id + "- " + this.name + ", " + this.grade + ", " + this.email + ", " + this.address + ", " + this.region + ", " + this.country;
	}
}
