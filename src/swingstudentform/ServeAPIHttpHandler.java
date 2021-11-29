package swingstudentform;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ServeAPIHttpHandler implements HttpHandler {
	private ArrayList<Student> student;
	ServeAPIHttpHandler (ArrayList<Student> student) {
		this.student = student;
	}
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		// TODO Auto-generated method stub
;		StringBuilder responseBuilder = new StringBuilder().append("{\n\"Student\": [\n");
		Iterator<Student> studentIterator = this.student.iterator();
		while(studentIterator.hasNext()) {
			Student singleStudent = studentIterator.next();
			responseBuilder.append("{\n\"Student Name:\" \"" + singleStudent.getStudentName() + "\",\n" +
									    "\"Student Year:\" \"" + singleStudent.getStudentYear() + "\",\n" +
									    "\"Student ID:\" \"" + singleStudent.getStudentID() + "\"\n},\n");
		}
		responseBuilder.append("]\n}");
		String response = responseBuilder.toString();
		httpExchange.sendResponseHeaders(200, response.length());
		OutputStream outputStream = httpExchange.getResponseBody();
		outputStream.write(response.getBytes());
		outputStream.flush();
		outputStream.close();
	}

}
