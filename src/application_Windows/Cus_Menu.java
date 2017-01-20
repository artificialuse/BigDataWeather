package application_Windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TextArea;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicButtonUI;

import Cus_UIs.Cus_ComboBoxUI;
import fileManager.AssetManager;
import fileManager.CityDailyWeather;
import fileManager.FileDownloader;
import fileManager.FileManipulator;
import fileManager.WeatherID;
import input_Control.MenuButtonHoverController;
import methods.Method;
import object_Controller.BackgroundControl;

/**The Menu that have the buttons to go to other panels
 * 
 * @author Ryan
 *
 */
public class Cus_Menu extends JPanel{

	//initialize all the private instances
	private Window window; 
	private JButton  table,lineGraph,pieChart,heatMap,report,hoverMap; //the button that will hold the images and the labels and it will change the panel
	private JButton update, reset, closeButton; //the buttons that allow the user to update the data and close the program
	private JLabel bgLabel,dataLabel,startLabel,endLabel, menu; //the JLabel that will provide indication to which text field is which
	private JComboBox<String> bgSelection; //the comboBox that allow the user to change the background

	private JLabel[] updateLabels; //the labels that shows the cities name
	private JTextField[] startYearInputs; // the input that will allow user to change the start year
	private JTextField[] endYearInputs; // the input that will allow user to change the end year


	/**constructor method
	 * 
	 * @param window a reference to the main frame
	 * @param bgControl a reference to the background control
	 * @param fileDownloader a reference to the file downloader
	 */
	public Cus_Menu(Window window, BackgroundControl bgControl, FileDownloader fileDownloader){
		this.window = window; //get a reference to other method within the class
		updateLabels = new JLabel[5]; //there will be 5 city labels
		startYearInputs = new JTextField[5]; //there will be 5 startYear inputs needed
		endYearInputs = new JTextField[5]; //there will be 5 endYear inputs needed

		setLayout(null); //set the layout to null

		menu = new JLabel("MENU"); //set the menu label
		menu.setBounds(100, 80, 500, 100); //set the bounds
		menu.setOpaque(false); //set the menu to be transparent
		menu.setFont(AssetManager.fonts[3]); //set the font 
		menu.setForeground(Color.white); //set the font color to wbe white

		//set the width and height and the start position and the end position of button so that the entire thing will changed changed by changing those variables
		int width = 255;
		int height = 255;
		int startX = 400;
		int startY = 130;
		int spacing = 20;
		// for all the labels that will lead the user to another panel, set the icon of the button and the text
		table = makeButton("Table",AssetManager.tableIcon,PanelID.Table,startX,startY,width,height); // To table button
		lineGraph = makeButton("Line Graph",AssetManager.lineGraphIcon,PanelID.LineGraph,startX+width+spacing,startY,width,height);// To line graph
		pieChart = makeButton("Pie Chart",AssetManager.pieChartIcon,PanelID.PieChart,startX,startY+height+spacing,width,height);// To pie chart
		heatMap = makeButton("HeatMap",AssetManager.heatMapIcon,PanelID.HeatMap,startX+width+spacing,startY+height+spacing,width,height);// To heat map
		report = makeButton("Report",AssetManager.reportIcon,PanelID.Report,startX+(width+spacing)*2,startY,width,height);// To report
		hoverMap = makeButton("Cities Map",AssetManager.citiesMapIcon,PanelID.HoverMap,startX+(width+spacing)*2,startY+height+spacing,width,height);// To hover map


		closeButton = new JButton(AssetManager.closeButton); //initialize the closeButton that will exit the program
		closeButton.setBounds(Window.WIDTH-50-15, 15, 50,50); //set the bound
		closeButton.setUI(new BasicButtonUI()); // set the UI
		closeButton.setOpaque(false); //set it to transparent
		closeButton.setBorderPainted(false); //set the border to be not painted 
		closeButton.addActionListener(e->System.exit(0)); //add the functionality to the close button

		bgLabel = createLable("Background:",100,280,200,50); //initialize the background label

		String[] bgs = {"No Background", "Theme: Spring", "Theme: Summer","Theme: Autumn","Theme: Winter"}; //the items that will be added to the comboBox
		bgSelection = new JComboBox<String>(bgs); //initialize the combo box based on the array
		bgSelection.setBounds(100, 330, 200, 30); //set the bound
		bgSelection.setFont(AssetManager.fonts[1]); //set the font
		bgSelection.setUI(new Cus_ComboBoxUI()); //set the UI of the comboBox to a customized one
		bgSelection.setSelectedIndex(bgControl.getCurrentBackground()); //set the default selected index to system time


		//create the labels that indicate what index is which
		dataLabel = createLable("Data/Year",100,395,85,30);  //create the label that indicate the tile of the data and year
		startLabel = createLable("Start",185,395,55,30); //create the start label
		endLabel = createLable("End",245,395,55,30); //create the end label

		int ySpacing = 35; //set the ySpacing 
		int counter = 0; //set the counter that will initialize all the counters
		for(CityDailyWeather eachCity: fileDownloader.getDownloadData()){ //for every city daily weather inside the downloaded data
			updateLabels[counter] = createLable(eachCity.getCity(),100,430+ySpacing*counter,85,30);  //initialize the city label
			startYearInputs[counter]  = createTextArea(185,430+ySpacing*counter,55,30,4); //initialize the start year input fields
			startYearInputs[counter].setText(Integer.toString(eachCity.getStartYear())); //set the text of the input fields to default years
			endYearInputs[counter]  = createTextArea(245, 430+ySpacing*counter,55,30,4); //initialize the end of your input fields
			endYearInputs[counter].setText(Integer.toString(eachCity.getEndYear()));//set the end year input field to default end years
			counter++; //increment counter
		}



		update = new JButton("Update"); //label that will allow the user to update the data
		update.setForeground(Color.black); //set the font to black
		update.setUI(new BasicButtonUI()); //set the UI to remove the default blue background
		update.setFont(AssetManager.fonts[1]); //set the font
		update.setBounds(100, 620, 95, 30); //set the bound

		reset = new JButton("Reset"); //initialize the reset button
		reset.setForeground(Color.black); //set the font color to black
		reset.setUI(new BasicButtonUI()); //set the UI to remove the default blue background
		reset.setFont(AssetManager.fonts[1]);//set the font
		reset.setBounds(205, 620, 95, 30);//set the bound

		JLabel umlLabel = createLable("URL",100,200,50,50);  //create the label that indicate the tile of the data and year
		JTextField uml = this.createTextArea(100, 250, 200, 30, 100);
		uml.setText(fileDownloader.uml);

		//add every component to the panel
		add(menu);
		add(table);
		add(lineGraph);
		add(pieChart);
		add(heatMap);
		add(report);
		add(hoverMap);
		add(closeButton);
		add(bgSelection);
		add(bgLabel);
		add(update);
		add(reset);
		add(uml);
		add(umlLabel);
		add(dataLabel);
		add(startLabel);
		add(endLabel);

		//add every JLabel and JTextField inside the array add them all to the panel
		for(JLabel labels:updateLabels)
			add(labels);
		for(JTextField textAreas:startYearInputs)
			add(textAreas);
		for(JTextField textAreas:endYearInputs)
			add(textAreas);	


		//set the functionality of the combo box listener
		bgSelection.addActionListener(e->{
			if(bgSelection.getSelectedIndex()==0){
				bgControl.setChanging(false); //0 is no background
			}else if(bgSelection.getSelectedIndex()==1){
				bgControl.setChanging(true); //allow the background to change
				bgControl.setCurrentBackground(WeatherID.Spring); //change the theme in background control class to Spring
			}else if(bgSelection.getSelectedIndex()==2){
				bgControl.setChanging(true);//allow the background to change
				bgControl.setCurrentBackground(WeatherID.Summer);//change the theme in background control class to Summer
			}else if(bgSelection.getSelectedIndex()==3){
				bgControl.setChanging(true);//allow the background to change
				bgControl.setCurrentBackground(WeatherID.Autumn);//change the theme in background control class to Autumn
			}else if(bgSelection.getSelectedIndex()==4){
				bgControl.setChanging(true);//allow the background to change
				bgControl.setCurrentBackground(WeatherID.Winter);//change the theme in background control class to Winter
			}
		});

		//set the functionality of the update and reset button
		update.addActionListener(e->{
			for(int i = 0; i<5;i++){ //for all 5 cities
				fileDownloader.getDownloadData()[i].setStartYear(Integer.parseInt(startYearInputs[i].getText())); //reset the start year in file downloader
				fileDownloader.getDownloadData()[i].setEndYear(Integer.parseInt(endYearInputs[i].getText())); //reset the end year in file downloader
				window.changeComp(PanelID.FileDownloading); //change the panel to FileDownloading
				fileDownloader.changeUML(uml.getText());
			}
		});

		reset.addActionListener(e->{
			for(int i = 0; i<fileDownloader.getDownloadData().length;i++){ //for all the CityDailyWeather information inside the data that need to be downloaded
				CityDailyWeather eachCity = fileDownloader.getDownloadData()[i]; //get the city
				startYearInputs[i].setText(Integer.toString(eachCity.getStartYear())); //set the text back to the city's information start year 
				endYearInputs[i].setText(Integer.toString(eachCity.getEndYear())); //set the text back to city'es information end year
				uml.setText(fileDownloader.uml);
			}
		});
	}

	/**draw the stuff
	 * 
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null);
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer));
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		g2d.setComposite(Method.makeTransparent(1));

	}

	/**create and return the textArea
	 * 
	 * @param x x position of the JTextField
	 * @param y y position of the JTextField
	 * @param width width of the JTextField
	 * @param height height of the JTextField
	 * @param wordLimit worldLimit off the JTextField
	 * @return return the styled JTextField
	 */
	private JTextField createTextArea(int x, int y, int width, int height, int wordLimit){
		JTextField textField = new JTextField(); //initialize the textField
		textField.setBounds(x, y, width, height); //set the bounds
		textField.setFont(AssetManager.fonts[4]); //set the font
		return textField; //return the textField
	}

	/**create and return the label
	 * 
	 * @param text the text of the label
	 * @param x the x position of the label
	 * @param y the y position of the label
	 * @param width the width of the label
	 * @param height the height of the label
	 * @return return the styled label
	 */
	private JLabel createLable(String text, int x, int y, int width,int height){
		JLabel label = new JLabel(text); //initialize the label
		label.setBounds(x, y, width, height); //set the bound
		label.setOpaque(false); //set the background to transparent
		label.setFont(AssetManager.fonts[1]); //set the font
		label.setForeground(Color.white); //set the font color to white
		return label; //return the label
	}


	/**
	 * create the return the button
	 * @param text the text of the button
	 * @param icon the icon of the button
	 * @param functionality the PanelID that the button will go to
	 * @param x the x position of the button
	 * @param y the y position of the button
	 * @param width the width of the button
	 * @param height the height of the button
	 * @return return the styled button
	 */
	private JButton makeButton(String text, ImageIcon icon, PanelID functionality, int x, int y, int width, int height){
		JButton button = new JButton(text,icon); //initialize the button with the text and the icon
		//button.setIcon(icon);
		button.setBounds(x, y, width, height); //set the bounds

		button.setVerticalTextPosition(SwingConstants.TOP); //set the vertical text position to top
		button.setHorizontalTextPosition(SwingConstants.CENTER); //set the horizontal text position to center
		button.setBorderPainted(false); //set the border to not be painted
		button.addActionListener(e->window.changeComp(functionality)); // add the functionality of the button to change to a specific panel
		button.setOpaque(false); //set the button to be transparent
		///button.setBorderPainted(false);
		button.setFont(AssetManager.fonts[6]); //set the font 
		button.setUI(new BasicButtonUI()); //set the UI to remove the default blue background
		button.setForeground(Color.white); //set the font color to be white
		button.addMouseListener(new MenuButtonHoverController(button)); //add the mouse listener to the button so that it change when hover
		return button; //return the button
	}

}
