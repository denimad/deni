/* 
 * deni 2017
 */
package main.java.deni.Tool;

import main.java.deni.Drawing.DForwardingDrawingObject;
import main.java.deni.Controller.DControlFrameWriter;
import main.java.deni.Drawing.DDrawingObjectImpl;
import main.java.deni.Color.DColorHelper;
import controlP5.ControlEvent;
import main.java.deni.Controller.DControlFrameWriterOwner;
import main.java.deni.Tool.DToolInterface;

/**
 *
 * @author daudirac
 */
public abstract class DToolForwardingDrawingObject extends DForwardingDrawingObject
		implements DToolInterface
{
	
	public DToolForwardingDrawingObject(DDrawingObjectImpl drawingObj) 
	{
		super(drawingObj);
		active = false;
	}

	public DDrawingObjectImpl getDrawingObject()
	{
		return this.drawingObj;
	}
	
	
	// = = = = = = = = = = = = = = = = = =
	// ControlFrameWriterOwner Interface methods.
	// = = = = = = = = = = = = = = = = = =
	@Override
	public void setControlFrameWriter(DControlFrameWriter cw) {
		this.controlFrameWriter = cw;
		
		this.controlFrameWriter.hideControlls();
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
			20, DColorHelper.WHITE, tabName);
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
    public DControlFrameWriter controlFrameWriter;
	
	private boolean active;

	
}
