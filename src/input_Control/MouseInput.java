package input_Control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import objects.Map;


/**
 * the mouse input to be able to add a city to the heat map
 * @author Leo
 *
 */
public class MouseInput extends MouseAdapter{

	//the JTextField for the request x position and y position
	private JTextField requestX; //textField for x position
	private JTextField requestY; //textField for y position
	/**
	 * the constructor method
	 * @param canadaMap canada's map
	 * @param requestX the JTextField for the desired x position
	 * @param requestY the JTextField for the desired y position
	 */
	public MouseInput(Map canadaMap,JTextField requestX, JTextField requestY){
		this.requestX = requestX; //set a reference to the x position input field
		this.requestY = requestY; //set a reference to the y position input field
	}
	/**
	 * when the mouse is clicked
	 */
	@Override
	public void mouseClicked(MouseEvent e){
		requestX.setText(Integer.toString(e.getX()-257)); //set the x position reference to the window
		requestY.setText(Integer.toString(e.getY())); //set the y position reference too the window
	}
}
