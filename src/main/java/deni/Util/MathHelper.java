/* 
 * deni 2017
 */
package main.java.deni.Util;

import main.java.deni.Canvas.CanvasManager;
import main.java.deni.Canvas.DeniCanvas;

/**
 *
 * @author daudirac
 */
public class MathHelper 
{
	/**
	 * a -> c
	 * b -> ?
	 * @param a
	 * @param b
	 * @param c
	 * @return 
	 */
	public static float directRuleOfThree(float a, float b, float c)
	{
		return (b * c)/a;
	}
	
	public static float random(float min, float max)
	{
		return CANVAS.random(min,max);
	}
	
	public static float noNegative(float num)
	{
		return num < 0 ? 0 : num;
	}
	
	private static final DeniCanvas CANVAS = CanvasManager.getInstance().getCanvas();
}
