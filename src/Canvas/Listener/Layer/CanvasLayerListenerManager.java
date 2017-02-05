/*
 * deni 2017
 */
package Canvas.Listener.Layer;

import Canvas.Layer.CanvasLayersManager;
import Canvas.Listener.CanvasListenerManager;
import Canvas.DeniCanvas;

/**
 * This method handles canvas listener events for singleton canvas layers.
 * @author daudirac
 */
public class CanvasLayerListenerManager extends CanvasListenerManager<CanvasLayersManager>
{
	
	public CanvasLayerListenerManager(DeniCanvas parentCanvas) 
	{
		super(parentCanvas);
		
		//add the input listener object
		this.addAllInputListeners(CanvasLayersManager.getInstance());
	}
	
	
}
