
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.ToolBox.FanPattern.DBrushFanPatternTool;
import main.java.deni.ToolBox.FanPattern.DLerpColorPoolFanPatternTool;
import main.java.deni.ToolBox.Tool1;
import main.java.deni.Util.DSystem;
import processing.core.PImage;

/*
 * deni 2017
 */

/**
 * This example is for executable deni apps.
 * the resources file are relative to the source code location.
 * It's useful when you want to share deni canvases to end users.
 */
public class ExecutableExample extends DeniCanvas
{
	DLerpColorPoolFanPatternTool lfanptool;
	DBrushFanPatternTool bfanptool;
	Tool1 tool1;

	PImage draftImage;
	
	@Override
	public void settings()
	{
		draftImage=loadImage(
			DSystem.getAppResourcesPath() + "/draft.jpg");
		canvasWidth = draftImage.width;
        canvasHeight = draftImage.height;
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
		
		this.drawImage(DCanvasLayer.Draft, draftImage);
		
		this.setSavingInfo("savedImage", DSystem.getAppSavedImagesPath());
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
}
