package studentdatabaseapp;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentDatabaseApp {

	public static void main(String[] args) {
		/**
		 * Ask how many students
		 */
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter how many students:");
		int numberOfStudent = sc.nextInt();
		
		/**
		 * Prompt name and year of each student
		 */
		ArrayList<Student> stdArr = new ArrayList<Student>();
		
		for (int i=1;i<=numberOfStudent;i++) {
			System.out.println("Student " + i);
			stdArr.add(new Student());
			stdArr.get(i-1).enrollCourse();
		}
		
		for (Student std : stdArr)
		std.checkStatus();

	}

}
