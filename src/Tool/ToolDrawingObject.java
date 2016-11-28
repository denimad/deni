/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import Canvas.CanvasObject;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Object.Drawing.DrawingObject;
import processing.core.PGraphics;

/**
 * This class represent objects that draws on the canvas,
 * support interaction and can use ControllerP5 to modify
 * internal variables.
 */
public class ToolDrawingObject implements
    DrawingObject, ControlFrameWriterOwner, CanvasObject
{
    public ToolDrawingObject()
    {
    
    }
    
    
    /**
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     * DRAWING METHODS
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     */
   
    
    
    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(PGraphics canvasLayer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDrawingProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     * CONTROL METHODS
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     */

    @Override
    public void setControlFrameWriter(ControlFrameWriter cw) {
        this.controlFrameWriter = cw;
        this.setControls();
    }

    @Override
    public ControlFrameWriter getControlFrameWriter() {
        return this.controlFrameWriter;
    }
    
    
    /**
     * All sub classes should individually set in this method
     * the controllers assigned to this tool.
     */
    @Override
    public void setControls() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     * INTERACTION METHODS
     * = = = = = = = = = = = = = = = = = = = = = = = = = = = = 
     */
    
    @Override
    public void onMousePressed(int mouseX, int mouseY) {
        
    }

    @Override
    public void onMouseDragged(int mouseX, int mouseY) {
        
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY) {
        
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY) {
        
    }

    @Override
    public void onKeyPressed(char key) {
        
    }

    /**
     * The control frame writer belonging to this tool
     */
    public ControlFrameWriter controlFrameWriter;
}