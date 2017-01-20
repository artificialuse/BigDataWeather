package application_Windows;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import fileManager.AssetManager;
import input_Control.MouseInput;
import methods.Method;
import object_Controller.BackgroundControl;
import object_Controller.MassHandler;
import objects.ID;
import objects.Loading;
import objects.Map;

/**
 * the actual heat map
 * @author Leo
 *
 */
// the Cus_HeatMap will be a JPanel, and it will as well be able to be put into a thread
public class Cus_HeatMap extends JPanel implements Runnable{

	//initialize private instances
	private Thread thread;
	private boolean running; //control if the headMap is still running or not
	private MassHandler handler; 	//MassController that will tick and render everything
	private Cus_HeatMap_Control control;	//The JPanel that included all the controls

	/** Constructor method
	 * 
	 * @param window a reference to the frame of the program
	 */
	public Cus_HeatMap(Window window){
		running = false; //initialize the running to be false
		//initialize the handler 
		handler = new MassHandler();

		Map canadaMap = new Map(Window.WIDTH/2-AssetManager.canadaMap.getWidth(null)/2,0,ID.Map); //create the map to be put into the handler
		Loading loading = new Loading(350,0,ID.Loading); //create the loading scene

		handler.addObject(canadaMap); //add the map to the handler, so that it will be ticked and rendered

		setLayout(null); //set the layout to be null
		setBackground(Color.black);//set the background to be black

		control = new Cus_HeatMap_Control(window,canadaMap,handler, loading); //create the JPanel with those parameters
		control.setBounds(0,0,250,Window.HEIGHT); //set the bound of the controls
		add(control); //add the control to the main heatMap

		//add the mouse input listener so that the text field in the controls change when the mouse clicked
		addMouseListener(new MouseInput(canadaMap,control.getRequestX(), control.getRequestY())); 

	}

	/**a start method that will be not interrupted
	 * 
	 */
	public synchronized void start(){
		thread = new Thread(this); //reinitialize the thread 
		thread.setDaemon(true); //set the thread to be collected when finished
		thread.start(); //start the thread
		running = true; // start running
	}
	/**
	 *a stop method that will be not interrupted
	 */
	public synchronized void stop(){
		running = false; //stop running
	}

	/**
	 * the part will be run when creating a thread of the class
	 */ 
	@Override
	public void run() {
		while(running){ //while the heatMap is running
			tick(); //call the tick method
			render(); //call the render method
			try {
				thread.sleep(50); //sleep for 50ms, so that there is like a frame rate
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * call the handler to tick
	 */
	private void tick(){
		handler.tick(); //tick all objects in the handler
	}

	/** call the render to render
	 * 
	 */
	private void render(){
		repaint(); //call the paintComponent
	}


	/**
	 * draw the stuffs including the background images and all objects inside the handler
	 */
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g; //cast graphics to be a 2D graphics
		super.paintComponent(g2d); //call the super component to draw the buttons and etc.
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image
		g2d.setColor(Color.black); //set the color to be black
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparency based on another value
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a black layer on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //reset the transparency to opaque
		handler.render(g2d); //render all the objects in the handler
	}
}




