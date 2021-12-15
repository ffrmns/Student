package swingstudentform;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ScoreFrame extends JFrame {

	private static final long serialVersionUID = 3434751014169009585L;
	private ArrayList<Student> studentArray;
	private Container contentPane;
	private GridBagConstraints gridBagConstraints;
	private JTextField searchID;
	private JPanel scorePanel;
	private GridBagLayout gridBagLayout;
	private ArrayList<Score> scoreArray;
	private JLabel choosedStudent;
	private JComboBox courseScoreEditComboBox;
	private JTextField courseScoreEditTextField;

	public JComboBox getCourseScoreEditComboBox() {
		return courseScoreEditComboBox;
	}
	public void setCourseScoreEditComboBox(JComboBox courseScoreEditComboBox) {
		this.courseScoreEditComboBox = courseScoreEditComboBox;
	}
	public JLabel getChoosedStudent() {
		return choosedStudent;
	}
	public ArrayList<Score> getScoreArray() {
		return scoreArray;
	}

	public void setScoreArray(ArrayList<Score> scoreArray) {
		this.scoreArray = scoreArray;
	}

	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}

	public void setGridBagLayout(GridBagLayout gridBagLayout) {
		this.gridBagLayout = gridBagLayout;
	}

	public JPanel getScorePanel() {
		return scorePanel;
	}

	public void setScorePanel(JPanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	public JTextField getSearchID() {
		return searchID;
	}

	public void setSearchID(JTextField searchID) {
		this.searchID = searchID;
	}

	public ArrayList<Student> getStudentArray() {
		return studentArray;
	}

	public void setStudentArray(ArrayList<Student> studentArray) {
		this.studentArray = studentArray;
	}


	public void setContentPane(Container contentPane) {
		this.contentPane = contentPane;
	}

	public GridBagConstraints getGridBagConstraints() {
		return gridBagConstraints;
	}

	public void setGridBagConstraints(GridBagConstraints gridBagConstraints) {
		this.gridBagConstraints = gridBagConstraints;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Student testData0 = new Student("Fikri", "2015", "15011");
		Student testData1 = new Student("Fikru", "2015", "15001");
		Student testData2 = new Student("Fikre", "2015", "15002");
		Student testData3 = new Student("Fikro", "2015", "15003");
		Student testData4 = new Student("Fikra", "2015", "15004");
		Student testData5 = new Student("Fikri", "2015", "15005");
		Student testData10 = new Student("Fikri", "2015", "15011");
		Student testData11 = new Student("Fikru", "2015", "15001");
		Student testData12 = new Student("Fikre", "2015", "15002");
		Student testData13 = new Student("Fikro", "2015", "15003");
		Student testData14 = new Student("Fikra", "2015", "15004");
		Student testData15 = new Student("Fikri", "2015", "15005");

		ArrayList<Student> testArrayList = new ArrayList<Student>();
		testArrayList.add(testData0);
		testArrayList.add(testData1);
		testArrayList.add(testData2);
		testArrayList.add(testData3);
		testArrayList.add(testData4);
		testArrayList.add(testData5);
		testArrayList.add(testData10);
		testArrayList.add(testData11);
		testArrayList.add(testData12);
		testArrayList.add(testData13);
		testArrayList.add(testData14);
		testArrayList.add(testData15);
		
		HashMap<String, Integer> testScoreMap0 = new HashMap<String, Integer>();
		testScoreMap0.put("Circuit I", 60);
		testScoreMap0.put("Circuit II", 55);
		Score testScore0 = new Score("15011",testScoreMap0);
		HashMap<String, Integer> testScoreMap1 = new HashMap<String, Integer>();
		testScoreMap1.put("Circuit I", 61);
		testScoreMap1.put("Fluid I", 61);
		Score testScore1 = new Score("15002",testScoreMap1);
		HashMap<String, Integer> testScoreMap2 = new HashMap<String, Integer>();
		testScoreMap2.put("Thermo I", 60);
		testScoreMap2.put("Thermo II", 58);
		Score testScore2 = new Score("15001",testScoreMap2);
		HashMap<String, Integer> testScoreMap3 = new HashMap<String, Integer>();
		testScoreMap3.put("Thermo I", 60);
		testScoreMap3.put("Fluid I", 60);
		Score testScore3 = new Score("15003",testScoreMap3);
		HashMap<String, Integer> testScoreMap4 = new HashMap<String, Integer>();
		testScoreMap4.put("Circuit II", 60);
		testScoreMap4.put("Thermo II", 60);
		Score testScore4 = new Score("15004",testScoreMap4);
		
		ArrayList<Score> scoreArrayList = new ArrayList<>();
		scoreArrayList.add(testScore0);
		scoreArrayList.add(testScore1);
		scoreArrayList.add(testScore2);
		scoreArrayList.add(testScore3);
		scoreArrayList.add(testScore4);
		
		System.out.println(new ScoreFrame(testArrayList));
		ScoreFrame jframe = new ScoreFrame(testArrayList,scoreArrayList);
		jframe.setVisible(true);
	}
	ScoreFrame (ArrayList<Student> studentArray) {
		ScoreFrame scoreFrame = new ScoreFrame(studentArray,null);
	}
	ScoreFrame (ArrayList<Student> studentArray, ArrayList<Score> scoreArray) {
		this.scoreArray = scoreArray;
		this.studentArray = studentArray;
		this.contentPane = getContentPane();
		gridBagLayout = new GridBagLayout();
		
		System.out.println(gridBagLayout + " 1 " + this.contentPane);
		this.contentPane.setLayout(new GridBagLayout());
		System.out.println(gridBagLayout + "2");
		gridBagConstraints = gridBagLayout.getConstraints(this.contentPane);
		
		setBounds(100, 100, 450,300);
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		
		JLabel searchLabel = new JLabel("Search");
		addSearchLabelToFrame(searchLabel);
		
		searchID = new JTextField("Fikri");
		addSearchIDToFrame();
		addListener();
		
		scorePanel = new JPanel();
		addScorePanelToFrame();
		addClickedLabelToFrame();
		addCourseScoreEditToFrame();
	}
	public void addListener() {
		ScoreListener scoreListener = new ScoreListener(this);
		searchID.addActionListener(scoreListener);
		DocumentListener searchDocumentListener = new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("a");
				scoreListener.actionPerformed(null);
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("b");
				scoreListener.actionPerformed(null);
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				System.out.println("c");
			}
		};
		searchID.getDocument().addDocumentListener(searchDocumentListener);
	}
	public void addSearchIDToFrame() {
		
		
		gridBagConstraints.gridy += 1;
		
		
		this.contentPane.add(searchID, gridBagConstraints);
	}
	public void addSearchLabelToFrame(JLabel searchLabel) {
		gridBagConstraints.gridy = 0;
		
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.insets = new Insets(0,20,0,20);
		gridBagConstraints.ipady = 5;
		this.contentPane.add(searchLabel, gridBagConstraints);
	}
	public void addScorePanelToFrame() {
		
		gridBagConstraints.gridy += 1;
		
		
		this.contentPane.add(scorePanel, gridBagConstraints);
		
	}
	
	public void addClickedLabelToFrame() {
		choosedStudent = new JLabel();
		
		
		gridBagConstraints.gridy += 1;
		this.contentPane.add(choosedStudent, gridBagConstraints);
	}
	
	public void addCourseScoreEditToFrame() {
		
		
		
		courseScoreEditComboBox = new JComboBox<>();
		
		courseScoreEditTextField = new JTextField("Change it here");
		
		this.contentPane.add(courseScoreEditTextField,gridBagConstraints);
		
		JPanel courseScoreEditPanel = new JPanel();
		gridBagConstraints.gridy += 1;
		courseScoreEditPanel.add(courseScoreEditComboBox);
		courseScoreEditPanel.add(courseScoreEditTextField);
		this.contentPane.add(courseScoreEditPanel,gridBagConstraints);
	}
}
