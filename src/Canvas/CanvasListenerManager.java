/* 
 * deni 2017
 */
package Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daudirac
 */
public class CanvasListenerManager<T extends CanvasObject> 
{
	public DeniCanvas canvas;
	
	public CanvasListenerManager(DeniCanvas parentCanvas)
	{
		canvas = parentCanvas;
		this.initListenerLists();
	}
	
	 public void initListenerLists()
    {
        this.onMousePressedListeners = new ArrayList();
        this.onMouseDraggedListeners = new ArrayList();
        this.onMouseReleasedListeners = new ArrayList();
        this.onMouseClickedListeners = new ArrayList();
        this.onKeyPressedListeners = new ArrayList();
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
    
    public void addOnMouseClickedListeners(T canvasObject)
    {
        this.onMouseClickedListeners.add(canvasObject);
    }
	
	public void addMouseListeners(T canvasObject)
    {
        addOnMousePressedListener(canvasObject);
        addOnMouseDraggedListener(canvasObject);
        addOnMouseReleasedListener(canvasObject);
        addOnMouseClickedListeners(canvasObject);
    }
	
    public List<T> onMousePressedListeners;
    public List<T> onMouseDraggedListeners;
    public List<T> onMouseReleasedListeners;
    public List<T> onMouseClickedListeners;
    public List<T> onKeyPressedListeners;
}
