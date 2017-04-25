

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.CanvasLayer;
import main.java.deni.Drawing.Pattern.LerpColorPoolFanPattern;
import main.java.deni.ToolBox.FanPattern.BrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.LerpColorFanPatternTool;
import main.java.deni.ToolBox.FanPattern.LerpColorPoolFanPatternTool;


/**
 * Example to use fan pattern tools
 */
public class Example1  extends DeniCanvas
{
	LerpColorPoolFanPatternTool lfanptool;
	BrushFanPatternTool bfanptool;
	
	
	@Override
	public void settings()
	{
		canvasWidth = 600;
        canvasHeight = 800;
        super.settings();
    }
	
	@Override
	public void setup()
	{
		super.setup();
		lfanptool = new LerpColorPoolFanPatternTool();
		bfanptool = new BrushFanPatternTool();
		
		this.toolController.addTool(lfanptool);
		this.toolController.addTool(bfanptool);
		this.toolController.setControls();
		
		this.drawImage(CanvasLayer.Draft, "/Users/daudirac/Desktop/images/arbol.png");
		
		this.setSavingInfo("rosa", "/Users/daudirac/Desktop/images");
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
