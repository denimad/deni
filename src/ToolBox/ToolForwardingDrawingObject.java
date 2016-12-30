/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox;

import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Object.Drawing.DrawingObjectImpl;
import Tool.ToolInterface;

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

	@Override
	public void setControlFrameWriter(ControlFrameWriter cw) {
		this.controlFrameWriter = cw;
		this.controlFrameWriter.setVariableObject(this.drawingObj);
        this.setControls();
	}

	@Override
	public ControlFrameWriter getControlFrameWriter() {
		return this.controlFrameWriter;
	}

	@Override
	public void setControls() 
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
    public ControlFrameWriter controlFrameWriter;
	
	private boolean active;

	
}
