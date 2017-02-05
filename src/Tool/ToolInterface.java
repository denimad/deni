/* 
 * deni 2017
 */
package Tool;

import Controller.ControlFrameWriterOwner;
import Drawing.DrawingObject;
import controlP5.ControlListener;
import Canvas.Listener.CanvasInputAwareObject;

/**
 *
 * @author daudirac
 */
public interface ToolInterface extends 
		ControlFrameWriterOwner, 
		DrawingObject,
		CanvasInputAwareObject,
		ControlListener
{
	public String getName();
	public boolean isActive();
	public void setActive(boolean active);
}
