

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.ToolBox.FanPattern.DBrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.DLerpColorPoolFanPatternTool;
import main.java.deni.ToolBox.DNoiseParticlesDrawerTool;


/**
 * Example to use fan pattern tools
 */
public class Example1  extends DeniCanvas
{
	DLerpColorPoolFanPatternTool lfanptool;
	DBrushFanPatternTool bfanptool;
	DNoiseParticlesDrawerTool tool1;
	DNoiseParticlesDrawerTool tool2;
	DNoiseParticlesDrawerTool tool3;
	
	@Override
	public void settings()
	{
		canvasWidth = 1398;
        canvasHeight = 800;
        super.settings();
    }
	
	@Override
	public void setup()
	{
		super.setup();
		lfanptool = new DLerpColorPoolFanPatternTool();
		bfanptool = new DBrushFanPatternTool();
		tool1 = new DNoiseParticlesDrawerTool();
		tool2 = new DNoiseParticlesDrawerTool();
		tool3 = new DNoiseParticlesDrawerTool();
		
		this.toolController.addTool(lfanptool);
		this.toolController.addTool(bfanptool);
		this.toolController.addTool(tool1);
		this.toolController.addTool(tool2);
		this.toolController.addTool(tool3);
		
		this.toolController.setControls();
	}
	
	@Override
	public void draw()
	{
		this.background(255);
		super.draw();
		lfanptool.draw(this.getCurrenDrawingLayer());
		bfanptool.draw(this.getCurrenDrawingLayer());
		tool1.draw(this.getCurrenDrawingLayer());
		
		tool2.draw(this.getCurrenDrawingLayer());
		tool3.draw(this.getCurrenDrawingLayer());
	}
	
	 @Override
    public void keyPressed()
    {
		super.keyPressed();
		 if (key == CODED) {
			if (keyCode == DeniCanvas.DOWN)
			{
				DCanvasLayer.Main.setOpacity(200);
			}
			if (keyCode == DeniCanvas.UP)
			{
				DCanvasLayer.Main.setOpacity(255);
			}
		}
	}
}
