package main;

import application_Windows.Window;
import fileManager.AssetManager;
import fileManager.FileDownloader;
import fileManager.FileManipulator;
import object_Controller.BackgroundControl;

/**
 * The main method that will start the application
 * @author Leo
 *
 */
public class MainWeather{
	
	/**
	 * the main method that will start the application
	 * @param args the array of arguments
	 */
	public static void main(String[] args) {
		
		//initialize the assetManager, the fileDownloader, and the fileManipulator
		AssetManager assetManager = new AssetManager();
		FileDownloader fileDownloader = new FileDownloader();
		FileManipulator fileManipulator = new FileManipulator();
		//System.out.println(FileManipulator.meanTemp.get("Markham"));
		//create window that will initialize the all the data and starting the frame
		Window window = new Window("Weather",assetManager,fileDownloader,fileManipulator);
	
	}
	
}
