/* 
 * deni 2017
 */
package main.java.deni.Canvas.Listener.Tool;

import main.java.deni.Canvas.Listener.CanvasListenerManager;
import main.java.deni.Canvas.DeniCanvas;
import main.java.deni.Tool.DToolInterface;

/**
 * ToolCanvasManager manages the tool interaction
 * with canvas.
 * Only Active Tool can interact with canvas.
 */
public class ToolCanvasListenerManager extends CanvasListenerManager<DToolInterface>
{
	
	public ToolCanvasListenerManager(DeniCanvas parentCanvas) 
	{
		super(parentCanvas);
	}
	
	@Override
	public void onMousePressed(int mouseX, int mouseY)
    {
        for (DToolInterface tool : this.onMousePressedListeners )
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
		for (DToolInterface tool : this.onMouseReleasedListeners )
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
		for (DToolInterface tool : this.onMouseClickedListeners )
		{
			if (tool.isActive())
			{
				tool.onMouseClicked(mouseX, mouseY);
			}
		}
	}
	
	@Override
	public void onKeyPressed(char key)
	{
		for (DToolInterface tool : this.onKeyPressedListeners)
		{
			if (tool.isActive())
			{
				tool.onKeyPressed(key);
			}
		}
	}
	
}
