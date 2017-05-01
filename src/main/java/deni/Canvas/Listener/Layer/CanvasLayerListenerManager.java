/*
 * deni 2017
 */
package main.java.deni.Canvas.Listener.Layer;

import main.java.deni.Canvas.Layer.DCanvasLayersManager;
import main.java.deni.Canvas.Listener.CanvasListenerManager;
import main.java.deni.Canvas.DeniCanvas;

/**
 * This method handles canvas listener events for singleton canvas layers.
 * @author daudirac
 */
public class CanvasLayerListenerManager extends CanvasListenerManager<DCanvasLayersManager>
{
	
	public CanvasLayerListenerManager(DeniCanvas parentCanvas) 
	{
		super(parentCanvas);
		
		//add the input listener object
		this.addAllInputListeners(DCanvasLayersManager.getInstance());
	}
	
	
}
