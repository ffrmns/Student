package swingstudentform;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ScoreListener implements ActionListener {
	
	private ScoreFrame frame;
	private JPanel scorePanel;
	
	
	ScoreListener (ScoreFrame frame){
		this.frame = frame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		ArrayList<Student> searchedStudent = searchStudent(this.frame.getStudentArray());
 		
		System.out.println(this.frame.getStudentArray().containsAll(searchedStudent));

		Iterator<Student> searchedStudentArrayIterator = searchedStudent.iterator();
		scoreResultLayoutListener();
		
		DefaultListModel listModel = new DefaultListModel();
		JList jList = new JList(listModel);
		jList.setCellRenderer(new ScoreListCellRenderer());
		jList.setBorder(new EmptyBorder(5, 10, 5, 10) );
		
		jList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				String rawChoosedList = jList.getSelectedValue().toString();
				int firstNewLine = rawChoosedList.indexOf("\n");
				String[] courseOfChoosed = rawChoosedList.substring(firstNewLine).split("\n");
				frame.getChoosedStudent().setText("<html>"+rawChoosedList.replaceAll("\n", "<br/>")+"</html>");
				
				frame.getCourseScoreEditComboBox().setModel(new DefaultComboBoxModel<>(courseOfChoosed));
				
			}
		});
		

		scorePanel.setLayout(frame.getGridBagLayout());
		frame.getGridBagConstraints().insets = new Insets(0,0,0,0);
		scorePanel.add(new JScrollPane(jList),frame.getGridBagConstraints());
		
		scorePanel.revalidate();
		scorePanel.repaint();
		while (searchedStudentArrayIterator.hasNext())
		{
			scorePanelController(searchedStudentArrayIterator,listModel);
		}
	}
	
	public Score matchScore(Student chosenStudent) {
		ArrayList<Score> scoreArray = this.frame.getScoreArray();
		Iterator<Score> scoreArrayIterator = scoreArray.iterator();
		HashMap<String,Integer> emptyScore = new HashMap<String, Integer>();
		Score studentScore = new Score(chosenStudent.getStudentID(),emptyScore);
		while (scoreArrayIterator.hasNext()) {
			Score currentScore = scoreArrayIterator.next();
			if (chosenStudent.getStudentID().equals(currentScore.getStudentID()))
				studentScore = currentScore;
		}
			
		return studentScore;
	}
	public void scoreResultLayoutListener() {
		scorePanel = this.frame.getScorePanel();
		scorePanel.removeAll();
	}
	
	public ArrayList<Student> searchStudent(ArrayList<Student> studentArrayList){
		Iterator<Student> studentArrayIterator = studentArrayList.iterator();
		ArrayList<Student> searchedStudentArrayList = new ArrayList<Student>();
		while (studentArrayIterator.hasNext()) {
			Student student = studentArrayIterator.next();
			if (student.getStudentName().matches("(?i)" + this.frame.getSearchID().getText()+".*")) 
				searchedStudentArrayList.add(student);
		}
		
		System.out.println(searchedStudentArrayList);
		
		return searchedStudentArrayList;
	}
	
	public void scorePanelController(Iterator<Student> searchedStudentArrayIterator, DefaultListModel listModel) {
		Student chosenStudent = searchedStudentArrayIterator.next();
		Score studentScore = matchScore(chosenStudent);
		
		addResultToScorePanel(studentScore,listModel);

	}
	public void addResultToScorePanel(Score studentScore, DefaultListModel listModel) {

		HashMap<String, Integer> studentScoreMap = studentScore.getCourseScore();
		String scoreAsLabel = createStringAsScoreRepresentation(studentScoreMap);		
		JLabel studentIDLabel = new JLabel(studentScore.getStudentID());
		
		listModel.addElement(studentIDLabel.getText() 
					+ "\n" + scoreAsLabel );
		
	}
	
	public String createStringAsScoreRepresentation(HashMap<String, Integer> studentScoreMap) {
		 
		StringBuilder studentScoreStringBuilder = new StringBuilder();
		if (studentScoreMap.isEmpty())
			studentScoreStringBuilder.append("No data");
		else {
			for (Map.Entry<String, Integer> entry:studentScoreMap.entrySet()) {
				studentScoreStringBuilder.append(entry.getKey() + ": " + entry.getValue() + "\n");
				System.out.println(entry.getKey() + ": " + entry.getValue());
			};
		}
		
		return studentScoreStringBuilder.toString();
	}

}
