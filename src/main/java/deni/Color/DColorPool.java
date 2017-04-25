/*
 * deni 2017
 */
package main.java.deni.Color;

import java.util.ArrayList;
import main.java.deni.Canvas.CanvasManager;
import main.java.deni.Util.MathHelper;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class DColorPool 
{
	private ArrayList<DColor> colorList;
	PApplet deniCanvas;
	
	public DColorPool() {
		colorList = new ArrayList<>();
		deniCanvas = CanvasManager.getInstance().getCanvas();
	}
	
	public DColorPool add(DColor clr) {
		colorList.add(clr);
		return this;
	}
	
	public DColorPool remove(DColor clr) {
		if (colorList.contains(clr))
		{
			colorList.remove(clr);
		}
		else
		{
			System.err.println("color doesn't exist in color pool.");
		}
		
		return this;
	}
	
	
	public DColor getDColor() 
	{
		if(colorList.size() <= 0) return DefaultColor;

		int index = (int) Math.floor(deniCanvas.random(colorList.size()));
		return colorList.get(index);
	}
	
	public ArrayList<DColor> getAllColors()
	{
		return colorList;
	}


	public DColor getColor(int seed) {
		MathHelper.tempSeed(seed);
		DColor clr = getDColor();
		MathHelper.removeTempSeed();
		return clr;
	}
	
	private static DColor DefaultColor = new DSimpleColor(100,255);

}
