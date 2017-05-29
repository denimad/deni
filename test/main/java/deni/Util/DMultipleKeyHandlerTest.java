/*
 * deni 2017
 */
package main.java.deni.Util;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.unhex;

/**
 *
 * @author daudirac
 */
public class DMultipleKeyHandlerTest extends PApplet
{
	DMultipleKeyHandler keyHandler;
	
	public static void main(String args[]) {
		PApplet.main(new String[] { DMultipleKeyHandlerTest.class.getName() });
	}
	
	public void settings()
	{
	 	size(300,300);
		
	}
	@Override
	public void setup()
	{
		keyHandler = new DMultipleKeyHandler();
	}

	
	public void draw()
	{
		String hex = "F8C16B";
		
		//int color =Integer.parseInt(hex, 16); 
		int color = unhex("FF" + hex);
		//background(color);
		
		
		
		
		ellipse(mouseX, mouseY,20,20);
		
	}
	
	@Override
	public void keyPressed()
	{
		
		this.keyHandler.onKeyPressed(key,keyCode); 
		
	}
	
	
	public void keyReleased()
	{
		if (this.keyHandler.isCntrlPaste())
		{
			System.out.println("yes");
			try {
				String data = (String) Toolkit.getDefaultToolkit() 
						.getSystemClipboard().getData(DataFlavor.stringFlavor);
				
				
				int color = unhex("FF" + data);
				fill(color);
				System.out.println(data);		
			} catch (UnsupportedFlavorException ex) {
				Logger.getLogger(DMultipleKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
			} catch (IOException ex) {
				Logger.getLogger(DMultipleKeyHandler.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		this.keyHandler.onKeyReleased();
		
	}
}