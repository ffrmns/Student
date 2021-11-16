package studentdatabaseapp;

import java.util.ArrayList;
import java.util.Scanner;

public class Student {
	private int balance;
	private int cost;
	private int courseCount;
	private String studentName;
	private String studentYear;
	private String studentID;
	private ArrayList<String> chosenCourse = new ArrayList<String>();
	public Student () {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter student name:");
		this.studentName = sc.next();
		System.out.println("Enter student year:");
		this.studentYear = sc.next();
		System.out.println("Enter student ID:");
		this.studentID = sc.next();
		this.balance = 5000;
	}
	/**
	 * Prompt ID
	 */
	/**
	 * Prompt to enroll
	 */
	public void enrollCourse() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1. History 101\n"
						 + "2. Mathemattics 101\n"
						 + "3. English 101\n"
						 + "4. Chemistry 101\n"
						 + "5. Computer Science 101\n"
						 + "Enter the course to enroll:");
		while (sc.findInLine("(?=\\S)") != null && sc.hasNextLine()) {
			this.chosenCourse.add(sc.nextLine());
		}
	
		this.courseCount = this.chosenCourse.size();
		this.cost = 600 * this.courseCount;
		this.balance = this.balance - this.cost;
	}

	/**
	 * Cost of enroll
	 */


	/**
	 * View balance and pay tuition
	 */

	/**
	 * See status
	 */
	public void checkStatus () {
		System.out.println("Student name: " + this.studentName
				         + "\nStudent year: " + this.studentYear
				         + "\nStudent ID: " + this.studentID
				         + "\nStudent course: " + this.chosenCourse
				         + "\nStudent billing: " + this.cost
				         + "\nStudent balance: " + this.balance);
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCourseCount() {
		return courseCount;
	}
	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentYear() {
		return studentYear;
	}
	public void setStudentYear(String studentYear) {
		this.studentYear = studentYear;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public ArrayList<String> getChosenCourse() {
		return chosenCourse;
	}
	public void setChosenCourse(ArrayList<String> chosenCourse) {
		this.chosenCourse = chosenCourse;
	}
}
