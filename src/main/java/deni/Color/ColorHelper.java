/* 
 * deni 2017
 */
package main.java.deni.Color;

import main.java.deni.Canvas.CanvasManager;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class ColorHelper 
{
	PApplet p5;
	private static ColorHelper instance;
	private ColorHelper()
	{
		p5 = CanvasManager.getInstance().getCanvas();
	}
	
	public int getColorWithAlpha(int color, float alpha)
	{
		float green  = p5.green(color);
		float red  = p5.red(color);
		float blue  = p5.blue(color);
		
		return p5.color(red, green, blue, alpha);
	}
	
	public int getColor(int r, int g, int b)
	{
		return p5.color(r, g, b);
	}
	
	public int getColor(int r, int g, int b, int a)
	{
		return p5.color(r, g, b,a );
	}
	
	
	
	
	public float getAlpha(int color)
	{
		return p5.alpha(color);
	}
	
	public float getBrightness(int color)
	{
		return p5.brightness(color);
	}
	
	public static ColorHelper getInstance()
	{
		if (instance == null)
		{
			instance = new ColorHelper();
		}
		
		return instance;
	}
	
	public static final int WHITE = ColorHelper.getInstance().getColor(255, 255, 255);
	public static final int BLACK = ColorHelper.getInstance().getColor(0, 0, 0);
	public static final int RED = ColorHelper.getInstance().getColor(255, 0, 0);
	public static final int BLUE = ColorHelper.getInstance().getColor(0, 255, 0);
	public static final int GREEN = ColorHelper.getInstance().getColor(0, 0, 255);
	public static final int BROWN2 = ColorHelper.getInstance().getColor(238, 59, 59);
	public static final int AQUAMARINE = ColorHelper.getInstance().getColor(69,	139, 116);
	public static final int DARKOLIVEGREEN = ColorHelper.getInstance().getColor(162,	205,	90);
	public static final int GOLDENROD = ColorHelper.getInstance().getColor(	218,	165,	32);
	
	
}
