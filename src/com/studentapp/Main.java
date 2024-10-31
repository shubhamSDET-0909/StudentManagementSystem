package com.studentapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static List<Student> studentList;
	private static Scanner scanner;

	public static void main(String[] args) {

		System.out.println("***** Student Management System ***** ");

		studentList = new ArrayList<Student>();
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("***** Welcome ***** ");
			System.out.println("Select an Option::");
			System.out.println("1: Resgiter a Student");
			System.out.println("2: Find a Student with Student ID");
			System.out.println("3: List all Student information");
			System.out.println("4: List Student information in sorted order");
			System.out.println("5: Exit");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				enrollStudent(scanner);
				break;
			case 2:
				findStudentById(scanner);
				break;
			case 3:
				printAllStudentsData();
				break;
			case 4:
				sortByName();
				break;
			case 5:
				exit();
				break;

			default:
				System.out.println("Invalid Option Selected..Enter from 1 to 5");
			}
		}
	}

	private static void exit() {
		System.out.println("Good Bye!!!");
		System.exit(0);
		

	}

	private static void printAllStudentsData() {
		if (studentList.size() > 0) {
			System.out.println("####### Print All Student Data #######");
			for (Student student : studentList) {
				student.printStudentInfo();
			}

			System.out.println("-----------All Students in LMS-----------");
		} else {
			System.err.println("Student List is Empty!! No Student Record Found");
		}
	}

	private static void findStudentById(Scanner scanner2) {

		Student studentFound = null;
		System.out.println("Enter the Student ID::");
		String studentId = scanner2.next();
		try {
			studentFound = studentList.stream().filter(student -> student.getStudentId().equalsIgnoreCase(studentId))
					.findFirst().orElseThrow(() -> new RuntimeException("No Data Found"));
		} catch (RuntimeException e) {
			System.err.println("Student with ID " + studentId + " not found!!");
		}

		studentFound.printStudentInfo();
	}

	private static void enrollStudent(Scanner scanner2) {
		System.out.println("Enter the Student Name");
		String studentName = scanner2.next();

		System.out.println("Enter the Student Age");
		int studentAge = scanner2.nextInt();

		System.out.println("Enter the Student Id");
		String studentId = scanner2.next();

		Student newStudent = new Student(studentName, studentAge, studentId);
		studentList.add(newStudent);
		while (true) {
			System.out.println("Enter the course to be enrolled in:: Type Done to Exit");
			String courseName = scanner2.next();
			if (courseName.equalsIgnoreCase("done")) {
				break;
			}
			newStudent.enrollCourse(courseName);
		}
		newStudent.printStudentInfo();
	}

	private static void sortByName() {
		Comparator<Student> studentNameComparator = (o1, o2) -> o1.getName().compareTo(o2.getName());

		/*
		 * new Comparator<Student>() {
		 * 
		 * @Override public int compare(Student o1, Student o2) { return
		 * o1.getName().compareTo(o2.getName());//Comparing on basis of Alphabetically }
		 * 
		 * };
		 */ Collections.sort(studentList, studentNameComparator);
		printAllStudentsData();
	}

	public static Student findStudentById(String studentId) {

		Student result = null;
		try {
			result = studentList.stream().filter(x -> x.getStudentId().equalsIgnoreCase(studentId)).findFirst()
					.orElseThrow(() -> new RuntimeException("No Data Found"));
		} catch (RuntimeException e) {
			System.err.println("Student with ID " + studentId + " not found!!");
		}
		return result;
	}

}
