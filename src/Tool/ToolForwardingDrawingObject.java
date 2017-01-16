/* 
 * deni 2017
 */
package Tool;

import Drawing.ForwardingDrawingObject;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Drawing.DrawingObjectImpl;
import Tool.ToolInterface;
import controlP5.ControlEvent;

/**
 *
 * @author daudirac
 */
public abstract class ToolForwardingDrawingObject extends ForwardingDrawingObject
		implements ToolInterface
{
	
	public ToolForwardingDrawingObject(DrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		active = false;
	}

	public DrawingObjectImpl getDrawingObject()
	{
		return this.drawingObj;
	}
	
	
	// = = = = = = = = = = = = = = = = = =
	// ControlFrameWriterOwner Interface methods.
	// = = = = = = = = = = = = = = = = = =
	@Override
	public void setControlFrameWriter(ControlFrameWriter cw) {
		this.controlFrameWriter = cw;
		
		this.controlFrameWriter.hideControlls();
		this.setControls();
	}

	@Override
	public ControlFrameWriter getControlFrameWriter() {
		return this.controlFrameWriter;
	}
	
	public void resetControlFrameWriter()
	{
		this.controlFrameWriter.resetController();
	}
	

	@Override
	public abstract void setControls();
	
	
	public void hideControls()
	{
		this.controlFrameWriter.hideControlls();
	}
	public void showControls()
	{
		this.controlFrameWriter.showControlls();
	}
	public void toggleControlsVisibility()
	{
		this.controlFrameWriter.toggleControllsVisibility();
	}
	
	
	// = = = = = = = = = = = = = = = = = =
	// TOOL Interface methods.
	// = = = = = = = = = = = = = = = = = =
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
	
	
	// = = = = = = = = = = = = = = = = = =
	// CONTROLLISTENER Interface methods.
	// = = = = = = = = = = = = = = = = = =
	/**
	 * Override this method when the tool needs
	 * to manage the control events.
	 * @param theEvent the event triggered by a CP5 
	 * controller action.
	 */
	@Override
	public void controlEvent(ControlEvent theEvent) 
	{
		
	}
	/**
     * The control frame writer belonging to this tool
     */
    public ControlFrameWriter controlFrameWriter;
	
	private boolean active;

	
}
