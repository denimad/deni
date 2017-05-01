/*
 * deni 2017
 */
package main.java.deni.ToolBox.FanPattern;

import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Drawing.Pattern.DLerpColorPoolFanPattern;

/**
 *
 * @author daudirac
 */
public class DLerpColorPoolFanPatternTool extends DLerpColorFanPatternTool
{
	private final DLerpColorPoolFanPattern fanPattern;
	
	public DLerpColorPoolFanPatternTool()
	{
		this(new DLerpColorPoolFanPattern());
	}
	
	public DLerpColorPoolFanPatternTool(DDrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		fanPattern  = (DLerpColorPoolFanPattern) this.drawingObj;
		
		
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
