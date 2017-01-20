package fileManager;

/**
 * the class that will store the cities that need data to be downloaded
 * @author Leo
 */
public class CityDailyWeather {


	//private instances
	private String city; //the name of the city
	private int startYear; //the year that the data of the city start to be collected
	private int endYear; //the year that the data of the city stop to be collected
	private int stationID; //the stationID for the data to be downloaded from that weather station


	/**the constructor that will allow the cities to be stored with its name, data year range, and the stationID
	 * 
	 * @param city the name of the city
	 * @param startYear the start year of the city's data range
	 * @param endYear the end year of the city's data range
	 * @param stationID the stationID to where the data should be downloaded
	 */
	public CityDailyWeather(String city, int startYear, int endYear, int stationID){
		this.city = city;
		this.startYear = startYear;
		this.endYear = endYear;
		this.stationID = stationID;
	}


	//getter and setter
	/**
	 * get the city name
	 * @return the name of the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * set the city name
	 * @param city the name of the city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * get the start year as integer
	 * @return the start year
	 */
	public int getStartYear() {
		return startYear;
	}

	/**
	 *  set the start year as integer
	 * @param startYear the start year
	 */
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	/**
	 * get the end year as integer
	 * @return the end year
	 */
	public int getEndYear() {
		return endYear;
	}

	/**
	 * set the end year as integer
	 * @param endYear the end year
	 */
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}

	/**
	 *  get the station ID
	 * @return the station ID
	 */
	public int getStationID() {
		return stationID;
	}

	/**
	 * set the stationID
	 * @param stationID the station ID
	 */
	public void setStationID(int stationID) {
		this.stationID = stationID;
	}


}
