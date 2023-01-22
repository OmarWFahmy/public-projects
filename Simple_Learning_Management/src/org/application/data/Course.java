package org.application.data;

public class Course {
	private String id;
	private String courseName;
	private String instructor;
	private String courseDuration;
	private String courseTime;
	private String location;

	public Course() {
		super();
		this.id = null;
		this.courseName = null;
		this.instructor = null;
		this.courseDuration = null;
		this.courseTime = null;
		this.location = null;
	}
	
	public Course(String id, String courseName, String instructor, String courseDuration, String courseTime,
			String location) {
		super();
		this.id = id;
		this.courseName = courseName;
		this.instructor = instructor;
		this.courseDuration = courseDuration;
		this.courseTime = courseTime;
		this.location = location;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String showCourse() {
		return this.id + ", " + this.courseName + ", " + this.instructor + ", " + this.courseDuration + ", " + this.courseTime + ", " + this.location;
	}
}
