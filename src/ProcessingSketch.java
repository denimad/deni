/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Canvas.CanvasManager;
import Canvas.CanvasObject;
import processing.core.PApplet;

/**
 *
 * @author daudirac
 */
public class ProcessingSketch extends PApplet
{
    public void settings() {
        size(540,540);
        canvasManager = CanvasManager.getInstance();
        canvasManager.setCanvas(this);
        
        String[] args = {"controlWindow"};
        controlWindow = new ControlWindow();
        PApplet.runSketch(args, controlWindow);
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
   
    
    
    public CanvasManager canvasManager;
    public PApplet controlWindow;
}
