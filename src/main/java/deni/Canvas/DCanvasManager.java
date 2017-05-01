/* 
 * deni 2017
 */
package main.java.deni.Canvas;

import main.java.deni.Canvas.Listener.CanvasInputAwareObject;
import main.java.deni.Canvas.Listener.CanvasListenerManager;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import processing.event.MouseEvent;

/**
 *
 * @author daudirac
 */
public final class DCanvasManager<T extends CanvasInputAwareObject> 
{
    private DCanvasManager()
    {
		canvasListeners = new HashMap<>();
    }
    
    public DeniCanvas getCanvas()
    {
        return canvas;
    }
    
    public void setCanvas(DeniCanvas _canvas)
    {
        canvas = _canvas;	
    }
	
	public void addCanvasListener(String listenerName,
			CanvasListenerManager<T> cm)
	{
		canvasListeners.put(listenerName, cm);
	}
	
	public Collection<CanvasListenerManager<T>> getCanvasListeners()
	{
		return this.canvasListeners.values();
	}
    
    public static DCanvasManager getInstance() 
	{
      return INSTANCE;
    }
	
    // Listener methods
	
	public CanvasListenerManager<T> getCanvasListener(
		String name)
	{
		return this.canvasListeners.get(name);
	}
	
	
	public void mouseDragged(int mouseX, int mouseY)
    {
		Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onMouseDragged(mouseX, mouseY);
		}
	}
	public void mouseReleased(int mouseX, int mouseY)
    {
		Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onMouseReleased(mouseX, mouseY);
		}
	}
	public void mousePressed(int mouseX, int mouseY)
    {
		Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onMousePressed(mouseX, mouseY);
		}
	}
	public void mouseClicked(int mouseX, int mouseY)
    {
        Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onMouseClicked(mouseX, mouseY);
		}
    }
	
	public void mouseWheel(MouseEvent e)
	{
		Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onMouseWheel(e);
		}
	}
	
	public void keyPressed(char key)
    {
        Iterator<CanvasListenerManager<T>> it = 
			canvasListeners.values().iterator();
		
		while (it.hasNext())
		{
			it.next().onKeyPressed(key);
		}
    }
	
    private static final DCanvasManager<CanvasInputAwareObject> INSTANCE = new DCanvasManager<>();
    private DeniCanvas canvas;
	
	//deni canvas can have different types of canvas object listeners.
	//we use the canvas manager to store these.
	private final Map<String, CanvasListenerManager<T>> 
			canvasListeners;
}
