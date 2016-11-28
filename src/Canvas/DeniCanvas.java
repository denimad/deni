package Canvas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.ControlFrame;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Controller.ControlOwner;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public class DeniCanvas extends PApplet
{
    @Override
    public void settings() {
        size(canvasHeight,canvasWidth);
         
    }
    
    @Override
    public void setup()
    {
        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(this);  
        canvasLayersManager = new CanvasLayersManager();
        this.initControlFrame();
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
   
    // ======= =========== ======
    // ======= CP5 METHODS ======
    // ======= =========== ======
    
    public void initControlFrame()
    {
        controlFrame = new ControlFrame(this,200,200,"deniController");
        
        
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
   
    
    private ControlP5 cp5; 
    ControlFrame controlFrame;
    
    public CanvasManager canvasManager;
    public CanvasLayersManager canvasLayersManager;
    
    public int canvasWidth = DENI_DEFAULT_WIDTH;
    public int canvasHeight = DENI_DEFAULT_HEIGHT;
    
    public static final int DENI_DEFAULT_WIDTH = 540;
    public static final int DENI_DEFAULT_HEIGHT = 540;
}
