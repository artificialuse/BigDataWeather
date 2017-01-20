package Cus_UIs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import fileManager.AssetManager;

/**
 * the customized UI design for the slider
 * @author Leo
 *
 */
public class Cus_SliderUI extends BasicSliderUI{

	/**
	 * initialized the slider to be referenced and get the position of the x,y, width, and height
	 * @param slider the slider that will be customized
	 */
	public Cus_SliderUI(JSlider slider) {
		super(slider); //parse the slider in the super constructor
	}


	/**
	 * return the thumb size of the thumb 
	 */
	@Override
	protected Dimension getThumbSize() {
		return new Dimension(12, 16); //return a dimension with 12*16
	}

	/**paint the track of the slider
	 * 
	 */
	@Override
	public void paintTrack(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;  //cast the graphics to a 2D graphics
		g2d.setStroke(AssetManager.strokes[5]); //set stroke
		g2d.setColor(Color.white); //set the color to white
		g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2, //draw the track of the slider with a line 
				trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
	}


	/**
	 * paint the thumb of the slider
	 */
	@Override
	public void paintThumb(Graphics g) {
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to a 2D graphics
		g2d.setColor(Color.white); //set the color to white
		g2d.fillOval(thumbRect.x-1,thumbRect.y,15,15); //draw the Oval as the thumb
	}


	/**
	 * paint the major tick for Horizontal slider
	 */
	@Override
	protected void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
		g.setColor(Color.white); //set the color to be white
		super.paintMajorTickForHorizSlider(g, tickBounds, x); //pass in the other input for painting the ticks
	}


}
