/* 
 * deni 2017
 */
import Canvas.DeniCanvas;
import Canvas.Layer.PGraphics.AbstractPGraphics;
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
import static processing.core.PApplet.map;
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
		
		this.drawDraftBackground(this.getClass().getResource("resources/images/rosa.jpg").getPath());
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
		
		protected class FanDrawingObj extends FanPattern.FanDrawingObj
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
			public void draw(AbstractPGraphics canvasLayer) 
			{
				
				if (!targetmd.nearTarget(2))
				{
					PVector point = movementDescriber.returnLocation().copy();
					stroke.addStrokePoint(point);
					
					this.calculateStrokeInfoMaps(point, canvasLayer);
				}
				else
				{
					if (!painted)
					{
						canvasLayer.beginDraw();
							this.circleSize = this.originalCircleSize;
							canvasLayer.getPG().noStroke();
							canvasLayer.getPG().fill(colorDeAletas,colorDeAletasalpha);
							for (PVector point : stroke.strokePoints)
							{
								this.calculateCircleSize(point);
								canvasLayer.getPG().ellipse(point.x,
									point.y,
									circleSize+2f,circleSize+2f);  
							}
						
							this.circleSize = this.originalCircleSize;
							for (PVector point : stroke.strokePoints)
							{
								canvasLayer.getPG().fill(lerpColorMap.get(point),startColoralpha);
								this.calculateCircleSize(point);
								canvasLayer.getPG().ellipse(point.x,
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
			private int getLerpColor(AbstractPGraphics canvasLayer, float totalDist, float curDist,int level)
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
						lerpColor = canvasLayer.getPG().lerpColor(
							startColor, 
							endColor, 
							amt);
					}
					else
					{
						if (amt<0.5)
						{
							lerpColor = canvasLayer.getPG().lerpColor(
							startColor, 
							middleColor, 
							amt*2f);
						}
						else
						{
							lerpColor = canvasLayer.getPG().lerpColor(
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
			
			
			protected void calculateStrokeInfoMaps(PVector point,
					AbstractPGraphics canvasLayer)
			{
				lerpColorMap.put(
						point,
						this.getLerpColor(canvasLayer, 
								targetmd.getInitialDistance(),
								targetmd.getDistanceToTarget(),
								level));
			}
			
			protected void calculateCircleSize(PVector point)
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
						r1 = event.getArrayValue(0);
						r2 = event.getArrayValue(1);
						break;
				}
			}
		}
	}
	
	float r1= 0,r2= 1;
	
	private class BrushFanPattern extends LerpColorFanPattern
	{
	
		
		public BrushFanPattern()
		{
			super();
			
		}
		
		@Override
		protected FanDrawingObj createFanDrawingObj(
			MovementDescriber mdes, 
			float circleSize,
			float circleSizeInc)
		{
			return new BrushFanDrawingObj(mdes, circleSize, circleSizeInc);
		}
		
		private class BrushFanDrawingObj extends LerpColorFanPattern.FanDrawingObj
		{
			float range;
			protected Map<PVector,Float> sizeStrokeMap;
			
			public BrushFanDrawingObj(MovementDescriber movementDescriber, float circleSize, float circleSizeInc) 
			{
				super(movementDescriber, circleSize, circleSizeInc);
				sizeStrokeMap = new HashMap<>();
				range = MathHelper.random(r1,r2);	
			}
			
			@Override
			protected void calculateStrokeInfoMaps(PVector point,
					AbstractPGraphics canvasLayer)
			{
				super.calculateStrokeInfoMaps(point, canvasLayer);
				
				this.sizeStrokeMap.put(point, MathHelper.noNegative(map(
						targetmd.getInitialDistance() - targetmd.getDistanceToTarget(),
						0,
						targetmd.getInitialDistance() * range,
						originalCircleSize,
						0)));
			}
			@Override
			protected void calculateCircleSize(PVector point)
			{
				circleSize =  this.sizeStrokeMap.get(point);
			}
		}
	}
}
