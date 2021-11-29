package swingstudentform;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpServer;

public class ServeAPI {
	private ArrayList<Student> student;
	ServeAPI (ArrayList<Student> student){
		this.student = student;
		try {
			Logger logger = Logger.getLogger(ServeAPI.class.getName());
			 
			HttpServer studentHttpsApiServer =  HttpServer.create(new InetSocketAddress("localhost", 8001),0);
			studentHttpsApiServer.createContext("/", new ServeAPIHttpHandler(this.student));
			
			studentHttpsApiServer.start();
			logger.info("Server started on port 8001");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
