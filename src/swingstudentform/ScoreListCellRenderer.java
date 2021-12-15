package swingstudentform;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

class ScoreListCellRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		String stringValue = (String) value;
		int firstNewLine = ((String) value).indexOf("\n")  ;
		String listText = "<html>" +
						  stringValue.substring(0,firstNewLine) +
						  "<br/>Course :<br/>" +
						  stringValue.substring(firstNewLine+1) +
						  "</html>"; 
		setText(listText);
		setBorder(new EmptyBorder(2,0,2,0));
		return this;
	}

}
