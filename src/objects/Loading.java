package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import application_Windows.Window;
import fileManager.AssetManager;
import methods.Method;

/**
 * the loading object that can be rendered with the render and tick method
 * @author Leo
 */
public class Loading extends EmptyObject{

	//private instances of the loading object that can be rendered by calling tick and render
	private Color green = new Color(0,255,153); //the green color of the GIF image
	private String text; //the loading text that will be drawn
	private int timer; //the timer that will control when the program will tick
	
	/**
	 * @param x the x position of the object
	 * @param y the y position of the object
	 * @param id the id of the object
	 */
	public Loading(float x, float y, ID id) {
		super(x, y, id); // super component that override the x, y position and the id from the EmptyObject class
		this.text = "Loading"; //set the initial loading text to loading
		this.timer = 0; //initialize the timer to be 0
	}

	/**a tick method that will be ticked in the handler
	 * 
	 */
	@Override
	public void tick() {
		timer++; //increment the timer
		if(timer%7==0){ //slow down the rate
			text += "."; //add a dot to the text
			if(text.length()>=11) //when there is going to be 4 dots
				text = "Loading"; //remove the dots
		}
	}

	/**
	 * draw the stuff
	 */
	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(Method.makeTransparent(0.8f)); //make the transparency to 0.8
		g2d.setColor(Color.black); //set the color to black
		//draw the rectangle to cover the top sides and the bottom
		g2d.fillRect(0, 0, Window.WIDTH, (int) y);
		g2d.fillRect(0, (int)y, (int)x, Window.HEIGHT-(int)y);
		g2d.fillRect((int)x+600, (int)y, Window.WIDTH-(int)x-600, Window.HEIGHT-(int)y);
		g2d.fillRect((int)x, (int)y+600, 600, Window.HEIGHT-(int)y-600);
		//draw the loading GIF
		g2d.drawImage(AssetManager.loading, (int)x, (int)y, null);
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency back to opaque
		g2d.setColor(green);//set the color to green 
		g2d.setFont(AssetManager.fonts[3]); //set the font
		g2d.drawString(text, x+120, y+500); //draw the string to the center under the loading image

	}


}
