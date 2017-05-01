/* 
 * deni 2017
 */
package main.java.deni.Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import main.java.deni.Canvas.Layer.DCanvasLayersManager;
import main.java.deni.Canvas.Layer.DCanvasLayer;
import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Canvas.Listener.CanvasListenerType;
import main.java.deni.Controller.Tool.ToolControl;
import processing.core.PApplet;
import processing.core.PImage;
import processing.event.MouseEvent;

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
        canvasManager = DCanvasManager.getInstance();
        canvasManager.setCanvas(this);  
        canvasLayersManager = DCanvasLayersManager.getInstance();
		
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
        canvasManager.mousePressed(
			(int) this.getMouseX(), 
			(int) this.getMouseY());
    }
    
    @Override
    public void mouseDragged()
    {
        canvasManager.mouseDragged(
			(int) this.getMouseX(), 
			(int) this.getMouseY());
    }
    
    @Override
    public void mouseReleased()
    {
       canvasManager.mouseReleased(
			(int) this.getMouseX(), 
			(int) this.getMouseY());
    }
    
    @Override
    public void mouseClicked()
    {
        canvasManager.mouseClicked(
			(int) this.getMouseX(), 
			(int) this.getMouseY());
    }
	
	@Override
	public void mouseWheel(MouseEvent e)
	{
		canvasManager.mouseWheel(e);
	}
    
    @Override
    public void keyPressed()
    {
		canvasManager.keyPressed(key);
    }
    
	public float getMouseX()
	{
		return (mouseX - canvasLayersManager.translateX) / 
			 canvasLayersManager.scaleFactor;
	}
	
	public float getMouseY()
	{
		return (mouseY - canvasLayersManager.translateY) / 
				canvasLayersManager.scaleFactor;
	}
	
    // ======= ============= ======
    // ======= LAYER METHODS ======
    // ======= ============= ======
	
    public DAbstractPGraphics getCurrenDrawingLayer()
    {
        return this.canvasLayersManager.getCurrentDrawingLayer();
    }
    
    public DAbstractPGraphics getToolDrawingLayer()
    {
        return this.getDrawingLayer(DCanvasLayer.Tool);
    }
    
    public DAbstractPGraphics getDrawingLayer(DCanvasLayer layer)
    {
        return this.canvasLayersManager.getLayer(layer);
    }
   
	/**
	 * Draws the image of the given path in the indicated layer.
	 * @param layer layer on which to draw the image.
	 * @param path path of the image to draw.
	 */
	public void drawImage(DCanvasLayer layer, String path)
	{
		PImage bg = this.loadImage(path);
		this.drawImage(layer, bg);
	}
	
	/**
	 * Draws the given image in the indicated layer.
	 * @param layer layer on which to draw the image.
	 * @param img image to draw.
	 */
	public void drawImage(DCanvasLayer layer, PImage img)
	{
		this.getDrawingLayer(layer).beginDraw();
		this.getDrawingLayer(layer).getPG().background(img);
		this.getDrawingLayer(layer).endDraw();
	}
	
	
	
	public void setBackgroundColor(int color)
	{
		this.getDrawingLayer(DCanvasLayer.Background).beginDraw();
			this.getDrawingLayer(DCanvasLayer.Background).getPG().background(color);
		this.getDrawingLayer(DCanvasLayer.Background).endDraw();
	}

	public void setSavingInfo(String fileName, String path)
	{
		this.canvasLayersManager.setSavingInfo(fileName, path);
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
	
    public DCanvasManager<CanvasInputAwareObject> canvasManager;
    public DCanvasLayersManager canvasLayersManager;
    
	public int canvasWidth = DeniCanvasConstants.DENI_DEFAULT_WIDTH;
    public int canvasHeight = DeniCanvasConstants.DENI_DEFAULT_HEIGHT;
}
