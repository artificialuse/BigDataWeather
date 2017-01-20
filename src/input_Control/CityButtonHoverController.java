package input_Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fileManager.AssetManager;

/**
 * the hover function to the city buttons
 * @author Leo
 *
 */
public class CityButtonHoverController extends MouseAdapter{

	/**
	 * private instances that be a reference to other methods
	 */
	private JButton button; //buttons that will be hovered 
	private JLabel label; //the label that is associated with the button
	private ImageIcon[] buttonImage; //the image for the label to change to

	/**
	 * constructor method
	 * @param button the associated button
	 * @param label the associated label
	 * @param buttonImage the array of images that with be switched in the button
	 */
	public CityButtonHoverController(JButton button, JLabel label, ImageIcon[] buttonImage){
		this.button = button; //give a reference to the button
		this.label = label; //give a reference to the label
		this.buttonImage = buttonImage; //give a reference to the button Images
	}


	/**when mouse in entered change the looking of the label as well as the images displayed
	 * 
	 */
	@Override
	public void mouseEntered(MouseEvent e){ 
		button.setBorderPainted(true); //change the border to be not painted
		button.setIcon(buttonImage[1]); //set the icon to be green
		label.setIcon(AssetManager.cityIconArrow_green); //set the button to green as well
	}

	/**
	 * when mouse exited the Button, change the looking of the label as well as the images displayed
	 */
	@Override
	public void mouseExited(MouseEvent e){
		button.setBorderPainted(false); //change the border to be painted
		button.setIcon(buttonImage[0]); //set the icon to the target image
		label.setIcon(AssetManager.cityIconArrow_white); //set the icon of the label to be white
	}


}
