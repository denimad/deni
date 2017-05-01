/* 
 * deni 2017
 */
package main.java.deni.Color;

import java.math.BigInteger;
import main.java.deni.Canvas.DCanvasManager;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class DColorHelper 
{
	PApplet deniCanvas;
	private static DColorHelper instance;
	private DColorHelper()
	{
		deniCanvas = DCanvasManager.getInstance().getCanvas();
	}
	
	public int getColorWithAlpha(int color, float alpha)
	{
		float green  = deniCanvas.green(color);
		float red  = deniCanvas.red(color);
		float blue  = deniCanvas.blue(color);
		
		return deniCanvas.color(red, green, blue, alpha);
	}
	
	public int getColor(int r, int g, int b)
	{
		return deniCanvas.color(r, g, b);
	}
	
	public int getColor(int r, int g, int b, int a)
	{
		return deniCanvas.color(r, g, b,a );
		
	}
	
	public int getColorFromHex(String hexString)
	{
		
		int decodedHex =  Integer.decode(hexString);
		return deniCanvas.color(decodedHex,255);
	}
	
	
	public float getAlpha(int color)
	{
		return deniCanvas.alpha(color);
	}
	
	public float getBrightness(int color)
	{
		return deniCanvas.brightness(color);
	}
	
	public static DColorHelper getInstance()
	{
		if (instance == null)
		{
			instance = new DColorHelper();
		}
		
		return instance;
	}
	
	public static final int WHITE = DColorHelper.getInstance().getColor(255, 255, 255);
	public static final int BLACK = DColorHelper.getInstance().getColor(0, 0, 0);
	public static final int RED = DColorHelper.getInstance().getColor(255, 0, 0);
	public static final int BLUE = DColorHelper.getInstance().getColor(0, 255, 0);
	public static final int GREEN = DColorHelper.getInstance().getColor(0, 0, 255);
	public static final int BROWN2 = DColorHelper.getInstance().getColor(238, 59, 59);
	public static final int AQUAMARINE = DColorHelper.getInstance().getColor(69,	139, 116);
	public static final int DARKOLIVEGREEN = DColorHelper.getInstance().getColor(162,	205,	90);
	public static final int GOLDENROD = DColorHelper.getInstance().getColor(	218,	165,	32);
	
	
}
