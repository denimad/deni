/*
 * deni 2017
 */
package ToolBox.FanPattern;

import Drawing.Pattern.BrushFanPattern;
import controlP5.ControlEvent;

/**
 *
 * @author daudirac
 */
public class BrushFanPatternTool extends LerpColorFanPatternTool
{
	BrushFanPattern brushPattern;
	public BrushFanPatternTool()
	{
		super(new BrushFanPattern());
		brushPattern = (BrushFanPattern) this.drawingObj;
	}

	@Override
	public String getName() {
		return "FPT2";
	}

	public void setControls() 
	{
		super.setControls();

		this.controlFrameWriter.addRange("circleSizeRange", "circleSize", 140, 120, 0, 1, "default");
	}

	@Override
	public void controlEvent(ControlEvent event) 
	{
		super.controlEvent(event);

		if (event.isController())
		{
			switch(event.getName())
			{
				case "circleSizeRange": 
					brushPattern.r1 = event.getArrayValue(0);
					brushPattern.r2 = event.getArrayValue(1);
					break;
			}
		}
	}
}
