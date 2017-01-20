package input_Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import fileManager.AssetManager;


/**
 * the pin button hover controller that set the label to be visible when mouse hover over
 * @author Leo
 *
 */
public class PinButtonHoverController extends MouseAdapter{

	//JLabel that is used to show the text of the pin Buttons when mouse hover over the button
	private JLabel label;

	/**
	 * the Constructor method
	 * @param label the label that will changed visibility when mouse enters or exits
	 */
	public PinButtonHoverController(JLabel label){
		this.label = label; //add a reference to the label based on the parameter
	}
	/**
	 * when the mouse is entered
	 */
	@Override
	public void mouseEntered(MouseEvent e){
		label.setVisible(true); //set the label with text to be visible
	}

	/**
	 * when the mouse exits from the button
	 */
	@Override
	public void mouseExited(MouseEvent e){
		label.setVisible(false); //set the label with text to be invisible
	}

	/**
	 * when mouse the released after clicked
	 */
	@Override
	public void mouseReleased(MouseEvent e){
		label.setVisible(false);//set the label with text to be invisible
	}

}
