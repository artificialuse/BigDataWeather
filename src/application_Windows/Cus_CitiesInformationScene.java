package application_Windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicButtonUI;

import Cus_UIs.Cus_ScrollBarUI;
import fileManager.AssetManager;
import input_Control.SwitchButtonHoverController;
import methods.Method;
import object_Controller.BackgroundControl;

/**The city's information with description and images of the city
 * @author Zhenyu
 */
public class Cus_CitiesInformationScene extends JPanel{

	//setting up private instance variables
	private JLabel title1,title2, cityPic, cityFlag, descriptionTitle; //JLabel for indications and descriptions
	private JButton back, left, right; // a button that allow user to go back
	private int currentImageIndex; //current displaying image's index
	private ImageIcon[] cityImages; //the array that will store all images that will be in he display cycle
	private JTextPane txt; // the text pane that is filled will the city's description
	private JScrollPane scrollPane; //the text pane is put inside the scroll pane so that is allow the user to scroll to see the full text
	
	/**
	 * @author Zhenyu
	 * @param cityName the name of the city
	 * @param cityImages the array of the images of the city
	 * @param informationText the text that hold the description of the city
	 * @param window reference to the main window
	 */
	public Cus_CitiesInformationScene(String cityName, ImageIcon[] cityImages, String informationText, Window window){

		//initialize background images variable
		this.cityImages = cityImages;
		currentImageIndex = 1;
		
		//setting up home button that will take user back to main menu
		back = new JButton("Back"); // initialize back button
		back.setBounds(1080, 620, 100, 30); //set bounds
		back.addActionListener(e->window.changeComp(PanelID.HoverMap)); //give back button and functionality when clicked
		back.setFont(AssetManager.fonts[1]); // set font
		back.setUI(new BasicButtonUI()); //set UI to basic to remove the default blue background

		title1 = new JLabel("City Of"); //initialize the title label that show "City Of"
		title1.setFont(AssetManager.fonts[6]); //set the font
		title1.setForeground(Color.white); //set the font color
		title1.setBounds(80, 40, 500, 50); //set the bounds
		
		//setting up name of city that user chooses on CityMap
		title2 = new JLabel(cityName); //initialize the second JLabel that shows the city name
		title2.setFont(AssetManager.fonts[6]); //set the font
		title2.setForeground(Color.white);// set the font color
		title2.setBounds(80, 100, 500, 50);//set the bounds
		
		//setting up description header in the panel for the city
		descriptionTitle = new JLabel("City's Description"); //initialize the the indication label for the description of the city
		descriptionTitle.setFont(AssetManager.fonts[0]); //set the font
		descriptionTitle.setForeground(Color.white); //set the font color
		descriptionTitle.setBounds(730, 40, 500, 50); //set the bounds

		//pictures of city chosen will rotate as time progresses
		cityPic = new JLabel(); //initialize the label to hold the cities' pictures
		cityPic.setBounds(80, 200, 600, 450); //set the bounds
		cityPic.setIcon(cityImages[currentImageIndex]); //set the icon

		//setting up picture for the chosen city flag
		cityFlag = new JLabel(); //initialize the JLabel to hold the flag of the city
		cityFlag.setBounds(430, 40, 250, 150); //set the bounds
		cityFlag.setIcon(cityImages[0]);//initialize the JLabel with a specific picture

		//this is where the actual description of each city will be stored; JTextPane that is not editable
		txt = new JTextPane(); //initialize the JTextPane that will hold the city's description
		txt.setEditable(false);//set it to not editable
		txt.setFont(AssetManager.fonts[5]);//set the font
		txt.setForeground(Color.white);// set the font color
		txt.setText(informationText);//initialize the text
		txt.setOpaque(false); //set it to transparent

		//initializing a scroll pane for the text pane so the user can go through the whole description of each city
		scrollPane = new JScrollPane(txt); //initialize the scrollPane that will hold the text pane
		scrollPane.setBounds(730, 120, 450, 480); //set bound
		scrollPane.setOpaque(false); //set it to transparent
		scrollPane.getViewport().setOpaque(false); //set the view point to transparent
		scrollPane.getVerticalScrollBar().setUI(new Cus_ScrollBarUI()); //set the vertical scroll bar UI to a customized one
		scrollPane.setHorizontalScrollBar(null); //remove the horizontal scroll bar
		scrollPane.getVerticalScrollBar().setOpaque(false); //set the vertical scroll bar to be transparent

		//initializing left and right buttons so user can switch to different pictures
		left = makeSwitchButton(AssetManager.leftSwitchButtonImage,-1,80,385,50,80); //make a left button with hover switch images
		right = makeSwitchButton(AssetManager.rightSwitchButtonImage, 1,630,385,50,80);//make a right button with hover switch images
		
		//setting layout of the panel and adding components to the panel
		setLayout(null); //set the layout to be null
		//add all components
		add(left);
		add(right);
		add(back);
		add(cityFlag);
		add(scrollPane);
		add(cityPic);
		add(title1);
		add(title2);
		add(descriptionTitle);
		
	}
	/** create a button that will switch the image and also switch its color on hover
	 * @author Zhenyu
	 * @param switchType the image that the button will switch to
	 * @param num set if the index will go back or go forward once
	 * @param x x position of the button
	 * @param y y position of the button
	 * @param width width of the button
	 * @param height height of the button
	 * @return return the styled JButton
	 */
	
	//method that sets up the left and right buttons so that the user can switch between images of the same city
	private JButton makeSwitchButton(ImageIcon[] switchType, int num, int x, int y, int width, int height){
		JButton switchButton = new JButton(); //initialize the button
		switchButton.setIcon(switchType[0]); //initialize the image that the button will hold
		switchButton.setBounds(x,y,width,height);//set the bounds
		switchButton.setOpaque(false); //set it to transparent
		switchButton.setBorderPainted(false); //set it to not paint the border
		switchButton.setUI(new BasicButtonUI()); //set it to not have a default background
		switchButton.addMouseListener(new SwitchButtonHoverController(switchButton, switchType)); //add the hover interface that will change the image hold on hover
		switchButton.addActionListener(e->{
			// add the actual functionality of the program
			currentImageIndex += num; //the currentImageIndex will either be +1 or -1
			
			// make sure that it is not 0 (0 is the flag) and it is not bigger than the array length. If so, change it one step forwards to a cycle
			if(currentImageIndex>=cityImages.length){ 
				currentImageIndex = 1;
			}else if(currentImageIndex <= 0){
				currentImageIndex = cityImages.length - 1;
			}
			cityPic.setIcon(cityImages[currentImageIndex]); //reset the icon after the index is selected
		});
		return switchButton; //return the button
	}
	
	/**
	 * method that draws the backgroundImages onto the current panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g); //calls super component to draw the buttons and all components
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to be a 2D graphics
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image
		g2d.setColor(Color.black); //set the color to be black
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the next drawing thing to be translucent based on a value
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw the dark layer on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency back
		
	}

}



