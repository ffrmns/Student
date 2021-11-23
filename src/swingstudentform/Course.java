package swingstudentform;

import java.util.HashMap;

public class Course {
	private String studentID;
	private HashMap<String,String> courseDescriptions;
	Course(String studentID) {
		this.studentID = studentID;
	}
	Course(String studentID, HashMap<String,String> courseDescriptions) {
		this(studentID);
		this.courseDescriptions = courseDescriptions;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public HashMap<String, String> getCourseDescriptions() {
		return courseDescriptions;
	}
	public void setCourseDescriptions(HashMap<String, String> courseDescriptions) {
		this.courseDescriptions = courseDescriptions;
	}
}
