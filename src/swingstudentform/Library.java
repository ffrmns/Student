package swingstudentform;

import java.sql.Date;
import java.util.HashMap;

public class Library {
	private String studentID;
	private HashMap <String,Date> borrowedBook;
	Library (String studentID){
		this.studentID = studentID;
	}
	Library (String studentID, HashMap<String,Date> borrowedBook) {
		this(studentID);
		this.borrowedBook = borrowedBook;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public HashMap<String, Date> getBorrowedBook() {
		return borrowedBook;
	}
	public void setBorrowedBook(HashMap<String, Date> borrowedBook) {
		this.borrowedBook = borrowedBook;
	}
}
