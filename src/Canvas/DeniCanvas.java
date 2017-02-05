/* 
 * deni 2017
 */
package Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Canvas.Listener.CanvasInputAwareObject;
import Canvas.Layer.CanvasLayersManager;
import Canvas.Layer.CanvasLayer;
import Canvas.Layer.PGraphics.AbstractPGraphics;
import Canvas.Listener.CanvasListenerType;
import Controller.Tool.ToolControl;
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
        canvasLayersManager = CanvasLayersManager.getInstance();
		
		this.initToolController();
		this.setCanavasManagerListeners();
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
		canvasManager.keyPressed(key);
    }
    
    // ======= ============= ======
    // ======= LAYER METHODS ======
    // ======= ============= ======
	
    public AbstractPGraphics getCurrenDrawingLayer()
    {
        return this.canvasLayersManager.getCurrentDrawingLayer();
    }
    
    public AbstractPGraphics getToolDrawingLayer()
    {
        return this.getDrawingLayer(CanvasLayer.Tool);
    }
    
    public AbstractPGraphics getDrawingLayer(CanvasLayer layer)
    {
        return this.canvasLayersManager.getLayer(layer);
    }
   
	public void drawDraftBackground(String path)
	{
		PImage bg = this.loadImage(path);
		this.getDrawingLayer(CanvasLayer.Draft).beginDraw();
		this.getDrawingLayer(CanvasLayer.Draft).getPG().background(bg);
		this.getDrawingLayer(CanvasLayer.Draft).endDraw();
	}


	public void setCanavasManagerListeners()
	{
		// set the tools listener
		canvasManager.addCanvasListener(
			CanvasListenerType.ToolListener.getName(), 
			CanvasListenerType.ToolListener.getCanvasListener());
		
		// set the canvas layers listener
		canvasManager.addCanvasListener(
			CanvasListenerType.LayerListener.getName(), 
			CanvasListenerType.LayerListener.getCanvasListener());
	}
    // ======= =========== ======
    // =======    TOOLS    ======
    // ======= =========== ======
    
	public void initToolController()
	{
		this.toolController = new ToolControl(this);
		this.toolController.initFrames();
	}


	public ToolControl toolController;
	
    public CanvasManager<CanvasInputAwareObject> canvasManager;
    public CanvasLayersManager canvasLayersManager;
    
	public int canvasWidth = DeniCanvasConstants.DENI_DEFAULT_WIDTH;
    public int canvasHeight = DeniCanvasConstants.DENI_DEFAULT_HEIGHT;
}
