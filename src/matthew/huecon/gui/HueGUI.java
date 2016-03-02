package matthew.huecon.gui;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public abstract class HueGUI extends JFrame{
	private int width, height;
	
	private enum Direction{X,Y}
	
	/**
	 * Constructor to setup HueGUI's windows.
	 * <p>
	 * Call super in any subclasses for proper setup
	 * 
	 * @param height height of the window
	 * @param width width of the window
	 * @param windowName name to be displayed on the window
	 * @see JFrame
	 */
	public HueGUI(int height, int width, String windowName){
		this.width = width;
		this.height = height;
		this.setBounds(getScreenModdedPos(this.width, Direction.X),
					   getScreenModdedPos(this.height, Direction.Y),
					   this.width, this.height);
		this.getContentPane().setName(windowName);
		this.setResizable(false);
		this.addObjects();
		this.setVisible(true);
	}
	
	/**
	 * Helper method to center windows on the screen
	 * 
	 * @param size size of the dimension to modify
	 * @param d Direction of the dimension to modify
	 * @see Direction
	 */
	private int getScreenModdedPos(int size, Direction d){
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int monitorSize = 0;
		if(d.equals(Direction.X)){
			monitorSize = gd.getDisplayMode().getWidth();
		}else if(d.equals(Direction.Y)){
			monitorSize = gd.getDisplayMode().getHeight();
		}else{
			throw new IllegalArgumentException("Invalid direction used");
		}
		return (monitorSize/2)-(size/2);
	}
	
	/*
	 * Override this method if there are JComponents that need to be added to the Window
	 */
	protected void addObjects(){
		//NO-OP
	}
}
