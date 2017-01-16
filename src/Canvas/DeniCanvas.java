/* 
 * deni 2017
 */
package Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Canvas.Tool.ToolCanvasListenerManager;
import Controller.Tool.ToolControl;
import Tool.ToolInterface;
import java.util.Iterator;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

/**
 *
 * @author daudirac
 */
public class DeniCanvas extends PApplet
{
    @Override
    public void settings() {
        size(canvasWidth,canvasHeight);
         
    }
    
    @Override
    public void setup()
    {
        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(this);  
        canvasLayersManager = new CanvasLayersManager();
		
		this.initToolController();
    }
    
    @Override
    public void draw()
    {
        canvasLayersManager.drawLayers(this);
    }
    
    // ======= ============= ======
    // ===== LISTENER METHODS =====
    // ======= ============= ======
    @Override
    public void mousePressed()
    {
        canvasManager.mousePressed(mouseX, mouseY);
    }
    
    @Override
    public void mouseDragged()
    {
        canvasManager.mouseDragged(mouseX, mouseY);
    }
    
    @Override
    public void mouseReleased()
    {
       canvasManager.mouseReleased(mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked()
    {
        canvasManager.mouseClicked(mouseX, mouseY);
    }
    
    @Override
    public void keyPressed()
    {
        this.canvasLayersManager.keyPressed(key);
    }
    
    // ======= ============= ======
    // ======= LAYER METHODS ======
    // ======= ============= ======
	
    public PGraphics getCurrenDrawingLayer()
    {
        return this.canvasLayersManager.getCurrentDrawingLayer();
    }
    
    public PGraphics getToolDrawingLayer()
    {
        return this.getDrawingLayer(CanvasLayer.Tool);
    }
    
    public PGraphics getDrawingLayer(CanvasLayer layer)
    {
        return this.canvasLayersManager.getLayer(layer);
    }
   
	public void drawDraftBackground(String path)
	{
		PImage bg = this.loadImage(path);
		this.getDrawingLayer(CanvasLayer.Draft).beginDraw();
		this.getDrawingLayer(CanvasLayer.Draft).background(bg);
		this.getDrawingLayer(CanvasLayer.Draft).endDraw();
	}
	
    // ======= =========== ======
    // =======    TOOLS    ======
    // ======= =========== ======
    
	public void initToolController()
	{
		this.toolController = new ToolControl(this);
		this.toolController.initFrames();
		
		canvasManager.addCanvasListener(
			CanvasListenerType.ToolListener.getName(), 
			CanvasListenerType.ToolListener.getCanvasListener());
	}
	
    
	public ToolControl toolController;
	public ToolCanvasListenerManager toolCanvasListenerManager;
	
    public CanvasManager<CanvasObject> canvasManager;
    public CanvasLayersManager canvasLayersManager;
    
	public int canvasWidth = DeniCanvasConstants.DENI_DEFAULT_WIDTH;
    public int canvasHeight = DeniCanvasConstants.DENI_DEFAULT_HEIGHT;
}
