package input_Control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Map;

public class KeyInput extends KeyAdapter{

	private Map map;
	
	public KeyInput(){
		
	}

	public KeyInput(Map map){
		this.map = map;
	}


	@Override
	public void keyPressed(KeyEvent e){
		int key  = e.getKeyCode();

		if(key==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}

		if(key==KeyEvent.VK_RIGHT){
			map.setYear(map.getYear()+1);
			map.refreshCities();
			map.refreshColor();
		}
		if(key==KeyEvent.VK_LEFT){
			map.setYear(map.getYear()-1);
			map.refreshCities();
			map.refreshColor();
			System.out.println("Complete");
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e){
	}


	@Override
	public void keyTyped(KeyEvent e){

	}
}








