/*
 * deni2016
 */
package Tool;

import Canvas.CanvasObject;
import Controller.ControlFrameWriterOwner;
import Drawing.DrawingObject;
import controlP5.ControlListener;

/**
 *
 * @author daudirac
 */
public interface ToolInterface extends 
		ControlFrameWriterOwner, 
		DrawingObject,
		CanvasObject,
		ControlListener
{
	public String getName();
	public boolean isActive();
	public void setActive(boolean active);
}
