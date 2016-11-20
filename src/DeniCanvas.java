/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Canvas.CanvasManager;
import Canvas.CanvasObject;
import java.util.HashMap;
import java.util.Map;
import processing.core.PApplet;
import processing.core.PGraphics;

/**
 *
 * @author daudirac
 */
public class DeniCanvas extends PApplet
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
   
    private void initLayers()
    {
    
    }
    
    public CanvasManager canvasManager;
    
    // this canvas has different layers
    // the main is the final drawing. the tool layer is used to show
    // visual guides for tools. And Test layer is to do tests before
    // drawing on the main layer.
    public Map<String, PGraphics> layersMap;
    
    public PApplet controlWindow;
}
