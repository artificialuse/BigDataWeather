package object_Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import application_Windows.Window;
import fileManager.AssetManager;
import fileManager.WeatherID;

/**
 * The background control for the background to fade in and out and change
 * @author Leo
 *
 */
public class BackgroundControl implements Runnable{

	//public static variable for the darkness of the panel, it is public static before there is only one background, and it is can be easily accessed in every class
	public static Float backgroundDarkLayer;
	//a thread will keep changing the background
	private Thread t;
	private boolean running = true; //the boolean variable that will control whether the program is running or not
	private boolean changing; //the boolean variable that will control whether the program is changing or not

	private Window window; //get the reference to the main window
	private AssetManager assetManager; //get the reference to the assetManager
	

	private WeatherID currentBackground; //set variable that will store the currentBackground type that is needed to be drawn


	/**Constructor method
	 * 
	 * @param window a reference to the main frame
	 * @param assetManager //a reference to the asset manager
	 */
	public BackgroundControl(Window window, AssetManager assetManager){
		this.window = window; //set the reference to the window based on the parameter
		this.assetManager = assetManager; //set the reference to the assetManaget based on the parameter
		
		backgroundDarkLayer = 0.4f; //set he default background darkness to 0.4
		DateFormat dateFormat = new SimpleDateFormat("MM");  //set the date format to the current system month for the data
		Date date = new Date(); //set date class that will get the current system date
		int month = Integer.parseInt(dateFormat.format(date)); //get the actual month and parse the string to an int
		if(month>=4&&month<=6) //if month is 4~6 set the weather to be spring
			currentBackground = WeatherID.Spring;
		else if(month>=7&&month<=9)  //if month is 7~9 set the weather to be summer
			currentBackground = WeatherID.Summer;
		else if(month>=10&&month<=12)  //if month is 10~12 set the weather to be autumn
			currentBackground = WeatherID.Autumn;
		else if(month>=1&&month<=3)  //if month is 1~3 set the weather to be winter
			currentBackground = WeatherID.Winter;

		changing = true; //prepared for the background to be changing
	 
		t = new Thread(this); //initialized the thread
		t.start(); //start the thread
	}

	/**Override the stuff that will be run in the thread
	 * 
	 */
	@Override
	public void run() {
		while(running){ //while it is running
			try {
				if(changing){ //if the background is set to change

					while(backgroundDarkLayer<0.99f){ // when the dark layer is not opaque
						backgroundDarkLayer += 0.01f; //increment the darkness
						window.repaint(); //repaint the window
						t.sleep(50); //delay 50 ms
					}
					//after the screen is all black change the background images
					assetManager.changeBackground(currentBackground);
	
					while(backgroundDarkLayer>0.4f){ //while the background's transparency is about 0.4
						backgroundDarkLayer -= 0.01f; //decrement the darkness
						window.repaint(); //repaint the window
						t.sleep(50);//delay 50 ms
					}
					t.sleep(1000); //delay 1 s
				}else{ //when it is not allowed to change
					while(backgroundDarkLayer<0.99f){ //when it is not dark yet
						backgroundDarkLayer += 0.01f; //increment the darkness
						window.repaint(); // repaint the window
						t.sleep(50); //delay 50 ms
					}
					backgroundDarkLayer = 1f; //set the transparency to opaque
					window.repaint(); //repaint the window
					t.sleep(1000); //delay for 1s
				}
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	/**get the currentBackground index
	 * 
	 * @return current background as an integer value
	 */
	
	public int getCurrentBackground() {
		//based on the currentBackgrond Id return an integer
		if (currentBackground==WeatherID.Spring)
			return 1; //if it is Spring return 1
		else if (currentBackground==WeatherID.Summer)
			return 2; //if it is Summer return 2
		else if (currentBackground==WeatherID.Autumn)
			return 3; //if it is Autumn return 3
		else if (currentBackground==WeatherID.Winter)
			return 4; //if it is Winter return 4
 
		return 0; //return 0 if there is not background
	}

	/**public method allow for the current background theme to change
	 * 
	 * @param currentBackground set the current background ID
	 */
	public void setCurrentBackground(WeatherID currentBackground) {
		this.currentBackground = currentBackground;
	}

	/**
	 * getter that will get if the background is changing
	 * @return get if the background is changing or not
	 */
	public boolean isChanging() {
		return changing;
	}

	/**setter that will allow the user to set the background to be either changing or not changing
	 * 
	 * @param changing set if the background is changing or not
	 */
	public void setChanging(boolean changing) {
		this.changing = changing;
	}



}
