/*
 * deni2016
 */
package Tool;

import Canvas.CanvasObject;
import Controller.ControlFrameWriterOwner;
import Object.Drawing.DrawingObject;

/**
 *
 * @author daudirac
 */
public interface ToolInterface extends 
		ControlFrameWriterOwner, 
		DrawingObject,
		CanvasObject
{
	public String getName();
	public boolean isActive();
	public void setActive(boolean active);
}
