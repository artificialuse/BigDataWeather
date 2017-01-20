package application_Windows;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicButtonUI;

import fileManager.AssetManager;
import object_Controller.MassHandler;
import objects.City;
import objects.Loading;
import objects.Map;

/** the controls for the heatMap
 * 
 * @author Leo
 *
 */
// the Cus_HeatMap_Control will be a JPanel
public class Cus_HeatMap_Control extends JPanel{

	//setting up private instance variables
	private Thread calculateThread;
	private JTextField requestX, requestY,requestTemp, requestYear,requestMonth,requestDay;
	private JLabel yearLabel, monthLabel,dayLabel,xLabel,yLabel, tempLabel;
	private JButton calculate, addCity,back;
	//constructor
	/** Constructor method for the heat map controls that will create a JPanel
	 * 
	 * @param window a reference to the main window
	 * @param canadaMap the map of Canada that is added to the scene
	 * @param handler the handler that will tick and render the objects
	 * @param loading the loading item that will show up when it is calculating
	 */
	public Cus_HeatMap_Control(Window window, Map canadaMap,MassHandler handler, Loading loading){
		setLayout(null); //set the layout to be null
		setOpaque(false); //set it be to transparent

		//initializing labels that contain "year, month, day"
		//every Label is created by calling a method since same type label will be created a lot
		yearLabel = createLabel("Year:",30,20,50,30); //create the yearLabel and set the bound
		monthLabel = createLabel("Month:",30,55,50,30); //create the monthLabel and set the bound
		dayLabel = createLabel("Day:",30,90,50,30); //create the dayLabel and set the bound

		//setting up areas where the user can type their specified year, month, and date
		//every text area is created by calling a method since same type text area will be created a lot
		requestYear = createTextArea(125,20,100,30,4); //create the requestYear field and the set the bound
		requestMonth =createTextArea(125,55,100,30,2); //create the requestMonth field and the set the bound
		requestDay = createTextArea(125,90,100,30,2); //create the requestDay field and the set the bound

		//setting up the button that will commence the calculations once the user has entered their specified data
		//every button is created by calling a method since same type button will be created a lot
		calculate = createButton("Calculate",30,140,100,30); //create the calculate button and the set the bound



		//second half of the control that will allow the user to add a city
		//setting up the labels that contain "x position", "y position", and "temperature" of specified city that the user wishes to search for 
		xLabel = createLabel("X:",30,225,50,30); //create the xLabel and set the bound
		yLabel = createLabel("Y:",30,260,50,30); //create the yLabel and set the bound
		tempLabel = createLabel("Temperature:",30,295,94,30); //create the tempLabel and set the bound

		//setting up areas where the user can type their specified city (x and position), and its temperature
		requestX = createTextArea(125,225,100,30,4); //create the requestX field and set the bound
		requestY =createTextArea(125,260,100,30,4);//create the requestY field and set the bound
		requestTemp =createTextArea(125,295,100,30,2); //create the requestTemp field and set the bound

		//button where once clicked, will show the specified city that the user wishes to search for on the heat map
		addCity = createButton("Add City",30,335,100,30); //create the button that will add the city

		//button that brings user back to main menu
		back = createButton("Back",30,370,100,30);//create the button that will allow the user to go back to main menu

		//add all components to the panel
		add(yearLabel);
		add(monthLabel);
		add(dayLabel);

		add(requestYear);
		add(requestMonth);
		add(requestDay);

		add(calculate);
		add(back);

		add(xLabel);
		add(yLabel);
		add(tempLabel);

		add(requestX);
		add(requestY);
		add(requestTemp);

		add(addCity);

		//add an action listener to calculate so that once clicked, the calculations for the specified city will begin
		calculate.addActionListener(e->{
			calculate.setEnabled(false); //disable the button itself to avoid continuously clicking
			addCity.setEnabled(false);//disable the addCity button ass well
			handler.addObject(loading); //add a loading thing to the view
			//reset the thread to be a thread that will calculate the information based on the data given
			//it is run on a separate thread to keep the loading screen going as well as keeping the main thread save
			initCalculateThread(canadaMap, handler, loading);
			calculateThread.start(); //start the thread
		});

		//add an action listener to back button so once clicked, user can return to main menu
		back.addActionListener(e->{
			window.changeComp(PanelID.Menu);
		});

		//add an action listener to addCity button once clicked, the specified city will be added and shown on the new heat map
		addCity.addActionListener(e->{
			calculate.setEnabled(false); //disable the calculate button to avoid continuously clicking
			addCity.setEnabled(false);  //disable the button itself as well
			handler.addObject(loading); //add a loading thing to the view
			//reset the thread to be a thread that will add a city to the heatMap based on the the information given
			//it is run on a separate thread to keep the loading screen going as well as keeping the main thread save
			initAddCityThread(canadaMap, handler, loading);
			calculateThread.start(); //start the thread
		});
	}

	/**method that create a button with certain style
	 * 
	 * @param text the text of the button
	 * @param x the x position of the button
	 * @param y the y position of the button
	 * @param width the width of the button
	 * @param height the height of the button
	 * @return return the styled button
	 */
	//method that creates a general basis for all buttons on this window
	private JButton createButton(String text, int x, int y, int width, int height ){
		JButton button = new JButton(text); //initialize the button with the text
		button.setBounds(x,y,width,height); //set the bound
		button.setFont(AssetManager.fonts[1]); //set the font
		button.setForeground(Color.black); //set the font color to be black
		button.setUI(new BasicButtonUI()); //remove the default blue background
		return button; //return the button
	}
	/**
	 *  method that reinitializes a thread that will add a city to the array and recalculate
	 * @param canadaMap the map that will be the map of Canada
	 * @param handler the handler that will tick and render the object
	 * @param loading the loading object
	 */
	private void initAddCityThread(Map canadaMap, MassHandler handler, Loading loading){
		calculateThread = new Thread(){ //initialize the new thread
			public void run() {
				Integer xPosition = null; //need a x position
				Integer yPosition = null; //need a y position
				Float cityTemp = null; //need a float value
				//use try and catch so that it will see which date is incorrect
				try{
					xPosition = Integer.parseInt(requestX.getText()); //try getting the information out of the requestX field, and parse the input into integer
				} catch(NumberFormatException e){ 
					requestX.setText("Wrong Input"); //if failed the the field to show that it is a wrong input
				}

				try{
					yPosition = Integer.parseInt(requestY.getText()); //try getting the information out of the requestY field, and parse the input into integer
				} catch(NumberFormatException e){
					requestY.setText("Wrong Input");//if failed the the field to show that it is a wrong input
				}

				try{
					cityTemp = Float.parseFloat(requestTemp.getText()); //try getting the information out of the requestTemp field, and parse the input into integer
				} catch(NumberFormatException e){
					requestTemp.setText("Wrong Input");//if failed the the field to show that it is a wrong input
				}

				if(xPosition!=null&&yPosition!=null&&cityTemp!=null){ //if nothing failed
					canadaMap.getCities().add(new City("Test City",xPosition,yPosition,cityTemp)); //add the new city to the map
					canadaMap.refreshColor(); //refresh the color
				}

				handler.removeObject(loading); //remove the loading thing
				calculate.setEnabled(true); //reset the calculate button to be enabled
				addCity.setEnabled(true); //reset the addCity button to be enabled

			}
		};
		calculateThread.setDaemon(true); //set the thread daemon to send it to a different collector

	}
	/**
	 * method that reinitializes the cities based on the selected date
	 * @param canadaMap the map that will be the map of Canada
	 * @param handler the handler that will tick and render the object
	 * @param loading the loading object
	 */

	private void initCalculateThread(Map canadaMap, MassHandler handler, Loading loading){
		calculateThread = new Thread(){ //initialize the new thread
			public void run() {
				canadaMap.setYear(getText(requestYear)); //get the text from requestYear, if it is not good return 0
				canadaMap.setMonth(getText(requestMonth)); //get the text from requestMonth, if it is not good return 0
				canadaMap.setDay(getText(requestDay)); //get the text from requestDay, if it is not good return 0
				canadaMap.refreshCities(); //refresh the cities based on the text
				canadaMap.refreshColor(); //refresh the color
				handler.removeObject(loading); //remove the loading thing
				calculate.setEnabled(true); //reset the calculate button to be enabled
				addCity.setEnabled(true);//reset the addCity button to be enabled
			}
		};
		calculateThread.setDaemon(true);//set the thread daemon to send it to a different collector
	}

	/** method that takes in user input and checks for appropriate format
	 * 
	 * @param textField the textField that want to get the data from
	 * @return return 0 if data is not correct return an int if the data is correct
	 */
	//method that takes in user input and checks for appropriate format
	private int getText(JTextField textField){
		int result; //declare an integer result
		try{
			result = Integer.parseInt(textField.getText()); // try parse the text from the textField to an integer
		}catch(Exception e){
			result = 0; //if it failed set the result to be 0
		}
		return result; //return the result
	}

	/**method that creates a general basis for all labels on this window
	 * 
	 * @param text the text of the label
	 * @param x the x position of the label
	 * @param y the y position of the label
	 * @param width the width of the label
	 * @param height the height of the label
	 * @return return the customized label
	 */
	private JLabel createLabel(String text,int x, int y, int width, int height){
		JLabel label = new JLabel(text); //initialize the JLabel with the text
		label.setBounds(x,y,width,height); //set the bound 
		label.setFont(AssetManager.fonts[1]); //set the font
		label.setForeground(Color.white); //set the font color to be whit
		return label; //return the label
	}
	/**
	 * 
	 * @param x the x position of the textField
	 * @param y the y position of the textField
	 * @param width the width of the textField 
	 * @param height the height of the textField
	 * @param wordLimit the word limit of the textField
	 * @return the styled JTextField
	 */
	//method that creates a general basis for all text areas on this window
	private JTextField createTextArea(int x, int y, int width, int height, int wordLimit){
		JTextField textField = new JTextField(); //initialized the textField
		textField.setBounds(x, y, width, height); //set the bound
		textField.setFont(AssetManager.fonts[1]); //set the font
		return textField; //return the font
	}

	//setters and getters for x and y positions
	//so that the mouseAdapter class can change the input field

	/**
	 * getter to get the JTextField for x position
	 * @return the JTextField that contains the target x position
	 */
	public JTextField getRequestX() {
		return requestX;
	}

	/**
	 * setter to set the JTextField for x position
	 * @param requestX a JTextField
	 */
	public void setRequestX(JTextField requestX) {
		this.requestX = requestX;
	}

	/**
	 * getter to get the JTextField for y position
	 * @return the JTextField that contains the target y position
	 */
	public JTextField getRequestY() {
		return requestY;
	}

	/**
	 * setter to set the JTextField for y position
	 * @param requestY a JTextField
	 */
	public void setRequestY(JTextField requestY) {
		this.requestY = requestY;
	}
}







