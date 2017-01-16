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
import java.util.HashMap;
import java.util.Map;
import processing.core.PGraphics;
import processing.core.PVector;


/**
 * Example for a custom lerp color fan pattern.
 */
public class Example1  extends DeniCanvas
{
	LerpColorFanPatternTool lfanptool;
	BrushFanPatternTool bfanptool;
	@Override
	public void settings()
	{
		canvasWidth = 500;
        canvasHeight = 530;
        super.settings();
    }
	
	@Override
	public void setup()
	{
		super.setup();
		lfanptool = new LerpColorFanPatternTool();
		bfanptool = new BrushFanPatternTool();
		
		this.toolController.addTool(lfanptool);
		this.toolController.addTool(bfanptool);
		this.toolController.setControls();
		
		this.drawDraftBackground("/Users/daudirac/Pictures/artists/inspiration/rosa.jpg");
	}
	
	@Override
	public void draw()
	{
		this.background(100);
		super.draw();
		lfanptool.draw(this.getCurrenDrawingLayer());
		bfanptool.draw(this.getCurrenDrawingLayer());
	}
	
	
	private class LerpColorFanPatternTool extends FanPatternTool
	{
		private final LerpColorFanPattern fanPattern;
		
		
		public LerpColorFanPatternTool()
		{
			this(new LerpColorFanPattern());
			
		}
		
		public LerpColorFanPatternTool(DrawingObjectImpl drawingObj) 
		{
			super(drawingObj);
			fanPattern  = (LerpColorFanPattern) this.drawingObj;
		}
		
		
		
		@Override
		public String getName() {
			return "FPT1";
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
	}
	
	private class LerpColorFanPattern extends FanPattern
	{
		
		public LerpColorFanPattern()
		{
			super();
			
		}
		
		protected FanDrawingObj createFanDrawingObj(
			MovementDescriber mdes, 
			float circleSize,
			float circleSizeInc)
		{
			return new FanDrawingObj(mdes, circleSize, circleSizeInc);
		}
		
		int startColor = ColorHelper.BROWN2;
		int startColoralpha = 255;
		int endColor = ColorHelper.AQUAMARINE;
		int endColoralpha = 255;
		int middleColor = ColorHelper.GOLDENROD;
		int middleColoralpha = 255;
		int level=2;
		
		float[] circleSizeRange = new float[2];
		
		private class FanDrawingObj extends FanPattern.FanDrawingObj
		{
			TargetMovementDescriber targetmd;
			protected Map<PVector,Integer> lerpColorMap;
			
			public FanDrawingObj(MovementDescriber movementDescriber, 
				float circleSize,
				float circleSizeInc) 
			{
				super(movementDescriber, circleSize, circleSizeInc);
				targetmd = (TargetMovementDescriber) movementDescriber;
				lerpColorMap = new  HashMap<>();
			}
			
			 @Override
			public void draw(PGraphics canvasLayer) 
			{
				
				if (!targetmd.nearTarget(2))
				{
					PVector point = movementDescriber.returnLocation().copy();
					stroke.addStrokePoint(point);
					lerpColorMap.put(
						point,
						this.getLerpColor(canvasLayer, 
								targetmd.getInitialDistance(),
								targetmd.getDistanceToTarget(),
								level));
				}
				else
				{
					if (!painted)
					{
						canvasLayer.beginDraw();
							this.circleSize = this.originalCircleSize;
							canvasLayer.noStroke();
							canvasLayer.fill(colorDeAletas,colorDeAletasalpha);
							for (PVector point : stroke.strokePoints)
							{
								this.calculateCircleSize();
								canvasLayer.ellipse(point.x,
									point.y,
									circleSize+2f,circleSize+2f);  
							}
						
							this.circleSize = this.originalCircleSize;
							for (PVector point : stroke.strokePoints)
							{
								canvasLayer.fill(lerpColorMap.get(point),startColoralpha);
								this.calculateCircleSize();
								canvasLayer.ellipse(point.x,
									point.y,
									circleSize,circleSize);  
							}
						canvasLayer.endDraw();
						
						//set painted variable to true
						painted = true;
					}
				}
			}
			
			/**
			 * This method returns the calculated lerp color. 
			 * it supports 2 levels of color lerp. 
			 * (only 1 and 2 values are valid)
			 * @param levels 
			 */
			private int getLerpColor(PGraphics canvasLayer, float totalDist, float curDist,int level)
			{
				int lerpColor=0;
				if (level != 1 || level != 2)
				{
					float amt = MathHelper.directRuleOfThree(
								totalDist,
								curDist, 
								1);
					
					if (level == 1)
					{
						lerpColor = canvasLayer.lerpColor(
							startColor, 
							endColor, 
							amt);
					}
					else
					{
						if (amt<0.5)
						{
							lerpColor = canvasLayer.lerpColor(
							startColor, 
							middleColor, 
							amt*2f);
						}
						else
						{
							lerpColor = canvasLayer.lerpColor(
							middleColor, 
							endColor, 
							(amt-0.5f)*2f);
						}
					}
				}
				else
				{
					System.err.println("not valid level used in lerpcolor calculation." + 
						this.getClass());
				}
				
				return lerpColor;
			}
			
			protected void calculateCircleSize()
			{
				circleSize += circleSizeInc;
			}
		}
	}
	
	private class BrushFanPatternTool extends LerpColorFanPatternTool
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
		}
	}
	
	private class BrushFanPattern extends LerpColorFanPattern
	{
		
		public BrushFanPattern()
		{
			super();
		}
		
		protected void calculateCircleSize()
		{
			
		}
	}
}
