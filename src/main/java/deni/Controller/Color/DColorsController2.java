/*
 * deni 2017
 */
package main.java.deni.Controller.Color;

import controlP5.ControlP5;
import java.util.HashMap;
import java.util.Map;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Grid.DSquareGrid;

/**
 *
 * @author daudirac
 */
public class DColorsController2 
{
	
	public DColorsController2(ControlP5 _cp5, 
		Object variableObject, 
		String groupName)
	{
		
		colorEditors = new HashMap<>();
		cp5 = _cp5;
		this.groupName = groupName;
		this.colorEditorPosition = new int[]{
			DColorsController2.DEFAULT_BANG_INIT_POSX,
			DColorsController2.DEFAULT_BANG_INIT_POSY};
		
		
	}
	
	public void addSimpleColorEditor(String varName,
			DColor color,
			int[] pos)
	{
		DSimpleColorEditor simpleCE = 
		 new DSimpleColorEditor(cp5, varName, color);
		colorEditors.put(varName, simpleCE);
		
		this.setColorEditorProperties(simpleCE, pos);
		
		simpleCE.drawMe();
	}
	
	public void addColorPoolEditor(String varName,
		DColorPool colorPool,
		int[] pos)
	{
		DColorPoolEditor colorPoolE = 
				new DColorPoolEditor(cp5, varName, colorPool);
		colorEditors.put(varName, colorPoolE);
		
		this.setColorEditorProperties(colorPoolE, pos);
		
		colorPoolE.drawMe();
	}
	
	private void setColorEditorProperties(DColorEditor ce, int[] pos)
	{
		ce.setPosition(pos[0], pos[1])
				.setGroupName(groupName);
	}
	public void resetController()
	{
		this.colorEditorPosition= new int[]{
			DColorsController2.DEFAULT_BANG_INIT_POSX,
			DColorsController2.DEFAULT_BANG_INIT_POSY};
	}
	
	Map<String, DColorEditor> colorEditors;
	/**
	 * cp5 object that invokes the controllers.
	 */
	ControlP5 cp5;
	
	/**
	 * variable object associated.
	 */
	Object variableObject;
	/**
	 * name of the group name where all the 
	 * color controls of this class will be added.
	 */
	String groupName;
	
	/**
	 * defines the position of a new bang.
	 * it automatically calculates the next editor position.
	 */
	int[] colorEditorPosition;
	
	/**
	 * This field is used to calculate the position of buttons
	 * in a square grid layout position.
	 */
	private static DSquareGrid squareGrid;
	
	private static final int DEFAULT_BANG_INIT_POSX= 20;
	private static final int DEFAULT_BANG_INIT_POSY= 30;
}
