package input_Control;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import application_Windows.Cus_Menu;
import fileManager.AssetManager;


/**
 * the mouse adapter that change the color the font of a text of a button, when the mouse hover over the button
 * @author Leo
 *
 */
public class MenuButtonHoverController extends MouseAdapter{

	private JButton button; //a reference to the menu button that need a hover change
	/**
	 * Constructor method
	 * @param button the buttons that need the font changed when mouse hover over
	 */
	public MenuButtonHoverController(JButton button){
		this.button = button; //set private button variable to the parameter
	}

	/**
	 * when the mouse entered the button, change the font color and font
	 */
	@Override
	public void mouseEntered(MouseEvent e){
		button.setForeground(Color.green);//change the font color to green
		button.setFont(AssetManager.fonts[7]); //change the font to be bigger
	}

	/**when the mouse exited the button, change the font color and font
	 * 
	 */
	@Override
	public void mouseExited(MouseEvent e){
		button.setForeground(Color.white);//change the font color to white
		button.setFont(AssetManager.fonts[6]); //change the font to be smaller
	}

	/**
	 * when the mouse pressed in the box of the button, change the font color and font
	 */
	@Override
	public void mousePressed(MouseEvent e){
		button.setForeground(Color.cyan);//change the font color to green
		button.setFont(AssetManager.fonts[6]); //change the font to be smaller
	}

	/**when the mouse is released in the box of the button, change the font color and font
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		button.setForeground(Color.white);//change the font color to white
		button.setFont(AssetManager.fonts[6]); //change the font to be smaller
	}

}
