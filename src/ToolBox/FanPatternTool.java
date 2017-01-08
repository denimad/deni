/*
 * deni 2017
 */
package ToolBox;

import Drawing.DrawingObjectImpl;
import Pattern.FanPattern;
import java.util.Arrays;


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
	public String getName() {
		return "Fan Pattern Tool";
	}
	
	private FanPattern getFanPatterObj()
	{
		return (FanPattern) this.drawingObj;
	}
	
	@Override
	public void setControls() 
	{
		this.controlFrameWriter.addSlider("strokePointsDistance", 20, 20, 1, 20,
				this.getFanPatterObj().strokePointsDistance);
		this.controlFrameWriter.addSlider("movingObjectsSpeed", 20, 30, 1, 10,
				this.getFanPatterObj().movingObjectsSpeed);
		this.controlFrameWriter.addSlider("movingObjectsInerciaStrengthMult", 20, 40, 1, 10,
				this.getFanPatterObj().movingObjectsInerciaStrengthMult);
			
		this.controlFrameWriter.addSlider("movingObjectsAttractionStrength", 20, 50, 1, 50,
				this.getFanPatterObj().movingObjectsAttractionStrength);
		this.controlFrameWriter.addSlider("fanCircleSizeInc", 20, 60, -1, 1,
				this.getFanPatterObj().fanCircleSizeInc);
		this.controlFrameWriter.addColorController("colorDeAletas", 
				this.getFanPatterObj().colorDeAletas);
		this.controlFrameWriter.addScrollableList("changeMode","mode", 20, 70, 
			FanPattern.MODES , "default");
	}

	
}
