package object_Controller;

import java.awt.Graphics;
import java.util.ArrayList;

import objects.EmptyObject;

/**
 * The handler that will tick and render the objects
 * @author Leo
 *
 */
public class MassHandler {

	//private arrayList that will store the objects that need to be ticked and drawn onto the panel
	private ArrayList<EmptyObject> objects = new ArrayList<>();
	/**
	 *  tick every single application objects
	 */
	public void tick(){
		for(int i = 0; i < objects.size(); i++){ //for every object 
			EmptyObject tempObject = objects.get(i); //get the object
			tempObject.tick(); //tick the object within its own method
		}
	}
	/**
	 * render every single graphics 
	 * @param g the graphics that will be used to draw the stuff
	 */
	public void render(Graphics g){ 
		for(int i = 0; i < objects.size(); i++){ //for every object
			EmptyObject tempObject = objects.get(i); //get the object
			tempObject.render(g); //render the object within its own method
		}
	}


	/**
	 * method that will add a object to the arrayList
	 * @param object the object wanted to be added
	 */
	public void addObject(EmptyObject object){ 
		this.objects.add(object);
	}


	/**
	 * method that will add an arrayList of objects to the master object arrayList
	 * @param objects the arrayList of objects wanted to be added
	 */
	public void addObject(ArrayList<EmptyObject> objects){
		this.objects.addAll(objects);
	}

	/**
	 * method that will remove a object from the arrayList
	 * @param object the object wanted to be removed
	 */
	public void removeObject(EmptyObject object){
		this.objects.remove(object);
	}

	/**
	 * method that will remove an arrayList of objects from the arrayList
	 * @param objects the arrayList of objects needed to be removed
	 */
	public void removeObject(ArrayList<EmptyObject> objects){
		this.objects.removeAll(objects);
	}

	/**
	 * method that will clear all objects
	 */
	public void clearObject(){
		this.objects.clear();
	}

}





