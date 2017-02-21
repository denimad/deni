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

		this.controlFrameWriter.addColorController(
			"startColor", 
			this.fanPattern.startColor,
			this.fanPattern.startColoralpha);
		this.controlFrameWriter.addColorController(
			"endColor", 
			this.fanPattern.endColor,
			this.fanPattern.endColoralpha);
		this.controlFrameWriter.addColorController(
			"middleColor", 
			this.fanPattern.middleColor,
			this.fanPattern.middleColoralpha);

	}		

	@Override
	public void controlEvent(ControlEvent event) 
	{
		super.controlEvent(event);

		if (event.isController())
		{
			switch(event.getName())
			{
				case "startColor": 
					this.fanPattern.startColor = (int) event.getValue();
					break;
				case "startColoralpha": 
					this.fanPattern.startColoralpha = (int) event.getValue();
					break;
				case "endColor": 
					this.fanPattern.endColor = (int) event.getValue();
					break;
				case "endColoralpha": 
					this.fanPattern.endColoralpha = (int) event.getValue();
					break;
				case "middleColor": 
					this.fanPattern.middleColor = (int) event.getValue();
					break;
				case "middleColoralpha": 
					this.fanPattern.middleColoralpha = (int) event.getValue();
					break;
				case "level":
					this.fanPattern.level = (int) event.getValue();
					break;
			}
		}
	}
}
