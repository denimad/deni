/*
 * deni 2017
 */
package main.java.deni.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import main.java.deni.Canvas.Listener.DCanvasKeyInputAwareObject;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.event.MouseEvent;

/**
 * This class can handle to store 
 * up the 10 key pressed at the same time.
 */
public class DMultipleKeyHandler implements DCanvasKeyInputAwareObject
{

	public DMultipleKeyHandler()
	{
		this.pressedKeys = new ArrayList<>();
		this.specialKeysinit();
	}

	private void specialKeysinit()
	{
		this.CntrlPressed = false;
		this.ALTPressed = false;
		this.ShiftPressed = false;
	}

	@Override
	public void onKeyPressed(char key) {
		
	}
	
	@Override
	public void onKeyPressed(char key, int keyCode) 
	{
		if (this.totalKeysCounter < MAX_CONCURRENT_KEY_PRESSED)
		{
			if(key == PApplet.CODED)
			{
				this.registerSpecialKeyPressed(keyCode);
			}
			else
			{
				this.registerKeyPressed(key, keyCode);
			}
		}
		else
		{
			System.err.println("Max key pressed reached");
		}
	}
	
	
	
	private void registerKeyPressed(char key, int keyCode)
	{
		char insertKey = key;
		
		if(this.isSpecialKeyPressed())
		{
			insertKey = Character.toString ((char) keyCode).charAt(0);
		}
		
		if (!this.pressedKeys.contains(insertKey))
		{
			this.pressedKeys.add(insertKey);
			this.totalKeysCounter++;
		}
	}
	
	private void registerSpecialKeyPressed(int keyCode)
	{
		switch (keyCode) {
			case PApplet.CONTROL:
				this.CntrlPressed = true;
				break;
			case PApplet.ALT:
				this.ALTPressed = true;
				break;
			case PApplet.SHIFT:
				this.ShiftPressed = true;
				break;
			default:
				break;
		}
		this.totalKeysCounter++;
	}
	
	private boolean isSpecialKeyPressed()
	{
		return this.CntrlPressed || this.ALTPressed || this.ShiftPressed;
	}

	public List<Character> getKeyCombination()
	{
		return this.pressedKeys;
	}
	
	public int getTotalKeysPressed()
	{
		return this.totalKeysCounter;
	}
	
	
	@Override
	public void onKeyReleased() 
	{
		keyReleased = true;
		this.pressedKeys.clear();
		this.specialKeysinit();
		totalKeysCounter = 0;
	}

	/**
	 * This method returns the first key pressed.
	 * if there aren't any keys pressed it will return 0.
	 * @return 
	 */
	public char getFirstKeyPressed()
	{
		return !this.pressedKeys.isEmpty() ?
			this.pressedKeys.get(0) : '0';
	}
	
	
	/**
	 * This method returns the last key pressed.
	 * if there aren't any keys pressed it will return 0.
	 * @return 
	 */
	public char getLastKeyPressed()
	{
		return !this.pressedKeys.isEmpty() ?
			this.pressedKeys.get(this.pressedKeys.size()-1) : '0';
	}


	public boolean isCntrlPaste()
	{
		boolean resul = false;
		if (this.totalKeysCounter == 2)
		{
			if (this.CntrlPressed &&
				(this.pressedKeys.contains('v')|| 
				this.pressedKeys.contains('V')))
			{
				resul = true;
			}
		}
		return resul;
	}


	List<Character>pressedKeys;
	
	boolean CntrlPressed;
	boolean ALTPressed;
	boolean ShiftPressed;
	boolean specialKeyIsPressed;
	
	int totalKeysCounter;
	boolean keyReleased;
	
	public static final int MAX_CONCURRENT_KEY_PRESSED = 10;

	


}
