package com.studentapp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {

	// Instance Variables - Non-Static

	private String name;
	private int age;
	private String studentId;
	private List<String> courses;

	public Student(String name, int age, String studentId) {
		super();
		if (validateAge(age) && validateName(name) && validateStudentId(studentId)) {
			this.name = name;
			this.age = age;
			this.studentId = studentId;
			courses = new ArrayList<String>(); // InitiaLization of course
		}
	}

	private boolean validateStudentId(String studentId) {
		// S-09
		String studentIdRegEx = "S-\\d+$";
		Pattern studentIdPattern = Pattern.compile(studentIdRegEx);
		Matcher studentIdMatcher = studentIdPattern.matcher(studentId);
		if (studentIdMatcher.matches()) {
			return true;
		} else {
			System.err.println("Invalid Student ID..Use Correct Patter S-Digit");
			return false;
		}

	}

	public void enrollCourse(String course) {
		if (validateCourseName(course)) {
			if (!courses.contains(course)) {
				courses.add(course);
				System.out.println("Student Enrolled to:: " + course + " Sucessfully!!");
			} else {
				System.err.println("Student has already enrolled to::" + course + " Course!!");
			}
		}else {
			System.err.println("Student Cannont enrolled to:: " + course + " Course as Course is Invalid!!");
		}
	}

	public void printStudentInfo() {
		System.out.println("====== Student Information ======");
		System.out.println("Student name::" + name);
		System.out.println("Student Age::" + age);
		System.out.println("Student Id::" + studentId);
		System.out.println("Enrolled For ::" + courses);
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", studentId=" + studentId + ", courses=" + courses + "]";
	}

	// Validation methods:: To Avoid Invalid values assignment to instance variables

	public boolean validateAge(int age) {
		if (age >= 19 && age <= 35) {
			return true;
		} else {
			System.err.println("Invalid age !! Student age should be within range mentioned i.e. 19 to 35");
			return false;
		}

	}

	public boolean validateName(String name) {
		// shubham
		String nameRegex = "^[a-zA-Z\\s]+$"; // Allowing alphabets in small/upper case with whitspaces
		Pattern namePattern = Pattern.compile(nameRegex);
		Matcher nameMatcher = namePattern.matcher(name);
		if (nameMatcher.matches()) {
			return true;
		} else {
			System.err.println("Invalid Name!! Please Enter Alphabets only..");
			return false;
		}

	}

	public boolean validateCourseName(String course) {
		if (course.equalsIgnoreCase("Java") || course.equalsIgnoreCase("DSA") || course.equalsIgnoreCase("DevOps")) {
			return true;
		} else {
			System.err.println("Invalid Course!! Please choose from available courses [DSA,JAVA,DevOps]..");
			return false;
		}

	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getStudentId() {
		return studentId;
	}

	public List<String> getCourses() {
		return courses;
	}
	

}
