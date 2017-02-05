/* 
 * deni 2017
 */
import Canvas.DeniCanvas;
import Canvas.Layer.CanvasLayer;
import Canvas.Layer.PGraphics.AbstractPGraphics;
import Canvas.Layer.SaveCanvasLayerAction;
import Drawing.DrawingObjectImpl;
import Moving.MovementDescriber;
import Moving.TargetMovementDescriber;
import Pattern.FanPattern;
import ToolBox.FanPattern.BrushFanPatternTool;
import ToolBox.FanPattern.FanPatternTool;
import ToolBox.FanPattern.LerpColorFanPatternTool;
import Util.ColorHelper;
import Util.MathHelper;
import controlP5.ControlEvent;
import java.util.HashMap;
import java.util.Map;
import static processing.core.PApplet.map;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;


/**
 * Example to use fan pattern tools
 */
public class Example1  extends DeniCanvas
{
	LerpColorFanPatternTool lfanptool;
	BrushFanPatternTool bfanptool;
	
	
	@Override
	public void settings()
	{
		canvasWidth = 842;
        canvasHeight = 700;
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
		
		this.drawDraftBackground(this.getClass().getResource("resources/images/lion.jpg").getPath());
		this.setSavingInfo("lion", "/Users/daudirac/Desktop/images");
	}
	
	@Override
	public void draw()
	{
		this.background(255);
		super.draw();
		lfanptool.draw(this.getCurrenDrawingLayer());
		bfanptool.draw(this.getCurrenDrawingLayer());
	}
	
	 @Override
    public void keyPressed()
    {
		super.keyPressed();
		 if (key == CODED) {
			if (keyCode == DeniCanvas.DOWN)
			{
				CanvasLayer.Main.setOpacity(200);
			}
			if (keyCode == DeniCanvas.UP)
			{
				CanvasLayer.Main.setOpacity(255);
			}
		}
	}
}
