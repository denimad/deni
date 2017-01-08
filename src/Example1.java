/*
 * deni 2016
 */

import Canvas.DeniCanvas;
import Drawing.DrawingObjectImpl;
import Moving.MovementDescriber;
import Moving.TargetMovementDescriber;
import Pattern.FanPattern;
import ToolBox.FanPatternTool;
import Util.ColorHelper;
import Util.MathHelper;
import controlP5.ControlEvent;
import processing.core.PApplet;
import processing.core.PGraphics;


/**
 * Example for a custom lerp color fan pattern.
 */
public class Example1  extends DeniCanvas
{
	CustomFanPatternTool cfanptool;
	@Override
	public void settings()
	{
		canvasWidth = 680;
        canvasHeight = 412;
        super.settings();
    }
	
	@Override
	public void setup()
	{
		super.setup();
		cfanptool = new CustomFanPatternTool();
		
		this.toolController.addTool(cfanptool);
		this.toolController.setControls();
	}
	
	@Override
	public void draw()
	{
		this.background(100);
		super.draw();
		cfanptool.draw(this.getCurrenDrawingLayer());
	}
	
	
	private class CustomFanPatternTool extends FanPatternTool
	{
		private final CustomFanPattern fanPattern;
		
		
		public CustomFanPatternTool()
		{
			super (new CustomFanPattern());
			fanPattern  = (CustomFanPattern) this.drawingObj;
		}
		
		
		@Override
		public String getName() {
			return "FPT1";
		}
		
		public void setControls() 
		{
			super.setControls();
			
			this.controlFrameWriter.addColorController(
				"startColor", 
				this.fanPattern.startColor,
				this.fanPattern.startColoralpha);
			this.controlFrameWriter.addColorController(
				"endColor", 
				this.fanPattern.endColor,
				this.fanPattern.endColoralpha);
		}		
	}
	
	private class CustomFanPattern extends FanPattern
	{
		
		public CustomFanPattern()
		{
			super();
			
		}
		
		protected FanDrawingObj createFanDrawingObj(
			MovementDescriber mdes, 
			float circleSize)
		{
			return new FanDrawingObj(mdes, circleSize);
		}
		
		int startColor = ColorHelper.WHITE;
		int startColoralpha = 255;
		int endColor = ColorHelper.WHITE;
		int endColoralpha = 255;
		
		private class FanDrawingObj extends FanPattern.FanDrawingObj
		{
			TargetMovementDescriber targetmd;
			
			public FanDrawingObj(MovementDescriber movementDescriber, 
				float circleSizeInc) 
			{
				super(movementDescriber, circleSizeInc);
				targetmd = (TargetMovementDescriber) movementDescriber;
			}
			
			 @Override
			public void draw(PGraphics canvasLayer) 
			{
				canvasLayer.beginDraw();
				if (!targetmd.nearTarget(2))
				{
					canvasLayer.noStroke();
					canvasLayer.fill(
						canvasLayer.lerpColor(
							startColor, 
							endColor, 
							MathHelper.directRuleOfThree(
								targetmd.getInitialDistance(),
								targetmd.getDistanceToTarget(), 
								1)),startColoralpha);
					//circleSize += circleSizeInc;
					canvasLayer.ellipse(targetmd.returnLocation().x, 
							targetmd.returnLocation().y, circleSize, circleSize);
					
				}
				canvasLayer.endDraw();
			}
			
			
			
		}
		
		// this part had to be implemented due to issue (reported in stack overflow):
		// http://stackoverflow.com/questions/41530679/controlp5-doesnt-modify-inherited-variables-of-plugto-object
		public void controlEvent(ControlEvent event) 
		{
			if (event.isController())
			{
				switch(event.getName())
				{
					case "strokePointsDistance":
						this.strokePointsDistance = (int) event.getValue();
						break;
					case "movingObjectsSpeed":
						this.movingObjectsSpeed =  event.getValue();
						break;	
					case "movingObjectsInerciaStrengthMult":
						this.movingObjectsInerciaStrengthMult =  event.getValue();
						break;
					case "movingObjectsAttractionStrength":
						this.movingObjectsAttractionStrength =  event.getValue();
						break;
					case "fanCircleSizeInc":
						this.fanCircleSizeInc =  event.getValue();
						break;
					case "colorDeAletas":
						this.colorDeAletas = (int) event.getValue();
						break;
					case "colorDeAletasalpha":
						this.colorDeAletasalpha = (int) event.getValue();
						break;
				}
			}
		}
	}
}
