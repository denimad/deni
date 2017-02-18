/* 
 * deni 2017
 */
package main.java.deni.Tool;

import main.java.deni.Drawing.ForwardingDrawingObject;
import main.java.deni.Controller.ControlFrameWriter;
import main.java.deni.Controller.ControlFrameWriterOwner;
import main.java.deni.Drawing.DrawingObjectImpl;
import main.java.deni.Tool.ToolInterface;
import main.java.deni.Color.ColorHelper;
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
	public void setControls()
	{
		this.writeControlTitle();
	}
	
	public void writeControlTitle()
	{
		for (String tabName : this.controlFrameWriter.getTabNames())
		{
			this.controlFrameWriter.addTextLabel("title"+tabName, 
			"--------TOOL: "+this.getName() + "----------", 
			40, 
			20, ColorHelper.WHITE, tabName);
		}	
	}
	
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
