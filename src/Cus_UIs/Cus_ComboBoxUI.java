package Cus_UIs;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;

import fileManager.AssetManager;

/**
 * the customized comboBox UI
 * @author Leo
 *
 */
public class Cus_ComboBoxUI extends BasicComboBoxUI{

	/**
	 * change the arrow of the comboBox to be a customized one
	 */
	@Override    
	protected JButton createArrowButton() { 
		JButton button = new JButton(AssetManager.dropDownArrow); //initialize button with the image
		button.setBackground(Color.white);	//set the background to be white
		return button; //return the button so that it will be added to the comboBox
	}


}
