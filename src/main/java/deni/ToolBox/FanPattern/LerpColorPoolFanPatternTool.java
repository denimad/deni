/*
 * deni 2017
 */
package main.java.deni.ToolBox.FanPattern;

import main.java.deni.Drawing.DrawingObjectImpl;
import main.java.deni.Drawing.Pattern.LerpColorPoolFanPattern;

/**
 *
 * @author daudirac
 */
public class LerpColorPoolFanPatternTool extends LerpColorFanPatternTool
{
	private final LerpColorPoolFanPattern fanPattern;
	
	public LerpColorPoolFanPatternTool()
	{
		this(new LerpColorPoolFanPattern());
	}
	
	public LerpColorPoolFanPatternTool(DrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		fanPattern  = (LerpColorPoolFanPattern) this.drawingObj;
		
		
	}
	
	@Override
	public String getName() {
		return "LerpColorPoolTool";
	}
	
	@Override
	public void setColorControls()
	{
		
		this.controlFrameWriter.addColorPoolController(
			"startColorPool", 
			this.fanPattern.startColorPool);
		this.controlFrameWriter.addColorPoolController(
			"endColorPool", 
			this.fanPattern.endColorPool);
		this.controlFrameWriter.addColorPoolController(
			"middleColorPool", 
			this.fanPattern.middleColorPool);;
	}
}
