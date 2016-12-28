/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Color;

import controlP5.ColorWheel;
import controlP5.ControlP5;

/**
 * Singleton class to create color controllers for
 * tools.
 */
public class ColorControllerCreator 
{
	
	
	private static ColorControllerCreator controllerCreator;
	
	public enum ColorControllerType
	{
		solidColorController,
		gradientColorController,
		colorSetController,
		gradientColorSetController
	}
	
	ControlP5 cp5;
	String name;
	Object variableObject;
	
	
	public void addColorController(ControlP5 cp5,
			Object variableObject,
			ColorControllerType type, 
			Object[] args,
			float posX, float posY, String groupName)
	{
		this.cp5 = cp5;
		
		this.variableObject = variableObject;
		
		switch (type)
		{
			case solidColorController: this.addSolidColorController(args, posX, posY,groupName);
										break;
		}
	}
	
	
	public void addSolidColorController(Object[] args, float posX, float posY,String groupName)
	{
		try
		{
			String varName = (String) args[0];
			this.cp5.addColorWheel(varName)
				.setPosition(posX, posY)
				.plugTo(this.variableObject)
				.moveTo(groupName);
		}
		catch(ClassCastException e)
		{
			System.err.print(
				"wrong arguments for solid color controller" + 
					e.getMessage());
		}	
		
		
	}
	
	public void addGradientColorController(Object[] args,
			float posX,
			float posY,
			String groupName)
	{
		try
		{
			String varNameColor1 = (String) args[1];
			String varNameColor2 = (String) args[1];
			
			
			
			
		}
		catch(ClassCastException e)
		{
			System.err.print(
				"wrong arguments for gradient color controller" + 
					e.getMessage());
		}
	}
	
	
	public static ColorControllerCreator getInstance()
	{
		if(controllerCreator == null)
		{
			controllerCreator = new ColorControllerCreator();
		}
		
		return controllerCreator;
	}
}
