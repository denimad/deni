/* 
 * deni 2017
 */
package main.java.deni.Controller;

/**
 * This interface describes a class that owns a control frame writer.
 * Generally this classes will be tool drawing objects in the canvas
 * that use a graphic controller to control their values.
 * 
 * How to use:
 * The control frame will generate its control frame writer and
 * return it to the drawing object.
 */
public interface DControlFrameWriterOwner 
{
    public void setControlFrameWriter(DControlFrameWriter cw);
    public DControlFrameWriter getControlFrameWriter();
    
	public void setControls();
	
	public void resetControlFrameWriter();
	
	// Controlls visibility methods
	public void hideControls();
	public void showControls();
	public void toggleControlsVisibility();
}
