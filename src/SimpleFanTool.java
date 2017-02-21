

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.PGraphics.AbstractPGraphics;
import main.java.deni.Color.ColorHelper;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Color.DSimpleColor;
import main.java.deni.Drawing.DrawingObjectImpl;
import main.java.deni.Drawing.Pattern.FanPattern;
import main.java.deni.Tool.ToolForwardingDrawingObject;
import main.java.deni.ToolBox.FanPattern.FanPatternTool;


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
		
		circleDO = new CircleDOTool();
		
		this.toolController.addTool(circleDO);
		this.toolController.setControls();
	}
	
	public void draw()
	{
		this.background(100);
		super.draw();
		circleDO.draw(this.getCurrenDrawingLayer());
	}
	
	CircleDOTool circleDO;
	
	public class CircleDOTool extends ToolForwardingDrawingObject
	{
		CircleDrawingObj cdo;
	
		public CircleDOTool()
		{
			super(new CircleDrawingObj());
		}

		@Override
		public void setControls()
		{
			super.setControls();
			this.controlFrameWriter.addSlider(
				"strokePointsDistance", 
				20, 30, 1, 20, 10);
			
			this.controlFrameWriter.addSimpleColorController(
				"circleColor",
				this.getCircleObj().circleColor);
			this.controlFrameWriter.addColorPoolController(
				"circleColorPool", 
				this.getCircleObj().circleColorPool);
		}
		
		protected CircleDrawingObj getCircleObj()
		{
			return (CircleDrawingObj) this.drawingObj;
		}
		
		public String getName() {
			return "Custom Circle Testing";
		}
	}
	
	public class CircleDrawingObj extends DrawingObjectImpl
	{
		DColor circleColor;
		DColorPool circleColorPool;
		
		public CircleDrawingObj()
		{
			circleColor = new DSimpleColor(ColorHelper.AQUAMARINE, 
				255);
			circleColorPool = new DColorPool();
		}
		@Override
		public void update() 
		{
			
		}

		
		@Override
		public void draw(AbstractPGraphics canvasLayer) 
		{
			canvasLayer.beginDraw();
				DColor dc = circleColorPool.getColor();
				canvasLayer.getPG().fill(dc.getColor(),
						dc.getAlpha());
				canvasLayer.getPG().ellipse(mouseX, mouseY, 30, 30);
			canvasLayer.endDraw();
		}

		@Override
		public void setDrawingProperties() {}
		
		
	}
}
