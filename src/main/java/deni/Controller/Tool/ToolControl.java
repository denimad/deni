/* 
 * deni 2017
 */
package main.java.deni.Controller.Tool;

import main.java.deni.Canvas.Listener.CanvasListenerType;
import main.java.deni.Canvas.CanvasManager;
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Controller.ControlFrame;
import main.java.deni.Controller.ControlFrameWriter;
import main.java.deni.Controller.ControlFrameWriterOwner;
import main.java.deni.Tool.ToolInterface;
import main.java.deni.Tool.ToolManager;
import controlP5.ControlEvent;
import controlP5.ScrollableList;
import controlP5.Toggle;

/**
 * ToolControl writes the selected Tool Controls to 
 * the ControlFrame
 */
public class ToolControl implements ControlFrameWriterOwner
{
	ToolManager toolManager;
	DeniCanvas owner;
	ControlFrame toolControlFrame;
	ControlFrame toolChooserFrame; 
	
	ControlFrameWriter toolChooserFrameWriter;
	
	String previousSelectedToolName;
	String currentSelectedToolName;
	
	int[] toolPosition; 
	public ToolControl(DeniCanvas _owner)
	{
		owner = _owner;
		toolManager = new ToolManager();
		
		toolPosition= new int[]{20,20};
	}
	
	public void initFrames()
	{
		
		toolControlFrame = new ControlFrame(owner,
		400,300,10,10,"toolControlFrame");
		
		toolChooserFrame = new ControlFrame(owner,
		400,200,10,310,"toolChooserFrame");
		
		
		this.setControlFrameWriter(toolChooserFrame.
				createNewControlFrameWriter(this));
		
	}
	
	public void addTool(ToolInterface tool)
	{
		// create a control frame writer of the Tool Control Frame 
		// for the tool.
		tool.setControlFrameWriter(
			this.toolControlFrame.createNewControlFrameWriter(tool));
		
		//add to tool listener manager.
		CanvasManager.getInstance().getCanvasListener(
			CanvasListenerType.ToolListener.getName()
		).addAllInputListeners(tool);
		
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
	public void resetControlFrameWriter() 
	{
		this.toolChooserFrameWriter.resetController();
	}

	@Override
	public void setControls() 
	{
		for (String toolName: this.toolManager.getToolNames())
		{
			this.toolChooserFrameWriter.addToogle(toolName, 
				toolPosition[0], 
				toolPosition[1], 
				20, 
				20, "default");
			
			this.calculateNextToolPosition();
		}
		
		//this.toolChooserFrame.showControllers();
	}
	
	private void calculateNextToolPosition()
	{
		toolPosition[1]+=40;
	}
	
	public void hideToolControls(ToolInterface tool)
	{
		if (tool != null)
		{
			tool.hideControls();
		}
		
	}
	
	public void setToolControls(ToolInterface tool)
	{
		if (tool != null)
		{
			tool.showControls();
		}
	}
	
	
	public void controlEvent(ControlEvent theEvent) 
	{
		this.previousSelectedToolName = 
			this.currentSelectedToolName;
		
		this.currentSelectedToolName = theEvent.getName();
		
		boolean isActive = ((Toggle) theEvent.getController()).getState();
		
		if (isActive)
		{
			this.toolManager.setActiveTool(
				this.currentSelectedToolName);
		}	
		else 
		{
			this.toolManager.unSetActiveTool(
				this.currentSelectedToolName);
		}
		
		//hide the previous shown tool controller.
		this.hideToolControls(this.toolManager.getTool(
				this.previousSelectedToolName));
		
		//show the new selected tool controller.
		this.setToolControls(this.toolManager.getTool(
				this.currentSelectedToolName));
	}
	
	
	//currently not it use 
	@Deprecated
	public void toolChanged(int n)
	{
		String choosenToolName = (String) this.toolChooserFrameWriter.getController(
			ScrollableList.class, "toolChanged").getItem(n).get("name");
		
		this.setToolControls(this.toolManager.getTool(choosenToolName));
	}

	
	@Override
	public void hideControls() {
		this.toolChooserFrameWriter.hideControlls();
	}

	@Override
	public void showControls() {
		this.toolChooserFrameWriter.showControlls();
	}

	@Override
	public void toggleControlsVisibility() {
		this.toolChooserFrameWriter.toggleControllsVisibility();
	}
	
}
