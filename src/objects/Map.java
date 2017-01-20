package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import application_Windows.Window;
import fileManager.AssetManager;
import fileManager.FileManipulator;
import methods.Method;

/**
 *The map object that will render the map based on different heat
 * @author Leo
 *
 */
public class Map extends EmptyObject{


	//private instance variables
	private ArrayList<City> cities; //the arrayList that holds all the city needed
	private HashMap<String, Float> tempCoord; //the hashMap that holds all the temperature

	private String year = "2005"; //initialize the year to be 2005
	private String month = "00"; //initialize the month to be empty
	private String day = "00"; //initialize the day to be empty

	private String date; //set the date

	private TreeMap<String, Float> minTemp1 = FileManipulator.minTemp.get("Charlottetown"); //get the minimum temperature treeMap of the Charlottetown
	private TreeMap<String, Float> minTemp2 = FileManipulator.minTemp.get("Iqaluit"); //get the minimum temperature treeMap of the Iqaluit
	private TreeMap<String, Float> minTemp3 = FileManipulator.minTemp.get("Markham"); //get the minimum temperature treeMap of the Markham
	private TreeMap<String, Float> minTemp4 = FileManipulator.minTemp.get("Vancouver");  //get the minimum temperature treeMap of the Vancouver
	private TreeMap<String, Float> minTemp5 = FileManipulator.minTemp.get("Whitehorse"); //get the minimum temperature treeMap of the Whitehorse

	/**
	 * Constructor method
	 * @param x the x position
	 * @param y the y position
	 * @param id the object Id
	 */
	public Map(float x, float y, ID id){
		super(x,y,id); //override the super component with x and y position as well as the id

		cities = new ArrayList<>(); //initialize the arrayList for the cities
		tempCoord = new HashMap<>(); //initialize the hashMap for the temperature

		refreshCities(); //refresh the cities
		refreshColor(); //refresh the color
	}

	/**
	 * method get all the cities
	 */
	public void refreshCities(){
		cities.clear(); // clear all cities

		date = year+"-"+month+"-"+day; //get the target date
		date = date.replaceAll("-00", ""); //manipulate the target date
		//get the average data for all 5 cities
		cities.add(new City("Charlottetown",681,570,Method.findAvg(minTemp1, date)));
		cities.add(new City("Iqaluit",514,341,Method.findAvg(minTemp2,date)));
		cities.add(new City("Markham",543,665,Method.findAvg(minTemp3,date)));
		cities.add(new City("Vancouver",91,607,Method.findAvg(minTemp4,date)));
		cities.add(new City("Whitehorse",38,416,Method.findAvg(minTemp5,date)));

		checkCities(); //check if cities are all relevant
	}

	/**method that check if cities are all relevant
	 * 
	 */
	private void checkCities(){
		for(int i=0; i<cities.size();i++){ // for all the date inside of the cities
			if(cities.get(i).getTemp().isNaN()){ //try get the value and see if it is not a number
				cities.remove(i); //remove that city if is not good
				i--; //go back once
			}
		}
	}

	/**
	 * method that recalculate the repaint the map
	 */
	public void refreshColor(){
		tempCoord.clear(); //clear all the coordinates
		// get the total number of cities
		int factor = cities.size();
		//for every city inside of the cities arrayList
		for(City everyCity: cities){
			for(int yP = 0; yP< AssetManager.canadaMap.getHeight();yP++){ //for all the y positions
				for(int xP = 0; xP<AssetManager.canadaMap.getWidth(); xP++){ //for all the x positions 
					if(Math.hypot(xP-everyCity.getX(), yP-everyCity.getY())<20){ //calculate the distance between the target position every single pixel
						tempCoord.put(xP+","+yP,100000f); //if the distance is really short then set the temperature to be 100000f, so that it draws a red dot
					}else{ //if the distance is normal
						double distance = yP-everyCity.getY() +   50*Math.sin(0.009*(xP-everyCity.getX())); //draw the data based on a vertically compressed sin wave
						if(tempCoord.containsKey(xP+","+yP)){ //if the temperature hashMap already contains the key value
							//find the average temperature of the target pixel based on the current value given and the previous value stored
							tempCoord.put(xP+","+yP, (float) (((everyCity.getTemp()+  distance/10*(Math.random()+0.2))/factor) +(tempCoord.get(xP+","+yP))));
						}else{ //if the average temperature of the tar get pixel is new
							//initialize the target key position to the given value
							tempCoord.put(xP+","+yP,(float) ((everyCity.getTemp() + distance/10*(Math.random()+0.2))/factor));
						}
					}
				}
			}
		}

		//color the map
		for(int yP = 0; yP< AssetManager.canadaMap.getHeight();yP++){ // for every single y position
			for(int xP = 0; xP<AssetManager.canadaMap.getWidth(); xP++){// for every single x position
				int p = AssetManager.canadaMap.getRGB(xP, yP); //get the color in form of an integer
				if (p!=0){ //if the color is not a transparent
					float temp; //try to get the target pixel's temperature
					if(tempCoord.containsKey(xP+","+yP)) 
						temp = tempCoord.get(xP+","+yP); //get the value
					else //if couldn't find the value
						temp = 0; //set the temperature to 0

					//based on the range of the temperature give at that pixel, get the color needed for that pixel
					if(temp>=-1000&&temp<-40)
						p = Method.getIntFromColor(0,0,75);
					else if(temp>=-40&&temp<-30)
						p = Method.getIntFromColor(0,0,105);
					else if(temp>=-30&&temp<-20)
						p = Method.getIntFromColor(0,0,155);
					else if(temp>=-20&&temp<-10)
						p = Method.getIntFromColor(0,0,255);
					else if(temp>=-10&&temp<0)
						p = Method.getIntFromColor(0,105,255);
					else if(temp>=0&&temp<10)
						p = Method.getIntFromColor(0,155,205);
					else if(temp>=10&&temp<20)
						p = Method.getIntFromColor(0,205,155);
					else if(temp>=20&&temp<=1000)
						p = Method.getIntFromColor(0,255,105);
					else if(temp>1000)
						p = Method.getIntFromColor(255,0,0);

				}
				//set the rgb of that pixel to the target calculated int color variable
				AssetManager.canadaMap.setRGB(xP, yP, p);
			}
		}
	}


	/**tick method that have to be overridden
	 * 
	 */
	@Override
	public void tick() {

	}

	/**
	 * render method that will render the map
	 */
	@Override
	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g; //cast the graphics to a 2D graphics
		g2d.setColor(Color.white); //set the color to white
		g2d.drawImage(AssetManager.canadaMap, (int)x, (int)y, null); //draw the map of Canada
		g2d.setColor(Color.white); //set the color to white
		g2d.setFont(AssetManager.fonts[1]); //set the font
		g2d.drawString("For: "+date,1000, 50); //draw the string that will indicate for what year is the year drawn for
		g2d.drawString("Cities", 1000, 100); //draw the string that will indicate the cities
		g2d.drawString("Mean Temperature", 1150, 100); //draw the string that will indicate where is the mean temperature

		int yCoord = 140; //select the target y coordinates of the map
		for(City eachCity: this.getCities()){ //for all the cities inside the cities arrayList
			g2d.drawString(eachCity.getName(), 1000, yCoord); //draws the name of the city
			g2d.drawString(Float.toString(eachCity.getTemp()), 1150, yCoord); //draws the temperature of the city
			yCoord+=40; //increment the y position so that is goes down
		}
	}

	//getters and setters
	/**
	 * getter for the cities
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**setter for the cities name
	 * 
	 * @param cities the cities name
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	/**getter get the year
	 * 
	 * @return the year
	 */
	public int getYear() {
		return Integer.parseInt(year);
	}

	/**setter for the year
	 * 
	 * @param year the year
	 */
	public void setYear(int year) {
		this.year = String.format("%04d", year);
	}

	/**
	 * the getter for the month
	 * @return the month
	 */
	public int getMonth() {
		return Integer.parseInt(month);
	}

	/**
	 * tbe setter for the month
	 * @param month the month
	 */
	public void setMonth(int month) {
		this.month = String.format("%02d", month);
	}

	/**
	 * the getter for the days
	 * @return the day
	 */
	public int getDay() {
		return Integer.parseInt(day);
	}

	/**
	 * the setter for the day
	 * @param day the day
	 */
	public void setDay(int day) {
		this.day = String.format("%02d", day);
	}

}
