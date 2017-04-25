/*
 * deni 2017
 */
package main.java.deni.ToolBox.FanPattern;

import main.java.deni.Drawing.DrawingObjectImpl;
import main.java.deni.Drawing.Pattern.LerpColorFanPattern;
import controlP5.ControlEvent;

/**
 *
 * @author daudirac
 */
public class LerpColorFanPatternTool extends FanPatternTool
{
	private final LerpColorFanPattern fanPattern;

	public LerpColorFanPatternTool(DrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		fanPattern  = (LerpColorFanPattern) this.drawingObj;
	}

	public LerpColorFanPatternTool()
	{
		this(new LerpColorFanPattern());
	}

	@Override
	public String getName() {
		return "LerpColorTool";
	}

	public void setControls() 
	{
		super.setControls();

		this.controlFrameWriter.addSlider("level",
			140, 100, 1, 2, this.fanPattern.level);

		this.setColorControls();
	}	

	public void setColorControls()
	{
		this.controlFrameWriter.addSimpleColorController(
			"startColor", 
			this.fanPattern.startColor);
		this.controlFrameWriter.addSimpleColorController(
			"endColor", 
			this.fanPattern.endColor);
		this.controlFrameWriter.addSimpleColorController(
			"middleColor", 
			this.fanPattern.middleColor);
	}

	@Override
	public void controlEvent(ControlEvent event) 
	{
		super.controlEvent(event);

		if (event.isController())
		{
			switch(event.getName())
			{
				case "level":
					this.fanPattern.level = (int) event.getValue();
					break;
			}
		}
	}
}
