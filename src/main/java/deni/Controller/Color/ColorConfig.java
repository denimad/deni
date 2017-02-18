/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import java.util.Map;

/**
 *
 * @author daudirac
 */
public class ColorConfig 
{
	public ColorConfig(ColorsController _colorsController)
	{
		this.colorsController = _colorsController;
	}
	
	Map<String, Integer> colorMap; 
	ColorsController colorsController;
}
