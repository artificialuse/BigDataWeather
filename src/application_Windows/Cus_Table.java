package application_Windows;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Cus_UIs.Cus_ComboBoxUI;
import Cus_UIs.Cus_ScrollBarUI;
import fileManager.AssetManager;
import fileManager.FileManipulator;
import input_Control.DocumentControl;
import methods.Method;
import object_Controller.BackgroundControl;

/**
 * The table that will organize the display all data
 * @author Ryan
 *
 */
public class Cus_Table extends JPanel{

	//setting up private instance variables
	private JLabel title; //a title that will indicate that this panel is a table
	private JTextField searchField; //searchField that allow user to search for a specific day
	private JLabel searchLabel; //label that will show that the textField is for searching
	private JButton homeButton; //a home button that will go back to main menu
	private DefaultTableModel model; //the table data
	private JTable table; //the table that holds the table data
	private JScrollPane scroller; //the scroller that holds the data for it to be able to scroll
	private JLabel cityLabel; // a cityLabel indicate what the comboBox is used for
	private JComboBox<String> combo; //the city selection comboBox
	private String city; //the String that will show the current city selected
	private TableRowSorter<TableModel> sorter; //the sorter that will be controlled by the searchField

	/**
	 * Constructor method
	 * @param window a reference to the main frame
	 */
	public Cus_Table(Window window) {
		
		//initialization of title of table
		title = new JLabel("Data Table"); //initialize the JLabel that will hold the text
		title.setBounds(Window.WIDTH/2-60, 30, 300, 40); //set the bounds
		title.setForeground(Color.white);  //set the font color to white
		title.setFont(AssetManager.fonts[0]); //set the font
		 
		//array of string that includes all headers for table
		String[] c=new String[]{"Date","Max Temp","Min Temp","Mean Temp",
				"Heat Days","Cool Days", "Total Rain","Total Snow","Total Precipitation",
				"Snow on Ground", "Direct Gust", "Speed Gust"};
		model=new DefaultTableModel(c,0);
		
		//initialize table format, included in Java API
		table=new JTable(model);

		//initialization of variable that will sort the data in the table
		sorter=new TableRowSorter<>(table.getModel());

		//initialize TextField where user can type in what they want to search
		searchField = new JTextField();
		searchField.setBounds(1030, 640, 200, 30); //set the bounds
		searchField.setFont(AssetManager.fonts[1]); //set the font
		searchField.getDocument().addDocumentListener( //add a document listener so that whenever the textField changes the sorter of the table will be changed as well
				new DocumentControl(searchField,sorter));

		//initialize button that will take user back to main menu
		homeButton = new JButton("Back"); //initialized the button with its text
		homeButton.setBounds(40, 640, 110, 30); //set the bounds
		homeButton.setFont(AssetManager.fonts[1]); //set the font 
		homeButton.setUI(new BasicButtonUI()); //set the UI to remove the default bluish background
		homeButton.addActionListener(e->window.changeComp(PanelID.Menu)); //add the actual functionality to the button
		
		//set up of the scroll pane on table that will allow users to view all data
		scroller =new JScrollPane(table); //put the table in a JScrollPane so that the table can be scrolled
		scroller.setBounds(40, 90, 1200, 530); //set the bounds
		
		//initialize current city that user is on
		cityLabel = new JLabel("City:"); //initialize the JLabel that will indicate the use of the comboBox
		cityLabel.setBounds(Window.WIDTH/2-100-25, 640, 200, 30); //set the bounds
		cityLabel.setForeground(Color.white); //set the font color to white
		cityLabel.setFont(AssetManager.fonts[1]); //set the font
		
		//initialize search label
		searchLabel = new JLabel("Search:"); //initialize the searchLabel with its text
		searchLabel.setBounds(960, 640, 200, 30); //set the bounds
		searchLabel.setForeground(Color.white); //set the font color to be white
		searchLabel.setFont(AssetManager.fonts[1]); //set the font

		//initialize array of string that includes all available cities for search
		String[] abc = new String[]{"Charlottetown","Iqaluit","Markham",
				"Vancouver","Whitehorse"};
		
		//initialize combo box where users can choose from a list of city options
		combo = new JComboBox<String>(abc); //initialize the comboBox with its items
		combo.setBounds(Window.WIDTH/2-100+25, 640, 200, 30); //set the bounds
		combo.setFont(AssetManager.fonts[1]); //set the font
		combo.setUI(new Cus_ComboBoxUI()); //set the UI to a customized one
		combo.addActionListener(new ActionListener() { //add action when comboBox change the selected item
			public void actionPerformed(ActionEvent e) {
				refreshCity(); //refresh the cities information based on the item selected
			}
		});

		//table aesthetics; to make it look as nice as it is currently
		table.getTableHeader().setFont(AssetManager.fonts[5]); //set the header of the font
		table.getTableHeader().setOpaque(false); //set the header to be transparent
		table.getTableHeader().setBackground(Color.BLACK); //set the background color of the header to be back
		table.getTableHeader().setForeground(Color.white); //set the font color of the header to be white
		table.getTableHeader().setReorderingAllowed(false); //set it so that the header cannot be rearranged
		table.getTableHeader().setEnabled(false); //disable the header
		table.setFont(AssetManager.fonts[1]); //set the font of the cells of the table
		table.setForeground(Color.white); //set the font color of the table to be white
		table.setRowSorter(sorter); //assign a sorter to the table
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //set selection mode to single selection only
		table.setDefaultEditor(Object.class, null); //set the default editor to null
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); //set it so that it does not automatically resize itself, we will set the width
		table.setSelectionForeground(Color.cyan); //set the selected cells' color to be Cyan
		table.setRowHeight(30); //set the height of each row to 30
		table.setGridColor(Color.white); //set the color of the grid to white
		table.setOpaque(false); //set the table to be transparent
		((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false); //set the default rendered of the table to be completely transparent
		
		//scrolling pane aesthetics
		scroller.setOpaque(false); //set the background of the scroll pane to be transparent
		scroller.getViewport().setOpaque(false); //set the view point to be transparent as well
		scroller.getHorizontalScrollBar().setUI(new Cus_ScrollBarUI()); //set the horizontal scroll bar to have a customized one
		scroller.getHorizontalScrollBar().setOpaque(false); //set the background of the horizontal scroll bar to be transparent
		scroller.getVerticalScrollBar().setUI(new Cus_ScrollBarUI()); //set the vertical scroll bar to have a customized one
		scroller.getVerticalScrollBar().setOpaque(false); //set the background of the vertical scroll bar to be transparent
		
		//method that sets up how wide each column can be
		for(int i=0;i<c.length;i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(150); //every column will have a width of 150
		}

		//adding components to main Window and repainting each time if necessary
		setLayout(null); //set the layout to null
		add(title);
		add(scroller);
		add(searchLabel);
		add(cityLabel);
		add(combo);
		add(searchField);
		add(homeButton);
		//re-validate and repaint to make sure that the component is changed and painted correctly
		repaint();
		revalidate();
		//when the constructor is called, refresh the information of the city once so that there is some data initially
		refreshCity();
	}

	/**
	 * method that draws on the background images behind the table
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //call the super component to draw the button etc.
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to be a 2D graphics
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image
		g2d.setColor(Color.black); //set the color to black
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparency of the graphics based on the value
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a dark layout on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency back to opaque
		
	}

	/**method that updates the data (per city) that the user wants to see onto the table
	 * 
	 */
	private void refreshCity(){
		String selected = (String) combo.getSelectedItem(); //get what the comboBox is selecting
		model.setRowCount(0); //set the row count to 0
		city=selected; //set the city to selected item
		changeCity(model); //change the model of the city
		scroller.repaint(); //repaint the table
	}

	/**method that gets the data for each city as the user switches between cities
	 * 
	 * @param model the model of the table that need to be changed
	 */
	public void changeCity(DefaultTableModel model){  
		for(String date: FileManipulator.meanTemp.get(city).keySet()){ //based on the city selected, for loop all dates available
			model.addRow(new Object[] {date, FileManipulator.maxTemp.get(city).get(date), // add the row that have every data possible for that specific day
					FileManipulator.minTemp.get(city).get(date),FileManipulator.meanTemp.get(city).get(date),
					FileManipulator.heatDays.get(city).get(date),FileManipulator.coolDays.get(city).get(date),
					FileManipulator.totalRain.get(city).get(date),FileManipulator.totalSnow.get(city).get(date),
					FileManipulator.totalPrecip.get(city).get(date),FileManipulator.snowOnGround.get(city).get(date),
					FileManipulator.direcGust.get(city).get(date),FileManipulator.speedGust.get(city).get(date)});
		}
	}

}


