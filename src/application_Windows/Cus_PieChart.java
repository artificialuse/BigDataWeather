package application_Windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JPanel;

import fileManager.AssetManager;
import fileManager.FileManipulator;
import methods.Method;
import object_Controller.BackgroundControl;

/**
 * 
 * @author Zhenyu
 *
 */
public class Cus_PieChart extends JPanel {

	// initialize private variables
	public static String name; //name of the pie chart
	private int x; //x position of the pie chart
	private int y; //y position of the pie chart
	private int width; //width of the pie chart
	private int height; //height of the pie chart
	private int startAngle; //startAngle of the slice
	private int endAngle; //endAngle of the slice
	private TreeMap <String, Integer> counter; // the counter that will count the number for each slices
	private ArrayList<Color> colors; //set color that will be displayed for each pie slices
	private TreeMap<String, Float> goThrough; //determine which treeMap is the data that need to be went through



	/**Constructor method
	 * 
	 */
	public Cus_PieChart() {

		colors = AssetManager.pieChartColors; //initialize the color array for the pie chart

		// initialized the treeMap for the counter and the x y position and width and height for the pie chart
		counter = new TreeMap <>();
		x = 700; //set x position
		y = 180; //set y position
		width = 400; //set the width
		height = 400; //set the height

		setLayout(null); //set the layout of the panel to null
	}

	/**change the city
	 * 
	 * @param name the name of the city
	 * @param dateType the data type that is selected
	 */
	public void changeCity(String name,String dateType){
		//initialize the gap to be 0
		int gap = 0;
		//set different gap for different data type so that the pie chart looks relevant and also so that the labels wont be crowded the overlapped
		if(dateType.equalsIgnoreCase("Mean Temperature")){
			goThrough =  FileManipulator.meanTemp.get(name);
			gap = 10;
		}else if(dateType.equalsIgnoreCase("Minimum Temperature")){
			goThrough =  FileManipulator.minTemp.get(name);
			gap = 10;
		}else if(dateType.equalsIgnoreCase("Maximum Temperature")){
			goThrough =  FileManipulator.maxTemp.get(name);
			gap = 10;
		}else if(dateType.equalsIgnoreCase("Heat Days")){
			goThrough = FileManipulator.heatDays.get(name);
			gap = 10;
		}else if(dateType.equalsIgnoreCase("Cool Days")){
			goThrough = FileManipulator.coolDays.get(name);
			gap = 5;
		}else if(dateType.equalsIgnoreCase("Total Rain")){
			goThrough = FileManipulator.totalRain.get(name);
			gap = 5;
		}else if(dateType.equalsIgnoreCase("Total Snow")){
			goThrough = FileManipulator.totalSnow.get(name);
			gap = 3;
		}else if(dateType.equalsIgnoreCase("Total Percipitation")){
			goThrough = FileManipulator.totalPrecip.get(name);
			gap = 5;
		}else if(dateType.equalsIgnoreCase("Snow On Ground")){
			goThrough = FileManipulator.snowOnGround.get(name);
			gap = 10;
		}else if(dateType.equalsIgnoreCase("Wind Speed")){
			goThrough = FileManipulator.speedGust.get(name);
			gap = 40;
		}
		//initialize the counter
		counter = new TreeMap <>();

		//for all the temperature inside of the data set
		for (float temp:goThrough.values()){
			String key = ""; //convert each value into a key value
			if(dateType.equalsIgnoreCase("Total Rain")||dateType.equalsIgnoreCase("Total Snow")||dateType.equalsIgnoreCase("Total Percipitation")
					||dateType.equalsIgnoreCase("Snow On Ground")||dateType.equalsIgnoreCase("Wind Speed")){
				//those data types are usually very polarized so that it would be better if data is separated with greater sign
				if(temp>=0&&temp<=gap){
					key = "0~"+gap; //one part is from 0 to the gap
				}else if(temp>=gap){ //the other part will be any other value greater than the gao
					key = ">"+gap;
				}
			}else{ //if the data is good like max/min/mean temperature then convert the data itself to a key value
				key += (int)(Math.floor(temp/gap)) * gap + "~"; //first data range start based on the gap and also "~"
				key += (int) (Math.floor(temp/gap)) * gap + gap; //second data range end based on the gap
			}


			incrementTreeMap(counter, key); //increment the key value of the treeMap by 1

		}
		repaint(); //recall the paintComponent
	}

	/**
	 * increment the treeMap based on if the treeMap already have the data or not
	 * @param map
	 * @param key
	 */
	private void incrementTreeMap(TreeMap<String, Integer> map, String key){

		if (map.containsKey(key)){ //if the heatMap already contains the key
			map.put(key, map.get(key)+1); //increment the original value by 1

		}
		else{ //if the heatMap does not contains the key
			map.put(key, 1); //initialize the key in the map, and put its counter to 1

		}
	}


	/**draw the stuff
	 * 
	 */
	@Override
	public void paintComponent (Graphics g){
		super.paintComponent(g); //calls the super component to draw the other stuffs

		Graphics2D g2d = (Graphics2D) g; //cast graphics to a 2D graphics

		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background images

		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparency of the graphics
		g2d.setColor(Color.black); //set the color to black
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a dark layer on top of the background image
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency back to opaque

		startAngle = 0; //initialize the start angle to 0

		int total = 0; //initialize the total to 0
		for (int cycle: counter.values()){
			total += cycle;	 //calculate the total by going through all the values
		}

		int colorCounter = 0; //initialize the counter that will be used to go through and set the pie chart slices to different colors

		int dimensionChange = 0; //initialize the counter that will be used to avoid overlap labels

		g2d.setFont(new Font("TimesRoman", Font.BOLD, 15)); //set the font
		for (String range: counter.keySet()){ //for all the range inside the the counter previously calculated
			int frequency = counter.get(range); //get the frequency of that range
			dimensionChange++; //increment dimensionChange counter

			g2d.setColor(colors.get(colorCounter));//set the color according to color index
			int angle = (int)(Math.ceil(frequency * 360.0 / total));  //calculate the angle based on the percentages
			g2d.fillArc(x,y,width,height,startAngle, angle); //fill in the arc based on the startAngle and the angle needed

			//calculation do draw the string on the outside of the circle
			float targetAngle = (float) ((startAngle + startAngle + angle)/2.0); //calculate the target angle which will be in between the startAngle and current angle
			float distanceX = (float) (Math.cos(targetAngle/180.0 * Math.PI)* 250.0); //calculate the x distance from the center of the circle
			float distanceY = (float) (Math.sin(targetAngle/180.0 * Math.PI) * -230.0); //calculate the y distance from the center of the circle
			startAngle += angle; //startAngle will be added by the angle previously calculated


			g2d.setColor(Color.white); //set the color to be white
			g2d.setFont(AssetManager.fonts[1]); //set the font
			if(dimensionChange == 3){ // is the dimension is the target one
				//draw the string a bit to the top so that it does not overlap
				g2d.drawString(range , x-25 + width/2 + distanceX, y - 30 + height/2 + distanceY);
			}else{
				//else draw the string normally
				g2d.drawString(range , x-25 + width/2 + distanceX, y + height/2 + distanceY);
			}

			colorCounter++; //increment the color counter
			if (colorCounter == colors.size()){ //if the color counter went out of the number of color provided
				colorCounter = 0; //start from the first color again
			}
		}

	}

}
