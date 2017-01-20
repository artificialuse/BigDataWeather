package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JTextArea;

/*
 * the class to download the files  
 * @author Leo
 *
 */
public class FileDownloader {

	//the array that will be used to store the cities' informations that are needed to be downloaded
	private CityDailyWeather[] downloadData;
	public String uml;


	/**Constructor method
	 * 
	 */
	public FileDownloader(){

		//initialize the array to have a length of 5
		downloadData = new CityDailyWeather[5]; 
		downloadData[0] = new CityDailyWeather("Charlottetown", 1991,2002,6928); //put Charlottetown into the array with its information
		downloadData[1] = new CityDailyWeather("Iqaluit", 2004,2016,42503);  //put Iqaluit into the array with its information
		downloadData[2] = new CityDailyWeather("Markham", 1986,2015,4841);  //put Markham into the array with its information
		downloadData[3] = new CityDailyWeather("Vancouver", 1925,2016,888);   //put Vancouver into the array with its information
		downloadData[4] = new CityDailyWeather("Whitehorse", 1959,2012,1618);  //put Whitehorse into the array with its information

		Scanner input = null;
		try {
			input = new Scanner(new File("res//website.txt"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		uml  = input.nextLine();
		
	}
	
	public void changeUML(String uml){
		File myFoo = new File("res//website.txt");
		FileWriter fooWriter;
		try {
			fooWriter = new FileWriter(myFoo, false);
			fooWriter.write(uml);
			fooWriter.close();
			this.uml = uml;
		} catch (IOException e) {
			
			e.printStackTrace();
		} // true to append                                         // false to overwrite.
		
	}


	/**
	 * download the data as well changing the textField
	 * @param textField the textField that will be updated when the data is downloading
	 */
	public void download(JTextArea textField){
		try{
			for(CityDailyWeather everyCity: downloadData){ // for every city that need to be downloaded
				for(int i = everyCity.getStartYear(); i<=everyCity.getEndYear(); i++){  //from the starting year to the ending year
					String place = "Data/"+everyCity.getCity()+"/"+everyCity.getCity()+"-"+Integer.toString(i)+".txt"; //get the position to be stored in
					URL website_Iqaluit = new URL(uml+"format=csv&stationID="+ //get the corresponding UML with stationID
							Integer.toString(everyCity.getStationID())+"&Year="+Integer.toString(i)+"&Month=5&Day=1&timeframe=2&submit=Download+Data");
					ReadableByteChannel rbc_Iqaluit = Channels.newChannel(website_Iqaluit.openStream()); //read the data
					FileOutputStream fos_Iqaluit = new FileOutputStream(place); //store the data
					fos_Iqaluit.getChannel().transferFrom(rbc_Iqaluit, 0, Long.MAX_VALUE); //transfer the stored data to a text file
					textField.setText(textField.getText()+"\n"+String.format("Downloading data to: %s", place)); //set the text field to display the current data downloading
					textField.setSelectionStart(textField.getText().length()); //refresh the selected index so that scroll pane is scroll to the bottom
					textField.setSelectionEnd(textField.getText().length()); //refresh the selected index so that scroll pane is scroll to the bottom
				}
			}
			textField.setText(textField.getText()+"\n\n"+"Finished downloading all files"); //after finished all the files, display that it is finished
			textField.setSelectionStart(textField.getText().length()); //refresh the selected index so that scroll pane is scroll to the bottom
			textField.setSelectionEnd(textField.getText().length()); //refresh the selected index so that scroll pane is scroll to the bottom
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	/**download without showing any progress
	 * 
	 */
	public void download(){
		try{
			for(CityDailyWeather everyCity: downloadData){ // for every city that need to be downloaded
				for(int i = everyCity.getStartYear(); i<=everyCity.getEndYear(); i++){ //from the starting year to the ending year
					URL website_Iqaluit = new URL(uml+"format=csv&stationID="+ //get the corresponding UML with stationID
							Integer.toString(everyCity.getStationID())+"&Year="+Integer.toString(i)+"&Month=5&Day=1&timeframe=2&submit=Download+Data");
					
					System.out.println(website_Iqaluit);
					ReadableByteChannel rbc_Iqaluit = Channels.newChannel(website_Iqaluit.openStream());  //read the data
					FileOutputStream fos_Iqaluit = new FileOutputStream("Data/"+everyCity.getCity()+"/"+everyCity.getCity()+"-"+Integer.toString(i)+".txt"); //store the data
					fos_Iqaluit.getChannel().transferFrom(rbc_Iqaluit, 0, Long.MAX_VALUE); //transfer the stored data to a text file
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	/**
	 * a main class
	 * @param args it can be ran individually to download the data only
	 */
	public static void main(String[] args){
		new FileDownloader().download(); //the downloader class can also be called here so that the initial data is downloaded
	}

	/**
	 * a getter method that allow other class to access the city that need data to be downloaded for
	 * @return the array of the cities that need data to be downloaded
	 */
	public CityDailyWeather[] getDownloadData() {
		return downloadData;
	}

	/**
	 * the setter method that will allow other class to change the city that need data to be downloaded for
	 * @param downloadData set the array of the cities that need data to be downloaded
	 */
	public void setDownloadData(CityDailyWeather[] downloadData) {
		this.downloadData = downloadData;
	}




}
