package objects;

import java.awt.Graphics;

/**
 * Abstract class that will show the empty object on that be be handled by the handler
 * @author Leo
 *
 */
public abstract class EmptyObject {

	//protected variables because x and y position can usually be gotten by both ways
	protected float x, y; //the x and y position of the object
	protected ID id; //the id of the object

	/**
	 * the abstract class so that the map and the loading must have render and tick method and also treated as a object to the handler
	 * @param x the x position of the object
	 * @param y the y position of the object
	 * @param id the id of the object
	 */
	public EmptyObject(float x,float y, ID id){
		//x y position as well as the id is needed for the panel
		this.x = x; 
		this.y = y;
		this.id = id;
	}

	/**
	 *  the abstract tick method that will be overridden when creating the actual object
	 */
	public abstract void tick();
	/**
	 * the abstract render method that will be overridden when creating the actual object
	 * @param g the graphics
	 */
	public abstract void render(Graphics g);  

	//getter and setters

	/**getter for the x position
	 * 
	 * @return the x position of the object
	 */
	public float getX() {
		return x;
	}

	/**
	 * setter for the x position
	 * @param x the x position of the object
	 */ 
	public void setX(float x) {
		this.x = x;
	}
	/**
	 * getter for the y position
	 * @return the y position of the object
	 */
	public float getY() {
		return y;
	}
	/**
	 * setter for the y position
	 * @param y the y position of the object
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * getter for the object Id
	 * @return the Id of the object
	 */
	public ID getId() {
		return id;
	}
	/**
	 * setter for the object Id
	 * @param id the Id of the object
	 */
	public void setId(ID id) {
		this.id = id;
	}	

}
