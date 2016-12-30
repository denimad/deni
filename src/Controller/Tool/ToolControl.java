/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Tool;

import Canvas.CanvasListenerType;
import Canvas.CanvasManager;
import Canvas.DeniCanvas;
import Controller.ControlFrame;
import Controller.ControlFrameWriter;
import Controller.ControlFrameWriterOwner;
import Tool.ToolInterface;
import Tool.ToolManager;
import controlP5.ControlEvent;
import controlP5.ScrollableList;
import controlP5.Toggle;

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
		
		//add to tool listener manager.
		CanvasManager.getInstance().getCanvasListener(
			CanvasListenerType.ToolListener.getName()
		).addMouseListeners(tool);
		
		//add to the tool list. all tools are disabled when added. 
		tool.setActive(false);
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
		
		/*this.toolChooserFrameWriter.addScrollableList(
			"toolChanged",
			0, 
			0, 
			this.toolManager.getToolNames(), 
			"default");
		*/
		for (String toolName: this.toolManager.getToolNames())
		{
			this.toolChooserFrameWriter.addToogle(toolName, 
				0, 
				0, 
				20, 
				20, "default");
		}
		
		this.toolChooserFrame.showControllers();
	}
	
	public void setToolControls(ToolInterface tool)
	{
		this.toolControlFrame.hideControllers();
		this.toolControlFrame.removeAllControls();
		tool.setControls();
		this.toolControlFrame.showControllers();
	}
	
	public void controlEvent(ControlEvent theEvent) 
	{
		String choosenToolName = theEvent.getName();
		boolean isActive = ((Toggle) theEvent.getController()).getState();
		
		if (isActive)
		{
			this.toolManager.setActiveTool(choosenToolName);
		}	
		else 
		{
			this.toolManager.unSetActiveTool(choosenToolName);
		}
		
		this.setToolControls(this.toolManager.getTool(choosenToolName));
	}
	
	public void toolChanged(int n)
	{
		String choosenToolName = (String) this.toolChooserFrameWriter.getController(
			ScrollableList.class, "toolChanged").getItem(n).get("name");
		
		this.setToolControls(this.toolManager.getTool(choosenToolName));
	}
	
}
