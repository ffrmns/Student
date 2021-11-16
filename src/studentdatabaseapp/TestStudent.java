package studentdatabaseapp;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import org.junit.jupiter.api.Test;

class TestStudent {

	private Student stdTest1;

	@Test
	void testStudent() {
		
		System.setIn(new ByteArrayInputStream(("Eren Yaeger\n"
				+ "2015\n"
				+ "18115\n").getBytes()));
		
		stdTest1 = new Student();
		
		assertEquals("Eren Yaeger",stdTest1.getStudentName());
		assertEquals("2015",stdTest1.getStudentYear());
		assertEquals("18115",stdTest1.getStudentID());
	}

	@Test
	void testEnrollCourse() {
		System.setIn(new ByteArrayInputStream(("Eren Yaeger\n"
				+ "2015\n"
				+ "18115\n").getBytes()));
		
		stdTest1 = new Student();
		
		System.setIn(new ByteArrayInputStream(("1. History 101\n"
				+ "2. Mathematics 101\n"
				+ "\n").getBytes()));
		stdTest1.enrollCourse();
		
		assertEquals("Eren Yaeger",stdTest1.getStudentName());
		assertEquals("2015",stdTest1.getStudentYear());
		assertEquals("18115",stdTest1.getStudentID());
		assertEquals("[1. History 101, 2. Mathematics 101]",stdTest1.getChosenCourse().toString());
		
	}

	@Test
	void testCheckStatus() {
		
		System.setIn(new ByteArrayInputStream(("Eren Yaeger\n"
				+ "2015\n"
				+ "18115\n").getBytes()));
		
		stdTest1 = new Student();
		
		System.setIn(new ByteArrayInputStream(("1. History 101\n"
				+ "2. Mathematics 101\n"
				+ "\n").getBytes()));
		stdTest1.enrollCourse();
		
		stdTest1.checkStatus();
		assertEquals("Eren Yaeger",stdTest1.getStudentName());
		assertEquals("2015",stdTest1.getStudentYear());
		assertEquals("18115",stdTest1.getStudentID());
		assertEquals("[1. History 101, 2. Mathematics 101]",stdTest1.getChosenCourse().toString());
	}

}
