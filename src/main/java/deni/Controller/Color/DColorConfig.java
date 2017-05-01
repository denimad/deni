/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import java.util.Map;

/**
 *
 * @author daudirac
 */
public class DColorConfig 
{
	public DColorConfig(DColorsController _colorsController)
	{
		this.colorsController = _colorsController;
	}
	
	Map<String, Integer> colorMap; 
	DColorsController colorsController;
}
