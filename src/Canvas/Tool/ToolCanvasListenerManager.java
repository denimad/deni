/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Canvas.Tool;

import Canvas.CanvasListenerManager;
import Canvas.DeniCanvas;
import Tool.ToolInterface;

/**
 * ToolCanvasManager manages the tool interaction
 * with canvas.
 * Only Active Tool can interact with canvas.
 */
public class ToolCanvasListenerManager extends CanvasListenerManager<ToolInterface>
{
	
	public ToolCanvasListenerManager(DeniCanvas parentCanvas) 
	{
		super(parentCanvas);
	}
	
	@Override
	public void onMousePressed(int mouseX, int mouseY)
    {
        for (ToolInterface tool : this.onMousePressedListeners )
        {
			if (tool.isActive())
			{
					tool.onMousePressed(mouseX, mouseY);
			}
		}
	} 
	
	public void onMouseDragged(int mouseX, int mouseY)
	{
		this.onMouseDraggedListeners.stream().filter((tool) -> (tool.isActive())).forEach((tool) -> {
			tool.onMouseDragged(mouseX, mouseY);
		});
	}

	@Override
	public void onMouseReleased(int mouseX, int mouseY)
	{
		for (ToolInterface tool : this.onMouseReleasedListeners )
		{
			if (tool.isActive())
			{
				tool.onMouseReleased(mouseX, mouseY);
			}
		}
	}

	@Override
	public void onMouseClicked(int mouseX, int mouseY)
	{
		for (ToolInterface tool : this.onMouseClickedListeners )
		{
			if (tool.isActive())
			{
				tool.onMouseClicked(mouseX, mouseY);
			}
		}
	}
	
}
