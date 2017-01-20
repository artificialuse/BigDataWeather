package Cus_UIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

import fileManager.AssetManager;
/**
 * The customized UI design for the scroll bar
 * @author Leo
 *
 */
public class Cus_ScrollBarUI extends BasicScrollBarUI{
	/**
	 * Override the paint method to paint the track of the scroll bar
	 */
	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to a 2D graphics
		g2d.setStroke(AssetManager.strokes[5]); //set the stroke
		g2d.setColor(Color.white);//set the color to white
		if(trackBounds.height<=300){ //if the track is horizontal
			g2d.drawLine(trackBounds.x, trackBounds.y + trackBounds.height / 2, //draw the horizontal track of the scroll bar
					trackBounds.x + trackBounds.width, trackBounds.y + trackBounds.height / 2); 
		}else{ //if the track is vertical
			g2d.drawLine(trackBounds.x + trackBounds.width / 2, trackBounds.y ,  //draw the vertical track on the scroll bar
					trackBounds.x + trackBounds.width/2, trackBounds.y + trackBounds.height);
		}
	}
	
	/**Override the paint method to paint the thumb of the scroll bar
	 * 
	 */
	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to a 2D graphics
		g2d.setColor(Color.white); //set the color to be white
		g2d.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height); //draw the rectangle that will be the thumb of the scroll bar
	}
	
	/**
	 * Override the paint method to paint the decrease button
	 */
	@Override
	protected JButton createDecreaseButton(int orientation) {
		return createZeroButton(); //remove the decrease button
	}
	
	/**Override the paint method to paint the increase button
	 * 
	 */
	@Override    
	protected JButton createIncreaseButton(int orientation) {
		return createZeroButton(); //remove the increase button
	}
	
	/**method that will create an empty button
	 * 
	 * @return the button that is just empty
	 */
	private JButton createZeroButton() {
		JButton button = new JButton(); //Initialize the button
		button.setPreferredSize(new Dimension(0, 0)); //set the preferred dimension to  0,0
		button.setMinimumSize(new Dimension(0, 0));  //set the maximum dimension to  0,0
		button.setMaximumSize(new Dimension(0, 0)); //set the minimum dimension to  0,0
		return button; //return the button
	}
}
