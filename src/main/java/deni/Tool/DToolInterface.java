/* 
 * deni 2017
 */
package main.java.deni.Tool;

import controlP5.ControlListener;
import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import main.java.deni.Controller.DControlFrameWriterOwner;
import main.java.deni.Drawing.DDrawingObject;

/**
 *
 * @author daudirac
 */
public interface DToolInterface extends 
		DControlFrameWriterOwner, 
		DDrawingObject,
		CanvasInputAwareObject,
		ControlListener
{
	public String getName();
	public String getUUID();
	public boolean isActive();
	public void setActive(boolean active);
}
