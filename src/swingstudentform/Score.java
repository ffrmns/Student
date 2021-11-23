package swingstudentform;

import java.util.HashMap;

public class Score {
	private String studentID;
	private HashMap<String,Integer> courseScore;

	Score(String studentID) {
		this.studentID = studentID;
	}
	Score(String studentID, HashMap<String,Integer> courseScore) {
		this(studentID);
		this.courseScore = courseScore;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public HashMap<String, Integer> getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(HashMap<String, Integer> courseScore) {
		this.courseScore = courseScore;
	}
}
