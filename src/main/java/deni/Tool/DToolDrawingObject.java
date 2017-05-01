/* 
 * deni 2017
 */
package main.java.deni.Tool;

import main.java.deni.Canvas.Layer.PGraphics.DAbstractPGraphics;
import main.java.deni.Controller.DControlFrameWriter;
import processing.core.PGraphics;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import processing.event.MouseEvent;
import main.java.deni.Controller.DControlFrameWriterOwner;
import main.java.deni.Drawing.DDrawingObject;

/**
 * This class represent objects that draws on the canvas,
 * support interaction and can use ControllerP5 to modify
 * internal variables.
 */
public abstract class DToolDrawingObject implements
    DToolInterface
{
    public DToolDrawingObject()
    {
		active = true;
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
    public void draw(DAbstractPGraphics canvasLayer) {
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
    public void setControlFrameWriter(DControlFrameWriter cw) {
        this.controlFrameWriter = cw;
        this.setControls();
    }

    @Override
    public DControlFrameWriter getControlFrameWriter() {
        return this.controlFrameWriter;
    }
    
    public void resetControlFrameWriter()
	{
		this.controlFrameWriter.resetController();
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
	
	@Override
	public void onMouseWheel(MouseEvent e)
	{
	}

	@Override
	public boolean isActive() {
			return this.active;
	}
	
	@Override
	public void setActive(boolean active) {
			this.active = active;
	}
	
	@Override
	public abstract String getName();
	
    /**
     * The control frame writer belonging to this tool
     */
    public DControlFrameWriter controlFrameWriter;
	
	private boolean active;
	
}
