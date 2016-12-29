/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Tool;

import Canvas.DeniCanvas;
import Controller.ControlFrame;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Tool.ToolInterface;
import Tool.ToolManager;
import controlP5.ScrollableList;

/**
 *
 * @author daudirac
 */
public class ToolControl implements ControlFrameWriterOwner
{
	ToolManager toolManager;
	DeniCanvas owner;
	ControlFrame toolControlFrame;
	ControlFrame toolChooserFrame; 
	
	ControlFrameWriter toolChooserFrameWriter;
	
	public ToolControl(DeniCanvas _owner)
	{
		owner = _owner;
		toolManager = new ToolManager();
		
	}
	
	public void initFrames()
	{
		
		toolControlFrame = new ControlFrame(owner,
		200,200,"toolControlFrame");
		
		toolChooserFrame = new ControlFrame(owner,
		200,200,"toolChooserFrame");
		
		toolControlFrame.hideControllers();
		toolChooserFrame.hideControllers();
		
		this.setControlFrameWriter(toolChooserFrame.
				createNewControlFrameWriter(this));
		
	}
	
	public void addTool(ToolInterface tool)
	{
		// create a control frame writer for the tool
		// this will be used by the tool to writes its controls.
		tool.setControlFrameWriter(
			this.toolControlFrame.createNewControlFrameWriter(tool));
		
		//add to all the listener of the main canvas.
		owner.canvasManager.addMouseListeners(tool);
		
		//add to the tool list
		this.toolManager.addTool(tool);
	}

	@Override
	public void setControlFrameWriter(ControlFrameWriter cw) 
	{
		toolChooserFrameWriter = cw;
	}

	@Override
	public ControlFrameWriter getControlFrameWriter() 
	{
		return this.toolChooserFrameWriter;
	}

	@Override
	public void setControls() 
	{
		
		this.toolChooserFrameWriter.addScrollableList(
			"toolChanged",
			0, 
			0, 
			this.toolManager.getToolNames(), 
			"default");
		
		this.toolChooserFrame.showControllers();
	}
	
	public void setToolControls(ToolInterface tool)
	{
		//this.toolControlFrame.hideControllers();
		this.toolControlFrame.removeAllControls();
		tool.setControls();
		this.toolControlFrame.showControllers();
	}
	
	
	public void toolChanged(int n)
	{
		String chooseToolName = (String) this.toolChooserFrameWriter.getController(
			ScrollableList.class, "toolChanged").getItem(n).get("name");
		
		this.setToolControls(this.toolManager.getTool(chooseToolName));
	}
	
}
