/*
 * deni 2017
 */
package main.java.deni.ToolBox;

import controlP5.ControlEvent;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Drawing.Pattern.DDrwobj1;
import main.java.deni.Tool.DToolForwardingDrawingObject;

/**
 *
 * @author daudirac
 */
public class Tool1 extends DToolForwardingDrawingObject
{
	DDrwobj1 drawingObj1;
	
	public Tool1()
	{
		this(new DDrwobj1());
	}
	
	public Tool1(DDrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		drawingObj1 = (DDrwobj1) this.drawingObj;
	}
	
	public DDrwobj1 getDrawingObj()
	{
		return this.drawingObj1;
	}
	
	@Override
	public String getName() 
	{
		return "drwobj1";
	}
	
	
	@Override
	public void setControls() 
	{
		super.setControls();
		
		this.controlFrameWriter.addSlider("noiseScale", 20, 30, 1, 200,
				this.getDrawingObj().noiseScale);
		this.controlFrameWriter.addSlider("noiseStrength", 20, 40, 0.3f, 10f,
				this.getDrawingObj().noiseStrength);
		this.controlFrameWriter.addSlider("circleSize", 20, 60, 0.5f, 100f,
				this.getDrawingObj().circleSize);
		this.controlFrameWriter.addSlider("circleInc", 20, 70, -5f, 5f,
				this.getDrawingObj().circleInc);
		
		this.controlFrameWriter.addColorPoolController("colorPool", 
				this.getDrawingObj().colorPool);
		this.controlFrameWriter.addColorPoolController("borderColorPool", 
				this.getDrawingObj().borderColorPool);
	}
	
	
	@Override
	public void controlEvent(ControlEvent event) 
	{
		super.controlEvent(event);

		if (event.isController())
		{
			switch(event.getName())
			{
				case "noiseScale": 
					this.getDrawingObj().noiseScale = event.getValue();
					break;
				case "noiseStrength": 
					this.getDrawingObj().noiseStrength = event.getValue();
					break;
				case "circleSize": 
					this.getDrawingObj().circleSize = event.getValue();
					break;
				case "circleInc": 
					this.getDrawingObj().circleInc = event.getValue();
					break;
				
			}
		}
	}
}
