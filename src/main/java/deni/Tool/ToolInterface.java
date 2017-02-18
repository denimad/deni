/* 
 * deni 2017
 */
package main.java.deni.Tool;

import main.java.deni.Controller.ControlFrameWriterOwner;
import main.java.deni.Drawing.DrawingObject;
import controlP5.ControlListener;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;

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
