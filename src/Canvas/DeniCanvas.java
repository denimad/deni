package Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.ControlFrame;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
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
		
		if (this.openControlFrame)
		{
			this.initControlFrame();
		}
    }
    
    @Override
    public void draw()
    {
        canvasLayersManager.drawLayers(this);
    }
    
    public void addMousePressedObject(CanvasObject canvasObject)
    {
        canvasManager.addOnMousePressedListener(
            canvasObject);
    }
    
    public void addMouseDraggedObject(CanvasObject canvasObject)
    {
        canvasManager.addOnMouseDraggedListener(
            canvasObject);
    }
    
    
    public void addMouseListenerObject(CanvasObject canvasObject)
    {
        canvasManager.addMouseListeners(canvasObject);
    }
    
    
    @Override
    public void mousePressed()
    {
        canvasManager.onMousePressed(mouseX, mouseY);
    }
    
    @Override
    public void mouseDragged()
    {
        canvasManager.onMouseDragged(mouseX, mouseY);
    }
    
    @Override
    public void mouseReleased()
    {
       canvasManager.onMouseReleased(mouseX,mouseY);
    }
    
    @Override
    public void mouseClicked()
    {
        canvasManager.onMouseClicked(mouseX,mouseY);
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
    // ======= CP5 METHODS ======
    // ======= =========== ======
    
    public void initControlFrame()
    {
        controlFrame = new ControlFrame(this,
			DeniCanvasConstants.DENI_DEFAULT_CONTROLLERWINDOW_WIDTH,
			DeniCanvasConstants.DENI_DEFAULT_CONTROLLERWINDOW_HEIGHT,
			"deniController");
        
        
    }
    
    /**
     * returns a control frame writer for the given control frame writer owner.
     * @param owner
     * @return 
     */
    public ControlFrameWriter getNewControlFrameWriter(ControlFrameWriterOwner owner)
    {
        return this.controlFrame.createNewControlFrameWriter(owner);
    }
   
	public ControlFrame getControlFrame()
	{
		return this.controlFrame;
	}
	
	
    ControlFrame controlFrame;
	public boolean openControlFrame = 
		DeniCanvasConstants.DENI_DEFAULT_OPEN_CONTROLLER;
    
    public CanvasManager canvasManager;
    public CanvasLayersManager canvasLayersManager;
    
    public int canvasWidth = DeniCanvasConstants.DENI_DEFAULT_WIDTH;
    public int canvasHeight = DeniCanvasConstants.DENI_DEFAULT_HEIGHT;
    
}
