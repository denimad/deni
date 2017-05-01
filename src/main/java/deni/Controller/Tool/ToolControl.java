/* 
 * deni 2017
 */
package main.java.deni.Controller.Tool;

import main.java.deni.Canvas.Listener.CanvasListenerType;
import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Controller.DControlFrame;
import main.java.deni.Controller.DControlFrameWriter;
import main.java.deni.Tool.DToolManager;
import controlP5.ControlEvent;
import controlP5.ScrollableList;
import controlP5.Toggle;
import main.java.deni.Controller.DControlFrameWriterOwner;
import main.java.deni.Tool.DToolInterface;

/**
 * ToolControl writes the selected Tool Controls to 
 * the ControlFrame
 */
public class ToolControl implements DControlFrameWriterOwner
{
	DToolManager toolManager;
	DeniCanvas owner;
	DControlFrame toolControlFrame;
	DControlFrame toolChooserFrame; 
	
	DControlFrameWriter toolChooserFrameWriter;
	
	String previousSelectedToolName;
	String currentSelectedToolName;
	
	int[] toolPosition; 
	public ToolControl(DeniCanvas _owner)
	{
		owner = _owner;
		toolManager = new DToolManager();
		
		toolPosition= new int[]{20,20};
	}
	
	public void initFrames()
	{
		
		toolControlFrame = new DControlFrame(owner,
		400,300,10,10,"toolControlFrame");
		
		toolChooserFrame = new DControlFrame(owner,
		400,200,10,310,"toolChooserFrame");
		
		
		this.setControlFrameWriter(toolChooserFrame.
				createNewControlFrameWriter(this));
		
	}
	
	public void addTool(DToolInterface tool)
	{
		// create a control frame writer of the Tool Control Frame 
		// for the tool.
		tool.setControlFrameWriter(
			this.toolControlFrame.createNewControlFrameWriter(tool));
		
		//add to tool listener manager.
		DCanvasManager.getInstance().getCanvasListener(
			CanvasListenerType.ToolListener.getName()
		).addAllInputListeners(tool);
		
		//add to the tool list. all tools are disabled when added. 
		tool.setActive(false);
		this.toolManager.addTool(tool);
	}

	@Override
	public void setControlFrameWriter(DControlFrameWriter cw) 
	{
		toolChooserFrameWriter = cw;
	}

	@Override
	public DControlFrameWriter getControlFrameWriter() 
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
	
	public void hideToolControls(DToolInterface tool)
	{
		if (tool != null)
		{
			tool.hideControls();
		}
		
	}
	
	public void setToolControls(DToolInterface tool)
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
