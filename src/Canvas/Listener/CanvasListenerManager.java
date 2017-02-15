/* 
 * deni 2017
 */
package Canvas.Listener;

import Canvas.DeniCanvas;
import java.util.ArrayList;
import java.util.List;
import processing.event.MouseEvent;

/**
 *
 * @author daudirac
 * @param <T> generic for object types that listen user inputs.
 */
public class CanvasListenerManager<T extends CanvasInputAwareObject> 
	implements CanvasInputAwareObject
{
	public DeniCanvas parentCanvas;
	
	public CanvasListenerManager(DeniCanvas parentCanvas)
	{
		this.parentCanvas = parentCanvas;
		this.initListenerLists();
	}
	
	 public void initListenerLists()
    {
        this.onMousePressedListeners = new ArrayList();
        this.onMouseDraggedListeners = new ArrayList();
        this.onMouseReleasedListeners = new ArrayList();
        this.onMouseClickedListeners = new ArrayList();
		this.onMouseWheelListeners = new ArrayList();
        this.onKeyPressedListeners = new ArrayList();
		this.onKeyReleasedListeners = new ArrayList();
    }
	 
	public void onMousePressed(int mouseX, int mouseY)
    {
        for (T object : this.onMousePressedListeners )
        {
					object.onMousePressed(mouseX, mouseY);
		}
	} 
	
	public void onMouseDragged(int mouseX, int mouseY)
    {
        for (T object : this.onMouseDraggedListeners )
        {
            object.onMouseDragged(mouseX, mouseY);
        }
    }
	
	public void onMouseReleased(int mouseX, int mouseY)
    {
        for (T object : this.onMouseReleasedListeners )
        {
            object.onMouseReleased(mouseX, mouseY);
        }
    }
	 
	public void onMouseClicked(int mouseX, int mouseY)
    {
        for (T object : this.onMouseClickedListeners )
        {
            object.onMouseClicked(mouseX, mouseY);
        }
    }
	
	@Override
	public void onMouseWheel(MouseEvent e) 
	{
		for (T object : this.onMouseWheelListeners )
        {
            object.onMouseWheel(e);
        }
	}
	
	public void onKeyPressed(char key)
	{
		for (T object : this.onKeyPressedListeners )
        {
            object.onKeyPressed(key);
        }
	}
	
	@Override
	public void onKeyReleased() {
		for (T object : this.onKeyReleasedListeners )
        {
            object.onKeyReleased();
        }
	}


	public void addOnMousePressedListener(T canvasObject)
    {
        this.onMousePressedListeners.add(canvasObject);
    }
    
    public void addOnMouseDraggedListener(T canvasObject)
    {
        this.onMouseDraggedListeners.add(canvasObject);
    }
    
    public void addOnMouseReleasedListener(T canvasObject)
    {
        this.onMouseReleasedListeners.add(canvasObject);
    }
    
    public void addOnMouseClickedListener(T canvasObject)
    {
        this.onMouseClickedListeners.add(canvasObject);
    }
	
	public void addOnMouseWheelListener(T canvasObject)
    {
        this.onMouseWheelListeners.add(canvasObject);
    }

	public void addOnKeyPressedListener(T canvasObject)
	{
		this.onKeyPressedListeners.add(canvasObject);
	}
    
	public void addOnKeyReleasedListener(T canvasObject)
	{
		this.onKeyReleasedListeners.add(canvasObject);
	}

	public void addMouseListeners(T canvasObject)
    {
        addOnMousePressedListener(canvasObject);
        addOnMouseDraggedListener(canvasObject);
        addOnMouseReleasedListener(canvasObject);
        addOnMouseClickedListener(canvasObject);
		addOnMouseWheelListener(canvasObject);
    }
	
	public void addKeyListeners(T canvasObject)
	{
		addOnKeyPressedListener(canvasObject);
		addOnKeyReleasedListener(canvasObject);
	}


	public void addAllInputListeners(T canvasObject)
	{
		addMouseListeners(canvasObject);
		addKeyListeners(canvasObject);
	}
	
    public List<T> onMousePressedListeners;
    public List<T> onMouseDraggedListeners;
    public List<T> onMouseReleasedListeners;
    public List<T> onMouseClickedListeners;
	public List<T> onMouseWheelListeners;
    public List<T> onKeyPressedListeners;
	public List<T> onKeyReleasedListeners;
}
