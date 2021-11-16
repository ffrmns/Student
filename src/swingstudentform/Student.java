package swingstudentform;

public class Student {
	private String studentName;
	private String studentYear;
	private String studentID;
	Student (String studentName, String studentYear, String studentID) {
		this.studentName = studentName;
		this.studentYear = studentYear;
		this.studentID = studentID;
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
}
