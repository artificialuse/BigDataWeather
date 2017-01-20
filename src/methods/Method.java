package methods;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.TreeMap;

/**
 * the method that are used pretty frequently in other classes
 * @author Leo
 *
 */
public class Method {
	public static Random r = new Random();

	/**a method that will produce a number based on the start number and end number
	 * 
	 * @param start the start number
	 * @param end the end number
	 * @return any number in between the start and the end
	 */
	public static int random(int start, int end){
		return r.nextInt(end-start)+start; //calculate the number 
 	}

	/**a method that will change rgb value to an int
	 * 
	 * @param Red the red value of a color
	 * @param Green the green value of a color
	 * @param Blue the blue value of a color
	 * @return integer that is converted based on the color
	 */
	public static int getIntFromColor(int Red, int Green, int Blue){
		Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
		Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
		Blue = Blue & 0x000000FF; //Mask out anything not blue.

		return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
	}
	
	/**a method that will change an image to a buffered image
	 * 
	 * @param img the Image needed to be changed
	 * @return the same image but to a buffered image type
	 */
	public static BufferedImage toBufferedImage(Image img){
		if (img instanceof BufferedImage) //if the image is already a bufferedImage
			return (BufferedImage) img; //just return it

		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB); //create an empty buffered image

		Graphics2D bGr = bimage.createGraphics(); //get the graphics for the buffered image
		bGr.drawImage(img, 0, 0, null); //draw the image into the buffered image
		bGr.dispose(); //dispose the graphics
		return bimage; // Return the buffered image
	}

	/**find the average based on the arrayList and the key date
	 * 
	 * @param temp the treeMap of temperatures
	 * @param specificDate the String of a specific Date
	 * @return //return the average of that selected date
	 */
	public static float findAvg(TreeMap<String, Float> temp, String specificDate){
		
		//find the average of a specific date based on the temperature treeMap
		float total = 0f; //initialize the total to be 0
		int counter = 0; //initialize the counter to be 0
		for(String date: temp.keySet()){ //for all the date available
			if(date.startsWith(specificDate)){ //if the date start will the specific date
				total+=temp.get(date); //add the total to the value on each day
				counter++; //increment the counter
			}
		}

		return total/counter; //return the average which is total/counter
	}


	/**
	 * the method that will return a composite based on the float value passed it, it is used to set the transparency of the component drawn
	 * @param alpha the transparency of the graphics (1 to be opaque, 0 to be transparent)
	 * @return return the AlphaComposite that can be used to change the transparency of the graphics
	 */
	public static AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER; //set the type
		return (AlphaComposite.getInstance(type,alpha)); //return the alphaComposite based on the float value and the type
	}
	
	
}
