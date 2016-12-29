/*
 * deni 2016
 */

import Canvas.DeniCanvas;
import ToolBox.FanPatternTool;


/**
 * This is a simple example of using the 
 * FanPatternTool object
 */
public class SimpleFanTool extends DeniCanvas
{
	@Override
	public void settings()
	{
		canvasWidth = 680;
        canvasHeight = 412;
        super.settings();
        
    }
	
	public void setup()
	{
		super.setup();
		
		this.drawDraftBackground("/Users/daudirac/Pictures/artists/inspiration/fish.jpg");
		
		fanPattern = new FanPatternTool();
		
		this.toolController.addTool(fanPattern);
		this.toolController.setControls();
		
		/*
		Below code is deprecated
		
		//create the control frame writer for the object 
		fanPattern.setControlFrameWriter(
				this.getNewControlFrameWriter(fanPattern));
		this.addMouseListenerObject(fanPattern);
		
		//show the control window
		this.getControlFrame().showControllers();
		*/
	}
	
	public void draw()
	{
		this.background(100);
		super.draw();
		fanPattern.draw(this.getCurrenDrawingLayer());
	}
	
	FanPatternTool fanPattern;
}
