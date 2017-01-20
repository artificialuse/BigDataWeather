package application_Windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;

import fileManager.AssetManager;
import input_Control.PinButtonHoverController;
import methods.Method;
import object_Controller.BackgroundControl;

/**The Scene that will show map the cities
 * 
 * @author Zhenyu
 *
 */
public class Cus_HoverMapScene extends JPanel{

	//initialize all the private instances
	private JLabel title; //a title that will say what this panel is
	private Window window; //get a reference to the main window, so that the other private method can use it
	//declare all the JLabels and JButtons that will be included in this panel
	private JLabel whitehorseButtonLabel,vancouverButtonLabel,iqaluitButtonLabel,charlottetownButtonLabel,markhamButtonLabel;
	private JButton  whitehorseButton,vancouverButton ,iqaluitButton, charlottetownButton,markhamButton,back;

	/** Constructor method
	 * 
	 * @param window a reference to the main frame
	 */
	
	public Cus_HoverMapScene(Window window){
		this.window = window; //initialize the private variable window based on the parameter

		//initialized all the components
		title = new JLabel("Map of Canada"); //initialize the title label with the text
		title.setFont(AssetManager.fonts[7]); //set the font
		title.setForeground(Color.white); //set the font color to white
		title.setBounds(150, 20, 350, 100); //set the bounds

		//create all the Pin Button Labels with the parameters
		//method with return type JLabel is called, because labels are about the same, and 5 are created
		//those labels will not be visible, and its visibility will be controlled by a mouse listener when hover over its corresponding button.
		whitehorseButtonLabel = makePinButtonLabel("Whitehorse",Color.white,175,195,200,50);
		vancouverButtonLabel =  makePinButtonLabel("Vancouver",Color.blue,175,370,200,50);
		iqaluitButtonLabel =  makePinButtonLabel("Iqaluit",Color.red,750,210,200,50);
		charlottetownButtonLabel =  makePinButtonLabel("Charlottetown",Color.green,920,407,200,50);
		markhamButtonLabel =  makePinButtonLabel("Markham",Color.yellow,750,500,200,50);

		//create all the Pin Button with the parameters
		//method with return type JButton is called, because buttons are about the same, and 5 are created
		whitehorseButton = makePinButton(AssetManager.whitePin,whitehorseButtonLabel, 200,235,38,53, PanelID.WhitehorsePage);
		vancouverButton = makePinButton(AssetManager.bluePin,vancouverButtonLabel, 200,410,38,53, PanelID.VancouverPage);
		iqaluitButton = makePinButton(AssetManager.redPin, iqaluitButtonLabel, 755,250,38,53, PanelID.IqaluitPage);
		charlottetownButton = makePinButton(AssetManager.greenPin,charlottetownButtonLabel, 960,447,38,53, PanelID.CharlottetownPage);
		markhamButton = makePinButton(AssetManager.yellowPin,markhamButtonLabel, 770,540,38,53, PanelID.MarkhamPage);

		back = new JButton("Back"); //initialize the back button with its text
		back.setBounds(1080, 620, 100, 30); //set the bounds
		back.setFont(AssetManager.fonts[1]); //set the font
		back.setUI(new BasicButtonUI()); //remove the default blue background 
		back.addActionListener(e->window.changeComp(PanelID.Menu)); // add the functionality for it to go back to the main menu

		setLayout(null); //set it to a null layout
		
		//add all the components previously created
		add(whitehorseButton);
		add(vancouverButton);
		add(iqaluitButton);
		add(charlottetownButton);
		add(markhamButton);
		add(whitehorseButtonLabel);
		add(vancouverButtonLabel);
		add(iqaluitButtonLabel);
		add(charlottetownButtonLabel);
		add(markhamButtonLabel);
		add(back);

		add(title);

	}

	/**
	 * draw all the component and background
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); //calls the super method to draw the original stuff
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to be a 2D graphics
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image 
		g2d.setColor(Color.black); //set the color to be black
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparency of the next part drawn
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a dark layout on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //reset the transparency back to Opaque
		g2d.drawImage(AssetManager.hoverMap_Map, Window.WIDTH/2-500, 0, this); //draw the hoverMap image on top of everything
	}

	/**
	 * make a pin button's label, it is made into a method because there are 5 cities, and the buttons are almost the same
	 * @param text the text of the label
	 * @param color the font color of the label
	 * @param x the x position of the label
	 * @param y the y position of the label
	 * @param width the width of the label
	 * @param height the height of the label
	 * @return the label used to indicate what is the pin button pointed to
	 */
	private JLabel makePinButtonLabel(String text,Color color, int x, int y, int width, int height){
		JLabel pinButtonLabel = new JLabel(text); //initialize the pinButtonLabel with its text
		pinButtonLabel.setBounds(x, y, width, height); //set the bounds
		pinButtonLabel.setOpaque(false); //set it to transparent
		pinButtonLabel.setFont(AssetManager.fonts[5]); //set the font
		pinButtonLabel.setForeground(color); //set the font color
		pinButtonLabel.setVisible(false); //set it so that it is not visible initially
		return pinButtonLabel; //return the label
	}

	/**
	 * make a pin button, it is made into a method because there are 5 cities, and the buttons are almost the same
	 * @param icon the icon and color of the pin
	 * @param associatedLabel the associated label with that button
	 * @param x the x position of the button
	 * @param y the y position of the button
	 * @param width the width of the button
	 * @param height the height of the button
	 * @param changeTo the PanelID that the main frame will changed to when clicked
	 * @return return the styled button
	 */
	private JButton makePinButton(ImageIcon icon, JLabel associatedLabel, int x, int y, int width, int height, PanelID changeTo){
		JButton pinButton = new JButton(); //initialize the JButton
		pinButton.setIcon(icon); //set the icon of the button
		pinButton.setBounds(x, y, width, height); //set the bounds
		pinButton.setOpaque(false);//set it to be transparent
		pinButton.setContentAreaFilled(false); //remove all the background painted
		pinButton.setBorderPainted(false); //remove the border of the button
		pinButton.addActionListener(e->window.changeComp(changeTo)); //add the actual functionality of the button to be able to change the frame to another panel
		pinButton.addMouseListener(new PinButtonHoverController(associatedLabel)); //add the hover interface to the button, so that the label will show when button is hovered
		return pinButton; //return the button

	}

}
