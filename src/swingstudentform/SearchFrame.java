package swingstudentform;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javax.swing.JTextField;

public class SearchFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7484648721266307671L;
	private ActionListener searchListener;
	private ButtonGroup studentButtonGroup;
	private JTextField studentSearch;
	
	public SearchFrame () {
		this.setSize(450, 300);
		Container contentPane = this.getContentPane();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		contentPane.setLayout(gridBagLayout);
		
		
		JLabel searchLabel = new JLabel("Search");
		this.studentSearch = new JTextField();
		
		
		JPanel choosePanel = new JPanel();
		
		JRadioButton studentNameChooseButton = new JRadioButton("Student Name");
		studentNameChooseButton.setActionCommand("Student Name");
		JRadioButton studentYearChooseButton = new JRadioButton("Student Year", true);
		studentYearChooseButton.setActionCommand("Student Year");
		JRadioButton studentIDChooseButton = new JRadioButton("Student ID");
		studentIDChooseButton.setActionCommand("Student ID");
		studentButtonGroup = new ButtonGroup();
		studentButtonGroup.add(studentNameChooseButton);
		studentButtonGroup.add(studentYearChooseButton);
		studentButtonGroup.add(studentIDChooseButton);
		

		choosePanel.add(studentNameChooseButton);
		choosePanel.add(studentYearChooseButton);
		choosePanel.add(studentIDChooseButton);
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		contentPane.add(searchLabel, gridBagConstraints);
		gridBagConstraints.insets = new Insets(0, 10, 0, 0);
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 0;
		contentPane.add(studentSearch, gridBagConstraints);
		gridBagConstraints.insets = new Insets(0, 5, 0, 5);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridwidth = 2;
		contentPane.add(choosePanel, gridBagConstraints);
		this.setVisible(true);
		this.search();
	}
	
	public void search() {
		this.searchListener = new SearchListener(this);
		this.studentSearch.addActionListener(this.searchListener);
	}

	public ButtonGroup getStudentButtonGroup() {
		return studentButtonGroup;
	}

	public void setStudentButtonGroup(ButtonGroup studentButtonGroup) {
		this.studentButtonGroup = studentButtonGroup;
	}

	public JTextField getStudentSearch() {
		return studentSearch;
	}

	public void setStudentSearch(JTextField studentSearch) {
		this.studentSearch = studentSearch;
	}
	
}
