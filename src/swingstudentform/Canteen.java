package swingstudentform;

import java.util.HashMap;

public class Canteen {
	private String studentID;
	private HashMap <String,Integer> canteenBill;
	Canteen(String studentID) {
		this.studentID = studentID;
	}
	Canteen(String studentID, HashMap <String, Integer> canteenBill) {
		this(studentID);
		this.canteenBill = canteenBill;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}
	public HashMap<String, Integer> getCanteenBill() {
		return canteenBill;
	}
	public void setCanteenBill(HashMap<String, Integer> canteenBill) {
		this.canteenBill = canteenBill;
	}
}
