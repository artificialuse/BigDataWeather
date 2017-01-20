package fileManager;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import application_Windows.Window;
import methods.Method;

/**
 * the asset for the program(picture)
 * @author Leo
 * @author Ryan
 * @author Zhenyu
 *
 */
public class AssetManager {

	//public static variables that will initialized in this class so that specific data especially images will only be read in once and stored
	public static BufferedImage canadaMap; // the map of the Canada

	public static Image loading; //the image for loading

	public static Image backgroundImage; //the background image that will be changed later

	public static ArrayList<Image> background_Spring; //the arrayList that store all the images of spring time
	public static ArrayList<Image> background_Summer; //the arrayList that store all the images of summer time
	public static ArrayList<Image> background_Autumn; //the arrayList that store all the images of autumn time
	public static ArrayList<Image> background_Winter; //the arrayList that store all the images of winter time


	public static ImageIcon dropDownArrow; //the drop down arrow for the customized comboBox

	public static ImageIcon[] charlottetown; //the array of Images that will store the pictures of charlottetown's logo white and green
	public static ImageIcon[] iqaluit; //the array of Images that will store the pictures of iqaluit's logo white and green
	public static ImageIcon[] markham; //the array of Images that will store the pictures of markham's logo white and green
	public static ImageIcon[] vancouver; //the array of Images that will store the pictures of vancouver's logo white and green
	public static ImageIcon[] whitehorse; //the array of Images that will store the pictures of whitehorse's logo white and green

	public static ImageIcon cityIconArrow_white; //the white icon arrow that will be used to set the icon 
	public static ImageIcon cityIconArrow_green; //the green icon arrow that show up on hover

	public static ImageIcon closeButton; //the image for the close button

	public static Image hoverMap_Map; //the map of Canada 3D for the city's information

	public static ImageIcon bluePin; //the bluePin to be put on map to indicate where the city is
	public static ImageIcon redPin; //the redPin to be put on map to indicate where the city is
	public static ImageIcon greenPin; //the greenPin to be put on map to indicate where the city is
	public static ImageIcon yellowPin; //the yellowPin to be put on map to indicate where the city is
	public static ImageIcon whitePin; // the whitePin to be put on map to indicate where the city is

	public static ImageIcon[] whitehorseImageFlag; //the images and flag for Whitehorse
	public static ImageIcon[] vancouverImageFlag; //the images and flag for Vancouver
	public static ImageIcon[] iqaluitImageFlag;//the images and flag for Iqaluit
	public static ImageIcon[] charlottetownImageFlag;//the images and flag for Charlottetown
	public static ImageIcon[] markhamImageFlag;//the images and flag for Markham

	public static ImageIcon[] leftSwitchButtonImage;//the left switch button with the image
	public static ImageIcon[] rightSwitchButtonImage; //the right switch button with the image

	public static Image reporter; //the picture of the reporter
	public static Image speechBubble; // the speechBubble of the reporter's speech

	public static ImageIcon sun; //the image of the weather forecast of a sunny day
	public static ImageIcon cloudsun;  //image of the weather forecast of a cloudy day
	public static ImageIcon snowflake; //image of the weather forecast of a snowy day

	public static ImageIcon tableIcon; //the icon on the menu for the table 
	public static ImageIcon lineGraphIcon; //the icon on the menu for the line graph
	public static ImageIcon pieChartIcon; //the icon on the menu for the pie chart
	public static ImageIcon reportIcon; //the icon on the menu for the report
	public static ImageIcon heatMapIcon; //the icon on the menu for the heatMap 
	public static ImageIcon citiesMapIcon; //the icon on the menu for the cities map

	private static float[] dash1 = {5.0f}; //moderate dash distance with the stroke
	private static float[] dash2 = {10.0f}; //longer dash distance with the stroke
	private static float[] dash3 = {3.0f}; //shorter dash distance with the stroke

	public static Color blue = new Color(101,153,255); // the customized blue color
	public static Color lightblue = new Color(82,216,255); //the customized light blue color
	public static Color lightBlueGrey = new Color(141,158,193); //the customized light blue with more greyish color
	public static Color lighterBlueGrey = new Color(196,205,223); //the customized lighter blue and grey color
	public static Color grey = new Color(64,64,64); //the grey color

	public static ArrayList<Color> pieChartColors; //the arrayList of the color that will displayed on the pie chart

	public static Stroke[] strokes = new Stroke[] { //the arrayList of the stroke
			new BasicStroke(4.0f), // The standard line but thicker
			new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,dash1, 0.0f), //dashed with 5 distance
			new BasicStroke(1.8f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,dash2, 0.0f), //dashed thicker with 10 distance
			new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,dash3, 0.0f),  //dashed line  with 3 distance
			new BasicStroke(4.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,dash1, 0.0f), //dashed line thicker with 5 distance
			new BasicStroke(2.0f),	// dash line with 2 point width
	};

	//the font with different size
	public static Font[] fonts  = new Font[] {
			new Font("Agency FB", Font.BOLD, 42), 
			new Font("Agency FB", Font.BOLD, 20),
			new Font("Agency FB", Font.BOLD, 12),
			new Font("Agency FB", Font.BOLD, 120),
			new Font("Agency FB", Font.BOLD, 16),
			new Font("Agency FB", Font.BOLD, 24),
			new Font("Agency FB", Font.BOLD, 45),
			new Font("Agency FB", Font.BOLD, 60)
	};

	/**
	 * the method used to change the weather based on the weatherID
	 * @param id the id of the background that will be changed to
	 */
	public void changeBackground(WeatherID id){
		Image changeTo = null; //initialized the image to be change to
		if(id==WeatherID.Spring){ //if the id is spring
			do{ 
				//the image that will be changed to will be chosen randomly from the arrayList
				changeTo = background_Spring.get(Method.random(0, background_Spring.size())); 
			}while(changeTo==backgroundImage); //no repeat image
		}else if(id==WeatherID.Summer){ //if the id is summer
			do{ 
				//the image that will be changed to will be chosen randomly from the arrayList
				changeTo = background_Summer.get(Method.random(0, background_Summer.size()));
			}while(changeTo==backgroundImage); //no repeat image
		}else if(id==WeatherID.Autumn){ //if the id is autumn
			do{ 
				//the image that will be changed to will be chosen randomly from the arrayList
				changeTo = background_Autumn.get(Method.random(0, background_Autumn.size()));
			}while(changeTo==backgroundImage); //no repeat image
		}else if(id==WeatherID.Winter){ //if the id is winter
			do{ 
				//the image that will be changed to will be chosen randomly from the arrayList
				changeTo = background_Winter.get(Method.random(0, background_Winter.size()));
			}while(changeTo==backgroundImage); //no repeat image
		}
		backgroundImage = changeTo; //change the background image
		changePieChartColor(); //change the pie chart color based on the background color
	}

	/**method to change the color of the pie chart
	 * 
	 */
	public void changePieChartColor(){
		pieChartColors.clear(); //clear the colors of the pie chart
		BufferedImage tempBuffer= Method.toBufferedImage(backgroundImage); //convert the image to a buffered image
		while(pieChartColors.size()<=10){ //keep adding color to the lost until reaches 10 colors
			//color will be chosen randomly from the picture
			Color color = new Color(tempBuffer.getRGB(Method.random(0, tempBuffer.getWidth()), Method.random(0, tempBuffer.getHeight())));
			if(!pieChartColors.contains(color)){ //if the pie chart does not contains the color already
				pieChartColors.add(color); //add it
			}
		}
	}

	/**
	 * initialize the image of the loading screen
	 */
	public void initLoading(){
		loading = new ImageIcon("res\\loading.gif").getImage(); //get the image from the position
	}

	/**initialized the collected all the image and scaling them and store them in java's data structure
	 * 
	 */
	public void init(){

		UIManager.put("ComboBox.selectionBackground", Color.cyan); //and the selection background for comboBox to be cyan color
		UIManager.put("RadioButton.select", Color.black); //and selected state of the radio button to be black

		//initialized the array of the images with the number needed
		charlottetown = new ImageIcon[2];
		iqaluit = new ImageIcon[2];
		markham = new ImageIcon[2];
		vancouver = new ImageIcon[2];
		whitehorse = new ImageIcon[2];

		//initialize the array of the images
		whitehorseImageFlag = new ImageIcon[7];
		vancouverImageFlag= new ImageIcon[7];
		iqaluitImageFlag= new ImageIcon[7];
		charlottetownImageFlag= new ImageIcon[7];
		markhamImageFlag= new ImageIcon[7];

		//initialize the array of the switch of the arrow button
		leftSwitchButtonImage = new ImageIcon[2];
		rightSwitchButtonImage = new ImageIcon[2];


		try {
			pieChartColors  = new ArrayList<Color>(); //initialize the arrayList that will hold the color of the pie chart

			//initialize the arrayList that will hold the background of the different theme
			background_Spring = new ArrayList<Image>();
			background_Summer = new ArrayList<Image>();
			background_Autumn = new ArrayList<Image>();
			background_Winter = new ArrayList<Image>();


			//get the background images
			for(int i=1; i<=4; i++){
				background_Spring.add(ImageIO.read(new File("res\\spring\\spring"+Integer.toString(i)+".jpg")).getScaledInstance(Window.WIDTH, Window.HEIGHT, Image.SCALE_SMOOTH));
				background_Summer.add(ImageIO.read(new File("res\\summer\\summer"+Integer.toString(i)+".jpg")).getScaledInstance(Window.WIDTH, Window.HEIGHT, Image.SCALE_SMOOTH));
				background_Autumn.add(ImageIO.read(new File("res\\autumn\\autumn"+Integer.toString(i)+".jpg")).getScaledInstance(Window.WIDTH, Window.HEIGHT, Image.SCALE_SMOOTH));
				background_Winter.add(ImageIO.read(new File("res\\winter\\winter"+Integer.toString(i)+".jpg")).getScaledInstance(Window.WIDTH, Window.HEIGHT, Image.SCALE_SMOOTH));
			}

			//change the background once so that variable are initialized
			changeBackground(WeatherID.Winter);


			//gather all the images from its position
			charlottetown[0] =  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Charlottetown.png")));
			iqaluit[0]= new ImageIcon( ImageIO.read(new File("res\\cityButtons\\Iqaluit.png")));
			markham[0]=  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Markham.png")));
			vancouver[0]=  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Vancouver.png")));
			whitehorse[0]=  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Whitehorse.png")));

			charlottetown[1] =  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Charlottetown_Green.png")));
			iqaluit[1] = new ImageIcon( ImageIO.read(new File("res\\cityButtons\\Iqaluit_Green.png")));
			markham[1] =  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Markham_Green.png")));
			vancouver[1] =  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Vancouver_Green.png")));
			whitehorse[1] =  new ImageIcon(ImageIO.read(new File("res\\cityButtons\\Whitehorse_Green.png")));

			cityIconArrow_white = new ImageIcon(ImageIO.read(new File("res\\buttons\\arrow_White.png")).getScaledInstance(83, 83, Image.SCALE_SMOOTH));
			cityIconArrow_green = new ImageIcon(ImageIO.read(new File("res\\buttons\\arrow_Green.png")).getScaledInstance(83, 83, Image.SCALE_SMOOTH));

			closeButton = new ImageIcon(ImageIO.read(new File("res\\buttons\\close_Button.png")).getScaledInstance(83, 83, Image.SCALE_SMOOTH));

			hoverMap_Map = ImageIO.read(new File("res\\hoverMapImages\\CanadaMap.png")).getScaledInstance(1000, 750, Image.SCALE_SMOOTH);

			bluePin = new ImageIcon("res\\hoverMapImages\\DropPinBlue.png");
			redPin = new ImageIcon("res\\hoverMapImages\\DropPinRed.png");
			greenPin = new ImageIcon("res\\hoverMapImages\\DropPinGreen.png");
			yellowPin = new ImageIcon("res\\hoverMapImages\\DropPinYellow.png");
			whitePin = new ImageIcon("res\\hoverMapImages\\DropPinWhite.png");

			whitehorseImageFlag[0] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\flags\\Whitehorse_flag.png")).getScaledInstance(250, 150, Image.SCALE_SMOOTH));
			vancouverImageFlag[0] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\flags\\Vancouver_flag.png")).getScaledInstance(250, 150, Image.SCALE_SMOOTH));
			iqaluitImageFlag[0] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\flags\\Iqaluit_flag.png")).getScaledInstance(250, 150, Image.SCALE_SMOOTH));
			charlottetownImageFlag[0] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\flags\\Charlottetown_flag.png")).getScaledInstance(250, 150, Image.SCALE_SMOOTH));
			markhamImageFlag[0] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\flags\\Markham_flag.png")).getScaledInstance(250, 150, Image.SCALE_SMOOTH));

			for(int i = 1; i<=6;i++){
				whitehorseImageFlag[i] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\cityImages\\Whitehorse"+Integer.toString(i)+".jpg")).getScaledInstance(600, 450, Image.SCALE_SMOOTH));
				vancouverImageFlag[i] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\cityImages\\Vancouver"+Integer.toString(i)+".jpg")).getScaledInstance(600, 450, Image.SCALE_SMOOTH));
				iqaluitImageFlag[i] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\cityImages\\Iqaluit"+Integer.toString(i)+".jpg")).getScaledInstance(600, 450, Image.SCALE_SMOOTH));
				charlottetownImageFlag[i] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\cityImages\\Charlottetown"+Integer.toString(i)+".jpg")).getScaledInstance(600, 450, Image.SCALE_SMOOTH));
				markhamImageFlag[i] = new ImageIcon(ImageIO.read(new File("res\\hoverMapImages\\cityImages\\Markham"+Integer.toString(i)+".jpg")).getScaledInstance(600, 450, Image.SCALE_SMOOTH));
			}

			leftSwitchButtonImage[0] = new ImageIcon("res\\buttons\\greyLeftPointer.png");
			leftSwitchButtonImage[1] = new ImageIcon("res\\buttons\\greenLeftPointer.png");

			rightSwitchButtonImage[0] = new ImageIcon("res\\buttons\\greyRightPointer.png");
			rightSwitchButtonImage[1] = new ImageIcon("res\\buttons\\greenRightPointer.png");

			dropDownArrow =  new ImageIcon(ImageIO.read(new File("res\\buttons\\drop_down_arrow.png")).getScaledInstance(30, 30, Image.SCALE_SMOOTH));

			speechBubble = ImageIO.read(new File("res\\report\\Speech_bubble.png")).getScaledInstance(420, 300, Image.SCALE_SMOOTH);
			reporter = ImageIO.read(new File("res\\report\\reporter.png"));
			sun = new ImageIcon(ImageIO.read(new File("res\\report\\sunFinal.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			cloudsun = new ImageIcon(ImageIO.read(new File("res\\report\\cloudsunFinal.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
			snowflake = new ImageIcon(ImageIO.read(new File("res\\report\\snowflakeFinal.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));

			tableIcon = new ImageIcon("res//buttons//menuButtons//TableIcon.png");
			lineGraphIcon= new ImageIcon("res//buttons//menuButtons//LineGraphIcon.png");
			pieChartIcon= new ImageIcon(ImageIO.read(new File("res//buttons//menuButtons//PieChartIcon.png")).getScaledInstance(230, 180, Image.SCALE_SMOOTH));
			reportIcon= new ImageIcon("res//buttons//menuButtons//ReportIcon.png");
			heatMapIcon= new ImageIcon("res//buttons//menuButtons//HeatMapIcon.png");
			citiesMapIcon= new ImageIcon("res//buttons//menuButtons//MapIcon.png");


			//store the picture of the map in a buffered image
			BufferedImage canadaMapBuffer = ImageIO.read(new File("res\\Canada_blank_map.png"));
			canadaMapBuffer = Method.toBufferedImage(canadaMapBuffer.getScaledInstance(canadaMapBuffer.getWidth()*Window.HEIGHT/canadaMapBuffer.getHeight(), Window.HEIGHT, Image.SCALE_SMOOTH));

			//for all the x and y coordinates on the picture
			for(int y = 0; y< canadaMapBuffer.getHeight();y++){
				for(int x = 0; x<canadaMapBuffer.getWidth(); x++){
					int p = canadaMapBuffer.getRGB(x, y); //get the rgb of that position
					if (p==-2894893){ //if the pixel is a land
						p = Method.getIntFromColor(0,0,0); //set the color to white
					}else{
						p = 0; //else set it to transparent
					}

					canadaMapBuffer.setRGB(x, y, p); //set the rbg value of the image
				}
			}
			//store the changed image
			canadaMap = canadaMapBuffer;
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
