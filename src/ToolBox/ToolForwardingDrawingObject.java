/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ToolBox;

import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Object.Drawing.DrawingObjectImpl;

/**
 *
 * @author daudirac
 */
public class ToolForwardingDrawingObject extends ForwardingDrawingObject
		implements ControlFrameWriterOwner
{
	
	public ToolForwardingDrawingObject(DrawingObjectImpl drawingObj) 
	{
		super(drawingObj);		
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
	
	/**
     * The control frame writer belonging to this tool
     */
    public ControlFrameWriter controlFrameWriter;
}
