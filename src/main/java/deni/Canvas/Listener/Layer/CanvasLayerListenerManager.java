/*
 * deni 2017
 */
package main.java.deni.Canvas.Listener.Layer;

import main.java.deni.Canvas.Layer.CanvasLayersManager;
import main.java.deni.Canvas.Listener.CanvasListenerManager;
import main.java.deni.Canvas.DeniCanvas;

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
