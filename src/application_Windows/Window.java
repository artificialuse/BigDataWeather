package application_Windows;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fileManager.AssetManager;
import fileManager.CitiesInformation;
import fileManager.FileDownloader;
import fileManager.FileManipulator;
import input_Control.FrameMouseDragController;
import object_Controller.BackgroundControl;
import objects.ID;
import objects.Loading;


/**
 * the class that will hold the main frame of the application
 * @author Leo
 *
 */
public class Window{

	//public final Variable that indicate the WIDTH and HEIGHT of the window
	public final static int WIDTH = 1280;
	public final static int HEIGHT =  WIDTH * 9 / 16;

	//initialize the private instance variables
	private AssetManager assetManager; //give a reference to the assetManager so that other method in the class can use it
	private FileDownloader fileDownloader; //give a reference to the fileDownloader so that other method in the class can use it
	private FileManipulator fileManipulator; //give a reference to the fileManipulator so that other method in the class can use it

	private Component comp; //current component that will show on the frame
	private JFrame frame; //the frame (the window)

	private HashMap<PanelID, Component> panels; //the hashMap that will allow the window the switch between scenes

	private JPanel progessPanel; //the progress panel that will hold the loading scene

	private Thread t; // the thread that is used to keep the loading screen running when gather all data

	private int sleepValue = 50; //the value that will control how fast the thread is going

	private boolean loading; //the boolean that will control whether the thread should be still running or not

	/**initialize all panels and gather all informations
	 * 
	 */
	private void init(){
		assetManager.init(); //gather all the images and data needed

		fileManipulator.startCollecting(fileDownloader); //start to manipulated the date into different java data structures

		panels = new HashMap<>(); //initialize the hashMap that will be used to assign each JPanel with a panel ID
		BackgroundControl bgControl = new BackgroundControl(this, assetManager); //the background control of the program

		Cus_HeatMap heatMap = new Cus_HeatMap(this); //initialize the heatMap
		Cus_Menu menu = new Cus_Menu(this,bgControl,fileDownloader); //initialize the main menu
		Cus_LineGraph lineGraph = new Cus_LineGraph(this, fileDownloader); //initialize the line graph
		Cus_Table table = new Cus_Table(this); //initialize the table
		Cus_PieChartScene pieChart = new Cus_PieChartScene(this); //initialize the pie chart
		Cus_HoverMapScene hoverMap = new Cus_HoverMapScene(this); //initialize the hover map (cities information map)

		//initialize all the city's information panel that will display an array of images and the description of the city
		Cus_CitiesInformationScene charlottetownPage = new Cus_CitiesInformationScene("Charlottetown",AssetManager.charlottetownImageFlag, CitiesInformation.charlottownInfo, this);
		Cus_CitiesInformationScene vancouverPage = new Cus_CitiesInformationScene("Vancouver",AssetManager.vancouverImageFlag, CitiesInformation.vancouverInfo, this);
		Cus_CitiesInformationScene whitehorsePage = new Cus_CitiesInformationScene("Whitehorse",AssetManager.whitehorseImageFlag, CitiesInformation. whitehorseInfo, this);
		Cus_CitiesInformationScene iqaluitPage = new Cus_CitiesInformationScene("Iqaluit",AssetManager.iqaluitImageFlag, CitiesInformation.iqaluitInfo, this);
		Cus_CitiesInformationScene markhamPage = new Cus_CitiesInformationScene("Markham",AssetManager.markhamImageFlag, CitiesInformation.markhamInfo, this);

		Cus_FileDownloading loadingScene = new Cus_FileDownloading(this, fileDownloader, fileManipulator); //the file downloading screen that will show when you decide to update the data

		Cus_Report report = new Cus_Report(this); //initialize the forecast report
		//assign each panel with a panelId so that it can be switched easily after
		panels.put(PanelID.HeatMap, heatMap);
		panels.put(PanelID.Menu, menu);
		panels.put(PanelID.LineGraph, lineGraph);
		panels.put(PanelID.Table, table);
		panels.put(PanelID.PieChart, pieChart);
		panels.put(PanelID.HoverMap, hoverMap);
		panels.put(PanelID.CharlottetownPage, charlottetownPage);
		panels.put(PanelID.VancouverPage, vancouverPage);
		panels.put(PanelID.WhitehorsePage, whitehorsePage);
		panels.put(PanelID.IqaluitPage, iqaluitPage);
		panels.put(PanelID.MarkhamPage, markhamPage);
		panels.put(PanelID.FileDownloading, loadingScene);
		panels.put(PanelID.Report,report);

		//set all the component to start from 0,0, and have the width and height to be screen size
		for(Component everyPanel: panels.values()){ 
			everyPanel.setBounds(0,0,WIDTH,HEIGHT); //set the bound of every single panel
		}
		this.comp = panels.get(PanelID.Menu); //default component that will show initially

		frame.remove(progessPanel); //remove the loading panel

		changeComp(PanelID.Menu); //change the component current showing to the current PanelID
		loading = false; //set loading to false, so that it stop the thread automatically
	}

	/**Constructor method
	 * 
	 * @param title the title of the frame
	 * @param assetManager the assetManager that initialize all images and some other data
	 * @param fileDownloader the file downloader that will download the data
	 * @param fileManipulator the file manipulate that will manipulate the downloaded data
	 */
	public Window(String title, AssetManager assetManager, FileDownloader fileDownloader, FileManipulator fileManipulator){
		this.assetManager = assetManager; //add a reference to the assetManager 
		this.fileDownloader = fileDownloader; //add a reference to the fileDownloader
		this.fileManipulator = fileManipulator; //add a reference to the fileManipulator
		assetManager.initLoading(); //get the loading image
		loading = true; //prepare to start loading

		frame = new JFrame(title); //initialize the frame with the title needed

		//set the frame width and height
		frame.setPreferredSize(new Dimension(WIDTH,HEIGHT)); //set its preferred size to the width and the height
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT)); //set its maximum size to the width and the height
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT)); //set its minimum size to the width and the height

		frame.setUndecorated(true); //remove the originally design of the frame
		frame.setLayout(null); //set the layout to be null
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when closed, exit the frame instead of hiding it
		frame.setResizable(false); //set the frame to be not re-sizable
		frame.setLocationRelativeTo(null); //set the position of the frame to the center of the computer screen
		FrameMouseDragController dragControl = new FrameMouseDragController(frame); //initialize the drag control that will be added to the frame
		frame.addMouseListener(dragControl); //add the dragControl to the frame as a mouse listener
		frame.addMouseMotionListener(dragControl); //add the dragControl to the frame as a mouse motion listener as well

		Loading loadingAnimation = new Loading(350,0,ID.Loading); //initialize the loading animation that will be rendered in the progress panel
		progessPanel = new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				super.paintComponent(g); //call the super component that will draw the other stuffs
				loadingAnimation.tick(); ///tick the loadingAnimation
				loadingAnimation.render(g); //render the loadingAnimation
			}
		};
		frame.add(progessPanel); //add the progressPanel to the frame
		progessPanel.setBounds(0,0,WIDTH,HEIGHT); //set the bounds
		frame.setVisible(true); //set the frame to be visible

		t = new Thread() { //initialize the thread that will repaint the loading animation
			public void run() {
				while(loading){ //while is it still loading
					progessPanel.repaint(); //repaint the animation so that it is animated
					//System.out.print("running");
					try {
						sleep(sleepValue); //sleep for a delay
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};
		t.setDaemon(true); //set the thread t to be collected differently
		t.start(); //start the thread

		init(); //initialize all the panels and other stuff, it will eventually set the loading to false when finished
	}

	/**a public method that will allow the window to be changed in other panels
	 * 
	 * @param key the PanelID to indicate which panel to be changed to
	 */
	public void changeComp(PanelID key){
		if(comp instanceof Cus_HeatMap){ //if the panel before is the heatMap
			((Cus_HeatMap) comp).stop(); //stop the thread that is ran in the thread in the heatMap
		}
		frame.remove(this.comp); //remove the current component
		this.comp = panels.get(key); //change the current displaying component to be the key wanted
		frame.add(comp); //add the component to the frame
		frame.repaint(); // repaint
		frame.revalidate(); //re-validate because a new frame is added

		//if the panel is changed into a heatMap start the thread
		if(comp instanceof Cus_HeatMap){
			((Cus_HeatMap) comp).start(); //heatMap has a thread that will update the map
		}		
	}
	
	/**it will repaint the entire frame when called
	 * 
	 */
	public void repaint(){ 
		if(frame!=null) //is there is a frame
			frame.repaint(); //repaint it
	}
}

