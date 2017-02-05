/* 
 * deni 2017
 */
package Canvas.Listener;

import Canvas.CanvasManager;
import Canvas.Listener.CanvasListenerManager;
import Canvas.Listener.Layer.CanvasLayerListenerManager;
import Canvas.Listener.Tool.ToolCanvasListenerManager;

/**
 *
 * @author daudirac
 */
public enum CanvasListenerType
{
	ToolListener("toolListener", 
		new ToolCanvasListenerManager(
			CanvasManager.getInstance().getCanvas())),
	LayerListener("layerListener",
		new CanvasLayerListenerManager(
			CanvasManager.getInstance().getCanvas()));
	
	
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
