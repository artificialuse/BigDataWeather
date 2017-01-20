package application_Windows;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicButtonUI;

import Cus_UIs.Cus_ScrollBarUI;
import fileManager.AssetManager;
import fileManager.FileDownloader;
import fileManager.FileManipulator;
import methods.Method;
import object_Controller.BackgroundControl;

/** The panel to download/update the file
 * @author Leo
 */
//the Cus_FileDownloading will be a JPanel
public class Cus_FileDownloading extends JPanel{
	//setting up private instance variables
	private FileDownloader fileDownloader; //set a reference to the fileDownloader that will be passed in the constructor
	private FileManipulator fileManipulator; //set a reference to t0he fileManipulator that will be passed in the constructor
	private JTextArea textField; //set the text area that will hold and show instructions and download progress
	private JButton back, startDownload; //set the JButton for back and startDownload
	private JScrollPane scroller; //set the JScrollPane for the text area so that it will scroll
/**
 * Constructor method
 * @author Leo
 * @param window the main frame
 * @param fileDownloader the class that will download all files
 * @param fileManipulator the class that will manipulate the data from downloaded files
 */
	//Constructor method
	public Cus_FileDownloading(Window window, FileDownloader fileDownloader, FileManipulator fileManipulator){
		this.fileDownloader = fileDownloader; //initialized the fileDownloader based on the parameter
		this.fileManipulator = fileManipulator; //initialized the fileManipulator based on the parameter

		//set all components for the panel
		textField = new JTextArea(); //initialize the text area
		textField.setFont(AssetManager.fonts[1]); //set the font
		//set the initial text
		textField.setText("Are you sure you want to update Data?\nIf so, click \"Start Downloading\" button.\nWarning: No interuption should be made while downloading!");
		textField.setEditable(false); //set it to not editable
		textField.setFocusable(false); //set it to not focusable
		textField.setOpaque(false); //set it to transparent
		textField.setForeground(Color.white); //set font color to white



		scroller = new JScrollPane(textField); //initialize the scroll pane that hold the textField
		scroller.setBounds((Window.WIDTH-1000)/2, (Window.HEIGHT-600)/2, 1000, 500); //set the bound
		scroller.setOpaque(false); //set it to transparent
		scroller.getViewport().setOpaque(false); //set the its viewpoint too be transparent
		scroller.setHorizontalScrollBar(null); //remove the horizontal scroll bar
		scroller.getVerticalScrollBar().setUI(new Cus_ScrollBarUI()); //set the vertical scroll bar to have a customized UI
		scroller.getVerticalScrollBar().setOpaque(false); //set the vertical scroll bar to be transparent


		startDownload = new JButton("Start Download"); //initialize the button that will start the download progress
		startDownload.setBounds(920, 630, 150, 30); //set the bound
		startDownload.setFont(AssetManager.fonts[1]); //set the font
		startDownload.setUI(new BasicButtonUI()); //remove the default bluish background

		back = new JButton("Back"); //initialize the button will allow the user to go back
		back.setBounds(1080, 630, 150, 30); //set the bounds
		back.setFont(AssetManager.fonts[1]); //set the font
		back.setUI(new BasicButtonUI()); //remove the default bluish background

		setLayout(null); //set the layout to be null
		//add all components to the JPanel
		add(scroller); 
		add(back);
		add(startDownload);

		//set the functionality of the buttons
		startDownload.addActionListener(e->{
			download(); //when download button is clicked call this private method
		});

		back.addActionListener(e->window.changeComp(PanelID.Menu)); //set the back button to change the panel to Menu
	}

	
	/**
	 * the method that will be called when download button is pressed
	 */
	private void download(){
		//set the text to indicate that it will start to download
		textField.setText("Downloading\nThis might take a while\n");
		//create a thread that will start the downloading progress so that the main progress thread will not be stuck when downloading
		Thread t = new Thread(){
			@Override
			public void run(){
				back.setEnabled(false); //set the back button to be non editable
				startDownload.setEnabled(false); //set the start button to be non editable
				
				fileDownloader.download(textField); //start the fileDownloader
				textField.setText(textField.getText()+"\n"+"Organizing Data"); //when the fileDownloader finished download show this text
				textField.setSelectionStart(textField.getText().length()); //refresh the selected index so that scroll pane goes to the button to show the text
				textField.setSelectionEnd(textField.getText().length());
				
				fileManipulator.startCollecting(fileDownloader); //start he fileManipulator
				textField.setText(textField.getText()+"\n\n"+"Finished!\nYou can go back to main menu now."); //when the fileManipulator finishes, set the text
				textField.setSelectionStart(textField.getText().length()); //refresh the selected index so that scroll pane goes to the button to show the text
				textField.setSelectionEnd(textField.getText().length());
				
				back.setEnabled(true); //set the back button back to editable
				startDownload.setEnabled(true); //set the start button back to editable
			}
		};
		t.start(); //start the thread
	}

	/**
	 * the paint component that will draw the buttons as well as the background image
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //call the super component
		Graphics2D g2d = (Graphics2D) g; //cast the graphics to be a 2D graphics
		g2d.drawImage(AssetManager.backgroundImage, 0, 0, null); //draw the background image
		g2d.setColor(Color.black); //set the color to be black
		g2d.setComposite(Method.makeTransparent(BackgroundControl.backgroundDarkLayer)); //set the transparency of the next component drawn
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT); //draw a dark layer on top of the image
		g2d.setComposite(Method.makeTransparent(1)); //set the transparency of the panel back

	}
}
