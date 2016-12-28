/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox;

import Controller.Color.ColorControllerCreator;
import Object.Drawing.DrawingObjectImpl;
import Pattern.FanPattern;
import Util.ColorHelper;


/**
 * 
 */
public class FanPatternTool extends ToolForwardingDrawingObject
{
	public FanPatternTool()
	{
		this(new FanPattern());
	}
	
	public FanPatternTool(DrawingObjectImpl drawingObj) {
		super(drawingObj);
	}
	
	
	@Override
	public void setControls() 
	{
		this.controlFrameWriter.addSlider("strokePointsDistance", 20, 20, 1, 20);
		this.controlFrameWriter.addSlider("movingObjectsSpeed", 20, 30, 1, 10);
		this.controlFrameWriter.addSlider("movingObjectsInerciaStrengthMult", 20, 40, 1, 10);
		this.controlFrameWriter.addSlider("movingObjectsAttractionStrength", 20, 50, 1, 50);
		this.controlFrameWriter.addSlider("fanCircleSizeInc", 20, 60, 0, 1);
		/*this.controlFrameWriter.addColorController("color", 
			ColorControllerCreator.ColorControllerType.solidColorController, 
			20, 20, "color");*/
		this.controlFrameWriter.addColorController("colorDeAletas", 
				FanPattern.DEFAULT_COLOR);
	}
}
