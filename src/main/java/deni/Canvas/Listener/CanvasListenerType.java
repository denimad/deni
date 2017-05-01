/* 
 * deni 2017
 */
package main.java.deni.Canvas.Listener;

import main.java.deni.Canvas.DCanvasManager;
import main.java.deni.Canvas.Listener.CanvasListenerManager;
import main.java.deni.Canvas.Listener.Layer.CanvasLayerListenerManager;
import main.java.deni.Canvas.Listener.Tool.ToolCanvasListenerManager;

/**
 *
 * @author daudirac
 */
public enum CanvasListenerType
{
	ToolListener("toolListener", 
		new ToolCanvasListenerManager(
			DCanvasManager.getInstance().getCanvas())),
	LayerListener("layerListener",
		new CanvasLayerListenerManager(
			DCanvasManager.getInstance().getCanvas()));
	
	
	CanvasListenerType(String _name,
		CanvasListenerManager _canvasListener)
	{
		name = _name;
		canvasListener = _canvasListener;
	}
	
	public String getName()
	{
		return name;
	}
	public CanvasListenerManager getCanvasListener()
	{
		return this.canvasListener;
	}
	
	private final String name;
	private final CanvasListenerManager canvasListener;
}
