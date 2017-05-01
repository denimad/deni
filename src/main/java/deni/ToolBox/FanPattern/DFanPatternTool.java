/* 
 * deni 2017
 */
package main.java.deni.ToolBox.FanPattern;

import main.java.deni.Tool.DToolForwardingDrawingObject;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Drawing.Pattern.DFanPattern;
import controlP5.ControlEvent;


/**
 * 
 */
public class DFanPatternTool extends DToolForwardingDrawingObject
{
	DFanPattern fp;
	
	public DFanPatternTool()
	{
		this(new DFanPattern());
	}
	
	public DFanPatternTool(DDrawingObjectImpl drawingObj) {
		super(drawingObj);
		fp = (DFanPattern) this.drawingObj;
	}
	
	@Override
	public String getName() {
		return "Fan Pattern Tool";
	}
	
	protected DFanPattern getFanPatterObj()
	{
		return (DFanPattern) this.drawingObj;
	}
	
	@Override
	public void setControls() 
	{
		super.setControls();
		
		this.controlFrameWriter.addSlider("strokePointsDistance", 20, 30, 1, 20,
				this.getFanPatterObj().strokePointsDistance);
		this.controlFrameWriter.addSlider("movingObjectsSpeed", 20, 40, 1, 10,
				this.getFanPatterObj().movingObjectsSpeed);
		this.controlFrameWriter.addSlider("movingObjectsInerciaStrengthMult", 20, 50, 1, 10,
				this.getFanPatterObj().movingObjectsInerciaStrengthMult);
			
		this.controlFrameWriter.addSlider("movingObjectsAttractionStrength", 20, 60, 1, 50,
				this.getFanPatterObj().movingObjectsAttractionStrength);
		this.controlFrameWriter.addSlider("fanCircleSizeInc", 20, 70, -1, 1,
				this.getFanPatterObj().fanCircleSizeInc);
		this.controlFrameWriter.addSlider("fanCircleSize", 20, 80, 0, 10,
				this.getFanPatterObj().fanCircleSize);
		this.controlFrameWriter.addSlider("randomStrength", 20, 90, 1, 100,
				this.getFanPatterObj().randomStrength);
		this.controlFrameWriter.addScrollableList("changeMode","mode", 20, 100, 
			DFanPattern.MODES , "default");
		
		// color controllers
		this.controlFrameWriter.addSimpleColorController(
			"colorDeAletas", 
			this.getFanPatterObj().colorDeAletas);
	}

	/**
	 * This tool uses controls.
	 * so we need to implement the responses to the events.
	 * this part is implemented because automatic variable changes 
	 * in CP5 doesn't support subtype classes.
	 * Case reported in Stack Overflow: 
	 * http://stackoverflow.com/questions/41530679/controlp5-doesnt-modify-inherited-variables-of-plugto-object
	 * @param event the event triggered by a control action.
	 */
	@Override
	public void controlEvent(ControlEvent event) 
	{
		if (event.isController())
			{
				switch(event.getName())
				{
					case "strokePointsDistance":
						fp.strokePointsDistance = (int) event.getValue();
						break;
					case "movingObjectsSpeed":
						fp.movingObjectsSpeed =  event.getValue();
						break;	
					case "movingObjectsInerciaStrengthMult":
						fp.movingObjectsInerciaStrengthMult =  event.getValue();
						break;
					case "movingObjectsAttractionStrength":
						fp.movingObjectsAttractionStrength =  event.getValue();
						break;
					case "fanCircleSizeInc":
						fp.fanCircleSizeInc =  event.getValue();
						break;
					case "fanCircleSize":
						fp.fanCircleSize =  event.getValue();
						break;
					/*case "colorDeAletas":
						fp.colorDeAletas = (int) event.getValue();
						break;
					case "colorDeAletasalpha":
						fp.colorDeAletasalpha = (int) event.getValue();
						break;*/
					case "changeMode":
						fp.mode = DFanPattern.MODES.get((int) event.getValue());
						break;
				}
			}
	}

	
}
