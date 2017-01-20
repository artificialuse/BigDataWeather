package application_Windows;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import Cus_UIs.Cus_ComboBoxUI;
import fileManager.AssetManager;
import input_Control.CityButtonHoverController;

/**
 * 
 * @author Zhenyu
 *
 */
public class Cus_PieChartScene extends Cus_PieChart{

	private JComboBox<String> select; //set the comboBox that will be used to select the data type
	private JLabel title; //set the title of the pie chart, it is stored in a string, because it might be changed later
	private JPanel panel1, panel2, indicator, controller; //set different panels for the button controls, the actual pie chart and other controls such as the comboBox
	private String cityName; //store the current displaying cityName inside a String value, because then it can be changed easily when button is pressed
	private String dataType; //store the current dataType inside a String value, so that data type can be changed easily
	//initialize the array of topics which is all the data types
	private String[] topics = { "Mean Temperature", "Minimum Temperature", "Maximum Temperature", "Heat Days", "Cool Days", "Total Rain", "Total Snow", "Total Percipitation", "Snow On Ground", "Wind Speed" };
	private JLabel iqaluit_Label,vancouver_Label,charlottetown_Label,whitehorse_Label,markham_Label; //initialize all the city labels that will gold an indication arrow image
	private JButton iqaluit,vancouver,charlottetown,whitehorse,markham, back; //initialize all the buttons with its city picture

	/**
	 * Constructor method
	 * @param window a reference to the main frame
	 */
	public Cus_PieChartScene(Window window){
		//initialize the panels to hold the labels
		panel1 = new JPanel(); //initialize panel1 which will be used to hold the labels and buttons
		panel1.setBounds(50, 0,500,Window.HEIGHT); //set the bounds
		panel1.setOpaque(false); //set it to transparent
		panel1.setLayout(null); //set the layout to null


		panel2 = new JPanel(); //initialize the panel2 which will be used to hold the other titles and comboBox
		panel2.setBounds(570,0,1000,Window.HEIGHT); //set the bounds
		panel2.setOpaque(false);//set it to transparent
		panel2.setLayout(null); //set the layout to null

		indicator = new JPanel(); //set the indicator panel which will be used to hold the JLabels of the city in a vertical order
		indicator.setOpaque(false); //set it to be transparent
		indicator.setLayout(new GridLayout(5,1)); //set the layout to be a vertical grid layout
		indicator.setBounds(20, 50, 100, Window.HEIGHT-100); //set the bounds

		controller = new JPanel(); //initialize the controller panel which will be used to hold the buttons that will allow the user to change to city
		controller.setOpaque(false); //set the transparency to transparent
		controller.setLayout(new GridLayout(5,1)); //set the layout to be a vertical grid layout
		controller.setBounds(100, 50, 400, Window.HEIGHT-100); //set the bounds


		title = new JLabel(); //initialize the title for the title
		title.setForeground(Color.WHITE); //set the font color to white
		title.setText("Mean Temperature of Markham"); //initialize its text
		title.setFont(AssetManager.fonts[0]); //set the font
		title.setBounds(110,0,1000,100); //set the bounds

		//initialize the city labels with its corresponding icon image
		iqaluit_Label = new JLabel(AssetManager.cityIconArrow_white); 
		vancouver_Label = new JLabel(AssetManager.cityIconArrow_white);
		charlottetown_Label = new JLabel(AssetManager.cityIconArrow_white);
		whitehorse_Label = new JLabel(AssetManager.cityIconArrow_white);
		markham_Label = new JLabel(AssetManager.cityIconArrow_white);

		//create the buttons by calling the method that will create a button when its similar functionality and design
		iqaluit = makeButton(AssetManager.iqaluit,"Iqaluit", iqaluit_Label);
		vancouver = makeButton(AssetManager.vancouver,"Vancouver",vancouver_Label);
		charlottetown = makeButton(AssetManager.charlottetown,"Charlottetown",charlottetown_Label);
		whitehorse = makeButton(AssetManager.whitehorse,"Whitehorse",whitehorse_Label);
		markham = makeButton(AssetManager.markham,"Markham",markham_Label);


		back = new JButton("Back"); //initialize the back Button with its text
		back.setFont(AssetManager.fonts[1]); //set the font of the back button
		back.setUI(new BasicButtonUI()); //set the UI of the back button to remove its default background style
		back.addActionListener(e->window.changeComp(PanelID.Menu)); //add the functionality to the button to go back to main menu
		back.setBounds(450,650,100,30); //set the bounds


		select = new JComboBox<>(topics); //initialize the comboBox for selecting the data type
		select.setSelectedIndex(0); //set the default selected index to 0
		select.addItemListener(e->{ //set action when the selected item is changed
			dataType=(String) select.getSelectedItem(); // change the dataType based on the selected item
			change(); //call change which will change the data need to be progressed
		});
		select.setFont(AssetManager.fonts[1]); //change the font
		select.setUI(new Cus_ComboBoxUI()); //set the UI of the comboBox to a customized one
		select.setBounds(100,650,300,30); //set the bounds

		cityName = "Markham"; //initialized the initial city name to be Markham
		dataType = (String) select.getSelectedItem(); //initialized the initial data type to be default selection

		//add all the component to the different organized panels
		indicator.add(iqaluit_Label);
		controller.add(iqaluit);
		indicator.add(vancouver_Label);
		controller.add(vancouver);
		indicator.add(charlottetown_Label);
		controller.add(charlottetown);
		indicator.add(whitehorse_Label);
		controller.add(whitehorse);
		indicator.add(markham_Label);
		controller.add(markham);

		//add the indicator and the controller to panel1
		panel1.add(indicator);
		panel1.add(controller);
		//add the title, and select city comboBox and the back button to the panel2
		panel2.add(title);
		panel2.add(select);
		panel2.add(back);

		//set the layout of the main panel to be null
		setLayout(null);
		//add both organized panel to the main panel
		add(panel1);
		add(panel2);

		//change it one when creating the constructor so that the pie chart will show
		change();

	}


	/**method to create the buttons with similar design and style
	 * @param buttonImage the image of the button
	 * @param changeToCity the city that will be changed to
	 * @param label the associated JLabel
	 * @return the styled button with its functionalities
	 */
	private JButton makeButton(ImageIcon[] buttonImage, String changeToCity, JLabel label){
		JButton button = new JButton(buttonImage[0]);
		button.setOpaque(false);
		button.setUI(new BasicButtonUI());
		button.addActionListener(e->{
			cityName= changeToCity;
			change();
		});
		button.setBorderPainted(false);
		button.addMouseListener(new CityButtonHoverController(button, label, buttonImage));
		return button;
	}

	/** change the data needed to be progressed with the selected cityName and dataType, also change the title
	 * 
	 */
	private void change(){
		//change the data
		this.changeCity(cityName,dataType);
		//based on what is selected in the comboBox, change the title as well based on the selected data Type and city
		if (dataType.equalsIgnoreCase("Mean Temperature")){
			title.setText("Mean Temperature of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Minimum Temperature")){
			title.setText("Minimum Temperature of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Maximum Temperature")){
			title.setText("Maximum Temperature of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Heat Days")){
			title.setText("Heat Days of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Cool Days")){
			title.setText("Cool Days of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Total Rain")){
			title.setText("Total Rain of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Total Snow")){
			title.setText("Total Snow of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Total Percipitation")){
			title.setText("Total Precipitation of "+ cityName);
		}else if (dataType.equalsIgnoreCase("Snow On Ground")){
			title.setText("The Amount of Snow on the Ground in "+ cityName);
		}else if (dataType.equalsIgnoreCase("Wind Speed")){
			title.setText("Speed of Wind of "+ cityName);
		}
		//repaint the panel
		panel1.repaint();
	}

}
