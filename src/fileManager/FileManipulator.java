package fileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * the class to manipulate the data and store them in java data stuctures
 * @author Leo
 */
public class FileManipulator {


	//public static instances that will allow other classes to directly access the data easily
	//therefore the data is only manipulated once to its appropriate form
	public static ArrayList<String> date; //the arrayList with all the date

	//all the hashMap that will store the data for different cities, and date for each city will be a treeMap that uses the date as the key and the variable as the the value
	public static HashMap<String,TreeMap<String, Float>> maxTemp;
	public static HashMap<String,TreeMap<String, Float>> minTemp;
	public static HashMap<String,TreeMap<String, Float>> meanTemp;

	public static HashMap<String,TreeMap<String, Float>> heatDays;
	public static HashMap<String,TreeMap<String, Float>> coolDays;

	public static HashMap<String,TreeMap<String, Float>> totalRain;
	public static HashMap<String,TreeMap<String, Float>> totalSnow;

	public static HashMap<String,TreeMap<String, Float>> totalPrecip;
	public static HashMap<String,TreeMap<String, Float>> snowOnGround;

	public static HashMap<String,TreeMap<String, Float>> direcGust;
	public static HashMap<String,TreeMap<String, Float>> speedGust;

	//an accessor that have all the hashMap in an arrayList so that certain setting can be applied in a for loop
	public static ArrayList<HashMap<String,TreeMap<String, Float>>> accessor;

	//the array that will store all the cities
	public static String[] cities = {"Charlottetown","Iqaluit","Markham",
			"Vancouver","Whitehorse"};


	//all the private final integer of which index is for which data type
	private final int INDEX_DATE = 0;
	private final int INDEX_YEAR = 1;
	private final int INDEX_MONTH = 2;
	private final int INDEX_DAY = 3;
	private final int INDEX_MAX_TEMP = 4;
	private final int INDEX_MIN_TEMP = 5;
	private final int INDEX_MEAN_TEMP = 6;
	private final int INDEX_HEAT_DEG_DAYS = 7;
	private final int INDEX_COOL_DEG_DAYS = 8;
	private final int INDEX_TOTAL_RAIN = 9; //usually Empty
	private final int INDEX_TOTAL_SNOW = 10; //usually Empty
	private final int INDEX_TOTAL_PRECIP = 11;
	private final int INDEX_SNOW_ON_GRND = 12;
	private final int INDEX_DIR_OF_MAX_GUST = 13;
	private final int INDEX_SPD_OF_MAX_GUST = 14;

	/**
	 * the constructor
	 */
	public FileManipulator(){

		//initialized all the data structures
		date = new ArrayList<String>();

		maxTemp = new HashMap<String,TreeMap<String, Float>>();
		minTemp = new HashMap<String,TreeMap<String, Float>>();
		meanTemp= new HashMap<String,TreeMap<String, Float>>();

		heatDays= new HashMap<String,TreeMap<String, Float>>();
		coolDays= new HashMap<String,TreeMap<String, Float>>();

		totalRain = new HashMap<String,TreeMap<String, Float>>();
		totalSnow = new HashMap<String,TreeMap<String, Float>>();

		totalPrecip= new HashMap<String,TreeMap<String, Float>>();
		snowOnGround= new HashMap<String,TreeMap<String, Float>>();

		direcGust= new HashMap<String,TreeMap<String, Float>>();
		speedGust= new HashMap<String,TreeMap<String, Float>>();

		//put all the hashMaps inside an arrayList so their certain setting can be applied together in a for loop
		accessor = new ArrayList<HashMap<String,TreeMap<String, Float>>>();
		accessor.add(maxTemp);
		accessor.add(minTemp);
		accessor.add(meanTemp);
		accessor.add(heatDays);
		accessor.add(coolDays);
		accessor.add(totalRain);
		accessor.add(totalSnow);
		accessor.add(totalPrecip);
		accessor.add(snowOnGround);
		accessor.add(direcGust);
		accessor.add(speedGust);

	}

	/**
	 * start collecting the data
	 * @param downloader the file downloader that has the year range of all cities
	 */
	public void startCollecting(FileDownloader downloader){

		//clear all the data structures
		date.clear();

		maxTemp.clear();
		minTemp.clear();
		meanTemp.clear();

		heatDays.clear();
		coolDays.clear();

		totalRain.clear();
		totalSnow.clear();

		totalPrecip.clear();
		snowOnGround.clear();

		direcGust.clear();
		speedGust.clear();

		try {
			//for each information inside the accessor
			for(HashMap<String,TreeMap<String, Float>> eachMap : accessor){
				for(String cityName : cities){ //for every city name
					eachMap.put(cityName,new TreeMap<String, Float>()); //initialize all the treeMaps
				}
			}

			//for all the data that is downloaded
			for(CityDailyWeather citiesYearRange: downloader.getDownloadData()){
				String cityName = citiesYearRange.getCity(); //get the cities name
				//for all the year inside of the data range
				for(int year = citiesYearRange.getStartYear(); year<=citiesYearRange.getEndYear();year++){
					//read the file inside a buffer reader
					BufferedReader br = new BufferedReader(new FileReader("Data//"+cityName+"//"+cityName+"-"+Integer.toString(year)+".txt"));

					// skip the lines that and start from the lines that include the actual data 
					while(!br.readLine().startsWith("\"Date/Time\"")){
						continue;
					}

					//for loop that will not break until the end of the data
					for(;;){
						String currentLine = br.readLine(); //read the next line
						if(currentLine==null){ //if there is no next line
							break; //break out of the for loop
						}
						//manipulate the string so that is only have the required information
						currentLine = currentLine.replaceAll("[^0-9,.-]", ""); //keep only the numbers comma, and some other symbol
						currentLine = currentLine.replaceAll(",,", ","); //replace double comma with comma between some data have a space for flag
						currentLine = currentLine.replaceAll(",,", ",/,"); //set the empty space to have a dash to indicate empty data
						currentLine = currentLine.replaceAll(",,", ",/,"); //do it again because there might be 3 comma together


						String[] tokens = currentLine.split(","); //split the string based on comma so that data is stored correctly

						//System.out.println(tokens[INDEX_DATE]);

						//if the index is not a slash which means it is not empty then add the data based on the selected index
						//do it for all the data for all the indexes using the private index integers listed before
						if(!tokens[INDEX_DATE].equals("/")){
							date.add(tokens[INDEX_DATE]);
						}

						if(!tokens[INDEX_MAX_TEMP].equals("/")){
							maxTemp.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_MAX_TEMP]));
						}

						if(!tokens[INDEX_MIN_TEMP].equals("/")){
							minTemp.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_MIN_TEMP]));
						}

						if(!tokens[INDEX_MEAN_TEMP].equals("/")){
							meanTemp.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_MEAN_TEMP]));
						}

						if(!tokens[INDEX_HEAT_DEG_DAYS].equals("/")){
							heatDays.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_HEAT_DEG_DAYS]));
						}

						if(!tokens[INDEX_COOL_DEG_DAYS].equals("/")){
							coolDays.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_COOL_DEG_DAYS]));
						}

						if(!tokens[INDEX_TOTAL_RAIN].equals("/")){
							totalRain.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_TOTAL_RAIN]));
						}

						if(!tokens[INDEX_TOTAL_SNOW].equals("/")){
							totalSnow.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_TOTAL_SNOW]));
						}

						if(!tokens[INDEX_TOTAL_PRECIP].equals("/")){
							totalPrecip.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_TOTAL_PRECIP]));
						}

						if(!tokens[INDEX_SNOW_ON_GRND].equals("/")){
							snowOnGround.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_SNOW_ON_GRND]));
						}

						if(!tokens[INDEX_DIR_OF_MAX_GUST].equals("/")){
							direcGust.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_DIR_OF_MAX_GUST]));
						}

						if(!tokens[INDEX_SPD_OF_MAX_GUST].equals("/")){
							speedGust.get(cityName).put(tokens[INDEX_DATE],Float.parseFloat(tokens[INDEX_SPD_OF_MAX_GUST]));
						}
					}
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//remove the repeated data using hashSet
		HashSet<String> removeRepeatedDate = new HashSet<>(date); // transfer the data from the arrayList to the hashSet
		date = new ArrayList<>(removeRepeatedDate); //transfer the data back to the arrayList
		Collections.sort(date); //sort the date arrayList

	}
}
