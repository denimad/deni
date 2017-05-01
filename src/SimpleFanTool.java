

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Color.DColorHelper;
import main.java.deni.Color.DColor;
import main.java.deni.Color.DColorPool;
import main.java.deni.Color.DSimpleColor;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Drawing.Pattern.DFanPattern;
import main.java.deni.Tool.DToolForwardingDrawingObject;
import main.java.deni.ToolBox.FanPattern.DBrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.DFanPatternTool;
import main.java.deni.ToolBox.FanPattern.DLerpColorPoolFanPatternTool;


/**
 * This is a simple example of using the 
 * FanPatternTool object
 */
public class SimpleFanTool extends DeniCanvas
{
	@Override
	public void settings()
	{
		canvasWidth = 600;
        canvasHeight = 800;
        super.settings();
        
    }
	
	public void setup()
	{
		super.setup();
		
		bfanptool =	new DBrushFanPatternTool();
		circleDO = new CircleDOTool();
		fanTool = new DLerpColorPoolFanPatternTool();
		
		this.toolController.addTool(bfanptool);
		this.toolController.addTool(circleDO);
		this.toolController.addTool(fanTool);
		
		this.toolController.setControls();
		
		this.drawImage(DCanvasLayer.Draft, "/Users/daudirac/Desktop/images/arbol.png");
		//this.drawImage(CanvasLayer.Background, "/Users/daudirac/Desktop/images/hanas2_Back.jpg");
		this.setSavingInfo("arbol", "/Users/daudirac/Desktop/images");
	}
	
	public void draw()
	{
		this.background(100);
		super.draw();
		//circleDO.draw(this.getCurrenDrawingLayer());
		fanTool.draw(this.getCurrenDrawingLayer());
	}

	DBrushFanPatternTool bfanptool;
	CircleDOTool circleDO;
	DLerpColorPoolFanPatternTool fanTool;

	public class CircleDOTool extends DToolForwardingDrawingObject
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
	
	public class CircleDrawingObj extends DDrawingObjectImpl
	{
		DColor circleColor;
		DColorPool circleColorPool;
		
		public CircleDrawingObj()
		{
			circleColor = new DSimpleColor(DColorHelper.AQUAMARINE, 
				255);
			circleColorPool = new DColorPool();
		}
		@Override
		public void update() 
		{
			
		}

		
		@Override
		public void draw(DAbstractPGraphics canvasLayer) 
		{
			canvasLayer.beginDraw();
				DColor dc = circleColorPool.getDColor();
				canvasLayer.getPG().fill(dc.getColor(),
						dc.getAlpha());
				canvasLayer.getPG().ellipse(mouseX, mouseY, 30, 30);
			canvasLayer.endDraw();
		}

		@Override
		public void setDrawingProperties() {}
		
		
	}
}
