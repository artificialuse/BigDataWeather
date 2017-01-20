package application_Windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;

import Cus_UIs.Cus_ComboBoxUI;
import Cus_UIs.Cus_ScrollBarUI;
import fileManager.AssetManager;
import fileManager.FileManipulator;
import methods.Method;
import object_Controller.BackgroundControl;

/**the panel that will be a weather broadcast report
 * 
 * @author Ryan
 *
 */
public class Cus_Report extends JPanel implements KeyListener{

	//initialized the private instances
	//the JLabel that will hold a image or that will just be a indication of what the item beside it is
	private JLabel yearLabel,speech,box1,box2,box3,minTemp,maxTemp,avgTemp, imageHolder1, imageHolder2,imageHolder3, comboIndicater; 
	private JComboBox<String> combo; //a comboBox allow for city selection change
	private JTextField yearField;  //a textField that allow the user to input the wanted data range
	private JButton yearButton,home; // the button that will start progress the data or exit back to main menu
	private String script; // the script that will be format to act as the speech of the forecaster
	private String city; //string variable used to hold the current city selected
	private String trend;  //string variable used to hold and to be added more about the trend of the data range selected
	private float max; //declare the maximum value
	private float total;  //declare the total value
	private float totalCounter;  //declare the totalCounter
	private float min;  //declare the minimum value
	private float avg;  //declare the average

	
	private TreeMap<String, Integer> counter; //the counter to count the number of values in a month
	private TreeMap<String, Float> monthly; //the counter to count the total values in a month
	private TreeMap<String, Float> average; //the average that is calculated with the counter and monthly treeMap

	private JScrollPane scroller; //a scroller to hold the speech label so that the user can scroll to see full information

	/**
	 * Constructor method
	 * @param window a reference to the main frame
	 */
	public Cus_Report(Window window){
		//initialize the tree map that will be used to count the totals and averages
		counter=new TreeMap<>();
		monthly=new TreeMap<>(); 
		average=new TreeMap<>();
		//set the layout of the main panel to be null
		setLayout(null);
		setBackground(Color.green); //set the background of the panel to be green

		minTemp = new JLabel(); //initialize the minimum temperature label that will hold the information
		minTemp.setBounds(625,360,100,50); //set the bounds
		minTemp.setFont(AssetManager.fonts[0]); //set font
		minTemp.setForeground(Color.white); //set the font color to white
 
		maxTemp = new JLabel(); //initialize the label to hold the maximum temperature information
		maxTemp.setBounds(1056,360,100,50); //set the bounds
		maxTemp.setFont(AssetManager.fonts[0]); //set the font
		maxTemp.setForeground(Color.white); //set the font color to be white
 
		avgTemp = new JLabel(); //set the average label to hold the the average information
		avgTemp.setBounds(850,360,100,50); //set the bounds
		avgTemp.setFont(AssetManager.fonts[0]); //set the font
		avgTemp.setForeground(Color.white); //set the font color to be white

		box1 = new JLabel("Min Temp"); //set the label to indication the information
		box1.setBounds(640,270,100,50); //set the bounds
		box1.setFont(AssetManager.fonts[1]); //set the font

		box2 = new JLabel("Avg Temp"); //set the label to indication the information
		box2.setBounds(850,270,100,50);//set the bounds
		box2.setFont(AssetManager.fonts[1]);//set the font

		box3 = new JLabel("Max Temp"); //set the label to indication the information
		box3.setBounds(1062,270,100,50);//set the bounds
		box3.setFont(AssetManager.fonts[1]);//set the font

		imageHolder1 = new JLabel(AssetManager.sun); //initialize the imageHolder to hold weather forecast status default image will be sunny
		imageHolder1.setBounds(620,470,100,100);//set the bounds
		imageHolder1.setFont(AssetManager.fonts[1]);//set the font

		imageHolder2 = new JLabel(AssetManager.sun);//initialize the imageHolder to hold weather forecast status default image will be sunny
		imageHolder2.setBounds(830,470,100,100);//set the bounds
		imageHolder2.setFont(AssetManager.fonts[1]);//set the font

		imageHolder3 = new JLabel(AssetManager.sun);//initialize the imageHolder to hold weather forecast status default image will be sunny
		imageHolder3.setBounds(1042,470,100,100);//set the bounds
		imageHolder3.setFont(AssetManager.fonts[1]);//set the font




		yearLabel = new JLabel("Year-Month:"); //initialize the year Label to indicator what the input field is used for
		yearLabel.setBounds(30,20,100,30); //set the bounds
		yearLabel.setFont(AssetManager.fonts[1]); //set font
		yearLabel.setForeground(Color.white); //set the font color to white
		
		speech = new JLabel(); //initialize the speech text
		speech.setFont(AssetManager.fonts[1]); //set the font
		speech.setHorizontalTextPosition(JLabel.CENTER); //set the horizontal text to center

		scroller = new JScrollPane(speech); //set scroll pane to hold the speech and therefore the user can scroll the text
		scroller.setBounds(390, 85, 270, 130); //set the bounds
		scroller.setOpaque(false); //set the transparency to be transparent
		scroller.getViewport().setOpaque(false); //set he viewpoint to be transparent as well
		scroller.setHorizontalScrollBar(null); //remove the horizontal scroll bar
		scroller.getVerticalScrollBar().setUI(new Cus_ScrollBarUI()); //set the UI of the vertical scroll bar to be a customized UI
		scroller.getVerticalScrollBar().setOpaque(false); //set the vertical Scroll bar to be transparent
		scroller.setBorder(BorderFactory.createEmptyBorder()); //remove the border of the scroller pane

		yearField = new JTextField(); //initialize the text field that will allow the user to input data and change the displaying data range
		yearField.setBounds(135,20,100,30); //set the data
		yearField.addKeyListener(this); //add the key listener so that the method will execute when pressed enter
		yearField.setFont(AssetManager.fonts[1]); //set the font


		comboIndicater = new JLabel("City:"); //initialize the label that will indicate what the comboBox is used for
		comboIndicater.setBounds(Window.WIDTH/2+100, 130, 50, 30); //set the bounds
		comboIndicater.setFont(AssetManager.fonts[1]); //set the font
		comboIndicater.setForeground(Color.white); //set the font color to be white

		String[] abc = new String[]{"Charlottetown","Iqaluit","Markham", //initialize the array of cities that will be allowed for chosen in the comboBox
				"Vancouver","Whitehorse"};
		combo = new JComboBox<String>(abc); //initialize the comboBox for city selection
		combo.setBounds(Window.WIDTH/2+150, 130, 200, 30); //set the bounds
		combo.setFont(AssetManager.fonts[1]); //set the font
		combo.setUI(new Cus_ComboBoxUI()); //set the UI of the comboBox to be a customized one
		combo.addActionListener(e->forecastScript()); //add the functionality to the comboBox so that it will re-process the data

		yearButton = new JButton("Go!"); //initialize the button
		yearButton.setBounds(30,65,100,30); //set the bounds 
		yearButton.addActionListener(e->forecastScript()); //add the functionality to the button so that it will re-process the data
		yearButton.setUI(new BasicButtonUI()); //remove the default background style
		yearButton.setFont(AssetManager.fonts[1]); //set the font

		home = new JButton("Back"); //initialize the button
		home.setBounds(30,100,100,30); //set the bounds
		home.addActionListener(e->window.changeComp(PanelID.Menu)); //add the functionality to the button, so that when clicked it will change to the main menu
		home.setUI(new BasicButtonUI()); //set the UI to remove the default style
		home.setFont(AssetManager.fonts[1]); //set the font

		//when calling the constructor initialize the graphics and data once, so that something will show initially
		forecastScript();

		//add all component to the main panel
		add(minTemp);
		add(maxTemp);
		add(avgTemp);
		add(box1);
		add(box2);
		add(box3);
		add(comboIndicater);
		add(combo);
		add(yearField);
		add(yearLabel);
		add(scroller);
		add(yearButton);
		add(home);
		add(imageHolder1);
		add(imageHolder2);
		add(imageHolder3);

	}
	

	/**
	 * draw the stuffs
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //call the super component so that buttons and labels are drawn
		Graphics2D g2d = (Graphics2D) g; //cast graphics to 2D graphics
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image
		g2d.setColor(Color.black); //set the color
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparent according to a value
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a dark layer on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency back to opaque

		g2d.drawImage(AssetManager.reporter, -10, 0, null); //draw the picture of the reporter
		g2d.drawImage(AssetManager.speechBubble, 300, 20, null); //draw the speechBubble for the reporter

	}

	
	/**
	 * method that will re-calcualate the data based on the range given
	 */
	private void calculate(){ 
		counter.clear(); //clear the treeMaps that count the stuff
		monthly.clear(); 
		average.clear();

		//initialize all the data need with a beginning data
		max=-999; //-999 for maximum temperature is impossible so it will be replace later for sure
		min=999; //999 for minimum temperature is impossible so it will be replace later for sure
		total=0; //start counting the total from 0
		totalCounter=0; //start the counter for the total from 0 
		avg=0; //initialize the average to be 0
		city= (String)combo.getSelectedItem(); //get the city based on the selected item of the comboBox
		trend = ""; //need a String that will be the trend



		//for all the dates inside of the mean temperature
		for(String year: FileManipulator.meanTemp.get(city).keySet()){
			if(year.startsWith(yearField.getText())){ //if the year start will be current year(month/day) then process it
				float f = FileManipulator.meanTemp.get(city).get(year); //get the value on that day
				if(FileManipulator.maxTemp.get(city).containsKey(year)){ //if on that day there is a maximum temperature
					float fMax = FileManipulator.maxTemp.get(city).get(year); //get the maximum temperature
					max = Math.max(max, fMax); //store the maximum temperature if appropriate
				}
				if(FileManipulator.maxTemp.get(city).containsKey(year)){ //if on that day there is a minimum temperature
					float fMin = FileManipulator.minTemp.get(city).get(year); //get the minimum temperature
					min = Math.min(min, fMin); //store the minimum temperature if appropriate
				}
				
				total+=f; //total will be plus together
				totalCounter++; //increment the total counter by 1
				avg=total/totalCounter; //average = total/ totalCounter
				String yearMonth = year.substring(0, 7); //get the year and the month of that day
				if(counter.containsKey(yearMonth)){ //if the counter already contains that year and that month
					counter.put(yearMonth, counter.get(yearMonth)+1); //count number of data for that month
					monthly.put(yearMonth,monthly.get(yearMonth)+f); //count total for that month
				}
				else{ //if the counter does not contains the year and the month already
					counter.put(yearMonth,1); //initialize the counter will 1
					monthly.put(yearMonth,f); //initialize the key as the first value
				}
			}
		}
		
		//if there is data in the month that is read in
		if(!monthly.isEmpty()){
			for(String key: monthly.keySet()){ //for every date inside of the month
				average.put(key, monthly.get(key)/counter.get(key)); //get the average
			}

			String beforeKey = average.firstKey(); //get the first date
			float beforeValue = average.get(average.firstKey()); //get the first value
			String beforeTrend = ""; //initialize the current trend

			for(String key: average.keySet()){ //for everything data inside the average ket set
				float value = average.get(key); //get the average for that date range
				if(!key.equals((average.firstKey()))){ //skip the first key 
					String currentTrend = "";  //initialize the current trend
					if(value >= beforeValue){ //if value later is greater
						currentTrend = "Increasing"; //then it is increasing
					}else{ //when the value later is smaller
						currentTrend = "Decreasing"; //then it is decreasing
					}
					//is the current trend stays the same then directly goes to next analysis, else add the current trend to the trend analysis
					//also if in December the trend doesn't change report will not be added, so manually set it to do report on the last month
					if(!currentTrend.equals(beforeTrend)||key.equals(average.lastKey())){
						trend+=String.format("%s~%s is: %s<br>", beforeKey, key, beforeTrend); //add the trend before to the entire trend
						beforeKey = key; //update the keyValue since report is out once
						beforeTrend = currentTrend; //update the trend as well
					}
					beforeValue = value; //update the previous value so that current trend is always correct

				}else{ //for the first key
					if(value >= beforeValue){ //initialized one value based on the distance
						beforeTrend = "Increasing"; //if greater later value then increasing
					}else{
						beforeTrend = "Decreasing"; //if value is smaller later than it is decreasing
					}
				}
			}
		}else { //is there is no monthly data gathered
			max = 0; //set the maximum to be 0
			min = 0; //set the minimum to be 0
			trend = "No Data Available"; //show the trend as there is no data available
		}

		if(trend.equals("")){ //when the data is not selected for yearly data the trend will be empty
			trend = "Trend Analysis only availble <br>for yearly data."; //tell the user that it is only available for yearly data
		}


	}
	/**
	 *  calculate the max, min, average and trends, and then display them
	 */
	private void forecastScript(){
		calculate(); //calculate the averages, max, min, trend, again based on the selected year range and city
		//format the script to fit the purpose of the speech script
		//it is coded in html, because JLabel can be easily customized with html code
		script=String.format("<html><span bg color=\"white\">Good evening.<br>Welcome to Leo's Forecast 101! </span><br>"
				+ "<span bg color=\"white\">The city is:</span><span bg color=\"yellow\"> %s </span><br>"
				+ "<span bg color=\"white\">Minimum Temperature:</span><span bg color=\"yellow\"> %.2f </span><br>"
				+ "<span bg color=\"white\">Average Temperature:</span><span bg color=\"yellow\"> %.2f </span><br>"
				+ "<span bg color=\"white\">Maximum Temperature:</span><span bg color=\"yellow\"> %.2f </span><br>"
				+ "<span bg color=\"white\">The trend(available for yearly data)is: </span><br><span bg color=\"yellow\"> %s </span><br>"
				+ "<span bg color=\"white\">Thank you, Leo out.</span></html>", city, min, avg, max, trend);
		speech.setText(script); //set the speech label's text to the script

		minTemp.setText(String.format("%.2f",min)); //set the variable for the minimum temperature on the display panel on the report's background image
		changeImage(imageHolder1,min);	//change the image of the weather status based on the value
		avgTemp.setText(String.format("%.2f",avg)); //set the average for the label 
		changeImage(imageHolder2,avg); //change the image of the weather status based on the value
		maxTemp.setText(String.format("%.2f",max)); //set the maximum of the label
		changeImage(imageHolder3,max); //change the image of the weather status based on the value
	}

	/**
	 * change the image of the weather status based on the values
	 * @param imageHolder the JLabel that will gold the image
	 * @param temp the temperature as a float value
	 */
	private void changeImage(JLabel imageHolder, float temp){
		if(temp<-5){ //if the temperature is less than -5
			imageHolder.setIcon(AssetManager.snowflake); //set the image to be snowy
		}else if(temp>=-5&&temp<=15) //if the temperature is -5~15
			imageHolder.setIcon(AssetManager.cloudsun); //set the image to be cloudy
		else if(temp>15){ //if the temperature is greater then 5
			imageHolder.setIcon(AssetManager.sun); //set the image to be sunny
		}
	}

	/**this method have to be overridden since mouse listener is implemented
	 * 
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	} 

	/**this method have to be overridden since mouse listener is implemented
	 * 
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	/**when enter key is pressed, data will be processed as well
	 * 
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			forecastScript();
		}

	}
}
