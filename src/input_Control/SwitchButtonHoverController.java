package input_Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fileManager.AssetManager;


/**
 * the button hover function for the button to change the icon
 * @author Leo
 *
 */
public class SwitchButtonHoverController extends MouseAdapter{


	//private instances as a reference to the other objects
	private JButton button; //button that will be changed
	private ImageIcon[] buttonImage; //the images that it will change to

	/**
	 *  Constructor method
	 * @param button the button that will be changed on hover
	 * @param buttonImage the array of the images that will be changed
	 */
	public SwitchButtonHoverController(JButton button, ImageIcon[] buttonImage){
		this.button = button; //reference the button based on the parameter
		this.buttonImage = buttonImage; //reference the button images based on the parameter
	}

	/**
	 * when mouse enter the button
	 */
	@Override
	public void mouseEntered(MouseEvent e){
		button.setIcon(buttonImage[1]); //set the icon of the button to be a green picture 
	}

	/**when mouse exit from the button
	 * 
	 */
	@Override
	public void mouseExited(MouseEvent e){
		button.setIcon(buttonImage[0]); //set the icon of the button to be a grey picture
	}


}
