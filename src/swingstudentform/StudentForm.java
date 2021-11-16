package swingstudentform;


import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
				// TODO Auto-generated method stub
				System.out.println("clicked!");
				System.out.println(studentNameTextField.getText());
				System.out.println(studentYearTextField.getText());
				System.out.println(studentIDTextField.getText());
				
				studentArray.add(new Student(studentNameTextField.getText(),
											 studentYearTextField.getText(),
											 studentIDTextField.getText()));
				System.out.println(studentArray.size());
			}
		};
		
		deleteEntry = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
				JPanel mainPanel = new JPanel(new GridLayout(2,1));
				JPanel searchPanel = new JPanel(new GridLayout(2,1));
				JPanel showPanel = new JPanel(new GridLayout(2,1));
				searchPanel.setBorder(BorderFactory.createTitledBorder("Search Panel"));
				showPanel.setBorder(BorderFactory.createTitledBorder("Show Panel"));
				mainPanel.add(searchPanel);
				mainPanel.add(showPanel);
				JTextField searchField = new JTextField();
				JTextField editField = new JTextField();
				JTextArea searchResult = new JTextArea();
				searchPanel.add(searchField);
				searchPanel.add(editField);
				showPanel.add(searchResult);
				editFrame.getContentPane().add(mainPanel);
			}
		};
		
		displayList = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		fromFile = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		toDatabase = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
		
		fromDatabase = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
