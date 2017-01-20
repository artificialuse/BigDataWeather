package objects;

/**
 * The city that contains the information for the thing to be drawn on a treeMap
 * @author Leo
 *
 */
public class City{

	//private instances of a City
	private String name; //the name of the city
	private int x;//the x position of the city to be drawn at
	private int y; //the y position of the city to be drawn at
	private Float temp; //the float variable that will hold the temperature

	/**
	 * Constructor method
	 * @param name the city's name
	 * @param x the city's x position
	 * @param y the city's y position
	 * @param temp the temperature at that position
	 */
	public City(String name, int x, int y, Float temp) {
		//initialized the variable of a city that will be passed in in the constructor
		this.name = name; 
		this.x = x;
		this.y = y;
		this.temp = temp;
	}

	//getter and setter
	/** getter for the name of the city
	 * @return the name of the city
	 */
	public String getName() {
		return name;
	}
	/**
	 * setter for the name of the city
	 * @param name city's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getter for the x position
	 * @return x position
	 */
	public int getX() {
		return x;
	}
	/**
	 * setter for the x position
	 * @param x x position
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * getter for the y position
	 * @return y position
	 */
	public int getY() {
		return y;
	}
	/**setter for the y position
	 * 
	 * @param y y position
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * getter for the temperature of that position
	 * @return temperature of that position
	 */
	public Float getTemp() {
		return temp;
	}
	/**
	 * setter for the temperature of that position
	 * @param temp temperature of that position
	 */
	public void setTemp(Float temp) {
		this.temp = temp;
	}

}
