

/* 
 * deni 2017
 */
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.ToolBox.FanPattern.DBrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.DLerpColorPoolFanPatternTool;
import main.java.deni.ToolBox.Tool1;


/**
 * Example to use fan pattern tools
 */
public class Example1  extends DeniCanvas
{
	DLerpColorPoolFanPatternTool lfanptool;
	DBrushFanPatternTool bfanptool;
	Tool1 tool1;
	
	@Override
	public void settings()
	{
		canvasWidth = 1170;
        canvasHeight = 800;
        super.settings();
    }
	
	@Override
	public void setup()
	{
		super.setup();
		lfanptool = new DLerpColorPoolFanPatternTool();
		bfanptool = new DBrushFanPatternTool();
		tool1 = new Tool1();
		
		this.toolController.addTool(lfanptool);
		this.toolController.addTool(bfanptool);
		this.toolController.addTool(tool1);
		this.toolController.setControls();
		
		this.drawImage(DCanvasLayer.Draft, "/Users/daudirac/Dropbox/deniCloud/denise.royskopp.png");
		this.setSavingInfo("denise.royskopp", "/Users/daudirac/Dropbox/deniCloud");
	}
	
	@Override
	public void draw()
	{
		this.background(255);
		super.draw();
		lfanptool.draw(this.getCurrenDrawingLayer());
		bfanptool.draw(this.getCurrenDrawingLayer());
		tool1.draw(this.getCurrenDrawingLayer());
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
