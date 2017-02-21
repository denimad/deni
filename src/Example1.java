

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.CanvasLayer;
import main.java.deni.ToolBox.FanPattern.BrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.LerpColorFanPatternTool;


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
		
		this.drawDraftBackground("/Users/daudirac/Desktop/images/rosa.jpg");
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
