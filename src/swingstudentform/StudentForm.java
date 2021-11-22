package swingstudentform;


import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class StudentForm {
	private ActionListener 		addStudent,deleteEntry,editEntry,displayList,toFile,fromFile,toDatabase,fromDatabase;
	private ArrayList<Student> 	studentArray;
	private Container 			contentPane;
	private File				selectedFile;
	private JButton 			addButton, deleteButton, editButton, displayButton, saveButton, loadButton, storeButton, retrieveButton;
	private JFrame 				frame;
	private JLabel 				studentNameLabel, studentYearLabel, studentIDLabel;
	private JTextField 			studentNameTextField, studentYearTextField, studentIDTextField;
	private final static String databaseURL = "jdbc:oracle:thin:@172.17.0.2:1521/ORCLPDB1";
	private final static String usernameDB = "ffa";
	private final static String passwordDB = "passwordffa";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentForm window = new StudentForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Student Form");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		contentPane = frame.getContentPane();
		instantiateFormAndButton();
		
		actionListenerForButton();
		layoutConfiguration();
		addComponentToJFrame();
		assignActionToButtonPress();
	}

	/**
	 * ActionListener for any button press
	 */
	private void actionListenerForButton() {
		studentArray = new ArrayList<Student>();
		addStudent = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String enteredStudentName = studentNameTextField.getText();
				String enteredStudentYear = studentYearTextField.getText();
				String enteredStudentID = studentIDTextField.getText();
				if (enteredStudentName.length() == 0) {
					JOptionPane.showMessageDialog(null, "Name must not be empty!");
				} else if (enteredStudentYear.length() != 4) {
					JOptionPane.showMessageDialog(null, "Student year is must have four length!");
				} else if (enteredStudentID.length() != 5) {
					JOptionPane.showMessageDialog(null, "Student ID length must have exact length of 5"); 
				} else {
					System.out.println("clicked!");
					System.out.println(enteredStudentName);
					System.out.println(enteredStudentYear);
					System.out.println(enteredStudentID);
					
					studentArray.add(new Student(enteredStudentName,
												 enteredStudentYear,
												 enteredStudentID));
					System.out.println(studentArray.size());
				}
			}
		};
		
		deleteEntry = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				studentArray.remove(studentArray.size()-1);
			}
		};
		
		editEntry = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame editFrame = new JFrame("Edit Student List");
				editFrame.setBounds(550,100,450,300);
				editFrame.setVisible(true);
				JPanel mainPanel = new JPanel(new GridLayout(1,1));
				JPanel editPanel = new JPanel(new GridLayout(3,1));
				
				JPanel editOriginPanel = new JPanel(new GridLayout(1,2));
				JPanel editDestinationPanel = new JPanel(new GridLayout(1,2));				
				JLabel editOriginLabel = new JLabel("Edit from name, year, and ID:");
				JLabel editDestinationLabel = new JLabel("Edit to:");
				JTextField editOriginField = new JTextField("Fikri,2015,18115011");
				JTextField editDestinationField = new JTextField("Fikri contoh,2021,18121011");
				JButton changeButton = new JButton("Change!");

				editOriginPanel.add(editOriginLabel);
				editOriginPanel.add(editOriginField);
				editDestinationPanel.add(editDestinationLabel);
				editDestinationPanel.add(editDestinationField);				
				editPanel.add(editOriginPanel);
				editPanel.add(editDestinationPanel);				
				editPanel.add(changeButton);
				
				editPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
				mainPanel.add(editPanel);
				editFrame.getContentPane().add(mainPanel);
				
				ActionListener changeAction = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("Change button clicked!");
						ListIterator<Student> iteratorStudent = studentArray.listIterator();
						try {
							String[] editOriginList = editOriginField.getText().split(",");
							String[] editDestinationList = editDestinationField.getText().split(",");
							while (iteratorStudent.hasNext()) {
								Student currentStudent = iteratorStudent.next();
								if (currentStudent.getStudentName().equals(editOriginList[0]) && currentStudent.getStudentYear().equals(editOriginList[1]) && currentStudent.getStudentID().equals(editOriginList[2])) {
									System.out.println("Found at index: "+ (iteratorStudent.nextIndex()-1));
									System.out.println("Before: " + currentStudent.getStudentName() + "," + currentStudent.getStudentYear() + "," + currentStudent.getStudentID());
									currentStudent.setStudentName(editDestinationList[0]);
									currentStudent.setStudentYear(editDestinationList[1]);
									currentStudent.setStudentID(editDestinationList[2]);
									System.out.println("After: "+ currentStudent.getStudentName() + "," + currentStudent.getStudentYear() + "," + currentStudent.getStudentID());
								}
							}
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				changeButton.addActionListener(changeAction);
				
			}
		};
		
		displayList = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame displayFrame = new JFrame("List of students:");
				displayFrame.setBounds(550, 100, 450, 300);
				displayFrame.setVisible(true);
				Container displayContentPane = displayFrame.getContentPane();
				
				String studentInCsv = "";
				for (Student student : studentArray)
				studentInCsv += student.getStudentName() + "," + student.getStudentYear() + "," + student.getStudentID() + "\n";
				displayContentPane.add(new JTextArea(studentInCsv));
			}
		};
		
		toFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileSave = new JFileChooser();
				fileSave.setSelectedFile(new File("../../StudentListFromEclipseWorkspace2.txt"));
				fileSave.showSaveDialog(null);
				File pathSave = fileSave.getSelectedFile();
				try (FileWriter fileWriter = new FileWriter(pathSave)){
					System.out.println("Written:");
					for (Student student : studentArray) {
						fileWriter.write(student.getStudentName() + "," +
								   student.getStudentYear() + "," +
								   student.getStudentID() + "\n");
						
						System.out.println(student.getStudentName() + "," +
										   student.getStudentYear() + "," +
										   student.getStudentID());
						
					}
					System.out.println("File written!");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		fromFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser openFile = new JFileChooser();
				openFile.showOpenDialog(null);
				selectedFile = openFile.getSelectedFile();
				
				System.out.println("The chosen file: " + selectedFile.getPath());
				Scanner scanner;
				try {
					scanner = new Scanner(selectedFile);
					scanner.useDelimiter(Pattern.compile("[,\n]"));
					while (scanner.hasNext())
					studentArray.add(new Student(scanner.next(),scanner.next(),scanner.next()));
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		
		toDatabase = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				String statement;
				try {
					conn = DriverManager.getConnection(databaseURL,usernameDB,passwordDB);
					statement = "INSERT INTO student_database (student_name,student_year,student_id) VALUES (?,?,?)";
					PreparedStatement preparedStatement = conn.prepareStatement(statement);
					int count = 0;
					for (Student student : studentArray) {
						preparedStatement.setString(1, student.getStudentName());
						preparedStatement.setString(2, student.getStudentYear());
						preparedStatement.setString(3, student.getStudentID());
						count = preparedStatement.executeUpdate();
					}
					System.out.println("Added " + count + " row to database!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		};
		
		fromDatabase = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection connection;
				try {
					connection = DriverManager.getConnection(databaseURL, usernameDB, passwordDB);
					String statement = "SELECT * FROM student_database";
					PreparedStatement preparedStatement = connection.prepareStatement(statement);
					ResultSet resultSet = preparedStatement.executeQuery();
					while (resultSet.next()) {
						studentArray.add (new Student(resultSet.getString(1),
												  resultSet.getString(2),
												  resultSet.getString(3))); 
					};
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		};
	}
	
	/**
	 * Adding form and button to JFrame Content Pane
	 */
	private void addComponentToJFrame () {
		contentPane.add(studentNameLabel);
		contentPane.add(studentNameTextField);
		contentPane.add(studentYearLabel);
		contentPane.add(studentYearTextField);
		contentPane.add(studentIDLabel);
		contentPane.add(studentIDTextField);
		contentPane.add(addButton);
		contentPane.add(deleteButton);
		contentPane.add(editButton);
		contentPane.add(displayButton);
		contentPane.add(saveButton);
		contentPane.add(loadButton);
		contentPane.add(storeButton);
		contentPane.add(retrieveButton);
	}
	
	/**
	 * Assigning action to button press
	 */
	private void assignActionToButtonPress () {
		studentNameTextField.addActionListener(addStudent);
		studentYearTextField.addActionListener(addStudent);
		studentIDTextField.addActionListener(addStudent);
		addButton.addActionListener(addStudent);
		deleteButton.addActionListener(deleteEntry);
		editButton.addActionListener(editEntry);
		displayButton.addActionListener(displayList);
		saveButton.addActionListener(toFile);		
		loadButton.addActionListener(fromFile);
		storeButton.addActionListener(toDatabase);
		retrieveButton.addActionListener(fromDatabase);
	}
	
	/**
	 * Instantiate form and button
	 */
	private void instantiateFormAndButton() {
		studentNameLabel 		= new JLabel("Student Name:");
		studentNameTextField 	= new JTextField();
		studentYearLabel 		= new JLabel("Student Year:");
		studentYearTextField 	= new JTextField();
		studentIDLabel 			= new JLabel("Student ID:");
		studentIDTextField 		= new JTextField();
		addButton 				= new JButton("Add");
		deleteButton 			= new JButton("Delete");
		editButton 				= new JButton("Edit");
		displayButton 			= new JButton("Display");
		saveButton 				= new JButton("Save");
		loadButton 				= new JButton("Load");
		storeButton				= new JButton("Store");
		retrieveButton			= new JButton("Retrieve");
	}
	
	/**
	 * Start of layout configuration
	 */
	private void layoutConfiguration() {
		SpringLayout layouter = new SpringLayout();
		contentPane.setLayout(layouter);
		
		/*
		 * row 1 form
		 */
		layouter.putConstraint(SpringLayout.VERTICAL_CENTER,studentNameLabel,0,SpringLayout.VERTICAL_CENTER,studentNameTextField);
		layouter.putConstraint(SpringLayout.NORTH,studentNameTextField,20,SpringLayout.NORTH,contentPane);
		layouter.putConstraint(SpringLayout.WEST,studentNameLabel,22,SpringLayout.WEST,contentPane);
		layouter.putConstraint(SpringLayout.WEST,studentNameTextField,20,SpringLayout.EAST,studentNameLabel);
		layouter.putConstraint(SpringLayout.EAST,studentNameTextField,-22,SpringLayout.EAST,contentPane);
		
		/*
		 * row 2 form
		 */
		layouter.putConstraint(SpringLayout.VERTICAL_CENTER,studentYearLabel,0,SpringLayout.VERTICAL_CENTER,studentYearTextField);
		layouter.putConstraint(SpringLayout.NORTH,studentYearTextField,5,SpringLayout.SOUTH,studentNameTextField);
		layouter.putConstraint(SpringLayout.WEST,studentYearLabel,0,SpringLayout.WEST,studentNameLabel);
		layouter.putConstraint(SpringLayout.WEST,studentYearTextField,0,SpringLayout.WEST,studentNameTextField);
		layouter.putConstraint(SpringLayout.EAST,studentYearTextField,0,SpringLayout.EAST,studentNameTextField);
		
		/*
		 * row 3 form 
		 */
		layouter.putConstraint(SpringLayout.VERTICAL_CENTER,studentIDLabel,0,SpringLayout.VERTICAL_CENTER,studentIDTextField);
		layouter.putConstraint(SpringLayout.NORTH,studentIDTextField,5,SpringLayout.SOUTH,studentYearTextField);
		layouter.putConstraint(SpringLayout.WEST,studentIDLabel,0,SpringLayout.WEST,studentNameLabel);
		layouter.putConstraint(SpringLayout.WEST,studentIDTextField,0,SpringLayout.WEST,studentNameTextField);
		layouter.putConstraint(SpringLayout.EAST,studentIDTextField,0,SpringLayout.EAST,studentNameTextField);
		
		/*
		 * row 1 buttons
		 */
		layouter.putConstraint(SpringLayout.NORTH,addButton,30,SpringLayout.SOUTH,studentIDTextField);
		layouter.putConstraint(SpringLayout.NORTH,displayButton,0,SpringLayout.NORTH,addButton);
		layouter.putConstraint(SpringLayout.WEST,addButton,26,SpringLayout.WEST,contentPane);
		layouter.putConstraint(SpringLayout.EAST,addButton,-3,SpringLayout.HORIZONTAL_CENTER,contentPane);
		layouter.putConstraint(SpringLayout.WEST,displayButton,3,SpringLayout.HORIZONTAL_CENTER,contentPane);
		layouter.putConstraint(SpringLayout.EAST,displayButton,-26,SpringLayout.EAST,contentPane);
		
		/*
		 * row 2 buttons
		 */
		layouter.putConstraint(SpringLayout.NORTH,deleteButton,5,SpringLayout.SOUTH,addButton);
		layouter.putConstraint(SpringLayout.NORTH,saveButton,0,SpringLayout.NORTH,deleteButton);
		layouter.putConstraint(SpringLayout.WEST,deleteButton,0,SpringLayout.WEST,addButton);
		layouter.putConstraint(SpringLayout.EAST,deleteButton,0,SpringLayout.EAST,addButton);
		layouter.putConstraint(SpringLayout.WEST,saveButton,0,SpringLayout.WEST,displayButton);
		layouter.putConstraint(SpringLayout.EAST,saveButton,0,SpringLayout.EAST,displayButton);
		
		/*
		 * row 3 buttons
		 */
		layouter.putConstraint(SpringLayout.NORTH,editButton,5,SpringLayout.SOUTH,deleteButton);
		layouter.putConstraint(SpringLayout.NORTH,loadButton, 0, SpringLayout.NORTH,editButton);
		layouter.putConstraint(SpringLayout.WEST,editButton,0,SpringLayout.WEST,addButton);
		layouter.putConstraint(SpringLayout.EAST,editButton,0,SpringLayout.EAST,addButton);
		layouter.putConstraint(SpringLayout.WEST,loadButton,0,SpringLayout.WEST,displayButton);
		layouter.putConstraint(SpringLayout.EAST,loadButton,0,SpringLayout.EAST,displayButton);
		
		/*
		 * row 4 buttons
		 */
		layouter.putConstraint(SpringLayout.NORTH,storeButton,5,SpringLayout.SOUTH,editButton);
		layouter.putConstraint(SpringLayout.NORTH,retrieveButton, 0, SpringLayout.NORTH,storeButton);
		layouter.putConstraint(SpringLayout.WEST,storeButton,0,SpringLayout.WEST,addButton);
		layouter.putConstraint(SpringLayout.EAST,storeButton,0,SpringLayout.EAST,addButton);
		layouter.putConstraint(SpringLayout.WEST,retrieveButton,0,SpringLayout.WEST,displayButton);
		layouter.putConstraint(SpringLayout.EAST,retrieveButton,0,SpringLayout.EAST,displayButton);
	}
}
