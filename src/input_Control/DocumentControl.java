package input_Control;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * the document control that will update the sorter based on the text inputed
 * @author Ryan
 *
 */
public class DocumentControl implements DocumentListener{

	//initialization of private instance variables
	private JTextField searchField; //searchField that execute the command and resort the list
	private TableRowSorter<TableModel> sorter; // the sorter that is added to the table so that certain data is sorted out

	/**
	 * constructor
	 * @param searchField the searchField that need a document listener
	 * @param sorter set the sorter for the table
	 */
	public DocumentControl(JTextField searchField,	TableRowSorter<TableModel> sorter){
		this.searchField=searchField; //give a reference to the searchField so that it can be used in other method within the class as well
		this.sorter=sorter; //give a reference to the sorter as well
	}

	/**
	 * when the document is updated
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {
		update(); //when the document is updated, update the sorter
	}


	/**
	 * when the document is inserted, Update
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {
		update(); //when extra stuff is inserted, update the sorter

	}

	/**
	 * when the document is removed, update
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		update(); //when any stuff is removed, update the sorter
	}

	/**
	 * method that filters through the data relevant to what the user has typed in the text field
	 */
	//and updates the table in response
	private void update(){
		String text=searchField.getText(); //get the text from the searchField
		if(text.trim().length()==0){ //trim the text to remove the spaces
			sorter.setRowFilter(null); //if there is not text, then there is not row filter
		}
		else{ //is there is text
			sorter.setRowFilter(RowFilter.regexFilter(text)); //set the row filter to be the text
		}	
	}
}
