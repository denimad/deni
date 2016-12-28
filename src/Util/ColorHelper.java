/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Canvas.CanvasManager;
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
	
	public float getAlpha(int color)
	{
		return p5.alpha(color);
	}
	
	public static ColorHelper getInstance()
	{
		if (instance == null)
		{
			instance = new ColorHelper();
		}
		
		return instance;
	}
	
	
}
