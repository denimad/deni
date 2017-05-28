/* 
 * deni 2017
 */
package main.java.deni.Util;

import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.DeniCanvas;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class DMathHelper 
{
	private static boolean usingTempSeed;
	private static int resetSeedValue;
	
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
	
	public static int floor(float num)
	{
		return (int) PApplet.floor(num);
	}
	
	public static int randomInt(float high) {
		return (int) Math.floor( CANVAS.random(high) );
	}

	public static int randomInt(float low, float high) {
		return (int) Math.floor( CANVAS.random(low,high) );
	}

	public static int randomInt32() {
		return randomInt(-2147483648,2147483647);
	}
	
	public static void tempSeed(long seed) {
		if(!usingTempSeed) {
			resetSeedValue = randomInt32();
			usingTempSeed = true;
		}
		CANVAS.randomSeed(seed);
	}
	
	public static void removeTempSeed() {
		CANVAS.randomSeed(resetSeedValue);
		usingTempSeed = false;
	}
	
	public static int round(float f)
	{
		return Math.round(f);
	}
	private static final DeniCanvas CANVAS = DCanvasManager.getInstance().getCanvas();
}
